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
        	p.getBall().setXY(p.getPad().getX()+p.getPad().getWidth()/2+8, p.getPad().getY()+p.getPad().getHeight()+16);
        	if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        		p.getBall().setEstaQuieto(false);
        }else
        	p.getBall().actualizar();
    }

    public void monitorPausee(){
    	if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Screen aa = new PantallaPausa(p.getGame(), p);
            p.getGame().setScreen(aa);
        }
    }

    public void underPlataform(){
    	 if (p.getBall().getY()<0) {
         	p.setVidas(p.getVidas()-1);
         	p.setBall(new PingBall(p.getPad().getX()+p.getPad().getWidth()/2-5, p.getPad().getY()+p.getPad().getHeight()+11, 15, 5, 7, true));
         }
    }

    public void verifyGameOver() {
        if (p.getVidas() <= 0) {
            if (p.getPuntaje() > p.getGame().getHighScore())
            	p.getGame().setHighScore(p.getPuntaje() );
            Screen ss = new PantallaGameOver(p.getGame());
            ss.resize(1200, 800);
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


}
