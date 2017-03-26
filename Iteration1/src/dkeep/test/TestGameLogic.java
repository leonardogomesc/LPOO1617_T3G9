package dkeep.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.Guard;
import dkeep.logic.Hero;
import dkeep.logic.Map;
import dkeep.logic.Ogre;

public class TestGameLogic {
	
	
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


char map2[][]={{'X','X','X','X','X'},
		{'X','H',' ','G','X'},
		{'I',' ',' ',' ','X'},
		{'I','k',' ',' ','X'},
		{'X','X','X','X','X'}};

int doors2[][]={{2,0},{3,0}};
int keypos2[]={3,1};
int guard2[][]={{1,3}};
int hero2[]={1,1};


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
	 
	 game.setStunned();
	 
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


@Test
public void testMoveHeroIntoFreeCell2(){
	  Map m=new  Map(map2,doors2,keypos2,1);
	  Hero h=new Hero(hero2,0);
	  Guard g=new Guard(guard2,0);
	
	  assertEquals(1,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
	  
	  h.HeroMove(m, "s");
	  g.GuardMove(m);
	  
	  assertEquals(2,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
}

@Test
public void testMoveHeroIntoWall2(){
	  Map m=new Map(map2,doors2,keypos2,1);
	  Hero h=new Hero(hero2,0);
	  Guard g=new Guard(guard2,0);
	
	  assertEquals(1,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
	  
	  h.HeroMove(m, "a");
	  g.GuardMove(m);
	  
	  assertEquals(1,h.getHero()[0]);
	  assertEquals(1,h.getHero()[1]);
}

@Test
public void testHeroIsCapturedByGuard(){
	 Map m=new  Map(map2,doors2,keypos2,1);
	 Hero h=new Hero(hero2,0);
	 Guard g=new Guard(guard2,0);
	 Game game=new Game(m,h,g);
	 
	 assertEquals(0 , game.losscheck());
	 
	 h.HeroMove(m, "d");
	 g.GuardMove(m);
	 
	 assertEquals(1 , game.losscheck());
}

@Test
public void testMoveHeroIntoClosedDoor2(){
	  Map m=new Map(map2,doors2,keypos2,1);
	  Hero h=new Hero(hero2,0);
	  Guard g=new Guard(guard2,0);
	
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
	  Map m=new  Map(map2,doors2,keypos2,1);
	  Hero h=new Hero(hero2,0);
	  Guard g=new Guard(guard2,0);
	  
	  
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
	  Map m=new Map(map2,doors2,keypos2,1);
	  Hero h=new Hero(hero2,0);
	  Guard g=new Guard(guard2,0);
	  
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
