package ca.mcgill.ecse223.block.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;

/**
 * This page defines game settings for a game within Block223.
 * 
 * @author Kelly Ma
 */
public class PageDefineGame extends ContentPage {

	private static final long serialVersionUID = 5362736975311160954L;

	public PageDefineGame(Block223MainPage parent) {

		// Inherit features from ContentPage
		super(parent);
		setLayout(new GridLayout(10, 1));
		add(createHeader("Define Game Settings"));

		// Get current game
		TOGame currentGame = null;
		try {
			currentGame = Block223Controller.getCurrentDesignableGame();
		} catch (InvalidInputException e) {
			String error = e.getMessage();
			new ViewError(error, true, parent);
		}

		// nrLevels Slider
		Slider nrLevelsSlider = addSlider("Number of Levels", 1, 99, currentGame.getNrLevels());
		add(nrLevelsSlider.panel);

		// nrBlocksPerLevel Slider
		Slider nrBlocksPerLevelSlider = addSlider("Blocks per Level", 1, 50, currentGame.getNrBlocksPerLevel());
		add(nrBlocksPerLevelSlider.panel);

		// minBallSpeedX Slider
		Slider minBallSpeedXSlider = addSlider("Minimum Ball Speed (X)", 0, 50, currentGame.getMinBallSpeedX());
		add(minBallSpeedXSlider.panel);

		// minBallSpeedY Slider
		Slider minBallSpeedYSlider = addSlider("Minimum Ball Speed (Y)", 0, 50, currentGame.getMinBallSpeedY());
		add(minBallSpeedYSlider.panel);

		// ballSpeedIncreaseFactor Slider
		Slider ballSpeedIncreaseFactorSlider = addSlider("Ball Speed Increase Factor", 0.01, 1.0,
				currentGame.getBallSpeedIncreaseFactor());
		add(ballSpeedIncreaseFactorSlider.panel);

		// maxPaddleLength Slider
		Slider maxPaddleLengthSlider = addSlider("Maximum Paddle Length", 1, 390, currentGame.getMaxPaddleLength());
		add(maxPaddleLengthSlider.panel);

		// minPaddleLength Slider
		Slider minPaddleLengthSlider = addSlider("Minimum Paddle Length", 1, 390, currentGame.getMinPaddleLength());
		add(minPaddleLengthSlider.panel);

		// ChangeListener that displays the selected parameter
		// This ChangeListener indicates the current selected value to the user
		ChangeListener slide = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (source == nrLevelsSlider.slider) {
					int nrLevels = nrLevelsSlider.getIValue();
					nrLevelsSlider.setISlider(nrLevels);
				} else if (source == nrBlocksPerLevelSlider.slider) {
					int nrBlocksPerLevel = nrBlocksPerLevelSlider.getIValue();
					nrBlocksPerLevelSlider.setISlider(nrBlocksPerLevel);
				} else if (source == nrBlocksPerLevelSlider.slider) {
					int nrBlocksPerLevel = nrBlocksPerLevelSlider.getIValue();
					nrBlocksPerLevelSlider.setISlider(nrBlocksPerLevel);
				} else if (source == minBallSpeedXSlider.slider) {
					int minBallSpeedX = minBallSpeedXSlider.getIValue();
					minBallSpeedXSlider.setISlider(minBallSpeedX);
				} else if (source == minBallSpeedYSlider.slider) {
					int minBallSpeedY = minBallSpeedYSlider.getIValue();
					minBallSpeedYSlider.setISlider(minBallSpeedY);
				} else if (source == ballSpeedIncreaseFactorSlider.slider) {
					DecimalFormat numberFormat = new DecimalFormat("#.00");
					double ballSpeedIncreaseFactor = ballSpeedIncreaseFactorSlider.getDValue();
					ballSpeedIncreaseFactorSlider
							.setDSlider(Double.parseDouble(numberFormat.format(ballSpeedIncreaseFactor)));
				} else if (source == maxPaddleLengthSlider.slider) {
					int maxPaddleLength = maxPaddleLengthSlider.getIValue();
					maxPaddleLengthSlider.setISlider(maxPaddleLength);
				} else if (source == minPaddleLengthSlider.slider) {
					int minPaddleLength = minPaddleLengthSlider.getIValue();
					minPaddleLengthSlider.setISlider(minPaddleLength);
				}
			}
		};

		// Add ChangeListeners to sliders to help user select parameters
		nrLevelsSlider.slider.addChangeListener(slide);
		nrBlocksPerLevelSlider.slider.addChangeListener(slide);
		minBallSpeedXSlider.slider.addChangeListener(slide);
		minBallSpeedYSlider.slider.addChangeListener(slide);
		ballSpeedIncreaseFactorSlider.slider.addChangeListener(slide);
		maxPaddleLengthSlider.slider.addChangeListener(slide);
		minPaddleLengthSlider.slider.addChangeListener(slide);

		// Save and Cancel buttons
		JPanel exitButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		exitButtons.setBackground(this.getBackground());
		JButton save = createButton("Save");
		JButton cancel = createButton("Cancel");
		exitButtons.add(save);
		exitButtons.add(cancel);
		add(exitButtons);

		// Listener for Save button
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				try { // Retrieve user's slider parameters
					Block223Controller.setGameDetails(nrLevelsSlider.getIValue(), nrBlocksPerLevelSlider.getIValue(),
							minBallSpeedXSlider.getIValue(), minBallSpeedYSlider.getIValue(),
							ballSpeedIncreaseFactorSlider.getDValue(), maxPaddleLengthSlider.getIValue(),
							minPaddleLengthSlider.getIValue());
				} catch (InvalidInputException e) {
					displayError(e.getMessage(), false);
					return;
				}
			}
		});

		// Listener for Cancel button
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel(); // Returns to the admin menu
			}
		});

	}

}