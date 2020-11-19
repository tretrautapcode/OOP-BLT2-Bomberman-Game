package entities.DestroyAble.Character;

import control.Setting;
import control.MyList;
import graphics.Sprite;
import javafx.scene.image.Image;

import java.util.Random;


public class Balloom extends Enemy {
    public Balloom(double x, double y, Image img) {
        super(x, y, img, Setting.timeEnemyDestroy);
        dx = -1;
        dy = 0;
        speed = 0.02;
    }

    @Override
    public void update() {
        int mod=(int)(Sprite.SCALED_SIZE/speed);
        if (!isMoving||checkInt()) {
            isMoving=true;
            Random rnd = new Random();
            int random = rnd.nextInt(4);
            if (random==0) {
                dx = 1;
                dy = 0;
            }
            if (random==1) {
                dx = 0;
                dy = 1;
            }
            if (random==2) {
                dx = -1;
                dy = 0;
            }
            if (random==3) {
                dx = 0;
                dy = -1;
            }
        }
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
