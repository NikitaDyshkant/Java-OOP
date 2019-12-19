import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Maze extends JComponent {
	private static JFrame frame;
	private int size = 16;
	private boolean field[][];
	private static int msize = 20; 

	Maze() {
		Logic m = new Logic(msize);
		frame.setSize(7 + (msize * 17), 29 + (msize * 17));
		m.openSite();
		field = m.getField();
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (int b = 0; b < field.length; b++)
			for (int a = 0; a < field[0].length; a++) {
				if (field[b][a])
					g2.setColor(Color.WHITE);
				else
					g2.setColor(Color.BLACK);
				g2.fillRect(1 + (17 * a), 1 + (b * 17), size, size);
			}
	}

	public static void savePic() {

		try {
			BufferedImage image = new BufferedImage(7 + msize * 17,
					29 + msize * 17, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = image.createGraphics();
			frame.paint(graphics2D);
			ImageIO.write(image, "png", new File("D:/5.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				frame = new JFrame("Practice");
				frame.setBackground(Color.BLACK);
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
				frame.getContentPane().setBackground(new Color(24, 129, 168));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Component c = new Maze();
				frame.add(c);
				frame.setVisible(true);
				savePic();
			}
		});

	}
}