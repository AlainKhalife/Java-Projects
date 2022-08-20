package lab3;

public abstract class Mammal extends  Animal {

    public Mammal(String name, int age) {
        super(name, age);
    }
    public String toString() {
        return null;
    }

    public boolean isDangerous() {
        return true;
    }
}
