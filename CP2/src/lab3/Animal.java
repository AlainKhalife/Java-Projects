package lab3;

public abstract class Animal {

    public String name;
    public int age;

    public Animal(String _name, int _age) {
        name = _name;
        age = _age;
    }

    public String getName(){
        return name;
    }
    public abstract String toString();
    public abstract boolean isDangerous();
    public abstract String makeSound();
}
