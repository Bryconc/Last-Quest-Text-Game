
public class Item {
	private int id;
	private int type;
	private String name;
	private String description;
	private int value;
	
	public Item(){
		id = 0;
	    name = "";
	    description = "";	
	    type = 0;
	    value = 0;
	}
	
	public Item(int i){
		id = i;
		name = determineName();
		description = determineDes();
		type = itemType();
		value = determineValue();
	}
	
	private String determineName(){
		switch(id){
		case 1:
			return "Bronze Sword";
		case 2:
			return "Iron Sword";
		case 3:
			return "Steel Sword";
		case 4:
			return "Bronze Helmet";
		case 5:
			return "Iron Helmet";
		case 6:
			return "Steel Helmet";
		case 7:
			return "Bronze Platebody";
		case 8:
			return "Iron Platebody";
		case 9:
			return "Steel Platebody";
		case 10:
			return "Bronze Platelegs";
		case 11:
			return "Iron Platelegs";
		case 12:
			return "Steel Platelegs";
		case 13:
			return "Health Elixir";
			
		default:
			return "";
		}
	}
	
	private String determineDes(){
		switch(id){
		case 1:
			return "A solid bronze sword";
		case 2:
			return "A well crafted iron sword";
		case 3:
			return "A finely made steel sword";
		case 4:
			return "A sturdy helmet of bronze";
		case 5:
			return "A well crafted iron helmet";
		case 6:
			return "A beautifully made helmet of steel";
		case 7:
			return "A solid bronze platebody";
		case 8:
			return "A well crafted iron platebody";
		case 9:
			return "An impenentrable body of steel.";
		case 10:
			return "A pair of solid bronze platelegs";
		case 11:
			return "A well crafted set of iron platelegs";
		case 12:
			return "An set of radiant steel platelegs.";
		case 13:
			return "Drinking this during conflict will heal you.";
			
		default:
			return "";
		}
	}
	
	private int determineValue(){
		switch(id){
		case 1:
			return 100;
		case 2:
			return 200;
		case 3:
			return 500;
		case 4:
			return 150;
		case 5:
			return 250;
		case 6:
			return 600;
		case 7:
			return 200;
		case 8:
			return 400;
		case 9:
			return 800;
		case 10:
			return 175;
		case 11:
			return 375;
		case 12:
			return 700;
		case 13:
			return 50;
			
		default:
			return 0;
		}
	}
	
	public int getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDes(){
		return description;
	}
	
	public int getType(){
		return type;
	}
	
	public int getValue(){
		return value;
	}
	
	public int[] sword = {1,2,3};
	public int[] helm = {4,5,6};
	public int[] plate = {7,8,9};
	public int[] legs = {10,11,12};
	
	private int itemType(){
		for(int i = 0; i < sword.length; i++){
			if(sword[i] == id){
				return 1;
			}
		}
		for(int i = 0; i < helm.length; i++){
			if(helm[i] == id){
				return 2;
			}
		}
		for(int i = 0; i < plate.length; i++){
			if(plate[i] == id){
				return 3;
			}
		}
		for(int i = 0; i < legs.length; i++){
			if(legs[i] == id){
				return 4;
			}
		}
		return 0;
	}
}
