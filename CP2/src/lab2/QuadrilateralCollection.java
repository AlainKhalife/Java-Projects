package lab2;

public class QuadrilateralCollection {
    Quadrilateral[] quad_list;
    int counter;

    public QuadrilateralCollection(){
        // Do nothing important
        quad_list = new Quadrilateral[1];
        counter = 0;
    }

    public int getSize() {
        return counter;
    }

    public void add(Quadrilateral q){
        if (counter == quad_list.length)
            increaseSize();

        quad_list[counter] = q;
        counter ++;
    }

    public void increaseSize(){
        Quadrilateral[] temp = new Quadrilateral[quad_list.length * 2 + 1];
        for(int i = 0; i < counter; i ++){
            temp[i] = quad_list[i];
        }
        quad_list = temp;
    }

    public Quadrilateral getItem(int i){
        return quad_list[i];
    }
}
