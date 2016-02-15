public class Main extends Source {
	
	public static void main(String args[]) {
		
		getFileDirectory(); // Assigns file directory based on location		
		
		askForProfile();  // Create profiles		
		
		if(Game.pregame.equals("Unplayed")) {  // Displays initial information
			pregame();
		}		
		
		displayTitle();  //Display title screen		
		
		Game.findRoom(); //Finds current location and proceeds with game.		
		
		save();
		print("The game has ended."); //Final task before ending game
		
	}
}
