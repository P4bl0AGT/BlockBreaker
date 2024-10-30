package puppy.code;

import java.util.ArrayList;
import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class PantallaJuego implements Screen {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private BlockBreaker game;
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

    private boolean pausa = false;
 

    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public PantallaJuego(BlockBreaker game, int nivel, int puntaje, int vidas) {
        this.game = game;
        this.nivel = nivel;
        this.puntaje = puntaje;
        this.vidas = vidas; //Hay que cambiarlo al paddle ¿?

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 800);

        batch = game.getBatch();
        //font = new BitmapFont();
        font = game.getFont();
        font.getData().setScale(2, 2);
        crearBloques(2 + nivel); 

        shape = new ShapeRenderer();
        ball = new PingBall(Gdx.graphics.getWidth() / 2 - 10, 41, 15, 5, 7, true);
        pad = new Paddle(Gdx.graphics.getWidth() / 2 - 50, 40, 150, 12);
    } 


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */



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
        batch.end();
    }

    @Override
    public void render(float delta) {
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        pad.dibujar(shape);
        
        ball.checkCollision(pad);

        // monitorear inicio del juego
        if (ball.estaQuieto()) {
        	ball.setXY(pad.getX()+pad.getWidth()/2+8, pad.getY()+pad.getHeight()+16);
        	if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
                ball.setEstaQuieto(false);
        }else
            ball.actualizar();

        //Monitorear pausa
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Screen aa = new PantallaPausa(game, this);
            game.setScreen(aa);
        }


        //verificar si se fue la bola x abajo
        if (ball.getY()<0) {
        	vidas--;
        	//nivel = 1;
        	ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 15, 5, 7, true);
        }

        // verificar game over
        if (vidas <= 0) {
            if (puntaje > game.getHighScore())
                game.setHighScore(puntaje);
            Screen ss = new PantallaGameOver(game);
            ss.resize(1200, 800);
            game.setScreen(ss);
            dispose();
        }

        // verificar si el nivel se terminó
        if (blocks.isEmpty()) {
            Screen aa = new PantallaNivelSuperado(game, nivel, puntaje, vidas);
            game.setScreen(aa);
            dispose();
        }

        //dibujar bloques
        for (BlockDefinitive b : blocks) {
            b.draw(shape);
            ball.checkCollision(b);
        }

        // actualizar estado de los bloques
        for (int i = 0; i < blocks.size(); i++) {
            BlockDefinitive b = blocks.get(i);
            if (b.destroyed) {
                puntaje++;
                b.applyEfect(pad, ball);  // Aplica el efecto del bloque
                blocks.remove(b);
                i--;  // Ajusta el índice después de eliminar
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

