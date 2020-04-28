package ca.utoronto.utm.paint;

import javax.swing.*;

import ca.utoronto.utm.paintCommands.ClearCanvasActionListener;
import ca.utoronto.utm.paintCommands.ColorChooserPanel;
import ca.utoronto.utm.paintCommands.ShapeChooserPanel;
import ca.utoronto.utm.paintCommands.ShapeStylePanel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;

/**
 * This is the top level View+Controller, it contains other aspects of the
 * View+Controller.
 * 
 * @author arnold
 *
 */
public class View extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private PaintModel model;

	// The components that make this up
	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private ColorChooserPanel colorChooserPanel;
	private ShapeStylePanel shapeStylePanel;
	private Graphics g;
	private Container c;
	private MouseCommands mouseCommands;

	public View(PaintModel model) {
		super("Paint"); // set the title and do other JFrame init

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(createMenuBar());
		this.setMinimumSize(new Dimension(550, 400));

		c = this.getContentPane();
		
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		c.add(this.shapeChooserPanel, BorderLayout.WEST);

		this.colorChooserPanel = new ColorChooserPanel(this);
		c.add(this.colorChooserPanel, BorderLayout.SOUTH);

		this.shapeStylePanel = new ShapeStylePanel(this);
		c.add(this.shapeStylePanel, BorderLayout.NORTH);

		this.model = model;
		
		this.paintPanel = new PaintPanel(model, this);
		c.add(this.paintPanel, BorderLayout.CENTER);
		
		this.mouseCommands = new MouseCommands(this.model, this.paintPanel);

		this.pack();
		this.setVisible(true);
	}

	public PaintPanel getPaintPanel() {
		return paintPanel;
	}

	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}

	public ColorChooserPanel getColorChooserPanel() {
		return colorChooserPanel;
	}

	public ShapeStylePanel getShapeStylePanel() {
		return shapeStylePanel;
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;

		menu = new JMenu("File");

		// a group of JMenuItems
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Edit");

		// a group of JMenuItems
		menuItem = new JMenuItem("Cut");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Undo");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Redo");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		return menuBar;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
		else if (e.getActionCommand().equals("New")) {
			this.paintPanel.setVisible(false);
			this.model = new PaintModel();
			this.paintPanel = new PaintPanel(this.model, this);

			this.mouseCommands = new MouseCommands(this.model, this.paintPanel);
			this.c.add(this.paintPanel, BorderLayout.CENTER);

			this.paintPanel.setVisible(true);
		}
		System.out.println(e.getActionCommand());
	}
}
