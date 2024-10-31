package puppy.code;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

import java.util.Random;

public abstract class BlockDefinitive {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private int x, y, width, height;
    private Color cc;
    private boolean destroyed;
    private int resistance;
    private int currentResistance;


    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public BlockDefinitive(int x, int y, int width, int height, int resistance) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        destroyed = false;
        this.resistance = resistance;
        this.currentResistance = resistance;
        Random r = new Random(x + y);
        cc = new Color(0.1f + r.nextFloat(1), r.nextFloat(1), r.nextFloat(1), 10);
    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */
    public int getX() {return x;}
    public void setX(int x) {this.x = x;}

    public int getCurrentResistance() {return currentResistance;}
    public void setCurrentResistance(int currentResistance) {this.currentResistance = currentResistance;}

    public int getResistance() {return resistance;}
    public void setResistance(int resistance) {this.resistance = resistance;}

    public boolean isDestroyed() {return destroyed;}
    public void setDestroyed(boolean destroyed) {this.destroyed = destroyed;}

    public Color getCc() {return cc;}
    public void setCc(Color cc) {this.cc = cc;}

    public int getHeight() {return height;}
    public void setHeight(int height) {this.height = height;}

    public int getY() {return y;}
    public void setY(int y) {this.y = y;}

    public int getWidth() {return width;}
    public void setWidth(int width) {this.width = width;}


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
    public void draw(ShapeRenderer shape) {
        shape.setColor(cc);
        shape.rect(x, y, width, height);
    }

    public void takeHit() {
        if (currentResistance > 0) {
            currentResistance--;
        }
    }


    public boolean itsDestroyed() {
        return currentResistance <= 0;
    }


    public abstract void applyEfect(Paddle paddle, PingBall ball);
}

