
public class Vertex {
	public int x;
	public int y;
	public int w;
	public Vertex prev = null;
	Vertex(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	Vertex(int x, int y, int w){
		this.x = x;
		this.y = y;
		this.w = w;
	}
	
	public void setWeight(int n){
		this.w = n;
	}
	
}
