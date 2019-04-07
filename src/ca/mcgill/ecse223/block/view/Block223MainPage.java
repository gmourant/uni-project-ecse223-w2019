package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;

public class Block223MainPage extends JFrame {

    public final static ViewTheme THEMES[] = { 
        new ViewTheme("Boxing Day", new Color(62, 61, 60), new Color(210, 215, 223), 
                new Color(62, 61, 60), new Font("Consolas", Font.PLAIN, 14), 
                Color.DARK_GRAY, new Color(227, 228, 219)),
        new ViewTheme("Spring Galore", new Color(235, 112, 96), new Color(202,194,165), 
                new Color(221,174,121), new Font("Consolas", Font.PLAIN, 14), 
                new Color(116, 157, 144), new Color(248,249,248)),
        new ViewTheme("Last Frontier", "imageThemes/spacethemeHeader.jpg",
                Color.BLACK, Color.LIGHT_GRAY, "imageThemes/spacethemeBackground.jpg",
                new Font("Consolas", Font.PLAIN, 14), 
                Color.WHITE, Color.WHITE),
    };
    public static ViewTheme currentTheme;
    
    public static int TITLE_SIZE_INCREASE = 4;
    int level;//We are still working on how to get the levels from the game
    private JLabel currentGameDisplay;
    private JComboBox<String> chooseGame;
    private JPanel leftSide;
    private JButton minimize;
    private JButton exit;

    // enums for tetermining current page
    public enum Page {
        adminMenu, // page for selecting destination page
        addGame, defineGame, defineNewGame,
        deleteGame, updateGame,
        addBlock, deleteBlock,
        updateBlock, positionBlock,
        moveBlock, removeBlock,
        login, logout, signUp, welcome,
        pickTheme, playGame, chooseGame
    }

    private Page currentPage = Page.welcome;

    private JPanel topMenu;
    private JButton save;
    private JButton logout;
    private JScrollPane sideMenu;
    private JList sideMenuItems;
    private ContentPage displayedPage;
    private final Block223MainPage thisInstance;
    
    public boolean defineTheme(String name){
        ViewTheme theme = null;
        for(int i = 0; i < THEMES.length; i++){
            if(name.equals(THEMES[i].name)){
                theme = THEMES[i];
                break;
            }
        }
        if(theme == null){
            if(currentTheme == null){
                defineTheme("Boxing Day");
            }
            return false;
        }
        currentTheme = theme;
        setUIFont(getUIFont(), getDefaultForeground());
        
        if(logout != null){
            logout.setBackground(getButtonBackground());
            logout.setForeground(getDefaultForeground());
        }
        if(save != null){
            save.setBackground(getButtonBackground());
            save.setForeground(getDefaultForeground());
        }
        
        if(minimize != null){
            minimize.setBackground(getHeaderBackgroundFiller());
            minimize.setForeground(getForegroundForBackground());
        }
        if(exit != null){
            exit.setBackground(getHeaderBackgroundFiller());
            exit.setForeground(getForegroundForBackground());
        }
        
        if(chooseGame != null){
            chooseGame.setBackground(getButtonBackground());
            chooseGame.setForeground(getDefaultForeground());
        }
        
        if(topMenu != null){
            topMenu.repaint();
        }
        
        return true;
    }
    
    public static Color getHeaderBackgroundFiller(){return currentTheme.headerBackgroundFiller;}
    public static Color getButtonBackground(){return currentTheme.buttonBackground;}
    public static Font getUIFont(){return currentTheme.font;}
    public static Color getDefaultForeground(){return currentTheme.textColor;}
    public static Color getForegroundForBackground(){return currentTheme.textColorWithHeaderBackground;}
    
    public Block223MainPage() {
        this.setSize(570, 500); // Specifies the size should adjust to the needs for space
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Specifies what the X to close does
        this.setLocationRelativeTo(null); // Places in the center of the screen
        this.setResizable(true); // stops user from resizing the dialog box
        this.setUndecorated(true);

        defineTheme("Boxing Day");
        thisInstance = this;
        
        // setting up
        setUIFont(getUIFont(), getDefaultForeground());
        setLayout(new BorderLayout());
        setupTopMenu();
        setupSlideMenu();
        displayPage(currentPage);
    }

    /**
     * This method changes the current page.
     *
     * @author Georges Mourant
     * @param newPage the enum representing the desired page
     */
    public void changePage(Page newPage) {
        currentPage = newPage;
        displayPage(newPage);
    }

