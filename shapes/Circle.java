package ca.utoronto.utm.shapes;

import java.awt.Color;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import ca.utoronto.utm.paint.PaintPanel;
import ca.utoronto.utm.paint.View;

public class Circle implements Shapes {
	private Point centre;
	private int radius;
	private Graphics2D g2d;
	private int currentColor;
	private int lineThickness;
	
	// NONSENSE
	private PaintPanel panel;
	private View view;
	
	public Circle(Graphics2D g2d, Point centre, int radius, int color, int lineThickness) {
		this.centre = centre;
		this.radius = radius;
		this.currentColor = color;
		this.lineThickness = lineThickness;
		this.g2d = g2d;
	}

	public Circle() {
		
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
