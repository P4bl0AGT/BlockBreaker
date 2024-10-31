package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;


public class GameLogic {
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
            Screen aa = new PantallaPausa(p.getGame(), p);
            p.getGame().setScreen(aa);
        }
    }

    public void underPlataform(){
    	 //if (p.getBall().getY()<0) {
        if (p.getBall().getY() < p.getPad().getY()) {
             p.setVidas(p.getVidas() - 1);
             p.setBall(new PingBall(this.ballXinPaddle(), this.ballYinPaddle(), BlockBreakerGame.RADIO_PELOTA_PREDETERMINADO, 5, 7, true));
         }
    }

    public void verifyGameOver() {
        if (p.getVidas() <= 0) {
            if (p.getPuntaje() > p.getGame().getHighScore())
            	p.getGame().setHighScore(p.getPuntaje() );
            Screen ss = new PantallaGameOver(p.getGame());
            ss.resize(BlockBreakerGame.ANCHO_PANTALLA_PREDETERMINADO, BlockBreakerGame.ALTO_PANTALLA_PREDETERMINADO);
            p.getGame().setScreen(ss);
            p.dispose();
        }
    }

    public void levelComplete(){
            Screen aa = new PantallaNivelSuperado(p.getGame(), p.getNivel(), p.getPuntaje(), p.getVidas());
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
        			p.getBall().setxSpeed(p.getBall().getxSpeed() * 2);
        			p.getBall().setySpeed(p.getBall().getySpeed() * 2);
        			p.getBall().setHasEffect(false);
        			p.getBall().setEffectSlowDownBall(false);
        		}
        		if(p.getBall().getEffectSizeIncrease()) {
        			p.getBall().setSize(15);
        			p.getBall().setHasEffect(false);
        			p.getBall().setEffectSizeIncrease(false);
        		}
         		if (p.getBall().getEffectFastDownBall()) {
         			p.getBall().setxSpeed(p.getBall().getxSpeed() / 2);
         			p.getBall().setySpeed(p.getBall().getySpeed() / 2);
         			p.getBall().setHasEffect(false);
         			p.getBall().setEffectFastDownBall(false);
        		}
        		if(p.getBall().getEffectSizeDecreases()) {
        			p.getBall().setSize(15);
        			p.getBall().setHasEffect(false);
        			p.getBall().setEffectSizeDecreases(false);
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
        			p.getPad().setWidth(p.getPad().getWidth() / 2);
        			p.getPad().setHasEffect(false);
        			p.getPad().setEffectSizeIncrease(false);

        		}

        		if(p.getPad().getEffectSizeDecreases()) {
        			p.getPad().setWidth(p.getPad().getWidth() * 2);
        			p.getPad().setHasEffect(false);
        			p.getPad().setEffectSizeDecreases(false);
        		}
        		p.setContPad(0);
        	}
    }
    }

}
