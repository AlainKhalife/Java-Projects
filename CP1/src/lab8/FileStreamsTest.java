package lab8;

import java.io.*;

class FileStreamsTest {
    public static void main(String[] args) {
        try {
            File inputFile = new File("source.txt");
            File outputFile = new File("destination.txt");

            FileInputStream fis = new FileInputStream(inputFile);
            FileOutputStream fos = new FileOutputStream(outputFile);
            int c;

            while ((c = fis.read()) != -1) {
               fos.write(c);
	System.out.println("Copying the file was finished successfully!");
            }
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
    }
}

