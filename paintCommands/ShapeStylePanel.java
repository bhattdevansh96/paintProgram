package ca.utoronto.utm.paintCommands;

import javax.swing.*;

import ca.utoronto.utm.paint.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShapeStylePanel extends JPanel implements ActionListener{

	private View view; // So we can talk to our parent or other components of the view
	private JTextField thicknessTF;
	public static int strokeThickness = 1;
	public static String shapeStyleIndicator = "";
	public ArrayList<String> shapeStyles = new ArrayList<String>();
	
	public ShapeStylePanel(View view) {	
		this.view=view;
		this.setLayout(new FlowLayout());

		shapeStyles.add("DASHED");
		shapeStyles.add("OUTLINE");
		shapeStyles.add("SOLID");
		for (String style: shapeStyles) {
			JButton button = new JButton(style);
			this.add(button);
			button.addActionListener(this);
		}
		
		thicknessTF = new JTextField("Current Thickness: " + strokeThickness);
		thicknessTF.setEditable(false);
		this.add(thicknessTF);
		String[] lineThickness = {"-", "+"};
		for (String thickness: lineThickness) {
			JButton button = new JButton(thickness);
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
		if(e.getActionCommand() == "-"){
			if(strokeThickness >= 2){
				strokeThickness--;
			}
		}
		else if(e.getActionCommand() == "+"){
			strokeThickness++;
		}
		else if(shapeStyles.contains(e.getActionCommand())){
			shapeStyleIndicator = e.getActionCommand();
		}
		thicknessTF.setText("Current Thickness: " + strokeThickness);
		System.out.println(e.getActionCommand());
	}
	

}
