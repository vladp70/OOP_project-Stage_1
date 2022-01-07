package santaReplacer;

import children.Child;
import enums.AgeGroup;
import enums.Category;
import fileio.AnnualChildReport;
import fileio.Input;
import fileio.Output;
import gifts.Gift;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {
    private static Database instance = null;
    private Integer numberOfYears;
    private Double santaBudget;
    //TODO: map for children found by id
    private List<Child> children;
    private List<Gift> gifts;
    private List<AnnualChange> annualChanges;
    private Double budgetUnit;

    private Database() {}

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
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
        initChildrenList(children);
    }

    public void initChildren(List<Child> children) {
        children.forEach(Child::init);
    }

    public void initChildrenList(List<Child> children) {
        initChildren(children);
        removeAdults();
        Collections.sort(children);
    }

    public void incrementAges() {
        children.forEach(Child::incrementAge);
    }

    public void removeAdults() {
        children.removeIf(child -> (child.getAgeGroup().equals(AgeGroup.YOUNG_ADULT)));
    }

    public void clearChildrenGifts() {children.forEach(Child::clearGifts);}

    public void calculateBudgetUnit() {
        Double averageSum = 0.0;
        for (var child : children) {
            averageSum += child.getAverageScore();
        }
        budgetUnit = santaBudget / averageSum;
    }

    public Gift findCheapestGiftByCategory(final Category category) {
        if (category == null) {
            return null;
        }

        Gift cheapestGift = null;

        for (var gift : gifts) {
            if (gift.getCategory().equals(category)) {
                if (cheapestGift == null) {
                    cheapestGift = gift;
                } else if (cheapestGift.getPrice() > gift.getPrice()) {
                    cheapestGift = gift;
                }
            }
        }

        return cheapestGift;
    }

    public AnnualChildReport assignGiftsToChild(Child child) {
        Double childBudget = budgetUnit * child.getAverageScore();

        for (var preference : child.getGiftsPreferences()) {
            if (childBudget <= 0.0) {
                break;
            }

            Gift gift = findCheapestGiftByCategory(preference);
            if (gift == null) {
                continue;
            }
            if (childBudget >= gift.getPrice()) {
                childBudget -= gift.getPrice();
                child.receiveGift(gift);
            }
        }

        return new AnnualChildReport(child, budgetUnit * child.getAverageScore());
    }

    public Child findChildByID(final int id) {
        for (var child : children) {
            if (child.getId().equals(id)) {
                return child;
            }
        }
        return null;
    }

    public void implementAnnualChange(AnnualChange changes) {
        incrementAges();
        clearChildrenGifts();
        children.addAll(changes.getNewChildren());
        initChildren(changes.getNewChildren());
        removeAdults();
        Collections.sort(children);
        gifts.addAll(changes.getNewGifts());
        santaBudget = changes.getNewSantaBudget();

        for (var update : changes.getChildrenUpdates()) {
            Child updatedChild = findChildByID(update.getId());
            if (updatedChild == null) {
                continue;
            }

            if (update.getNiceScore() != null) {
                updatedChild.addNiceScore(update.getNiceScore());
            }
            updatedChild.addPreferences(update.getGiftsPreferences());
        }
    }

    public List<AnnualChildReport> simulateRound(final int round) {
        List<AnnualChildReport> report = new ArrayList<>();

        if (round > numberOfYears || round < 0) {
            return report;
        } else if (round != 0) {
            implementAnnualChange(annualChanges.get(round - 1));
        }

        calculateBudgetUnit();
        for (var child : children) {
            report.add(assignGiftsToChild(child));
        }

        return report;
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