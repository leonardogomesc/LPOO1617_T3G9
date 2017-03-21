package dkeep.logic;

import java.io.IOException;
import java.util.Vector;

import dkeep.cli.Interface;

public class Game {
	
	public static final int WIN = 1;
	public static final int LOSS=2;
	private Vector<String>levels;
	private int currentLevel;
	private Map map;
	
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
		if(this.currentLevel>=this.levels.size())
			return 0;
		this.map=new Map(this.levels.get(this.currentLevel), info);
		return 1;
	}
	
	public int moveHero(char direction)
	{
		return map.moveHero(direction);
	}
	public void printMap()
	{
		Interface.clearScrn();
		System.out.print(this.map.toString());
	}
	public String getMap()
	{
		return this.map.toString();
	}
}
