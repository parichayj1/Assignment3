import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class AI {

	int x, y;
	boolean goingUp=false;
	boolean goingDown=false;
    private int d = PingPong.diff_level;
    private int width,height;
	Rectangle boundingbox;
	int speed;
   
    public AI(int x,int y) {
    	this.x = x;
    	this.y = y;
    	if(d == 1){
        	width = 15;
        	height = 40;
        	speed=3;
        }
        else{
        	width = 15;
        	height = 60;
        	speed=2;
        }
    	boundingbox=new Rectangle(x,y,width,height);
    	boundingbox.setBounds(x,y,width,height);

    }
    
    public void tick(Game game){
    	boundingbox.setBounds(x,y,width,height);
    	if(game.ball.y<y && y>=0){
    		
    		y-=speed;
    	}
    	else if(game.ball.y>y && y+height <= game.getHeight()){
    		y+=speed;
    	}
    }
    
    public void render(Graphics g){
    	g.setColor(Color.RED);
    	g.fillRect(x,y,width,height);
    }
    

    }