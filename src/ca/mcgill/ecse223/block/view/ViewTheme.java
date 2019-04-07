package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class ViewTheme{
    public enum Type {Color, Image}
    
    public final Type type;
    public final String name;
    public final Object headerBackground;
    public final Color headerBackgroundFiller;
    public final Color buttonBackground;
    public final Object background;
    public final Font font;
    public final Color textColor;
    public final Color textColorWithHeaderBackground;
    
    public ViewTheme(String name, Color headerBackground,
            Color buttonBackground, Color background, Font font, 
            Color defaultColor, Color colorOfTextWithHeaderBackground){
        type = Type.Color;
        this.name = name;
        this.headerBackground = headerBackground;
        headerBackgroundFiller = headerBackground;
        this.buttonBackground = buttonBackground;
        this.background = background;
        this.font = font;
        textColor = defaultColor;
        textColorWithHeaderBackground = colorOfTextWithHeaderBackground;
    }
    
    public ViewTheme(String name, String headerBackground, Color headerBackgroundFiller,
            Color buttonBackground, String background, Font font, 
            Color defaultColor, Color colorOfTextWithHeaderBackground){
        type = Type.Image;
        this.name = name;
        this.headerBackground = headerBackground;
        this.headerBackgroundFiller = headerBackgroundFiller;
        this.buttonBackground = buttonBackground;
        this.background = background;
        this.font = font;
        textColor = defaultColor;
        textColorWithHeaderBackground = colorOfTextWithHeaderBackground;
    }
}