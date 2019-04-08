package ca.mcgill.ecse223.block.view;

import java.awt.*;


public class BlockAnimation {

	///////////////////////////////////////////////////////////////// BallModel
	
	    final static int SIDE = 30;
	    
	    //... Instance variables
	    private int m_x; // x and y coordinates upper left
	    private int m_y;
	    
	    // Pixels to move each time animateBlock() is called.
	    private double m_velocityY;
	    
	    private int m_rightBound;  // Maximum permissible x, y values.
	    private int m_bottomBound;
	    
	    //======================================================== constructor
	    public BlockAnimation(int x, int y, double velocityY) {
	        m_x = x;
	        m_y = y;
	        m_velocityY = velocityY;
	    }
	    
	    //======================================================== setBounds
	    public void setBounds(int width, int height) {
	        m_rightBound  = width  - SIDE;
	        m_bottomBound = height - 2*SIDE;
	    }
	    
	    //============================================================== move
	    public void animateBlock() {
	        //... Move the block at the given velocity.
	        m_y += m_velocityY;        
	        
	        if (m_y < 0) {                 // if we're at top
	            m_y       = 0;
	            m_velocityY = -m_velocityY;
	            
	        } else if (m_y > m_bottomBound) { // if we're at bottom
	            m_y       =  m_bottomBound;
	            m_velocityY = -m_velocityY;
	        }
	    }
	    
	    //============================================================== draw
	    public void draw(Graphics g) {
	        g.fillRect(m_x, m_y, SIDE, SIDE);
	    }
	    
	    //============================================= getDiameter, getX, getY
	    public int  getSide() { return SIDE;}
	    public int  getX()        { return m_x;}
	    public int  getY()        { return m_y;}
	    
	    //======================================================== setPosition
	    public void setPosition(int x, int y) {
	        m_x = x;
	        m_y = y;
	    }
	
}//End of BlockAnimation class
