package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaMenu implements Screen {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private BlockBreakerGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture background;


    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public PantallaMenu(BlockBreakerGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 800);
        batch = game.getBatch();
        background = new Texture(Gdx.files.internal("Background.png"));
    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */
    public BlockBreakerGame getGame() {return game;}
    public void setGame(BlockBreakerGame game) {this.game = game;}

    public OrthographicCamera getCamera() {return camera;}
    public void setCamera(OrthographicCamera camera) {this.camera = camera;}


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, BlockBreakerGame.ANCHO_PANTALLA_PREDETERMINADO, BlockBreakerGame.ALTO_PANTALLA_PREDETERMINADO);
        batch.end();
        
        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Bienvenido a Block Breaker !", 140, 600);

        game.getFont().draw(game.getBatch(), "Teclas", 140, 500);
        game.getFont().draw(game.getBatch(), "[<-] ó [A] Mover Izquierda", 180, 450);
        game.getFont().draw(game.getBatch(), "[->] ó [D] Mover Derecha", 180, 400);
        game.getFont().draw(game.getBatch(), "[ESPACIO] Lanzar pelota", 180, 350);
        game.getFont().draw(game.getBatch(), "[ESC] Poner Pausa", 180, 300);

        game.getFont().draw(game.getBatch(), "  Presiona cualquier tecla para comenzar ...", 140, 200);

        game.getBatch().end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            Screen ss = new PantallaJuego(game, BlockBreakerGame.NIVEL_PREDETERMINADO, BlockBreakerGame.PUNTAJE_PREDETERMINADO, BlockBreakerGame.VIDAS_PREDETERMINADO);
            ss.resize(BlockBreakerGame.ANCHO_PANTALLA_PREDETERMINADO, BlockBreakerGame.ALTO_PANTALLA_PREDETERMINADO);
            game.setScreen(ss);
            dispose();
        }
    }

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


