package Lesson1;

public class Wall extends Barreirs {
    int barrierCondition;
    boolean passTheBarrier = true;

    @Override
    public boolean passTheBarrier(int canDo) {
        if (canDo >= barrierCondition) {
            return true;
        } else return false;
    }
}
