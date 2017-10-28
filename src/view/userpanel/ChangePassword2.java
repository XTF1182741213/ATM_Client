package view.userpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import model.md.MyButton;
import view.father.FatherPanel;
import view.father.MainFrame;

public class ChangePassword2 extends FatherPanel {
	private MyButton transferpanelpage2Button[];
	private JLabel label[];

	private MainFrame frame;
	private FatherPanel panel;
	
	public ChangePassword2(MainFrame f, String account) {
		super(f);
		String name101[] = { "退卡", "回主界面" };
		this.setButtonName(name101);
		this.setLabel(1);
		

		// TODO Auto-generated constructor stub
		frame = f;
	}

	@Override
	public void init() {

		this.transferpanelpage2Button = this.getButton();
		// TODO Auto-generated method stub
		for (int i = 0; i < this.getButtonNumber(); i++) {
			this.add(transferpanelpage2Button[i]);
		}
		label = new JLabel[2];
		for (int i = 0; i < 2; i++) {
			label[i] = new JLabel();
			this.add(label[i]);
		}	

		transferpanelpage2Button[0].setIcon("image/退卡左1.png", "image/退卡左2.png",
				"image/退卡左3.png", label[0]);
		transferpanelpage2Button[1].setIcon("image/回主菜单右1.png", "image/回主菜单右2.png",
				"image/回主菜单右3.png", label[1]);

		this.getLabel()[0].setText("密码修改成功！");
		this.getLabel()[0].setFont(new Font("宋体", Font.BOLD, 30));
		this.getLabel()[0].setBounds(280, 150, 400, 150);// 提示输入金额
		this.add(this.getLabel()[0]);
		this.setImg("image/背景图.png");
		this.setJl_bg(this.getImg());
		this.getJl_bg().setBounds(-3, -20, 815, 625); // 设置位置和大小，先setLayout(null)一下。
		this.add(this.getJl_bg(), new Integer(Integer.MIN_VALUE));// 添加背景,需要最后添加，不然会不显示其他组件

	}

	public void changepanel() {
		this.timerTask.cancel();
		panel.init();
		frame.add(panel);
		this.setVisible(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.transferpanelpage2Button[0]) {
			System.out.println("退卡");
			frame.dispose();
			LoginFrame login=new LoginFrame();
			login.timepanel();
			login.setVisible(true);
		} else if (e.getSource() == transferpanelpage2Button[1]) {
			System.out.println("主界面");
			panel = new MainPanel1(frame);
			changepanel();

		}
	}

}
