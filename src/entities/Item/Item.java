package entities.Item;
import entities.Entity;
import javafx.scene.image.Image;

public class Item extends Entity {
    public int type;
    public Item(double x, double y, Image img) {
        super(x, y, img);
    }
    @Override
    public void update() {

    }
}
