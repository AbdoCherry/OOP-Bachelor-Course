package Week11.Script.Buffered;

import java.io.*;

public class MyBufFilCop {

    public static void main(String[] args) {
        // TODO Auto-Generated method stub

        //Check if In- or Outputfiles are passed
        //    if(args.length != 2){
        //     System.out.println("Java FileCopy InputFile OutputFile Error");
        //     System.exit(1);
        // }

        // In/OutputStream declaration

        try (FileReader fr = new FileReader("src/Week11/Script/Buffered/BufferedReader.byt");
             BufferedReader br = new BufferedReader(fr);
             BufferedWriter bw = new BufferedWriter(new FileWriter("src/Week11/Script/Buffered/BufferedReader.byt"))) {

                 //Example of Stream reading and directly writing
                 String line;

                 while ((line =br.readLine()) !=null){
                     bw.write(line);
                     bw.newLine();
                 }
                 System.out.println("Copy Done ...");

             }catch(FileNotFoundException f) {
            System.out.println("File not found: " + f);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
