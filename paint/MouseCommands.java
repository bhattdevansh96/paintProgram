package ca.utoronto.utm.paint;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import ca.utoronto.utm.shapes.Circle;
import ca.utoronto.utm.shapes.Line;
import ca.utoronto.utm.shapes.Point;
import ca.utoronto.utm.shapes.Polyline;
import ca.utoronto.utm.shapes.Rectangle;
import ca.utoronto.utm.shapes.Square;
import ca.utoronto.utm.shapes.Squiggle;

public class MouseCommands extends JPanel implements Observer, MouseMotionListener, MouseListener {

  private boolean mouseClicked;
  private String mode;
  private Circle circle;
  private Rectangle rectangle;
  private Square square;
  private Squiggle squiggle;
  private Line line;
  private Polyline polyline;
  //	private Eraser eraser;
  private PaintModel model;
  private PaintPanel panel;
  private Graphics2D g2d;

  public MouseCommands(PaintModel model, PaintPanel panel) {
    this.model = model;
    this.panel = panel;

    this.panel.addObserver(this);
  }

  public String getMode(){
    return this.panel.getMode();
  }
  // MouseMotionListener below
  @Override
  public void mousePressed(MouseEvent e) {
    if (this.getMode() == "Squiggle") {	
      Point start = new Point(e.getX(), e.getY());
      this.squiggle = new Squiggle(g2d, start, panel.getColor(), panel.getStroke());
      this.panel.addToSquiggles(this.squiggle);
    }

    else if (this.getMode() == "Circle") {
      Point centre = new Point(e.getX(), e.getY());
      this.circle = new Circle(g2d, centre, 0, this.panel.getColor(), this.panel.getStroke());

      this.panel.addToCircles(this.circle);
    }

    else if (this.getMode() == "Rectangle") {
      Point edge = new Point(e.getX(), e.getY());
      this.rectangle = new Rectangle(g2d, edge, 0,0,this.panel.getColor(), this.panel.getStroke());
      this.panel.addToRectangles(this.rectangle);
    }

    else if (this.getMode() == "Square") {
      Point edge = new Point(e.getX(), e.getY());
      this.square = new Square(g2d, edge, 0,this.panel.getColor(), this.panel.getStroke());
      this.panel.addToSquares(this.square);
    }

    else if(this.getMode() == "Polyline"){

      Point start = new Point(e.getX(), e.getY());
      this.polyline = new Polyline(g2d, start, panel.getColor(), panel.getStroke());
      this.panel.addToPolylines(this.polyline);

    }

    else if(this.getMode() == "Line"){
      Point start = new Point(e.getX(), e.getY());
      this.line = new Line(g2d, start, panel.getColor(), panel.getStroke());
      this.panel.addToLines(this.line);
    }

    else if (this.getMode() == "Eraser") {
      this.panel.getErasers().add(new Point(e.getX(), e.getY()));
    }
  }

  @Override
  public void mouseDragged(MouseEvent e) {

    if (this.getMode() == "Squiggle") {	
      this.squiggle.addPoint(new Point(e.getX(), e.getY()));
      this.panel.addToSquiggles(this.squiggle);
    }

    else if (this.getMode() == "Circle") {
      int radius = Math.abs(this.circle.getCentre().getX() - e.getX());
      this.circle.setRadius(radius);
      this.panel.addToCircles(this.circle);
    } 

    else if (this.getMode() == "Rectangle") {
      int length = Math.abs(this.rectangle.getEdge().getX() - e.getX());
      int width = Math.abs(this.rectangle.getEdge().getY() - e.getY());
      this.rectangle.setLength(length);
      this.rectangle.setWidth(width);
      this.panel.addToRectangles(this.rectangle);
    } 

    else if (this.getMode() == "Square") {
      int length = Math.abs(this.square.getEdge().getX() - e.getX());
      this.square.setLength(length);
      this.square.setWidth(length);
      this.panel.addToSquares(this.square);
    } 

    else if (this.getMode() == "Eraser") {
      this.panel.getErasers().add(new Point(e.getX(), e.getY()));
    }

    else if(this.getMode() == "Line"){
      if(this.line != null){
        Point point = new Point(e.getX(), e.getY());
        this.line.addEndPoint(point);
        panel.addToLines(this.line);
      }
    }
    this.panel.repaint();
  }
  @Override
  public void mouseReleased(MouseEvent e) {
    if (this.getMode() == "Squiggle") {
      this.squiggle = null;
      this.mouseClicked = false;

    } else if (this.getMode() == "Circle") {
      if (this.circle != null) {
        int radius = Math.abs(this.circle.getCentre().getX() - (e.getX()));
        this.circle.setRadius(radius);
        this.panel.addToCircles(this.circle);
        this.circle = null;
      }
    } 

    else if(this.getMode()=="Rectangle"){
      if(this.rectangle!=null){
        int length = Math.abs(this.rectangle.getEdge().getX() - e.getX());
        int width = Math.abs(this.rectangle.getEdge().getY() - e.getY());
        this.rectangle.setLength(length);
        this.rectangle.setWidth(width);
        this.panel.addToRectangles(this.rectangle);
        this.rectangle=null;
      }
    }

    else if (this.getMode() == "Square") {
      if (this.square!=null) {
        int length = Math.abs(this.square.getEdge().getX() - e.getX());
        int width = Math.abs(this.square.getEdge().getY() - e.getY());
        System.out.println(length + "   " + width);
        this.square.setLength(length);
        this.panel.addToSquares(this.square);
        this.square = null;
      }
    }

    else if(this.getMode() == "Polyline"){
      if(e.getClickCount() == 2){
        this.polyline.addEndPoint();
        this.panel.addToPolylines(this.polyline);
        this.polyline = null;
      }else{
        this.polyline.addPoint(new Point(e.getX(), e.getY()));
        this.panel.addToPolylines(this.polyline);
      }
    }

    else if(this.getMode() == "Line"){
      this.line.addEndPoint(new Point(e.getX(), e.getY()));
      this.panel.addToLines(this.line);
      this.line = null;
    }

    this.panel.repaint();
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (this.mode == "Squiggle") {

    } else if (this.mode == "Circle") {

    } else if (this.mode == "Rectangle") {

    }
  }

  @Override
  public void mouseExited(MouseEvent e) {
    if (this.mode == "Squiggle") {

    } else if (this.mode == "Circle") {

    } else if (this.mode == "Rectangle") {

    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (this.mode == "Polyline"){
      if(this.polyline != null){
        this.polyline.addPoint(new Point(e.getX(), e.getY()));
        this.panel.addToPolylines(this.polyline);
      }
    }

  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if (this.mode == "Polyline"){
      if(this.polyline != null){
        this.polyline.addPoint(new Point(e.getX(), e.getY()));
        this.panel.addToPolylines(this.polyline);
      }
    }
    this.repaint();
  }

  @Override
  public void update(Observable o, Object arg) {
    // TODO Auto-generated method stub

  }
}
