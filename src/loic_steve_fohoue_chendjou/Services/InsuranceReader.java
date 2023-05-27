package loic_steve_fohoue_chendjou.Services;

import loic_steve_fohoue_chendjou.datamodel.Insurances;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
public class InsuranceReader {


    public List<Insurances> readAll() throws FileNotFoundException {
        List<Insurances> insurances = new ArrayList<>();
        File file = new File("resources/insurances.csv");

        // read all the lines at once by this Files class function
        // Files.readAllLines(file.toPath());
        try (Scanner scanner = new Scanner(file);) {
            scanner.nextLine(); //skip the header line
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
                    String[] split = line.split(";");
                    Integer insuranceID = Integer.parseInt(split[0].trim());
                    String insuranceName = split[1].trim();
                    Insurances insurance = new Insurances(insuranceID, insuranceName);
                    insurances.add(insurance);

                } catch (Exception e) {
                    System.out.println("problem while dealing with this line: " + line);
                    e.printStackTrace(); // this is just printing the error, the while loop is not interrupted
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return insurances;
    }
}