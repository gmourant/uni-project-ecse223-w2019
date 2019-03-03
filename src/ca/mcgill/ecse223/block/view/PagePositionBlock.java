package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;

public class PagePositionBlock extends ContentPage {
	
	enum EditorMode {
    	ADD, CUT, PASTE, REMOVE
    }
	
	static EditorMode editorMode = EditorMode.ADD;
	
	public PagePositionBlock(Block223MainPage parent) {
		super(parent);
		initializeEditor();
	    
	    EditorMode editorMode = EditorMode.ADD;
	    
	}

    public void initializeEditor() {
                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new EditorPanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
    }
    
    class EditorPanel extends JPanel {
    	
    	static JComboBox<Integer> levelSelector;
    	
    	static JComboBox<Integer> blockList;
    	
    	EditorPanel() {
        	
            setLayout(new BorderLayout());
            add(new EditorGrid(), BorderLayout.CENTER);
            
            // Set up the tool radio buttons.
            
            ButtonGroup toolbarGroup = new ButtonGroup();
	            JRadioButton addButton = new JRadioButton("Add", true);
	            addButton.setActionCommand("ADD");
	            JRadioButton removeButton = new JRadioButton("Remove");
	            removeButton.setActionCommand("REMOVE");
	            JRadioButton moveButton = new JRadioButton("Move");
	            moveButton.setActionCommand("MOVE");
	            toolbarGroup.add(addButton);
	            toolbarGroup.add(removeButton);
	            toolbarGroup.add(moveButton);
	        
            // ComboBox block type selector.
	        
            List<TOBlock> blocks = null;
	        try {
	        	blocks = Block223Controller.getBlocksOfCurrentDesignableGame();
	        } catch(InvalidInputException e) {
	        	
	        }
	        blockList = new JComboBox<Integer>();
	        for (TOBlock block : blocks) {
	        	blockList.addItem(block.getId());
	        }
	        
	        // ComboBox level selector.
	        
	        levelSelector = new JComboBox<Integer>();
	        for (int i = 0; i < 99; i++) {
	        	levelSelector.addItem(i);
	        }
	        

	        // Add elements to the toolbar.
	        
	        JPanel toolbar = new JPanel();
	        toolbar.setLayout(new GridLayout(5,1));
	        toolbar.add(levelSelector);
	        toolbar.add(blockList);
	        toolbar.add(addButton);
	        toolbar.add(removeButton);
	        toolbar.add(moveButton);
            add(toolbar, BorderLayout.EAST);
        }
  

	    class EditorGrid extends JPanel {
	
	    	EditorGrid() {
	        	
	            setLayout(new GridBagLayout());
	
	            GridBagConstraints gbc = new GridBagConstraints();
	            for (int row = 0; row < 8; row++) {
	                for (int col = 0; col < 8; col++) {
	                    gbc.gridx = col;
	                    gbc.gridy = row;
	
	                    CellPane cellPane = new CellPane((col+1), (row+1));
	                    Border border = null;
	                    if (row < 7) {
	                        if (col < 7) {
	                            border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
	                        } else {
	                            border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
	                        }
	                    } else {
	                        if (col < 7) {
	                            border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
	                        } else {
	                            border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
	                        }
	                    }
	                    cellPane.setBorder(border);
	                    add(cellPane, gbc);
	                }
	            }
	        }
	    }
	
	    class CellPane extends JPanel {
	
	        private Color defaultBackground;
	        private boolean empty = true;
	        
	        int x;
	        int y;
	
	        public CellPane(int xpos, int ypos) {
	        	
	        	x = xpos;
	        	y = ypos;
	        	
	            addMouseListener(new MouseAdapter() {
	                @Override
					                public void mouseEntered(MouseEvent e) {
					                    defaultBackground = getBackground();
					                    if (empty)
					                    	if (PagePositionBlock.editorMode == PagePositionBlock.EditorMode.PASTE) {
					                    		setBackground(Color.GRAY);
					                		} else {
					                    		setBackground(Color.LIGHT_GRAY);
					                		}
					                }
					
					                @Override
					                public void mouseExited(MouseEvent e) {
					                	if (empty)
					                		setBackground(defaultBackground);
					                }
					                
					                @Override
					                public void mousePressed(MouseEvent e) {
					                    switch (PagePositionBlock.editorMode) {
					                    case ADD: {
					                    	try {
					                    		Block223Controller.positionBlock( ((TOBlock)blockList.getSelectedItem()).getId(),
					                    			(int) levelSelector.getSelectedItem(),
					                    			((CellPane)e.getComponent()).getX(),
					                    			((CellPane)e.getComponent()).getX() );
							                	setBackground(new Color( 
							                			((TOBlock)blockList.getSelectedItem()).getRed(),
							                			((TOBlock)blockList.getSelectedItem()).getGreen(), 
							                			((TOBlock)blockList.getSelectedItem()).getBlue()
						                			)
					                			);
							                    empty = false;
					                    	
					                    	} catch (InvalidInputException ex) {
					                    		System.out.println(ex.getMessage());
					                    	}
					                    break;
					                    }
										case CUT:
											break;
										case PASTE:
											break;
										case REMOVE:
											break;
										default:
											break;
					                    }
					                    repaint();
					                }
					            });
					        }
					
					        @Override
					        public Dimension getPreferredSize() {
					            return new Dimension(50, 50);
					        }
					    }
				    }
				    
				    class RadioListener implements ActionListener {
				    	public void actionPerformed(ActionEvent e) {
				    		switch (e.getActionCommand()) {
				    			case "ADD": PagePositionBlock.editorMode = PagePositionBlock.EditorMode.ADD;
				    			case "REMOVE": PagePositionBlock.editorMode = PagePositionBlock.EditorMode.REMOVE;
				    			case "MOVE": PagePositionBlock.editorMode = PagePositionBlock.EditorMode.CUT;
				    			default: break;
				    		}
			    	}
}
