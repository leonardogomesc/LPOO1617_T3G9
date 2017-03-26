package dkeep.logic;

public class Hero {
	private int hero[];
	private int basher;
	private char [][]map;
	private int[] keypos;
	private int key;
	private int[][] doors;
	private int mapType;
	public Hero(int heropos[], int b){
		hero=heropos;
		basher=b; }

	public int[] getHero(){return hero; }

	public int getBasher() {return basher; }
	private char getSymbol(){
		if(key==0 && mapType==2){
			return 'K'; }
		if(basher==1){
			return 'A'; 
			}
		return 'H'; }
	private int[] setMovement(String move){
		int inc[]={0,0};
		switch(move){
		case "w": inc[0]=-1; break;
		case "a": inc[1]=-1; break;
		case "s": inc[0]=1; break;
		case "d": inc[1]=1; break; }
		return inc; }
	public void HeroMove(Map m,String move){
		map=m.getMap(); keypos=m.getKeyPos();
		key=m.getKey(); doors=m.getDoors();
		mapType=m.getMapType();
		char h=getSymbol(); int inc[]=setMovement(move);
		if(map[hero[0]+inc[0]][hero[1]+inc[1]]!='X' && map[hero[0]+inc[0]][hero[1]+inc[1]]!='I'){
				map[hero[0]][hero[1]]=' '; hero[0]=hero[0]+inc[0]; hero[1]=hero[1]+inc[1];
				map[hero[0]][hero[1]]=h; }
			else if(mapType==2 && map[hero[0]+inc[0]][hero[1]+inc[1]]=='I' && key==0){map[hero[0]+inc[0]][hero[1]+inc[1]]='S'; }		
		if((hero[0]==keypos[0] && hero[1]==keypos[1]) && mapType==1){
			for(int i=0;i<doors.length;i++){map[doors[i][0]][doors[i][1]]='S'; }
			m.setKey(0); }
		else if((hero[0]==keypos[0] && hero[1]==keypos[1]) && mapType==2){m.setKey(0); map[hero[0]][hero[1]]='K'; }
		if(mapType==1 && !(hero[0]==keypos[0] && hero[1]==keypos[1])){map[keypos[0]][keypos[1]]='k'; } } }
