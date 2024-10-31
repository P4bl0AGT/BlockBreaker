package puppy.code;

import java.util.ArrayList;
import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class PantallaJuego implements Screen {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private BlockBreakerGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer shape;

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
    public PantallaJuego(BlockBreakerGame game, int nivel, int puntaje, int vidas) {
        this.game = game;
        this.nivel = nivel;
        this.puntaje = puntaje;
        this.vidas = vidas; //Hay que cambiarlo al paddle ¿?
        gameLogic = new GameLogic(this);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, BlockBreakerGame.ANCHO_PANTALLA_PREDETERMINADO, BlockBreakerGame.ALTO_PANTALLA_PREDETERMINADO);

        batch = game.getBatch();
        font = game.getFont();
        font.getData().setScale(2, 2);
        crearBloques(1 + nivel);

        shape = new ShapeRenderer();

        int ancho = BlockBreakerGame.ANCHO_PLATAFORMA_PREDETERMINADO;
        int alto = BlockBreakerGame.ALTO_PLATAFORMA_PREDETERMINADO;
        int radio = BlockBreakerGame.RADIO_PELOTA_PREDETERMINADO;

        int xPelota = Gdx.graphics.getWidth() / 2;
        int xPlataforma = Gdx.graphics.getWidth() / 2 - ancho / 2;

        ball = new PingBall(xPelota, 41, radio, 5, 7, true);
        pad = new Paddle(xPlataforma, 40, ancho, alto);
    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */
    public PingBall getBall(){return ball;}
    public Paddle getPad() {return pad;}
    public BlockBreakerGame getGame() {return game;}
    public int getVidas() {return vidas;}
    public int getPuntaje() {return puntaje;}
    public int getNivel() {return nivel;}

    public ShapeRenderer getShape() {return shape;}

    public void setVidas(int vidas) {this.vidas = vidas;}
    public void setBall(PingBall ball) {this.ball = ball;}
    public void setPuntaje(int puntaje) {this.puntaje = puntaje;}


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
    public void crearBloques(int filas) {
        blocks.clear();
        int blockWidth = 140; //70
        int blockHeight = 40; //26
        int y = Gdx.graphics.getHeight();
        Random random = new Random();

        for (int cont = 0; cont < filas; cont++) {
            //separacion y entre bloques
            y -= blockHeight + 10;
            //separacion x entre bloques
            for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                BlockDefinitive block;
                int blockType = random.nextInt(3); // Genera un número aleatorio entre 0 y 2

                if (blockType == 0) {
                    block = new GoodBlock(x, y, blockWidth, blockHeight);
                } else if (blockType == 1) {
                    block = new BadBlock(x, y, blockWidth, blockHeight);
                } else {
                    block = new NormalBlock(x, y, blockWidth, blockHeight);
                }

                blocks.add(block);
            }
        }
    }

    public void dibujaTextos() {
        //actualizar matrices de la cámara
        camera.update();
        //actualizar
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //dibujar textos
        font.draw(batch, "Puntos: " + puntaje, 10, 25);
        font.draw(batch, "Vidas : " + vidas, 210, 25);
        font.draw(batch, "Nivel : " + nivel, 410, 25);
        font.draw(batch, "HighScore : " + game.getHighScore(), 610, 25);

        font.setColor((contPad != 0) ? Color.LIME: Color.WHITE);
        font.draw(batch, "TimeB : " + (10 - contPad / 60), 850, 25);

        font.setColor((contBall != 0) ? Color.LIME: Color.WHITE);
        font.draw(batch, "TimeP : " + (10 - contBall / 60), 1050, 25);

        font.setColor(Color.WHITE);
        batch.end();
    }

    @Override
    public void render(float delta) {
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        pad.actualizar();
        pad.dibujar(shape);

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
        if (blocks.isEmpty())
        	gameLogic.levelComplete();

        //dibujar bloques
        for (BlockDefinitive b : blocks) {
        	gameLogic.createBlock(b);
        }

        // actualizar estado de los bloques
        for (int i = 0; i < blocks.size(); i++) {
            if (gameLogic.checkBlock(blocks.get(i))) {
            	blocks.remove(blocks.get(i));
                i--;  // Ajusta el índice después de eliminar
            }

        }


        if (ball.getHasEffect()) {
        	contBall++;
        	System.out.println(contBall / 60);
        	if ((contBall / 60) >= 10) {
        		if (ball.getEffectSlowDownBall()) {
        			ball.setxSpeed(ball.getxSpeed() * 2);
        		    ball.setySpeed(ball.getySpeed() * 2);
            		ball.setHasEffect(false);
            		ball.setEffectSlowDownBall(false);
        		}
        		if(ball.getEffectSizeIncrease()) {
        			ball.setSize(15);
            		ball.setHasEffect(false);
            		ball.setEffectSizeIncrease(false);
        		}
         		if (ball.getEffectFastDownBall()) {
        			ball.setxSpeed(ball.getxSpeed() / 2);
        		    ball.setySpeed(ball.getySpeed() / 2);
            		ball.setHasEffect(false);
            		ball.setEffectFastDownBall(false);
        		}
        		if(ball.getEffectSizeDecreases()) {
        			ball.setSize(15);
            		ball.setHasEffect(false);
            		ball.setEffectSizeDecreases(false);
        		}

        		contBall = 0;

        	}
        }

        if (pad.getHasEffect()) {
        	contPad++;
        	System.out.println(contPad / 60);
        	if ((contPad / 60) >= 10) {
        		if (pad.getEffectSizeIncrease()) {
        			pad.setWidth(pad.getWidth() / 2);
            		pad.setHasEffect(false);
            		pad.setEffectSizeIncrease(false);

        		}

        		if(pad.getEffectSizeDecreases()) {
        			pad.setWidth(pad.getWidth() * 2);
        			pad.setHasEffect(false);
            		pad.setEffectSizeDecreases(false);
        		}
        		contPad = 0;
        	}
        }


        ball.dibujar(shape);
        shape.end();
        dibujaTextos();
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
}

