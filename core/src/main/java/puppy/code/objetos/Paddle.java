package puppy.code.objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import puppy.code.power.PaddleStrategy;
import puppy.code.torretas.Enemy;

public class Paddle implements Sprite {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private int x = 20;
    private int y = 20;
    private int width = 100;
    private int height = 10;
    private int speed = 15;
    private boolean hasEffect = false;
    private boolean effectSizeIncrease = false;
    private boolean effectSizeDecreases = false;
    private PaddleStrategy currentStrategy;

    private int escudo;
    public static final int DFLT_ESCUDO = 5;


    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public Paddle(int x, int y, int ancho, int alto, PaddleStrategy currentStrategy) {
        this.x = x;
        this.y = y;
        width = ancho;
        height = alto;
        escudo = DFLT_ESCUDO;
        this.currentStrategy = currentStrategy;
    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */
    
    public void setStrategy(PaddleStrategy strategy) {
        this.currentStrategy = strategy;
    }
    
    public PaddleStrategy getCurrentStrategy() {
        return currentStrategy;
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int newWhidth) {
        this.width = newWhidth;
    }

    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean getHasEffect() {
        return hasEffect;
    }

    public void setHasEffect(boolean hasEffect) {
        this.hasEffect = hasEffect;
    }

    public boolean getEffectSizeIncrease() {
        return effectSizeIncrease;
    }

    public void setEffectSizeIncrease(boolean effectSizeIncrease) {
        this.effectSizeIncrease = effectSizeIncrease;
    }

    public boolean getEffectSizeDecreases() {
        return effectSizeDecreases;
    }

    public void setEffectSizeDecreases(boolean effectSizeDecreases) {
        this.effectSizeDecreases = effectSizeDecreases;
    }


    public int getEscudo() {return escudo;}
    public void setEscudo(int escudo) {this.escudo = escudo;}

    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
    public void dibujar(ShapeRenderer shape) {
        shape.setColor(Color.BLUE);
        shape.rect(x, y, width, height);
    }

    public void actualizar() {
        int x2 = x; //= Gdx.input.getX();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
            x2 = x - speed;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
            x2 = x + speed;
        // y = Gdx.graphics.getHeight() - Gdx.input.getY();
        if (x2 > 0 && x2 + width < Gdx.graphics.getWidth()) {
            x = x2;
        }
    }



}
