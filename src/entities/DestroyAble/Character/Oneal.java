package entities.DestroyAble.Character;

import control.BombermanGame;
import control.Setting;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Oneal extends Character {
    public Oneal(double x, double y, Image img) {
        super(x, y, img, Setting.timeEnemyDestroy);
        dx=1;
        dy=0;
        speed = 0.02;
    }

    @Override
    public void update() {
        if (getIsDestroy()) {
            img.setImage(Sprite.oneal_dead.getFxImage());
            if(checkDestroy()) {
                this.remove();
            }
        } else {
            if(intersect(BombermanGame.player)){
                BombermanGame.player.setIsDestroy(true);
            }
            move();
        }
    }

    public void move() {
        if (checkMove()==false){
            dx=-dx;
            dy=-dy;
        }
        x = x + dx * speed;
        y = y + dy * speed;
        if (dx == 1) {
            Animation(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, 12);
        }
        if (dx == -1) {
            Animation(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, 12);
        }
    }
}
