import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class InputHandler implements KeyListener{

	public InputHandler(Game game){
		game.addKeyListener(this);
	}

	public void keyPressed(KeyEvent e) {
		
		
		if (e.getKeyCode() == KeyEvent.VK_UP){
			System.out.println("pressed up");
			Game.player.goingUp=true;
			Game.player.goingDown=false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN){
			Game.player.goingDown=true;
			Game.player.goingUp=false;
		}
	}



	public void keyReleased(KeyEvent e) {
		
		
	}


	
	public void keyTyped(KeyEvent e) {
		
		
	}

}
