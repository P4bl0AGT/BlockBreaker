package puppy.code;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PingBall implements Andante {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private int x;
    private int y;
    private int size;
    private int xSpeed;
    private int ySpeed;
    private Color color = Color.WHITE;
    private boolean estaQuieto;


    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public PingBall(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        estaQuieto = iniciaQuieto;
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
        if (collidesWith(paddle)) {
            color = Color.GREEN;
            ySpeed = -ySpeed;
        } else {
            color = Color.WHITE;
        }
    }

    private boolean collidesWith(Paddle pp) {
        boolean intersectaX = (pp.getX() + pp.getWidth() >= x - size) && (pp.getX() <= x + size);
        boolean intersectaY = (pp.getY() + pp.getHeight() >= y - size) && (pp.getY() <= y + size);
        return intersectaX && intersectaY;
    }

    public void checkCollision(Block block) {
        if (collidesWith(block)) {
            ySpeed = -ySpeed;
            block.destroyed = true;
        }
    }

    private boolean collidesWith(Block bb) {
        boolean intersectaX = (bb.x + bb.width >= x - size) && (bb.x <= x + size);
        boolean intersectaY = (bb.y + bb.height >= y - size) && (bb.y <= y + size);
        return intersectaX && intersectaY;
    }

}
