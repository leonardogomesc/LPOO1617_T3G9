package dkeep.logic;

public class Map {
	private char map[][];
	private int doors[][];
	private Guard guards[];
	private int key;
	private int keypos[];
	
	public Map(char maparray[][], Guard guardsarray[],int doorsarray[][],int keyposarray[]){
		map=maparray;
		guards=guardsarray;
		key=1;
		doors=doorsarray;
		keypos=keyposarray;
	}
}
