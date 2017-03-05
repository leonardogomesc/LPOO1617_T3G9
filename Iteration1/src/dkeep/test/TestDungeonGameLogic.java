package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

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

public void testMoveHeroIntoFreeCell(){
	  Map m=new Map(map,doors,keypos);
	  Hero h=new Hero(hero,0);
	  int CellPosition1[]={1,1};
	  int CellPosition2[]={2,1};
	  
	  assertEquals(CellPosition1,h.getHero());
	 
	  
	  
}
}
