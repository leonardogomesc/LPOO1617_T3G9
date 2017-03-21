package dkeep.logic;

import java.util.Random;

public class Ogre
{
	private int[] pos;
	private int[] batPos;
	private int stunned;
	private boolean Okey;
	private boolean Bkey;

	public int[] getPos() {
		return pos;
	}

	public char getSymbol() {
		if (Okey)
			return '$';
		if(0!=stunned)
			return '8';
		return '0';
	}

	public char getBatSymbol() {
		if(Bkey)
			return '$';
		return '*';
	}

	public int[] getBatPos() {
		return batPos;
	}

	private void placeBat(char batPos)
	{
		switch(batPos)
		{
		case 'n':
			this.batPos[0]=pos[0]-1;
			this.batPos[1]=pos[1];
			break;
		case 's':
			this.batPos[0]=pos[0]+1;
			this.batPos[1]=pos[1];
			break;
		case 'e':
			this.batPos[0]=pos[0];
			this.batPos[1]=pos[1]+1;
			break;
		case 'w':
			this.batPos[0]=pos[0];
			this.batPos[1]=pos[1]-1;
			break;
		}
	}

	public Ogre(int[] pos, String batPos)
	{
		
		this.pos=pos;
		this.stunned=0;
		this.Okey=false;
		this.Bkey=false;
		this.batPos= new int[2];
		this.placeBat(batPos.toCharArray()[0]);

	}

	private boolean validContent(char content)
	{
		if(('X'==content)||('I'==content)||('S'==content))
			return false;
		return true;
	}
	
	private char[] newMove(Map map)
	{
		Random rand = new Random();
		int i;
		char content='\0', dir='\0';
		while(true)
		{
			i= rand.nextInt(4);

			switch(i)
			{
			case 0:		// North
				content=map.checkCellContent(new int[] {this.pos[0]-1,this.pos[1]});
				if(this.validContent(content))
					dir='n';
				break;
			case 1:		// South
				content=map.checkCellContent(new int[] {this.pos[0]+1,this.pos[1]});
				if(this.validContent(content))
					dir='s';
				break;
			case 2:		// East
				content=map.checkCellContent(new int[] {this.pos[0],this.pos[1]+1});
				if(this.validContent(content))
					dir='e';
				break;
			case 3:		// West
				content=map.checkCellContent(new int[] {this.pos[0],this.pos[1]-1});
				if(this.validContent(content))
					dir='w';
				break;
			}
			return new char[] {content, dir};
		}
	}
	
	public void setStunned()
	{
		stunned=3;
	}

	protected void move(Map map)
	{
		char[] aux=newMove(map);
		char dir=aux[1], content=aux[0]; 
		if(0==stunned)
		{
			switch(dir)
			{
			case 'n':		// North
				this.pos[0]=this.pos[0]-1;
				if('k'==content)
					Okey=true;
				else Okey=false;
				break;
			case 's':		// South
				this.pos[0]=this.pos[0]+1;
				if('k'==content)
					Okey=true;
				else Okey=false;
				break;
			case 'e':		// East
				this.pos[1]=this.pos[1]+1;
				if('k'==content)
					Okey=true;
				else Okey=false;
				break;
			case 'w':		// West
				this.pos[1]=this.pos[1]-1;
				if('k'==content)
					Okey=true;
				else Okey=false;
				break;
			}
			
		}
		else
			this.stunned--;
		moveBat(map);
		return;
	}

	private void moveBat(Map map)
	{
		char[] aux=newMove(map);
		char dir=aux[1], content=aux[0];

		switch(dir)
		{
		case 'n':		// North
			placeBat(dir);
			if('k'==content)
				Bkey=true;
			else Bkey=false;
			return;
		case 's':		// South
			placeBat(dir);
			if('k'==content)
				Bkey=true;
			else Bkey=false;
			return;
		case 'e':		// East
			placeBat(dir);
			if('k'==content)
				Bkey=true;
			else Bkey=false;
			return;
		case 'w':		// West
			placeBat(dir);
			if('k'==content)
				Bkey=true;
			else Bkey=false;
			return;
		}
	}
}
