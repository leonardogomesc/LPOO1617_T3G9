import java.util.Random;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Game a= new Game();
		
		//a.task2();
		//a.task3();
		//a.task4();
		a.task5();
	}
	
	public void task2(){
		
		int win=0;
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
	
	public void task4(){
		
		int win=0;
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
			task4_stage2();
		}
		
		
	}

public void task4_stage2(){
		
		int win=0;
		int hero[]={7,1};
		int ogre[]={1,4};
		String move;
		int ogremove;
		Scanner s = new Scanner(System.in);
		Random rand = new Random();
		
		char map[][]={{'X','X','X','X','X','X','X','X','X'},
				      {'I',' ',' ',' ','O',' ',' ','K','X'},
				      {'X',' ',' ',' ',' ',' ',' ',' ','X'},
				      {'X',' ',' ',' ',' ',' ',' ',' ','X'},
				      {'X',' ',' ',' ',' ',' ',' ',' ','X'},
				      {'X',' ',' ',' ',' ',' ',' ',' ','X'},
				      {'X',' ',' ',' ',' ',' ',' ',' ','X'},
				      {'X','H',' ',' ',' ',' ',' ',' ','X'},
				      {'X','X','X','X','X','X','X','X','X'}};
		
		while(win==0){
	
			for(int i1=0;i1<9;i1++){
				for(int i2=0;i2<9;i2++){
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
		
		ogremove = rand.nextInt(4);
		
		switch(ogremove){
		case 0:
			if(map[ogre[0]-1][ogre[1]]!='X' && map[ogre[0]-1][ogre[1]]!='I'&& map[ogre[0]-1][ogre[1]]!='S'){
				
				if(ogre[0]!=1 || ogre[1]!=7){
				map[ogre[0]][ogre[1]]=' ';
				if(map[ogre[0]-1][ogre[1]]=='K'){
					map[ogre[0]-1][ogre[1]]='$';
				}
				else{
					map[ogre[0]-1][ogre[1]]='O';
				}
			ogre[0]=ogre[0]-1;
			}
				else if(ogre[0]==1 && ogre[1]==7){
					map[ogre[0]][ogre[1]]='K';
					map[ogre[0]-1][ogre[1]]='O';
				ogre[0]=ogre[0]-1;
				}
			}
			break;
		case 1:
			if(map[ogre[0]][ogre[1]-1]!='X' && map[ogre[0]][ogre[1]-1]!='I' && map[ogre[0]][ogre[1]-1]!='S'){
				if(ogre[0]!=1 || ogre[1]!=7){
					map[ogre[0]][ogre[1]]=' ';
					if(map[ogre[0]][ogre[1]-1]=='K'){
						map[ogre[0]][ogre[1]-1]='$';
					}
					else{
						map[ogre[0]][ogre[1]-1]='O';
					}
					ogre[1]=ogre[1]-1;
				}
					else if(ogre[0]==1 && ogre[1]==7){
						map[ogre[0]][ogre[1]]='K';
						map[ogre[0]][ogre[1]-1]='O';
					ogre[1]=ogre[1]-1;
					}
			}
			break;
		case 2:
			if(map[ogre[0]+1][ogre[1]]!='X' && map[ogre[0]+1][ogre[1]]!='I' && map[ogre[0]+1][ogre[1]]!='S'){
				if(ogre[0]!=1 || ogre[1]!=7){
					map[ogre[0]][ogre[1]]=' ';
					if(map[ogre[0]+1][ogre[1]]=='K'){
						map[ogre[0]+1][ogre[1]]='$';
					}
					else{
						map[ogre[0]+1][ogre[1]]='O';
					}
				ogre[0]=ogre[0]+1;
				}
					else if(ogre[0]==1 && ogre[1]==7){
						map[ogre[0]][ogre[1]]='K';
						map[ogre[0]+1][ogre[1]]='O';
					ogre[0]=ogre[0]+1;
					}
			}
			break;
		case 3:
			if(map[ogre[0]][ogre[1]+1]!='X' && map[ogre[0]][ogre[1]+1]!='I' && map[ogre[0]][ogre[1]+1]!='S'){
				if(ogre[0]!=1 || ogre[1]!=7){
					
					map[ogre[0]][ogre[1]]=' ';
					if(map[ogre[0]][ogre[1]+1]=='K'){
						map[ogre[0]][ogre[1]+1]='$';
					}
					else{
						map[ogre[0]][ogre[1]+1]='O';
					}
				ogre[1]=ogre[1]+1;
				}
					else if(ogre[0]==1 && ogre[1]==7){
						map[ogre[0]][ogre[1]]='K';
						map[ogre[0]][ogre[1]+1]='O';
					ogre[1]=ogre[1]+1;
					}
				
			}
		   break;
		default:
			break;
		}
		
		
		
		if(hero[0]==1 && hero[1]==7){
				map[1][0]='S';	
		}
		
		
		if(hero[0]==1 && hero[1]==0){
			win=1;
		}
		
		if((hero[0]==ogre[0] && hero[1]==ogre[1]+1)||(hero[0]==ogre[0]-1 && hero[1]==ogre[1])||(hero[0]==ogre[0] && hero[1]==ogre[1]-1)||(hero[0]==ogre[0]+1 && hero[1]==ogre[1])||(hero[0]==ogre[0] && hero[1]==ogre[1])){
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



public void task5(){
	
	int win=0;
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
		
		for(int i1=0;i1<10;i1++){
			for(int i2=0;i2<10;i2++){
				System.out.print(map[i1][i2]);
				System.out.print(" ");
			}
			System.out.println("");
		}
			
		System.out.println("\nYou lost!");
	}
	else if(win==1){
		task5_stage2();
	}
	
	
}

public void task5_stage2(){
	
	int win=0;
	int key=1;
	int hero[]={7,1};
	int ogre[]={1,4};
	int bat[]={2,4};
	int r;
	String move;
	int ogremove;
	int batmove;
	Scanner s = new Scanner(System.in);
	Random rand = new Random();
	
	char map[][]={{'X','X','X','X','X','X','X','X','X'},
			      {'I',' ',' ',' ','O',' ',' ','K','X'},
			      {'X',' ',' ',' ','*',' ',' ',' ','X'},
			      {'X',' ',' ',' ',' ',' ',' ',' ','X'},
			      {'X',' ',' ',' ',' ',' ',' ',' ','X'},
			      {'X',' ',' ',' ',' ',' ',' ',' ','X'},
			      {'X',' ',' ',' ',' ',' ',' ',' ','X'},
			      {'X','H',' ',' ',' ',' ',' ',' ','X'},
			      {'X','X','X','X','X','X','X','X','X'}};
	
	while(win==0){

		for(int i1=0;i1<9;i1++){
			for(int i2=0;i2<9;i2++){
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
	
	r=0;
	
	while(r==0){
	ogremove = rand.nextInt(4);
	
	switch(ogremove){
	case 0:
		if(map[ogre[0]-1][ogre[1]]!='X' && map[ogre[0]-1][ogre[1]]!='I'&& map[ogre[0]-1][ogre[1]]!='S'){
			
			map[ogre[0]][ogre[1]]=' ';
			map[bat[0]][bat[1]]=' ';
			map[ogre[0]-1][ogre[1]]='O';
			ogre[0]=ogre[0]-1;
			r=1;
		}
		break;
	case 1:
		if(map[ogre[0]][ogre[1]-1]!='X' && map[ogre[0]][ogre[1]-1]!='I' && map[ogre[0]][ogre[1]-1]!='S'){
			
			map[ogre[0]][ogre[1]]=' ';
			map[bat[0]][bat[1]]=' ';
			map[ogre[0]][ogre[1]-1]='O';
			ogre[1]=ogre[1]-1;
			r=1;
		}
		break;
	case 2:
		if(map[ogre[0]+1][ogre[1]]!='X' && map[ogre[0]+1][ogre[1]]!='I' && map[ogre[0]+1][ogre[1]]!='S'){
			map[ogre[0]][ogre[1]]=' ';
			map[bat[0]][bat[1]]=' ';
			map[ogre[0]+1][ogre[1]]='O';
			ogre[0]=ogre[0]+1;
			r=1;
		}
		
		break;
	case 3:
		if(map[ogre[0]][ogre[1]+1]!='X' && map[ogre[0]][ogre[1]+1]!='I' && map[ogre[0]][ogre[1]+1]!='S'){
			map[ogre[0]][ogre[1]]=' ';
			map[bat[0]][bat[1]]=' ';
			map[ogre[0]][ogre[1]+1]='O';
			ogre[1]=ogre[1]+1;		
			r=1;
		}
		
	   break;
	default:
		break;
	 }
	}
	
	r=0;
	
	while(r==0){
	
	batmove = rand.nextInt(4);
	
	switch(batmove){
	case 0:
		if(map[ogre[0]-1][ogre[1]]!='X' && map[ogre[0]-1][ogre[1]]!='I'&& map[ogre[0]-1][ogre[1]]!='S'){
			
			map[ogre[0]-1][ogre[1]]='*';
			bat[0]=ogre[0]-1;
			bat[1]=ogre[1];
			r=1;
		}
		break;
	case 1:
		if(map[ogre[0]][ogre[1]-1]!='X' && map[ogre[0]][ogre[1]-1]!='I' && map[ogre[0]][ogre[1]-1]!='S'){
			map[ogre[0]][ogre[1]-1]='*';
			bat[1]=ogre[1]-1;
			bat[0]=ogre[0];
			r=1;
		}
		break;
	case 2:
		if(map[ogre[0]+1][ogre[1]]!='X' && map[ogre[0]+1][ogre[1]]!='I' && map[ogre[0]+1][ogre[1]]!='S'){
			map[ogre[0]+1][ogre[1]]='*';
			bat[0]=ogre[0]+1;
			bat[1]=ogre[1];
			r=1;
		}
		break;
	case 3:
		if(map[ogre[0]][ogre[1]+1]!='X' && map[ogre[0]][ogre[1]+1]!='I' && map[ogre[0]][ogre[1]+1]!='S'){
			map[ogre[0]][ogre[1]+1]='*';
			bat[0]=ogre[0];
			bat[1]=ogre[1]+1;
			r=1;
		}
	   break;
	default:
		break;
	}
	}
	
	if(hero[0]==1 && hero[1]==7){
		map[1][0]='S';
		key=0;
     }
	
	if(key==1 && (ogre[0]==1 && ogre[1]==7)){
		map[1][7]='$';
	}
	else if(key==1 && (bat[0]==1 && bat[1]==7)){
		map[1][7]='$';
	}
	else if(key==1){
		map[1][7]='K';
	}
	
	
	
	if(hero[0]==1 && hero[1]==0){
		win=1;
	}
	
	if((hero[0]==ogre[0] && hero[1]==ogre[1]+1)||(hero[0]==ogre[0]-1 && hero[1]==ogre[1])||(hero[0]==ogre[0] && hero[1]==ogre[1]-1)||(hero[0]==ogre[0]+1 && hero[1]==ogre[1])||(hero[0]==ogre[0] && hero[1]==ogre[1]) ||(hero[0]==bat[0] && hero[1]==bat[1]+1)||(hero[0]==bat[0]-1 && hero[1]==bat[1])||(hero[0]==bat[0] && hero[1]==bat[1]-1)||(hero[0]==bat[0]+1 && hero[1]==bat[1])||(hero[0]==bat[0] && hero[1]==bat[1])){
		win=2;
	}
	
	System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	
	
}
	
	if(win==2){
		
		for(int i1=0;i1<9;i1++){
			for(int i2=0;i2<9;i2++){
				System.out.print(map[i1][i2]);
				System.out.print(" ");
			}
			System.out.println("");
		}
			
		System.out.println("\nYou lost!");
	}
	else if(win==1){
		System.out.println("You won!");
	}
	
}
	
}
