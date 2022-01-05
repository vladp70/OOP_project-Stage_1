package fileio;

import children.Child;
import gifts.Gift;
import santaReplacer.AnnualChange;

import java.util.List;

public class Input {
    private Integer numberOfYears;
    private Double santaBudget;
    private InitialDataInput initialData;
    private List<AnnualChange> annualChanges;

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public List<AnnualChange> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(List<AnnualChange> annualChanges) {
        this.annualChanges = annualChanges;
    }

    public InitialDataInput getInitialData() {
        return initialData;
    }

    public void setInitialData(InitialDataInput initialData) {
        this.initialData = initialData;
    }

    @Override
    public String toString() {
        return "Input{\n" +
                "numberOfYears = " + numberOfYears + ",\n" +
                "santaBudget = " + santaBudget + ",\n" +
                initialData + ",\n" +
                "annualChanges: " + annualChanges +
                '}';
    }
}
