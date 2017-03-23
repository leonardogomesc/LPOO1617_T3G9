package dkeep.logic;

public class Map {
	private char map[][];
	private int doors[][];
	private int key;
	private int keypos[];
	private int maptype;  // 1- Dungeon       2-Keep
	
	public Map(char maparray[][],int doorsarray[][],int keyposarray[],int m){
		map=maparray;
		key=1;
		doors=doorsarray;
		keypos=keyposarray;
		maptype=m;
	}

	public char[][] getMap(){
		return map;
	}
	
	public int getMapType(){
		
		return maptype;
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
	
	public String to_String(){
		String result="";	
			
		for(int i1=0;i1<map.length;i1++){
			for(int i2=0;i2<map[i1].length;i2++){
				result=result+map[i1][i2]+" ";
			}
			result=result+"\n";
		}
		
		return result;
	}

}
