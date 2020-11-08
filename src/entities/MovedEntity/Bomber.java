package entities.MovedEntity;


import control.BombermanGame;
import entities.Entity;

import entities.StillEntity.Wall;
import graphics.Sprite;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Bomber extends Entity {
    private boolean isMoving = false;

    public Bomber(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        move();
    }

    public void move() {
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
            Sprite sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animation, 12);
            img.setImage(sprite.getFxImage());
            updateAnimation();
        }
        if (dx == -1) {
            Sprite sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animation, 12);
            img.setImage(sprite.getFxImage());
            updateAnimation();
        }
        if (dy == 1) {
            Sprite sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animation, 12);
            img.setImage(sprite.getFxImage());
            updateAnimation();
        }
        if (dy == -1) {
            Sprite sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animation, 12);
            img.setImage(sprite.getFxImage());
            updateAnimation();
        }
    }

    public void KeyEvent() {
        BombermanGame.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT) {
                    setDx(1);
                    y=formatY();
                    isMoving = true;
                }
                if (event.getCode() == KeyCode.LEFT) {
                    setDx(-1);
                    y=formatY();
                    isMoving = true;
                }
                if (event.getCode() == KeyCode.UP) {
                    setDy(-1);
                    x=formatX();
                    isMoving = true;
                }
                if (event.getCode() == KeyCode.DOWN) {
                    setDy(1);
                    x=formatX();
                    isMoving = true;
                }
                if (event.getCode() == KeyCode.SPACE) {
                    Bomb bomb = new Bomb((int)(x*2)-(int)x, (int)(y*2)-(int)y, Sprite.bomb.getFxImage());
                    bomb.addToPane();
                    bomb.render();
                    BombermanGame.bombs.add(bomb);
                    BombermanGame.playerPane.getChildren().remove(img);
                    BombermanGame.playerPane.getChildren().add(img);
                }
            }
        });
        BombermanGame.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                isMoving = false;
            }
        });
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
                if (bomb.getOke() == true) {
                    return false;
                }
            } else {
                bomb.setOke(true);
            }
        }
        return true;
    }
}
