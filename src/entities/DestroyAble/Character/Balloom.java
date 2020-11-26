package entities.DestroyAble.Character;

import control.Setting;

import graphics.Sprite;
import javafx.scene.image.Image;


public class Balloom extends Enemy {
    public Balloom(double x, double y, Image img) {
        super(x, y, img, Setting.timeEnemyDestroy);
        dx = -1;
        dy = 0;
        speed = 0.02;
    }

    @Override
    public void update() {
        randomMove();
        super.update();
        if (getIsDestroy()) {
            img.setImage(Sprite.balloom_dead.getFxImage());
        }
    }

    public boolean move() {
        if (dx == 1||dy==1) {
            Animation(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, 30);

        }
        if (dx == -1||dy==-1) {
            Animation(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, 30);
        }
        return super.move();
    }
}