    /**
     * This method actually removes the old displayed page and puts the new one
     * in place.
     *
     * @param toDisplay enum of the desired page
     */
    private void displayPage(Page toDisplay) {
        // removes old page JPanel from JFrame
        if (displayedPage != null) {
            displayedPage.setVisible(false);
            remove(displayedPage);
            repaint();
        }
        // make sure all buttons are visible
        leftSide.setVisible(false);
        save.setVisible(false);
        logout.setVisible(false);

        // show menus if appropriate
        if (toDisplay != Page.login && toDisplay != Page.signUp && toDisplay != Page.welcome && toDisplay != Page.playGame && toDisplay != Page.chooseGame) {
            leftSide.setVisible(true);
            save.setVisible(true);
            logout.setVisible(true);
        }
         if(toDisplay == Page.playGame || toDisplay == Page.chooseGame) {
        	save.setVisible(true);
        	logout.setVisible(true);
        }
        
        else {
            setCurrentGameDisplay("");
        }

        // creates the correct JPanel depending on the selected page specified 
        // in the toDisplay enum
        switch (toDisplay) {
            case chooseGame : 
        		displayedPage = new PageChooseGame(this);
        		break;
        	case playGame:
        		displayedPage = new PagePlayGame(this);
        		break; 
            case welcome:
                displayedPage = new PageWelcome(this);
                break;
            case login:
                displayedPage = new PageLogin(this);
                break;
            case logout:
                displayedPage = new PageLogout(this);
                break;
            case signUp:
                displayedPage = new PageSignUp(this);
                break;
            case addGame:
                displayedPage = new PageAddGame(this);
                break;
            case defineNewGame:
                displayedPage = new PageDefineNewGame(this);
                break;
            case defineGame:
                displayedPage = new PageDefineGame(this);
                break;
            case deleteGame:
                displayedPage = new PageDeleteGame(this);
                break;
            case updateGame:
                displayedPage = new PageUpdateGame(this);
                break;
            case addBlock:
                displayedPage = new PageAddBlock(this);
                break;
            case deleteBlock:
                displayedPage = new PageDeleteBlock(this);
                break;
            case updateBlock:
                displayedPage = new PageUpdateBlock(this);
                break;
            case positionBlock:
                displayedPage = new PagePositionBlock(this);
                break;
            case moveBlock:
                displayedPage = new PageMoveBlock(this, level);
                break;
            case removeBlock:
                displayedPage = new PageRemoveBlock(this, level);
                break;
            case pickTheme:
                displayedPage = new PagePickTheme(this);
                break;
            default:
                displayedPage = new PageLogout(this);
        }
        add(displayedPage, BorderLayout.CENTER); // adds panel to JFrame
    }

