package dkeep.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Vector;
/**
 * Game.java - class that manages the game, keeps record of levels, saves and reads data from files
 * @author Leonardo Capozzi
 * @author Ricardo Carvalho
 *
 */
public class Game {

	/*
	 *variables used in file input
	 *(not used outside this class)
	 */
	private Path path;
	private final static Charset ENCODING = StandardCharsets.UTF_8; 
	private String levelName;
	private Scanner in;
	private String line;
	private PrintWriter outFile;
	/*
	 * variables used for the game itself
	 */
	private Guard g;
	private Hero h;
	private Map m;
	private Ogre[] o;
	private int currentLevel;
	private Vector<String> levels;

	
	/**
	 * Constructor used to create a new dungeon type game, only for saving purposes. 
	 * @param m2 map information to save for latter use
	 * @param h2  hero status to save in a file
	 * @param g2 guard status to write in a file
	 */
	public Game(Map m2,Hero h2, Guard g2){m=m2; h=h2; g=g2; }

	/**
	 * Constructor used to create a new keep type game, only for saving and testing an edited level.
	 * @param m2 map information to save for latter use
	 * @param h2  hero status to save in a file
	 * @param g2 guard status to write in a file
	 */
	public Game(Map m2,Hero h2, Ogre[] o2){m=m2; h=h2; o=o2; }

	/**
	 * Constructor used to play the default game levels saved in level1.map and keep.map
	 */
	public Game(){
		this.levels=new Vector<String>();
		levels.clear();
		this.levels.add("level1");
		this.levels.add("keep");
		this.currentLevel=-1; }

	/**
	 * Sets the next level checking the game state (if next level doesn't exist considers game beaten)
	 * @param info array that includes information as guard personality (at index 0) and number of ogres (at index 1)
	 * @return int to indicate game state, returns 0 case game is won and 1 otherwise
	 * @throws IOException
	 */
	public int nextLevel(int[] info) throws IOException{
		this.currentLevel=this.currentLevel+1;
		if(this.currentLevel>=this.levels.size()){return 0; }
		else{
			setGame(levels.get(this.currentLevel), info);
			return 1; } }

	private void setGame(String levelName, int[] info) throws IOException{
		this.levelName=levelName+".map";
		this.openFile();

		this.processFile(info);		
		this.setPlaces(); }

	private void openFile() throws IOException{
		path = Paths.get("src/dkeep/logic/levels/"+levelName);
		this.in =  new Scanner(path, ENCODING.name()); }

	private void processFile(int[] info){
		int[] keyPos= new int[2];
		int type; in.useDelimiter("\n"); NextLine();
		char map[][]=ReadBoardSize();
		if(1==(type= Integer.parseInt(line.substring(6, 7)))) {o=new Ogre[info[1]];} //level type
		NextLine(); h=ReadHero();
		NextLine(); int [][] doorPos=ReadDoor();
		NextLine(); keyPos=ReadKeyPos();
		NextLine();
		if(0==type) {ReadGuard(info); }
		else if(1==type) {ReadOgre(info); }
		map=ReadWallLayout(map);			
		m=new Map(map,doorPos,keyPos,type+1);
		in.close(); }

	private char[][] ReadBoardSize(){
		int[] size=new int[2];//board size
		size[0]= Integer.parseInt(line.substring(0, 2));
		size[1]=Integer.parseInt(line.substring(3, 5));
		return new char[size[0]][size[1]]; }

	private void NextLine(){line=in.next(); }

	private Hero ReadHero(){
		int[] coordh = new int[2];
		int basher=Integer.parseInt(line.substring(0, 1));
		coordh[0]=Integer.parseInt(line.substring(2, 4));
		coordh[1]=Integer.parseInt(line.substring(5, 7));
		return new Hero(coordh,basher); }

	private int [][] ReadDoor(){
		int[] coord = new int[2];
		int n=Integer.parseInt(line.substring(0, line.indexOf(';')));
		int doorPos[][]= new int[n][2];
		for(int i=0; i<n;i++){
			line=line.substring(line.indexOf(';')+1);
			coord[0]=Integer.parseInt(line.substring(0, line.indexOf(',')));
			if (i+1==n){coord[1]=Integer.parseInt(line.substring(line.indexOf(',')+1,line.indexOf(',')+3)); }
			else{coord[1]=Integer.parseInt(line.substring(line.indexOf(',')+1,line.indexOf(';'))); }
			doorPos[i][0]=coord[0];
			doorPos[i][1]=coord[1]; }
		return doorPos; }

