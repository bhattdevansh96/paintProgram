package ca.utoronto.utm.paintCommands;

import javax.imageio.ImageIO;
import javax.swing.*;

import ca.utoronto.utm.paint.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/

public class ShapeChooserPanel extends JPanel implements ActionListener {
	private View view; // So we can talk to our parent or other components of the view
	private JTextArea shapeMode;

	public ShapeChooserPanel(View view) {	
		this.view=view;

		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline", "Line", "Eraser" };
		this.setLayout(new GridLayout(buttonLabels.length + 1, 1));

		shapeMode = new JTextArea("MODE:\nNone");
		this.add(shapeMode);

		for (String label : buttonLabels) {
			JButton button = new JButton();
			button.setPreferredSize(new Dimension(75, 55));
			button.setBackground(Color.WHITE);
			button.setActionCommand(label);
			button.setFocusable(false);

			try {
				Image img = ImageIO.read(getClass().getResource("/images/" + label + ".png"));
				button.setIcon(new ImageIcon(img));
			} catch (IOException ex) {}

			this.add(button);
			button.addActionListener(this);
		}
	}

	/**
	 * Controller aspect of this
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.view.getPaintPanel().setMode(e.getActionCommand());

		shapeMode.setText("MODE:\n"+e.getActionCommand());
		System.out.println(e.getActionCommand());
	}
}
