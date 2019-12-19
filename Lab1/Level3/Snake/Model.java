package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {
	private List<Integer> tx = new ArrayList<Integer>(); 
	private List<Integer> ty = new ArrayList<Integer>();
	private DragPlayer drag;
	private String moveTo;
	private int step = 10;
	private int tail = 0;
	private int x = 10;
	private int y = 10;
	private int fx; // f-stands for food
	private int fy;

	public Model() {
		moveTo = "RIGHT";
		drag = new DragPlayer();
		generateFood();
	}

	private void generateFood() {
		fx = random(31) * 10;
		fy = random(34) * 10;
	}

	private int random(int range) {
		return (int) (Math.random() * range);
	}

	public void moveTo(String direction) {
		moveTo = "";
		moveTo = direction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int tailSize() {
		return tail;
	}

	public int getTailX(int index) {
		return tx.get(index);
	}

	public int getTailY(int index) {
		return ty.get(index);
	}
	
	public int getFoodX() {
		return fx;
	}

	public int getFoodY() {
		return fy;
	}

	private class DragPlayer implements Runnable {
		public DragPlayer() {
			Thread t = new Thread(this);
			t.start();
		}

		public void run() {
			while (true) {
				if (moveTo.equals("UP"))
					y -= step;
				if (moveTo.equals("RIGHT"))
					x += step;
				if (moveTo.equals("DOWN"))
					y += step;
				if (moveTo.equals("LEFT"))
					x -= step;
				if (x == fx && y == fy) {
					tail++;
					generateFood();
				}
				loopBorders();
				setChanged();
				notifyObservers();
				try {
					Thread.sleep(100); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				tx.add(0, x);
				ty.add(0, y);
			}
		}

		private void loopBorders() {
			if (y <= -10) {
				y = 350;
				return;
			}
			if (y >= 350) {
				y = -10;
				return;
			}
			if (x <= -10) {
				x = 320;
				return;
			}
			if (x >= 320) {
				x = -10;
				return;
			}

		}
	}
}
