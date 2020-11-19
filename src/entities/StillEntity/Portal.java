package entities.StillEntity;

import entities.Entity;
import javafx.scene.image.Image;

public class Portal extends Entity {
    private boolean isHide=true;
    public Portal(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
}