	private int[] ReadKeyPos() {
		int[] keyPos= new int[2], coord = new int[2];
		coord[0]=Integer.parseInt(line.substring(0, 2));
		coord[1]=Integer.parseInt(line.substring(3, 5));
		keyPos[0]=coord[0];
		keyPos[1]=coord[1];
		return keyPos; }

	private void ReadGuard(int[] info){
		int[] coord = new int[2];
		int n=Integer.parseInt(line.substring(0,line.indexOf(';')));
		int[][] guardPos= new int[n][2];
		for(int i=0; i<n;i++) {
			line=line.substring(line.indexOf(';')+1);
			coord[0]=Integer.parseInt(line.substring(0, line.indexOf(',')));
			if (i+1==n){coord[1]=Integer.parseInt(line.substring(line.indexOf(',')+1,line.indexOf(',')+3)); }
			else{coord[1]=Integer.parseInt(line.substring(line.indexOf(',')+1,line.indexOf(';'))); }
			guardPos[i][0]=coord[0];
			guardPos[i][1]=coord[1]; }
		g = new Guard(guardPos,info[0]);
		o=null; }

	private void ReadOgre(int[] info){
		String bat;int[] coord = new int[2];
		coord[0]=Integer.parseInt(line.substring(0,line.indexOf(',')));
		line=line.substring(line.indexOf(',')+1);
		coord[1]=Integer.parseInt(line.substring(0,line.indexOf(',')));
		line=line.substring(line.indexOf(',')+1,line.indexOf(',')+2);
		for(int i=0;i<o.length;i++){
			bat=new String(line.substring(line.indexOf(',')+1));
			switch(bat){
			case "n": o[i]=new Ogre(new int[] {coord[0],coord[1]}, new int[] {coord[0]-1,coord[1]}); break;
			case "s": o[i]=new Ogre(new int[] {coord[0],coord[1]}, new int[] {coord[0]+1,coord[1]}); break;
			case "e": o[i]=new Ogre(new int[] {coord[0],coord[1]}, new int[] {coord[0],coord[1]+1}); break;
			case "o": o[i]=new Ogre(new int[] {coord[0],coord[1]}, new int[] {coord[0],coord[1]-1}); break; } }
		g=null; }

	private char[][] ReadWallLayout(char map[][]){
		for(int j=0; j<map.length;j++) {
			NextLine();
			char[] arrayLine=line.toCharArray();
			for (int i=0;i<map[0].length;i++) {map[j][i]= arrayLine[i]; } }
		return map; }

	private void setPlaces() {
		if(h.getBasher()==0){m.getMap()[h.getHero()[0]][h.getHero()[1]]='H'; }
		else if(h.getBasher()==1){m.getMap()[h.getHero()[0]][h.getHero()[1]]='A'; }
		if (null!=g){m.getMap()[g.getGuard()[g.getGuardpos()][0]][g.getGuard()[g.getGuardpos()][1]]='G'; }
		if (null != o){
			for(int i=0;i<o.length;i++){
				m.getMap()[o[i].getOgre()[0]][o[i].getOgre()[1]]='O';
				m.getMap()[o[i].getBat()[0]][o[i].getBat()[1]]='*'; } }
		m.getMap()[m.getKeyPos()[0]][m.getKeyPos()[1]]='k';
		for(int i=0;i<m.getDoors().length;i++){m.getMap()[m.getDoors()[i][0]][m.getDoors()[i][1]]='I'; } }

	/**
	 * Gets the guard in the current level
	 * @return Guard object or null if current level is of keep type
	 */
	public Guard getGuard() {return g; }

	/**
	 * Gets the current hero state in the current level
	 * @return Hero object
	 */
	public Hero getHero() {return h; }

	/**
	 * Gets the current map state in the current level
	 * @return Map object
	 */
	public Map getMap() {return m; }

	/**
	 * Gets the ogres in the current level
	 * @return An array of Ogre object or null if current level is of dungeon type
	 */
	public Ogre[] getOgre() {return o; }

