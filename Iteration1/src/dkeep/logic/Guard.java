package dkeep.logic;

public class Guard {
	private int guard[][];
	private int guardpos;
	private int type; //rookie-1 /drunken-2 /suspicious-3

	public Guard(int guardarray[][], int t){
		guardpos=0;
		guard=guardarray;
		type = t;
	}

	public int[][] getGuard(){
		return guard;
	}

	public int getGuardpos(){
		return guardpos;
	}



	public void GuardMove(Map m){
		char map[][]=m.getMap();
		if(type==1){
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
		else if(type==2){

			
		} 
		else if (type==3){

			
		}

	}
}

