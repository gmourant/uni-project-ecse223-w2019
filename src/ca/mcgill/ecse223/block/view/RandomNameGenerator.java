package ca.mcgill.ecse223.block.view;

import java.util.Random;

public class RandomNameGenerator {

   private String[] beginning = { "Kr", "Ca", "Ra", "Mrok", "Cru",
         "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
         "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
         "Mar", "Luk" };
   private String[] middle = { "air", "ir", "mi", "sor", "mee", "clo",
         "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur", "zer",
         "marac", "zoir", "slamar", "salmar", "urak" };
   private String[] end = { "d", "ed", "ark", "arc", "es", "er", "der",
         "tron", "med", "ure", "zur", "cred", "mur" };
   private String[] game = {" Destructor", " Terminator", " the Only", " Race",
		   " le Defi", ", an Experience", ", Meet Your Destiny", ", Play if you Dare",
		   ", Test your Wits", ", Noobs Only"};
   private Random rand = new Random();

   public String generateName() {
      return beginning[rand.nextInt(beginning.length)] + 
    		  middle[rand.nextInt(middle.length)] + 
    		  end[rand.nextInt(end.length)] +
    		  game[rand.nextInt(game.length)];
   }

}
