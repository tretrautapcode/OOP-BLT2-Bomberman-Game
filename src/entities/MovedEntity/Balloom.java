package entities.MovedEntity;

import control.BombermanGame;
import entities.Entity;
import entities.StillEntity.Wall;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Balloom extends Entity {
    public Balloom(double x, double y, Image img) {
        super(x, y, img);
        dx = 1;
        dy = 0;
        speed = 0.015;
    }

    @Override
    public void update() {
        move();
    }

    public void move() {
        if (checkMove() == false) {
            dx = -dx;
            dy = -dy;
        }
        x = x + dx * speed;
        y = y + dy * speed;
        if (dx == 1) {
            Sprite sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animation, 12);
            img.setImage(sprite.getFxImage());
            updateAnimation();
        }
        if (dx == -1) {
            Sprite sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animation, 12);
            img.setImage(sprite.getFxImage());
            updateAnimation();
        }
    }

    public boolean checkMove() {
        for (Wall wall : BombermanGame.walls) {
            if (intersect(wall)) {
                return false;
            }
        }
        for (Brick brick : BombermanGame.bricks) {
            if (intersect(brick)) {
                return false;
            }
        }
        for (Bomb bomb : BombermanGame.bombs) {
            if (intersect(bomb)) {
                return false;
            }
        }
        return true;
    }
}
