package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.ActionEvent;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import Model.Model;
import View.View;

public class Controller {
	private Model model;
	private View view;
	private InputMap im;
	private ActionMap am;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		im = view.getInputMap(view.WHEN_IN_FOCUSED_WINDOW);
		am = view.getActionMap();
		bindKeys();
	}

	private void bindKeys() {
		im.put(KeyStroke.getKeyStroke("UP"), "UP");
		im.put(KeyStroke.getKeyStroke("RIGHT"), "RIGHT");
		im.put(KeyStroke.getKeyStroke("DOWN"), "DOWN");
		im.put(KeyStroke.getKeyStroke("LEFT"), "LEFT");
		am.put("UP", new Arrows("UP"));
		am.put("RIGHT", new Arrows("RIGHT"));
		am.put("DOWN", new Arrows("DOWN"));
		am.put("LEFT", new Arrows("LEFT"));
	}

	private class Arrows extends AbstractAction {
		private String cmd;

		public Arrows(String cmd) {
			this.cmd = cmd;
		}

		public void actionPerformed(ActionEvent e) {
			model.moveTo(cmd);

		}
	}
}