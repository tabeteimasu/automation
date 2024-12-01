package commonLibs.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CloseDriversUtilities {
    public static void main(String[] args) {
        List<Integer> chromeDriverPids = listChromeDriverPIDs();
        killChromeDriverProcesses(chromeDriverPids);
    }

    // Method to list all ChromeDriver PIDs
    private static List<Integer> listChromeDriverPIDs() {
        List<Integer> pids = new ArrayList<>();
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder processBuilder;
            if (os.contains("win")) {
                processBuilder = new ProcessBuilder("cmd.exe", "/c", "tasklist", "/FI", "IMAGENAME eq chromedriver.exe", "/FO", "LIST");
            } else {
                processBuilder = new ProcessBuilder("/bin/sh", "-c", "ps -e | grep chromedriver");
            }

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (os.contains("win")) {
                    if (line.startsWith("PID:")) {
                        String pid = line.split(":")[1].trim();
                        pids.add(Integer.parseInt(pid));
                    }
                } else {
                    if (line.contains("chromedriver")) {
                        String[] parts = line.trim().split("\\s+");
                        String pid = parts[0];
                        pids.add(Integer.parseInt(pid));
                    }
                }
            }
            reader.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return pids;
    }

    // Method to kill ChromeDriver processes by their PIDs
    private static void killChromeDriverProcesses(List<Integer> pids) {
        for (int pid : pids) {
            try {
                String os = System.getProperty("os.name").toLowerCase();
                ProcessBuilder processBuilder;
                if (os.contains("win")) {
                    processBuilder = new ProcessBuilder("cmd.exe", "/c", "taskkill", "/PID", String.valueOf(pid), "/F");
                } else {
                    processBuilder = new ProcessBuilder("/bin/sh", "-c", "kill", "-9", String.valueOf(pid));
                }

                Process process = processBuilder.start();
                process.waitFor();
                System.out.println("Terminated ChromeDriver process with PID: " + pid);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    
}
