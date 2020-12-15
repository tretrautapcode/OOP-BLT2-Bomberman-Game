package entities.Item;

import control.Audio;
import control.MyList;
import javafx.scene.image.Image;

public class SpeedItem extends Item {
    public SpeedItem(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (intersect(MyList.player)) {
            Audio.itemCollectSound();
            MyList.player.addSpeed();
            setIsDestroy(true);
            remove();
        }
    }
}
