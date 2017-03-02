package dkeep.logic;

public class Map {
	private char map[][];
	private int doors[][];
	private int key;
	private int keypos[];
	
	public Map(char maparray[][],int doorsarray[][],int keyposarray[]){
		map=maparray;
		key=1;
		doors=doorsarray;
		keypos=keyposarray;
	}

	public char[][] getMap(){
		return map;
	}

	public int[] getKeyPos(){
		return keypos;
	}

	public int getKey(){
		return key;
	}

	public int[][] getDoors(){
		return doors;
	}

	public void setKey(int k){
		key=k;
	}
}
