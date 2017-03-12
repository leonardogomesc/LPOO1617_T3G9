package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.Guard;
import dkeep.logic.Hero;
import dkeep.logic.Map;
import dkeep.logic.Ogre;

public class TestKeepGameLogic {
	
	
  char map[][]={{'X','X','X','X','X'},
				{'X','A',' ','O','X'},
				{'I',' ',' ','*','X'},
				{'I','k',' ',' ','X'},
				{'X','X','X','X','X'}};

int doors[][]={{2,0},{3,0}};
int keypos[]={3,1};
int ogre[]={1,3};
int bat[]= {2,3};
int hero[]={1,1};

@Test
public void testMoveHeroIntoFreeCell(){
	  Map m=new Map(map,doors,keypos,2);
	  Hero h=new Hero(hero,1);
	  Ogre o=new Ogre(ogre,bat);
	
	  assertEquals(1,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
	  
	  h.HeroMove(m, "s");
	  
	  assertEquals(2,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
}

@Test
public void testMoveHeroIntoWall(){
	  Map m=new Map(map,doors,keypos,2);
	  Hero h=new Hero(hero,1);
	  Ogre o=new Ogre(ogre,bat);
	
	  assertEquals(1,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
	  
	  h.HeroMove(m, "a");
	  
	  assertEquals(1,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
}

@Test
public void testHeroStunsOgre(){
	
	 Map m=new Map(map,doors,keypos,2);
	  Hero h=new Hero(hero,1);
	  Ogre o=new Ogre(ogre,bat);
	  Ogre ogre[]={o};
	 Game game=new Game(m,h,ogre);
	 
	 assertEquals(game.getMap().getMap()[o.getOgre()[0]][o.getOgre()[1]], 'O');
	 
	 h.HeroMove(m, "d");
	 
	 game.losscheckkeep();
	 
	 assertEquals(game.getMap().getMap()[o.getOgre()[0]][o.getOgre()[1]], '8');
}

@Test
public void testHeroIsKilledByBat(){
	 Map m=new Map(map,doors,keypos,2);
	  Hero h=new Hero(hero,1);
	  Ogre o=new Ogre(ogre,bat);
	  Ogre ogre[]={o};
	 Game game=new Game(m,h,ogre);
	 
	 assertEquals(0 , game.losscheckkeep());
	 
	 h.HeroMove(m, "d");
	 h.HeroMove(m, "s");
	 
	 
	 assertEquals(1 , game.losscheckkeep());
}

@Test
public void testMoveHeroIntoClosedDoor(){
	  Map m=new Map(map,doors,keypos,2);
	  Hero h=new Hero(hero,1);
	  Ogre o=new Ogre(ogre,bat);
	  Ogre ogre[]={o};
	  Game game=new Game(m,h,ogre);
	
	  assertEquals(1,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
	  
	  h.HeroMove(m, "s");
	  
	  assertEquals(2,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
	  
	  h.HeroMove(m, "a");
	  
	  
	  assertEquals(2,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
}

@Test
public void testMoveHeroIntoKey(){
	  Map m=new Map(map,doors,keypos,2);
	  Hero h=new Hero(hero,1);
	  Ogre o=new Ogre(ogre,bat);
	  Ogre ogre[]={o};
	  Game game=new Game(m,h,ogre);
	  
	  assertEquals('A',m.getMap()[h.getHero()[0]][h.getHero()[1]]);
	  assertEquals('k',m.getMap()[m.getKeyPos()[0]][m.getKeyPos()[1]]);
	
	  h.HeroMove(m, "s");
	  h.HeroMove(m, "s");  
	  
	  assertEquals('K',m.getMap()[m.getKeyPos()[0]][m.getKeyPos()[1]]);
}

@Test
public void testHeroOpenDoor(){
	  Map m=new Map(map,doors,keypos,2);
	  Hero h=new Hero(hero,1);
	  Ogre o=new Ogre(ogre,bat);
	  Ogre ogre[]={o};
	  Game game=new Game(m,h,ogre);
	  
	  
	  assertEquals('I',m.getMap()[3][0]);
	  
	  h.HeroMove(m, "s");	  
	  h.HeroMove(m, "s");
	  
	  assertEquals('I',m.getMap()[3][0]);
	  
	  h.HeroMove(m, "a");
	  
	  assertEquals('S',m.getMap()[3][0]);
	  
}

@Test
public void testVictory(){
	  Map m=new Map(map,doors,keypos,2);
	  Hero h=new Hero(hero,1);
	  Ogre o=new Ogre(ogre,bat);
	  Ogre ogre[]={o};
	  Game game=new Game(m,h,ogre);
	  
	  
	  assertEquals(0 , game.wincheck());
	  
	  h.HeroMove(m, "s");	  
	  h.HeroMove(m, "s");
	  h.HeroMove(m, "a");
	  h.HeroMove(m, "a");
	  
	  assertEquals(1 , game.wincheck());
	  
}
	
}
