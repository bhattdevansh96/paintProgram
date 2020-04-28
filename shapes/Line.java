package ca.utoronto.utm.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Line implements Shapes {
  private Graphics2D g2d; 
  private Point start, end;
  public static ArrayList<Line> allLines = new ArrayList<Line>();
  private int currentColor;
  private int lineThickness;
  
  public Line(Graphics2D g2d, Point p, int color, int lineThickness){
    this.g2d = g2d;
    this.start = p;
    this.currentColor = color;
    this.lineThickness = lineThickness;
  }
  
  public Line(){}
  
  public void addEndPoint(Point p){
    this.end = p;
  }
  
  public Point getStart(){
    return start;
  }
  
  public Point getEnd(){
    return end;
  }
  
  public void draw(){
    this.g2d.drawLine(this.start.getX(), this.start.getY(), this.end.getX(), this.end.getY());
    allShapes.add(this);
  }
}
