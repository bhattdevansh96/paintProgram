package ca.utoronto.utm.shapes;

import java.awt.Graphics2D;
import java.util.ArrayList;

import ca.utoronto.utm.paint.View;

public class Polyline extends Line {
   
  ArrayList<Point> points = new ArrayList<Point>();
  private Graphics2D g2d;
  private View view;
  private int currentColor;
  private int lineThickness;
  private Point start;
  
  public Polyline(Graphics2D g2d, Point p, int color, int lineThickness){
    this.g2d = g2d;
    this.start = p;
    this.points.add(p);
    this.currentColor = color;
    this.lineThickness = lineThickness;
  }

  public void addPoint(Point p){
    this.points.add(p);
  }
  
  public void addStart(Point start){
    this.start = start;
    this.points.add(start);
  }

  public ArrayList<Point> getPoints(){
    return this.points;
  }

  public void setG2D(Graphics2D g2d){
    this.g2d = g2d;
  }
  
  public void setView(View view){
    this.view = view;
  }
  
  public void addEndPoint(){
    this.points.add(this.start);
  }
  
  public void draw(){
    for(int i=0;i<points.size()-1; i++){
      Point p1=points.get(i);
      Point p2=points.get(i+1);
      g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }
  }
}
