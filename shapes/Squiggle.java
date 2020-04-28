package ca.utoronto.utm.shapes;

import java.awt.Graphics2D;
import java.util.ArrayList;
import ca.utoronto.utm.paint.View;

public class Squiggle extends Line{

  ArrayList<Point> points = new ArrayList<Point>();
  private Graphics2D g2d;
  private View view;
  private int currentColor;
  private int lineThickness;
  private boolean mouseClick;
  
  public Squiggle(Graphics2D g2d, Point p, int currentColor, int lineThickness){
    this.g2d = g2d;
    this.points.add(p);
    this.currentColor = currentColor;
    this.lineThickness = lineThickness;
  }

  public Squiggle(){};

  public void addPoint(Point p){
    this.points.add(p);
  }

  public ArrayList<Point> getPoints(){
    return this.points;
  }
  
  public void draw(){
    for(int i=0;i<points.size()-1; i++){
      Point p1=points.get(i);
      Point p2=points.get(i+1);
      g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
      Shapes.allShapes.add(this);
    }
  }
}

