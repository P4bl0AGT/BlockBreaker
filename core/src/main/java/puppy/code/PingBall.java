package puppy.code;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class PingBall implements Sprite {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
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
    
    public boolean getEffectSlowDownBall() {
    	return effectSlowDownBall;
    }
    
    public void setEffectSlowDownBall(boolean effectSlowDownBall) {
    	this.effectSlowDownBall = effectSlowDownBall;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
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


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
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
        if (collidesWith(paddle) && estaQuieto) {
        	ySpeed = -ySpeed;
            color = Color.GREEN;
            
        }
        else if (collidesWith(paddle) && !estaQuieto) {
        	ySpeed = -ySpeed;
        	boing.play();
        	color = Color.GREEN;
        } else {
            color = Color.WHITE;
        }
    }

    private boolean collidesWith(Paddle pp) {
        boolean intersectaX = (pp.getX() + pp.getWidth() >= x - size) && (pp.getX() <= x + size);
        boolean intersectaY = (pp.getY() + pp.getHeight() >= y - size) && (pp.getY() <= y + size);
        return intersectaX && intersectaY;
    }

    public void checkCollision(BlockDefinitive block) {
        if (collidesWith(block)) {
            ySpeed = -ySpeed;
            block.takeHit(); 

            
            if (block.itsDestroyed()) {
            	breaking.play();
                block.destroyed = true;
                
            }
        }
    }


    private boolean collidesWith(BlockDefinitive bb) {
        boolean intersectaX = (bb.x + bb.width >= x - size) && (bb.x <= x + size);
        boolean intersectaY = (bb.y + bb.height >= y - size) && (bb.y <= y + size);
        return intersectaX && intersectaY;
    }

}
