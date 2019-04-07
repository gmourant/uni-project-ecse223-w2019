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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGridCell;

public class PagePositionBlock extends ContentPage {

	private static final String Regex = "\\d+";
	private static final Pattern pattern = Pattern.compile(Regex);

	private String error = "";

	public PagePositionBlock(Block223MainPage parent) {
		super(parent);

		setLayout(new GridLayout(7, 1));

		// Populate the ID combobox.
		List<TOBlock> blocks = new ArrayList<TOBlock>();
		try {
			blocks = Block223Controller.getBlocksOfCurrentDesignableGame();
		} catch (InvalidInputException e) {
			error = e.getMessage();
			new ViewError(error, true, parent);
		}

		// Header
		add(createHeader("Position a Block"));

		// Rectangle changes color with slider
		JPanel colorPatch;
		colorPatch = new JPanel();
		JPanel gridbagPanel = new JPanel();
		gridbagPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		// colorPatch.setBounds(230,40,50,50);
		gridbagPanel.setPreferredSize(new Dimension(20, 20));
		gridbagPanel.setLocation(230, 40);
		add(gridbagPanel);
		colorPatch.setPreferredSize(new Dimension(40, 37));
		gridbagPanel.add(colorPatch);
		gridbagPanel.setBackground(this.getBackground());
		colorPatch.setBackground(new Color(blocks.get(0).getRed(), blocks.get(0).getGreen(), blocks.get(0).getBlue()));
		Color borderColorBlock = new Color(0, 0, 0);
		Border blockBorder = BorderFactory.createLineBorder(borderColorBlock, 1);
		colorPatch.setBorder(blockBorder);

		// ID panel
		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		idPanel.setBorder(
				BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(0, 0, 0, 0)));
		JLabel idLabel = new JLabel("ID : ");
		idPanel.add(idLabel);
		JComboBox<Integer> idComboBox = new JComboBox<Integer>();
		idComboBox.setPreferredSize(new Dimension(200, 30));
		// Color aqua = new Color(224, 249, 246);
		// Color greenForest = new Color(50,205,50);
		Color borderColor = new Color(207, 243, 238);
		for (TOBlock block : blocks) {
			idComboBox.addItem(block.getId());
		}
		// Set visuals and add ID panel.
		Border border = BorderFactory.createLineBorder(borderColor, 3);
		idComboBox.setBorder(border);
		idPanel.add(idComboBox);
		idPanel.setBackground(this.getBackground());
		add(idPanel);

