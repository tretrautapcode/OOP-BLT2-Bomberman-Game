package entities.MovedEntity;

import entities.Entity;

import javafx.scene.image.Image;

public class Bomb extends Entity {
    private boolean oke;
    public boolean getOke(){
        return oke;
    }

    public void setOke(boolean oke) {
        this.oke = oke;
    }

    public Bomb(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
}
