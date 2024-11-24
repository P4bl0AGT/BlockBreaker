package puppy.code.principal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import puppy.code.blocks.BadBlock;
import puppy.code.blocks.BlockDefinitive;
import puppy.code.blocks.GoodBlock;
import puppy.code.blocks.NormalBlock;
import puppy.code.objetos.Paddle;
import puppy.code.pantallas.PantallaGameOver;
import puppy.code.pantallas.PantallaNivelSuperado;
import puppy.code.pantallas.PantallaPausa;
import puppy.code.objetos.PingBall;
import puppy.code.power.*;


public class GameLogic{
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private PantallaJuego p;



    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public GameLogic(PantallaJuego pantallaJuego) {
        this.p = pantallaJuego;
    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */
    public PantallaJuego getPantallaJuego() {return p;}
    public void setPantallaJuego(PantallaJuego p) {this.p = p;}



    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
    public void dibujarTextos() {
    	//actualizar matrices de la cámara
        p.getCamera().update();
        //actualizar
        p.getBatch().setProjectionMatrix(p.getCamera().combined);
        p.getBatch().begin();
        //dibujar textos
        p.getFont().draw(p.getBatch(), "Puntos: " + p.getPuntaje(), 10, 25);
        p.getFont().draw(p.getBatch(), "Vidas : " + p.getVidas(), 210, 25);
        p.getFont().draw(p.getBatch(), "Escudo : " + p.getEscudo(), 210, 50);
        p.getFont().draw(p.getBatch(), "Nivel : " + p.getNivel(), 410, 25);
        p.getFont().draw(p.getBatch(), "HighScore : " + p.getGame().getHighScore(), 610, 25);

        p.getFont().setColor((p.getContPad() != 0) ? Color.LIME: Color.WHITE);
        p.getFont().draw(p.getBatch(), "Paddle : " + (10 - p.getContPad() / 60), 850, 25);

        p.getFont().setColor((p.getContBall() != 0) ? Color.LIME: Color.WHITE);
        p.getFont().draw(p.getBatch(), "Ball : " + (10 - p.getContBall() / 60), 1050, 25);

        p.getFont().setColor(Color.WHITE);
        p.getBatch().end();
    }

    public void crearBloques(ArrayList<BlockDefinitive> blocks, int filas) {
        blocks.clear();
        int blockWidth = 140; //70
        int blockHeight = 40; //26
        int y = Gdx.graphics.getHeight() - 74;
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

    // monitorear inicio del juego //
    public void monitorStartup(ArrayList<PingBall>balls){
    	
    	for (PingBall ball:balls) {
	        if (ball.estaQuieto()) {
	        	ball.setXY(this.ballXinPaddle(), this.ballYinPaddle());
	        	if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
	        		ball.setEstaQuieto(false);
	        		
	        	}
	        }
	        else
        		ball.actualizar();
    	}
    }

    public int ballXinPaddle() {
        //(posicion Y plataforma) + (mitad plataforma)
        return p.getPad().getX() + p.getPad().getWidth() / 2;
    }

    public int ballYinPaddle() {
        int extraY = 2;
        //(Posicion Y plataforma) + (Altura plataforma) + (Radio pelota) + (Extra)
        return p.getPad().getY() + p.getPad().getHeight() + p.getBall().getSize() + extraY;
    }

    public void monitorPausee(){
    	if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Screen aa = new PantallaPausa(p);
            p.getGame().setScreen(aa);
        }
    }

    public void underPlataform(ArrayList<PingBall> balls) {
        for (int i = 0; i < balls.size(); i++) {
            if (balls.get(i).getY() < p.getPad().getY()) {
                balls.remove(i);

                // Resetear estado de la bola y efectos activos
                if (balls.size() == 0) {
                    p.setVidas(p.getVidas() - 1);
                    p.setContBall(0);
                    p.setContPad(0);
               

                    // Agregar una nueva bola
                    PingBall aux = new PingBall(this.ballXinPaddle(), this.ballYinPaddle(), BlockBreakerGame.DFLT_RADIO_PELOTA, 5, 7, true);
                    balls.add(aux);

                    // Reiniciar plataforma
                    int ancho = BlockBreakerGame.DFLT_ANCHO_PLATAFORMA;
                    int alto = BlockBreakerGame.DFLT_ALTO_PLATAFORMA;
                    int xPlataforma = Gdx.graphics.getWidth() / 2 - ancho / 2;
                    p.setPad(new Paddle(xPlataforma, 40, ancho, alto));
                }
            }
        }
    }


    public void verifyGameOver() {
        if (p.getVidas() <= 0) {
            if (p.getPuntaje() > p.getGame().getHighScore())
            	p.getGame().setHighScore(p.getPuntaje() );
            Screen ss = new PantallaGameOver();
            ss.resize(BlockBreakerGame.DFLT_ANCHO_PANTALLA, BlockBreakerGame.DFLT_ALTO_PANTALLA);
            p.getGame().setScreen(ss);
            p.dispose();
        }
    }

    public void verifyGameComplete(ArrayList<BlockDefinitive> blocks) {
    	if (blocks.isEmpty())
        	levelComplete();
    }

    public void levelComplete(){
            Screen aa = new PantallaNivelSuperado(p.getNivel(), p.getPuntaje(), p.getVidas());
            p.getGame().setScreen(aa);
            p.dispose();
    }

    public void createBlock(BlockDefinitive b, ArrayList<PingBall>balls) {
    	
    	b.draw(p.getShape());
    	for(PingBall ball:balls) {
    		ball.checkCollision(b);
    	}
    }

    public boolean checkBlock(BlockDefinitive b, PingBall ball, ArrayList<PingBall>balls) {
        if (b.isDestroyed()) {
            p.setPuntaje(p.getPuntaje()+1);
            b.applyEfect(p.getPad(), balls);
            return true;
        }
    	return false;
    }

    public void verifyBallEffect(ArrayList<PingBall> balls) {
    	for (PingBall ball:balls) {
	        if (ball.getHasEffect()) {
	            p.setContBall(p.getContBall() + 1);
	            if (p.getContBall() >= 600) {
	                ball.getCurrentStrategy().remove(balls);
	                p.setContBall(0);
	                ball.setHasEffect(false); // Desactivar el efecto
	            }
	        }
    	}
    }

    public void verifyPadEffect() {
        if (p.getPad().getHasEffect()) {
            p.setContPad(p.getContPad() + 1);
            if (p.getContPad() >= 600) {
                p.getPad().getCurrentStrategy().remove(p.getPad());
                p.setContPad(0);
                p.getPad().setHasEffect(false); // Desactivar el efecto
            }
        }
    }



    public void drawsBlocks(ArrayList<BlockDefinitive> blocks, ArrayList<PingBall>balls) {
    	for (BlockDefinitive b : blocks) {
        	createBlock(b, balls);
        }
    }

    public void blockState(ArrayList<BlockDefinitive> blocks, ArrayList<PingBall> balls) {
        for (Iterator<BlockDefinitive> iterator = blocks.iterator(); iterator.hasNext(); ) {
            BlockDefinitive block = iterator.next(); 
            
            for (PingBall ball : balls) {
                if (checkBlock(block, ball, balls)) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

}
