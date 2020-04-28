package ca.utoronto.utm.paint;

import java.awt.Color;
import ca.utoronto.utm.shapes.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Observable;

public class PaintModel extends Observable{
  ArrayList<Shapes> shapes  = new ArrayList<Shapes>();
  private ArrayList<Circle> circles=new ArrayList<Circle>();
  ArrayList<Color> colors = new ArrayList<Color>();
  
  public void addCommand(Shapes shape){
    shapes.add(shape);
    this.setChanged();
    this.notifyObservers();
  }
  
  public void drawShape(){
    
  }
//	private ArrayList<Squiggle> squiggles=new ArrayList<Squiggle>();
//	private ArrayList<Rectangle> rectangles=new ArrayList<Rectangle>();
//	private ArrayList<Square> squares=new ArrayList<Square>();	
//	private ArrayList<Color> colors=new ArrayList<Color>();
	
	
//	public void addSquiggle(Squiggle s){
//		this.squiggles.add(s);
//		this.setChanged();
//		this.notifyObservers();
//	}
//	public ArrayList<Squiggle> getSquiggles(){
//		return this.squiggles;
//	}
//	
//	public void addCircle(Circle c){
//		this.circles.add(c);
//		this.setChanged();
//		this.notifyObservers();
//	}
//	
//	public ArrayList<Circle> getCircles(){
//		return circles;
//	}
//	
//	public void addRectangle(Rectangle r){
//	  this.rectangles.add(r);
//	  this.setChanged();
//	  this.notifyObservers();
//	}
//	
//	public ArrayList<Rectangle> getRectangles(){
//	  return rectangles;
//	}
//	
//	public void addSquare(Square s){
//		this.squares.add(s);
//		this.setChanged();
//		this.notifyObservers();
//	}
//	
//	public ArrayList<Square> getSquares(){
//		return squares;
//	}
//	
	public void addColorChoice(Color c){
		this.colors.add(c);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Color> getColors(){
		return colors;
	}
}
