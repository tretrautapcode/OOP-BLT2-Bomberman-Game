package entities.DestroyAble;

import control.Audio;
import control.BombermanGame;
import control.Setting;
import control.Support;
import graphics.Sprite;

import javafx.scene.image.Image;

public class Bomb extends DestroyAble {
    private boolean oke = false;
    private int size = 1;

    public boolean getOke() {
        return oke;
    }

    public void setOke(boolean oke) {
        this.oke = oke;
    }

    public Bomb(double x, double y, Image img) {
        super(x, y, img, Setting.timeBombsDestroy);
        setIsDestroy(true);
    }

    @Override
    public void update() {
        Animation(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, 60);
        if (checkDestroy()) {
            destroy();
        }
    }

    public void destroy() {
        Flame flame = new Flame(x, y, Sprite.bomb_exploded.getFxImage(), 0);
        BombermanGame.flames.add(flame);
        flame.addToPane();
        Audio.bombExplode();
        for (int i = 1; i <= size; ++i) {
            if (i == size) {
                destroyBrick((int) x - size, (int) y, Sprite.explosion_horizontal, 1);
                break;
            }
            if (destroyBrick((int) x - i, (int) y, Sprite.explosion_horizontal, 6)) {
                break;
            }
        }
        for (int i = 1; i <= size; ++i) {
            if (i == size) {
                destroyBrick((int) x + size, (int) y, Sprite.explosion_horizontal, 3);
                break;
            }
            if (destroyBrick((int) x + i, (int) y, Sprite.explosion_horizontal, 6)) {
                break;
            }
        }
        for (int i = 1; i <= size; ++i) {
            if (i == size) {
                destroyBrick((int) x, (int) y - size, Sprite.explosion_vertical, 2);
                break;
            }
            if (destroyBrick((int) x, (int) y - i, Sprite.explosion_vertical, 5)) {
                break;
            }
        }
        for (int i = 1; i <= size; ++i) {
            if (i == size) {
                destroyBrick((int) x, (int) y + size, Sprite.explosion_vertical, 4);
                break;
            }
            if (destroyBrick((int) x, (int) y + i, Sprite.explosion_vertical, 5)) {
                break;
            }
        }
        this.remove();
    }

    public boolean destroyBrick(int _x, int _y, Sprite sprite, int type) {
        Brick brick = Support.getBrick(_x, _y);
        if (brick != null) {
            brick.setIsDestroy(true);
            return true;
        }
        if (Support.isWall(_x, _y)) {
            return true;
        }
        Flame flame = new Flame(_x, _y, sprite.getFxImage(), type);
        BombermanGame.flames.add(flame);
        flame.addToPane();
        return false;
    }
}