	/**
	 * Checks if level is won
	 * @return 1 if level is won and 0 otherwise
	 */
	public int wincheck(){
		int hero[]=h.getHero();
		int doors[][]=m.getDoors();
		int result=0;
		for(int i =0;i<doors.length;i++){	
			if(hero[0]==doors[i][0] && hero[1]==doors[i][1]){result=1; } }
		return result; }
	/**
	 * Checks the loss conditions for a dungeon type level (if hero and guard are in adjacent cells)
	 * @return 1 if level is lost and 0 otherwise
	 */
	public int losscheck(){
		int hero[]=h.getHero(), guard[][]=g.getGuard(), guardpos=g.getGuardpos(), result=0;
		char map[][]=m.getMap();

		if (map[guard[guardpos][0]][guard[guardpos][1]]=='G'){
			if((hero[0]==guard[guardpos][0] && hero[1]==guard[guardpos][1]+1)||
					(hero[0]==guard[guardpos][0]-1 && hero[1]==guard[guardpos][1])||
					(hero[0]==guard[guardpos][0] && hero[1]==guard[guardpos][1]-1)||
					(hero[0]==guard[guardpos][0]+1 && hero[1]==guard[guardpos][1])||
					(hero[0]==guard[guardpos][0] && hero[1]==guard[guardpos][1])){
				result=1; } }
		return result; }
/**
 * Checks the loss conditions for a keep type level (if hero with basher is in cell adjacent to an ogre's bat
 * or hero without basher is either adjacent to an ogre or its bat)
 * @return 1 if level is lost and 0 otherwise
 */
	public int losscheckkeep(){
		int result = 0;
		for(int i=0;i<o.length;i++){
			if (h.getBasher()==0 && result==0){result=lossKeepNoBasher(i); 
			} 
			else if(h.getBasher()==1 && result==0){
				result=lossKeepBasher(i); 
				}
			}
		return result; 
		}

	private int lossKeepBasher(int i){
		int[] hero=h.getHero(), bat=o[i].getBat();
		if(	(hero[0]==bat[0] && hero[1]==bat[1]+1) || (hero[0]==bat[0]-1 && hero[1]==bat[1])||
						(hero[0]==bat[0] && hero[1]==bat[1]-1) || (hero[0]==bat[0]+1 && hero[1]==bat[1])||
						(hero[0]==bat[0] && hero[1]==bat[1])) {return 1; }
		return 0;
	}
	private int lossKeepNoBasher(int i)
	{
		int[] hero=h.getHero(), ogre=o[i].getOgre(),bat=o[i].getBat();
		if(((hero[0]==ogre[0]) && (hero[1]==ogre[1]+1)) || ((hero[0]==ogre[0]-1) && (hero[1]==ogre[1]))||
						((hero[0]==ogre[0] && hero[1]==ogre[1]-1)) || ((hero[0]==ogre[0]+1 && hero[1]==ogre[1]))||
						((hero[0]==ogre[0] && hero[1]==ogre[1])) || ((hero[0]==bat[0] && hero[1]==bat[1]+1))||
						((hero[0]==bat[0]-1 && hero[1]==bat[1])) || ((hero[0]==bat[0] && hero[1]==bat[1]-1))||
						((hero[0]==bat[0]+1 && hero[1]==bat[1])) || ((hero[0]==bat[0] && hero[1]==bat[1])))
		{return 1; }
		return 0;
	}
	
	/**
	 * Sets in motion the ogres' movement, erasing current position,
	 * setting a new position and checking proximity to a hero's basher (if there is any)
	 * recurring to auxiliary functions
	 */
	public void OgreMove(){
		int i;
		for(i=0;i<o.length;i++){o[i].OgreErase(m); }

		if(m.getKey() == 1){m.getMap()[m.getKeyPos()[0]][m.getKeyPos()[1]]='k'; }

		for(i=0;i<o.length;i++){o[i].OgreMove(m); }

		setStunned(); }

