package opp.ex6.main;

import java.io.*;

public class FileOperations {
    public static void main(String[] args) throws IOException {
        // File to be read

        File file = new File("/cs/usr/itayyamin/Desktop/oop_ex6/tests/fucntion_signature_tests/fucntion_signature_test_should_fail");

        // Subfolder to be created
        File subfolder = new File("CURRENT_TEST");
        if (!subfolder.exists()) {
            subfolder.mkdir();
        }

        // Reading file line by line
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        int lineCount = 0;
        while ((line = br.readLine()) != null) {
            lineCount++;
            // Writing each line to a new file
            File newFile = new File(subfolder, "line" + lineCount + ".txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
            bw.write(line);
            bw.close();
        }
        br.close();
    }
}
