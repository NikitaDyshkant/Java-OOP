import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Controller;
import Model;
import View;

public class MainClass {
	private JFrame frame;

	public MainClass() {
		setFrame();
		buildMVC();
		frame.setVisible(true);
	}

	private void setFrame() {
		frame = new JFrame("Snake-beta");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(320, 380);
		frame.setFocusable(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

	}

	private void buildMVC() {
		Model model = new Model();
		View view = new View(model);
		Controller control = new Controller(model, view);
		frame.add(view);
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainClass();
			}
		});
	}
}