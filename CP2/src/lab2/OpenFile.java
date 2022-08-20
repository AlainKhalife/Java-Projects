package lab2;
import java.io.*;
import java.util.Scanner;

public class OpenFile {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        QuadrilateralCollection qc = new QuadrilateralCollection();

        boolean foundFile = false;

        // Keep repeating until the file is found
        while(!foundFile)
        {
            System.out.println("What is the file name?");
            String filename = scan.nextLine();

            try {
                File f = new File(filename);
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                foundFile = true;

                String readLine = br.readLine();
                while(readLine != null && !readLine.equals("") ) {
                    String[] splitLine = readLine.split("\t");
                    if(splitLine.length != 4){
                        // Incorrect Format
                        System.out.println("The file format is incorrect "+splitLine.length);

                        // Use read line as a flag to exit
                        readLine = null;
                    } else {
                        // Correct number of lines

                        // Start by going through the file
                        try {
                            int side1 = Integer.parseInt(splitLine[0]);
                            int side2 = Integer.parseInt(splitLine[1]);
                            int side3 = Integer.parseInt(splitLine[2]);
                            int side4 = Integer.parseInt(splitLine[3]);

                            Quadrilateral qr = new Quadrilateral(side1, side2, side3, side4);

                            qc.add(qr);
                            readLine = br.readLine();
                        } catch(NumberFormatException e) {
                            System.out.println("The file format is incorrect 1");
                            // Use read line as a flag to exit
                            readLine = null;
                        }

                    }
                }

                // Close the stream
                br.close();
            } catch(FileNotFoundException e) {
                System.out.println("File not found. Please try again.");
            } catch (IOException e) {
                // Error during Reading or Writing
                System.out.println("An unknown error occured. The application will now exit");
            }
        }

        if(qc.getSize() != 0)
        {
            // Data is stored in the array
            try {
                System.out.println("Saving the file");
                File writtenFile = new File("out.txt");
                FileWriter fw = new FileWriter(writtenFile);
                BufferedWriter bw = new BufferedWriter(fw);

                for(int x = 0; x < qc.getSize(); x ++) {
                    bw.write(qc.getItem(x).toString());

                    // Avoid writing an extra newline at the end
                    if(x != qc.getSize() - 1)
                        bw.write("\n");
                }

                // Close the Stream
                bw.close();
            } catch (IOException e) {
                System.out.println("An unknown error occured. The application will now exit");
            }

        }

    }
}
