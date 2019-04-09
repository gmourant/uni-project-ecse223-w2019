package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorChooserUI;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

/**
 * Page where the user will have be able to register for an account.
 * @author Sofia Dieguez
 * */
public class PageSignUp extends ContentPage{

	//UI elements
	private static Font defaultFont = new Font("Consolas", Font.PLAIN, 14);
	private static Font titleFont = new Font("Consolas", Font.PLAIN, 50);

	public static Color lightColor = Block223MainPage.getLightColor();
	public static Color mediumColor = Block223MainPage.getMediumColor();
	public static Color darkColor = Block223MainPage.getDarkColor();
	
	BlockAnimation block1 = new BlockAnimation(30,80,5);
	BlockAnimation block2 = new BlockAnimation(60,50,4);
	BlockAnimation block3 = new BlockAnimation(90,20,3);
	BlockAnimation block4 = new BlockAnimation(30,20,0);
	BlockAnimation block5 = new BlockAnimation(480,360,5);
	BlockAnimation block6 = new BlockAnimation(510,330,4);
	BlockAnimation block7 = new BlockAnimation(450,390,3);
	BlockAnimation block8 = new BlockAnimation(510,390,0);
	private int m_interval = 35; // ms b/w updates
	private Timer m_timer;//timer fires to animate one feature

	JTextField usernameTextField;
	JPasswordField playerPasswordPField;
	JPasswordField adminPasswordPField;

