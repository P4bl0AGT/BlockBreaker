package puppy.code.torretas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import puppy.code.objetos.Paddle;

public class Bullet {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private Sprite spr;
    private int velocidad;
    private int frecuencia;
    private int contador;
    private int golpe;


    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public Bullet(Texture tx, int x, int y, int ancho, int alto, int velocidad, int frecuencia, int golpe) {
        this.spr = new Sprite(tx);
        spr.setBounds(x, y, ancho, alto);
        this.velocidad = velocidad;
        this.frecuencia = frecuencia;
        this.contador = 200;
        this.golpe = golpe;
    }

    public Bullet(Bullet b) {
        this.spr = new Sprite(b.getSprite());
        spr.setBounds(b.getSprite().getX(), b.getSprite().getY(), b.getSprite().getWidth(), b.getSprite().getHeight());
        this.frecuencia = b.getFrecuencia();
        this.velocidad = b.getVelocidad();
        this.contador = b.getContador();
        this.golpe = b.getGolpe();
    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */
    public Sprite getSprite() {return spr;}
    public void setSpr(Sprite spr) {this.spr = spr;}

    public int getVelocidad() {return velocidad;}
    public void setVelocidad(int velocidad) {this.velocidad = velocidad;}

    public int getFrecuencia() {return frecuencia;}
    public void setFrecuencia(int frecuencia) {this.frecuencia = frecuencia;}

    public int getContador() {return contador;}
    public void setContador(int contador) {this.contador = contador;}

    public int getGolpe() {return golpe;}
    public void setGolpe(int golpe) {this.golpe = golpe;}

    public float getX(){return spr.getX();}
    public float getY(){return spr.getY();}
    public float getAncho(){return spr.getWidth();}
    public float getAlto(){return spr.getHeight();}


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
    public boolean collidesWith(Paddle pad){
        float x1Pad = pad.getX();
        float x2Pad = pad.getX() + pad.getWidth();
        float y1Pad = pad.getY();
        float y2Pad = pad.getY() + pad.getHeight();

        float xBala = spr.getX();
        float yBala = spr.getY();
        float sizeBala = spr.getHeight();

        boolean colisionX = (x1Pad <= xBala + sizeBala) && (x2Pad >= xBala);
        boolean colisionY = (yBala + sizeBala >= y1Pad) && (yBala <= y2Pad);

        return colisionX && colisionY;
    }
}
