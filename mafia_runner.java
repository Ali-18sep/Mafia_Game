package Assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random; 
import java.util.Scanner;

public class mafia_runner {

	public static int nn=0;
	protected static boolean[] isAlive;
	public static ArrayList<players> kp = new ArrayList<>();

	protected static boolean[] isPrinted;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random random = new Random(); 


		System.out.println("Welcome to Mafia");
		System.out.println("Enter Number of playres:");

		int N = sc.nextInt();
		while(N<6) {
			System.out.println("Invalid input, enter a number again");
			N = sc.nextInt();
		}
		players.setPlayer_alive(N);
		nn = N;

		isPrinted = new boolean[mafia_runner.nn+1];


		boolean[] visited = new boolean[N+1];
		isAlive = new boolean[N+1];
		for(int i=1; i<isAlive.length; i++) {
			isAlive[i] = true;
		}

		int yes = 0;
		while(yes<N/5) {
			int rand_int = random.nextInt(N+1); 
			//arr.add(rand_int, true);
			if(rand_int!=0) {
				if(visited[rand_int]==false) {
					visited[rand_int]=true;
					mafia m = new mafia(2500, rand_int, "MAFIA");
					yes++;
				}

			}
		}


		int k=0;
		while(k<N/5) {
			int random_int = random.nextInt(N+1);
			//if(!arr.get(random_int)) {
			if(random_int!=0) {
				if(visited[random_int]==false) {
					//arr.add(random_int, true);
					visited[random_int]=true;
					detective d = new detective(800,random_int,"DETECTIVE");
					k++;
				}
			}
		}

		int j=0;
		int ss = Math.max(1, N/10);
		while(j<ss) {
			int random_int = random.nextInt(N+1);
			//if(!arr.get(random_int)) {
			if(random_int!=0) {
				if(visited[random_int]==false) {
					//visited.add(random_int, true);
					visited[random_int]=true;
					healer d = new healer(800,random_int,"HEALER");
					j++;
				}
			}
		}

		for(int i=1; i<=N; i++) {
			if(visited[i]==false) {
				visited[i]=true;
				commoners c = new commoners(1000,i,"COMMONERS");
			}
		}

		System.out.println("Choose a Character");

		System.out.println("1) Mafia");
		System.out.println("2) Detective");
		System.out.println("3) Healer");
		System.out.println("4) commoner");
		System.out.println("5) Assign Randomly");

		//System.out.println("ArrayLength: "+isPrinted.length);

		int character = sc.nextInt();

		while(true) {
			if(character==1 || character==2 || character==3 || character==4 || character==5) {
				break;
			}
			else {
				System.out.print("Invalid input. Please enter the shown choice: ");
				character = sc.nextInt();
			}
		}

		if(character == 1) {

			int nnn = mafia.getM().get(0).getPlayer_ID();

			System.out.println("You are Player"+nnn);
			System.out.println("you are a mafia");
			System.out.print("Other mafias are:"+" ");

			for(int i=1; i<mafia.getM().size(); i++) {
				System.out.print("[Player"+mafia.getM().get(i).getPlayer_ID()+"]"+"  ");
			}
			System.out.println();

			//players.game_chalaaao();
			mafia m = new mafia();

			m.gamePlay(nnn);
			m.winnerCall();
		}	

		else if(character == 2) {
			int nnn = detective.getD().get(0).getPlayer_ID();

			System.out.println("You are player"+nnn);
			System.out.println("You are a detective");
			System.out.print("Other detective are:"+" ");

			for(int i=1; i<detective.getD().size(); i++) {
				System.out.print("[Players"+detective.getD().get(i).getPlayer_ID()+"]"+"  ");
			}
			System.out.println();

			detective dd = new detective();
			dd.gamePlay(nnn);
			dd.winnerCall();

		}

		else if(character==3) {
			int nnn = healer.getH().get(0).getPlayer_ID();

			System.out.println("You are player"+nnn);
			System.out.println("You are a healer");
			System.out.print("Other healers are:"+" ");

			for(int i=1; i<healer.getH().size(); i++) {
				System.out.print("[Players"+healer.getH().get(i).getPlayer_ID()+"]"+"  ");
			}
			System.out.println();

			healer h = new healer();
			h.gamePlay(nnn);
			h.winnerCall();
		}

		else if(character==4) {
			int nnn = commoners.getC().get(0).getPlayer_ID();

			System.out.println("You are player"+nnn);
			System.out.println("You are a commoner");
			System.out.print("Other commoners are:"+" ");

			for(int i=1; i<commoners.getC().size(); i++) {
				System.out.print("[Players"+commoners.getC().get(i).getPlayer_ID()+"]"+"  ");
			}
			System.out.println();

			commoners c = new commoners();
			c.gamePlay(nnn);
			c.winnerCall();
		}

		else if(character==5) {
			int magic = random.nextInt(5);
			randomHandler(magic);
		}

	}

	private static void randomHandler(int magic) {

		if(magic == 1) {

			int nnn = mafia.getM().get(0).getPlayer_ID();

			System.out.println("You are Player"+nnn);
			System.out.println("you are a mafia");
			System.out.print("Other mafias are:"+" ");

			for(int i=1; i<mafia.getM().size(); i++) {
				System.out.print("[Player"+mafia.getM().get(i).getPlayer_ID()+"]"+"  ");
			}
			System.out.println();

			//players.game_chalaaao();
			mafia m = new mafia();

			m.gamePlay(nnn);
			m.winnerCall();
		}	

		else if(magic == 2) {
			int nnn = detective.getD().get(0).getPlayer_ID();

			System.out.println("You are player"+nnn);
			System.out.println("You are a detective");
			System.out.print("Other detective are:"+" ");

			for(int i=1; i<detective.getD().size(); i++) {
				System.out.print("[Players"+detective.getD().get(i).getPlayer_ID()+"]"+"  ");
			}
			System.out.println();

			detective dd = new detective();
			dd.gamePlay(nnn);
			dd.winnerCall();

		}

		else if(magic==3) {
			int nnn = healer.getH().get(0).getPlayer_ID();

			System.out.println("You are player"+nnn);
			System.out.println("You are a healer");
			System.out.print("Other healers are:"+" ");

			for(int i=1; i<healer.getH().size(); i++) {
				System.out.print("[Players"+healer.getH().get(i).getPlayer_ID()+"]"+"  ");
			}
			System.out.println();

			healer h = new healer();
			h.gamePlay(nnn);
			h.winnerCall();
		}
		else{
			int nnn = commoners.getC().get(0).getPlayer_ID();

			System.out.println("You are player"+nnn);
			System.out.println("You are a commoner");
			System.out.print("Other commoners are:"+" ");

			for(int i=1; i<commoners.getC().size(); i++) {
				System.out.print("[Players"+commoners.getC().get(i).getPlayer_ID()+"]"+"  ");
			}
			System.out.println();
			commoners c = new commoners();
			c.gamePlay(nnn);
			c.winnerCall();
		}

	}

}
