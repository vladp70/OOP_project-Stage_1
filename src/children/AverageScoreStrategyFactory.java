package children;

import enums.AgeGroup;

public class AverageScoreStrategyFactory {
    private static AverageScoreStrategyFactory instance = null;

    private AverageScoreStrategyFactory() {
    }

    public static AverageScoreStrategyFactory getInstance() {
        if (instance == null)
            instance = new AverageScoreStrategyFactory();
        return instance;
    }

    public AverageScoreStrategy createStrategy(AgeGroup ageGroup) {
        if (ageGroup.equals(AgeGroup.BABY)) {
            return new BabyAverageScoreStrategy();
        } else if (ageGroup.equals(AgeGroup.KID)) {
            return new KidAverageScoreStrategy();
        } else if (ageGroup.equals(AgeGroup.TEEN)) {
            return new TeenAverageScoreStrategy();
        }
        return null;
    }
}
