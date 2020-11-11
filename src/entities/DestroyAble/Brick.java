package entities.DestroyAble;


import control.Setting;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Brick extends DestroyAble {
    public Brick(double x, double y, Image img) {
        super(x, y, img, Setting.timeBrickDestroy);
    }

    @Override
    public void update() {
        if(getIsDestroy()){
            Animation(Sprite.brick_exploded,Sprite.brick_exploded1,Sprite.brick_exploded2,20);
        }
        if(checkDestroy()){
            this.remove();
        }
    }
}
