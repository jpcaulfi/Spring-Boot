import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static components.Database.primeDB;
import static components.Database.restartDB;

public class Main {

    public static void main(String[] args) throws Exception {

        restartDB(); // deletes only a few tables
        primeDB(); // pre-fills certain tables

        // Data compiled : 1_17 - 1_26

        String[] timespan = {"1_27", "1_28", "1_29", "1_30", "1_31"};

        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Launching Canopy....");
        System.out.println(" ");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Compiling data from the following dates:");
        System.out.println(" ");
        System.out.println(Arrays.toString(timespan));
        System.out.println(" ");
        TimeUnit.SECONDS.sleep(3);

        for (String s : timespan) {

            System.out.println(s + " Compiling...");

            // SUMMON CSV-A

            List<List<String>> A = fetchCsv(s);

            // SUMMON CSV-B

            List<List<String>> B = fetchCsv("Marks");

            // SUMMON CSV-C

            List<List<String>> C = fetchCsv("Attendance");

            // SUMMON CSV-I

            List<List<String>> D = fetchCsv("Roster");

            // SUMMON CSV-II

            List<List<String>> E = fetchCsv("RouteLocations");

            // Launch Compiler

            Compiler.main(A);

            System.out.println(" ");
            System.out.println("Data for "+s+" compiled successfully.");
            System.out.println(" ");
            TimeUnit.SECONDS.sleep(1);

            // Launch Query

            System.out.println(s + " Querying...");
            System.out.println(" ");

            Query.main(s, B, C, D, E);

            System.out.println("Data for "+s+" queried successfully.");
            System.out.println(" ");
            TimeUnit.SECONDS.sleep(1);

            //
            //break;
        }
        System.out.println("Rendering Outputs...");
        System.out.println(" ");
        TimeUnit.SECONDS.sleep(3);
        // Launch Render
        //
        // Query all scores and details about the scores
        // Build & output scores into csv
        //
        // Screen research data (driverIDs, locations, etc.)
        System.out.println("Canopy program has completed without error.");
        System.out.println(" ");
        System.out.println(" ");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Goodbye");
    }

    public static List<List<String>> fetchCsv(String csvFile) {
        List<List<String>> fileContents = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader("** OMITTED **/"+csvFile+".csv"))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                fileContents.add(Arrays.asList(values));
            }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
        return fileContents;
    }

}
