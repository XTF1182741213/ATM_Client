package view.userpanel;

import java.util.*;
import java.util.Timer;

import javax.swing.*;


import java.awt.*;

public class Client extends JPanel {

	public Client(){
				ImageIcon img;
		JLabel jl_bg; // 背景
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包

		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸

		int screenWidth = screenSize.width; // 获取屏幕的宽

		int screenHeight = screenSize.height; // 获取屏幕的高
		img = new ImageIcon("image/6.gif");
		jl_bg = new JLabel(img); // 背景
		JFrame frame = new JFrame();
		jl_bg.setBounds(-3, 20, 815, 625); // 设置位置和大小，先setLayout(null)一下。
		this.add(jl_bg, new Integer(Integer.MIN_VALUE));
		frame.setUndecorated(true);
		frame.setLayout(null);
		this.setBounds(0, -10, 500, 350);
		frame.add(this);
		frame.setBounds(screenWidth / 4+240, screenHeight / 4+100, 500, 330);// 设置界面位置以及大小

		frame.setVisible(true);
		LoginFrame login = new LoginFrame();

		
		Timer timer = new Timer();
		
		Timers1 timerTask = new Timers1(frame, login);
		timer.schedule(timerTask, 3300, 1000);
	}

	public static void main(String arg[]) {

Client c=new Client();
	}
}

class Timers1 extends TimerTask {
	LoginFrame nextFrame;
	JFrame f;

	public Timers1(JFrame f, LoginFrame nf) {
		this.nextFrame = nf;
		this.f = f;
	}

	public void run() {
		System.out.println("交易处理完成");

		f.dispose();
		nextFrame.timepanel();
		nextFrame.setVisible(true);
		this.cancel();
		// 下一个界面

	}
}