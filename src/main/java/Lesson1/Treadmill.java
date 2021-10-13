package Lesson1;

public class Treadmill extends Barreirs {
    int barrierCondition;
    boolean passTheBarrier = true;

    @Override
    public boolean passTheBarrier(int canDo) {
        if (canDo >= barrierCondition) {
            return true;
        } else return false;
    }

    public int getBarrierCondition() {
        return barrierCondition;
    }

    public void setBarrierCondition(int barrierCondition) {
        this.barrierCondition = barrierCondition;
    }

    public boolean isPassTheBarrier() {
        return passTheBarrier;
    }

    public void setPassTheBarrier(boolean passTheBarrier) {
        this.passTheBarrier = passTheBarrier;
    }
}
