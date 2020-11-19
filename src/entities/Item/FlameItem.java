package entities.Item;

import control.MyList;
import control.Setting;
import javafx.scene.image.Image;

public class FlameItem extends Item{
    public FlameItem(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if(intersect(MyList.player)){
            setIsDestroy(true);
            Setting.sizeBomb++;
            this.remove();
        }
    }
}
