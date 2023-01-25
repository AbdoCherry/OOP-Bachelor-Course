package Week09.Task02.BusinessRules;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Week09.Task02.Model.Deposit;

public class Reader {

    public static List<Deposit> readCSV() {

        List<Deposit> deposits = new ArrayList<>();

        String pathWindows = "src\\Week09\\Task02\\Data\\input.csv";
        String pathMacOS = "src/Week09/Task02/Data/input.csv";
        String path = "";
        String myOS = System.getProperty("os.name");
        String line = null;

        if (myOS.startsWith("Win")) {
            path = pathWindows;
        } else if (myOS.startsWith("Mac")) {
            path = pathMacOS;
        } else {
            path = null;
        }

        try {
            BufferedReader readCSV = new BufferedReader(new FileReader(path));
            readCSV.readLine();

            while ((line = readCSV.readLine()) != null) {
                String[] depVal = line.split(";");
                deposits.add(new Deposit(depVal[0], depVal[1], depVal[2], depVal[3],
                        Double.parseDouble(depVal[5]), depVal[6], depVal[7]));
            }

            readCSV.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(deposits.size() > 0
                ? "\n================================ IMPORT SUCCESSFUL ================================\n"
                : "\n================================ IMPORT FAILED ================================\n");

        return deposits;

    }

}
