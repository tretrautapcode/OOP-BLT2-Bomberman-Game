package entities.DestroyAble.Character;

import control.Setting;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Oneal extends Enemy {
    public Oneal(double x, double y, Image img) {
        super(x, y, img, Setting.timeEnemyDestroy);
        dx=1;
        dy=0;
        speed = 0.02;
    }

    @Override
    public void update() {
        super.update();
        if(getIsDestroy()){
            img.setImage(Sprite.oneal_dead.getFxImage());
        }
    }

    public boolean move() {
        if (dx == 1) {
            Animation(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, 30);
        }
        if (dx == -1) {
            Animation(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, 30);
        }
        return super.move();
    }
}
