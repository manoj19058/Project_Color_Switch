package controller;

import javafx.scene.Group;

public class Obstacles  {
    private Group gp;
    private int area;
    private int perimeter;
    private int shape;

    public void remove() {

        this.gp.setLayoutY(this.shape);
    }

    public void create() {

        this.gp.setLayoutY(this.area);
    }

    public Group getGp() {
        return gp;
    }

    public int getArea() {

        return area;
    }

    public int getPerimeter() {
        return perimeter;
    }

    public int getShape() {
        return shape;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setGp(Group gp) {
        this.gp = gp;
    }

    public void setPerimeter(int perimeter) {
        this.perimeter = perimeter;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

}
