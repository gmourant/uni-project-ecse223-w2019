
package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import static ca.mcgill.ecse223.block.view.Block223MainPage.currentTheme;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Allows for cusstom backgrounds including image backgrounds.
 * @author Georges Mourant
 */
public class JPanelWithBackground extends JPanel {
    public enum Background {header, general, none, transparent}
    private final Background b;
    private BufferedImage tile;
    
    public JPanelWithBackground(Background b){
        super();
        this.b = b;
    }
    public JPanelWithBackground(Background b, LayoutManager layout){
        super(layout);
        this.b = b;
    }
    public JPanelWithBackground(){
        super();
        this.b = Background.none;
    }
    public JPanelWithBackground(LayoutManager layout){
        super(layout);
        this.b = Background.none;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        tile = null;
        
        if(b == Background.header){
            if(currentTheme.headerBackground instanceof String){
                try {
                    tile = ImageIO.read(getClass().getResource((String)currentTheme.headerBackground));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                this.setBackground((Color)Block223MainPage.currentTheme.headerBackgroundFiller);
            } else if(Block223MainPage.currentTheme.background instanceof Color)
                this.setBackground((Color)Block223MainPage.currentTheme.headerBackground);
        } else if(b == Background.general) {
            if(Block223MainPage.currentTheme.background instanceof String){
                try {
                    tile = ImageIO.read(getClass().getResource((String)currentTheme.background));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                this.setBackground(Color.WHITE);
            } else if(Block223MainPage.currentTheme.background instanceof Color)
                this.setBackground((Color)Block223MainPage.currentTheme.background);
        } else if(b == Background.transparent) {
            setOpaque(false);
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));

            g2d.setColor(getBackground());
            g2d.fill(getBounds());

            g2d.dispose();
        }
        
        if(tile != null){
            Graphics2D g2d = (Graphics2D) g.create();
            int tileWidth = tile.getWidth();
            int tileHeight = tile.getHeight();
            for (int y = 0; y < getHeight(); y += tileHeight) {
                for (int x = 0; x < getWidth(); x += tileWidth) {
                    g2d.drawImage(tile, x, y, this);
                }
            }
            g2d.dispose();
        }
    }
}