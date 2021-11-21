package java3_Lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<F extends Fruit> {

    private ArrayList<F> fruitsList;

    public Box(F... fruits) {
        this.fruitsList = new ArrayList<F>(Arrays.asList(fruits));
    }

    public void addFruit(F... fruits) {
        this.fruitsList.addAll(Arrays.asList(fruits));
    }

    public void removeFruit(F...fruits) {
        for (F elem: fruits) {
            this.fruitsList.remove(elem);
        }
    }

    public ArrayList<F> getFruitsList() {
        return new ArrayList<F>(fruitsList);
    }

    public float getWeight() {
        float weight = 0.0f;

        for (F fruit : fruitsList) {
            weight += fruit.getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> box) {
        return Math.abs(this.getWeight() - box.getWeight()) <= 0.1;
    }

    public void putToAnotherBox(Box<F> boxToPutIn) {
        boxToPutIn.fruitsList.addAll(fruitsList);
        fruitsList.clear();
    }

}
