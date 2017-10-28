package view.userpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import model.md.MyButton;
import view.father.FatherPanel;
import view.father.MainFrame;

public class TakeMoneypage3 extends FatherPanel {
	private MyButton takeinfo1Button[];
	private MainFrame frame; 	
	private int dealmoney;    private JLabel saveLabel[];
	private FatherPanel panel;	private JLabel label[];
	public int getDeal() {
		return dealmoney;
	}

	public void setDeal(int deal) {
		this.dealmoney = deal;
	}
	public TakeMoneypage3(MainFrame f) {
		super(f);
		String name5[] = { "继续取款",  "回主界面" };
		this.setButtonName(name5);
		this.setLabel(1);
		// TODO Auto-generated constructor stub
		this.frame=f;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		takeinfo1Button = this.getButton();
		for (int i = 0; i < this.getButtonNumber(); i++) {
			this.add(takeinfo1Button[i]);
		}
		label = new JLabel[3];
		for (int i = 0; i < 3; i++) {
			label[i] = new JLabel();
			this.add(label[i]);
		}		

		saveLabel=this.getLabel();

		takeinfo1Button[0].setIcon("image/继续取款1.png", "image/继续取款2.png",
				"image/继续取款3.png", label[0]);
//		takeinfo1Button[1].setIcon("image/打印凭条1.png", "image/打印凭条2.png",
//				"image/打印凭条3.png", label[1]);
		
		takeinfo1Button[1].setIcon("image/回主菜单右1.png", "image/回主菜单右2.png",
				"image/回主菜单右3.png", label[2]);
		saveLabel[0].setText("取款"+this.getUser().getDealMoney()+"成功！");
		saveLabel[0].setFont(new Font("宋体", Font.BOLD, 30));
		//Label设置布局
		saveLabel[0].setBounds(250, 230, 320, 50);
		this.add(saveLabel[0]);
		this.setImg("image/背景图.png");
		this.setJl_bg(this.getImg());
		this.getJl_bg().setBounds(-3, -20, 815, 625); // 设置位置和大小，先setLayout(null)一下。
		this.add(this.getJl_bg(), new Integer(Integer.MIN_VALUE));//添加背景,需要最后添加，不然会不显示其他组件
	}
	public void changepanel(){
		this.timerTask.cancel();
		panel.init();
		frame.add(panel);
		this.setVisible(false);
	}
//	public void changepanel(){
//		this.change(frame, panel);
//		this.setVisible(false);
//	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.takeinfo1Button[0]) {
			System.out.println("继续取款");
			panel = new TakeMoneypage1(frame);
changepanel();
			
		}  else if (e.getSource() == takeinfo1Button[1]) {
			System.out.println("回主界面");
			panel=new MainPanel1(frame);
			 changepanel();
		} 
	}

}
