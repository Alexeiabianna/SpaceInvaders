import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents a shot that crosses the screen from bottom to up and then dismiss
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Shot extends BasicElement{

    private int dirShot;

    public Shot(int px,int py, int dirShot){
        super(px,py);
        this.dirShot = dirShot;
    }

    @Override
    public void start(){
        setDirV(dirShot);
        setSpeed(5);
    }

    @Override
    public void testaColisao(Character outro){
        // Não verifica colisão de um tiro com outro tiro
        if (outro instanceof Shot){
            return;
        }else{
            super.testaColisao(outro);
        }
    }

    @Override
    public void Update(long deltaTime){
        if (jaColidiu()){
            deactivate();
        }else{
            setPosY(getY() + getDirV() * getSpeed());
            // Se chegou na parte superior da tela ...
            if (getY() <= getLMinV()){
                // Desaparece
                deactivate();
            }
        }
    }

    @Override
    public int getAltura(){
        return 16;
    }

    @Override
    public int getLargura(){
        return 8;
    }

    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.setFill(Paint.valueOf("#FF09FF"));
        graphicsContext.fillRect(getX(), getY(), 8, 16);
    }
}
