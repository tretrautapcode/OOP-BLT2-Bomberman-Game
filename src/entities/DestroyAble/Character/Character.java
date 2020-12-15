package entities.DestroyAble.Character;

import control.Audio;
import control.BombermanGame;
import control.Setting;
import entities.DestroyAble.Bomb;
import entities.DestroyAble.Brick;
import entities.DestroyAble.DestroyAble;
import entities.Entity;
import entities.StillEntity.Wall;
import graphics.Sprite;
import javafx.scene.image.Image;

public abstract class Character extends DestroyAble {
    protected int animation = 0;
    protected double speed = 0.0625;
    protected double dx = 0;
    protected double dy = 0;

    public Character(double x, double y, Image img,long nanoSecond) {
        super(x, y, img,nanoSecond);
    }

    @Override
    public void update() {


    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public boolean checkBomb() {
        for (Bomb bomb : BombermanGame.bombs) {
            if (intersect(bomb)) {
                return false;
            }
        }
        return true;
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
        return checkBomb();
    }

    public double formatY() {
        if (y - (int) y <= Setting.exp) {
            return (int) y;
        }
        if (y - (int) y > 1 - Setting.exp) {
            return (int) y + 1;
        }
        return y;
    }

    public double formatX() {
        if (x - (int) x <= Setting.exp) {
            return (int) x;
        }
        if (x - (int) x > 1 - Setting.exp) {
            return (int) x + 1;
        }
        return x;
    }

    public void Animation(Sprite x1, Sprite x2, Sprite x3, int time) {
        Sprite sprite = Sprite.movingSprite(x1, x2, x3, animation, time);
        img.setImage(sprite.getFxImage());
        animation++;
        if (animation > 1000000) {
            animation = 0;
        }
    }

    public void Animation(Sprite x1, Sprite x2, int time) {
        Sprite sprite = Sprite.movingSprite(x1, x2, animation, time);
        img.setImage(sprite.getFxImage());
        animation++;
        if (animation > 1000000) {
            animation = 0;
        }
    }

    public boolean intersect(Entity entity) {
        double _x = x + dx * speed;
        double _y = y + dy * speed;
        if (_x + 1 <= entity.getX()) return false;
        if (entity.getX() + 1 <= _x) return false;
        if (_y + 1 <= entity.getY()) return false;
        if (entity.getY() + 1 <= _y) return false;
        return true;
    }

    public void destroy() {
        Audio.entityDeathSound();
        remove();
    }
}
