package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Font;

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
    public final Color lightColor;//Color for one of the UI blocks
    public final Color mediumColor;//Color for one of the UI blocks 
    public final Color darkColor;//Color for one of the UI blocks
    public final Color ballColor;//Color of ball for UI
    public final Color paddleColor;//Color of ball for UI
    
    public ViewTheme(String name, Color headerBackground,
            Color buttonBackground, Color background, Font font, 
            Color defaultColor, Color colorOfTextWithHeaderBackground,
            Color lightColor, Color mediumColor, Color darkColor, 
            Color ballColor, Color paddleColor){
        type = Type.Color;
        this.name = name;
        this.headerBackground = headerBackground;
        headerBackgroundFiller = headerBackground;
        this.buttonBackground = buttonBackground;
        this.background = background;
        this.font = font;
        textColor = defaultColor;
        textColorWithHeaderBackground = colorOfTextWithHeaderBackground;
        this.lightColor = lightColor;
        this.mediumColor = mediumColor;
        this.darkColor = darkColor;
        this.ballColor = ballColor;
        this.paddleColor = paddleColor;
    }
    
    public ViewTheme(String name, String headerBackground, Color headerBackgroundFiller,
            Color buttonBackground, String background, Font font, 
            Color defaultColor, Color colorOfTextWithHeaderBackground,
            Color lightColor, Color mediumColor, Color darkColor, 
            Color ballColor, Color paddleColor){
        type = Type.Image;
        this.name = name;
        this.headerBackground = headerBackground;
        this.headerBackgroundFiller = headerBackgroundFiller;
        this.buttonBackground = buttonBackground;
        this.background = background;
        this.font = font;
        textColor = defaultColor;
        textColorWithHeaderBackground = colorOfTextWithHeaderBackground;
        this.lightColor = lightColor;
        this.mediumColor = mediumColor;
        this.darkColor = darkColor;
        this.ballColor = ballColor;
        this.paddleColor = paddleColor;
    }
}