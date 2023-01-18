package Week10.Task02.Auxiliary;

import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;

public class FileDialog {

    // Let user choose file. Default directory is our data folder in same directory
    public static @Nullable String FileChooser(String className) {
        JButton chooser = new JButton();
        final JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("src/Week10/Task02/Data"));
        fc.setDialogTitle("Select File for " + className + " data");

        if (fc.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile().getAbsoluteFile().toString();
        }
        System.out.println("\nCancelled. No File choosed");
        return null;
    }
}
