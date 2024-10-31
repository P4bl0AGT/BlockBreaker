package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;


public class PantallaGameOver implements Screen {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private BlockBreakerGame game;
    private OrthographicCamera camera;


    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public PantallaGameOver(BlockBreakerGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, BlockBreakerGame.ANCHO_PANTALLA_PREDETERMINADO, BlockBreakerGame.ALTO_PANTALLA_PREDETERMINADO);
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
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Game Over !!! ", 200, 600, 400, 1, true);
        game.getFont().draw(game.getBatch(), "Presiona una tecla para reiniciar ...", 200, 400);
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

