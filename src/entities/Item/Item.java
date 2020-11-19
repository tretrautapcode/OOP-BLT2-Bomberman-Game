package entities.Item;
import entities.DestroyAble.DestroyAble;
import entities.Entity;
import javafx.scene.image.Image;

public abstract class Item extends DestroyAble {
    public int type;
    public Item(double x, double y, Image img) {
        super(x, y, img,-1);
    }
    public abstract void update();
}
