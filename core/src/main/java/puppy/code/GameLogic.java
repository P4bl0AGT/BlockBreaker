package puppy.code;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import puppy.code.Pantallas.PantallaGameOver;
import puppy.code.Pantallas.PantallaJuego;
import puppy.code.Pantallas.PantallaNivelSuperado;
import puppy.code.Pantallas.PantallaPausa;


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
        p.getFont().draw(p.getBatch(), "Nivel : " + p.getNivel(), 410, 25);
        p.getFont().draw(p.getBatch(), "HighScore : " + p.getGame().getHighScore(), 610, 25);

        p.getFont().setColor((p.getContPad() != 0) ? Color.LIME: Color.WHITE);
        p.getFont().draw(p.getBatch(), "TimeP : " + (10 - p.getContPad() / 60), 850, 25);

        p.getFont().setColor((p.getContBall() != 0) ? Color.LIME: Color.WHITE);
        p.getFont().draw(p.getBatch(), "TimeB : " + (10 - p.getContBall() / 60), 1050, 25);

        p.getFont().setColor(Color.WHITE);
        p.getBatch().end();
    }

    public void crearBloques(ArrayList<BlockDefinitive> blocks, int filas) {
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

    // monitorear inicio del juego //
    public void monitorStartup(){

        if (p.getBall().estaQuieto()) {
        	p.getBall().setXY(this.ballXinPaddle(), this.ballYinPaddle());
        	if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        		p.getBall().setEstaQuieto(false);
        }else
        	p.getBall().actualizar();
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

    public void underPlataform(){
    	 //if (p.getBall().getY()<0) {
        if (p.getBall().getY() < p.getPad().getY()) {
             p.setVidas(p.getVidas() - 1);
             p.setContBall(0);
             p.setContPad(0);
             p.setBall(new PingBall(this.ballXinPaddle(), this.ballYinPaddle(), BlockBreakerGame.RADIO_PELOTA_PREDETERMINADO, 5, 7, true));
             p.setPad(new Paddle(20,20,150,10));
         }
    }

    public void verifyGameOver() {
        if (p.getVidas() <= 0) {
            if (p.getPuntaje() > p.getGame().getHighScore())
            	p.getGame().setHighScore(p.getPuntaje() );
            Screen ss = new PantallaGameOver();
            ss.resize(BlockBreakerGame.ANCHO_PANTALLA_PREDETERMINADO, BlockBreakerGame.ALTO_PANTALLA_PREDETERMINADO);
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

    public void createBlock(BlockDefinitive b) {
    	b.draw(p.getShape());
        p.getBall().checkCollision(b);
    }

    public boolean checkBlock(BlockDefinitive b) {
        if (b.isDestroyed()) {
            p.setPuntaje(p.getPuntaje()+1);
            if (!p.getBall().getHasEffect())
                b.applyEfect(p.getPad(), p.getBall());  // Aplica el efecto del bloque
            if (!p.getPad().getHasEffect())
                b.applyEfect(p.getPad(), p.getBall());  // Aplica el efecto del bloque
            return true;
        }
        return false;
    }

    public void verifyBallEffect() {
        if (p.getBall().getHasEffect()) {
        	p.setContBall(p.getContBall()+1);
        	System.out.println(p.getContBall() / 60);
        	if ((p.getContBall() / 60) >= 10) {
        		if (p.getBall().getEffectSlowDownBall()) {
                   new SlowDownBall().remove(null, p.getBall());
        		}
        		if(p.getBall().getEffectSizeIncrease()) {
        			new BallSizeIncrease().remove(null, p.getBall());
        		}
         		if (p.getBall().getEffectFastDownBall()) {
         			new FastDownBall().remove(null, p.getBall());
        		}
        		if(p.getBall().getEffectSizeDecreases()) {
        			new BallSizeDecreases().remove(null, p.getBall());
        		}

        		p.setContBall(0);

        	}
        }
    }

    public void verifyPadEffect() {
        if (p.getPad().getHasEffect()) {
        	p.setContPad(p.getContPad()+1);
        	System.out.println(p.getContPad() / 60);
        	if ((p.getContPad() / 60) >= 10) {
        		if (p.getPad().getEffectSizeIncrease()) {
        			new PaddleSizeIIncrease().remove(p.getPad(), null);
        		}

        		if(p.getPad().getEffectSizeDecreases()) {
        			new PaddleSizeDecreases().remove(p.getPad(), null);
        		}
        		p.setContPad(0);
        	}
        }
    }

    public void drawsBlocks(ArrayList<BlockDefinitive> blocks) {
    	for (BlockDefinitive b : blocks) {
        	createBlock(b);
        }
    }

    public void blockState(ArrayList<BlockDefinitive> blocks) {
    	for (int i = 0; i < blocks.size(); i++) {
            if (checkBlock(blocks.get(i))) {
            	blocks.remove(blocks.get(i));
                i--;  // Ajusta el índice después de eliminar
            }
        }
    }

}
