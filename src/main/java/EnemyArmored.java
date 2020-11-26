import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class EnemyArmored extends Enemy{

    private Image image;
    private static int PONTUACAO = 100;

    public EnemyArmored(int px, int py){
        super(px,py,PONTUACAO);
        try{
            image =  new Image( "invader2.png",0,40,true,true );
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start(){
        super.start();
        setSpeed(5);
        setPtsVida(3);
    }

    @Override
    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.drawImage(image, getX(),getY());
    }
}
