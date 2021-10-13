package Lesson1;

public class CompetitionApp {
    public static void main(String[] args) {
        int distanceToRun1 = 4;
        int heightToJump1 = 2;
        int distanceToRun2 = 5;

        int[] route = {
                distanceToRun1, heightToJump1, distanceToRun2
        };

        Athlete[] athletes = {
            new Cat("A", 4, 3),
            new Robot("B", 2, 1),
            new Human("C", 20, 2),
            new Cat("D", 7, 4),
            new Robot("E", 10, 2),
            new Human("F", 25, 2)
        };

        for (int i = 0; i < athletes.length; i++) {
            athletes[i].runPassChecker(distanceToRun1);
            if (athletes[i].runPassChecker(distanceToRun1) == true) {
                athletes[i].runPassChecker(distanceToRun1);
                if (athletes[i].jumpPassChecker(heightToJump1) == true) {
                    athletes[i].jumpPassChecker(heightToJump1);
                    if (athletes[i].runPassChecker(distanceToRun2) == true) {
                        System.out.println(athletes[i].getName() + " финишировал");
                    }
                }
            } else System.out.println("Сошел");


        }

/*
* У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки.
Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.
*/
    }
}
