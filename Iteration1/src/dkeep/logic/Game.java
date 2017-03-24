package dkeep.logic;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Game {

	/*
	 *variables used in file input
	 *(not used outside this class)
	 */
	private Path path;
	private final static Charset ENCODING = StandardCharsets.UTF_8; 
	private String levelName;
	private Scanner in;
	
	
	private Guard g;
	private Hero h;
	private Map m;
	private Ogre[] o;
	private int currentLevel;
	private Vector<String> levels;
	
	/*public Game(Map m2,Hero h2, Guard g2){
		m=m2;
		h=h2;
		g=g2;
		
	}
	

	public Game(Map m2,Hero h2, Ogre[] o2){
		m=m2;
		h=h2;
		o=o2;
	}*/
	
	public Game()
	{
		this.levels=new Vector<String>();
		levels.clear();
		this.levels.add("level1");
		this.levels.add("keep");
		this.currentLevel=-1;
	}
	
	public int nextLevel(int[] info) throws IOException
	{
		this.currentLevel=this.currentLevel+1;
		if(this.currentLevel>=this.levels.size()){
			return 0;
		}
		else{
		setGame(levels.get(this.currentLevel), info);
		return 1;
		}
	}
	
	private void setGame(String levelName, int[] info) throws IOException
	{
		
		this.levelName=levelName+".map";
		this.openFile();
		
		this.processFile(info);		
		this.setPlaces();
		
	}

	private void openFile() throws IOException
	{
		path = Paths.get("src/dkeep/logic/levels/"+levelName);
		this.in =  new Scanner(path, ENCODING.name());		
	}
		
	private void processFile(int[] info)
{
		int keyPos[]= new int[2];
		String bat;
		
		in.useDelimiter("\n");
		String line=in.next();
		int[] coord = new int[2];
		int[] coordh = new int[2];
		int n;
		
		//board size
		int[] size= new int[2];
		size[0]= Integer.parseInt(line.substring(0, 2));
		size[1]=Integer.parseInt(line.substring(3, 5));
		char map[][]=new char[size[0]][size[1]];
		
		//level type
		int type= Integer.parseInt(line.substring(6, 7));
		
		if(1==type)
			o=new Ogre[info[1]];
		//next line
		line=in.next();
		
		//hero position		
		coordh[0]=Integer.parseInt(line.substring(0, 1));
		coordh[1]=Integer.parseInt(line.substring(2, 3));
		
		h=new Hero(coordh,type);
		
		//next line
				
		line=in.next();
		n=Integer.parseInt(line.substring(0, line.indexOf(';')));
		int doorPos[][]= new int[n][2];
		//door position
		for(int i=0; i<n;i++)
		{
			line=line.substring(line.indexOf(';')+1);
			coord[0]=Integer.parseInt(line.substring(0, line.indexOf(',')));
			
			if (i+1==n)
				coord[1]=Integer.parseInt(line.substring(line.indexOf(',')+1,line.indexOf(',')+2));
			else
				coord[1]=Integer.parseInt(line.substring(line.indexOf(',')+1,line.indexOf(';')));
			doorPos[i][0]=coord[0];
			doorPos[i][1]=coord[1];
		}
		
		
		//next line
		line=in.next();
		
		//key position
		coord[0]=Integer.parseInt(line.substring(0, 1));
		coord[1]=Integer.parseInt(line.substring(2, 3));
		keyPos[0]=coord[0];
		keyPos[1]=coord[1];
		
		//next line
		line=in.next();
		
		//guard|ogre
		if(0==type)
		{
			n=Integer.parseInt(line.substring(0,line.indexOf(';')));
			int[][] guardPos= new int[n][2];
			for(int i=0; i<n;i++)
			{
				line=line.substring(line.indexOf(';')+1);
				coord[0]=Integer.parseInt(line.substring(0, line.indexOf(',')));
				if (i+1==n)
					coord[1]=Integer.parseInt(line.substring(line.indexOf(',')+1,line.indexOf(',')+2));
				else
					coord[1]=Integer.parseInt(line.substring(line.indexOf(',')+1,line.indexOf(';')));
				
				guardPos[i][0]=coord[0];
				guardPos[i][1]=coord[1];
			}
			g = new Guard(guardPos,info[0]);
			o=null;
		}
		else
			if(1==type)
			{
				coord[0]=Integer.parseInt(line.substring(0,line.indexOf(',')));
				line=line.substring(line.indexOf(',')+1);
				coord[1]=Integer.parseInt(line.substring(0,line.indexOf(',')));
				line=line.substring(line.indexOf(',')+1,line.indexOf(',')+2);
				
				for(int i=0;i<o.length;i++){
					bat=new String(line.substring(line.indexOf(',')+1));
				
				switch(bat){
				case "n":
					o[i]=new Ogre(new int[] {coord[0],coord[1]}, new int[] {coord[0]-1,coord[1]});
					break;
				case "s":
					o[i]=new Ogre(new int[] {coord[0],coord[1]}, new int[] {coord[0]+1,coord[1]});
					break;
				case "e":
					o[i]=new Ogre(new int[] {coord[0],coord[1]}, new int[] {coord[0],coord[1]+1});
					break;
				case "o":
					o[i]=new Ogre(new int[] {coord[0],coord[1]}, new int[] {coord[0],coord[1]-1});
				    break;
				}
				}
				g=null;
			}
		
		//wall layout
					
		for(int j=0; j<size[0];j++)
		{
			line=in.next();
			char[] arrayLine=line.toCharArray();
			for (int i=0;i<size[1];i++)
			{
				
				map[j][i]= arrayLine[i];
			}
		}
		
		m=new Map(map,doorPos,keyPos,type+1);
		
		in.close();
		
	}

	private void setPlaces()
	{
		//hero
		if(h.getBasher()==0){
		m.getMap()[h.getHero()[0]][h.getHero()[1]]='H';
		}
		else if(h.getBasher()==1){
			m.getMap()[h.getHero()[0]][h.getHero()[1]]='A';
		}
		
		//guard
		if (null!=g)
		{
			m.getMap()[g.getGuard()[g.getGuardpos()][0]][g.getGuard()[g.getGuardpos()][1]]='G';
		}
		
		//ogre
		if (null != o)
		{
			for(int i=0;i<o.length;i++)
			{
				m.getMap()[o[i].getOgre()[0]][o[i].getOgre()[1]]='O';
				m.getMap()[o[i].getBat()[0]][o[i].getBat()[1]]='*';
			}
		}
		
		//key
		m.getMap()[m.getKeyPos()[0]][m.getKeyPos()[1]]='k';
		
		//door
		for(int i=0;i<m.getDoors().length;i++)
		{
			m.getMap()[m.getDoors()[i][0]][m.getDoors()[i][1]]='I';
		}
	}
	
	////////////////////////////////////
	public Guard getGuard() {
		return g;
	}

	public Hero getHero() {
		return h;
	}

	public Map getMap() {
		return m;
	}

	
	public Ogre[] getOgre() {
		return o;
	}

	public int wincheck(){

		int hero[]=h.getHero();
		int doors[][]=m.getDoors();
		int result=0;

		for(int i =0;i<doors.length;i++){	
			if(hero[0]==doors[i][0] && hero[1]==doors[i][1]){
				result=1;
			}
		}

		return result;
	}

	public int losscheck(){
		int hero[]=h.getHero();
		int guard[][]=g.getGuard();
		int guardpos=g.getGuardpos();
		char map[][]=m.getMap();
		int result=0;

		if (map[guard[guardpos][0]][guard[guardpos][1]]=='G'){
			
		if((hero[0]==guard[guardpos][0] && hero[1]==guard[guardpos][1]+1)||
				(hero[0]==guard[guardpos][0]-1 && hero[1]==guard[guardpos][1])||
				(hero[0]==guard[guardpos][0] && hero[1]==guard[guardpos][1]-1)||
				(hero[0]==guard[guardpos][0]+1 && hero[1]==guard[guardpos][1])||
				(hero[0]==guard[guardpos][0] && hero[1]==guard[guardpos][1])){
			result=1;
		}
		}

		return result;

	}

	public int losscheckkeep(){
		int hero[]=h.getHero();
		int ogre[];
		int bat[];
		char map[][]=m.getMap();
		int result =0;
		
		for(int i=0;i<o.length;i++){
			
	       ogre=o[i].getOgre();
		   bat=o[i].getBat();
	
		if (h.getBasher()==0){
			if((hero[0]==ogre[0] && hero[1]==ogre[1]+1)||
					(hero[0]==ogre[0]-1 && hero[1]==ogre[1])||
					(hero[0]==ogre[0] && hero[1]==ogre[1]-1)||
					(hero[0]==ogre[0]+1 && hero[1]==ogre[1])||
					(hero[0]==ogre[0] && hero[1]==ogre[1]) ||
					(hero[0]==bat[0] && hero[1]==bat[1]+1)||
					(hero[0]==bat[0]-1 && hero[1]==bat[1])||
					(hero[0]==bat[0] && hero[1]==bat[1]-1)||
					(hero[0]==bat[0]+1 && hero[1]==bat[1])||
					(hero[0]==bat[0] && hero[1]==bat[1])){
				result=1;
			}
		}
		else if(h.getBasher()==1){

			if(	(hero[0]==bat[0] && hero[1]==bat[1]+1)||
					(hero[0]==bat[0]-1 && hero[1]==bat[1])||
					(hero[0]==bat[0] && hero[1]==bat[1]-1)||
					(hero[0]==bat[0]+1 && hero[1]==bat[1])||
					(hero[0]==bat[0] && hero[1]==bat[1])){
				result=1;
			}
		}
		
		
		}
		
		return result;

	}
	
	
	//Ogre
	
	public void OgreMove(){
		
		int i;
		
		for(i=0;i<o.length;i++){
			
			o[i].OgreErase(m);
		}
		
		if(m.getKey() == 1){
			m.getMap()[m.getKeyPos()[0]][m.getKeyPos()[1]]='k';
		}
		
		for(i=0;i<o.length;i++){
			o[i].OgreMove(m);
		}
		
		setStunned();
	}
	
	public void setStunned(){
		if(h.getBasher()==1){
		
		for(int i=0;i<o.length;i++){
			
		int hero[]=h.getHero();
		int ogre[]=o[i].getOgre();
		char map[][]=m.getMap();
		
		if((hero[0]==ogre[0] && hero[1]==ogre[1]+1)||
				(hero[0]==ogre[0]-1 && hero[1]==ogre[1])||
				(hero[0]==ogre[0] && hero[1]==ogre[1]-1)||
				(hero[0]==ogre[0]+1 && hero[1]==ogre[1])||
				(hero[0]==ogre[0] && hero[1]==ogre[1])){
			o[i].setStunned();
			map[ogre[0]][ogre[1]]='8';
		}
		
		}
		
	   }
	}
}
