import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/*
 * This class extends JPanel and takes a size x and size y value
 * Class draws out an array as it is represented as '1' | '0'
 * This draws out a grid to represent a road structure
 */

public class Grid extends JPanel{
private static final long serialVersionUID = 1L;

	/*
		PUT ARRAY STRUCTURE HERE
	 */
	
	//Globals
	private final int SIZEX=10,SIZEY=10; //desired grid size
	public int  scaleX,scaleY; //Physical window size / desired grid size
	private char grid[][]; //Main Grid
	private Vertex start = null;
	private Vertex dest = null;
	private boolean started = false;
	private ArrayList<RouteLine> route; //Holds the Path desired to be colored blue
	private ArrayList<Vertex> Vertices = new ArrayList<Vertex>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	Grid(int x, int y){
		super();
		//[SIZEX] [SIZEY]
		grid = Maps.Map2();
		route = new ArrayList<RouteLine>();		
		this.setBackground( new Color(60,60,60));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5) );
		scaleX = x/SIZEX;
		scaleY = y/SIZEY;	
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int spotX,spotY;
		char down, up, left, right,here;
		g.setColor(Color.WHITE);
		for(int x=0;x<=SIZEX;x++){
				//Nested Loop
				for(int y=0;y<=SIZEY;y++){
					int n = 0;
					spotX = x*scaleX; //Find Position in Panel
					spotY = y*scaleY;
					//Get neighbors If I can try all catch exceptions 'e' for empty
					try{
					down = grid[x][y+1];
					}catch(ArrayIndexOutOfBoundsException e){down='e';}
					try{
					up = grid[x][y-1];
					}catch(ArrayIndexOutOfBoundsException e){up='e';}
					try{
					left = grid[x-1][y];
					}catch(ArrayIndexOutOfBoundsException e){left='e';}
					try{
					right = grid[x+1][y];
					}catch(ArrayIndexOutOfBoundsException e){right='e';}
					try{
						here = grid[x][y];
					}catch(ArrayIndexOutOfBoundsException e){here='e';}
					//System.out.printf("(%c,%c,%c,%c)\n",down,up,left,right);
					//check DOWN
					if(down == '1' && here=='1'){
						g.drawLine(spotX, spotY, spotX, spotY+scaleY);
						edges.add(new Edge(new Vertex(x,y),new Vertex(x,y+1)));
						n++;
					}
					//check UP
					if(up == '1' && here=='1') {
						g.drawLine(spotX, spotY, spotX, spotY-scaleY);
						edges.add(new Edge(new Vertex(x,y),new Vertex(x,y-1)));
						n++;
					}
					//check LEFT
					if(left == '1' && here=='1') {
						g.drawLine(spotX, spotY, spotX-scaleX, spotY);
						edges.add(new Edge(new Vertex(x,y),new Vertex(x-1,y)));
						n++;
					}
					//check RIGHT
					if(right == '1' && here=='1') {
						g.drawLine(spotX, spotY, spotX+scaleX, spotY);
						edges.add(new Edge(new Vertex(x,y),new Vertex(x+1,y)));
						n++;
					}
					
					if(n >= 1){
						if (!started){ //started == false
						Graphics2D g2d = (Graphics2D)g;
						Vertex addV = new Vertex(x,y);
						if(start == null){
							g.setColor(Color.yellow);
							start = addV;
							Ellipse2D.Double circle = new Ellipse2D.Double( (x*scaleX)-5, (y*scaleY)-5, 10, 10);
							g2d.fill(circle);
							g.setColor(Color.white);
						} else if (dest == null) {
							g.setColor(Color.red);
							dest = addV;
							Ellipse2D.Double circle = new Ellipse2D.Double( (x*scaleX)-5, (y*scaleY)-5, 10, 10);
							g2d.fill(circle);
							g.setColor(Color.white);
						} else {
							g.setColor(Color.white);
							Ellipse2D.Double circle = new Ellipse2D.Double( (x*scaleX)-5, (y*scaleY)-5, 10, 10);
							g2d.fill(circle);
						}
						Vertices.add(addV);
						} else { //After Set-up started
							Graphics2D g2d = (Graphics2D)g;
							//Vertex addV = new Vertex(x,y);
							if(start.x == x && start.y == y){
								g.setColor(Color.GREEN);
								//start = addV;
								Ellipse2D.Double circle = new Ellipse2D.Double( (x*scaleX)-5, (y*scaleY)-5, 10, 10);
								g2d.fill(circle);
								g.setColor(Color.white);
							} else if (dest.x == x && dest.y == y) {
								g.setColor(Color.RED);
								//dest = addV;
								Ellipse2D.Double circle = new Ellipse2D.Double( (x*scaleX)-5, (y*scaleY)-5, 10, 10);
								g2d.fill(circle);
								g.setColor(Color.white);
							} else {
								g.setColor(Color.white);
								Ellipse2D.Double circle = new Ellipse2D.Double( (x*scaleX)-5, (y*scaleY)-5, 10, 10);
								g2d.fill(circle);
							}
						}
					}
					
				}
		}
		
		started = true;
		try{
			Graphics2D g2d = (Graphics2D)g;
			g.setColor(Color.BLUE); //Change Color for route
			Ellipse2D.Double circleEnd = new Ellipse2D.Double( ((route.get(route.size() - 1) ).x2)-7.5 ,
					((route.get(route.size() - 1) ).y2)-7.5 , 15, 15);
			Ellipse2D.Double circleStart = new Ellipse2D.Double( ((route.get(0) ).x1)-7.5 ,
					((route.get(0) ).y1)-7.5 , 15, 15);
			g2d.fill(circleEnd);
			g2d.fill(circleStart);
			Ellipse2D.Double circle;
			g2d.setStroke(new BasicStroke(2));
			for(RouteLine r : route) {
				g2d.fill(new Ellipse2D.Double( (r.x1)-5, (r.y1)-5, 11, 11));
				g2d.draw(new Line2D.Float(r.x1, r.y1, r.x2, r.y2));
				//g.drawLine();
			}
		}catch(IndexOutOfBoundsException e){}

	}
	
	//Add A blue line to ArrayList RouteLine
	public void drawBlueLine(int x,int y,int x2,int y2){
		route.add(new RouteLine(x*scaleX,y*scaleY,x2*scaleX,y2*scaleY) );
	}
	
	//Clears all Lines to original map
	public void clearAllLines(){
		route.clear();
		repaint();
	}
	
	//combine edges and vertices
	public void combineEV(){
		for(Edge edge: edges){
			for(Vertex v: Vertices){
				//Check if x in edge(x,y) == Vertex(x,y)
				if(edge.x.x == v.x && edge.x.y == v.y){
					edge.x = v;
				}
				//Check if y in edge(x,y) == Vertex(x,y)
				if (edge.y.x == v.x && edge.y.y == v.y){
					edge.y = v;
				}
				
				if (v.x == start.x && v.y == start.y){
					start = v;
				}
				
				if (v.x == dest.x && v.y == dest.y){
					dest = v;
				}
			}
		}
	}
	//Set speed with random integer value between 20-50 (mph) for each edge both ways
	public void setEdgeSpeed(){
		//Reset Speed for all edges
		for(Edge edge: edges){
			edge.speed = 0;
		}
		//Randomly generate speed of road for each edge
		for(Edge edge: edges){
			if(edge.speed == 0){
				int r = (int)(Math.random() *30) + 20;
				//System.out.println(r);
				edge.setSpeed(r);
				for (Edge edge2: edges){
					if(edge2.x.x == edge.y.x && edge2.x.y == edge.y.y && edge2.y.x == edge.x.x && edge2.y.y == edge.x.y){
						//System.out.printf("(%d,%d) == (%d,%d)\n",edge2.x.x,edge2.x.y,edge.y.x,edge.y.y);
						//System.out.println("Match");
						//System.out.println(r);
						edge2.setSpeed(r);
						break;
					}
				}
			}
		}
	}
	
	//Set speed with random integer value between 20-50 (mph) for each edge both ways
		public void setEdgeSpeedTraffic(){
			//Reset Speed for all edges
			for(Edge edge: edges){
				edge.speed = 0;
			}
			//Randomly generate speed of road for each edge
			for(Edge edge: edges){
				if(edge.speed == 0){
					int r = (int)(((Math.random() *30) + 20)*Math.random());
					System.out.println(r);
					edge.setSpeed(r);
					for (Edge edge2: edges){
						if(edge2.x.x == edge.y.x && edge2.x.y == edge.y.y && edge2.y.x == edge.x.x && edge2.y.y == edge.x.y){
							//System.out.printf("(%d,%d) == (%d,%d)\n",edge2.x.x,edge2.x.y,edge.y.x,edge.y.y);
							//System.out.println("Match");
							//System.out.println(r);
							edge2.setSpeed(r);
							break;
						}
					}
				}
			}
		}
	
	public ArrayList<Vertex> getShortest(){
		ArrayList<Vertex> best = new ArrayList<Vertex>();
		Vertex temp = dest;
		//System.out.println("Starting...temp val: " + temp + ", start = " + start);
		best.add(temp);
		while(temp != start){
			//System.out.println(temp);
			best.add(temp.prev);
			temp = temp.prev;
		}
		//System.out.println("Done");
		clearAllLines();
		for(int i=0; i<best.size();i++){
			try{
				drawBlueLine(best.get(i).x,best.get(i).y,best.get(i+1).x ,best.get(i+1).y );
				System.out.printf("(%d,%d)->(%d,%d)\n",best.get(i).x,best.get(i).y,best.get(i+1).x ,best.get(i+1).y );
			}catch(IndexOutOfBoundsException e){}
		}
		repaint();
		
		return best;
	}
	
	
	//Run Dijkstra's Algorithm to produce the best path
	public void calcDijkstra(){
		//TEST VARIABLES
		long startTime,endTime;
		startTime =  System.currentTimeMillis();
		
//================================================================================================
		
		//ArrayList<Vertex> current = new ArrayList<Vertex>(); //holds the vertices to visit next
		ArrayList<Vertex> current =new ArrayList<Vertex>();
		ArrayList<Vertex> visited = new ArrayList<Vertex>(); //holds the vertices to visit next
		ArrayList<Vertex> tempVertices = new ArrayList<Vertex>();
		tempVertices.addAll(Vertices); //holds the vertices to visit next
		current.add(start); //current list updates from start point
		for (Vertex v: tempVertices){
			v.w = 0;
			v.prev = null;
		}
		//Run
		while(!current.isEmpty()){
			Vertex temp = current.remove(0);//temp = pop
			//check all edges for neighboring vertices
			for (Edge edge: edges){
				//if the x element of an edge equals the temp
				if(temp == edge.x && !visited.contains(edge.y)){
					if((edge.speed + temp.w) > edge.y.w){
						//System.out.println("Edge Speed: " + edge.speed + ", temp.w: " + temp.w + "total: " + (edge.speed+temp.w));
						//set weight to the sum of the edge speed and weight of the previous node
						edge.y.w = (edge.speed + temp.w);
						edge.y.prev = temp;
						//System.out.println(edge.y.prev);
						//check neighbors
						for(Vertex v: tempVertices){
							if(v == edge.y){
							//if(v.x == edge.y.x && v.y == edge.y.y){
								//v.setWeight((edge.speed + temp.w));
								System.out.println(edge.x.x + ", " + edge.x.y + " weight: " + edge.x.w);
								current.add(v);
								tempVertices.remove(v);
								break;
							}
						}
					}
				}
				
				
				//tempEdges.remove(edge);
			}
			visited.add(temp);
		}//End While Loop
		
		
		endTime = System.currentTimeMillis();
		System.out.printf("\n\nDijkstra's Algorithm Total Time = %dms\n\n", (endTime - startTime) );
	}
	//Change Start Point
	public void changeStart(){
		//ALL one statement
		int random;
		//Get a random number until it is not equal to the destination
		for(random = (int)(Math.random()*Vertices.size());
				Vertices.get(random) == dest || Vertices.get(random)== start; 
				random = (int)(Math.random()*Vertices.size()) );
		
		start = Vertices.get(random);
		clearAllLines();
		repaint();
	}
	
	//Change End Point
	public void changeDest(){
		int random;
		//Get a random number until it is not equal to the destination
		for(random = (int)(Math.random()*Vertices.size());
				Vertices.get(random) == start || Vertices.get(random)== dest; 
				random = (int)(Math.random()*Vertices.size()) );
		
		dest = Vertices.get(random);
		clearAllLines();
		repaint();
	}
	
	
	
	
	
//======================= GETS ================================================
	
	//return grid
	public char[][] getGrid(){ return this.grid; }
	
	//Get Vertices
	public ArrayList<Vertex> getVertices(){ return this.Vertices; }
	
	//Get Edges
	public ArrayList<Edge> getEdges(){ return this.edges; }
	
	//Get Start Vertex
	public Vertex getStart(){ return this.start;}

	//Get destination weight
	public int getDestWeight(){ return dest.w; }
	
//=========================== SETS =========================================================	
	
	//Set Grid
	public void setGrid(char[][] newGrid){ 
	this.grid = newGrid;
	clearAllLines();
	this.started = false;
	this.start = null;
	this.dest = null;
	this.Vertices.clear();
	this.edges.clear();
	repaint();
	}
	
	
}
