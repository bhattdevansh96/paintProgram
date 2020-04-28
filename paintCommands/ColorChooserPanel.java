package ca.utoronto.utm.paintCommands;

import javax.swing.*;

import ca.utoronto.utm.paint.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChooserPanel extends JPanel implements ActionListener{

	private View view; // So we can talk to our parent or other components of the view
	public static String currentColor; // So we can access color in PaintPanel
	
	public ColorChooserPanel(View view) {	
		this.view=view;

		Color[] colors = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY,
				 Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};
		this.setLayout(new GridLayout());
		for (Color color : colors) {
			JButton button = new JButton();
			button.setPreferredSize(new Dimension(10,15));
			button.setActionCommand(Integer.toString(color.getRGB()));
			button.setBackground(color);
			button.setOpaque(true);
			button.setBorderPainted(false);
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
		currentColor = e.getActionCommand();
		System.out.println(currentColor);
	}

}
