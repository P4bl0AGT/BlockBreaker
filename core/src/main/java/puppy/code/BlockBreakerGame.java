package puppy.code;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


// BASE DE JUEGO
// NO MODIFICAR

public class BlockBreakerGame extends Game {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private SpriteBatch batch;
    private BitmapFont font;
    private int highScore;


    /* = = = = = = = = = = = = CONSTANTES  = = = = = = = = = = = = = */
    public static final int RADIO_PELOTA_PREDETERMINADO = 15;
    public static final int ANCHO_PLATAFORMA_PREDETERMINADO = 150;
    public static final int ALTO_PLATAFORMA_PREDETERMINADO = 12;
    public static final int VIDAS_PREDETERMINADO= 3;
    public static final int NIVEL_PREDETERMINADO = 1;
    public static final int PUNTAJE_PREDETERMINADO = 0;
    public static final int ANCHO_PANTALLA_PREDETERMINADO = 1200;
    public static final int ALTO_PANTALLA_PREDETERMINADO = 800;



    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2f);
        highScore = 0;
        Screen ss = new PantallaMenu(this);
        this.setScreen(ss);
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
    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}
