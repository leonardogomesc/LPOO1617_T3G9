package dkeep.logic;
/**
 * Class that keeps information on the level's board(wall layout), its doors' positions, its type and the position of the key/lever
 * @author Leonardo Capozzi
 * @author Ricardo Carvalho 
 *
 */
public class Map {
	private char map[][];
	private int doors[][];
	private int key;
	private int keypos[];
	private int maptype;  // 1- Dungeon       2-Keep
	/**
	 * Creates a Map given it's key features
	 * @param maparray array containing the map's layout
	 * @param doorsarray array containing the doors' coordinates
	 * @param keyposarray array containing the key's coordinates
	 * @param m map's type (1 for dungeon and 2 for keep)
	 */
	public Map(char maparray[][],int doorsarray[][],int keyposarray[],int m){
		map=maparray;
		key=1;
		doors=doorsarray;
		keypos=keyposarray;
		maptype=m;
	}

	/**
	 * gets the array with the map as it would be presented in an interface
	 * @return char containing the map's layout
	 */
	public char[][] getMap(){
		return map;
	}
	
	/**
	 * Gets the level's type
	 * @return 1 for dungeon and 2 for keep
	 */
	public int getMapType(){
		
		return maptype;
	}
/**
 * Gets the key's coordinates
 * @return array with key's position
 */
	public int[] getKeyPos(){
		return keypos;
	}
/**
 * Gets the key status (caught or not)
 * @return 0 if the key has been caught and 1 otherwise
 */
	public int getKey(){
		return key;
	}

	/**
	 * Gets the doors's positions
	 * @return array of array with the coordinates of each door
	 */
	public int[][] getDoors(){
		return doors;
	}

	/**
	 * Sets the key status
	 * @param k value to set key to(0 if caught or 1 otherwise)
	 */
	public void setKey(int k){
		key=k;
	}

}
