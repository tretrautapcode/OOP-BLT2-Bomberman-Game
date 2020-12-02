package entities.DestroyAble.Character;

import control.Setting;
import control.MyList;
import entities.DestroyAble.DestroyAble;
import graphics.Sprite;
import javafx.scene.image.Image;

public abstract class Character extends DestroyAble {
    protected boolean isMoving = false;
    protected int animation = 0;
    protected double speed = 0.0625;
    protected double dx = 0;
    protected double dy = 0;

    public Character(double x, double y, Image img,long nanoSecond) {
        super(x, y, img,nanoSecond);
    }

    @Override
    public abstract void update();

    public void addSpeed(){
        speed=speed+Setting.speedAdd;
    }
    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
    public boolean move()
    {
        int floorX=(int)Math.floor(x+dx*speed);
        int floorY=(int)Math.floor(y+dy*speed);
        int ceilX=(int)Math.ceil(x+dx*speed);
        int ceilY=(int)Math.ceil(y+dy*speed);
        if(dx==1){
            if(checkMove(ceilX,floorY)&&checkMove(ceilX,ceilY)){
                x=x+dx*speed;
                return true;
            }
            if(y-floorY<=0.25&&checkMove(ceilX,floorY))
            {
                x=x+dx*speed;
                y=floorY;
                return true;
            }
            if(ceilY-y<=0.25&&checkMove(ceilX,ceilY)){
                x=x+dx*speed;
                y=ceilY;
                return true;
            }
            x=Math.ceil(x);
        }
        if(dx==-1){
            if(checkMove(floorX,floorY)&&checkMove(floorX,ceilY)){
                x=x+dx*speed;
                return true;
            }
            if(y-floorY<=0.25&&checkMove(floorX,floorY))
            {
                x=x+dx*speed;
                y=floorY;
                return true;
            }
            if(ceilY-y<=0.25&&checkMove(floorX,ceilY)){
                x=x+dx*speed;
                y=ceilY;
                return true;
            }
            x=Math.floor(x);
        }
        if(dy==1){
            if(checkMove(ceilX,ceilY)&&checkMove(floorX,ceilY)){
                y=y+dy*speed;
                return true;
            }
            if(x-floorX<=0.25&&checkMove(floorX,ceilY)){
                x=floorX;
                y=y+dy*speed;
                return true;
            }
            if(ceilX-x<=0.25&&checkMove(ceilX,ceilY)){
                x=ceilX;
                y=y+dy*speed;
                return true;
            }
            y=Math.ceil(y);
        }
        if(dy==-1){
            if(checkMove(ceilX,floorY)&&checkMove(floorX,floorY)){
                y=y+dy*speed;
                return true;
            }
            if(x-floorX<=0.25&&checkMove(floorX,floorY)){
                x=floorX;
                y=y+dy*speed;
                return true;
            }
            if(ceilX-x<=0.25&&checkMove(ceilX,floorY)){
                x=ceilX;
                y=y+dy*speed;
                return true;
            }
            y=Math.floor(y);
        }
        isMoving=false;
        return false;
    }
    public boolean checkBomb(int _x,int _y) {
        return MyList.getBomb(_x,_y)!=null;
    }
    public boolean checkBrick(int _x,int _y){
        return MyList.getBrick(_x,_y)!=null;
    }
    public boolean checkWall(int _x,int _y){
        return MyList.getWall(_x,_y)!=null;
    }
    public boolean checkMove(int _x,int _y) {
        return !checkBomb(_x,_y)&&!checkWall(_x,_y)&&!checkBrick(_x,_y);
    }

    public void Animation(Sprite x1, Sprite x2, Sprite x3, int time) {
        Sprite sprite = Sprite.movingSprite(x1, x2, x3, animation, time);
        img.setImage(sprite.getFxImage());
        animation++;
        if (animation > 1000000) {
            animation = 0;
        }
    }

    public void Animation(Sprite x1, Sprite x2, int time) {
        Sprite sprite = Sprite.movingSprite(x1, x2, animation, time);
        img.setImage(sprite.getFxImage());
        animation++;
        if (animation > 1000000) {
            animation = 0;
        }
    }
}
