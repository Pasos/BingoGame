import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FieldPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	Graphics2D g2;
	AudioClip audioRoll, audioFinish, audioBass, audioTrumpet;
	boolean[] number;
	boolean roll;
	int[] log;
	int hit, nownum, mx=0, my=0, bingo = 0;
	Timer timer1;
	Random rn;
	BufferedImage title;
	FontMetrics fm;
	Rectangle rectText;
	
	/* ★★コンストラクタ★★ */
	FieldPanel() {
		super();
		setBackground(new Color(255, 255, 255));
		audioFinish = Applet.newAudioClip(MainWindow.class.getResource("finish.wav"));
		audioFinish.play();
		audioFinish.stop();
		audioBass = Applet.newAudioClip(MainWindow.class.getResource("Bassdrum.wav"));
		audioBass.play();
		audioBass.stop();
		audioTrumpet = Applet.newAudioClip(MainWindow.class.getResource("trumpet1.wav"));
		audioTrumpet.play();
		audioTrumpet.stop();
		
		File file = new File("Title.bmp");
		try {
			title = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		number = new boolean[75];
		log = new int[75];
		for(int i = 0; i < 75;number[i] = false, log[i] = 0, i++);
		nownum = 0;
		roll = false;
		rn = new Random();
		
		timer1 = new Timer();
		timer1.schedule(new TimerTask() {public void run() {
			if(roll){
				do{
					hit = rn.nextInt(75);
				}while(number[hit]);
			}
			repaint();
		}}, 0, 100);//0.05秒ごと
	}
	
	/*★描写★*/
	public void paintComponent(Graphics g) {
		g2 = (Graphics2D) g;
		super.paintComponent(g);
		super.paintComponent(g2);
		
		g2.drawImage(title,this.getWidth()/2  - title.getWidth()/2, 20, this);
		
		String Mozi;
		if(nownum == 76){
			g2.setFont(new Font("昭和モダン体", Font.BOLD, 180));
			Mozi = "終了";
			fm = g2.getFontMetrics();
			rectText = fm.getStringBounds(Mozi, g2).getBounds();
			g2.setColor(Color.BLACK);
			g2.drawString(Mozi, this.getWidth()/2-rectText.width/2 -3, this.getHeight()/2-rectText.height/2+fm.getMaxAscent() -3);
			g2.drawString(Mozi, this.getWidth()/2-rectText.width/2, this.getHeight()/2-rectText.height/2+fm.getMaxAscent() -3);
			g2.drawString(Mozi, this.getWidth()/2-rectText.width/2 +3, this.getHeight()/2-rectText.height/2+fm.getMaxAscent() -3);
			g2.drawString(Mozi, this.getWidth()/2-rectText.width/2 -3, this.getHeight()/2-rectText.height/2+fm.getMaxAscent());
			g2.drawString(Mozi, this.getWidth()/2-rectText.width/2, this.getHeight()/2-rectText.height/2+fm.getMaxAscent());
			g2.drawString(Mozi, this.getWidth()/2-rectText.width/2 +3, this.getHeight()/2-rectText.height/2+fm.getMaxAscent());
			g2.drawString(Mozi, this.getWidth()/2-rectText.width/2 -3, this.getHeight()/2-rectText.height/2+fm.getMaxAscent() +3);
			g2.drawString(Mozi, this.getWidth()/2-rectText.width/2, this.getHeight()/2-rectText.height/2+fm.getMaxAscent() +3);
			g2.drawString(Mozi, this.getWidth()/2-rectText.width/2 +3, this.getHeight()/2-rectText.height/2+fm.getMaxAscent() +3);
			g2.setColor(Color.RED);
			g2.drawString(Mozi, this.getWidth()/2-rectText.width/2, this.getHeight()/2-rectText.height/2+fm.getMaxAscent());
		}else{
			//スポット描写
			if(roll){
				g2.setColor(Color.GRAY);
				g2.fillOval(mx - 150, my - 150, 300, 300);
				
			}else{
				g2.setColor(Color.YELLOW);
				g2.fillOval(this.getWidth()/2-150, this.getHeight()/2-150, 300, 300);
				
			}
			
			//文字描写
			if(nownum == 0 && !roll){
				Mozi = "?";
			}else{
				Mozi = Integer.toString(hit+1);
			}
			g2.setFont(new Font("昭和モダン体", Font.BOLD, 180));
			fm = g2.getFontMetrics();
			rectText = fm.getStringBounds(Mozi, g2).getBounds();
			
			if(!roll){//縁描写
				g2.setColor(Color.BLACK);
				g2.drawString(Mozi, this.getWidth()/2-rectText.width/2 -3, this.getHeight()/2-rectText.height/2+fm.getMaxAscent() -3);
				g2.drawString(Mozi, this.getWidth()/2-rectText.width/2, this.getHeight()/2-rectText.height/2+fm.getMaxAscent() -3);
				g2.drawString(Mozi, this.getWidth()/2-rectText.width/2 +3, this.getHeight()/2-rectText.height/2+fm.getMaxAscent() -3);
				g2.drawString(Mozi, this.getWidth()/2-rectText.width/2 -3, this.getHeight()/2-rectText.height/2+fm.getMaxAscent());
				g2.drawString(Mozi, this.getWidth()/2-rectText.width/2, this.getHeight()/2-rectText.height/2+fm.getMaxAscent());
				g2.drawString(Mozi, this.getWidth()/2-rectText.width/2 +3, this.getHeight()/2-rectText.height/2+fm.getMaxAscent());
				g2.drawString(Mozi, this.getWidth()/2-rectText.width/2 -3, this.getHeight()/2-rectText.height/2+fm.getMaxAscent() +3);
				g2.drawString(Mozi, this.getWidth()/2-rectText.width/2, this.getHeight()/2-rectText.height/2+fm.getMaxAscent() +3);
				g2.drawString(Mozi, this.getWidth()/2-rectText.width/2 +3, this.getHeight()/2-rectText.height/2+fm.getMaxAscent() +3);
			}
			g2.setColor(Color.WHITE);
			g2.drawString(Mozi, this.getWidth()/2-rectText.width/2, this.getHeight()/2-rectText.height/2+fm.getMaxAscent());
		}
		
		//ログ表示
		int x = 30, y = 650;
		for(int i = 0;i < 75;i++){
			if(i < nownum){
				g2.setColor(Color.GREEN);
				g2.fillOval(x-20, y-20, 40, 40);
				g2.setFont(new Font("Arial", Font.BOLD, 20));
				g2.setColor(Color.WHITE);
				fm = g2.getFontMetrics();
				rectText = fm.getStringBounds(Integer.toString(log[i]+1), g2).getBounds();
				g2.drawString(Integer.toString(log[i]+1), x-rectText.width/2, y-rectText.height/2+fm.getMaxAscent());
			}else{
				g2.setColor(Color.GRAY);
				g2.drawOval(x-20, y-20, 40, 40);
				g2.setFont(new Font("Arial", Font.BOLD, 20));
			}
			x += 40;
			if(x+20 > this.getWidth()){
				x = 30;
				y += 40;
			}
		}
	}
	/*★マウス操作★*/
	public void mouseDragged(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}

	public void mousePressed(MouseEvent e) {
		if(roll){//回転ストップ
			roll = false;
			log[nownum++] = hit;
			number[hit] = true;
			
			audioRoll.stop();
			audioFinish.play();
		}else{
			if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) == 0) {
				if(nownum < 75){//回転開始
					audioFinish.stop();
					if(rn.nextInt(100) < 80){
						audioFinish = Applet.newAudioClip(MainWindow.class.getResource("finish.wav"));
					}else{
						switch(rn.nextInt(3)){
						case 0:
							audioFinish = Applet.newAudioClip(MainWindow.class.getResource("finish2.wav"));
							break;
						case 1:	
							audioFinish = Applet.newAudioClip(MainWindow.class.getResource("finish3.wav"));
							break;
						case 2:	
							audioFinish = Applet.newAudioClip(MainWindow.class.getResource("finish4.wav"));
							break;
						}
					}
					roll = true;
					if(rn.nextInt(2) == 0){
						audioRoll = Applet.newAudioClip(MainWindow.class.getResource("roll.wav"));
						audioRoll.loop();
					}else{
						audioRoll = Applet.newAudioClip(MainWindow.class.getResource("roll2.wav"));
						audioRoll.play();
					}
					//audioBass.play();
				}else{
					nownum = 76;
					repaint();
				}
			}else if((e.getModifiers() & MouseEvent.BUTTON1_MASK) == 0){//ビンゴ！
				audioTrumpet.stop();
				audioTrumpet.play();
				bingo++;
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
}