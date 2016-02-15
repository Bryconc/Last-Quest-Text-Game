import java.util.Scanner;

public class Combat {
	private int playerHP, originalHP, playerMaxHit, range, playerLevel;
	private double damageModifier, resistance;
	private boolean victory;
	private Enemy enemy;
	private Scanner sc = new Scanner(System.in);
	
	
	public Combat(){
		enemy = null;
		playerHP = 0;
		originalHP = playerHP;
		playerMaxHit = 0;
		range = 0;
		
		damageModifier = 0;
		resistance = 0;
		victory = false;
	}
	
	public Combat(int id){
		enemy = new Enemy(id);
		
		playerLevel = Game.playerLevel();
		playerHP = 10 + (Game.playerLevel() * 2);
		originalHP = playerHP;
		playerMaxHit = playerHP / 10;
		damageModifier = getDamage();
		resistance = getResistance();
		
		
		range = 4 +(playerLevel - enemy.getLevel())/5;
		victory = false;
		begin();
	}
	
	public boolean getWin(){
		return victory;
	}
	
	public void begin(){
		System.out.print("********************************************************************************");
		System.out.print("********************************************************************************");
		System.out.print("********************************************************************************");
		System.out.print("********************************************************************************");
		System.out.print("*********** *~            ____        _   _   _                  ~*  ***********");
		System.out.print("***********   `          |  _ \\      | | | | | |                '    ***********");
		System.out.print("***********              | |_) | __ _| |_| |_| | ___                 ***********");
		System.out.print("***********              |  _ < / _` | __| __| |/ _ \\                ***********");
		System.out.print("***********              | |_) | (_| | |_| |_| |  __/                ***********");
		System.out.print("***********              |____/ \\__,_|\\__|\\__|_|\\___|                ***********");
		System.out.print("*********** *~                                                   ~*  ***********");
		System.out.print("********************************************************************************");
		System.out.print("********************************************************************************");
		System.out.print("********************************************************************************");
		fight();
	}
	
	public void fight(){
		boolean attacking = false;
		
		if(Math.random() > .5){
			attacking = true;
			System.out.println("\nYour speed allows you to attack first.\n");
		}
		
		while(playerHP > 0 && enemy.getHP() > 0){
			System.out.println("Player:\t\tEnemy ("+enemy.getName()+"):");
			System.out.println("Level: " +playerLevel+ "\tLevel: "+enemy.getLevel());
			System.out.println("HP: " +playerHP+ "\t\tHP: "+enemy.getHP());
			System.out.println("\n"+healthBar()+"\t\t"+enemy.healthBar()+"\n");
			
			if(attacking){
				System.out.println("Your move!");
				System.out.println("Type a random letter (a-z)! Then hit enter.");
				System.out.println("Due to level difference range between attack and defense can be "+range);
				
				String res = sc.nextLine();
				char enemyDefense = enemy.getRandom();
				
				if(res.equalsIgnoreCase("item")){
					item();
					res = ""+enemyDefense;
					System.out.println("Could not attack this turn.");
				}
				
				if(res.length() == 0){
					res = " ";
				}
				
				char hit = res.toLowerCase().charAt(0);
				
				if(hit < 'a' || hit > 'z'){
					hit = enemyDefense;
					System.out.println("Invalid entry. Enemy given free hit!");
				}
				
				
				System.out.println("\nYour attack: "+hit);
				System.out.println("Enemy's defense:"+enemyDefense);
				
				if(Math.abs(enemyDefense - hit) > range){
					System.out.println("***** Attack successful *****");
					playerHit();
				}
				else {
					System.out.println("***** Enemy Defense Successful *****");
					if(enemyDefense == hit){
						System.out.println("Enemy predicted your move PERFECTLY. Counter Attack Damage!");
						playerDamage();
					}
					System.out.println("As a result no damage was dealt");
				}
				attacking = false;
			}
			
			else if(!attacking){
				System.out.println("\nEnemy is making attack. Time to make your defense");
				System.out.println("Type a random letter (a-z)! Then hit enter.");
				System.out.println("Due to level difference range between attack and defense can be "+range);
				
				char enemyAttack = enemy.getRandom();
				
				String res = sc.nextLine();
				
				if(res.equalsIgnoreCase("item")){
					item();
					res = " ";
					System.out.println("Unable to defend this turn.");
				}
				
				if(res.length() == 0){
					res = " ";
				}
				
				char defense = res.charAt(0);
				
				if(defense < 'a' || defense > 'z'){
					defense = 0;
					System.out.println("Invalid entry. Enemy given free hit!");
				}
				
				System.out.println("\nYour defense: "+defense);
				System.out.println("Enemy's attack:"+enemyAttack);
				
				if(Math.abs(enemyAttack - defense) > range ){
					System.out.println("***** Enemy attack successful *****");
					playerDamage();
				}
				else {
					System.out.println("***** Defense Successful *****");
					if(defense == enemyAttack){
						System.out.println("You predicted the enemy's attack PERFECTLY. Counter Attack Damage!");
						Game.perfectBlocks++;
						playerHit();
					}
					System.out.println("As a result no damage was dealt");
				}
				attacking = true;
			}
		}
		if(playerHP <= 0){
			System.out.println("Player died.");
			Source.pause();
			Game.findRoom();
		}
		else if(enemy.getHP() <= 0){
			System.out.println("Enemy died.");
			rewards();
		}
	}
	
