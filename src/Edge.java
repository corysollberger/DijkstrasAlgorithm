//Class that handles the connection between edges
public class Edge {
	public Vertex x;
	public Vertex y;
	public int speed = 0; //MPH of road
	public boolean traffic = false; //Whether the road has traffic
	
	//Constructor
	Edge(Vertex x, Vertex y){
		this.x = x;
		this.y = y;
	}
	
	//Set speed
	public void setSpeed(int n){
		this.speed = n;
	}
	//Set traffic true
	public void setTraffic(){
		this.traffic = true;
	}
	
}
