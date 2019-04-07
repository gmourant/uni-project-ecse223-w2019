package ca.mcgill.ecse223.block.view;

import static ca.mcgill.ecse223.block.view.Block223MainPage.TITLE_SIZE_INCREASE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import ca.mcgill.ecse223.block.controller.TOGridCell;

/**
 *  This class creates a new level grid view to visualize the designed levels.
 *  
 *  @author Georges Mourant modified by @author Mathieu Bissonnette
 *  
 */

public class LevelView extends JFrame{

    final Color HEADER_BACKGROUND = 
            new Color(255 + (255 - 255)*5/8, 204 + (255 - 204)*5/8, 204 + (255 - 204)*5/8);
    
    private final Block223MainPage framework;
    private final boolean errorRedirect;
    private final JPanel windowHolder;
    private JPanel topMenu;
    private JButton exit;
    
    public LevelView(List<TOGridCell> assignments, boolean errorRedirect,  Block223MainPage parent){
        framework = parent;
        this.errorRedirect = errorRedirect;
        this.setSize(600, 650); // Specifies the size should adjust to the needs for space
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Specifies what the X to close does
        this.setLocationRelativeTo(null); // Places in the center of the screen
        this.setResizable(false); // stops user from resizing the dialog box
        this.setUndecorated(true);
        this.setVisible(true);
        windowHolder = new JPanel(new BorderLayout());
        windowHolder.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        setupTopMenu();
        JPanel grid = new JPanel(new GridLayout(16,16));
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {

                CellPane cellPane = new CellPane();
                Border border = null;
                if (row == 0 && col == 0) {
                	cellPane.add(new JLabel("Y \\ X"));
                } else if (row == 0) {
                	cellPane.add(new JLabel(""+col));
            	} else if (col == 0) {
                	cellPane.add(new JLabel(""+row));
                } else if (row < 15) {
                    if (col < 15) {
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if (col < 15) {
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
            	TOGridCell cell = coordInList(col, row, assignments);
            	if (cell != null)
            		cellPane.setBackground(new Color(cell.getRed(), cell.getGreen(), cell.getBlue()));
                cellPane.setBorder(border);
                grid.add(cellPane);
            }
        }
        windowHolder.add(grid,BorderLayout.CENTER);
        add(windowHolder);
    }

    class CellPane extends JPanel {

        private Color defaultBackground;
    
    }
    
    /**
     * This method initalises all the information for the top menu.
     * @author Georges Mourant
     */
    private void setupTopMenu() {
        topMenu = new JPanel(new GridLayout(1, 2));
        topMenu.setBorder(BorderFactory.createCompoundBorder(topMenu.getBorder(), 
                BorderFactory.createEmptyBorder(5, 10, 5, 5)));
        topMenu.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()/8));
        topMenu.setBackground(HEADER_BACKGROUND);

        JLabel title = new JLabel("Level preview"); // empty by default
        title.setFont(new Font(Block223MainPage.getUIFont().getFamily(), 
                    Font.BOLD, Block223MainPage.getUIFont().getSize() + TITLE_SIZE_INCREASE));
        topMenu.add(title);

        JPanel exitMin = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitMin.setBackground(topMenu.getBackground()); // match to background
        exit = new JButton("X");
        exit.setBackground(exitMin.getBackground()); // match to background
        exitMin.add(exit);
        topMenu.add(exitMin);

        windowHolder.add(topMenu, BorderLayout.NORTH);
        
        JPanel holder = new JPanel(new BorderLayout());
        holder.setBorder(BorderFactory.createCompoundBorder(holder.getBorder(), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        holder.setBackground(Color.WHITE);
        holder.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()*3/4));
        
        windowHolder.add(holder, BorderLayout.CENTER);
        
        exit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(errorRedirect)
                        framework.changePage(Block223MainPage.Page.adminMenu);
                    dispose(); // quit program
                }
        });
    }
    
	private TOGridCell coordInList(int x, int y, List<TOGridCell> list) {
		for (TOGridCell cell : list) {
			if (x == cell.getGridHorizontalPosition() && y == cell.getGridVerticalPosition()) {
				return cell;
			}
		}
		return null;
	}
}