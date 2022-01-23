package Assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class mafia extends players{

	private static ArrayList<mafia> m = new ArrayList<>();
	protected static boolean[] isMafia = new boolean[mafia_runner.nn+1];

	protected static int number_of_alive_mafia;

	

	private int userID=0;

	public mafia() {    //default constructor

	}


	public mafia(int i, int rand_int, String string) {
		this.setHP(i);
		this.setRole(string);
		this.setPlayer_ID(rand_int);
		this.setKilled(false);
		number_of_alive_mafia++;
		isMafia[rand_int] = true;
		m.add(this);
		mafia_runner.kp.add(this);
	}

	public static int getNumber_of_alive_mafia() {
		return number_of_alive_mafia;
	}

	public static ArrayList<mafia> getM() {
		return m;
	}

	@Override
	public void gamePlay(int nnn) {
		userID = nnn;
		int num = 1;
		Scanner sc = new Scanner(System.in);
		Random random = new Random(); 


		while(true) {	

			if(number_of_alive_mafia==players.getPlayer_alive()-number_of_alive_mafia) {
				break;
			}
			if(number_of_alive_mafia==0) {
				break;
			}

			System.out.println("Round"+num);

			System.out.print(players.getPlayer_alive()+" "+"Players are remaining:"+" ");

			for(int i=0; i<mafia_runner.kp.size(); i++) {
				if(mafia_runner.kp.get(i).isKilled()==false) {
					System.out.print("Player"+mafia_runner.kp.get(i).getPlayer_ID()+"  ");
				}
			}
			System.out.println();
			int mafia_target=0;
			for(int o=0; o<mafia_runner.kp.size(); o++) {
				if(mafia_runner.kp.get(o).getPlayer_ID()==userID && mafia_runner.kp.get(o).isKilled()==false) {
					System.out.println("Choose a target: ");
					mafia_target = sc.nextInt();
					//mafia choosing their target

					while(isMafia[mafia_target]==true || mafia_runner.isAlive[mafia_target]==false) {
						if(mafia_runner.isAlive[mafia_target]==false) {
							System.out.println("this player is already died or voted out");
							System.out.println("choose other player:");
							mafia_target = sc.nextInt();
						}
						else if(isMafia[mafia_target]==true) {
							System.out.println("Player"+mafia_target+" "+"is a mafia");
							System.out.println("choose other player:");
							mafia_target = sc.nextInt();
						}
					}
				}
				else if(number_of_alive_mafia!=0) {
					mafia_target = random.nextInt(mafia_runner.nn+1);
					//mafia choosing their target

					while(isMafia[mafia_target]==true || mafia_runner.isAlive[mafia_target]==false) {
						if(mafia_runner.isAlive[mafia_target]==false) {
							mafia_target = random.nextInt(mafia_runner.nn+1);
						}
						else if(isMafia[mafia_target]==true) {
							mafia_target = random.nextInt(mafia_runner.nn+1);
						}
					}
				}
			}

			//detective choosing a player to test
			int detect=0;
			//boolean bb = false;
			if(detective.total_detectiveAlive!=0) {
				detect = random.nextInt(mafia_runner.nn+1);
				while(mafia_runner.isAlive[detect]==false) {
					//System.out.println("you cannot test a detective. ");
					detect = random.nextInt(mafia_runner.nn+1);
					//	bb = true;
				}
				System.out.println("Detectives have choosen a player to test.");
			}
		//	System.out.println("-----------------CHECKER:--------------"+" "+detective.total_detectiveAlive);
			//System.out.println("-----------------CHECKER:--------------"+" "+healer.total_healerAlive);



			//healer healing
			int heal = 0;
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
				System.out.println("Healers have choosen someone to heal");
			}
			 //	System.out.println("Healers have choosen someone to heal");


			//--------------------- HP HANDLE --------------------------------------------//
			// sorting on the basis of HP of mafias
			Collections.sort(mafia.getM(), new comparator()); 
			//
			int totl_mafiaHP=0;
			int totl_targetHP=0;
			int xyz = 0;
			if(mafia.number_of_alive_mafia!=0) {
				//totl_mafiaHP=0;
				for(int i=0; i<m.size(); i++) {
					totl_mafiaHP+=m.get(i).getHP();
				}

				//totl_targetHP=0;

				for(int i=0; i<mafia_runner.kp.size(); i++) {
					if(mafia_runner.kp.get(i).getPlayer_ID()==mafia_target) {
						totl_targetHP = mafia_runner.kp.get(i).getHP();
						xyz=i;
						break;
					}
				}
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

				} 
			}
			
			for(int j=0; j<mafia_runner.kp.size(); j++) {
				if(mafia_runner.kp.get(j).getPlayer_ID()==mafia_target && mafia_runner.kp.get(j).getHP()<=0) {
					mafia_runner.kp.get(j).setKilled(true);
					if(mafia_runner.kp.get(j).getRole()=="MAFIA"){
						number_of_alive_mafia--;
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

			boolean mafia_caught = false;
			for(int i=0; i<m.size(); i++) {
				if(m.get(i).getPlayer_ID()==detect) {
					m.get(i).setKilled(true);
					number_of_alive_mafia--;
					players.player_alive--;
					mafia_runner.isAlive[detect]=false;
					mafia_caught = true;
					//break;
				}
			}

			System.out.println("----End of Action----");

			for(int x=0; x<mafia_runner.kp.size(); x++) {
				if(mafia_runner.kp.get(x).isKilled() && mafia_runner.isPrinted[mafia_runner.kp.get(x).getPlayer_ID()]==false) {
					System.out.println("Player"+mafia_runner.kp.get(x).getPlayer_ID()+" "+"has died.");
					mafia_runner.isPrinted[mafia_runner.kp.get(x).getPlayer_ID()] = true;
				}
			}
			
			

			if(mafia_caught==false) {
				voting(userID);
			}
			System.out.println("---End of round "+ num +"----");
			num++;
		}

	}


	@Override
	public void winnerCall() {

		if(number_of_alive_mafia==players.getPlayer_alive()-number_of_alive_mafia) {
			System.out.println();
			int xyz = players.getPlayer_alive()-number_of_alive_mafia;
			System.out.println();
			System.out.println();
			System.out.println("Game Over.");
			System.out.println("The Mafias have won.");
			players.printData();
		}
		else if(number_of_alive_mafia==0) {
			int xyz = players.getPlayer_alive()-number_of_alive_mafia;
			System.out.println();
			System.out.println();
			System.out.println("Game Over.");
			System.out.println("The Mafias have lost.");
			players.printData();
		}

	}

}
