package dkeep.cli;

import dkeep.logic.Map;

public class Output {

	public void output(Map m){
		
		char map[][]=m.getMap();
				
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		for(int i1=0;i1<map.length;i1++){
			for(int i2=0;i2<map[i1].length;i2++){
				System.out.print(map[i1][i2]);
				System.out.print(" ");
			}
			System.out.println("");
		}
		
	}
}
