package lab3;

import javax.swing.*;

public class Zoo {
    Animal[] animal_list = null;
    int counter;

    public Zoo(){
        // Do nothing important
        animal_list = new Animal[1];
        counter = 0;
    }

    public int getSize() {
        return counter;
    }

    public void add(Animal a){
        if (counter == animal_list.length)
            increaseSize();

        animal_list[counter] = a;
        counter ++;
    }

    public void printZoo() {
        for(int i = 0; i< counter; i ++){
            System.out.println(animal_list[i]);
        }
    }

    public void increaseSize(){
        Animal[] temp = new Animal[animal_list.length * 2 + 1];
        for(int i = 0; i < counter; i ++){
            temp[i] = animal_list[i];
        }
        animal_list = temp;
    }

    public Animal getAnimal(int i){
        return animal_list[i];
    }

    public int findElement(String name) {
        for (int i =0; i < counter; i++){
            if (animal_list[i].getName().equals(name))
                    return i;
        }
        // -1 indicates not found
        return -1;
    }

    public boolean deleteAnimal(String name) {
        int location = findElement(name);

        if(location != -1){
            animal_list[location] = animal_list[counter-1];
            counter--;
            return true;
        }
        return false;
    }
}
