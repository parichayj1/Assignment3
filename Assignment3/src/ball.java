import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class ball {
	int x,y;
	int size=16;
	int speed=2;
	int vx,vy;
	Rectangle boundingbox;
	
	public ball(int x, int y){
		this.x=x;
		this.y=y;
		vx=speed;
		vy=speed;
		boundingbox=new Rectangle(x,y,size,size);
    	boundingbox.setBounds(x,y,size,size);
		
	}
	
	public void tick(Game game){
		boundingbox.setBounds(x,y,size,size);
		if(x<=0){
			game.p2score++;
			vx=speed;
		}
		else if(x+size >= game.getWidth()){
			game.p1score++;
			vx = -speed;
		}
		if(y<=0){
			vy=speed;
		}
		else if(y+size >= game.getHeight()){
			vy = -speed;
		}
		x+=vx;
		y+=vy;
		paddlecollide(game);
	}
	
	public void render(Graphics g){
		g.setColor(Color.GREEN);
		g.fillOval(x, y, size, size);
	}
	
	private void paddlecollide(Game game){
		if(boundingbox.intersects(game.player.boundingbox)){
			vx = speed;
		}
		else if(boundingbox.intersects(game.ai.boundingbox)){
			vx = -speed;
		}
	}
}
