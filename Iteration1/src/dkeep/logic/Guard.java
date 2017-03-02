package dkeep.logic;

public class Guard {
	private int guard[][];
	private int guardpos;
	
	public Guard(int guardarray[][]){
		guardpos=0;
		guard=guardarray;
	}
	
	public int[][] getGuard(){
		return guard;
	}
	
	public int getGuardpos(){
		return guardpos;
	}
	
	
	
	public void GuardMove(Map m){
		char map[][]=m.getMap();
		
		map[guard[guardpos][0]][guard[guardpos][1]]=' ';
		
		if(guardpos!=23){
		map[guard[guardpos+1][0]][guard[guardpos+1][1]]='G';
		guardpos=guardpos+1;
		}
		else if(guardpos==23) {
			map[guard[0][0]][guard[0][1]]='G';
			guardpos=0;
			}
		
	}
	}

