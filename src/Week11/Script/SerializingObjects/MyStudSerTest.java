package Week11.Script.SerializingObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class MyStudSerTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Declaring and initializing our Objects of Class MyStudents
        MyStudents[] students = { 
            new MyStudents(1, "Kyle", "Broflovski"),
            new MyStudents(2, "Kenny", "McCormick"),
            new MyStudents(3, "Stan", "Marsh"),
            new MyStudents(4, "Eric", "Cartmenez")}; 
            // Cartmen, but now im watching the hispanic school episode

        String fileName = "src/Week11/Script/SerializingObjects/SouthParkCharacters.bin";

        // Declaration of OutputStream for our objects
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));

        // Serializing the objects
        for(MyStudents s : students){
            outputStream.writeObject(s);
        }
        outputStream.close();

        // Reading from file again and printing them in console
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
        ((MyStudents) inputStream.readObject()).print();
        ((MyStudents) inputStream.readObject()).print();
        ((MyStudents) inputStream.readObject()).print();
        ((MyStudents) inputStream.readObject()).print();

        inputStream.close();
        
        

    }

}
