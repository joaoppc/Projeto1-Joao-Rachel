import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private static final int SIZE = 24;
	private static final int BORDER = 0;
	private final static int CELL_SIZE = 25; 

	private int width;
	private int height;
	private int xBoneco;
	private int yBoneco;
	private int xLeft;
	private int xRight;
	private int xQuadrado;
	private int yQuadrado;

	private boolean[][] labyrinth;
	private Image image;

	public Screen(boolean[][] labyrinth) {
		width = 2 * SIZE + 3 * BORDER;
		height = 2 * SIZE + 3 * BORDER;

		xLeft = BORDER + SIZE / 2;
		xRight = 2 * BORDER + 3 * SIZE / 2;

		xBoneco = xLeft;
		yBoneco = BORDER + SIZE / 2;

		xQuadrado = xLeft;
		yQuadrado = 2 * BORDER + 3 * SIZE / 2;
		this.labyrinth = labyrinth;

		this.width = this.labyrinth[0].length;
		this.height = this.labyrinth.length;

		setPreferredSize(new Dimension(this.width * CELL_SIZE, this.height * CELL_SIZE));
		image = new ImageIcon(getClass().getResource("/img/example.png")).getImage();
		Timer timer = new Timer(1000, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		for(int i = 0; i < this.height; i++) {
			int y = i * CELL_SIZE;

			for(int j = 0; j < this.width; j++) {
				int x = j * CELL_SIZE;

				if(labyrinth[i][j]) {
					g.setColor(Color.WHITE);
				}
				else {
					g.setColor(Color.BLACK);
				}

				g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
			}
		}
		g.setColor(Color.CYAN);
		g.fillRect(xQuadrado - SIZE / 2, yQuadrado - SIZE / 2, SIZE, SIZE);
		g.drawImage(image, xBoneco - SIZE / 2, yBoneco - SIZE / 2, SIZE, SIZE, null);
    	getToolkit().sync();
    }
	
	public void actionPerformed(ActionEvent e) {
		if(xQuadrado-CELL_SIZE>0){
			xQuadrado = xLeft;
			repaint();
		}
		
		if(xQuadrado+CELL_SIZE<width*CELL_SIZE) {
			xQuadrado = xRight;
			repaint();
		}
		
		if(yQuadrado-CELL_SIZE>0) {
			yQuadrado -= CELL_SIZE;
			repaint();
		}
		
		if(yQuadrado+CELL_SIZE<height*CELL_SIZE){
			yQuadrado += CELL_SIZE;
			repaint();
		}
	}
	
	public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();

    	if(key == KeyEvent.VK_LEFT) {    		
    		if(xBoneco-CELL_SIZE>0){
					xBoneco -= CELL_SIZE;
					repaint();
    			}
    		}

    	if(key == KeyEvent.VK_RIGHT) {
    		if(xBoneco+CELL_SIZE<width*CELL_SIZE){
    			xBoneco += CELL_SIZE;
    			repaint();
    		}
    	}
    	
    	if(key == KeyEvent.VK_UP) {
    		if(yBoneco-CELL_SIZE>0){
    			yBoneco -= CELL_SIZE;
    			repaint();
    		}
    	} 
    	
    	if(key == KeyEvent.VK_DOWN) {
    		if(yBoneco+CELL_SIZE<height*CELL_SIZE){
    			yBoneco += CELL_SIZE;
    			repaint();
    		}
    	}
	}

	public void keyReleased(KeyEvent event) {
	}

	public void keyTyped(KeyEvent event) {
	}

}

