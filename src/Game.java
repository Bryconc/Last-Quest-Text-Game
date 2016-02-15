import java.util.Scanner;

public class Game extends Source {

	public static boolean onMap = false;
	public static String pregame = "Unplayed";

	public static int locationsAmount = 5;
	public static String interest = "";

	public static int dHome = 0;
	public static int dHurlain = 1;
	public static int dTown = 2;
	public static Scanner sc = new Scanner(System.in);
	public static Combat combat;

	public static int currentStage = 0;
	public static int playerXp = 0;

	public static int playerLevel(){
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= 99; lvl++) {
			
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			
			if (output >= playerXp) {
				return lvl;
			}
			
		}
		return 99;
	}

	public static int xpForLevel() {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= playerLevel(); lvl++) {
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
		}
		return output;
		
	}

	public static void addXp(int xp) {
		int next = xpForLevel();
		playerXp += xp;
		System.out.println("**** Gained " + xp + " Experience ***");
		if (playerXp > next) {
			System.out.println("###########   LEVEL UP   ###########     New Level: "
							+ playerLevel());
			if(playerLevel() == 5){
				Discovery("Location", "Dead Man's Swamp");
				loc[3] = 1;
			}
		}
		save();

	}
	
	public static String itemsOfInterest(String[] items) {
		String end = "Items of Interest: " + items[0];
		for (int i = 1; i < items.length; i++) {
			end += ", " + items[i];
		}
		interest = end;

		return end;
	}

	public static void findRoom() {

		print("**** " + playerLocation + " ****");

		if (playerLocation.equalsIgnoreCase("MyBedroom")) {
			loc[0] = 1;
			save();
			print("You are in your bedroom, a large spacious room, that has served as");
			print("your living quaters since your birth. There is a door to the south.");

			String items[] = { "Door (to downstairs)" };
			System.out.println(itemsOfInterest(items));

			space();
			print("type 'commands' to view a list of usable commands.");
		} else if (playerLocation.equalsIgnoreCase("HomeDownstairs")) {
			if(loc[1] < 2){
				print("The grand hall of your father's home. Your family gathers here");
				print("often to discuss ongoing events.");
				if ((loc[2] == 0)) {
					print("Your father stands in the middle of the room.");
				
					String items[] = { "Dad", "Door (to Outside)" };
					System.out.println(itemsOfInterest(items));
				} else {
					print("There's no one home.");
					String[] items = { "Door (to Outside)" };
					System.out.println(itemsOfInterest(items));
				}
			}
			else if(loc[1] == 2 && loc[0] > 0){
				System.out.println("               (  .      )");
				System.out.println("           )           (              )");
				System.out.println("                 .  '   .   '  .  '  .");
				System.out.println("        (    , )       (.   )  (   ',    )");
				System.out.println("         .' ) ( . )    ,  ( ,     )   ( .");
				System.out.println("      ). , ( .   (  ) ( , ')  .' (  ,    )");
				System.out.println("     (_,) . ), ) _) _,')  (, ) '. )  ,. (' )");
				System.out.println("    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				print("... The house has been under siege. Almost everything is destroyed.");
				print("The attackers have obviously left though. Your father lies badly beaten");
				print("over in the distance.");
				String[] items = {"Dad"};
				System.out.println(itemsOfInterest(items));		
			}
			else if(loc[0]  == 0){
				System.out.println("You must claim vengence for your family and save your mother!");
				System.out.println("At the moment though Baron von Drasyl is much too powerful for you");
				System.out.println("you will need to be at least level 20 before you try to fight him.");
				System.out.println("Until then you must train and fight other enemies. New locations");
				System.out.println("Will appear as you level. Train where you can. Visit Glyncove");
				System.out.println("to purchase potions and equipment. Revisit Hurlain's Basement");
				System.out.println("if you need to reread the combat guide. Keep strong,");
				System.out.println("one day, you will have revenge!");
				pause();
				if(playerLevel() < 5){
					addXp(390-playerXp);
				}
				playerLocation = "GlyncoveSquare";
				outside();
			}
		}

		else if (playerLocation.equalsIgnoreCase("Hurlain")) {
			print("A well kept tower that has been meticulously cleaned. This tower has stood");
			print("longer than almost any other structure in Ustagrath, and the Hurlain family");
			print("has occupied it since it's creation.");
			String items[] = { "Hurlain", "Door (to Outside)",
					"Ladder (to Basement)" };
			System.out.println(itemsOfInterest(items));
			if ((inventory.hasItem(1, 1) || inventory.getSword() == 1) && loc[1] == 1) {
				talk("Elder Hurlain");
			}
		}

		else if (playerLocation.equalsIgnoreCase("GlyncoveSquare")) {
			print("A humble town established some 150 years ago. It is the 2nd largest city in");
			print("Ustagrath, and is the market center of the region. In the center stands");
			print("the iconic statue of King Iglesias III who liberated the town founders");
			print("from the oppression of a rival nation.");

			if (loc[1] == 1) { // Not finished with Hurlain
				String[] items = { "Hurlain", "Blacksmith Shop",
						"Trail(to Out of Town)" };
				System.out.println(itemsOfInterest(items));
			}
			else {
				String[] items= {"Blacksmith Shop","Trail(to Out of Town)"};
				System.out.println(itemsOfInterest(items));
			}

			if ((loc[1] == 1) && (loc[0] == 1)) {
				talk("Elder Hurlain");
			}
		}

		else if (playerLocation.equalsIgnoreCase("Blacksmith")) {
			print("A shop adorned with some of the finest weapons you've ever seen.");
			print("The blacksmith stands behind the counter. The man's odor and appearence");
			print("reflects the tedious masterful work put in to each instrument.");

			if (loc[1] == 1) {
				String[] items = { "Blacksmith", "Hurlain", "Door (to Town)" };
				System.out.println(itemsOfInterest(items));
			}
			else {
				String[] items = { "Blacksmith", "Door (to Town)" };
				System.out.println(itemsOfInterest(items));
			}
		}

		else if (playerLocation.equalsIgnoreCase("HurlainBasement")) {
			print("The basement of Hurlain's Tower. It does not refect the level");
			print("of cleanliness exhibited throughout the rest of the tower. You");
			print("can hear many rats scurring around in the darkness.");
			if(loc[1] == 1){
				String items[] = { "Hurlain", "Rat Nest", "Ladder (to upstairs)" };
				System.out.println(itemsOfInterest(items));
			}
			else {
				String items[] = { "Rat Nest", "Ladder (to upstairs)" };
				System.out.println(itemsOfInterest(items));
			}

		}
		
		else if(playerLocation.equalsIgnoreCase("Swamp")){
			System.out.println("          ____________");
			System.out.println("        _(            )_");
			System.out.println("      _(                )_");
			System.out.println("     (_                  _)");
			System.out.println("     & (_              _) &");
			System.out.println("     &  &(____________)   &");
			System.out.println("     &  &  \\         /&   &");
			System.out.println("     &  &   |       |  &   &");
			System.out.println("    &   &   |       |   &   &");
			System.out.println("    &   &   |       |   &   &");
			System.out.println("    &   &   |       |   &   &");
			System.out.println("    &  &   /         \\  &   &");
			System.out.println("    &  &  /___________\\ &   &");
			
			print("A swamp that his been forgotten over time. Many have ");
			print("fallen victim to its murky waters, hence it's name.");
			print("Now the swamp has been overriden with various monsters.");
			
			String items[] = {"Cave", "Trail (out of Swamp)"};
			System.out.println(itemsOfInterest(items));
			
		}

		System.out.println();
		listen();

	}

	public static void changeRoom(String place) {
		onMap = false;
		System.out.println("\t\t _________");
		System.out.println("\t\t|         |");
		System.out.println("\t\t|         |");
		System.out.println("\t\t|         |");
		System.out.println("\t\t|       0 |");
		System.out.println("\t\t|         |");
		System.out.println("\t\t|         |");
		System.out.println("\t\t|         |");
		System.out.println("\t\t|         |");
		System.out.println("\t\t|_________|\n");

		if (place.equals("Outside")) {
			outside();
		}

		else {
			playerLocation = place;
		}

		save();
		findRoom();
	}

	public static void outside() {
		onMap = true;
		boolean[] l = new boolean[loc.length];

		for (int i = 0; i < loc.length; i++) {
			if (loc[i] == 0) {
				l[i] = false;
			} else {
				l[i] = true;
			}
		}
		Map m = new Map(l);
		m.drawMap();

		print("Where would you like to go? \n Type 'to [place]' with the place exactly as you see it.");
		listen();
	}

	public static void talk(String character) {
		Dialogue npc = new Dialogue(character);

		if (character.equalsIgnoreCase("Dad")) {
			if(loc[1] < 2){
				if (loc[1] == 0) {
					npc.add("Well son, the day has finally come when you are to set out on your own.");
					npc.add("Here's your chance to make me proud to call you a son of mine. Venture to");
					npc.add("the Elder Hurlain's tower. He will instruct you further.");
					npc.add("I believe in you son.");
					loc[1] = 1;
					save();
					npc.addRes("Thanks dad! I won't disappoint you.");
					Discovery("Location", "Hurlain's Tower");
				} else {
					npc.add("I have already told you all you need to know. Now go see Elder Hurlain");
					npc.add("before I lose my patience with you, child!");
					npc.setEmotion(1);
				}
			}
			else {
				npc.add("My son! Thank God you are safe! Baron Von Drasyl... He attacked our");
				npc.add("home, ransacked it, stole everything, even kidnapped your mother.");
				npc.add("You must seek him out! You must avenge us son!");
				npc.setEmotion(3);
				loc[0] = 0;
				save();
			}
		}
		if (character.equalsIgnoreCase("Elder Hurlain")) {
			npc.setEmotion(2);
			if (loc[1] == 1) {
				if (loc[2] == 0) {
					npc.add("Ah, so there you are boy. Your father told me of your coming. I've heard");
					npc.add("a great deal about you, but you still have much to learn. I will instruct you");
					npc.add("in many arts. But first you must accompany me to town. We shall leave");
					npc.add("whenever you are ready. Just open the door and I will follow.");
					loc[2] = 1;
					save();
					Discovery("Location", "City of Gylncove");
				} else if (((inventory.hasItem(1, 1) || inventory.getSword() == 1))
						&& playerLocation.equals("Hurlain")) {
					npc.add("Ah good, home sweet home again. Kindly climb that ladder over there and");
					npc.add("we'll proceed to the basement for your final training.");
					npc.say();
					pause();
					changeRoom("HurlainBasement");
				} else if (playerLocation.equalsIgnoreCase("GlyncoveSquare")) {
					if (loc[0] == 1) {
						loc[0] = 0;
						save();
						npc.add("Well, here we are in Glyncove. My, this city sure has changed in my");
						npc.add("lifetime. It's gone from beggars galore to the club house of the");
						npc.add("nobles. Why in my time... Oh, excuse me, I don't get out very often.");
						npc.add("Anyway, what were we here for? Oh right, you'll need armaments...");
						npc.add("Let's go over to the blacksmith shop.");
					} else if (!inventory.hasItem(1, 1)) {
						npc.add("Let's go to the blacksmith shop.");
					} else if (inventory.hasItem(1, 1)) {
						npc.add("We should return to the tower, I'll explain more there.");
					}
				} else if (playerLocation.equalsIgnoreCase("Blacksmith")) {
					if (inventory.getGold() < 100 && !inventory.hasItem(1, 1) && inventory.getSword() != 1) {
						npc.add("Ah yes, this seems like a fine establishment to purchase your");
						npc.add("weapon. Here's 100 coins. Go see what the salesman has to offer.");
						inventory.changeGold(100);
						npc.addRes("Thanks Elder!");
					} else if (inventory.getGold() >= 100
							&& !inventory.hasItem(1, 1) && inventory.getSword() != 1) {
						npc.add("I've given you the money already... Now go talk to the blacksmith.");
					} else if (inventory.hasItem(1, 1) || inventory.getSword() == 1) {
						npc.add("That's a fine sword indeed, let's return to the tower.");
					}
				}

				else if (playerLocation.equalsIgnoreCase("HurlainBasement")) {
					if (playerLevel() == 1) {
						if (inventory.getSword() != 1) {
							npc.add("My apologies for the uncleanliness... I haven't been down here in years.");
							npc.add("Now for your final lesson. First equip that new sword you got.");
						} else {
							npc.add("Wonderful! You wield it well! Go examine that rat's nest for your first");
							npc.add("taste of combat! Remember your enemy is unpredictable so just go for it!");
						}
					} else if (playerLevel() > 1) {
						npc.add("Well done my squire! You have performed admirably!");
						npc.add("Meet me upstairs and we will say our final words.");
						loc[1] = 2;
						save();
					}
				} else {
					npc.add("I have nothing more to say to you...");
				}
			}
			else if(loc[1] == 2){
				if(loc[0] == 0 && playerLocation.equalsIgnoreCase("Hurlain")){
					npc.add("Well child, you have completed your training!");
					npc.add("Best you head home now and speak with your father, tell him the news.");
					npc.add("Unless you feel like killing more rats in my basement, which you are");
					npc.add("more than welcome to! You might want to stop by town and see if");
					npc.add("the merchants have anymore to offer you. Plus help yourself to these");
					npc.add("potions and this helmet to help you along the way. Take care!");
					npc.addRes("Thank you very much for everything Elder!");
					loc[0] = 1;
					inventory.addItem(4,1);
					inventory.addItem(13,3);
				}
				else{
					npc.add("Good luck in your journeying child.");
				}
			}
		}
		if (character.equalsIgnoreCase("Blacksmith")) {
			if (loc[1] == 1) {
				if (inventory.getGold() < 100) {
					npc.add("You'd better go speak with the Elder.");
					npc.addRes("Alright I'll go do that");
				} else {
					npc.add("Greetings, how can I assist you, lad?");
					npc.addRes("I'd like to purchase a sword.");

					npc.say();
					pause();

					npc.add("Alright then, that will be 100 coins.");
					npc.say();

					print("Would you like to make this purchase? Key 'y' for yes anything else will void transaction.");
					if (sc.next().equalsIgnoreCase("y")) {
						if (inventory.getGold() >= 100) {
							inventory.changeGold(-100);
							inventory.addItem(1, 1);
						} else {
							print("Sorry you don't have enough money for that purchase right now.");
						}
					}
					npc.add("I hope to do business again with you in the future!");
				}

			}
			else {
				Shop blacksmith = new Shop(0);
				blacksmith.shop();
				npc.add("Thank you for shopping! Please come again!");
			}
		}
		npc.say();
		pause();
		clearScreen(25);
		findRoom();
	}

	public static void Discovery(String type, String thing) {
		System.out.println("*********** " + type + " Discovered! : " + thing
				+ " ***********");

	}

	public static void listen() {
		Scanner scanner = new Scanner(System.in);
		String command = scanner.nextLine();

		clearScreen(30);
		customCommands(command);
	}

	public static void customCommands(String command) {

		if (command.equalsIgnoreCase("commands")) {
			print("Command			Purpose");
			space();
			print("use [object]		Uses the object referenced");
			print("talkto [person]	Talks to the person referenced");
			print("inv			Displays your inventory");
			print("equip        \t\tDisplays the equipment interface");
			print("pos			Displays information about current location");
			print("stats		\tDisplays information about the player");
			print("save			Saves the game for the player");
		}

		else if (command.startsWith("use")) {
			space();
			String[] obj = command.split(" ");
			if (obj.length > 1) {

				String object = command.substring(4);

				if (interest.toLowerCase().contains(object.toLowerCase())) {
					if (object.equalsIgnoreCase("Door")) {
						if (playerLocation.equalsIgnoreCase("MyBedroom")) {
							print("Opened my bedroom door.");
							changeRoom("HomeDownstairs");
						} else if (playerLocation
								.equalsIgnoreCase("Blacksmith")) {
							print("Opened door to Gylncove Square");
							changeRoom("GlyncoveSquare");
						} else if ((playerLocation
								.equalsIgnoreCase("HomeDownstairs") && loc[1] == 1)
								|| (playerLocation.equalsIgnoreCase("Hurlain") && loc[2] == 1 && loc[1] == 1)
								|| (playerLocation.equalsIgnoreCase("Hurlain") && loc[1] == 2 && loc[0] == 1)) {
							print("Opened door to outside.");
							changeRoom("Outside");
						} else {
							print("Either you cannot locate a door, or there is more to be done here.");
						}
					} else if (object.equalsIgnoreCase("Trail")) {
						changeRoom("Outside");
					} else if (object.equalsIgnoreCase("Ladder")) {
						if (playerLocation.equalsIgnoreCase("Hurlain") && (inventory.hasItem(1, 1)
								|| inventory.getSword() == 1 || loc[1] > 1) ) {
								changeRoom("HurlainBasement");
						} else if (playerLocation.equalsIgnoreCase("HurlainBasement")) {
							if (loc[1] == 2 && playerLevel() > 1) {
								print("You climb the ladder upstairs.");
								changeRoom("Hurlain");
							}
						} else {
							print("It's not the time to use that right now.");
						}
					} else if (object.equalsIgnoreCase("Blacksmith Shop")) {
						print("You proceed to the Blacksmith Shop");
						changeRoom("Blacksmith");
					} else if (object.equalsIgnoreCase("Rat Nest")) {
						if (loc[1] == 1 && inventory.getSword() != 1) {
							print("There is no use for this right now.");
						} else if ((loc[1] == 1 && inventory.getSword() == 1)
								|| loc[1] == 2) {
							print("Would you like to see a guide before the battle starts? (y or n)");
							String res = sc.next();

							if (res.charAt(0) == 'y' || res.charAt(0) == 'Y') {
								combatGuide();
							}
							combat = new Combat(1);
							if (combat.getWin()) {
								talk("Elder Hurlain");
							}
						}
					} else if(object.equalsIgnoreCase("Cave")){
						print("You search the cave");
						combat = new Combat(enemies());
						
					} else {
					
						print("Object : '" + object + "'  is not defined.");
					}
				} else {
					print("No " + object + " found at this location.");
				}
			} else {
				print("Invalid. Use as 'use (object)'. ");
			}
		}

		else if (command.startsWith("talkto")) {
			space();
			String[] person = command.split(" ");
			if (person.length == 2) {
				String p = "";
				if (interest.toLowerCase().contains(person[1].toLowerCase())) {
					if (person[1].equalsIgnoreCase("daddy")
							|| person[1].equalsIgnoreCase("dad")) {
						p = "Dad";
					}
					if (person[1].equalsIgnoreCase("Hurlain")) {
						p = "Elder Hurlain";
					}

					if (person[1].equalsIgnoreCase("Blacksmith")) {
						p = "Blacksmith";
					}

					if (!p.equalsIgnoreCase(""))
						talk(p);
					else
						print("No such person available.");
				} else {
					print("Person " + person[1]
							+ " not found at this location.");
				}
			}
		}

		else if (command.equalsIgnoreCase("position")
				|| command.equalsIgnoreCase("pos")) {
			space();
			findRoom();
		}

		else if (command.equalsIgnoreCase("equip")) {
			inventory.showInv();
			print("Choose the number of the item you would like to equip. (Left Column)");
			inventory.equip(sc.nextInt());
		}

		else if (command.equalsIgnoreCase("stats")) {
			print("Total experience: " + playerXp);
			print("Player Level: " + playerLevel());
			print("Experience till next level: " + (xpForLevel() - playerXp));
			print("Total number of enemies killed: " + enemiesDefeated);
			print("Total number of perfect blocks: " + perfectBlocks);
		}

		else if (command.equalsIgnoreCase("save")) {
			save();
			print("Saved for player " + playerName);
		}

		else if (command.startsWith("to") && onMap) {
			String place = command.substring(3);

			if (place.equalsIgnoreCase("Home") && loc[0] == 1) {
				print("You travel back home.");
				changeRoom("HomeDownstairs");
			} else if (place.equalsIgnoreCase("Hurlain's Tower") && loc[1] == 1) {
				print("You travel to Hurlain's Tower.");
				changeRoom("Hurlain");
			} else if (place.equalsIgnoreCase("Glyncove") && loc[2] == 1) {
				print("You travel to Glyncove");
				changeRoom("GlyncoveSquare");
			} else if(place.equalsIgnoreCase("Dead Man's Swamp") && loc[3] == 1){
				print("You venture to Dead Man's Swamp.");
				changeRoom("Swamp");
			}

			else {
				print("Place you designated:" + place);
				print("Invalid location. Use format 'to &&&&&&&'  &&&&&-> Place exactly as on map.");
			}
		}

		else if (command.equalsIgnoreCase("inv")) {
			inventory.showInv();
		}

		else {
			print("Invalid code. Type 'commands' to view acceptable commands.");
		}
		listen();
	}

	public static void combatGuide() {
		System.out
				.println("Combat is determined in a turn-by-turn manner. The player and computer will");
		System.out
				.println("each choose a random letter. And if the letters are within a range (determined");
		System.out
				.println("by level difference) then an attack can be blocked. If they are too far apart,");
		System.out
				.println("then the attacker deals his damage. If the two letters are the same, then the");
		System.out
				.println("defender deals counter attack damage. The process repeats until one of the two");
		System.out
				.println("combatants are dead. Various armors and weapons contribute to your overall");
		System.out
				.println("damage. Typing 'item' when battling allows you to drink potions you buy at");
		System.out
				.println("the general store, But you cannot attack or defend that turn.");
		pause();
	}
	
	public static int enemies(){
		int enemy;
		if(playerLocation.equalsIgnoreCase("Swamp")){
			int[] swamp = {2,3,4};
			enemy = swamp[(int) (Math.random() * swamp.length)];
		}
		else {
			enemy = 1;
		}
		return enemy;
	}

}
