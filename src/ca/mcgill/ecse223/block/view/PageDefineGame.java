package ca.mcgill.ecse223.block.view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
        setLayout(new GridLayout(7, 1));
        add(createHeader("Define Game Settings"));

        // All sliders necessary on the page
        Slider nrLevels = new Slider("Number of Levels:", 1, 99, 30);
        Slider nrBlocksPerLevel = new Slider("Number of blocks per level:", 1, 50, 15);
        Slider minBallSpeedX = new Slider("Minimum ball speed (X):", 1, 10, 3);
        Slider minBallSpeedY = new Slider("Minimum ball speed (X):", 1, 10, 3);
        Slider ballSpeedIncreaseFactor = new Slider("Ball Speed Increase Factor:", 0.1, 1.0, 0.2);
        Slider maxPaddleLength = new Slider("Maximum paddle length:", 1, 390, 50);
        Slider minPaddleLength = new Slider("Minimum paddle length:", 1, 50, 20);

        // Add sliders
        add(nrLevels.panel);
        add(nrBlocksPerLevel.panel);
        add(minBallSpeedX.panel);
        add(minBallSpeedY.panel);
        add(ballSpeedIncreaseFactor.panel);
        add(maxPaddleLength.panel);
        add(minPaddleLength.panel);

    }

}
