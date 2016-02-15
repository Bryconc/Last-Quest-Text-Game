
public class Inventory {
	private int[] equip = new int[4];
	private int[] item = new int[20];
	private int[] amount = new int[20];
	private int gold;
	
	public Inventory(){
		for(int i = 0; i < 20; i++){
			item[i] = 0;
			amount[i] = 0;
		}
		for(int i = 0; i < 4; i++){
			equip[i] = 0;
		}
		
		gold = 0;
	}
	
	public int getGold(){
		return gold;
	}
	
	public void setGold(int g){
		gold = g;
	}
	
	public int getItem(int i){
		return item[i];
	}
	
	public int getAmount(int i){
		return amount[i];
	}
	
	public void setEquipment(int i1, int i2, int i3, int i4){
		equip[0] = i1;
		equip[1] = i2;
		equip[2] = i3;
		equip[3] = i4;
	}
	
	public int getSword(){
		return equip[0];
	}
	
	public int getHelm(){
		return equip[1];
	}
	
	public int getPlate(){
		return equip[2];
	}
	
	public int getLegs(){
		return equip[3];
	}
	
	public boolean hasItem(int id, int a){
		for(int i = 0; i < 20; i++){
			if(item[i] == id && amount[i] >= a){
					return true;
			}
		}
		return false;
	}
	
	public void changeGold(int g){
		if(g > 0)
			System.out.println("******* Gained "+g+" Gold *******");	
		if(g<0)
			System.out.println("******* Lost "+(g*-1)+" Gold ********");
			
		gold += g;
		Source.save();
	}
	
	public void addItem(int id, int n){
		boolean finished = false;
		
		for(int i = 0; i < 20; i++){
			if(item[i] == id && !finished){
				amount[i] += n;
				finished = true;
			}
		}
		
		for(int i = 0; i < 20; i++){
			if(item[i] == 0 && !finished){
				item[i] = id;
				amount[i] = n;
				finished = true;
			}
		}
		
		Source.save();
	}
	
	public void removeItem(int id, int n){
		
		for(int i = 0; i < 20; i++){
			if(item[i] == id){
				amount[i] -= n;
				if(amount[i] == 0){
					item[i] = 0;
				}
			}
		}
		Source.save();
	}
	
	public void equip(int slot){
		if(slot <= 0 || slot > 20){
			System.out.println("Invalid slot! Slot designation changed to 1");
			slot = 1;
		}
		
		if(item[slot-1] == 0){
			System.out.println("Nothing exists in that slot.");
		}
		else {
			Item i = new Item(item[slot-1]);
		
			if(i.getType() > 0){
				equip[i.getType() - 1] = item[slot-1];
				removeItem(item[slot-1], 1);
				System.out.println(i.getName()+" Equiped!");
			}
		
			else
				System.out.println("This item cannot be equiped.");
		}
	}

	public void showInv() {
		String goldLine = "| Player Gold: "+gold;
		String swordLine = "| Equiped: Sword: "+sword().getName();
		String helmLine =  "|          Helmet: "+helm().getName();
		String plateLine = "|          Plate: "+plate().getName();
		String legsLine =  "|          Legs: "+legs().getName();
		System.out.print("________________________________________________________________________________");
		
		System.out.print(swordLine);
		for(int i = 0; i < (79- swordLine.length()); i++){
			System.out.print(" ");
		}
		System.out.print("|");
		
		System.out.print(helmLine);
		for(int i = 0; i < (79- helmLine.length()); i++){
			System.out.print(" ");
		}
		System.out.print("|");
		System.out.print(plateLine);
		for(int i = 0; i < (79- plateLine.length()); i++){
			System.out.print(" ");
		}
		System.out.print("|");
		System.out.print(legsLine);
		for(int i = 0; i < (79- legsLine.length()); i++){
			System.out.print(" ");
		}
		System.out.print("|");
		
		System.out.print("|                               Inventory:                                     |");
		System.out.print(goldLine);
		for(int i = 0; i < (79 - goldLine.length()); i++){
			System.out.print(" ");
		}
		System.out.print("|");
		
		System.out.print("|******************************************************************************|");
		System.out.print("|           Item   : Amount      Description                                   |");
		
		for(int i = 0; i < 20; i++){
			Item a = new Item(item[i]);
			String line = "|    "+(i+1)+".    "+a.getName()+" : "+amount[i]+" "+a.getDes();
			System.out.print(line);
			for(int b = 0; b < 79-line.length(); b++){
				System.out.print(" ");
			}
			System.out.print("|");
		}
		
		System.out.print("--------------------------------------------------------------------------------");
		
	}
	public Item sword(){
		Item sword = new Item (equip[0]);
		return sword;
	}
	public Item helm(){
		Item helm = new Item (equip[1]);
		return helm;
	}
	public Item plate(){
		Item plate = new Item (equip[2]);
		return plate;
	}
	public Item legs(){
		Item legs = new Item (equip[3]);
		return legs;
	}
}
