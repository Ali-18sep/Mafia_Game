package Assignment3;

import java.util.Random;
import java.util.Scanner;

public abstract class players {

	protected static int player_alive;

	private String role;
	private int HP;
	private int player_ID;
	private boolean killed;
	
	

	public boolean isKilled() {
		return killed;
	}
	public void setKilled(boolean killed) {
		this.killed = killed;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	public int getPlayer_ID() {
		return player_ID;
	}
	public void setPlayer_ID(int player_ID) {
		this.player_ID = player_ID;
	}

	public static int getPlayer_alive() {
		return player_alive;
	}
	public static void setPlayer_alive(int player_alive) {
		players.player_alive = player_alive;
	}


	public abstract void gamePlay(int yx);
	public abstract void winnerCall();
	//public abstract void manageHP(int x, int a, int hh);
	
	public static void voting(int nnn) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		Random random = new Random(); 
		int[] arr = new int[mafia_runner.nn+1];

		for(int o=0; o<mafia_runner.kp.size(); o++) {
			if(mafia_runner.kp.get(o).getPlayer_ID()==nnn && mafia_runner.kp.get(o).isKilled()==false) {
				System.out.println("Select a person to vote out: ");
				int v = sc.nextInt();

				while(mafia_runner.isAlive[v]==false) {
					System.out.println("This player is already died");
					System.out.print("choose other player: ");
					v = sc.nextInt();
				}

				arr[v] = arr[v]+1;
			}
		}
		if(player_alive!=0) {
			int ss = random.nextInt(mafia_runner.nn+1);
			for(int i=0; i<players.getPlayer_alive()-1; i++) {
				
				while(mafia_runner.isAlive[ss]==false) {
					ss = random.nextInt(mafia_runner.nn+1);
				}
				arr[ss]=arr[ss]+1;
			}
		}

		int max = Integer.MIN_VALUE;
		//int count = 0;
		int ans = Integer.MIN_VALUE;

		for(int j=0; j<arr.length; j++) {
			if(arr[j]>=max) {
				max = arr[j];
				ans = j;
			}
		}

		

		//else {
		for(int i=0; i<mafia_runner.kp.size(); i++) {
			if(mafia_runner.kp.get(i).getPlayer_ID()==ans && mafia_runner.kp.get(i).isKilled()==false){
				System.out.println("Player"+mafia_runner.kp.get(i).getPlayer_ID()+" "+"is voted out");
				mafia_runner.kp.get(i).setKilled(true);
				mafia_runner.isAlive[ans] = false;
				mafia_runner.isPrinted[mafia_runner.kp.get(i).getPlayer_ID()]=true;

				if(mafia_runner.kp.get(i).getRole()=="MAFIA"){
					mafia.number_of_alive_mafia--;
					//players.setPlayer_alive(player_alive);
					players.player_alive--;

					mafia_runner.isAlive[ans] = false;
					mafia_runner.isPrinted[mafia_runner.kp.get(i).getPlayer_ID()]=true;
				}

				else if(mafia_runner.kp.get(i).getRole()=="DETECTIVE"){
					detective.total_detectiveAlive--;
					players.player_alive--;
					mafia_runner.isAlive[ans] = false;
					mafia_runner.isPrinted[mafia_runner.kp.get(i).getPlayer_ID()]=true;
				}

				else if(mafia_runner.kp.get(i).getRole()=="HEALER") {
					healer.total_healerAlive--;
					players.player_alive--;
					mafia_runner.isAlive[ans] = false;
					mafia_runner.isPrinted[mafia_runner.kp.get(i).getPlayer_ID()]=true;
				}

				else if(mafia_runner.kp.get(i).getRole()=="COMMONERS") {
					commoners.total_commonersAlive--;
					players.player_alive--;
					mafia_runner.isPrinted[mafia_runner.kp.get(i).getPlayer_ID()]=true;
					mafia_runner.isAlive[ans] = false;
				}			
			}
		}

	}
	
	public static void printData() {

		for(int i=0; i<mafia.getM().size(); i++) {
			System.out.print("Player"+mafia.getM().get(i).getPlayer_ID()+","+" ");
		}
		System.out.println("were mafias.");

		for(int i=0; i<detective.getD().size(); i++) {
			System.out.print("Player"+detective.getD().get(i).getPlayer_ID()+","+" ");
		}
		System.out.println("were detectives.");

		int count = 0;
		for(int i=0; i < healer.getH().size(); i++) {
			count+=1;
			System.out.print("Player"+healer.getH().get(i).getPlayer_ID()+","+" ");
		}
		if(count<=1) {
			System.out.println("was healer.");
		}
		else{
			System.out.println("were healer.");
		}
		for(int i=0; i<commoners.getC().size(); i++) {
			System.out.print("Player"+commoners.getC().get(i).getPlayer_ID()+","+" ");
		}
		System.out.println("were commoners.");
	}





}
