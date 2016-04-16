import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable,KeyListener {
	private static final long serialVersionUID = 1L;
	
	JFrame frame;
	public static Player player;
	public static AI ai;
	public static ball ball;
	InputHandler IH;
	public final int width = PingPong.WINDOW_WIDTH;
	public final int height = PingPong.WINDOW_HEIGHT;
	public final Dimension gameSize = new Dimension(width,height);
	BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	public int p1score;
	public int p2score;
	
	static boolean gameRunning = false;
	
	public void run(){
		 while(gameRunning){
			 tick();
			 render();
			 try{
				 Thread.sleep(7);
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		
	}

	public Game(){
		frame = new JFrame("Ping-Pong");
		frame.setSize(width,height);	
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		this.addKeyListener(this);
        this.setFocusable(true);
		
		player = new Player(10,60);
		ai = new AI(width-26,60);
		ball=new ball(width-6/2, height-28/2);
		start();
	}
	 
	 public void tick(){
		 player.tick(this);
		 ai.tick(this);
		 ball.tick(this);
	 }

	 public synchronized void start(){
		 gameRunning = true;
		 new Thread(this).start();
	 }
	 
	 public static synchronized void stop(){
		 gameRunning = false;
		 System.exit(0);
	 }
	 
		
		public void render(){
			 BufferStrategy bs= getBufferStrategy();
			 if(bs==null){
				 createBufferStrategy(3);
				 return;
			 }
			 Graphics g= bs.getDrawGraphics();
			
			 g.drawImage(image,0,0,getWidth(),getHeight(),null);
			 g.setColor(Color.WHITE);
			 g.drawString("Player1 : "+p1score,5,10);
			 g.drawString("Player2 : "+p2score,width-63,10);
			 player.render(g);
			 ai.render(g);
			 ball.render(g);
			 g.dispose();
			 bs.show();
		}
		
	 
	
	

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP){
			Game.player.goingUp=true;
			Game.player.goingDown=false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			Game.player.goingDown=true;
			Game.player.goingUp=false;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP){
			Game.player.goingUp=false;
			//Game.player.goingDown=false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			Game.player.goingDown=false;
			//Game.player.goingUp=false;
		}
		
	}


	public void keyTyped(KeyEvent arg0) {
		
	}
	
}
