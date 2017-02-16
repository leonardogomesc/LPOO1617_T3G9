
public class Game {

	public static void main(String[] args) {
		char map[][]={{'X','X','X','X','X','X','X','X','X','X'},
				      {'X',' ',' ',' ','I',' ','X',' ','G','X'},
				      {'X','X','X',' ','X','X','X',' ',' ','X'},
				      {'X',' ','I',' ','I',' ','X',' ',' ','X'},
				      {'X','X','X',' ','X','X','X',' ',' ','X'},
				      {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				      {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				      {'X','X','X',' ','X','X','X','X',' ','X'},
				      {'X',' ','I',' ','I',' ','X','K',' ','X'},
				      {'X','X','X','X','X','X','X','X','X','X'}};
		
		for(int i1=0;i1<10;i1++){
			for(int i2=0;i2<10;i2++){
				System.out.print(map[i1][i2]);
			}
			System.out.println("");
		}
		
	}
	

}
