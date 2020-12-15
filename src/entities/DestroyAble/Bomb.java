package entities.DestroyAble;

import control.Audio;
import control.Setting;
import control.MyList;
import graphics.Sprite;

import javafx.scene.image.Image;

public class Bomb extends DestroyAble {
    private boolean oke = false;//kiểm tra xem player đã di chuyển ra khỏi bomb hay chưa

    public boolean getOke() {
        return oke;
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
            MyList.player.setCountBomb(MyList.player.getCountBomb()-1);
        }
        if(!intersect(MyList.player)){
            oke=true;
        }
    }


    public void destroy() {
        Flame flame = new Flame(x, y, Sprite.bomb_exploded.getFxImage(), 0);
        MyList.add(flame);
        flame.addToPane();
        for (int i = 1; i <= Setting.sizeBomb; ++i) {
            if (i == Setting.sizeBomb) {
                destroyBrick((int) x - Setting.sizeBomb, (int) y, Sprite.explosion_horizontal, 1);
                break;
            }
            if (destroyBrick((int) x - i, (int) y, Sprite.explosion_horizontal, 6)) {
                break;
            }
        }
        for (int i = 1; i <= Setting.sizeBomb; ++i) {
            if (i == Setting.sizeBomb) {
                destroyBrick((int) x + Setting.sizeBomb, (int) y, Sprite.explosion_horizontal, 3);
                break;
            }
            if (destroyBrick((int) x + i, (int) y, Sprite.explosion_horizontal, 6)) {
                break;
            }
        }
        for (int i = 1; i <= Setting.sizeBomb; ++i) {
            if (i == Setting.sizeBomb) {
                destroyBrick((int) x, (int) y - Setting.sizeBomb, Sprite.explosion_vertical, 2);
                break;
            }
            if (destroyBrick((int) x, (int) y - i, Sprite.explosion_vertical, 5)) {
                break;
            }
        }
        for (int i = 1; i <= Setting.sizeBomb; ++i) {
            if (i == Setting.sizeBomb) {
                destroyBrick((int) x, (int) y + Setting.sizeBomb, Sprite.explosion_vertical, 4);
                break;
            }
            if (destroyBrick((int) x, (int) y + i, Sprite.explosion_vertical, 5)) {
                break;
            }
        }
        Audio.bombExplode();
        this.remove();
    }

    public boolean destroyBrick(int _x, int _y, Sprite sprite, int type) {
        Brick brick = MyList.getBrick(_x, _y);
        if (brick != null) {
            brick.setIsDestroy(true);
            return true;
        }
        if (MyList.getWall(_x, _y)!=null) {
            return true;
        }
        Flame flame = new Flame(_x, _y, sprite.getFxImage(), type);
        MyList.add(flame);
        flame.addToPane();
        return false;
    }
}
