package lab3;

import java.util.Scanner;
// By design I chose to pass scanner and zoo as parameters. You could make them static if you choose
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Zoo z = new Zoo();
        Main m = new Main();

        // Flag as well as an option
       int option = 98;

        while(option != -1) {

            System.out.println(m.printMenu());
            option = m.getInteger(scan);

            switch(option) {
                case 1:
                    // Adding Animal
                    m.addAnimal(scan, m, z);
                    break;
                case 2:
                    // Deleting Animal
                    m.deleteAnimal(scan, z);
                    break;
                case 3:
                    // List animals
                    m.printList(z);
                    break;
                case 4:
                    // Find animal
                    m.findAnimal(scan, z);
                    break;
                case 5:
                    System.out.println("Exiting");
                    option = -1;

            }
        }
    }
    public int getInteger(Scanner scan) {
        String scanned = scan.nextLine();
        try {
            return Integer.parseInt(scanned);
        } catch(NumberFormatException e) {
            System.out.println("Incorrect number; please try again");
            return getInteger(scan);
        }
    }
    public boolean addAnimal(Scanner scan, Main m, Zoo z) {
        System.out.println("What is the animal's type?");
        String animal_type = scan.nextLine();
        Animal a = null;
        if(!(animal_type.equalsIgnoreCase("Lion") || (animal_type.equalsIgnoreCase("Dog")) ||  animal_type.equalsIgnoreCase("Bird"))) {
            System.out.println("Unknown/Invalid type. Returning to the main menu");
            return false;
        } else {

            // Logic after cheking valdi type to reduce uesless input from the user
            System.out.println("What is its name?");
            String name = scan.nextLine();
            System.out.println("What is its age?");
            int age = m.getInteger(scan);

            if(animal_type.equalsIgnoreCase("Lion"))
                a = new Lion(name, age);
            else if(animal_type.equalsIgnoreCase("Bird"))
                a = new Bird(name, age);
            else
                a = new Dog(name, age);

            z.add(a);
            return true;
        }
    }

    public void findAnimal(Scanner scan, Zoo z) {
        System.out.println("What is the animal's name?");
        String name = scan.nextLine();

        int found = z.findElement(name);
        if (found != -1)
            System.out.println("Animal found");
        else System.out.println("Animal not found");
    }
    public String printMenu() {
        return "1- Add Animal\n"+
                "2- Delete Animal\n" +
                "3- List Animals\n" +
                "4- Find Animals\n" +
                "5- Exit" ;
    }

    public void printList(Zoo z) {
        if(z.getSize() != 0)
            z.printZoo();
        else System.out.println("Zoo is empty");;
    }

    public void deleteAnimal(Scanner scan, Zoo z){

        if(z.getSize() != 0)
        {
            System.out.println("What is the animal's name?");
            String name = scan.nextLine();

            if(z.deleteAnimal(name))
            {
                System.out.println("Animal deleted");
            } else {
                System.out.println("Could not find the animal");
            }
        } else System.out.println("Zoo is empty");

    }
}
