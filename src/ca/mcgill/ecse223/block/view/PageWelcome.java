package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorChooserUI;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

/**
 * Page Welcome for the user
 * @author Imane Chafi
 * */

public class PageWelcome extends ContentPage {
	//UI elements
		private static Font defaultFont = new Font("Bank Gothic",Font.PLAIN,14);
		private static Font titleFont = new Font("Futura",Font.PLAIN,60);
		BouncingBall ball = new BouncingBall(150,310,5,5);
		//... Instance variables for the animiation
	    private int   m_interval  = 35;  // Milliseconds between updates.
	    private Timer m_timer;           // Timer fires to anmimate one step.
		JLabel Block223Welcome;
		
		//*******************
		//Constructor method
		//*******************
		public PageWelcome(Block223MainPage frame) {
			super(frame);
			setBackground(new Color (62, 61, 60));
			m_timer = new Timer(m_interval, new TimerAction());
			m_timer.start();  // start animation by starting the timer.
	
			//*****************
		    //UI elements
		    //*****************
			//create the font

			try {
			     GraphicsEnvironment ge = 
			         GraphicsEnvironment.getLocalGraphicsEnvironment();
			     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Potra.ttf")));
			} catch (IOException|FontFormatException e) {
			     //Handle exception
			}

	
			//Welcome elements
			Block223Welcome = new JLabel("Block 223");
			Block223Welcome.setFont(new Font("Consolas",Font.PLAIN,60));
			Block223Welcome.setForeground(new Color(227, 228, 219 ));
			add(Block223Welcome);
			
			//Border border = BorderFactory.createLineBorder(Block223MainPage.getHeaderBackground(), 3);
			
			
		    //Start button panel
		    JPanel StartButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		    StartButtonPanel.setLocation(250, 300);
		    
		    StartButtonPanel.setBackground(Color.WHITE);
		    JButton StartButton = createButton(" Start Game ");
		    StartButton.setFont(new Font("Consolas",Font.PLAIN,40));
		    StartButton.setBackground(new Color (62, 61, 60));
		    Border border1 = new LineBorder(Color.WHITE, 3);
		    StartButton.setBorder(border1);
		    StartButton.setForeground(Color.WHITE);
		    StartButtonPanel.add(StartButton,BorderLayout.PAGE_END );
		   // StartButtonPanel.setLayout(null);
		    StartButtonPanel.setLocation(250, 300);
		  StartButtonPanel.setBackground(new Color (62, 61, 60));
		    
		    //****************************
	      	//Adding panels to the screen
	      	//****************************
		    
		   
		    frame.add(StartButtonPanel, BorderLayout.PAGE_END);
		    
		    
		    //***********************
		  	//Adding ActionListener 
		  	//***********************
		    StartButton.addActionListener(new java.awt.event.ActionListener() {
		    	public void actionPerformed(java.awt.event.ActionEvent evt) {
		    		changePage(Block223MainPage.Page.login);
		    		StartButton.setVisible(false);
		    	}
		    });
		  
			
		}
		
		//Making a bouncing ball : 
		Color color = Color.red;
	    int dia = 30;
	    long delay = 40;
	    private int x = 1;
	    private int y = 1;
	    private int dx = 3;
	    private int dy = 2;
	    
	    public void run() {
	        while(isVisible()) {
	            try {
	                Thread.sleep(delay);
	            } catch(InterruptedException e) {
	                System.out.println("interrupted");
	            }
	            move();
	            repaint();
	        }
	    }
	     
	    public void move() {
	        if(x + dx < 0 || x + dia + dx > getWidth()) {
	            dx *= -1;
	           
	        }
	        if(y + dy < 0 || y + dia + dy > getHeight()) {
	            dy *= -1;
	        }
	        x += dx;
	        y += dy;
	    }
	    
	    private void start() {
	        while(!isVisible()) {
	            try {
	                Thread.sleep(25);
	            } catch(InterruptedException e) {
	                System.exit(1);
	            }
	        }
	        Thread thread = new Thread();
	        thread.setPriority(Thread.NORM_PRIORITY);
	        thread.start();
	    }
	    
	    
		@Override
		protected void paintComponent(Graphics g) {
		    super.paintComponent(g); //ALWAYS call this method first!
		    //g.drawRect(60, 100, 50, 50); //Draws square
		    g.setColor(new Color(179, 141, 151));
		    g.fillRect(60, 100, 50, 50); //Fills a square
		    g.setColor(new Color(0,0,0));
		    
		    //g.drawRect(160, 100, 50, 50); //Draws square
		    g.setColor(new Color(213, 172, 169));
		    g.fillRect(160, 100, 50, 50); //Fills a square
		    
		   // g.drawRect(260, 100, 50, 50); //Draws square
		    g.setColor(new Color(235, 207, 178));
		    g.fillRect(260, 100, 50, 50); //Fills a square
		    
		    //g.drawRect(360, 100, 50, 50); //Draws square
		    g.setColor(new Color(179, 141, 151));
		    g.fillRect(360, 100, 50, 50); //Fills a square
		    
		   // g.drawRect(460, 100, 50, 50); //Draws square
		    g.setColor(new Color(227, 228, 219));
		    g.fillRect(460, 100, 50, 50); //Fills a square
		    
		    //g.drawRect(60, 200, 50, 50); //Draws square
		    g.setColor(new Color(235, 207, 178));
		    g.fillRect(60, 200, 50, 50); //Fills a square
		    
		    //g.drawRect(160, 200, 50, 50); //Draws square
		    g.setColor(new Color(213, 172, 169));
		    g.fillRect(160, 200, 50, 50); //Fills a square
		    
		    //g.drawRect(260, 200, 50, 50); //Draws square
		    g.setColor(new Color(227, 228, 219));
		    g.fillRect(260, 200, 50, 50); //Fills a square
		    
		    //g.drawRect(360, 200, 50, 50); //Draws square
		    g.setColor(new Color(213, 172, 169));
		    g.fillRect(360, 200, 50, 50); //Fills a square
		    
		    //g.drawRect(460, 200, 50, 50); //Draws square
		    g.setColor(new Color(179, 141, 151));
		    g.fillRect(460, 200, 50, 50); //Fills a square
		    
		    //Paddle : 
		    g.setColor(new Color(222, 195, 190));
		    g.fillRect(210, 350, 150, 20); //Fills a square
		    
		    //Ball : 
		    g.setColor(new Color(213, 172, 169));
		    g.setColor(new Color(185, 49, 79));
		    ball.draw(g);
		}
		
		
////////////////////////////////////inner listener class ActionListener
class TimerAction implements ActionListener {
//================================================== actionPerformed
/** ActionListener of the timer.  Each time this is called,
*  the ball's position is updated, creating the appearance of
*  movement.
*@param e This ActionEvent parameter is unused.
*/
public void actionPerformed(ActionEvent e) {
ball.setBounds(getWidth(), getHeight());
ball.move();  // Move the ball.
repaint();      // Repaint indirectly calls paintComponent.
}
}
		
}


