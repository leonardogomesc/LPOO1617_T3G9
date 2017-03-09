package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.cli.Game;
import dkeep.logic.Guard;
import dkeep.logic.Hero;
import dkeep.logic.Map;

public class TestDungeonGameLogic {

	 char map[][]={{'X','X','X','X','X'},
				{'X','H',' ','G','X'},
				{'I',' ',' ',' ','X'},
				{'I','k',' ',' ','X'},
				{'X','X','X','X','X'}};

int doors[][]={{2,0},{3,0}};
int keypos[]={3,1};
int guard[][]={{1,3}};
int hero[]={1,1};

@Test
public void testMoveHeroIntoFreeCell(){
	  Map m=new Map(map,doors,keypos,1);
	  Hero h=new Hero(hero,0);
	  Guard g=new Guard(guard,1);
	
	  assertEquals(1,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
	  
	  h.HeroMove(m, "s");
	  g.GuardMove(m);
	  
	  assertEquals(2,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
}

@Test
public void testMoveHeroIntoWall(){
	  Map m=new Map(map,doors,keypos,1);
	  Hero h=new Hero(hero,0);
	  Guard g=new Guard(guard,1);
	
	  assertEquals(1,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
	  
	  h.HeroMove(m, "a");
	  g.GuardMove(m);
	  
	  assertEquals(1,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
}

@Test
public void testHeroIsCapturedByGuard(){
	 Map m=new Map(map,doors,keypos,1);
	 Hero h=new Hero(hero,0);
	 Guard g=new Guard(guard,1);
	 Game game=new Game();
	 
	 assertEquals(0 , game.losscheck(h, g, m));
	 
	 h.HeroMove(m, "d");
	 g.GuardMove(m);
	 
	 assertEquals(1 , game.losscheck(h, g, m));
}

@Test
public void testMoveHeroIntoClosedDoor(){
	  Map m=new Map(map,doors,keypos,1);
	  Hero h=new Hero(hero,0);
	  Guard g=new Guard(guard,1);
	
	  assertEquals(1,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
	  
	  h.HeroMove(m, "s");
	  g.GuardMove(m);
	  
	  assertEquals(2,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
	  
	  h.HeroMove(m, "a");
	  g.GuardMove(m);
	  
	  assertEquals(2,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
}

@Test
public void testMoveHeroIntoLever(){
	  Map m=new Map(map,doors,keypos,1);
	  Hero h=new Hero(hero,0);
	  Guard g=new Guard(guard,1);
	  
	  
	  assertEquals('I',m.getMap()[2][0]);
	  assertEquals('I',m.getMap()[3][0]);
	
  
	  h.HeroMove(m, "s");
	  g.GuardMove(m);
	  
	  h.HeroMove(m, "s");
	  g.GuardMove(m);
	  
	  
	  assertEquals('S',m.getMap()[2][0]);
	  assertEquals('S',m.getMap()[3][0]);
}

@Test
public void testMoveHeroIntoOpenDoor(){
	  Map m=new Map(map,doors,keypos,1);
	  Hero h=new Hero(hero,0);
	  Guard g=new Guard(guard,1);
	  
	  h.HeroMove(m, "s");
	  g.GuardMove(m);
	  
	  h.HeroMove(m, "s");
	  g.GuardMove(m);
	  
	  h.HeroMove(m, "a");
	  g.GuardMove(m);
	  
	  
	  assertEquals(3,h.getHero()[0]);
	  assertEquals(0,h.getHero()[1]);
}
}
