public class Maps{	
	
	//Map 1
	public static char[][] Map1(){ 	
		return new char[][]{
			{'0','0','0','0','0','0','0','0','0','0'},
			{'0','1','1','1','1','1','1','1','0','0'},	
			{'0','1','0','0','0','1','0','1','0','0'},	
			{'0','1','0','0','0','1','1','1','0','0'},	
			{'0','1','1','1','1','1','1','1','0','0'},	
			{'0','1','0','0','0','1','1','1','0','0'},	
			{'0','1','1','1','1','1','0','1','0','0'},	
			{'0','1','0','0','0','1','0','1','0','0'},	
			{'0','1','1','1','1','1','1','1','0','0'},	
			{'0','0','0','0','0','0','0','0','0','0'},	
		};
		
	}
	
	//Map 2
	public static char[][] Map2(){ 	
		return new char[][]{
			{'0','0','0','0','0','0','0','0','0','0'},
			{'0','0','1','1','1','1','0','0','0','0'},	
			{'0','0','1','0','1','1','0','0','0','0'},	
			{'0','0','1','0','1','1','1','1','1','0'},	
			{'0','0','1','0','1','1','0','1','1','0'},	
			{'0','0','1','0','1','1','0','1','1','0'},	
			{'0','0','1','0','1','1','0','1','1','0'},	
			{'0','0','1','1','1','1','1','1','1','0'},	
			{'0','0','0','0','0','0','0','0','0','0'},	
			{'0','0','0','0','0','0','0','0','0','0'},	
		};
		
	}
	
	//Map 3
	public static char[][] Map3(){ 	
		return new char[][]{
			{'0','0','0','0','0','0','0','0','0','0'},	
			{'0','1','1','1','1','1','1','1','1','0'},	
			{'0','1','0','1','0','0','1','0','1','0'},	
			{'0','1','0','1','0','0','1','0','1','0'},	
			{'0','1','0','1','0','0','1','0','1','0'},	
			{'0','1','0','1','1','1','1','0','1','0'},	
			{'0','1','0','1','0','0','1','0','1','0'},	
			{'0','1','0','1','0','0','1','0','1','0'},	
			{'0','1','1','1','1','1','1','1','1','0'},	
			{'0','0','0','0','0','0','0','0','0','0'},	
		};
		
	}
	
	//Map 4
	public static char[][] Map4(){
		return new char[][]{
			{'0','0','0','0','0','0','0','0','0','0'},//X is from top to bottom, y is right	
			{'0','0','0','0','0','0','0','0','0','0'},	
			{'0','1','1','1','0','1','1','1','0','0'},	
			{'0','1','0','1','0','1','0','1','0','0'},	
			{'0','1','0','1','1','1','1','1','1','0'},	
			{'0','1','0','1','0','1','0','0','1','0'},	
			{'0','1','0','1','1','1','1','1','1','0'},	
			{'0','1','0','1','0','0','1','0','0','0'},	
			{'0','1','0','1','1','1','1','0','0','0'},	
			{'0','0','0','0','0','0','0','0','0','0'},	
		};
	}
	
	//Produce a random map
	//Supply this function with a sizex and sizey
	public static char[][] randomMap(int sizex, int sizey ){
		//start in a random point within the grid
		int posx,posy,duration; //duration refers to how many lines should we draw
		posx = (int)(Math.random()*sizex);
		posy = (int)(Math.random()*sizey);
		duration = (int)(Math.random()*(sizex+sizey)+5 );
		//System.out.printf("ranx %d randy %d dur %d\n", posx, posy, duration);
		char[][] map = new char[sizex][sizey];
		//Fill in array
		for(int x=0; x<sizex;x++)
			for(int y=0;y<sizey;y++)
				map[x][y] = '0';
		
		for( int x=0; x < duration; x++){
			int lposx=(sizex-posx),lposy=(sizey-posy),lnegx=(posx),lnegy=(posy); //Length of directions from current postion
			//choose a direction and a draw length
			int dir = 0, length;
			if(x%2==1) dir = 1; //draw horizontally
			//else draw vertical
			
			//What to do if we are drawing horizontally
			if(dir == 1){
				if(lposx > lnegx){ //go postive if we have more space in this direction
						length = (int)(Math.random()*lposx);
						for(int i=0;i<length;posx+=1,i++){
							try{
								map[posx][posy] = '1';
							}catch(ArrayIndexOutOfBoundsException e){ }
						}
							
				} else{ //Go Negative lneg >= lposx
					length = (int)(Math.random()*lnegx);
					for(int i=0;i<length;posx-=1,i++){
						try{
							map[posx][posy] = '1';
						}catch(ArrayIndexOutOfBoundsException e){ }
					}
				}
				
			}			
			//What to do if we are drawing vertically
			else{
				if(lposy > lnegy){ //go postive if we have more space in this direction
					length = (int)(Math.random()*lposy);
					for(int i=0;i<length;posy+=1,i++){
						try{
							map[posx][posy] = '1';
						}catch(ArrayIndexOutOfBoundsException e){ }
					}
				} else{ //Go Negative lneg >= lposx
					length = (int)(Math.random()*lnegy);
					for(int i=0;i<length;posy-=1,i++){
						try{
							map[posx][posy] = '1';
						}catch(ArrayIndexOutOfBoundsException e){ }
					}
					
				}
				
			}
		}
		return map;
	}
	
	
	
}
