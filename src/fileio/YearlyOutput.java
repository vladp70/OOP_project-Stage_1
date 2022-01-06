package fileio;

import santaReplacer.AnnualChildReport;

import java.util.ArrayList;
import java.util.List;

public class YearlyOutput {
    List<AnnualChildReport> children;

    public YearlyOutput() {
        children = new ArrayList<>();
    }

    public YearlyOutput(List<AnnualChildReport> children) {
        this.children = children;
    }

    public List<AnnualChildReport> getChildren() {
        return children;
    }

    public void setChildren(List<AnnualChildReport> children) {
        this.children = children;
    }

    public void addReport(final AnnualChildReport report) {
        children.add(report);
    }
}
