import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

class MainWindow extends JFrame {
	private static final long serialVersionUID = 2L;
	static MainWindow main;
	
	static FieldPanel p3;
	/* ★★入出力系★★ */
	
	/* ★★コンストラクタ★★ */
	MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ビンゴ");
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		DisplayMode displayMode = env.getDefaultScreenDevice().getDisplayMode();
		setSize(displayMode.getWidth(), displayMode.getHeight());
		setResizable(false);
		
		Container c = getContentPane();
		
		c.setLayout(new BorderLayout());
		p3 = new FieldPanel();
		c.setBackground(Color.white);

		setLayout(new BorderLayout());
		c.add(p3, BorderLayout.CENTER);

		p3.addMouseListener(p3);
		p3.addMouseMotionListener(p3);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					
				} catch (Exception d) {
				}
				System.exit(0);
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowOpened(WindowEvent e) {
			}
		});
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				p3.revalidate();
				p3.repaint();
				repaint();
			}

			public void componentHidden(ComponentEvent e) {
			}

			public void componentMoved(ComponentEvent e) {
			}

			public void componentShown(ComponentEvent e) {
			}
		});
		
		setVisible(true);
		
	}

	/* ★★メイン★★ */
	public static void main(String[] args) {
		 main = new MainWindow();
	}
}
