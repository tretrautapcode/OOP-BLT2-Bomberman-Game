package entities;

import control.BombermanGame;
import entities.dow.Grass;
import javafx.scene.SnapshotParameters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import graphics.Sprite;

public abstract class Entity {
    protected double x;
    protected double y;
    protected double speed = 1;
    protected Image img;

    public Entity(double x, double y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public void render() {

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        Image base = iv.snapshot(params, null);

        BombermanGame.gc.drawImage(base, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }

    public abstract void update();

    public void Animation(Sprite x1, Sprite x2, Sprite x3, double dx, double dy, int time) {
        new Grass((int) x, (int) y, Sprite.grass.getFxImage()).render();
        Sprite sprite = Sprite.movingSprite(x1, x2, x3, BombermanGame.animation, time);
        img = sprite.getFxImage();
        x += dx;
        y += dy;
        render();
    }

    public void Animation(Sprite x1, Sprite x2, double dx, double dy, int time) {
        new Grass((int) x, (int) y, Sprite.grass.getFxImage()).render();
        Sprite sprite = Sprite.movingSprite(x1, x2, BombermanGame.animation, time);
        img = sprite.getFxImage();
        x += dx;
        y += dy;
        new Grass((int) x, (int) y, Sprite.grass.getFxImage()).render();
        render();
    }
}
