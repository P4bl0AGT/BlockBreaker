package puppy.code.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import puppy.code.principal.BlockBreakerGame;
import puppy.code.principal.PantallaJuego;


public class PantallaGameOver extends Template {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private BlockBreakerGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture background;

    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public PantallaGameOver() {
        this.game = BlockBreakerGame.getInstancia();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, BlockBreakerGame.DFLT_ANCHO_PANTALLA, BlockBreakerGame.DFLT_ALTO_PANTALLA);
        batch = game.getBatch();
        background = new Texture(Gdx.files.internal("Background03.png"));
    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */
    public BlockBreakerGame getGame() {
        return game;
    }

    public void setGame(BlockBreakerGame game) {
        this.game = game;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
    @Override
    protected void iniciar() {
    	ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        batch.setProjectionMatrix(camera.combined);
    }

    protected void dibujar() {
    	 batch.begin();
         batch.draw(background, 0, 0, BlockBreakerGame.DFLT_ANCHO_PANTALLA, BlockBreakerGame.DFLT_ALTO_PANTALLA);
         batch.end();

         game.getBatch().begin();
         game.getFont().draw(game.getBatch(), "Game Over !!! ", 200, 600, 400, 1, true);
         game.getFont().draw(game.getBatch(), "Presiona una tecla para reiniciar ...", 200, 400);
         game.getBatch().end();
    }

    protected void actualizar() {
    	 if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
             Screen ss = new PantallaJuego(BlockBreakerGame.DFLT_NIVEL, BlockBreakerGame.DFLT_PUNTAJE, BlockBreakerGame.DFLT_VIDAS);
             ss.resize(BlockBreakerGame.DFLT_ANCHO_PANTALLA, BlockBreakerGame.DFLT_ALTO_PANTALLA);
             game.setScreen(ss);
             dispose();
         }
    }

    protected void finalizar() {};

    @Override
    public void show() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    }

}

