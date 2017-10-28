package view.father;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.manager.HomePanel;
import view.userpanel.MainPanel1;

public class MainFrame extends FatherFrame {

	private int manageselect;

	public int getManageselect() {
		return manageselect;
	}

	public void setManageselect(int manageselect) {
		this.manageselect = manageselect;
	}

	private FatherPanel mainpanel;
	private HomePanel mainpanel2;

	private boolean who;



	public FatherPanel getMainpanel() {
		return mainpanel;
	}

	public void setMainpanel(FatherPanel mainpanel) {
		this.mainpanel = mainpanel;
	}

	public MainFrame(int x, int y, int width, int height, boolean who) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);

		this.who = who;
		this.setResizable(false);
	}

	public void display(MainFrame f) {
		// 初始化主界面组件
		if (who) {
			System.out.println("普通用户主界面");

			mainpanel = new MainPanel1(f);
			mainpanel.init();

		} else {
			
			System.out.println("管理员主界面");
			mainpanel2 = new HomePanel(f);
			
			
		}

		// 0.主面板 1.取款面板 2.取款面板 3.查询面板 4.转账面板 5.修改密码面板

		// 排版界面
		Maininit(who);
		this.setVisible(true);

	}

	private void Maininit(boolean who) {

		// TODO Auto-generated method stub

		if (who) {
			
			this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			mainpanel.setBounds(0, 0, 815, 600);
			this.getContentPane().add(mainpanel);
		} else {

			this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			mainpanel2.setBounds(0, 0, 815, 600);
			this.getContentPane().add(mainpanel2);
		}

	}

}
