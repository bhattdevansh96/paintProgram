package ca.utoronto.utm.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import ca.utoronto.utm.shapes.*;
import ca.utoronto.utm.shapes.Point;
// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/
import ca.utoronto.utm.shapes.Rectangle;
import ca.utoronto.utm.paintCommands.*;

public class PaintPanel extends JPanel implements Observer {

	private PaintModel model; // slight departure from MVC, because of the way
	// painting works
	private View view; // So we can talk to our parent or other components of
	// the view

	private String mode; // modifies how we interpret input (could be better?)
	// private Circle circle; // the circle we are building
	private Rectangle rectangle;
	private Square square;
	private Squiggle squiggle;
	private boolean mouseClick;
	private Graphics2D g2d;
	private MouseCommands mouseCommands;

	private ArrayList<Circle> circles = new ArrayList<Circle>();
	private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	private ArrayList<Square> squares = new ArrayList<Square>();
	private ArrayList<Squiggle> squiggles = new ArrayList<Squiggle>();
	private ArrayList<Line> lines = new ArrayList<Line>();
	private ArrayList<Polyline> polylines = new ArrayList<Polyline>();
	private ArrayList<Point> erasers = new ArrayList<Point>();

	public void addToCircles(Circle c) {
		circles.add(c);
	}

	public void addToSquiggles(Squiggle s) {
      squiggles.add(s);
  }
	public void addToLines(Line l){
      lines.add(l);
  }
	
	public void addToPolylines(Polyline p){
      polylines.add(p);
  }
	
	public void addToRectangles(Rectangle r){
		rectangles.add(r);
	}
	public void addToSquares(Square s){
		squares.add(s);
	}
	public void addToErasers(Point e){
		erasers.add(e);
	}

	public int getColor() {
		int currentColor = -16777216; // default black RGB
		try {
			int checkColor = Integer.parseInt(ColorChooserPanel.currentColor);
			currentColor = checkColor;
		} catch (NumberFormatException nfe) {
		}
		return currentColor;
	}

	public int getStroke() {
		return ShapeStylePanel.strokeThickness;
	}

	public String getStyle() {
		return ShapeStylePanel.shapeStyleIndicator;
	}

	public PaintPanel(PaintModel model, View view) {
		this.model = model;
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(300, 300));
		this.mouseCommands = new MouseCommands(this.model, this);

		this.addMouseListener(this.mouseCommands);
		this.addMouseMotionListener(this.mouseCommands);
	}

	/**
	 * View aspect of this
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;

		// Origin is at the top left of the window 50 over, 75 down
		g2d.setColor(new Color(getColor()));


		for (Circle c : this.getCircles()) {
			int x = c.getCentre().getX();
			int y = c.getCentre().getY();
			int radius = c.getRadius();


			g2d.drawOval(x - radius, y - radius, radius * 2, radius * 2);
			if (getStyle() == "DASHED") {
				BasicStroke dashed = new BasicStroke(getStroke(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
						new float[] { 9 }, 0);
				g2d.setStroke(dashed);
			} 
			else if (getStyle() == "OUTLINE") {
				g2d.setStroke(new BasicStroke(getStroke()));
			}
			else if (getStyle() == "SOLID") {
				g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);

			}
		}

		for (Point e: this.getErasers()) {
			g2d.setColor(Color.white);
			g2d.fillOval(e.getX(), e.getY(), 20, 20);
		}
		
		for (Squiggle s: this.getSquiggles()) {
          ArrayList<Point> points = s.getPoints();
          for(int i = 0; i <= points.size()  - 2; i++){
            g2d.drawLine(points.get(i).getX(), points.get(i).getY(), points.get(i+1).getX(), points.get(i+1).getY());
          }
        }
		
		for (Polyline p: this.getPolylines()) {
          ArrayList<Point> points = p.getPoints();
          for(int i = 0; i <= points.size()  - 2; i++){
            g2d.drawLine(points.get(i).getX(), points.get(i).getY(), points.get(i+1).getX(), points.get(i+1).getY());
          }
        }
		
		for (Line l: this.getLines()) {
          Point start = l.getStart();
          Point end = l.getEnd();
            g2d.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
        }
		
		for (Rectangle r : this.getRectangles()) {
			int x = r.getEdge().getX();
			int y = r.getEdge().getX();
			int length = r.getLength();
			int width = r.getWidth();
			g2d.drawRect(x, y, length, width);
			
			if (getStyle() == "DASHED") {
				BasicStroke dashed = new BasicStroke(getStroke(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
						new float[] { 9 }, 0);
				g2d.setStroke(dashed);
			} 
			else if (getStyle() == "OUTLINE") {
				g2d.setStroke(new BasicStroke(getStroke()));
			}
			else if (getStyle() == "SOLID") {
				g2d.fillRect(x, y, length, width);
			}
		}

		for(Square s: this.getSquares()){
			int x = s.getEdge().getX();
			int y = s.getEdge().getY();
			int sideLength = s.getLength();
			g2d.drawRect(x, y, sideLength, sideLength);
			
			if (getStyle() == "DASHED") {
				BasicStroke dashed = new BasicStroke(getStroke(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
						new float[] { 9 }, 0);
				g2d.setStroke(dashed);
			} 
			else if (getStyle() == "OUTLINE") {
				g2d.setStroke(new BasicStroke(getStroke()));
			}
			else if (getStyle() == "SOLID") {
				g2d.fillRect(x, y, sideLength, sideLength);

			}
		}
		g2d.dispose();

		if (this.mode == "New") {
			super.paintComponents(g);
		}
	}

	public ArrayList<Circle> getCircles() {
		return circles;
	}
	public ArrayList<Line> getLines() {
      return lines;
  }

	public ArrayList<Point> getErasers() {
		return erasers;
	}
	
	public ArrayList<Squiggle> getSquiggles() {
      return squiggles;
    }
	
	public ArrayList<Polyline> getPolylines() {
      return polylines;
    }

	public ArrayList<Rectangle> getRectangles(){
		return rectangles;
	}

	public ArrayList<Square> getSquares(){
		return squares;
	}

	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}

	public void restart() {
		super.paintComponent(g2d);
	}

	/**
	 * Controller aspect of this
	 */
	public void setMode(String mode) {
		this.mode = mode;
		this.mouseCommands = new MouseCommands(this.model, this);
	}

	public String getMode() {
		return this.mode;
	}

	public void addObserver(MouseCommands mouseCommands2) {

	}
}
