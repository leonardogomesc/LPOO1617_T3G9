package dkeep.logic;

import java.util.Random;

public class Guard {
	private int type; //0-rookie|1-drunken|2-suspicious
	private int[][] path;
	private int position;
	private int step;
	private boolean asleep;
	public int getType() {
		return type;
	}
	public Guard(int[][] path)
	{
		this.path=path;
		this.position=0;
		this.step=1;
		this.asleep=false;
		type=0;
	}

	public void setType(int type) {
		this.type = type;
	}

	public char getSymbol() {
		if(asleep)
			return 'g';
		return 'G';
	}
	public int[][] getPath() {
		return this.path;
	}
	
	public boolean getAsleep()
	{
		return this.asleep;
	}

	protected void move(){
		Random rand = new Random();
		int i= rand.nextInt(5);
		int max=path.length;

		if(0==this.type)
			position++;
		else
			if(1==this.type)
			{
				boolean prev=asleep;
				if(0==i)
					this.asleep=!this.asleep;
				if((prev!=asleep)&&(0==(rand.nextInt(5))))
					step=0-step;
				if(!asleep)
					position=position+step;
			}
			else
				if(2==this.type)
				{
					if(0==(rand.nextInt(5)))
						step=0-step;
					position=position+step;
				}
		if (position==max)
			position=0;
		if (-1==this.position)
			position=max-1;
	}
	public int[] getPos()
	{
		return path[position];
	}
}
