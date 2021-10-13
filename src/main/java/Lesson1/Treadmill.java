package Lesson1;

public class Treadmill extends Barreirs {
    private String barrierType;
    private int barrierCondition;

    public Treadmill(String barrierType, int barrierCondition){
        this.barrierType = barrierType;
        this.barrierCondition = barrierCondition;
    }

    public String getBarrierType() {
        return barrierType;
    }

    public void setBarrierType(String barrierType) {
        this.barrierType = barrierType;
    }

    public int getBarrierCondition() {
        return barrierCondition;
    }

    public void setBarrierCondition(int barrierCondition) {
        this.barrierCondition = barrierCondition;
    }
}
