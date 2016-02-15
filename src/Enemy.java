
public class Enemy {
	private int hitpoints, originalHP, level, id;
	private int xpReward;
	private String name;
	
	public Enemy(){
		id = 0;
		name = "";
		hitpoints = 0;
		originalHP = 0;
		level = 0;
		xpReward = 0;
	}
	
	public Enemy(int i){
		id = i;
		name = determineName();
		level = determineLevel();
		hitpoints = level * 5;
		originalHP = hitpoints;
		xpReward = hitpoints *15;
	}
	
	public String determineName(){
		switch(id){
		case 1:
			return "Rat";
		case 2:
			return "Giant Rat";
		case 3:
			return "Skeleton";
		case 4:
			return "Undead";
		default:
			return "NoName";
		}
	}
	
	public int determineLevel(){
		switch(id){
		case 1:
			return 2;
		case 2:
			return 5;
		case 3:
			return 7;
		case 4:
			return 9;
		default:
			return 0;
		}
	}
	
	public void damage(int h){
		hitpoints -= h;
	}
	
	public int maxHit(){
		return ((hitpoints) / 10);
	}
	
	public String getName(){
		return name;
	}
	
	public int getHP(){
		return hitpoints;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getXp(){
		return xpReward;
	}
	
	public int HP(){
		return originalHP;
	}
	
	public char getRandom(){
		int hit = (int)(Math.random() * 26);
		return (char) (122-hit);
	}
	
	public String healthBar(){
		double percentage = (double) hitpoints/originalHP * 100;
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
}
