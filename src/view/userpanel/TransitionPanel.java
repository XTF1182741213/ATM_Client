package view.userpanel;

import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.father.FatherPanel;

public class TransitionPanel extends JPanel {
	private ImageIcon img;
	private JLabel jl_bg; // 背景
	private JLabel tLabel;
	private String tips;
	public TransitionPanel() {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		tLabel=new JLabel();
		tips="操作正在进行,请稍等。。。";
		
		tLabel=new JLabel(tips);
		tLabel.setFont(new Font("宋体", Font.BOLD, 30));
		Icon icon=new ImageIcon("image/背景图.png");
		JLabel	jl_bg = new JLabel(icon); // 背景
		Icon icon2=new ImageIcon("image/2.gif");
		JLabel	jl_bg2 = new JLabel(icon2); // 背景
		jl_bg.setBounds(-3, -20, 815, 625); // 设置位置和大小，先setLayout(null)一下。
		jl_bg2.setBounds(160,200, 500, 400);
		tLabel.setBounds(250,180, 380, 50);
		this.add(tLabel);
		this.add(jl_bg2);// 添加背景,需要最后添加，不然会不显示其他组件
		this.add(jl_bg, new Integer(Integer.MIN_VALUE));// 添加背景,需要最后添加，不然会不显示其他组件
		
	}

	public void changeNextPanel(JFrame f,FatherPanel nextPanel) {
		// TODO Auto-generated method stub
			Timer timer = new Timer();
			nextPanel.init();
		Timers timerTask = new Timers(f,this,nextPanel);
		
		timer.schedule(timerTask, 4000, 1000);
	}

}
class Timers extends TimerTask{
	FatherPanel nextPanel;
	JPanel p;
	JFrame f;
	public Timers(JFrame f,JPanel p,FatherPanel np){
		this.nextPanel=np;
		this.p=p;
		this.f=f;
	}

	
		public void run() {
			System.out.println("交易处理完成");
				p.setVisible(false);
				
				f.add(nextPanel);
				nextPanel.setVisible(true);
				this.cancel();
				//下一个界面
				
				
			}
}
