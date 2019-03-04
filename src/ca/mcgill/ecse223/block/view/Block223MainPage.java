package ca.mcgill.ecse223.block.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

public class Block223MainPage extends JFrame {

    public final static Color HEADER_BACKGROUND = new Color(224, 249, 246);
    public final static Color BUTTON_BACKGROUND = new Color(207, 243, 238);
    public final static Font UI_FONT = new Font("Century Gothic", Font.PLAIN, 14);
    public final static int TITLE_SIZE_INCREASE = 4;
    int level;//We are still working on how to get the levels from the game

    // enums for tetermining current page
    public enum Page {
        adminMenu, // page for selecting destination page
        addGame, defineGame,
        deleteGame, updateGame,
        addBlock, deleteBlock,
        updateBlock, positionBlock,
        moveBlock, removeBlock,
        saveGame,
        login, logout, signUp
    }

    private Page currentPage = Page.login;

    private JPanel topMenu;
    private JButton save;
    private JButton logout;
    private JScrollPane sideMenu;
    private JList sideMenuItems;
    private ContentPage displayedPage;
    private final Block223MainPage thisInstance;

    public Block223MainPage() {
        this.setSize(500, 500); // Specifies the size should adjust to the needs for space
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Specifies what the X to close does
        this.setLocationRelativeTo(null); // Places in the center of the screen
        this.setResizable(true); // stops user from resizing the dialog box
        this.setUndecorated(true);

        thisInstance = this;

        // setting up
        setUIFont(new javax.swing.plaf.FontUIResource(UI_FONT.getFontName(), UI_FONT.getStyle(), UI_FONT.getSize()));
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
        sideMenu.setVisible(false);
        save.setVisible(false);
        logout.setVisible(false);

        // show menus if appropriate
        if (toDisplay != Page.login && toDisplay != Page.signUp) {
            sideMenu.setVisible(true);
            save.setVisible(true);
            logout.setVisible(true);
        }

        // creates the correct JPanel depending on the selected page specified 
        // in the toDisplay enum
        switch (toDisplay) {
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
            case saveGame:

                break;
            default:
                displayedPage = new PageAdminMenu(this);
        }
        add(displayedPage, BorderLayout.CENTER); // adds panel to JFrame
    }

    /**
     * This method initalises all the information for the top menu.
     *
     * @author Georges Mourant
     */
    private void setupTopMenu() {
        topMenu = new JPanel(new GridLayout(1, 4));
        topMenu.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() / 10));
        topMenu.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        topMenu.setBackground(HEADER_BACKGROUND);

        JLabel adminName = new JLabel(""); // empty by default
        save = createButton("Save");
        logout = createButton("Logout");
        topMenu.add(adminName);
        topMenu.add(save);
        topMenu.add(logout);

        JPanel exitMin = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitMin.setBackground(topMenu.getBackground()); // match to background
        JButton minimize = createButton("_");
        JButton exit = createButton("X");
        minimize.setBackground(exitMin.getBackground()); // match to background
        exit.setBackground(exitMin.getBackground()); // match to background
        exitMin.add(minimize);
        exitMin.add(exit);
        topMenu.add(exitMin);

        add(topMenu, BorderLayout.NORTH);

        // always show
        topMenu.setVisible(true);

        // listeners
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //clear error message
                String error = null;

                //call the controller
                try {
                    Block223Controller.saveGame();
                } catch (InvalidInputException e) {
                    new ViewError(e.getMessage(), false, thisInstance);
                    //refreshDataOfCurrentScreen//TODO
                }
            }//End of actionPerformed by save method
        });
        minimize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setState(JFrame.ICONIFIED); // minimize window
            }
        });
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        DefaultListModel listModel;
        listModel = new DefaultListModel();
        JLabel Label = new JLabel("Main Menu");
        Label.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        listModel.addElement("Main Menu :");
        listModel.addElement("Add Game");
        listModel.addElement("Delete Game");
        sideMenuItems = new JList(listModel);
        listModel.addElement("Add Block");
        listModel.addElement("Delete Block");
        listModel.addElement("Position Block");
        listModel.addElement("Update Block");

        //SideMenu options
        sideMenu = new JScrollPane(sideMenuItems);
        sideMenu.createVerticalScrollBar();
        sideMenu.setPreferredSize(new Dimension(this.getWidth() / 4, this.getHeight()));
        add(sideMenu, BorderLayout.WEST);

        //If statements to change page
        sideMenuItems.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String s = (String) sideMenuItems.getSelectedValue();
                if (s.equals("Add Game")) {
                    changePage(Block223MainPage.Page.addGame);
                } else if (s.equals("Delete Game")) {
                    changePage(Block223MainPage.Page.deleteGame);
                } else if (s.equals("Add Block")) {
                    changePage(Block223MainPage.Page.addBlock);
                } else if (s.equals("Update Block")) {
                    changePage(Block223MainPage.Page.updateBlock);
                } else if (s.equals("Delete Block")) {
                    changePage(Block223MainPage.Page.deleteBlock);
                } else if (s.equals("Main Menu :")) {
                    changePage(Block223MainPage.Page.adminMenu);
                } else if (s.equals("Position Block")) {
                    changePage(Block223MainPage.Page.positionBlock);
                } else if (s.equals("Update Block")) {
                    changePage(Block223MainPage.Page.updateBlock);
                }
            }
        });

        //To center the text
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) sideMenuItems.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
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
        button.setBackground(Block223MainPage.BUTTON_BACKGROUND);
        button.setForeground(Color.DARK_GRAY);
        button.setFocusPainted(false);
        return button;
    }

    /**
     * This method applies a font to the entire application.
     *
     * @author the World Wide Web
     * @param f the desired font
     */
    private static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

}
