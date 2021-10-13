package Lesson1;

public class CompetitionApp {
    public static void main(String[] args) {

        Barreirs[] barreirs = {
                new Treadmill("Run", 4),
                new Wall("Jump", 2),
                new Treadmill("Run", 10),
        };

        Athlete[] athletes = {
                new Cat("Egg", 4, 3),
                new Cat("Dinner", 15, 4),
                new Robot("B-32", 2, 1),
                new Robot("E-11", 10, 5),
                new Human("Clor", 3, 1),
                new Human("Fred", 10, 2)
        };

        for (int i = 0; i < athletes.length; i++) {
            for (int j = 0; j < barreirs.length; j++) {
                String barrierType = barreirs[j].getBarrierType;
                int barrierCondition = barreirs[j].getBarrierCondition;
                if (barrierType == "Run") {
                    athletes[i].runPassChecker(barrierCondition);
                    if (athletes[i].runPassChecker(barrierCondition) == false){
                        System.out.println("Сошел");
                        break;}
                } else {
                    athletes[i].jumpPassChecker(barrierCondition);
                    if (athletes[i].jumpPassChecker(barrierCondition) == false){
                        System.out.println("Сошел");
                        break;}
                }
            }
        }
    }
}
