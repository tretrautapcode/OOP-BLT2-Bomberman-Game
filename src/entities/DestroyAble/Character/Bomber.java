package entities.DestroyAble.Character;


import control.BombermanGame;
import control.Setting;
import entities.DestroyAble.Bomb;

import graphics.Sprite;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Bomber extends Character {
    private boolean isMoving = false;
    private int maxCountBombs = 1;//số bomb tối đa có thể đặt

    public Bomber(double x, double y, Image img) {
        super(x, y, img, Setting.timeBomberDestroy);
        speed = 0.0625;
    }

    @Override
    public void update() {
        BombermanGame.playerPane.getChildren().remove(img);
        BombermanGame.playerPane.getChildren().add(img);
        if (getIsDestroy()) {
            Animation(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, 30);
            if (checkDestroy()){
                this.remove();
            }
        } else {
            move();
        }
    }

    public void move() {
        if (checkMove() == false) {
            x = formatX();
            y = formatY();
        }
        if (isMoving == false || checkMove() == false) {
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
            return;
        }
        if (checkMove() == true) {
            x = x + dx * speed;
            y = y + dy * speed;
        }
        if (dx == 1) {
            Animation(Sprite.player_right_1, Sprite.player_right_2, 12);

        }
        if (dx == -1) {
            Animation(Sprite.player_left_1, Sprite.player_left_2, 12);

        }
        if (dy == 1) {
            Animation(Sprite.player_down_1, Sprite.player_down_2, 12);
        }
        if (dy == -1) {
            Animation(Sprite.player_up_1, Sprite.player_up_2, 12);
        }
    }

    public boolean checkBomb() {
        for (Bomb bomb : BombermanGame.bombs) {
            if (intersect(bomb)) {
                if (bomb.getOke() == true) {
                    return false;
                }
            } else {
                bomb.setOke(true);
            }
        }
        return true;
    }

    public void KeyEvent() {
        BombermanGame.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
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
            }
        });
        BombermanGame.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                isMoving = false;
                if (event.getCode() == KeyCode.SPACE && BombermanGame.bombs.size() < maxCountBombs) {
                    Bomb bomb = new Bomb((int) (x * 2) - (int) x, (int) (y * 2) - (int) y, Sprite.bomb.getFxImage());
                    bomb.addToPane();
                    BombermanGame.bombs.add(bomb);
                }
            }
        });
    }
}
