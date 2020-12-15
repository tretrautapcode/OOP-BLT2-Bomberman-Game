package entities.StillEntity;

import control.Audio;
import control.BombermanGame;
import control.MyList;
import entities.Entity;
import javafx.scene.image.Image;

public class Portal extends Entity {
    public Portal(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (intersect(MyList.player) && MyList.oneals.size() + MyList.ballooms.size() == 0) {
            MyList.loadMap();
        }
    }
}
