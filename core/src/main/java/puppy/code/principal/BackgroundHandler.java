package puppy.code.principal;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundHandler {
    private Texture background;
    private int backgroundY;

    public BackgroundHandler(String backgroundFilePath) {
        this.background = new Texture(Gdx.files.internal(backgroundFilePath));
        this.backgroundY = 0;
    }

    public void moverFondo() {
        backgroundY -= 1;
        if (backgroundY <= -background.getHeight() - 1100) {
            backgroundY = 0;
        }
    }

    public void dibujar(SpriteBatch batch, int screenWidth, int screenHeight) {
        batch.draw(background, 0, backgroundY, screenWidth, 4 * screenHeight);
    }

    public Texture getBackground() {
        return background;
    }

    public int getBackgroundY() {
        return backgroundY;
    }
}