	//*******************
	//Constructor method
	//*******************
	public PageSignUp(Block223MainPage frame) {
		super(frame, JPanelWithBackground.Background.general);
		setLayout(new GridLayout(8,1));

		m_timer = new Timer(m_interval, new TimerAction());
		m_timer.start();//start animation by starting the timer

		//*****************
		//UI Sign Up elements
		//*****************
		//Sign up elements 
		Border border = BorderFactory.createLineBorder(Block223MainPage.getHeaderBackgroundFiller(), 3);

		//Title page panel
		JPanel titlePanel = new JPanelWithBackground(Background.transparent, new BorderLayout());
		JLabel titleLabel = new JLabel("Block 223", JLabel.CENTER);
		titleLabel.setFont(titleFont);
		titleLabel.setForeground(Block223MainPage.getForegroundForBackground());
		titlePanel.add(titleLabel, BorderLayout.CENTER);

		//Username label panel
		JPanel usernameLabelPanel = new JPanelWithBackground(Background.transparent, new BorderLayout());
		JLabel usernameLabel = new JLabel("         Username:");
		usernameLabel.setFont(defaultFont);
		usernameLabel.setForeground(Block223MainPage.getForegroundForBackground());
		usernameLabel.setHorizontalAlignment(JLabel.LEFT);
		usernameLabelPanel.add(usernameLabel, BorderLayout.WEST);

		//Username text field panel
		JPanel usernameTxtFieldPanel = new JPanelWithBackground(Background.transparent, new FlowLayout(FlowLayout.CENTER));
		usernameTextField = new JTextField();
		usernameTextField.setPreferredSize(new Dimension(253, 27));
		usernameTextField.setBorder(border);
		usernameTextField.setFont(defaultFont);
		usernameTxtFieldPanel.add(usernameTextField);

		//Password
		//Player password label panel
		JPanel playerPwordLabelPanel = new JPanelWithBackground(Background.transparent, new BorderLayout());
		JLabel playerPasswordLabel = new JLabel("         Player password:");
		playerPasswordLabel.setFont(defaultFont);
		playerPasswordLabel.setForeground(Block223MainPage.getForegroundForBackground());
		playerPasswordLabel.setHorizontalAlignment(JLabel.LEFT);
		playerPwordLabelPanel.add(playerPasswordLabel, BorderLayout.WEST);

		//Player password password field panel
		JPanel playerPwordPFieldPanel = 
				new JPanelWithBackground(Background.transparent, new FlowLayout(FlowLayout.CENTER));
		playerPasswordPField = new JPasswordField();
		playerPasswordPField.setPreferredSize(new Dimension(253, 27));
		playerPasswordPField.setBorder(border);
		playerPwordPFieldPanel.add(playerPasswordPField);

		//Admin password label panel
		JPanel adminPwordLabelPanel =
				new JPanelWithBackground(Background.transparent, new BorderLayout());
		JLabel adminPasswordLabel = new JLabel("         Admin password (optional):");
		adminPasswordLabel.setFont(defaultFont);
		adminPasswordLabel.setForeground(Block223MainPage.getForegroundForBackground());
		adminPasswordLabel.setHorizontalAlignment(JLabel.LEFT);
		adminPwordLabelPanel.add(adminPasswordLabel, BorderLayout.WEST);

		//Admin password password field panel
		JPanel adminPwordPFieldPanel =
				new JPanelWithBackground(Background.transparent, new FlowLayout(FlowLayout.CENTER));
		adminPasswordPField = new JPasswordField();
		adminPasswordPField.setPreferredSize(new Dimension(253, 27));
		adminPasswordPField.setBorder(border);
		adminPwordPFieldPanel.add(adminPasswordPField);

		//Sign up button panel
		JPanel signUpBtnPanel =
				new JPanelWithBackground(Background.transparent, new FlowLayout(FlowLayout.CENTER));
		JButton signUpButton = createButton("Sign Up");
		signUpBtnPanel.add(signUpButton);

		//****************************
		//Adding panels to the screen
		//****************************
		add(titlePanel);
		add(usernameLabelPanel);
		add(usernameTxtFieldPanel);
		add(playerPwordLabelPanel);
		add(playerPwordPFieldPanel);
		add(adminPwordLabelPanel);
		add(adminPwordPFieldPanel);
		add(signUpBtnPanel);

		//***********************
		//Adding ActionListener 
		//***********************
		signUpButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				signUpButtonActionPerformed(evt);
			}
		});

	}//End of PageSignUp constructor

	//Animating the blocks
	int side = 30;
	long delay = 40;
	private int x = 1;
	private int y = 3;
	private int dy = 2;

	public void run() {
		while(isVisible()) {
			try {
				Thread.sleep(delay);
			}catch(InterruptedException e) {
				System.out.println("Interrupted");
			}
			animateBlock();
			repaint();
		}
	}//End of run

	public void animateBlock() {
		if(y + dy < 0 || y + side + dy > getHeight()) {
			dy *= -1;
		}
		y += dy;
	}//End of animateBlock method

	private void start() {
		while(!isVisible()) {
			try {
				Thread.sleep(25);
			}catch (InterruptedException e) {
				System.exit(1);
			}
		}
		Thread thread = new Thread();
		thread.setPriority(Thread.NORM_PRIORITY);
		thread.start();

	}//End of start method

	//*******************
	//RefreshData method
	//*******************
	private void refreshDataSignUp() {

		//populate page with data
		usernameTextField.setText("");
		playerPasswordPField.setText("");
		adminPasswordPField.setText("");
	}//End of refreshDataSignUp method

	//***********************
	//ActionPerformed methods
	//***********************
	private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//Convert array of characters into a String
		String playerPassword = new String(playerPasswordPField.getPassword());
		String adminPassword = new String(adminPasswordPField.getPassword());

		//call the controller
		try {
			Block223Controller.register(usernameTextField.getText(), playerPassword, adminPassword);
		} catch(InvalidInputException e) {
			displayError(e.getMessage(), false);
			refreshDataSignUp();
		}

		changePage(Block223MainPage.Page.login);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); //ALWAYS call this method first!
		g.setColor(darkColor);
		block1.draw(g);

		g.setColor(mediumColor);
		block2.draw(g);

		g.setColor(darkColor);
		block3.draw(g);

		g.setColor(mediumColor);
		block4.draw(g);

		g.setColor(darkColor);
		block5.draw(g);

		g.setColor(mediumColor);
		block6.draw(g);

		g.setColor(mediumColor);
		block7.draw(g);

		g.setColor(mediumColor);
		block8.draw(g);
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
			block1.setBounds(getWidth(), getHeight());
			block1.animateBlock();  // animate block1.
			block2.setBounds(getWidth(), getHeight());
			block2.animateBlock();  // animate block2.
			block3.setBounds(getWidth(), getHeight());
			block3.animateBlock();  // animate block3.
			block4.setBounds(getWidth(), getHeight());
			block4.animateBlock();  // animate block4.
			block5.setBounds(getWidth(), getHeight());
			block5.animateBlock();  // animate block5.
			block6.setBounds(getWidth(), getHeight());
			block6.animateBlock();  // animate block6.
			block7.setBounds(getWidth(), getHeight());
			block7.animateBlock();  // animate block7.
			block8.setBounds(getWidth(), getHeight());
			block8.animateBlock();  // animate block8.
			repaint();  // Repaint indirectly calls paintComponent.
		}
	}

}//End of PageSignUp class
