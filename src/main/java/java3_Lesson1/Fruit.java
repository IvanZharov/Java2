package java3_Lesson1;

public abstract class Fruit {
    protected float weight;

    public Fruit(float weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
