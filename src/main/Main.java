package main;

import checker.Checker;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import fileio.Input;
import fileio.Output;
import santaReplacer.AnnualChildReport;
import santaReplacer.Database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.OUTPUT_DIR);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File[] outputDirectory = new File(Constants.OUTPUT_DIR).listFiles();
        if (outputDirectory != null) {
            for (File file : outputDirectory) {
                if (!file.delete()) {
                    System.out.println("Could not be deleted");
                }
            }
        }






        //action("tests/test2.json", "output/out_test");





        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = Constants.OUTPUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input input = objectMapper.readValue(new File(filePath1), Input.class);
        Output output = new Output();

        Database database = Database.getInstance();
        database.initDatabase(input);

        output.addChildReport(new AnnualChildReport(database.getChildren().get(0), 228.1764705882353));
        output.addChildReport(new AnnualChildReport(database.getChildren().get(1), 760.5882352941177));
        output.addNewYear();
        output.addChildReport(new AnnualChildReport(database.getChildren().get(0), 345.1764705882353));
        output.addChildReport(new AnnualChildReport(database.getChildren().get(1), 862.9411764705883));

        output.writeToFile(filePath2);
    }
}
