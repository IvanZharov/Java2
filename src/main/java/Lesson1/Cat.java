package Lesson1;

public class Cat implements Athlete {

    private String name;
    private int canRunForDistance;
    private int canJumpUpTo;

    public Cat (String name, int canRunForDistance, int canJumpUpTo) {
        this.name = name;
        this.canRunForDistance = canRunForDistance;
        this.canJumpUpTo = canJumpUpTo;
    }

    @Override
    public boolean runPassChecker (int distance) {
        System.out.println(name + " подошел к следующему этапу");
        if (canRunForDistance >= distance) {
            System.out.println(name + " пронесся по маршруту");
            return true;
        } else
            System.out.println(name + " не смог");
        return false;
    }

    @Override
    public boolean jumpPassChecker (int height) {
        System.out.println(name + " подошел к следующему этапу");
        if (canJumpUpTo >= height) {
            System.out.println(name + " запрыгнул на стену");
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
