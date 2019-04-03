package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Font;

public class ViewTheme{
    public final String name;
    public final Color headerBackground;
    public final Color buttonBackground;
    public final Color background;
    public final Font font;
    
    public ViewTheme(String name, Color headerBackground,
            Color buttonBackground, Color background, Font font){
        this.name = name;
        this.headerBackground = headerBackground;
        this.buttonBackground = buttonBackground;
        this.background = background;
        this.font = font;
    }
}