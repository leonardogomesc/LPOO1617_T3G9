import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Game a= new Game();
		
		//a.task2();
		a.task3();
		
	}
	
	public void task2(){
		
		int win=0;
		int score=0;
		int hero[]={1,1};
		int doors[][]={{5,0},{6,0}};
		String move;
		Scanner s = new Scanner(System.in);
		
		char map[][]={{'X','X','X','X','X','X','X','X','X','X'},
				      {'X','H',' ',' ','I',' ','X',' ','G','X'},
				      {'X','X','X',' ','X','X','X',' ',' ','X'},
				      {'X',' ','I',' ','I',' ','X',' ',' ','X'},
				      {'X','X','X',' ','X','X','X',' ',' ','X'},
				      {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				      {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				      {'X','X','X',' ','X','X','X','X',' ','X'},
				      {'X',' ','I',' ','I',' ','X','K',' ','X'},
				      {'X','X','X','X','X','X','X','X','X','X'}};
		
		while(win==0){
	
			for(int i1=0;i1<10;i1++){
				for(int i2=0;i2<10;i2++){
					System.out.print(map[i1][i2]);
					System.out.print(" ");
				}
				System.out.println("");
			}
				
			
		
		move= s.nextLine();
		
		switch(move){
		case "w":
			if(map[hero[0]-1][hero[1]]!='X' && map[hero[0]-1][hero[1]]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]-1][hero[1]]='H';
				hero[0]=hero[0]-1;
			}
			break;
		case "a":
			if(map[hero[0]][hero[1]-1]!='X' && map[hero[0]][hero[1]-1]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]][hero[1]-1]='H';
				hero[1]=hero[1]-1;
			}
			break;
		case "s":
			if(map[hero[0]+1][hero[1]]!='X' && map[hero[0]+1][hero[1]]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]+1][hero[1]]='H';
				hero[0]=hero[0]+1;
			}
			break;
		case "d":
			if(map[hero[0]][hero[1]+1]!='X' && map[hero[0]][hero[1]+1]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]][hero[1]+1]='H';
				hero[1]=hero[1]+1;
			}
		   break;
		default:
			break;
		}
		
		
		if(hero[0]==8 && hero[1]==7){
			for(int i2=0;i2<2;i2++){
				map[doors[i2][0]][doors[i2][1]]='S';
			}
			
		}
		
		if((hero[0]==5 && hero[1]==0)||(hero[0]==6 && hero[1]==0)){
			win=1;
		}
		
		if((hero[0]==1 && hero[1]==7)||(hero[0]==2 && hero[1]==8)){
			win=2;
		}
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
	}
		
		if(win==2){
			System.out.println("You lost!");
		}
		else if(win==1){
			System.out.println("You won!");
		}
	
	}
	
	public void task3(){
	
		int win=0;
		int score=0;
		int hero[]={1,1};
		int doors[][]={{5,0},{6,0}};
		int guard[][]={{1,8},{1,7},{2,7},{3,7},{4,7},{5,7},{5,6},{5,5},{5,4},{5,3},{5,2},{5,1},{6,1},{6,2},{6,3},{6,4},{6,5},{6,6},{6,7},{6,8},{5,8},{4,8},{3,8},{2,8}};
		int guardpos=0;
		String move;
		Scanner s = new Scanner(System.in);
		
		char map[][]={{'X','X','X','X','X','X','X','X','X','X'},
				      {'X','H',' ',' ','I',' ','X',' ','G','X'},
				      {'X','X','X',' ','X','X','X',' ',' ','X'},
				      {'X',' ','I',' ','I',' ','X',' ',' ','X'},
				      {'X','X','X',' ','X','X','X',' ',' ','X'},
				      {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				      {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				      {'X','X','X',' ','X','X','X','X',' ','X'},
				      {'X',' ','I',' ','I',' ','X','K',' ','X'},
				      {'X','X','X','X','X','X','X','X','X','X'}};
		
		while(win==0){
	
			for(int i1=0;i1<10;i1++){
				for(int i2=0;i2<10;i2++){
					System.out.print(map[i1][i2]);
					System.out.print(" ");
				}
				System.out.println("");
			}
				
			
		
		move= s.nextLine();
		
		switch(move){
		case "w":
			if(map[hero[0]-1][hero[1]]!='X' && map[hero[0]-1][hero[1]]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]-1][hero[1]]='H';
				hero[0]=hero[0]-1;
			}
			break;
		case "a":
			if(map[hero[0]][hero[1]-1]!='X' && map[hero[0]][hero[1]-1]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]][hero[1]-1]='H';
				hero[1]=hero[1]-1;
			}
			break;
		case "s":
			if(map[hero[0]+1][hero[1]]!='X' && map[hero[0]+1][hero[1]]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]+1][hero[1]]='H';
				hero[0]=hero[0]+1;
			}
			break;
		case "d":
			if(map[hero[0]][hero[1]+1]!='X' && map[hero[0]][hero[1]+1]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]][hero[1]+1]='H';
				hero[1]=hero[1]+1;
			}
		   break;
		default:
			break;
		}
		
		map[guard[guardpos][0]][guard[guardpos][1]]=' ';
		
		if(guardpos!=23){
		map[guard[guardpos+1][0]][guard[guardpos+1][1]]='G';
		guardpos=guardpos+1;
		}
		else if(guardpos==23) {
			map[guard[0][0]][guard[0][1]]='G';
			guardpos=0;
			}
		
		if(hero[0]==8 && hero[1]==7){
			for(int i2=0;i2<2;i2++){
				map[doors[i2][0]][doors[i2][1]]='S';
			}
			
		}
		
		
		if((hero[0]==5 && hero[1]==0)||(hero[0]==6 && hero[1]==0)){
			win=1;
		}
		
		if((hero[0]==guard[guardpos][0] && hero[1]==guard[guardpos][1]+1)||(hero[0]==guard[guardpos][0]-1 && hero[1]==guard[guardpos][1])||(hero[0]==guard[guardpos][0] && hero[1]==guard[guardpos][1]-1)||(hero[0]==guard[guardpos][0]+1 && hero[1]==guard[guardpos][1])||(hero[0]==guard[guardpos][0] && hero[1]==guard[guardpos][1])){
			win=2;
		}
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
	}
		
		if(win==2){
			System.out.println("You lost!");
		}
		else if(win==1){
			System.out.println("You won!");
		}
	
		
	}
	
}
