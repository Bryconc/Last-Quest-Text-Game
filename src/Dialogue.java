public class Dialogue{
	
	private int emotion;
	
	private String name;
	
	private String[] response = new String[100];
	private String[] dialogue =  new String[100];
	 
	public Dialogue(){
		name = "";
		emotion = 0;
		blank();
	}
	
	public Dialogue(String n){
		name = n;
		emotion = 0;
		blank();
	}
	
	public String getName(){
		return name;
	}
	
	public void setEmotion(int e){
		emotion = e;
	}
	
	public void add(String n){
		boolean finished = false;
		
		for(int i = 0; i < dialogue.length; i++){
			if(dialogue[i].equals("None") && !finished){
				dialogue[i] = n;
				finished = true;
			}
		}
	}
	
	public void addRes(String s){
		boolean finished = false;
		
		for(int i = 0; i < response.length; i++){
			if(response[i].equals("None") && !finished){
				response[i] = s;
				finished = true;
			}
		}
	}
	
	public void say(){
		System.out.print("--------------------------------------------------------------------------------");
		String first = "|"+name+": ";
		System.out.print(first);
		for(int i = 0; i < (79 - first.length()); i++){
			System.out.print(" ");
		}
		System.out.print("|");
		for(int i = 0; i < dialogue.length; i++){
			if(!dialogue[i].equals("None")){
				System.out.print("|"+dialogue[i]);
				for(int a = 0; a < (78-(dialogue[i].length())); a++){
					System.out.print(" ");
				}
				System.out.print("|");
			}	
		}
		System.out.print("--------------------------------------------------------------------------------");
		
		face();
		sayRes();	
	}
	
	private void sayRes(){
		System.out.println("\n\n");
		for(int i = 0; i < dialogue.length; i++){
			if(!response[i].equals("None")){
				System.out.println(Game.playerName+":    "+response[i]);
			}
		}
		blank();
	}
	
	private void blank(){
		for(int i = 0; i < dialogue.length; i++){
			dialogue[i] = "None";
		}
		
		for(int i = 0; i < response.length; i++){
			response[i] = "None";
		}
		
		emotion = 0;
	}
	
	public void face(){
		if(emotion == 0){//default
			System.out.println("                        \\_     \\_          ___________");
			System.out.println("                          \\_     \\        /           \\");
			System.out.println("                            \\_    \\_     |    -   -    |");
			System.out.println("                              \\_    \\____|      o      |");
			System.out.println("                                \\________|    _____    |");
			System.out.println("                                         |   |_____|   |");
			System.out.println("                                          \\___________/");
		}
		if(emotion == 1){//angry
			System.out.println("                        \\_     \\_          ___(___)___");
			System.out.println("                          \\_     \\        /           \\");
			System.out.println("                            \\_    \\_     |    \\   /    |");
			System.out.println("                              \\_    \\____|      o      |");
			System.out.println("                                \\________|    _____    |");
			System.out.println("                                         |   |_____|   |");
			System.out.println("                                          \\___________/");
		}
		
		if(emotion == 2){//Old-Wizened
			System.out.println("                        \\_     \\_          ___________");
			System.out.println("                          \\_     \\        /  --   --  \\");
			System.out.println("                            \\_    \\_     |    -   -    |");
			System.out.println("                              \\_    \\____|      o      |");
			System.out.println("                                \\________|  @@@@@@@@@  |");
			System.out.println("                                         |  @|_____|@  |");
			System.out.println("                                          \\_\\@@@@@@@/_/");
			System.out.println("                                             \\@@@@@/");
			System.out.println("                                              \\@@@/");
			System.out.println("                                               \\@/");
			
		}
		
		if(emotion == 3){ //Distraught
			System.out.println("                        \\_     \\_          ___________");
			System.out.println("                          \\_     \\        /  /    \\   \\");
			System.out.println("                            \\_    \\_     |    =   =    |");
			System.out.println("                              \\_    \\____|      o      |");
			System.out.println("                                \\________|    _____    |");
			System.out.println("                                         |   |_____|   |");
			System.out.println("                                          \\___________/");
		}
	}
	
	
	
	
}