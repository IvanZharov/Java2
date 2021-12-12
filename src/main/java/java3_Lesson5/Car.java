package java3_Lesson5;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static boolean hasWin;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    private CyclicBarrier cyclicBarrier = RaceApp.cyclicBarrier;
    private CountDownLatch countDownLatch = RaceApp.countDownLatch;
    private CountDownLatch countDownLatchF = RaceApp.countDownLatchF;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            countDownLatch.countDown();
            System.out.println(this.name + " готов");
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        final ArrayList<Stage> stages = race.getStages();
        for (Stage stage : stages) {
            stage.go(this);
        }
        checkWin(this);
        countDownLatchF.countDown();
    }

    private static synchronized void checkWin(Car car) {
        if (!hasWin) {
            hasWin = true;
            System.out.println(car.name + " - WIN");

        }
    }
}
