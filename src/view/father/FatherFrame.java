package view.father;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FatherFrame extends JFrame {
	// 界面大小和位置
	private int x;
	private int y;
	private int width;
	private int height;



	public int getX() {// 获取界面X坐标
		return x;
	}

	public void setX(int x) {// 设置界面X坐标
		this.x = x;
	}

	public int getY() {// 获取界面Y坐标
		return y;
	}

	public void setY(int y) {// 设置界面Y坐标
		this.y = y;
	}

	public int getWidth() {// 获取界面宽度
		return width;
	}

	public void setWidth(int width) {// 设置界面宽度
		this.width = width;
	}

	public int getHeight() {// 获取界面高度
		return height;
	}

	public void setHeight(int height) {// 设置界面高度
		this.height = height;
	}



	public FatherFrame() {// 构造方法
		this.setUndecorated(true); 

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void display() {

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
