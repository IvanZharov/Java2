package Lesson5;

public class TwoMethodsApp {
    public static void main(String[] args) throws InterruptedException {
        firstMethod(10_000_000);
        secondMethod(10_000_000);
    }

    private static void firstMethod(int size) {
        float[] array1 = new float[size];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < array1.length; i++) {
            array1[i] = 1.0f;
        }
        for (int i = 0; i < array1.length; i++) {
            array1[i] = (float) (array1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("One thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static void secondMethod(int size) throws InterruptedException {
        float[] arrayMain = new float[size];
        long startTime = System.currentTimeMillis();
        float[] arrayFirstHalf = new float[size/2];
        float[] arraySecondHalf = new float[size - arrayFirstHalf.length];
        Object lock = new Object();
        Object lock2 = new Object();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < arrayFirstHalf.length; i++) {
                synchronized (lock) {
                    arrayFirstHalf[i] = 1.0f;
                    arrayMain[i] = (float) (arrayFirstHalf[i] * Math.sin(0.2f +
                            i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < arraySecondHalf.length; i++) {
                synchronized (lock2) {
                    arraySecondHalf[i] = 1.0f;
                    arrayMain[i + arrayFirstHalf.length] = (float) (arraySecondHalf[i] * Math.sin(0.2f +
                            (i + arrayFirstHalf.length) / 5) * Math.cos(0.2f +
                            (i + arrayFirstHalf.length) / 5) * Math.cos(0.4f + (i + arrayFirstHalf.length) / 2));
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Two threads time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }
}
