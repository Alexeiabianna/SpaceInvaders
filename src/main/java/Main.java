import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.image.Image;

/**
 * Handles window initialization and primary game setup
 * @author Bernardo Copstein, Rafael Copstein
 */

/* public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Initialize Window
        stage.setTitle(Params.WINDOW_TITLE);
        stage.setResizable(false);

        Group root = new Group();
        Scene scene = new Scene( root );
        stage.setScene( scene );        

        Canvas canvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT );
        Image background = new Image("a.png",0,0,true,true);

        root.getChildren().add( canvas );

        // Setup Game object
        Game.getInstance().Start();

        // Register User Input Handler
        scene.setOnKeyPressed((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), true);
        });

        scene.setOnKeyReleased((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), false);
        });

        // Register Game Loop
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer()
        {
            long lastNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime)
            {
                long deltaTime = currentNanoTime - lastNanoTime;

                Game.getInstance().Update(currentNanoTime, deltaTime);
                gc.clearRect(0, 0, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
                gc.drawImage(background,0,0);
                gc.fillText("Pontos: "+Game.getInstance().getPontos(), 10, 10);
                Game.getInstance().Draw(gc);
                if (Game.getInstance().isGameOver()){
                    stop();
                }

                lastNanoTime = currentNanoTime;
            }

        }.start();

        // Show window
        stage.show();
    }

    public static void main(String args[]) {
        launch();
    }
} */



public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Initialize Window
        stage.setTitle(Params.WINDOW_TITLE);
        stage.setResizable(false);

        Group root = new Group();
        Scene scene = new Scene( root, Color.BLACK);
        stage.setScene( scene );        

        Canvas canvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT );

        root.getChildren().add( canvas );

        // Setup Game object
        Game.getInstance().Start();

        // Register User Input Handler
        scene.setOnKeyPressed((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), true);
        });

        scene.setOnKeyReleased((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), false);
        });

        // Register Game Loop
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer()
        {
            long lastNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime)
            {
                long deltaTime = currentNanoTime - lastNanoTime;
                // Retirar daqui e colocar em uma classe painel de status ?                
                Image credLife = new Image("credLife.png");
                
                Game.getInstance().Update(currentNanoTime, deltaTime);                
                gc.clearRect(0, 0, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
                
                // Retirar daqui e colocar em uma classe painel de status ? 
                // Set Styles Text
                gc.setFill( Color.WHITE);
                gc.setFont(Font.font( "file:resources/fonts/SpaceMono-Bold.ttf", FontWeight.BOLD, 20));
                gc.fillText("SCORE<1>",15,25);
                gc.fillText(""+Game.getInstance().getPontos(),15, 45);
                gc.fillText(""+Game.getInstance().getCanhao().getPtsVida(), 15, 790);

                gc.fillText("HI-SCORE",380,25);
                gc.fillText(""+Game.getInstance().getPontos(),380, 45); // Get do arquivo texto com maior pontuação.

                gc.fillText("SCORE<2>",685,25);
                gc.fillText(""+Game.getInstance().getPontos(),685, 45); // Get última pontuação do player.
                
                // Retira a imagem da vida após perder uma vida.
                if(Game.getInstance().getCanhao().getPtsVida() >= 2 ){
                    gc.drawImage(credLife, 35, 765);
                    gc.drawImage(credLife, 85, 765);
                }
                if(Game.getInstance().getCanhao().getPtsVida() <= 1){
                    gc.drawImage(credLife, 35, 765);
                }

                Game.getInstance().Draw(gc);
                if (Game.getInstance().isGameOver()){
                    stop();
                }

                lastNanoTime = currentNanoTime;
            }

        }.start();

        // Show window
        stage.show();
    }

    public static void main(String args[]) {
        launch();
    }
}
