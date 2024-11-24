package puppy.code.principal;

import java.util.ArrayList;
import java.util.Random;
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
import com.badlogic.gdx.utils.Array;
import puppy.code.blocks.BlockDefinitive;
import puppy.code.pantallas.PantallaPausa;
import puppy.code.pantallas.Template;
import puppy.code.objetos.Paddle;
import puppy.code.objetos.PingBall;
import puppy.code.torretas.Director;
import puppy.code.torretas.Enemy;
import puppy.code.torretas.EnemyBuilder;


public class PantallaJuego extends Template {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private BlockBreakerGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer shape;
    private Texture background;
    private int backgroundY;
    private BackgroundHandler backgroundHandler;

    private PingBall aux;
    private Paddle pad;
    private ArrayList<BlockDefinitive> blocks = new ArrayList<>();
    private ArrayList<PingBall> balls = new ArrayList<>();

    private int vidas;
    private int puntaje;
    private int nivel;

    private int contBall = 0 ;
    private int contPad = 0 ;

    private GameLogic gameLogic;

    private Array<Enemy> listaEnemigos;
    private Director director;
    private EnemyBuilder builder;

    private int[][] matrizEnemigos = {
        {0, 0, 0, 0, 0, 0, 0, 0},  // Ocupado
        {5, 155, 305, 455, 605, 755, 905, 1055}      // PosX
    };

    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public PantallaJuego(int nivel, int puntaje, int vidas) {
        this.game = BlockBreakerGame.getInstancia();
        this.nivel = nivel;
        this.puntaje = puntaje;
        this.vidas = vidas;
        gameLogic = new GameLogic(this);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, BlockBreakerGame.DFLT_ANCHO_PANTALLA, BlockBreakerGame.DFLT_ALTO_PANTALLA);

        batch = game.getBatch();
        font = game.getFont();
        font.getData().setScale(2, 2);
        gameLogic.crearBloques(blocks, 1 + nivel);

        shape = new ShapeRenderer();

        int ancho = BlockBreakerGame.DFLT_ANCHO_PLATAFORMA;
        int alto = BlockBreakerGame.DFLT_ALTO_PLATAFORMA;
        int radio = BlockBreakerGame.DFLT_RADIO_PELOTA;

        int xPelota = Gdx.graphics.getWidth() / 2;
        int xPlataforma = Gdx.graphics.getWidth() / 2 - ancho / 2;

        aux = new PingBall(xPelota, 41, radio, 5, 7, true);
        balls.add(aux);
        pad = new Paddle(xPlataforma, 40, ancho, alto);

        //background = new Texture(Gdx.files.internal("temp.jpg"));
        backgroundHandler = new BackgroundHandler("temp.jpg");
        backgroundY = 0;

        listaEnemigos = new Array<>();
        director = new Director();
        builder = new EnemyBuilder();
    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */
    public PingBall getBall(){return aux;}
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
    public int getBackgroundY() {return backgroundY;}
    public Texture getBackground() {return background;}

    public void setVidas(int vidas) {this.vidas = vidas;}
    public void setBall(PingBall ball) {this.aux = ball;}
    public void setPuntaje(int puntaje) {this.puntaje = puntaje;}
    public void setContBall(int contBall) {this.contBall = contBall;}
    public void setContPad(int contPad) {this.contPad = contPad;}
    public void setPad(Paddle pad) {this.pad = pad;}
    public void setBackgroundY(int backgroundY) {this.backgroundY = backgroundY;}

    public int getEscudo(){return pad.getEscudo();}


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */

    protected void iniciar() {
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	batch.setProjectionMatrix(camera.combined);
        batch.begin();
        shape.begin(ShapeRenderer.ShapeType.Filled);
    }

    protected void dibujar() {
        //batch.draw(background, 0, backgroundY, BlockBreakerGame.DFLT_ANCHO_PANTALLA, 4*BlockBreakerGame.DFLT_ALTO_PANTALLA);
        backgroundHandler.dibujar(batch, BlockBreakerGame.DFLT_ANCHO_PANTALLA, BlockBreakerGame.DFLT_ALTO_PANTALLA);
        batch.end();
        pad.dibujar(shape);batch.begin();
        for (int i = 0; i < listaEnemigos.size; i++){
            listaEnemigos.get(i).getSprite().draw(game.getBatch());
            listaEnemigos.get(i).dibujarBalas(game.getBatch());
            }
        batch.end();
        for(PingBall ball:balls) {
        	ball.dibujar(shape);
        }
        gameLogic.dibujarTextos();

    }

    protected void actualizar() {

        //Mover fondo
        //gameLogic.moverFondo();
        backgroundHandler.moverFondo();

        //Crear enemigo con patron Builder
        crearEnemigo();

        //Comprobar colisiones torreta-ball paddle-bala
        gameLogic.comprobarColisionesTorretasMisiles(listaEnemigos, balls, matrizEnemigos);

        //Actualizar posicion pad
        pad.actualizar();

        //Comprobar colision pelota-pad
        gameLogic.verifyColissionPaddleBall(balls);
        
        // monitorear inicio del juego
        gameLogic.monitorStartup(balls);
        //Monitorear pausa
        gameLogic.monitorPausee();
        //verificar si se fue la bola x abajo
        gameLogic.underPlataform(balls);
        // verificar game over
        gameLogic.verifyGameOver();
        // verificar si el nivel se terminó
        gameLogic.verifyGameComplete(blocks);
        //dibujar bloques
        gameLogic.drawsBlocks(blocks,balls);
        // actualizar estado de los bloques
        gameLogic.blockState(blocks,balls);
        //verificar si la bola tiene efecto
        gameLogic.verifyBallEffect(balls);
        //verificar si la plataforma tiene efecto
        gameLogic.verifyPadEffect();
    }

    protected void finalizar() {
    	shape.end();
    }

    public void crearEnemigo(){
        if ((listaEnemigos.size <= nivel - 1) && (listaEnemigos.size <= BlockBreakerGame.DFLT_MAX_ENEM)) {
            int aux = gameLogic.obtenerPosicionValida(matrizEnemigos);

            Random rand = new Random();
            int tipo = rand.nextInt(3);

            if (tipo == 0) {
                director.buildEnemyType1(builder);
                Enemy enemigo1 = builder.getProducto();
                enemigo1.getSprite().setPosition(matrizEnemigos[1][aux], Enemy.DFLT_POS_Y);
                listaEnemigos.add(enemigo1);
            }
            else if (tipo == 1) {
                director.buildEnemyType2(builder);
                Enemy enemigo2 = builder.getProducto();
                enemigo2.getSprite().setPosition(matrizEnemigos[1][aux], Enemy.DFLT_POS_Y);
                listaEnemigos.add(enemigo2);
            }
            else if (tipo == 2) {
                director.buildEnemyType3(builder);
                Enemy enemigo3 = builder.getProducto();
                enemigo3.getSprite().setPosition(matrizEnemigos[1][aux], Enemy.DFLT_POS_Y);
                listaEnemigos.add(enemigo3);
            }
        }
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

