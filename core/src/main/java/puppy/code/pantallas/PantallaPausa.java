package puppy.code.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import puppy.code.principal.BlockBreakerGame;

public class PantallaPausa extends Template {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private BlockBreakerGame game;
    private OrthographicCamera camera;
    private PantallaJuego pantalla;
    private final String[] strOpciones = {"Volver", "Menu Principal"};
    private int opcion = 0;
    private Texture background;
    private SpriteBatch batch;


    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public PantallaPausa(PantallaJuego pantalla) {
        this.game = BlockBreakerGame.getInstancia();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, BlockBreakerGame.DFLT_ANCHO_PANTALLA, BlockBreakerGame.DFLT_ALTO_PANTALLA);
        this.pantalla = pantalla;
        batch = game.getBatch();
        background = new Texture(Gdx.files.internal("Background01.png"));
    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */
    public BlockBreakerGame getGame() {return game;}
    public void setGame(BlockBreakerGame game) {this.game = game;}

    public OrthographicCamera getCamera() {return camera;}
    public void setCamera(OrthographicCamera camera) {this.camera = camera;}

    public PantallaJuego getPantalla() {return pantalla;}
    public void setPantalla(PantallaJuego pantalla) {this.pantalla = pantalla;}


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
    protected void iniciar() {
    	ScreenUtils.clear(0, 0, 0.2f, 1);

        batch.begin();
        batch.draw(background, 0, 0, BlockBreakerGame.DFLT_ANCHO_PANTALLA, BlockBreakerGame.DFLT_ALTO_PANTALLA);
        batch.end();
    }
	protected void dibujar() {
		game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Pausa !!! ", 140, 700, 400, 1, true);
	}
	protected void actualizar() {
		camera.update();
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
                game.setScreen(new PantallaMenu());
                dispose();
            }
        }
	}
	protected void finalizar() {
		game.getBatch().end();
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
