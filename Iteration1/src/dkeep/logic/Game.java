package dkeep.logic;

import java.util.Random;

public class Game {

	private Guard g;
	private Hero h;
	private Map m;
	private Ogre[] o;
	
	public Game(Map m2,Hero h2, Guard g2){
		m=m2;
		h=h2;
		g=g2;
		
	}
	

	public Game(Map m2,Hero h2, Ogre[] o2){
		m=m2;
		h=h2;
		o=o2;
	}
	
	
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
		
		return result;

	}
	
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
	}

}
