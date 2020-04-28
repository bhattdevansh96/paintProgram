package ca.utoronto.utm.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import ca.utoronto.utm.paint.View;

public class Rectangle extends JPanel implements Shapes{
  
  private Point edge;
  private int length;
  private int width;
  private Graphics2D g2d;
  private int currentColor;
  private int lineThickness;

  public Rectangle(Graphics2D g2d, Point edge, int length, int width, int color, int lineThickness){
    this.g2d=g2d;
    this.edge = edge;
    this.length = length;
    this.width = width;
    this.currentColor = color;
    this.lineThickness = lineThickness;
  }
  
  public Rectangle() {
    // TODO Auto-generated constructor stub
  }


public Point getEdge(){
    return this.edge;
  }
  
  public void setEdge(Point edge){
    this.edge = edge;
  }
  
  public int getLength(){
    return this.length;
  }
  
  public void setLength(int length){
    this.length = length;
  }
  public int getWidth(){
    return this.width;
  }
  
  public void setWidth(int width){
    this.width = width;
  }
  
  public void setG2D(Graphics2D g2d){
    this.g2d = g2d;
  }

  public void setCurrentColor(int currentColor) {
    this.currentColor = currentColor;
  }

  public void setLineThickness(int lineThickness) {
    this.lineThickness = lineThickness;
  }
}

