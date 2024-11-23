package puppy.code.Pantallas;

import java.util.ArrayList;
//import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import puppy.code.*;


public class PantallaJuego extends Template {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private BlockBreakerGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer shape;
    private Texture background;
    private int backgroundY;

    private PingBall ball;
    private Paddle pad;
    private ArrayList<BlockDefinitive> blocks = new ArrayList<>();

    private int vidas;
    private int puntaje;
    private int nivel;

    private int contBall = 0 ;
    private int contPad = 0 ;

    private GameLogic gameLogic;


    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public PantallaJuego(int nivel, int puntaje, int vidas) {
        this.game = BlockBreakerGame.getInstancia();
        this.nivel = nivel;
        this.puntaje = puntaje;
        this.vidas = vidas; //Hay que cambiarlo al paddle ¿?
        gameLogic = new GameLogic(this);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, BlockBreakerGame.ANCHO_PANTALLA_PREDETERMINADO, BlockBreakerGame.ALTO_PANTALLA_PREDETERMINADO);

        batch = game.getBatch();
        font = game.getFont();
        font.getData().setScale(2, 2);
        gameLogic.crearBloques(blocks, 1 + nivel);

        shape = new ShapeRenderer();

        int ancho = BlockBreakerGame.ANCHO_PLATAFORMA_PREDETERMINADO;
        int alto = BlockBreakerGame.ALTO_PLATAFORMA_PREDETERMINADO;
        int radio = BlockBreakerGame.RADIO_PELOTA_PREDETERMINADO;

        int xPelota = Gdx.graphics.getWidth() / 2;
        int xPlataforma = Gdx.graphics.getWidth() / 2 - ancho / 2;

        ball = new PingBall(xPelota, 41, radio, 5, 7, true);
        pad = new Paddle(xPlataforma, 40, ancho, alto);

        background = new Texture(Gdx.files.internal("temp.jpg"));
        backgroundY = 0;


    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */
    public PingBall getBall(){return ball;}
    public Paddle getPad() {return pad;}
    public BlockBreakerGame getGame() {return game;}
    public int getVidas() {return vidas;}
    public int getPuntaje() {return puntaje;}
    public int getNivel() {return nivel;}
    public ShapeRenderer getShape() {return shape;}
    public int getContBall() {return contBall;}
    public int getContPad() {return contPad;}
    public OrthographicCamera getCamera() {return camera;}
    public SpriteBatch getBatch() {return batch;}
    public BitmapFont getFont() {return font;}

    public void setVidas(int vidas) {this.vidas = vidas;}
    public void setBall(PingBall ball) {this.ball = ball;}
    public void setPuntaje(int puntaje) {this.puntaje = puntaje;}
    public void setContBall(int contBall) {this.contBall = contBall;}
    public void setContPad(int contPad) {this.contPad = contPad;}
    public void setPad(Paddle pad) {this.pad = pad;}


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */

    protected void iniciar() {
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	batch.setProjectionMatrix(camera.combined);
        batch.begin();
        shape.begin(ShapeRenderer.ShapeType.Filled);
    }

    protected void dibujar() {
        batch.draw(background, 0, backgroundY, BlockBreakerGame.ANCHO_PANTALLA_PREDETERMINADO, 4*BlockBreakerGame.ALTO_PANTALLA_PREDETERMINADO);
        batch.end();
        pad.dibujar(shape);
        ball.dibujar(shape);
        gameLogic.dibujarTextos();
    }

    protected void actualizar() {
        //movimiento fondo
        backgroundY -= 2;
        // Si el fondo salio
        if (backgroundY <= -background.getWidth()-1100) {
            backgroundY = 0;
        }

    	pad.actualizar();
        ball.checkCollision(pad);
        // monitorear inicio del juego
        gameLogic.monitorStartup();
        //Monitorear pausa
        gameLogic.monitorPausee();
        //verificar si se fue la bola x abajo
        gameLogic.underPlataform();
        // verificar game over
        gameLogic.verifyGameOver();
        // verificar si el nivel se terminó
        gameLogic.verifyGameComplete(blocks);
        //dibujar bloques
        gameLogic.drawsBlocks(blocks);
        // actualizar estado de los bloques
        gameLogic.blockState(blocks);
        //verificar si la bola tiene efecto
        gameLogic.verifyBallEffect();
        //verificar si la plataforma tiene efecto
        gameLogic.verifyPadEffect();
    }

    protected void finalizar() {
    	shape.end();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
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
    	Screen aa = new PantallaPausa(this);
        getGame().setScreen(aa);
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }
}

