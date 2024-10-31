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
