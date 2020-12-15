package entities.DestroyAble.Character;

import control.BombermanGame;
import control.Setting;
import graphics.Sprite;
import javafx.scene.image.Image;


public class Balloom extends Character {
    public Balloom(double x, double y, Image img) {
        super(x, y, img, Setting.timeEnemyDestroy);
        dx = 1;
        dy = 0;
        speed = 0.015;
    }

    @Override
    public void update() {
        if (getIsDestroy()) {
            img.setImage(Sprite.balloom_dead.getFxImage());
            if(checkDestroy()) {
                destroy();
            }
        } else {
            if(intersect(BombermanGame.player)){
                BombermanGame.player.setIsDestroy(true);
            }
            move();
        }
    }

    public void move() {
        if (!checkMove()) {
            dx = -dx;
            dy = -dy;
        }
        x = x + dx * speed;
        y = y + dy * speed;
        if (dx == 1) {
            Animation(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, 12);

        }
        if (dx == -1) {
            Animation(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, 12);
        }
    }
}
