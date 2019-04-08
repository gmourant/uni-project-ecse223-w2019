package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;

/**
 * The page for deleting a block.
 * 
 * @author Imane Chafi
 */
public class PageDeleteBlock extends ContentPage {

	// data elements
	private String error = null;

	public PageDeleteBlock(Block223MainPage framework) {
		super(framework);
		setLayout(new GridLayout(6, 4));

		// Delete a block Layout
		add(createHeader("Delete a Block"));
		JLabel choosingID = new JLabel("Choose the ID of the block to be deleted:");
		choosingID.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		add(choosingID);
		JPanel b = new JPanel();
		b.setBackground(this.getBackground());
		JComboBox<Integer> ids = new JComboBox<Integer>();
		ids.setPreferredSize(new Dimension(150, 30));
		ids.setAlignmentX(Component.CENTER_ALIGNMENT);
		ids.setBackground(Block223MainPage.getButtonBackground());
		ids.setForeground(Color.DARK_GRAY);
		// ids.addItem();
		b.add(ids);
		add(b);

		// List adding blockIDs
		java.util.List<TOBlock> blocks;

		try {
			blocks = Block223Controller.getBlocksOfCurrentDesignableGame();
		} catch (InvalidInputException e) {
			displayError(e.getMessage(), true);
			// stop now to prevent future errors based on this exception
			return;
		}

		// View Layout
		JPanel colorPatch;
		colorPatch = new JPanel();
		JLabel viewLabel = new JLabel("          View:");
		viewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		add(viewLabel);
		JPanel gridbagPanel = new JPanel();
		gridbagPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		gridbagPanel.setPreferredSize(new Dimension(10, 10));
		gridbagPanel.setLocation(130, 40);
        try {
        	colorPatch.setBackground(new Color(blocks.get(0).getRed(), blocks.get(0).getGreen(), blocks.get(0).getBlue()));
        } catch (Exception e) {
        	colorPatch.setBackground(Color.black);
        }
		colorPatch.setPreferredSize(new Dimension(57, 60));
		gridbagPanel.add(colorPatch);
		add(gridbagPanel);
		gridbagPanel.setBackground(this.getBackground());
		Color borderColorBlock = new Color(0, 0, 0);
		Border blockBorder = BorderFactory.createLineBorder(borderColorBlock, 1);
		colorPatch.setBorder(blockBorder);

		JPanel exitButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		exitButtons.setBorder(
				BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(1, 0, 0, 0)));
		exitButtons.setBackground(this.getBackground());
		JButton delete = createButton("Delete");
		JButton cancel = createButton("Cancel");
		exitButtons.add(delete);
		exitButtons.add(cancel);
		add(exitButtons);

		// Side Menu
		JList list = getSideMenuList();

		list.setVisible(true);

		// deleteBlock and Cancel listeners
		delete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// call the controller
				try {
                                    if(ids.getSelectedItem() == null) return;
					Block223Controller.deleteBlock((int) ids.getSelectedItem());
				} catch (InvalidInputException e) {
					displayError(e.getMessage(), false);
					return;
				}
				// update visuals
				// refreshData();
			}

		});

		// add this list
		for (TOBlock block : blocks) {
			ids.addItem(block.getId());
		}

		// add dropdown to panel
		b.add(ids);

		// View ActionListener
		ids.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// call the controller
				TOBlock block = Block223Controller.findTOBlock((int) ids.getSelectedItem());
				int R = block.getRed();
				int G = block.getGreen();
				int B = block.getBlue();
				colorPatch.setBackground(new Color(R, G, B));

			}
		});

		cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				cancel();
			}
		});

	}
}
