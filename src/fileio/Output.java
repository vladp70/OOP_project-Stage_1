package fileio;

import com.fasterxml.jackson.databind.ObjectMapper;
import santaReplacer.AnnualChildReport;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Output {
    List<YearlyOutput> annualChildren = new ArrayList<>();

    public Output() {
        addNewYear();
    }

    public List<YearlyOutput> getAnnualChildren() {
        return annualChildren;
    }

    public void setAnnualChildren(final List<YearlyOutput> annualChildren) {
        this.annualChildren = annualChildren;
    }

    public void addChildReport(final AnnualChildReport report) {
        annualChildren.get(annualChildren.size() - 1).addReport(report);
    }

    public void addNewYear() {
        annualChildren.add(new YearlyOutput());
    }

    public void writeToFile(final String outputPath) throws IOException {
        FileWriter file = new FileWriter(outputPath);
        ObjectMapper objectMapper = new ObjectMapper();
        String JSONString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);

        try {
            file.write(JSONString);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
