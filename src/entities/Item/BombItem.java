package entities.Item;

import control.MyList;
import control.Setting;
import javafx.scene.image.Image;

public class BombItem extends Item{
    public BombItem(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if(intersect(MyList.player)){
            setIsDestroy(true);
            MyList.player.setMaxCountBomb(MyList.player.getMaxCountBombs()+1);
            this.remove();
        }
    }
}
