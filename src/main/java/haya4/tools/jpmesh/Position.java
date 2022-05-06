package haya4.tools.jpmesh;

public class Position {
	double lat = 0.0d;
	double lon = 0.0d;
	
	public Position() {
		this.lat = 0.0d;
		this.lon = 0.0d;
	}
	
	public Position(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	public double getX() {
		return this.lat;
	}
	
	public double getY() {
		return this.lon;
	}

}
