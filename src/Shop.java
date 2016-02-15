import java.util.Scanner;

public class Shop {
	
	private Item[] items;
	private int[] amount;
	private Scanner sc = new Scanner(System.in);
	
	private int id;
	
	public Shop(){
		for(int i = 0; i < 20; i++){
			items[i] = new Item(0);
		}
	}
	
	public Shop(int i){
		id = i;
		setupShop();
	}
	
	private void setupShop(){
		if(id == 0){
			int[] merc = {1,2,3,4,5,6,7,8,9,10,11,12,13};
			
			items = new Item[merc.length];
			amount = new int[merc.length];
			
			for(int i = 0; i < merc.length; i++){
				items[i] = new Item(merc[i]);
				amount[i] = 20;
			}
		}
	}
	
	public void shop(){
		String goldLine = "| Player Gold: "+Game.inventory.getGold();
		System.out.print("________________________________________________________________________________");		
		System.out.print("|                                  Shop:                                       |");
		System.out.print(goldLine);
		for(int i = 0; i < (79 - goldLine.length()); i++){
			System.out.print(" ");
		}
		System.out.print("|");
		
		System.out.print("|******************************************************************************|");
		System.out.print("| $$$       Item   : Amount      Description                                   |");
		
		for(int i = 0; i < items.length; i++){
			String line = "|"+items[i].getValue()+"   "+(i+1)+".    "+items[i].getName()+" : "+amount[i]+" "+items[i].getDes();
			System.out.print(line);
			for(int b = 0; b < 79-line.length(); b++){
				System.out.print(" ");
			}
			System.out.print("|");
		}
		
		System.out.print("--------------------------------------------------------------------------------");
		
		System.out.println("\nType the slot number of the item you would like to buy, or 0 to exit the shop.");
		int res = sc.nextInt();
		
		if (res < 0 || res > 20){
			System.out.println("Invalid slot! Closing shop!");
			res = 0;
		}
		if(res != 0){
			res --;
			System.out.println("How many "+items[res].getName()+ "s would you like to buy?");
		
			int am = sc.nextInt();
			if(am < 0 || am > 20){
				System.out.println("\nYou can't buy that many! Amount set to 1.");
				am = 1;
			}
			int value = am * items[res].getValue();
			if(value <= Game.inventory.getGold()){
			
				System.out.println("\nYour total would be $"+value+". Type 'y' to complete purchase.");
			
				if(sc.next().equalsIgnoreCase("y")){
					Game.inventory.changeGold(-1*value);
					Game.inventory.addItem(items[res].getID(),am);
					amount[res] -= am;
					if(amount[res] == 0){
						items[res] = new Item(0);
					}
				}
			}
			else {
				System.out.println("Insufficent funds!");
				Source.pause();
			}
			Source.save();
		}
	}
}
