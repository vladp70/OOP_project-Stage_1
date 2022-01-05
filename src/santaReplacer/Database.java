package santaReplacer;

import children.Child;
import fileio.Input;
import gifts.Gift;

import java.util.List;

public class Database {
    private static Database instance = null;
    private Integer numberOfYears;
    private Double santaBudget;
    //TODO: map for children found by id
    private List<Child> children;
    private List<Gift> gifts;
    private List<AnnualChange> annualChanges;

    private Database() {}

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    //TODO: implement clear database?
    public static void Clear() {

    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public List<Child> getChildren() {
        return children;
    }

    public List<Gift> getGifts() {
        return gifts;
    }

    public List<AnnualChange> getAnnualChanges() {
        return annualChanges;
    }

    public void initDatabase(final Input input) {
        numberOfYears = input.getNumberOfYears();
        santaBudget = input.getSantaBudget();
        children = input.getInitialData().getChildren();
        gifts = input.getInitialData().getSantaGiftsList();
        annualChanges = input.getAnnualChanges();
    }

    @Override
    public String toString() {
        return "Database{" +
                "numberOfYears=" + numberOfYears +
                ", santaBudget=" + santaBudget +
                ",\nchildren=" + children +
                ",\ngifts=" + gifts +
                ",\nannualChanges=" + annualChanges +
                '}';
    }
}
