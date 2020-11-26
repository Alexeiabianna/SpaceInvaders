import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class EnemyShooter extends Enemy {
    
    private int RELOAD_TIME = Integer.MAX_VALUE; // Time is in nanoseconds
    private static int PONTUACAO = 25;
    private int shot_timer = 0;
    private Image image;

    public EnemyShooter(int px, int py) {
        super(px, py, PONTUACAO);
        try{
            image =  new Image( "invader1.png",0,40,true,true );
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void Update(long deltaTime){
        super.Update(deltaTime);

        if(shot_timer <= 0){
            Game.getInstance().addChar(new Shot(getX()+16,getY()+64, 1));
            shot_timer = RELOAD_TIME;
        } else {
            shot_timer -= deltaTime;
        }
    }

    @Override
    public void start(){
        super.start();
        setSpeed(5);
    }

    @Override
    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.drawImage(image, getX(),getY());
    }
}