	public void playerDamage() {
		int hit = (int)(((Math.random() * enemy.maxHit()) +1) * resistance);
		if(Math.random() < .15){
			hit *=2;
			System.out.println("****************Critical Hit! Double Damage!****************");
		}
		if(hit == 0){
			hit = 1;
		}
		playerHP -= hit;
		System.out.println("Enemy dealt "+hit+" damage to player");
		
	}

	public void playerHit(){
		int hit = (int)(((Math.random() * playerMaxHit)+1) * damageModifier);
		if(Math.random() < .25){
			hit *=2;
			System.out.println("****************Critical Hit! Double Damage!****************");
		}
		enemy.damage(hit);
		System.out.println("Player dealt "+hit+" damage to enemy.");
	}
	
	private double getDamage(){
		double damage = 1.0;
		
		if(Game.inventory.getSword() == 1){
			damage += .2;			
		}
		if(Game.inventory.getSword() == 2){
			damage += .4;			
		}
		if(Game.inventory.getSword() == 3){
			damage += .6;			
		}
		
		return damage;
	}
	
	private double getResistance(){
		double res = 1;
		
		if(Game.inventory.getHelm() == 4){
			res -= .1;			
		}
		
		if(Game.inventory.getHelm() == 5){
			res -= .15;			
		}
		
		if(Game.inventory.getHelm() == 6){
			res -= .2;			
		}
		
		if(Game.inventory.getPlate() == 7){
			res -= .1;			
		}
		
		if(Game.inventory.getPlate() == 8){
			res -= .15;			
		}
		
		if(Game.inventory.getPlate() == 9){
			res -= .2;
		}
		if(Game.inventory.getLegs() == 10){
			res -= .1;			
		}
		
		if(Game.inventory.getLegs() == 11){
			res -= .15;			
		}
		
		if(Game.inventory.getLegs() == 12){
			res -= .2;			
		}
		
		return res;
	}
	
	private void rewards(){
		int coins = (int)(Math.random() * enemy.HP()*10) + 1;
		String enemyLine = "|  Enemy Defeated: "+enemy.getName();
		String coinLine = "|  Coins Found: "+coins;
		System.out.print("--------------------------------------------------------------------------------");
		System.out.print("|                 __      ___      _                                           |");
		System.out.print("|                 \\ \\    / (_)    | |                                          |");
		System.out.print("|                  \\ \\  / / _  ___| |_ ___  _ __ _   _                         |");
		System.out.print("|                   \\ \\/ / | |/ __| __/ _ \\| '__| | | |                        |");
		System.out.print("|                    \\  /  | | (__| || (_) | |  | |_| |                        |");
		System.out.print("|                     \\/   |_|\\___|\\__\\___/|_|   \\__, |                        |");
		System.out.print("|                                                 __/ |                        |");
		System.out.print("|                                                |___/                         |");
		System.out.print("|                                                                              |");
		System.out.print("|                                                    \\|||||/                   |");
		System.out.print("|                                                   ( ~   ~ )                  |");
		System.out.print("|                                                  @( 0   0 )@                 |");
		System.out.print("|                                                   (   C   )                  |");
		System.out.print("|                                                    \\ \\_/ /                   |");
		System.out.print("|                                                     |___|                    |");
		System.out.print("|                                                      | |                     |");
		System.out.print("|                                                                              |");
		System.out.print("|                                                                              |");
		System.out.print(enemyLine);
		for(int i = 0; i < (79-enemyLine.length()); i++){
			System.out.print(" ");
		}
		System.out.print("|");
		
		System.out.print(coinLine);
		for(int i = 0; i < (79-coinLine.length()); i++){
			System.out.print(" ");
		}
		System.out.print("|");
		System.out.print("|                                                                              |");
		System.out.print("|                                                                              |");
		System.out.print("--------------------------------------------------------------------------------");
		victory = true;
		Source.pause();
		Game.enemiesDefeated++;
		Game.inventory.changeGold(coins);
		Game.addXp(enemy.getXp()/(Game.playerLevel()));
	}
	
	public String healthBar(){
		double percentage = ((double) playerHP/originalHP) * 100;
		if(percentage > 90){
			return "[++++++++++]";
		}
		if(percentage > 80){
			return "[+++++++++ ]";
		}
		if(percentage > 70){
			return "[++++++++  ]";
		}
		if(percentage > 60){
			return "[+++++++   ]";
		}
		if(percentage > 50){
			return "[++++++    ]";
		}
		if(percentage > 40){
			return "[+++++     ]";
		}
		if(percentage > 30){
			return "[++++      ]";
		}
		if(percentage > 20){
			return "[+++       ]";
		}
		if(percentage > 10){
			return "[++        ]";
		}
		if (percentage > 0) {
			return "[+         ]";
		}
		else {
			return "[          ]";
		}
	}
	
	public void item(){
		Game.inventory.showInv();
		System.out.println("Key the slot of the health potion to use.");
		
		int slot = sc.nextInt();sc.nextLine();
		
		if(Game.inventory.getItem(slot-1) == 13){
			Game.inventory.removeItem(13,1);
			int heal = originalHP - playerHP;
			playerHP += heal;
			System.out.println("Health potion healed you "+heal+" hitpoints.");
		}
	}
	
	
}
