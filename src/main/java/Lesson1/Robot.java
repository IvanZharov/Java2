package Lesson1;

public class Robot implements Athlete {

    private String name;
    private int canRunForDistance;
    private int canJumpUpTo;

    public Robot (String name, int canRunForDistance, int canJumpUpTo) {
        this.name = name;
        this.canRunForDistance = canRunForDistance;
        this.canJumpUpTo = canJumpUpTo;
    }

    @Override
    public boolean runPassChecker (int distance) {
        System.out.println(name + " подошел к следующему этапу");
        if (canRunForDistance >= distance) {
            System.out.println(name + " выпустил шасси и проехал дистанцию");
            return true;
        } else
            System.out.println(name + " не смог");
        return false;
    }

    @Override
    public boolean jumpPassChecker (int height) {
        System.out.println(name + " подошел к следующему этапу");
        if (canJumpUpTo >= height) {
            System.out.println(name + " удлинил ноги и перешагнул");
            return true;
        } else
            System.out.println(name + " не смог");
        return false;
    }

    public String getName() {
        return name;
    }

    public int getCanRunForDistance() {
        return canRunForDistance;
    }

    public int getCanJumpUpTo() {
        return canJumpUpTo;
    }
}
