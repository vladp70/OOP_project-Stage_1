package children;

public class BabyAverageScoreStrategy implements AverageScoreStrategy{
    @Override
    public Double getAverageScore(Child child) {
        return 10.0;
    }
}