    /**
     * This method initalises all the information for the top menu.
     *
     * @author Georges Mourant
     */
    private void setupTopMenu() {
        topMenu = new JPanelWithBackground(JPanelWithBackground.Background.header, new GridLayout(1, 4));
        topMenu.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() / 10));
        topMenu.setBorder(BorderFactory.createLineBorder(Color.darkGray));

        currentGameDisplay = new JLabel("None selected"); // empty by default
        currentGameDisplay.setForeground(getForegroundForBackground());
        save = createButton("Save");
        logout = createButton("Log out");
        topMenu.add(currentGameDisplay);
       // topMenu.add(save);
        //topMenu.add(logout);
        save.setForeground(getForegroundForBackground());
        save.setBackground(Block223MainPage.getHeaderBackground());
        logout.setForeground(getForegroundForBackground());
        logout.setBackground(Block223MainPage.getHeaderBackground());


        JPanel exitMin = new JPanelWithBackground(JPanelWithBackground.Background.header, new FlowLayout(FlowLayout.RIGHT));
        minimize = createButton("_");
        minimize.setForeground(getForegroundForBackground());
        exit = createButton("X");
        exit.setForeground(getForegroundForBackground());
        minimize.setBackground(getHeaderBackgroundFiller()); // match to background
        exit.setBackground(getHeaderBackgroundFiller()); // match to background
        exitMin.add(save);
        exitMin.add(logout);
        exitMin.add(minimize);
        exitMin.add(exit);
        topMenu.add(exitMin);

        add(topMenu, BorderLayout.NORTH);

        // always show
        topMenu.setVisible(true);

        // listeners
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                //call the controller
                try {
                    Block223Controller.saveGame();
                } catch(InvalidInputException e) {
                    new ViewError(e.getMessage(), false, thisInstance);
                }
            }//End of actionPerformed by save method
        });
        
        
        logout.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                	//call to controller
            		Block223Controller.logout();
            		//Go back to login screen
            		changePage(Block223MainPage.Page.login);
                }
        });//End of actionPerformed by logout method
        
        minimize.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    setState(JFrame.ICONIFIED); // minimize window
                }
        });
        
        minimize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setState(JFrame.ICONIFIED); // minimize window
            }
        });
        
        
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ca.mcgill.ecse223.block.controller.Block223Controller.logout();
                System.exit(0); // quit program
            }
        });
    }

    /**
     * This method initalises all the information for the slider menu on the
     * left.
     *
     * @author Georges Mourant, Imane Chafi helped :)
     */
    @SuppressWarnings("unchecked")
    private void setupSlideMenu() {
        leftSide = new JPanel(new BorderLayout());
        
        JPanel chooseGameHolder = new JPanel();
        chooseGameHolder.setBackground(Color.WHITE);
        chooseGameHolder.setBorder(BorderFactory.createLineBorder(Color.gray));
        chooseGame = ContentPage.createComboBox();
        chooseGameHolder.add(chooseGame);
        leftSide.add(chooseGameHolder, BorderLayout.SOUTH);
        loadGameList();
        
        DefaultListModel listModel;
        listModel = new DefaultListModel();
        sideMenuItems = new JList(listModel);
        listModel.addElement("Add Game");
        listModel.addElement("Delete Game");
        listModel.addElement("Update Game");
        listModel.addElement("Add Block");
        listModel.addElement("Delete Block");
        listModel.addElement("Position Block");
        listModel.addElement("Update Block");
        listModel.addElement("Move Block");
        listModel.addElement("Remove Block");
        listModel.addElement("Change Theme");
            
        //SideMenu options
        sideMenu = new JScrollPane(sideMenuItems);
        sideMenu.createVerticalScrollBar();
        sideMenu.setPreferredSize(new Dimension(this.getWidth() / 4, this.getHeight()));
        leftSide.add(sideMenu, BorderLayout.NORTH);
        
        add(leftSide, BorderLayout.WEST);

        //If statements to change page
        sideMenuItems.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String s = (String) sideMenuItems.getSelectedValue();
                switch (s) {
                    case "Add Game":
                        changePage(Block223MainPage.Page.addGame);
                        break;
                    case "Delete Game":
                        changePage(Block223MainPage.Page.deleteGame);
                        break;
                    case "Add Block":
                        changePage(Block223MainPage.Page.addBlock);
                        break;
                    case "Update Block":
                        changePage(Block223MainPage.Page.updateBlock);
                        break;
                    case "Delete Block":
                        changePage(Block223MainPage.Page.deleteBlock);
                        break;
                    case "Main Menu :":
                        changePage(Block223MainPage.Page.logout);
                        break;
                    case "Position Block":
                        changePage(Block223MainPage.Page.positionBlock);
                        break;
                    case "Move Block":
                        changePage(Block223MainPage.Page.moveBlock);
                        break;
                    case "Remove Block":
                        changePage(Block223MainPage.Page.removeBlock);
                        break;
                    case "Update Game":
                        changePage(Block223MainPage.Page.updateGame);
                        break;
                    case "Change Theme":
                        changePage(Block223MainPage.Page.pickTheme);
                        break;
                    default:
                        break;
                }
            }
        });

        //To center the text
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) sideMenuItems.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        chooseGame.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String game = (String)chooseGame.getSelectedItem();
                if (game == null) {
                    return;
                }
                if (game.equals("Select a Game")) {
                    return;
                }
                currentGameDisplay.setText(game);
                try{
                    Block223Controller.selectGame(game);
                } catch (InvalidInputException ev){
                    new ViewError(ev.getMessage(), false, thisInstance);
                }
            }
        });
    }

    //Action Listeners 
    /* listModel.addActionListener(new java.awt.event.ActionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                jList1ValueChanged(evt);
             
            });*/
    /**
     * This method returns the JList of the side menu so that it may be
     * modified.
     *
     * @author Georges Mourant
     * @return the JList of the side menu
     */
    public JList getSideMenuItems() {
        return sideMenuItems;
    }

    // ****************************
    // Private Helper Methods
    // ****************************
    /**
     * Creates and returns a formatted JButton following this application's
     * design.
     *
     * @author Georges Mourant
     * @param text Text of the JButton
     * @return a formatted JButton
     */
    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(getButtonBackground());
        button.setForeground(getDefaultForeground());
        button.setFocusPainted(false);
        return button;
    }

    /**
     * This method applies a font to the entire application.
     *
     * @author the World Wide Web
     * @param f the desired font
     */
    private static void setUIFont(Font font, Color color) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, new javax.swing.plaf.FontUIResource(font));
            }
//            if(value instanceof javax.swing.plaf.ColorUIResource){
//                UIManager.put(key, new javax.swing.plaf.ColorUIResource(color));
//            }
        }
    }

    public void loadGameList(){
        chooseGame.removeAllItems();
        chooseGame.addItem("Select a Game");
        java.util.List<TOGame> games;
        try{
            games = Block223Controller.getDesignableGames();
        }
        catch(InvalidInputException e){
            // stop now to prevent future errors based on this exception
            return;
        }
        // add this list
        for(TOGame game : games){
            chooseGame.addItem(game.getName());
        }
    }
    
    public void setCurrentGameDisplay(String txt){
        if(currentGameDisplay == null) return;
        currentGameDisplay.setText(txt);
    }
}
