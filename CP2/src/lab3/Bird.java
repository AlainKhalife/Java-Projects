package lab3;

public class Bird extends Animal {

    public Bird(String name, int age) {
        super(name, age);
    }
    public boolean isDangerous() {
        return false;
    }

    @Override
    public String makeSound() {
        return "QuikQuik";
    }

    public String toString() {
        return "I am a Bird, and I am "+ age+" years old" + " years old. My name is "+name;
    }
}
