package dkeep.logic;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;


public class Map {
	
	/*
	 *variables used in file input
	 *(not used outside this class)
	 */
	private Path path;
	private final static Charset ENCODING = StandardCharsets.UTF_8; 
	private String levelName;
	private Scanner in;
	
	/*
	 * variables used for the map itself
	 */
	private int type; // 0 for dungeon | 1 for keep
	private char[][] map;
	private Hero hero;
	private int[][] doorPos;
	private int[] keyPos;
	private Guard guard;
	private Ogre[] ogre;
	
	public Map(String levelName, int[] info) throws IOException
	{
		
		keyPos= new int[2];
		
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
		in.useDelimiter("\n");
		String line=in.next();
		int[] coord = new int[2];
		int n;
		
		//board size
		int[] size= new int[2];
		size[0]= Integer.parseInt(line.substring(0, 2));
		size[1]=Integer.parseInt(line.substring(3, 5));
		map=new char[size[0]][size[1]];
		
		//level type
		type= Integer.parseInt(line.substring(6, 7));
		
		this.hero=new Hero(type);
		if(1==type)
			ogre=new Ogre[info[1]];
		//next line
		line=in.next();
		
		//hero position		
		coord[0]=Integer.parseInt(line.substring(0, 1));
		coord[1]=Integer.parseInt(line.substring(2, 3));
		hero.setPos(coord[0], coord[1]);
		
		//next line
				
		line=in.next();
		n=Integer.parseInt(line.substring(0, line.indexOf(';')));
		doorPos= new int[n][2];
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
			guard = new Guard(guardPos);
			guard.setType(info[0]);
			ogre=null;
		}
		else
			if(1==type)
			{
				coord[0]=Integer.parseInt(line.substring(0,line.indexOf(',')));
				line=line.substring(line.indexOf(',')+1);
				coord[1]=Integer.parseInt(line.substring(0,line.indexOf(',')));
				line=line.substring(line.indexOf(',')+1,line.indexOf(',')+2);
				
				for(int i=0;i<ogre.length;i++)
					ogre[i]=new Ogre(new int[] {coord[0],coord[1]}, new String(line.substring(line.indexOf(',')+1)));
				guard=null;
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
		
		in.close();
		
	}

	private void setPlaces()
	{
		//hero
		map[hero.getPos()[0]][hero.getPos()[1]]=hero.getSymbol();
		
		//guard
		if (null!=guard)
		{
			map[guard.getPos()[0]][guard.getPos()[1]]=guard.getSymbol();
		}
		
		//ogre
		if (null != ogre)
		{
			for(int i=0;i<ogre.length;i++)
			{
				map[ogre[i].getPos()[0]][ogre[i].getPos()[1]]=ogre[i].getSymbol();
				map[ogre[i].getBatPos()[0]][ogre[i].getBatPos()[1]]=ogre[i].getBatSymbol();
			}
		}
		
		//key
		map[keyPos[0]][keyPos[1]]='k';
		
		//door
		for(int i=0;i<doorPos.length;i++)
		{
			map[doorPos[i][0]][doorPos[i][1]]='I';
		}
	}

	public String toString() {
		String ret= new String("");
		for(int i=0;i<map.length;i++)
		{
			for(int j=0; j<map[i].length;j++)
			{
				ret=ret+" "+map[i][j];
			}
			ret=ret+'\n';
		}
		return ret;
	}

	public int moveHero(char direction)
	{
		
		map[hero.getPos()[0]][hero.getPos()[1]]=' ';
		map[keyPos[0]][keyPos[1]]='k';
		if(!this.checkValid(direction))
		{
			map[hero.getPos()[0]][hero.getPos()[1]]=hero.getSymbol();
			return 0;
		}
		
		
		//move everything if hero moves
		
		if(0==this.type)
		{
			map[guard.getPos()[0]][guard.getPos()[1]]=' ';
			guard.move();
		}
		else
		{
			for(int i=0;i<ogre.length;i++)
			{
				map[ogre[i].getPos()[0]][ogre[i].getPos()[1]]=' ';
				map[ogre[i].getBatPos()[0]][ogre[i].getBatPos()[1]]=' ';
				map[keyPos[0]][keyPos[1]]='k';
				ogre[i].move(this);
			}
			
		}
		int result=hero.stateCheck(this);
		if((hero.getKey()) && (0==this.type))
			this.openDoors();
		else
			if((hero.getKey()) && (1==this.type))
				map[keyPos[0]][keyPos[1]]=' ';
		
		if(0!=result)
			return result;
		
		map[hero.getPos()[0]][hero.getPos()[1]]=hero.getSymbol();
		if(0==this.type)
			map[guard.getPos()[0]][guard.getPos()[1]]=guard.getSymbol();
		else
		{
			for(int i=0;i<ogre.length;i++)
				map[ogre[i].getBatPos()[0]][ogre[i].getBatPos()[1]]=ogre[i].getBatSymbol();
			for(int i=0;i<ogre.length;i++)
				map[ogre[i].getPos()[0]][ogre[i].getPos()[1]]=ogre[i].getSymbol();
		}
		return 0;
	}
	
	protected void openDoors() {
		for(int i=0;i<this.doorPos.length;i++)
		{
			map[doorPos[i][0]][doorPos[i][1]]='S';
		}
		
	}

	private boolean checkValid(char direction)
	{
		boolean ret=false;
		switch(direction)
		{
		case 'w':
			ret=this.hero.move(this, 'n');
			break;
		case 'a':
			ret=this.hero.move(this, 'w');
			break;
		case 's':			
			ret=this.hero.move(this, 's');
			break;
		case 'd':
			ret=this.hero.move(this, 'e');
			break;
		default:
			break;
		}		
		/*
		 * if the move is valid returns true and updates
		 * the hero's position
		 */
		return ret;
	}
	
	public char checkCellContent(int[] pos)
	{
		if((pos[0]>this.map.length) || (pos[1]>this.map[0].length))
			return '\0';
		return map[pos[0]][pos[1]];
	}

	public Hero getHero()
	{
		return this.hero;
	}
	
	public Ogre[] getOgre()
	{
		return this.ogre;
	}
	
	public Guard getGuard()
	{
		return this.guard;
	}
	
	public int[][] getDoors()
	{
		return this.doorPos;
	}
	
	public int[] getKey()
	{
		return this.keyPos;
	}
}
