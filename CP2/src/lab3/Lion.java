package lab3;

public class Lion extends Mammal{

    public Lion(String name, int age){
        super(name, age);
    }

    public String toString() {
        return "I am a Lion, and I am "+ age + " years old. My name is "+name;
    }

    public String makeSound() {
        return "ROAR";
    }
}
