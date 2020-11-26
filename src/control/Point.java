package control;

public class Point {
    private int x;
    private int y;
    public Point(){

    }
    public Point(Point u){
        x= u.x;
        y= u.y;
    }
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
