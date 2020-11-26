import javafx.scene.canvas.GraphicsContext;


public abstract class Enemy extends BasicElement{

    private int pontuacao;

    public Enemy(int px, int py, int pontuacao){
        super(px, py);
        this.pontuacao = pontuacao;
    }
    
    @Override
    public void start(){
        setDirH(1);
    }

    @Override
    public void Update(long deltaTime){
        if (jaColidiu()){
            
            Game.getInstance().incPontos(pontuacao);

            deactivate();
        }else{
            setPosX(getX() + getDirH() * getSpeed());
            // Se chegou no lado direito da tela ...
            if (getX() >= getLMaxH() || getX() < getLMinH()){
                // Inverte a direção
                setDirH(getDirH() * -1);
                setPosY(getY()+20);

            }
        }
    }

    public abstract void Draw(GraphicsContext graphicsContext);

    @Override
    public void testaColisao(Character outro){
        if (outro instanceof Enemy){
            return;
        }else{
            super.testaColisao(outro);
        }
    }
}