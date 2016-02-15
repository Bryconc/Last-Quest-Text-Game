public class Map {
	private boolean[] locations = new boolean[Game.loc.length];
	
	public Map(){
		for(int i = 0; i < Game.locationsAmount; i++){
			locations[i] = false;
		}
	}
	
	public Map(boolean[] loc){
		
		for(int i = 0; i < loc.length; i++){
			locations[i] = loc[i];
		}
	}
	
	public void drawMap(){
		System.out.print("--------------------------------------------------------------------------------");
		System.out.print("|    /\\                                        |_|_|			       |");
		System.out.print("|   /  \\					| |			       |");
		System.out.print("|   |__|					|_|			       |");
		
		System.out.print("|   ");
		if(locations[0])
			System.out.print("Home");
		else
			System.out.print("****");
		System.out.print("                                    ");
		if(locations[1])
			System.out.print("Hurlain's Tower");
		else
			System.out.print("***************");
		System.out.print("                    |");
		
		System.out.print("|    \\  \\                                         / /                          |");
		System.out.print("|     \\  \\ ______________________________________/ /                           |");
		System.out.print("|      \\_______________________    _______________/                            |");
		System.out.print("|                              \\  \\                                            |");
		System.out.print("|                                   /\\                                         |");
		System.out.print("|                               /\\  []                                         |");
		System.out.print("|                               [] /\\                                          |");
		System.out.print("|                                  []                    _____                 |");
		System.out.print("|                              ");
				if(locations[2] == true)
					System.out.print("Glyncove");
				else 
					System.out.print("********");
		System.out.print("                 /____/|                |");
		
		System.out.print("|     %%     ____________________/   /_________________|_|__|_/                |");
		System.out.print("|     %\\ ___ /  _______________________________________ ");
		if(locations[4]){
			System.out.print("Haunted Cave");
		}
		else {
			System.out.print("************");
		}
	    System.out.print("           |");
		System.out.print("|      /~~~) /                                                                 |");
		System.out.print("|     (___/                                                                    |");
		System.out.print("| ");
		if(locations[3]){
			System.out.print("Dead Man's Swamp");
		}
		else {
			System.out.print("****************");
		}
		System.out.print("                                                             |");
		System.out.print("--------------------------------------------------------------------------------");
	}
}
