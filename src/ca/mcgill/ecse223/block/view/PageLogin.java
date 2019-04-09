package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.application.Block223Application;
import java.awt.BorderLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorChooserUI;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import static ca.mcgill.ecse223.block.controller.Block223Controller.getUserMode;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOUserMode;
import ca.mcgill.ecse223.block.view.Block223MainPage;
import ca.mcgill.ecse223.block.view.Block223MainPage.Page;
import ca.mcgill.ecse223.block.view.PageSignUp.TimerAction;



/**
 * Page where the user will have to log in.
 *
 * @author Sofia Dieguez
 *
 */
public class PageLogin extends ContentPage {
	
	//UI elements
    private static Font defaultFont = new Font("Consolas", Font.PLAIN, 14);
    private static Font titleFont = new Font("Consolas", Font.PLAIN, 50);
    
    public static Color lightColor = Block223MainPage.getLightColor();
	public static Color mediumColor = Block223MainPage.getMediumColor();
	public static Color darkColor = Block223MainPage.getDarkColor();
    
	
	
    BlockAnimation block1 = new BlockAnimation(30,60,5);
    BlockAnimation block2 = new BlockAnimation(60,30,4);
    BlockAnimation block3 = new BlockAnimation(90,0,3);
    BlockAnimation block4 = new BlockAnimation(30,0,0);
    BlockAnimation block5 = new BlockAnimation(480,360,5);
    BlockAnimation block6 = new BlockAnimation(510,330,4);
    BlockAnimation block7 = new BlockAnimation(450,390,3);
    BlockAnimation block8 = new BlockAnimation(510,390,0);
    private int m_interval = 35; // ms b/w updates
    private Timer m_timer;//timer fires to animate one feature
    
    JTextField usernameTextField;
    JPasswordField passwordPField;
	
