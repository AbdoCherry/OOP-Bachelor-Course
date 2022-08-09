package Week11.Script.InputOutput;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyBStream {

    public static void main(String[] args) {
        // TODO Auto-Generated method stub

        // Check, if In- Outputfile are passed
      /*  if(args.length != 2){
            System.out.println("\nJava FileCopy InputFile OutputFile Error");
            System.exit(1);
        }*/

        // Array serves as buffer for bytes read
        byte[] b = new byte[128];
        int bLen = b.length;

        // Example for In/Outputstream declaration
        try(FileInputStream fis = new FileInputStream("src/Week11/Script/InputOutput/Byte2.byt");
            FileOutputStream fos = new FileOutputStream("src/Week11/Script/InputOutput/Byte2.byt")){

            // Example of reading byte array stream and directly writing
            System.out.println("Bytes in File: " + fis.available());

            int count = 0;
            int byteRead = 0;

            while((byteRead = fis.read(b)) != -1) {
                if (byteRead < bLen) {
                    fos.write(b, 0, byteRead);
                } else {
                    fos.write(b);
                    count += byteRead;
                }
            }
                System.out.println("Written: " + count);
            }catch(FileNotFoundException f){
                System.out.println("File not Found: " + f);
            }catch(IOException e){
            System.out.println("IOException: " + e);
        }
    }
}
