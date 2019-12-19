package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

import Model.Model;

public class View extends JComponent implements Observer {
	private Model model;

	public View(Model model) {
		this.model = model;
		this.model.addObserver(this);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D head = new Rectangle2D.Double(model.getX(), model.getY(),
				10, 10);
		g2.fill(head);
		for (int a = 0; a < model.tailSize(); a++) {
			g2.drawRect(model.getTailX(a), model.getTailY(a), 9, 9);
		}
		g2.drawRect(model.getFoodX(), model.getFoodY(), 10, 10);
	}

	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
