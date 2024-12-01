import java.io.*;
import java.util.*;
public class FileUpdater {
    public static void main(String[] args) {
        String file1 = "Coursera/config/config.properties"; // Replace with your actual file name
     	String file0 = "Main/config/config.properties"; 
	String file2 = "NTI/config/config.properties";
	String file3 = "Grant/config/config.properties";
	String file4 = "NY/config/config.properties";
	String file5 = "Hotmail/config/config.properties";
	String file6 = "SentEmail/config/config.properties";
	
	List<String> list = new ArrayList<>();
	list.add(file1);
	list.add(file2);
	list.add(file3);
	list.add(file0);
	list.add(file4);
	list.add(file5);
	list.add(file6);
	
 	String tempFileName = "tmp"; // Temporary file name


	for(String file:list)
	{
        try {
            // Read the original file content
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder input = new StringBuilder();
            while ((line = fileReader.readLine()) != null) {
                if (line.startsWith("email=")) {
                    // Replace the email part after "Email="
                    String newEmail = args[0]; // Replace with the desired email
                    line = "email=" + newEmail;
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
}

