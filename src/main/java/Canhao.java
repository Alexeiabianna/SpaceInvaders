import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;

/**
 * Represents the game Gun
 * @author Bernardo Copstein, Rafael Copstein
 */
public class Canhao extends BasicElement implements KeyboardCtrl{
    private int RELOAD_TIME = 1000000000; // Time is in nanoseconds
    private int shot_timer = 0;
    private Image image;

    public Canhao(int px,int py){
        super(px,py);
        setPtsVida(2);
        setSpeed(7);
        try{
            // Carrega a imagem ajustando a altura para 40 pixels
            // mantendo a proporção em ambas dimensões
            image =  new Image( "cannon.png",0,38,true,true );
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start() {
        setLimH(20,Params.WINDOW_WIDTH-20);
        setLimV(Params.WINDOW_HEIGHT-100,Params.WINDOW_HEIGHT);
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()){
            //persiste a quantidade de pontos feitos no arquivo texto.
            Game.getInstance().setGameOver();
        }else{
            
            if (shot_timer > 0) shot_timer -= deltaTime;

            setPosX(getX() + getDirH() * getSpeed());
            //setPosY(getY() + getDirV() * getSpeed());
            // Se chegou no lado direito da tela ...
            if (getX() >= getLMaxH()){
                setPosX(getLMaxH()); //faz ele bater na parede

            } else if(getX() < getLMinH()){
                setPosX(getLMinH());
            }
        }
        
    }

    @Override
    public void OnInput(KeyCode keyCode, boolean isPressed) {
        if(keyCode == KeyCode.UP){
            int dh = isPressed ? -1 : 0;
            setDirV(dh);
        }
        if(keyCode == KeyCode.DOWN){
            int dh = isPressed ? 1 : 0;
            setDirV(dh);
        }

        if (keyCode == KeyCode.LEFT){
            int dh = isPressed ? -1 : 0; 
            setDirH(dh);
        }
        if (keyCode == KeyCode.RIGHT){
            int dh = isPressed ? 1 : 0;
            setDirH(dh);
        }
        if (keyCode == KeyCode.SPACE){
            if (shot_timer <= 0) {
                Game.getInstance().addChar(new Shot(getX()+16, getY()-32, -1));
                shot_timer = RELOAD_TIME;
            }
        }
        //if (keyCode == KeyCode.UP) do nothing
        //if (keyCode == KeyCode.DOWN) do nothing
    }

    @Override
    public int getAltura(){
        return 80;
    }

    @Override
    public int getLargura(){
        return 32;
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, getX(),getY());
        /*
        graphicsContext.setFill(Paint.valueOf("#FF0000"));
        graphicsContext.fillRect(getX(), getY()+16, 32, 32);
        graphicsContext.fillRect(getX()+8, getY()-16, 16, 48);
        */
    }
}
