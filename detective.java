package Assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class detective extends players {

	Scanner sc = new Scanner(System.in);

	private static ArrayList<detective> d = new ArrayList<>();
	private static boolean[] isDetective = new boolean[mafia_runner.nn+1];
	protected static int total_detectiveAlive;
	private int userID;

	public detective(int i, int random_int, String string) {
		this.setHP(i);
		this.setPlayer_ID(random_int);
		this.setRole(string);
		this.setKilled(false);
		d.add(this);
		isDetective[random_int] = true;
		total_detectiveAlive++;
		mafia_runner.kp.add(this);
	}

	public detective() {

	}

	public static int gettotal_detectiveAlive() {
		return total_detectiveAlive;
	}
	public static void setTotalDetective() {

	}

	public static boolean[] getIsDetective() {
		return isDetective;
	}


	public static ArrayList<detective> getD() {
		return d;
	}

	@Override
	public void gamePlay(int id) {
		userID = id;
		Random random = new Random();
		int num = 1;

		while(true) {


			if(mafia.number_of_alive_mafia==players.getPlayer_alive()-mafia.number_of_alive_mafia) {
				break;
			}
			if(mafia.number_of_alive_mafia==0) {
				break;
			}

			System.out.println("Round"+num);

			System.out.print(players.getPlayer_alive()+" "+"players are remaining:"+" ");
			for(int i=0; i<mafia_runner.kp.size(); i++) {
				if(mafia_runner.kp.get(i).isKilled()==false) {
					System.out.print("Player"+mafia_runner.kp.get(i).getPlayer_ID()+","+" ");
				}
			}
			System.out.println("are alive");

			// MAFIA IS CHOOSING THE PLAYER--------------
			int mafia_target=0;

			if(mafia.getNumber_of_alive_mafia()!=0) {


				mafia_target = random.nextInt(mafia_runner.nn+1);

				while(mafia_runner.isAlive[mafia_target]==false && mafia.isMafia[mafia_target]==true) {
					mafia_target = random.nextInt(mafia_runner.nn+1);
				}
				System.out.println("Mafias have choosen their target.");

			}
			// MAFIA WORK END  --------------------------

			// DETECTIVE WORK START -------------
			int detect=0;
			
			for(int o=0; o<mafia_runner.kp.size(); o++) {

				if(mafia_runner.kp.get(o).getPlayer_ID()==userID && mafia_runner.kp.get(o).isKilled()==false) {
					System.out.print("Choose a player to test: ");
					detect = sc.nextInt();
					while(isDetective[detect]==true || mafia_runner.isAlive[detect]==false ) {
						if(isDetective[detect]==true) {
							System.out.print("You cannot test a detective."+" ");
							System.out.print("Choose a player to test: ");
							detect = sc.nextInt();
						}

						if(mafia_runner.isAlive[detect]==false) {
							System.out.print("This player has already died or voted out. Choose other player: ");
							detect = sc.nextInt();
						}
					}	
				}
				else if(detective.total_detectiveAlive!=0 && mafia_runner.kp.get(o).isKilled()==true ) {
					
					while(isDetective[detect]==true || mafia_runner.isAlive[detect]==false ) {
							detect = random.nextInt(mafia_runner.nn+1);
					}	
					
					
				}
			}

			for(int i=0; i<mafia_runner.kp.size(); i++) {
				if(mafia_runner.kp.get(i).getPlayer_ID()==detect && mafia_runner.kp.get(i).getRole().equals("MAFIA")) {
					System.out.println("Player"+mafia_runner.kp.get(i).getPlayer_ID()+" "+"is a mafia");
				}

				else if(mafia_runner.kp.get(i).getPlayer_ID()==detect && !mafia_runner.kp.get(i).getRole().equals("MAFIA")){
					System.out.println("Player"+mafia_runner.kp.get(i).getPlayer_ID()+" "+"is not a mafia");
				}
			}


			// END OF DETECTIVE --------------------


			// HEALER WORK STARTED -----------------
			int heal=0;
			if(healer.total_healerAlive!=0) {
				heal = random.nextInt(mafia_runner.nn+1);

				while(mafia_runner.isAlive[heal]==false) {
					heal = random.nextInt(mafia_runner.nn+1);
				}

				for(int i=0; i<mafia_runner.kp.size(); i++) {
					if(mafia_runner.kp.get(i).getPlayer_ID()==heal) {
						int currentHP = mafia_runner.kp.get(i).getHP();
						mafia_runner.kp.get(i).setHP(currentHP+500);
					}
				}

			}
			System.out.println("Healers have chosen someone to heal.");
			System.out.println("----END of actions----");

			// HEALER WORK END FOR NOW

			int totl_mafiaHP = 0;
			for(int j=0; j<mafia.getM().size(); j++) {
				int nu = mafia.getM().get(j).getHP();
				totl_mafiaHP+=nu;
			}

			int totl_targetHP=0;
			for(int x=0; x<mafia_runner.kp.size(); x++) {
				if(mafia_runner.kp.get(x).getPlayer_ID()==heal) {
					int xy = mafia_runner.kp.get(x).getHP();
					totl_targetHP+=xy;
				}
			}

			Collections.sort(mafia.getM(), new comparator()); 
			int xyz = 0;
			if(mafia.number_of_alive_mafia!=0) {
				//totl_mafiaHP=0;
				for(int i=0; i<mafia.getM().size(); i++) {
					totl_mafiaHP+=mafia.getM().get(i).getHP();
				}
				for(int i=0; i<mafia_runner.kp.size(); i++) {
					if(mafia_runner.kp.get(i).getPlayer_ID()==mafia_target) {
						totl_targetHP = mafia_runner.kp.get(i).getHP();
						xyz=i;
						break;
					}
				}
				// ------------ SRIJAN HELP -----------//	
				if(totl_mafiaHP>=totl_targetHP) {
					for(int i=0; i<mafia_runner.kp.size(); i++) {
						if(mafia_runner.kp.get(i).getPlayer_ID()==mafia_target) {
							mafia_runner.kp.get(i).setHP(0);
						}
					}
				}
				else {
					for(int i=0; i<mafia_runner.kp.size(); i++) {
						if(mafia_runner.kp.get(i).getPlayer_ID()==mafia_target) {
							mafia_runner.kp.get(i).setHP(totl_targetHP-totl_mafiaHP);
						}
					}
				}
				// ----------- SRIJAN HELP END ------------//

				int damage = totl_targetHP/mafia.number_of_alive_mafia;

				for(int sir=0; sir<mafia.getM().size(); sir++) { // SIRAJ WALA
					if(mafia.getM().get(sir).getHP()>=damage) {
						int cur = mafia.getM().get(sir).getHP();
						mafia.getM().get(sir).setHP(cur-damage);
					}

					if(mafia.getM().get(sir).getHP()<damage) {
						int curHPmafia = mafia.getM().get(sir).getHP();
						mafia.getM().get(sir).setHP(0);
						damage = totl_targetHP-curHPmafia/mafia.number_of_alive_mafia-1;

					}

				} // SIRAJ WALA
			}

			for(int j=0; j<mafia_runner.kp.size(); j++) {
				if(mafia_runner.kp.get(j).getPlayer_ID()==mafia_target && mafia_runner.kp.get(j).getHP()<=0) {
					mafia_runner.kp.get(j).setKilled(true);
					
					if(mafia_runner.kp.get(j).getRole()=="MAFIA"){
						mafia.number_of_alive_mafia--;
						//players.setPlayer_alive(player_alive);
						players.player_alive--;
						mafia_runner.isAlive[mafia_target]=false;
					}

					else if(mafia_runner.kp.get(j).getRole()=="DETECTIVE"){
						detective.total_detectiveAlive--;
						players.player_alive--;
						mafia_runner.isAlive[mafia_target]=false;
					}

					else if(mafia_runner.kp.get(j).getRole()=="HEALER") {
						healer.total_healerAlive--;
						players.player_alive--;
						mafia_runner.isAlive[mafia_target]=false;
					}

					else if(mafia_runner.kp.get(j).getRole()=="COMMONERS") {
						commoners.total_commonersAlive--;
						players.player_alive--;
						mafia_runner.isAlive[mafia_target]=false;
					}		
				}
			}


			// setting up mafia killed or not by the detective after healer work //		

			boolean mafia_caught = false;
			for(int i=0; i<mafia.getM().size(); i++) {
				if(mafia.getM().get(i).getPlayer_ID()==detect) {
					mafia.getM().get(i).setKilled(true);
					mafia.number_of_alive_mafia--;
					players.player_alive--;
					mafia_runner.isAlive[detect]=false;
					mafia_caught = true;
					//break;
				}
			}



			//printing dead players//
			for(int x=0; x<mafia_runner.kp.size(); x++) {
				if(mafia_runner.kp.get(x).isKilled() && mafia_runner.isPrinted[mafia_runner.kp.get(x).getPlayer_ID()]==false) {
					System.out.println("Player"+mafia_runner.kp.get(x).getPlayer_ID()+" "+"has died.");
					mafia_runner.isPrinted[mafia_runner.kp.get(x).getPlayer_ID()] = true;
				}
			}
			// ----------------- //
			if(mafia_caught==false) {
				players.voting(userID);
			}
			System.out.println("---End of round "+ num +"----");
			num++;
		}
	}
	@Override
	public void winnerCall() {

		if(mafia.number_of_alive_mafia==players.getPlayer_alive()-mafia.number_of_alive_mafia) {
		//	System.out.println();
			//int xyz = players.getPlayer_alive()-mafia.number_of_alive_mafia;
			System.out.println();
			System.out.println();

			System.out.println("Game Over.");
			System.out.println("The Mafias have won.");
			players.printData();
		}
		else if(mafia.number_of_alive_mafia==0) {
			int xyz = players.getPlayer_alive()-mafia.number_of_alive_mafia;
			System.out.println();
			//System.out.println("----------"+"  "+mafia.number_of_alive_mafia+"  "+"--------");
			//System.out.println("----------"+"  "+xyz+"  "+"--------");

			System.out.println();

			System.out.println("Game Over.");
			System.out.println("The Mafias have lost.");
			players.printData();
		}
	}

}
