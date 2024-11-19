import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Database {
    

    public String ReadAll(String filePath) throws IOException {
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.append(line).append(System.lineSeparator());
            }
        }
        return fileContent.toString();
    }

    public String ReadFile(String filePath, String required, int Column) throws IOException {
        StringBuilder fileContent = new StringBuilder();

        if (Column == -1) {
            // Search the entire line for an exact match
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().equals(required.trim())) {
                        fileContent.append(line).append(System.lineSeparator());
                        break;  // Stop once the required line is found
                    }
                }
            } catch (IOException e) {
                return "An error occurred while reading the file: " + e.getMessage();
            }
            return fileContent.toString().trim(); // Return the found line or empty string
        } else {
            // Search within a specific column
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] words = line.split("\\|"); // Split by '|'
                    if (words.length > Column && words[Column].trim().equals(required.trim())) {
                        fileContent.append(line).append(System.lineSeparator());
                    }
                }
            } catch (IOException e) {
                return "An error occurred while reading the file: " + e.getMessage(); // Error handling within the method
            }
            return fileContent.toString();
        }
    }


    public void UpdateFile(String filePath, String id, String old, String replace) throws IOException {
        File tempFile = new File("tempFile.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\|"); // Split by '|'

                if (words.length > 0 && words[0].trim().equals(id.trim())) { 
                    for (int i = 0; i < words.length; i++) {
                        if (words[i].trim().equalsIgnoreCase(old.trim())) {
                            words[i] = replace;
                        }
                    }
                    line = String.join("|", words); // Join with '|'
                }               
                bw.write(line);
                bw.newLine();
            }
        }       
        File originalFile = new File(filePath);
        if (originalFile.delete()) {
            if (!tempFile.renameTo(originalFile)) {
                throw new IOException("Could not rename temporary file.");
            }
        } else {
            throw new IOException("Could not delete the original file.");
        }
    }

 
    public void addnew(String filePath, String newline) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { 
            writer.write(newline);
            writer.newLine(); // Ensure the new line is properly separated
        }
    }

   
    public void deleteRecord(String filePath, String required, int column) throws IOException {
        File tempFile = new File("tempFile.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\|"); // Split by '|'
                if (words.length > column && words[column].trim().equals(required.trim())) {
                    found = true; 
                    continue; // Skip writing this line to delete it
                }
                bw.write(line);
                bw.newLine();
            }

            if (!found) {
                System.out.println("Record " + required + " not found.");
            }
        }
        File originalFile = new File(filePath);
        if (originalFile.delete()) {
            if (!tempFile.renameTo(originalFile)) {
                throw new IOException("Could not rename the temp file to the original file.");
            }
        } else {
            throw new IOException("Could not delete the original file.");
        }
    }

    
    public void replaceLine(String filePath, String targetLine, String newLine) throws IOException {
        File tempFile = new File("tempFile.txt");
        File originalFile = new File(filePath); 

        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().equals(targetLine.trim())) {
                    bw.write(newLine);
                } else {
                    bw.write(line);
                }
                bw.newLine();
            }
        }

        if (originalFile.delete()) {
            if (!tempFile.renameTo(originalFile)) {
                throw new IOException("Could not rename temporary file to original file.");
            }
        } else {
            throw new IOException("Could not delete the original file.");
        }
    }

    public void appendToLine(String filePath, String id, String[] newWords) throws IOException {
    File tempFile = new File("tempFile.txt");
    
    try (BufferedReader br = new BufferedReader(new FileReader(filePath));
         BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
    
        String line;
        boolean idFound = false;
        while ((line = br.readLine()) != null) {
            String[] words = line.split("\\|"); 
    
            if (words[0].equals(id)) {
                idFound = true;
                StringBuilder updatedLine = new StringBuilder(line); 
                
                
                for (String newWord : newWords) {
                    updatedLine.append("|").append(newWord); 
                }
                
                line = updatedLine.toString(); 
            }
    
            bw.write(line); 
            bw.newLine(); 
        }
    
        if (!idFound) {
            System.out.println("Appointment ID " + id + " not found.");
        }
    }
    
    // Replace the original file with the updated one
    File originalFile = new File(filePath);
    if (originalFile.delete()) {
        if (!tempFile.renameTo(originalFile)) {
            throw new IOException("Could not rename temporary file.");
        }
    } else {
        throw new IOException("Could not delete the original file.");
    }
}

    
}
