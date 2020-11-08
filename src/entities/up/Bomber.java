package entities.up;

import control.BombermanGame;
import entities.Entity;
import entities.dow.Grass;
import graphics.Sprite;

import javafx.scene.image.Image;

public class Bomber extends Entity {

    public Bomber(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        //Animation(Sprite.player_right_1,Sprite.player_right_2,0,0,27);
        //if(x>BombermanGame.WIDTH)x=0;
    }

    public void moveRight() {
        Animation(Sprite.player_right_1,Sprite.player_right_2,1,0,3);
        if(x== BombermanGame.WIDTH)x=0;
    }
    public void moveLeft() {
        new Grass((int)x,(int)y, Sprite.grass.getFxImage()).render();
        x--;
        render();
    }
    public void moveUp() {
        new Grass((int)x,(int)y, Sprite.grass.getFxImage()).render();
        y--;
        render();
    }
    public void moveDow() {
        new Grass((int)x,(int)y, Sprite.grass.getFxImage()).render();
        y++;
        render();
    }
    public boolean canMove()
    {
        return true;
    }
}
