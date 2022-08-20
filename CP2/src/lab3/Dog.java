package lab3;

public class Dog extends Mammal{

    public Dog(String name, int age){
        super(name, age);
    }

    public String toString() {
        return "I am a Dog, and I am "+ age+" years old" + " years old. My name is "+name;
    }

    public String makeSound() {
        return "Woof";
    }
}
