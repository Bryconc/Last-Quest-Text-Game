import java.io.*;
import java.util.Scanner;

public class Player {

	public static Source source = new Source();
	public static Game game = new Game();
	
	public static String directory;
	public static String profileDirectory;
	public static String playerName;
	public static String playerLocation = "MyBedroom";
	public static int enemiesDefeated = 0;
	public static int perfectBlocks = 0;
	
	public static Inventory inventory = new Inventory();
	public static int[] loc = { 0, 0, 0, 0, 0 };
	
	public static void getFileDirectory(){
		for(int i = 0; i < 26; i++){
			char n = (char)('A'+i);
			File file = new File(n+":/Senior Project");
			
			if(file.isDirectory()){
				directory = n+":/Senior Project";
			}
			
		}
		if(directory.length() == 0){
			System.out.println("Directory could not be found");
		}
		else {
			System.out.println("Directory found on "+directory);
			profileDirectory = directory + "Senior Project/profiles/";
		}
	}
	                     
	public static void createFile(String name) throws IOException {
		File profile = new File(profileDirectory+name+".txt");
		File inventory = new File(profileDirectory+"Inventory/"+name+".txt");
		if(!profile.exists()){
			profile.createNewFile();
			if(!inventory.exists()){
				inventory.createNewFile();
			}
			saveProfile(name);
			Source.space();
			Source.print("Profile "+name+" created! You should now be able to select this profile at the");
			Source.print(" beginning of each play.");
			Source.pause();
			Source.clearScreen(30);
		}
		else if(profile.exists()) {
			Source.print("Sorry that name is already taken. Try a different one.");
			Scanner scanner =  new Scanner(System.in);
			name = scanner.nextLine();
			if(name.indexOf(" ") > -1){
	    		name = name.substring(0,name.indexOf(" "));
	    		Source.print("Profiles cannot contain a space. Only "+name+" used as name.");
			}
			try {
				createFile(name);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	public static void saveProfile(String name) throws IOException {
		File profile = new File(profileDirectory+name+".txt");
		File inv = new File(profileDirectory+"Inventory/"+name+".txt");
		BufferedWriter out = new BufferedWriter(new FileWriter(profile));
		BufferedWriter in = new BufferedWriter(new FileWriter(inv));
		
	    playerName = name;
	    try {
	      out.write(playerName+".Username"); out.newLine();
	      out.write(Game.pregame+".pregame"); out.newLine();
	      out.write(Game.currentStage+".currentStage"); out.newLine();
	      out.write(Game.playerXp+".playerXp"); out.newLine();
	      out.write(Game.playerLocation+".playerLocation");out.newLine();
	      out.write(loc[0]+".dHome");out.newLine();
	      out.write(loc[1]+".dHurlain");out.newLine();
	      out.write(loc[2]+".dTown");out.newLine();
	      out.write(loc[3]+".dSwamp");out.newLine();
	      out.write(loc[4]+".dCave");out.newLine();
	      out.write(enemiesDefeated+".enemiesDefeated");out.newLine();
	      out.write(perfectBlocks+".perfectBlocks"); out.newLine();
	      
	      
	      in.write(inventory.getGold()+".playerGold"); in.newLine();
	      in.write(inventory.getSword()+".Sword");in.newLine();
	      in.write(inventory.getHelm()+".Helm");in.newLine();
	      in.write(inventory.getPlate()+".Plate");in.newLine();
	      in.write(inventory.getLegs()+".Legs");in.newLine();
	      for(int i = 0; i < 20; i++){
	    	  in.write(inventory.getItem(i)+" "+inventory.getAmount(i)); in.newLine();
	      }
	    }
	    finally {
	      out.close();
	      in.close();
	    }
		
	}
	
	public static void readProfile(String name) throws IOException {
		Scanner scanner = new Scanner(new File(profileDirectory+name+".txt"));
		Scanner scan2 = new Scanner(new File(profileDirectory+"Inventory/"+name+".txt"));
		String token;
		while(scanner.hasNext() || scan2.hasNext()){
			token = scanner.next(); playerName = stripExtension(token);
			token = scanner.next(); Game.pregame = stripExtension(token);
			token = scanner.next(); Game.currentStage = Integer.parseInt(stripExtension(token));
			token = scanner.next(); Game.playerXp = Integer.parseInt(stripExtension(token));
			token = scanner.next(); Game.playerLocation = stripExtension(token);
			token = scanner.next(); Game.loc[0] = Integer.parseInt(stripExtension(token));
			token = scanner.next(); Game.loc[1] = Integer.parseInt(stripExtension(token));
			token = scanner.next(); Game.loc[2] = Integer.parseInt(stripExtension(token));
			token = scanner.next(); Game.loc[3] = Integer.parseInt(stripExtension(token));
			token = scanner.next(); Game.loc[4] = Integer.parseInt(stripExtension(token));
			token = scanner.next(); Game.enemiesDefeated = Integer.parseInt(stripExtension(token));
			token = scanner.next(); Game.perfectBlocks = Integer.parseInt(stripExtension(token));
			
			token = scan2.next(); inventory.setGold(Integer.parseInt(stripExtension(token)));
			
			token = scan2.next(); String token2 = scan2.next(); String token3 = scan2.next();String token4 = scan2.next();
			inventory.setEquipment(Integer.parseInt(stripExtension(token)), Integer.parseInt(stripExtension(token2)), Integer.parseInt(stripExtension(token3)), Integer.parseInt(stripExtension(token4)));
			
			for(int i = 0; i < 20; i++){
				int token1 = scan2.nextInt(); int token21 = scan2.nextInt(); inventory.addItem(token1, token21);
			}
		}
	}
	
	public static String stripExtension(String str) {
		int pos = str.lastIndexOf(".");		// Get position of last '.'.
		if (str == null) {
			return null;
		}
		if (pos == -1) { 					// If there wasn't any '.' just return the string as is.
			return str;
		}
		return str.substring(0, pos); 		// Otherwise return the string, up to the dot.
	}
	
	public static String convertInteger(int value){
		return Integer.toString(value);
	}
	
	public static void save(){
		try {
			saveProfile(playerName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
