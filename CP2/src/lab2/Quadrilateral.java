package lab2;

public class Quadrilateral {
    private int side1, side2, side3, side4;
    private int sum;

    public Quadrilateral(int _side1, int _side2, int _side3, int _side4){
        side1 = _side1;
        side2 = _side2;
        side3 = _side3;
        side4 = _side4;

        sum = side1 + side2 + side3 + side4;
    }

    public String toString() {
        return side1 + "\t" + side2 + "\t" + side3 + "\t" + side4 + "\t" + sum;
    }

}
