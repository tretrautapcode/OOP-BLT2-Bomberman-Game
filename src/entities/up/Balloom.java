package entities.up;

import entities.Entity;
import javafx.scene.image.Image;

import graphics.Sprite;


public class Balloom extends Entity {
    public Balloom(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

        Animation(Sprite.balloom_left1,Sprite.balloom_left2,Sprite.balloom_left3,0,0,60);
    }
}
