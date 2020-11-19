package control;

import graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BombermanGame extends Application {
    public static Pane playerPane;
    public static Scene scene;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        playerPane = new Pane();
        playerPane.setStyle("-fx-background-color: black;");
        Group root = new Group();
        root.getChildren().addAll(playerPane);
        scene = new Scene(root, Sprite.SCALED_SIZE * Setting.WIDTH, Sprite.SCALED_SIZE * Setting.HEIGHT);

        stage.setScene(scene);

        stage.show();
        loadMap();
        addToPane();
        render();
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(MyList.player.checkDestroy()){
                    stop();
                }
                update();
                render();
            }
        };
        gameLoop.start();
        MyList.player.KeyEvent();
    }

    public void loadMap() {
        MyList.loadMap();
    }

    public void update() {
        if(MyList.player!=null)
        MyList.player.update();
        MyList.update();
        for (int i=0;i< MyList.ballooms.size();)
        {
            MyList.ballooms.get(i).update();
            if (MyList.ballooms.get(i).checkDestroy()) {
                MyList.ballooms.remove(i);
            }else {
                i++;
            }
        }
        for (int i=0;i< MyList.oneals.size();)
        {
            MyList.oneals.get(i).update();
            if (MyList.oneals.get(i).checkDestroy()) {
                MyList.oneals.remove(i);
            }else {
                i++;
            }
        }
    }

    public void addToPane() {
        MyList.addToPane();
        MyList.ballooms.forEach(g->g.addToPane());
        MyList.oneals.forEach(g->g.addToPane());
        MyList.player.addToPane();
    }

    public void render() {
        MyList.render();
        MyList.ballooms.forEach(g->g.render());
        MyList.oneals.forEach(g->g.render());
        if(MyList.player!=null)
        MyList.player.render();
    }
}
