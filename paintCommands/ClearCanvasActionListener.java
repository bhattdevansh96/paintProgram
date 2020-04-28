package ca.utoronto.utm.paintCommands;

import java.awt.event.*;

import javax.swing.JPanel;
public class ClearCanvasActionListener extends JPanel implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	  this.removeAll();
	}

}
