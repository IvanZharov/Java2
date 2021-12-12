package java3_Lesson5;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class RaceApp {
        public static final int CARS_COUNT = 4;
        static CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        static CountDownLatch countDownLatch = new CountDownLatch(CARS_COUNT);
        static CountDownLatch countDownLatchF = new CountDownLatch(CARS_COUNT);

        public static void main(String[] args) throws InterruptedException {

            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
            Race race = new Race(new Road(60), new Tunnel(), new Road(40));
            Car[] cars = new Car[CARS_COUNT];
            for (int i = 0; i < cars.length; i++) {
                cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
            }
            for (int i = 0; i < cars.length; i++) {
                new Thread(cars[i]).start();
            }
            countDownLatch.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            countDownLatchF.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        }
    }

