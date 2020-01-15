package main.model.asteroid;



public interface circularObj extends Obstacle {
    abstract double area();
    abstract double perimeter();
    abstract void updateDistance(int dRed, int dGreen, int dBlue);
}
