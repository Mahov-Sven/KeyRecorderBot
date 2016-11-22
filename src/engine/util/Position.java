package engine.util;

import java.awt.Point;

public class Position {

	public int x, y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Position setPosition(int x, int y){
		this.x = x;
		this.y = y;
		return this;
	}
	
	public static Position fromJPoint(Point point){
		return new Position(point.x, point.y);
	}
	
	public boolean equals(Object obj){
		Position pObj = (Position) obj;
		return pObj.x == this.x && pObj.y == this.y;
	}
}
