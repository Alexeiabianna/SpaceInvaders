import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EnemySimple extends Enemy {
    private Image image;
    private static int PONTUACAO = 10;

    public EnemySimple(int px,int py){
        super(px,py,PONTUACAO);
        try{
            // Carrega a imagem ajustando a altura para 40 pixels
            // mantendo a proporção em ambas dimensões
            image =  new Image( "enemy01.png",0,40,true,true );
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start(){
        super.start();
        setSpeed(4);
    }

    @Override
    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.drawImage(image, getX(),getY());
    }
}
