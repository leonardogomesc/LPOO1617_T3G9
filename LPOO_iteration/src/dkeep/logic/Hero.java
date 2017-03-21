package dkeep.logic;

public class Hero {
	private int[] pos;
	private boolean basher;
	private boolean key;
	private int levelType;
	
	public Hero(int levelType)
	{
		this.pos= new int[2];
		this.levelType=levelType;
		if(0==this.levelType)
			basher=false;
		else
			basher=true;
	}
	
	public void setKey(boolean value)
	{
		this.key=value;
	}
	
	public
	
	boolean getKey()
	{
		return key;
	}
	
	public int[] getPos()
	{
		return pos;
	}

	protected void setPos(int x, int y)
	{
		pos[0]=x;
		pos[1]=y;
	}
	
	public char getSymbol()
	{
		if(key)
			return 'K';
		else
			if (basher)
				return 'A';
		return 'H';
	}
	
	protected boolean move(Map map, char direction)
	{
		int[] aux =new int[] { pos[0],pos[1]};
		switch(direction)
		{
		case 'n':
			aux[0]=this.pos[0]-1;
			break;
		case 'w':
			aux[1]=this.pos[1]-1;
			break;
		case 's':
			aux[0]=this.pos[0]+1;
			break;
		case 'e':
			aux[1]=this.pos[1]+1;
			break;
		}
		char content = map.checkCellContent(aux);
		if(('X'==content) || ('\0'==content) )
			return false;		
		if('I'==content)
			if(key && (1==levelType))
			{
				map.openDoors();
				return true;
			}		
			else
				return false;				
		if('k'==content)
			this.key=true;		
		pos=aux;
		return true;
	}

	public int stateCheck(Map map)
	{
		/*
		 * returns 1 on win, 2 on loss and 0 otherwise
		 */
		
		int[] keyPos=map.getKey();
		int[][] doorPos = map.getDoors();
		
		if(this.pos==keyPos)
		{
			this.setKey(true);
		}
		for(int[] door:doorPos)
		{
			if((this.pos[0]==door[0]) && (this.pos[1]==door[1]))
				return Game.WIN;
		}
		if(0==this.levelType)
		{
			Guard guard=map.getGuard();
			if(this.pos==guard.getPos() ||
					((this.pos[0]-1==guard.getPos()[0]) &&((this.pos[1]==guard.getPos()[1])))|| 
					((this.pos[0]+1==guard.getPos()[0]) &&((this.pos[1]==guard.getPos()[1])))||
					((this.pos[0]==guard.getPos()[0]) &&((this.pos[1]+1==guard.getPos()[1])))||
					((this.pos[0]==guard.getPos()[0]) &&((this.pos[1]-1==guard.getPos()[1]))))
				if(!guard.getAsleep())
					return Game.LOSS;
		}
		else
		{
			Ogre[] ogre=map.getOgre();
			for(int i=0;i<ogre.length;i++)
			{
				if(((this.pos[0]-1==ogre[i].getPos()[0]) &&((this.pos[1]==ogre[i].getPos()[1])))|| 
						((this.pos[0]+1==ogre[i].getPos()[0]) &&((this.pos[1]==ogre[i].getPos()[1])))||
						((this.pos[0]==ogre[i].getPos()[0]) &&((this.pos[1]+1==ogre[i].getPos()[1])))||
						((this.pos[0]==ogre[i].getPos()[0]) &&((this.pos[1]-1==ogre[i].getPos()[1]))))
					if(basher)
						ogre[i].setStunned();
					else
						return Game.LOSS;
				if((this.pos==ogre[i].getPos())||(this.pos==ogre[i].getBatPos())||
						((this.pos[0]-1==ogre[i].getBatPos()[0]) &&((this.pos[1]==ogre[i].getBatPos()[1])))|| 
						((this.pos[0]+1==ogre[i].getBatPos()[0]) &&((this.pos[1]==ogre[i].getBatPos()[1])))||
						((this.pos[0]==ogre[i].getBatPos()[0]) &&((this.pos[1]+1==ogre[i].getBatPos()[1])))||
						((this.pos[0]==ogre[i].getBatPos()[0]) &&((this.pos[1]-1==ogre[i].getBatPos()[1]))))
					return Game.LOSS;
			}

		}
		return 0;
	}
}