		// Level combobox
		JPanel levelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		levelPanel.setBorder(
				BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(0, 0, 0, 0)));
		JLabel levelLabel = new JLabel("Level : ");
		levelPanel.add(levelLabel);
		JComboBox<Integer> levelSelector = new JComboBox<Integer>();
		levelSelector.setPreferredSize(new Dimension(200, 30));
		levelSelector.setBorder(border);
		// Populate combobox
		for (Integer i = 1; i < 100; i++) {
			levelSelector.addItem(i);
		}
		levelPanel.add(levelSelector);
		levelPanel.setBackground(this.getBackground());
		add(levelPanel);

		// Coordinates panel
		JPanel coordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		coordPanel.setBorder(
				BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(0, 0, 0, 0)));
		JLabel coordLabel = new JLabel("X,Y : ");
		coordPanel.add(coordLabel);
		JTextField coordTextField = new JTextField();
		coordTextField.setPreferredSize(new Dimension(200, 30));
		coordTextField.setBorder(border);
		coordPanel.add(coordTextField);
		coordPanel.setBackground(this.getBackground());
		add(coordPanel);

		// View button
		JButton viewButton = createButton("Level view");
		viewButton.setPreferredSize(new Dimension(200, 20));
		add(viewButton);

		// Button Panels
		JPanel exitButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		exitButtons.setBorder(
				BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(1, 0, 0, 2)));
		exitButtons.setBackground(this.getBackground());
		JButton addButton = createButton("Position Block");
		JButton cancelButton = createButton("Cancel");
		exitButtons.add(addButton);
		exitButtons.add(cancelButton);
		add(exitButtons);

		// addButton and CancelButton listeners
		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				// Parse the coordinate textField.
				String coord = coordTextField.getText();
				Integer x = 0;
				Integer y = 0;
				Matcher matcher = null;

				try {
					matcher = pattern.matcher(coord);
					matcher.find();
					x = Integer.parseInt(matcher.group());
					matcher.find();
					y = Integer.parseInt(matcher.group());
				} catch (NumberFormatException e) {
					error = "The coordinate numeric values must have a valid format (i.e. 12,34).";
					new ViewError(error, false, parent);
				} catch (IllegalStateException e) {
					error = "Could not match coordinates.";
					new ViewError(error, false, parent);
				}

				// Call the controller.
				try {
					Block223Controller.positionBlock((Integer) idComboBox.getSelectedItem(),
							(int) levelSelector.getSelectedItem(), x, y);
				} catch (InvalidInputException e) {
					error = e.getMessage();
					if (error.equals("A game must be selected to position a block.")
							|| error.equals("Admin privileges are required to position a block.")
							|| error.equals("Only the admin who created the game can position a block.")) {
						new ViewError(error, true, parent);
					}
					new ViewError(error, false, parent);
				} catch (NumberFormatException e) {
					error = "The block ID must be a valid number.";
					new ViewError(error, false, parent);
				} catch (NullPointerException e) {
					error = "No block selected.";
					new ViewError(error, false, parent);
				}

				// update visuals
				// refreshData();
			}

		});

		// viewButton listener

		viewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				// Get the block assignments of the current level.

				List<TOGridCell> assignments = new ArrayList<TOGridCell>();
				try {
					assignments = Block223Controller
							.getBlocksAtLevelOfCurrentDesignableGame((Integer) levelSelector.getSelectedItem());
					new LevelView(assignments, false, parent);
				} catch (InvalidInputException e) {
					error = e.getMessage();
					new ViewError(error, false, parent);
				}

			}
		});

		// Action listener idComboBox
		// @author http://math.hws.edu/eck/cs124/javanotes4/source/RGBColorChooser.java
		ActionListener actionListenerId = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int r, g, b;
				Integer id = (Integer) idComboBox.getSelectedItem();
				TOBlock block = Block223Controller.findTOBlock(id);
				if (block != null) {
					r = block.getRed();
					g = block.getGreen();
					b = block.getBlue();
					colorPatch.setBackground(new Color(r, g, b));
				}
			}
		};

		idComboBox.addActionListener(actionListenerId);

		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancel();
			}
		});

		// Side menu editing
		// JList sideMenu = getSideMenuList();
		// add(sideMenu);

	}

	private TOGridCell coordInList(int x, int y, List<TOGridCell> list) {
		for (TOGridCell cell : list) {
			if (x == cell.getGridHorizontalPosition() && y == cell.getGridVerticalPosition()) {
				return cell;
			}
		}
		return null;
	}

	/**
	 * This class creates a new level grid view to visualize the designed levels.
	 * 
	 * @author Georges Mourant modified by @author Mathieu Bissonnette
	 * 
	 */

	public class LevelView extends JFrame {

		final Color HEADER_BACKGROUND = new Color(255 + (255 - 255) * 5 / 8, 204 + (255 - 204) * 5 / 8,
				204 + (255 - 204) * 5 / 8);

		private final Block223MainPage framework;
		private final boolean errorRedirect;
		private final JPanel windowHolder;
		private JPanel topMenu;
		private JButton exit;

		public LevelView(List<TOGridCell> assignments, boolean errorRedirect, Block223MainPage parent) {
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
			JPanel grid = new JPanel(new GridLayout(15, 15));
			for (int row = 0; row < 15; row++) {
				for (int col = 0; col < 15; col++) {

					CellPane cellPane = new CellPane();
					Border border = null;
					if (row < 14) {
						if (col < 14) {
							border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
						} else {
							border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
						}
					} else {
						if (col < 14) {
							border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
						} else {
							border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
						}
					}
					TOGridCell cell = coordInList(col + 1, row + 1, assignments);
					if (cell != null)
						cellPane.setBackground(new Color(cell.getRed(), cell.getGreen(), cell.getBlue()));
					cellPane.setBorder(border);
					grid.add(cellPane);
				}
			}
			windowHolder.add(grid, BorderLayout.CENTER);
			add(windowHolder);
		}

		class CellPane extends JPanel {

			private Color defaultBackground;

		}

		/**
		 * This method initalises all the information for the top menu.
		 * 
		 * @author Georges Mourant
		 */
		private void setupTopMenu() {
			topMenu = new JPanel(new GridLayout(1, 2));
			topMenu.setBorder(BorderFactory.createCompoundBorder(topMenu.getBorder(),
					BorderFactory.createEmptyBorder(5, 10, 5, 5)));
			topMenu.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() / 8));
			topMenu.setBackground(HEADER_BACKGROUND);

			JLabel title = new JLabel("Level preview"); // empty by default
			title.setFont(new Font(Block223MainPage.getUIFont().getFamily(), Font.BOLD,
					Block223MainPage.getUIFont().getSize() + TITLE_SIZE_INCREASE));
			topMenu.add(title);

			JPanel exitMin = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			exitMin.setBackground(topMenu.getBackground()); // match to background
			exit = createButton("X");
			exit.setBackground(exitMin.getBackground()); // match to background
			exitMin.add(exit);
			topMenu.add(exitMin);

			windowHolder.add(topMenu, BorderLayout.NORTH);

			JPanel holder = new JPanel(new BorderLayout());
			holder.setBorder(BorderFactory.createCompoundBorder(holder.getBorder(),
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			holder.setBackground(Color.WHITE);
			holder.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() * 3 / 4));

			windowHolder.add(holder, BorderLayout.CENTER);

			exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (errorRedirect)
						framework.changePage(Block223MainPage.Page.adminMenu);
					dispose(); // quit program
				}
			});
		}
	}

}
