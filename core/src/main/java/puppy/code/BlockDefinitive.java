package puppy.code;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import java.util.Random;

public abstract class BlockDefinitive {
	
	  int x,y,width,height;
	  Color cc;
	  boolean destroyed;
	  int resistance;
	  protected int currentResistance ;
	  
	  public BlockDefinitive(int x, int y, int width, int height, int resistance) {
	        this.x = x;
	        this.y = y;
	        this.width = width;
	        this.height = height;
	        destroyed = false;
	        this.resistance = resistance; 
	        this.currentResistance = resistance;
	        Random r = new Random(x+y);
	        cc = new Color(0.1f+r.nextFloat(1), r.nextFloat(1), r.nextFloat(1), 10);
	  
	    }
	  
	    public void draw(ShapeRenderer shape){
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
	    
	    
	    public abstract void applyEfect();
}

