package Week10.Task02.Auxiliary;

import javax.swing.*;
import java.io.File;

public class FileDialog {

    public static String FileChooser(String className) {
        JButton chooser = new JButton();
        final JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("src/Week10/Task02/Data"));
        fc.setDialogTitle("Select File for " + className + " data");

        switch (fc.showOpenDialog(chooser)) {
            case JFileChooser.APPROVE_OPTION:
                return fc.getSelectedFile().getAbsoluteFile().toString();
            default:
                System.out.println("\nCancelled. No File choosed");
                return null;
        }
    }
}
