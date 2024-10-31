package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaPausa implements Screen {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private BlockBreakerGame game;
    private OrthographicCamera camera;
    private PantallaJuego pantalla;
    private final String[] strOpciones = {"Volver", "Menu Principal"};
    private int opcion = 0;


    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public PantallaPausa(BlockBreakerGame game, PantallaJuego pantalla) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, BlockBreakerGame.ANCHO_PANTALLA_PREDETERMINADO, BlockBreakerGame.ALTO_PANTALLA_PREDETERMINADO);
        this.pantalla = pantalla;
    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */
    public BlockBreakerGame getGame() {return game;}
    public void setGame(BlockBreakerGame game) {this.game = game;}

    public OrthographicCamera getCamera() {return camera;}
    public void setCamera(OrthographicCamera camera) {this.camera = camera;}

    public PantallaJuego getPantalla() {return pantalla;}
    public void setPantalla(PantallaJuego pantalla) {this.pantalla = pantalla;}


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Pausa !!! ", 140, 700, 400, 1, true);

        for (int i = 0; i < strOpciones.length; i++) {
            if (i == opcion) {
                game.getFont().setColor(Color.GOLD);
                game.getFont().draw(game.getBatch(), "> " + strOpciones[i], 160, 400 - i * 50);
            } else {
                game.getFont().setColor(Color.WHITE);
                game.getFont().draw(game.getBatch(), "  " + strOpciones[i], 160, 400 - i * 50);
            }
        }
        game.getFont().setColor(Color.WHITE);
        game.getBatch().end();

        //Mover arriba abajo
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            opcion = (opcion == 0) ? 1: 0;
        }

        //Seleccionar opciones
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (opcion == 0) { //volver
                game.setScreen(pantalla);
                dispose();
            } else if (opcion == 1) { //menu
                game.setScreen(new PantallaMenu(game));
                dispose();
            }
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
