import java.io.*;
import java.util.Scanner;

public class Source extends Player {

	public static Scanner sc = new Scanner(System.in);
	
	public static void print(String message){
		System.out.println("* "+message);
		space();
	}
	
	public static void space() {
		System.out.println("");
	}
	
	public static void pause(){
		print("Press return to continue...");
		space();
		sc.nextLine();
	}
	
	public static void clearScreen(int number){ //60
		print("---------------------------------------------------------------------------");
		for(int i = 0; i < number; i++){
			space();
		}
	}

	public static void displayTitle(){
		System.out.print("--------------------------------------------------------------------------------");
		System.out.print("|  _        _______  _______ _________                         .-.             |");
		System.out.print("| ( \\      (  ___  )(  ____ \\\\__   __/                        {{@}}            |");
		System.out.print("| | (      | (   ) || (    \\/   ) (                            8@8             |");
		System.out.print("| | |      | (___) || (_____    | |                            888             |");
		System.out.print("| | |      |  ___  |(_____  )   | |                            8@8             |");
		System.out.print("| | |      | (   ) |      ) |   | |                       _    )8(    _        |");
		System.out.print("| | (____/\\| )   ( |/\\____) |   | |                      (@)__/8@8\\__(@)       |");
		System.out.print("| (_______/|/     \\|\\_______)   )_(                       `~\"-=):(=-\"~`        |");
		System.out.print("|                                                              |/|             |");
		System.out.print("|  _______           _______  _______ _________                |'|             |");
		System.out.print("| (  ___  )|\\     /|(  ____ \\(  ____ \\\\__   __/                |/|             |");
		System.out.print("| | (   ) || )   ( || (    \\/| (    \\/   ) (                   |'|             |");
		System.out.print("| | |   | || |   | || (__    | (_____    | |                   |/|             |");
		System.out.print("| | |   | || |   | ||  __)   (_____  )   | |                   |'|             |");
		System.out.print("| | | /\\| || |   | || (            ) |   | |                   |/|             |");
		System.out.print("| | (_\\ \\ || (___) || (____/\\/\\____) |   | |                   |'|             |");
		System.out.print("| (____\\/_)(_______)(_______/\\_______)   )_(                   |/|             |");
		System.out.print("|                                                              |'|             |");
		System.out.print("|                                                              |/|             |");
		System.out.print("|                                                              |'|             |");
		System.out.print("| Developed By:                                                |/|             |");
		System.out.print("|                                                              |'|             |");
		System.out.print("| Brycon Andrew Carpenter                                      \\ /             |");
		System.out.print("|                                                               `              |");
		System.out.print("|______________________________________________________________________________|");
		sc.nextLine();
		clearScreen(30);
	}

	public static void displayProfiles(){
		print("Current profiles:"); space();
		File folder = new File(profileDirectory);
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length;i++) {
			if (listOfFiles[i].isFile()) {
				print(stripExtension(listOfFiles[i].getName()));
			}
		}
	}

	public static void retrieveProfile(String profile) {
		File file = new File(profileDirectory+profile+".txt");
		if(profile.equalsIgnoreCase("create")){
			space();
			print("Initalizing profile creatation...");
			clearScreen(30);
			createProfileScreen();
		}
		else if(file.exists()){
			try {
				readProfile(profile);
				print("Profile "+profile+" successfully loaded");
				pause();
				clearScreen(60);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			print("'"+profile+"' is an invaild response. Type a profile in the list or 'create' to make a new one.");
			Scanner scanner = new Scanner(System.in);
			profile = scanner.nextLine();
			retrieveProfile(profile);
		}
	}

	public static void askForProfile() {
		print("Would you like to use a saved profile or create a new one?"); space();
		print("Create new profile (type 'create')"); space();
		displayProfiles();
		
		Scanner scanner = new Scanner(System.in);
		String profile = scanner.nextLine();
		retrieveProfile(profile);
	}
	
	public static void createProfileScreen() {
		print("You've just started down a journey... But every great hero needs a name.");
		print("Enter yours now.");
		Scanner scanner =  new Scanner(System.in);
		String profile = scanner.nextLine();
		if(profile.indexOf(" ") > -1){
    		profile = profile.substring(0,profile.indexOf(" "));
    		print("Profiles cannot contain a space. Only "+profile+" used as name.");
		}
		try {
			createFile(profile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void pregame() {
		print("Welcome "+playerName+"! You have just embarked upon a quest for great power!");
		print("In the medival providence of Ustagrath, your homeland, a great evil is about");
		print("to unfold. As the son of a powerful land owner you have enjoyed a lavish life. ");
		print("But all of that is about to change...");
		Game.pregame = "Passed";
		try {
			saveProfile(playerName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pause();
		clearScreen(30);
	}
}
