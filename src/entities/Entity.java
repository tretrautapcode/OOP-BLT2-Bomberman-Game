package entities;

import control.BombermanGame;
import control.Setting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import graphics.Sprite;

import java.util.Set;

public abstract class Entity {
    protected int animation = 0;
    protected double x;
    protected double y;
    protected double speed = 0.0625;
    protected double dx = 0;
    protected double dy = 0;
    protected ImageView img;

    public Entity(double x, double y, Image img) {
        this.x = x;
        this.y = y;
        this.img = new ImageView(img);
    }


    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public void addToPane() {
        BombermanGame.playerPane.getChildren().add(img);
    }

    public void render() {
        double u= BombermanGame.player.x;
        double v= BombermanGame.player.y;
        u=u- Setting.WIDTH/2;
        v=v-Setting.HEIGHT/2;
        u=Math.min(u,Setting.L-Setting.WIDTH);
        v=Math.min(v,Setting.R-Setting.HEIGHT);
        u=Math.max(u,0);
        v=Math.max(v,0);
        img.relocate((x-u) * Sprite.SCALED_SIZE, (y-v) * Sprite.SCALED_SIZE);
    }

    public abstract void update();
    public boolean intersect(Entity entity){
        double _x=x+dx*speed;
        double _y=y+dy*speed;
        if (_x+1<=entity.getX())return false;
        if (entity.getX()+1<=_x)return false;
        if (_y+1<=entity.getY())return false;
        if (entity.getY()+1<=_y)return false;
        return true;
    }
    public double formatY()
    {
        if (y - (int) y <= Setting.exp) {
            return (int) y;
        }
        if (y - (int) y > 1 - Setting.exp) {
            return (int) y + 1;
        }
        return y;
    }
    public double formatX()
    {
        if (x - (int) x <= Setting.exp) {
            return (int) x;
        }
        if (x - (int) x > 1 - Setting.exp) {
            return (int)x + 1;
        }
        return x;
    }
    public void updateAnimation() {
        animation++;
        if (animation > 1000000) animation = 0;
    }
}
