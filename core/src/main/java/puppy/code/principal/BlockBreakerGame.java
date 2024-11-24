package puppy.code.principal;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import puppy.code.pantallas.PantallaMenu;


// BASE DE JUEGO
// NO MODIFICAR

public class BlockBreakerGame extends Game {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private SpriteBatch batch;
    private BitmapFont font;
    private int highScore;
    private static BlockBreakerGame instancia;


    /* = = = = = = = = = = = = CONSTANTES  = = = = = = = = = = = = = */
    public static final int DFLT_RADIO_PELOTA = 15;
    public static final int DFLT_ANCHO_PLATAFORMA = 150;
    public static final int DFLT_ALTO_PLATAFORMA = 12;
    public static final int DFLT_VIDAS = 3;
    public static final int DFLT_NIVEL = 1;
    public static final int DFLT_PUNTAJE = 0;
    public static final int DFLT_ANCHO_PANTALLA = 1200;
    public static final int DFLT_ALTO_PANTALLA = 800;

    public final static int DFLT_MAX_ENEM = 4;

    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    private BlockBreakerGame() {
    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */
    public void setBatch(SpriteBatch batch) {this.batch = batch;}
    public SpriteBatch getBatch() {
        return batch;
    }

    public void setFont(BitmapFont font) {this.font = font;}
    public BitmapFont getFont() {
        return font;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
    public int getHighScore() {
        return highScore;
    }


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
    // Metodo para Singleton
    public static BlockBreakerGame getInstancia() {
        // Crear la instancia solo si no existe
        if (instancia == null) {
            instancia = new BlockBreakerGame();
        }
        return instancia;
    }


    public void create() {
        BlockBreakerGame juego = BlockBreakerGame.getInstancia();

        juego.batch = new SpriteBatch();
        juego.font = new BitmapFont();
        juego.font.getData().setScale(2f);
        juego.highScore = 0;

        Screen ss = new PantallaMenu();
        juego.setScreen(ss);
    }


    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}
