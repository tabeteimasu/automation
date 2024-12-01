import java.io.*;
import java.util.*;
public class FileUpdater {
    public static void main(String[] args) {
        String file = "GameflipSold/config/config.properties"; // Replace with your actual file name
	
 	String tempFileName = "tmp"; // Temporary file name


        try {
            // Read the original file content
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder input = new StringBuilder();
            while ((line = fileReader.readLine()) != null) {
                if (line.startsWith("gameflipURL=")) {
                    // Replace the email part after "Email="
                    String newEmail = args[0]; // Replace with the desired email
                    line = "gameflipURL=" + newEmail;
                }
                input.append(line).append("\n");
            }
            fileReader.close();

            // Write the modified content to the temporary file
            FileWriter tempFileWriter = new FileWriter(tempFileName);
            tempFileWriter.write(input.toString());
            tempFileWriter.close();

            // Rename the temporary file to the original file name
            File sourceFile = new File(tempFileName);
            File targetFile = new File(file);
            sourceFile.renameTo(targetFile);

            System.out.println("File "+file+" updated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

