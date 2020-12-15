package entities.DestroyAble.Character;


import control.Audio;
import control.BombermanGame;
import control.Setting;
import control.MyList;
import entities.DestroyAble.Bomb;

import graphics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;


public class Bomber extends Character {
    private int maxCountBomb = 1;//số bomb tối đa có thể đặt
    private int countBomb=0;
    public Bomber(double x, double y, Image img) {
        super(x, y, img, Setting.timeBomberDestroy);
        speed = Setting.speedBomber;
    }

    @Override
    public void update() {
        BombermanGame.playerPane.getChildren().remove(img);
        BombermanGame.playerPane.getChildren().add(img);
        if (getIsDestroy()) {
            Animation(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, 30);
            if (checkDestroy()) {
                this.remove();
                Audio.bomberDeathSound();
            }
        } else {
            move();
        }
    }
    public int getMaxCountBombs(){
        return maxCountBomb;
    }

    public void setMaxCountBomb(int maxCountBomb) {
        this.maxCountBomb = maxCountBomb;
    }
    private int time=25;
    public boolean move() {
        if(super.move()){
            time--;
            if(time==0) {
                Audio.move();
                time=25;
            }
        }
        if (!isMoving) {
            if (dx == 1) {
                img.setImage(Sprite.player_right.getFxImage());
            }
            if (dx == -1) {
                img.setImage(Sprite.player_left.getFxImage());
            }
            if (dy == 1) {
                img.setImage(Sprite.player_down.getFxImage());
            }
            if (dy == -1) {
                img.setImage(Sprite.player_up.getFxImage());
            }
            dx = 0;
            dy = 0;
            return false;
        }
        if (dx == 1) {
            Animation(Sprite.player_right_1, Sprite.player_right_2, 20);

        }
        if (dx == -1) {
            Animation(Sprite.player_left_1, Sprite.player_left_2, 20);

        }
        if (dy == 1) {
            Animation(Sprite.player_down_1, Sprite.player_down_2, 20);
        }
        if (dy == -1) {
            Animation(Sprite.player_up_1, Sprite.player_up_2, 20);
        }
        return true;
    }
    public boolean checkBomb(int _x,int _y) {
        Bomb bomb=MyList.getBomb(_x,_y);
        if(bomb==null){
            return false;
        }
        return bomb.getOke();
    }

    public void KeyEvent() {
        BombermanGame.scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                setDx(1);
                isMoving = true;
            }
            if (event.getCode() == KeyCode.LEFT) {
                setDx(-1);
                isMoving = true;
            }
            if (event.getCode() == KeyCode.UP) {
                setDy(-1);
                isMoving = true;
            }
            if (event.getCode() == KeyCode.DOWN) {
                setDy(1);
                isMoving = true;
            }
        });
        BombermanGame.scene.setOnKeyReleased(event -> {
            isMoving = false;
            if (event.getCode() == KeyCode.SPACE&&countBomb<maxCountBomb) {
                Audio.placeBomb();
                Bomb bomb = new Bomb((int) (x * 2) - (int) x, (int) (y * 2) - (int) y, Sprite.bomb.getFxImage());
                bomb.addToPane();
                MyList.add(bomb);
                countBomb++;
            }
        });
    }

    public int getCountBomb() {
        return countBomb;
    }

    public void setCountBomb(int countBomb) {
        this.countBomb = countBomb;
    }
}
