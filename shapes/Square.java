package ca.utoronto.utm.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

public class Square extends Rectangle {

	public Square(Graphics2D g2d, Point edge, int sideLength, int color, int lineThickness) {
		super(g2d, edge, sideLength, sideLength, color, lineThickness);
	}
	
	public void setSideLength(int sL){
		super.setLength(sL);
		super.setWidth(sL);
	}
	public Square() {
    // TODO Auto-generated constructor stub
  }

  public int getSideLength(){
		return super.getLength();
	}
	

}