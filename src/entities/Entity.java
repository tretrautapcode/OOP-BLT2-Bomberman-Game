package entities;

import control.BombermanGame;
import control.Setting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import graphics.Sprite;

public abstract class Entity {
    protected double x;
    protected double y;
    protected ImageView img;

    public Entity(double x, double y, Image img) {
        this.x = x;
        this.y = y;
        this.img = new ImageView(img);
    }


    public double getY() {
        return y;
    }

    public double getX() {
        return x;
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
    public void remove() {
        BombermanGame.playerPane.getChildren().remove(img);
    }
    public void addToPane(){
        BombermanGame.playerPane.getChildren().add(img);
    }
    public boolean intersect(Entity entity) {
        if (x + 1 <= entity.x) return false;
        if (entity.getX() + 1 <= x) return false;
        if (y + 1 <= entity.y) return false;
        if (entity.y + 1 <= y) return false;
        return true;
    }
}