	/**
	 * Sets the ogre's stunned status (ogre is stunned if it's in a cell adjacent to hero with basher)
	 */
	public void setStunned(){
		if(h.getBasher()==1){

			for(int i=0;i<o.length;i++){

				int[] hero=h.getHero(), ogre=o[i].getOgre();
				char map[][]=m.getMap();

				if((hero[0]==ogre[0] && hero[1]==ogre[1]+1) || (hero[0]==ogre[0]-1 && hero[1]==ogre[1])||
						(hero[0]==ogre[0] && hero[1]==ogre[1]-1) || (hero[0]==ogre[0]+1 && hero[1]==ogre[1])||
						(hero[0]==ogre[0] && hero[1]==ogre[1])){
					o[i].setStunned(); map[ogre[0]][ogre[1]]='8'; } } } }
	
	/**
	 * Saves the whole level's information in a .map file to play later
	 * @param levelName the name of the level that we want to save, previously checked to avoid replacing an existing file
	 */
	public void SaveLevelFile(String levelName) {
		try{
			outFile = new PrintWriter("src/dkeep/logic/levels/"+levelName+".map", "UTF-8");
			SaveBoardInfo();
			SaveHeroInfo();
			SaveDoors();
			SaveKey();
			SaveEnemy();
			SaveLayout();
			outFile.close();
		} catch (IOException e) {e.printStackTrace();} }

	private void SaveBoardInfo(){
		int aux; char[][] map=m.getMap();
		if((aux=map.length)<10){outFile.print("0"+aux+"x");}
		else {outFile.print(aux+"x");}
		if((aux=map[0].length)<10){outFile.print("0"+aux+";");}
		else {outFile.print(aux+";");}
		outFile.println(""+(this.m.getMapType()-1));}

	private void SaveHeroInfo(){
		outFile.print(h.getBasher()+";");
		if(h.getHero()[0]<10){
			outFile.print("0"+h.getHero()[0]+",");
		}
		else{
			outFile.print(""+h.getHero()[0]+",");
		}
		
		if(h.getHero()[1]<10){
			outFile.print("0"+h.getHero()[1]);
		}
		else{
			outFile.print(""+h.getHero()[1]);
		}
		outFile.println("");
		}

	private void SaveDoors(){
		int [][] doors=m.getDoors();
		outFile.print(doors.length+"");
		for(int i=0;i<doors.length;i++) {
			outFile.print(";");
		     
			if(doors[i][0]<10){
				outFile.print("0"+doors[i][0]);
			}
			else{
				outFile.print(doors[i][0]);
			}
			outFile.print(",");

			if(doors[i][1]<10){
				outFile.print("0"+doors[i][1]);
			}
			else{
				outFile.print(doors[i][1]);
			}
		}
		outFile.println("");}

	private void SaveKey() {
		
		if(m.getKeyPos()[0]<10){
			outFile.print("0"+m.getKeyPos()[0]+",");
		}
		else{
			outFile.print(m.getKeyPos()[0]+",");
		}
		
		if(m.getKeyPos()[1]<10){
			outFile.print("0"+m.getKeyPos()[1]);
		}
		else{
			outFile.print(""+m.getKeyPos()[1]);
		}
		outFile.println("");
		}

	private void SaveEnemy(){
		if(this.m.getMapType()==2){
			if(o[0].getOgre()[0]<10){
				outFile.print("0"+o[0].getOgre()[0]+",");
			}
			else{
				outFile.print(o[0].getOgre()[0]+",");
			}
			
			if(o[0].getOgre()[1]<10){
				outFile.print("0"+o[0].getOgre()[1]);
			}
			else{
				outFile.print(""+o[0].getOgre()[1]);
			}
			
			if(o[0].getBat()[0]==o[0].getOgre()[0]+1) {outFile.println(","+"s"); }
			if(o[0].getBat()[0]==o[0].getOgre()[0]-1) {outFile.println(","+"n"); }
			if(o[0].getBat()[1]==o[0].getOgre()[1]+1) {outFile.println(","+"e"); }
			if(o[0].getBat()[1]==o[0].getOgre()[1]-1) {outFile.println(","+"o"); } }
		 }

	private void SaveLayout() {
		char[][] map=m.getMap();
		for (int i=0;i<map.length;i++) {
			for (int j=0;j<map[i].length;j++) {
				if(map[i][j]=='X' || map[i][j]=='I') {outFile.print('X'); }
				else {outFile.print(' '); } }
			if(i!=map.length-1){outFile.println("");} } } }