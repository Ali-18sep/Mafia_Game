package Assignment3;

import java.util.Comparator;

class comparator implements Comparator<mafia> {  
	@Override
	public int compare(mafia o1, mafia o2) {
		// TODO Auto-generated method stub
		return o1.getHP()-o2.getHP();
	} 
} 
  
