package puppy.code.objetos;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.audio.Sound;
import puppy.code.blocks.BlockDefinitive;
import puppy.code.power.BallStrategy;
import puppy.code.torretas.Enemy;
import puppy.code.torretas.Bullet;

import java.util.ArrayList;
import java.util.Random;

public class PingBall implements Sprite {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
	private ArrayList<PingBall> balls;
    private int x;
    private int y;
    private int size;
    private int xSpeed;
    private int ySpeed;
    private Color color = Color.WHITE;
    private boolean estaQuieto;
    private Sound boing;
    private Sound breaking;
    private boolean hasEffect = false;
    private boolean effectSizeIncrease = false;
    private boolean effectSlowDownBall = false;
    private boolean effectSizeDecreases = false;
    private boolean effectFastDownBall = false;
    private BallStrategy currentStrategy;


    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public PingBall(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        estaQuieto = iniciaQuieto;
        boing = Gdx.audio.newSound(Gdx.files.internal("Boing.mp3"));
        breaking = Gdx.audio.newSound(Gdx.files.internal("Break.mp3"));
        balls = new ArrayList<PingBall>();
    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size){this.size = size;}

    public int getxSpeed() {
        return xSpeed;
    }
    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }
    public int getySpeed() {
        return ySpeed;
    }
    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public boolean getEstaQuieto() {return estaQuieto;}
    public void setEstaQuieto(boolean estaQuieto) {this.estaQuieto = estaQuieto;}

    public Sound getBoingSound() {return boing;}
    public void setBoingSound(Sound boing) {this.boing = boing;}
    public Sound getBreakingSound() {return breaking;}
    public void setBreakingSound(Sound breaking) {this.breaking = breaking;}

    public boolean getHasEffect() {return hasEffect;}
    public void setHasEffect(boolean hasEffect) {this.hasEffect = hasEffect;}
    public boolean getEffectSizeIncrease() {return effectSizeIncrease;}
    public void setEffectSizeIncrease(boolean effectSizeIncrease) {this.effectSizeIncrease = effectSizeIncrease;}
    public boolean getEffectSlowDownBall() {return effectSlowDownBall;}
    public void setEffectSlowDownBall(boolean effectSlowDownBall) {this.effectSlowDownBall = effectSlowDownBall;}
    public boolean getEffectSizeDecreases() {return effectSizeDecreases;}
    public void setEffectSizeDecreases(boolean effectSizeDecreases) {this.effectSizeDecreases = effectSizeDecreases;}
    public boolean getEffectFastDownBall() {return effectFastDownBall;}
    public void setEffectFastDownBall(boolean effectFastDownBall) {this.effectFastDownBall = effectFastDownBall;}


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
    
    public void setStrategy(BallStrategy strategy) {
        this.currentStrategy = strategy;
    }
    
    public BallStrategy getCurrentStrategy() {
        return currentStrategy;
    }

    
    public boolean estaQuieto() {
        return estaQuieto;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void dibujar(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(x, y, size);
    }

    public void actualizar() {
        if (estaQuieto) return;
        x += xSpeed;
        y += ySpeed;
        if (x - size < 0 || x + size > Gdx.graphics.getWidth()) {
            xSpeed = -xSpeed;
        }
        if (y + size > Gdx.graphics.getHeight()) {
            ySpeed = -ySpeed;
        }
    }

    public void checkCollision(Paddle paddle) {
        boolean hayColision = collidesWith(paddle);
        if (hayColision) {
            ySpeed = -ySpeed;
            color = Color.GREEN;
            if (!estaQuieto)
                boing.play();
        }
        else
            color = Color.WHITE;
    }



    private boolean collidesWith(Paddle pp) {
        int padLeft = pp.getX();
        int padRight = pp.getX() + pp.getWidth();
        int padBottom = pp.getY();
        int padUp = pp.getY() + pp.getHeight();

        int ballLeft = x - size;
        int ballRight = x + size;
        int ballBottom = y - size;
        int ballUp = y + size;

        //Si choco
        if ((ballLeft <= padRight && ballRight >= padLeft) &&
            (ballUp >= padBottom && ballBottom <= padUp)) {
            y = padUp + size -1; //si esta dentro, -1 para asegurar colision
            return true;
        }
        return false;
    }



    public void checkCollision(BlockDefinitive block) {
        if (collidesWith(block)) {
            Random r = new Random(y + x);
            block.setCc(new Color(0.1f + r.nextFloat(1), r.nextFloat(1), r.nextFloat(1), 10));
            ySpeed = -ySpeed;
            block.takeHit();


            if (block.itsDestroyed()) {
            	breaking.play();
                block.setDestroyed(true);

            }
        }
    }


    private boolean collidesWith(BlockDefinitive bb) {
        boolean intersectaX = (bb.getX() + bb.getWidth() >= x - size) && (bb.getX() <= x + size);
        boolean intersectaY = (bb.getY() + bb.getHeight() >= y - size) && (bb.getY() <= y + size);
        return intersectaX && intersectaY;
    }


    public boolean checkCollision(Enemy ee){
        if (collidesWith(ee)) {
            ySpeed = -ySpeed;
            color = Color.GREEN;
            System.out.println("choque");
            return true;
        }
        else {
            color = Color.WHITE;
            return false;
        }

    }

    private boolean collidesWith(Enemy ee){
        float xEnemigo = ee.getSprite().getX();
        float yEnemigo = ee.getSprite().getY();
        float sizeEnemigo = ee.getSprite().getHeight();

        float xPelota = this.x;
        float yPelota = this.y;
        float sizePelota = this.size;

        boolean colisionX = (xPelota + sizePelota >= xEnemigo) && (xPelota - sizePelota <= xEnemigo + sizeEnemigo);
        boolean colisionY = (yPelota + sizePelota >= yEnemigo) && (yPelota - sizePelota <= yEnemigo + sizeEnemigo);

        return colisionX && colisionY;
    }
    public void add(PingBall ball) {
        if (ball != null) {
            balls.add(ball);
        }
    }

}
