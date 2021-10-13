package Lesson1;

public interface Athlete {
    String getName();
    int getCanRunForDistance();
    int getCanJumpUpTo();

    public boolean runPassChecker (int distance);
    public boolean jumpPassChecker (int height);

}