	//*******************
	//Constructor method
	//*******************
	public PageLogin(Block223MainPage frame) {
		super(frame, JPanelWithBackground.Background.general);

		refreshDataLogIn();

		setLayout(new GridLayout(7, 1));

		m_timer = new Timer(m_interval, new TimerAction());
		m_timer.start();//start animation by starting the timer

		//*****************
		//UI Login elements
		//*****************
		//Login elements 
		Border border = BorderFactory.createLineBorder(darkColor, 3);

		//Title page panel
		JPanel titlePanel = new JPanelWithBackground(Background.transparent, new BorderLayout());
		JLabel titleLabel = new JLabel("Block 223", JLabel.CENTER);
		//titleLabel.setForeground(new Color(227, 228, 219));
		titleLabel.setFont(titleFont);
		titleLabel.setForeground(Block223MainPage.getForegroundForBackground());
		titlePanel.add(titleLabel, BorderLayout.CENTER);

		//Username label panel
		JPanel usernameLabelPanel = new JPanelWithBackground(Background.transparent, new BorderLayout());
		JLabel usernameLabel = new JLabel("         Username:");
		usernameLabel.setFont(defaultFont);
		usernameLabel.setForeground(Block223MainPage.getForegroundForBackground());
		usernameLabel.setHorizontalAlignment(JLabel.LEFT);
		//usernameLabel.setFont(new Font("Consolas", Font.PLAIN, 17));
		usernameLabelPanel.add(usernameLabel, BorderLayout.WEST);

		//Username text field panel
		JPanel usernameTxtFieldPanel = new JPanelWithBackground(Background.transparent, new FlowLayout(FlowLayout.CENTER));
		usernameTextField = new JTextField();
		usernameTextField.setPreferredSize(new Dimension(253, 27));
		usernameTextField.setBorder(border);
		usernameTextField.setFont(defaultFont);
		usernameTxtFieldPanel.add(usernameTextField);

		//Password label panel
		JPanel passwordLabelPanel = new JPanelWithBackground(Background.transparent, new BorderLayout());
		JLabel passwordLabel = new JLabel("         Password:");
		passwordLabel.setFont(defaultFont);
		passwordLabel.setForeground(Block223MainPage.getForegroundForBackground());
		passwordLabel.setHorizontalAlignment(JLabel.LEFT);
		//passwordLabel.setFont(new Font("Consolas", Font.PLAIN, 17));
		passwordLabelPanel.add(passwordLabel, BorderLayout.WEST);

		//Password password field panel
		JPanel passwordPFieldPanel = new JPanelWithBackground(Background.transparent, new FlowLayout(FlowLayout.CENTER));
		passwordPField = new JPasswordField();
		passwordPField.setPreferredSize(new Dimension(253, 27));
		passwordPField.setBorder(border);
		passwordPFieldPanel.add(passwordPField);

		//Buttons
		//Log in button panel
		JPanel loginBtnPanel = new JPanelWithBackground(Background.transparent, new FlowLayout(FlowLayout.CENTER));
		JButton logInButton = createButton("Log In");
		logInButton.setFont(new Font("Consolas", Font.PLAIN, 20));
		loginBtnPanel.add(logInButton);

		//Sign up button panel
		JPanel signUpBtnPanel = new JPanelWithBackground(Background.transparent, new FlowLayout(FlowLayout.CENTER));
		JButton signUpButton = new JButton("Sign Up!");
		signUpButton.setForeground(Block223MainPage.getMediumColor());
		signUpButton.setFont(new Font("Consolas", Font.PLAIN, 20));
		signUpButton.setOpaque(false);
		//signUpButton.setBackground(Block223MainPage.getButtonBackground());
		signUpButton.setContentAreaFilled(false);
		signUpButton.setBorderPainted(false);
		//signUpButton.setForeground(Color.BLUE);
		JLabel recommendSignUp = new JLabel("Don't have an account?");
		recommendSignUp.setFont(new Font("Consolas", Font.PLAIN, 18));
		recommendSignUp.setForeground(Block223MainPage.getLightColor());
		Border emptyBorder = BorderFactory.createEmptyBorder();
		signUpButton.setBorder(emptyBorder);
		signUpBtnPanel.add(recommendSignUp);
		signUpBtnPanel.add(signUpButton);

		//****************************
		//Adding panels to the screen
		//****************************
		add(titlePanel);
		add(usernameLabelPanel);
		add(usernameTxtFieldPanel);
		add(passwordLabelPanel);
		add(passwordPFieldPanel);
		add(loginBtnPanel);
		add(signUpBtnPanel);

		//***********************
		//Adding ActionListeners 
		//***********************
		logInButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logInButtonActionPerformed(evt);
			}
		});//End of logInButton action listener

		signUpButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				signUpButtonActionPerformed(evt);
			}
		});//End of signUpButton action listener

	}//End of PageLogin constructor
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
	private void refreshDataLogIn() {
		//populate page with data
		Block223Application.resetBlock223();
		//pack();
	}//End of refreshDataLogin

	//***********************
	//ActionPerformed methods
	//***********************
	private void logInButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//clear error message

		//Convert array of characters into a String
		String password = new String(passwordPField.getPassword());

		//call the controller
		try {
			Block223Controller.login(usernameTextField.getText(), password);
		} catch (InvalidInputException e) {
			displayError(e.getMessage(), Block223MainPage.Page.login);
			return;

		}

		switch (getUserMode().getMode()) {
		case Design:
			changePage(Block223MainPage.Page.logout);
			break;
		case Play:
			changePage(Block223MainPage.Page.chooseGame);
			break;
		default:
		}
	}//End of logInButtonActionPerformed method

	private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {
		changePage(Block223MainPage.Page.signUp);

	}//End of signUpActionPerformed method

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); //ALWAYS call this method first!
		g.setColor(darkColor);
		g.fillRect(30,80,30,30);
		//block1.draw(g);

		g.setColor(mediumColor);
		g.fillRect(60,50,30,30);
		//block2.draw(g);

		g.setColor(darkColor);
		g.fillRect(90,20,30,30);
		//block3.draw(g);

		g.setColor(mediumColor);
		g.fillRect(30,20,30,30);
		//block4.draw(g);

		g.setColor(darkColor);
		g.fillRect(480,360,30,30);
		//block5.draw(g);

		g.setColor(darkColor);
		g.fillRect(510,330,30,30);
		//block6.draw(g);

		g.setColor(mediumColor);
		g.fillRect(450,390,30,30);
		//block7.draw(g);

		g.setColor(mediumColor);
		g.fillRect(510,390,30,30);
		//block8.draw(g);
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
	

}//End of the PageLogin class
