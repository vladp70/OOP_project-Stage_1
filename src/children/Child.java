package children;

import enums.AgeGroup;
import enums.Category;
import enums.Cities;
import gifts.Gift;
import utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Child implements Comparable<Child> {
    private Integer id;
    private String lastName;
    private String firstName;
    private Integer age;
    private Cities city;
    private Double niceScore;
    private LinkedList<Category> giftsPreferences;
    private List<Double> niceScoreHistory = new ArrayList<>();
    private Double averageScore = 0.0;
    //TODO: gifts needs to support push at the beginning
    private List<Gift> receivedGifts = new ArrayList<>();
    private AgeGroup ageGroup = AgeGroup.UNKNOWN;
    private AverageScoreStrategy averageScoreStrategy = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(LinkedList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(Double niceScore) {
        this.niceScore = niceScore;
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void receiveGift(Gift receivedGift) {
        if (receivedGift != null) {
            this.receivedGifts.add(receivedGift);
        }
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public void addNiceScore(final Double newNiceScore) {
        niceScoreHistory.add(newNiceScore);
    }

    public void setStrategy() {
        averageScoreStrategy =AverageScoreStrategyFactory.getInstance()
                .createStrategy(ageGroup);
    }

    public Double getAverageScore() {
        averageScore = averageScoreStrategy.getAverageScore(this);
        return averageScore;
    }

    public void initAgeGroup() {
        if (ageGroup == AgeGroup.UNKNOWN) {
            ageGroup = Utils.ageToAgeGroup(age);
        }
    }

    public void init() {
        initAgeGroup();
        addNiceScore(niceScore);
        setStrategy();
    }

    public void addPreferences(List<Category> newGiftsPreferences) {
        LinkedList<Category> aux = new LinkedList<>(newGiftsPreferences);
        while (!(aux.isEmpty())) {
            var newPreferenece = aux.removeLast();
            if (giftsPreferences.contains(newPreferenece)) {
                giftsPreferences.remove(newPreferenece);
            }
            giftsPreferences.addFirst(newPreferenece);
        }
    }

    public void incrementAge() {
        age++;
        if (age > Utils.upperLimitAgeGroup(ageGroup)) {
            ageGroup = Utils.nextAgeGroup(ageGroup);
            setStrategy();
        }
    }

    public void clearGifts() {
        receivedGifts.clear();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" +
                id + ") {" +
                age + " yo" +
                ", " + city +
                ", niceScoreHistory: " + niceScoreHistory +
                ", averageScore: " + averageScore +
                ", giftsPreferences: " + giftsPreferences +
                "}";
    }

    @Override
    public int compareTo(Child o) {
        return this.id.compareTo(o.getId());
    }
}
