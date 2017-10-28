package view.userpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.UserAction.UserAction;
import model.db.CustnfoDAO;
import model.md.MyButton;
import model.md.MyDocument;
import model.md.User;
import view.father.FatherPanel;
import view.father.MainFrame;

public class Savemoneypage1 extends FatherPanel {
	private MyButton saveButton[];
	private MainFrame frame;
	private JLabel saveLabel[];
	private JTextField text;
	private FatherPanel panel;
	private int dealmoney;
	private String tips;
	private JLabel label[];
	public int getDeal() {
		return dealmoney;
	}

	public void setDeal(int deal) {
		this.dealmoney = deal;
	}

	public Savemoneypage1(MainFrame f) {
		super(f);
		frame = f;
		String name1[] = { "确认", "回主菜单" };
		this.setButtonName(name1);
		this.setText(20);
		
		this.setLabel(2);
		
		this.setLayout(null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		saveButton = this.getButton();
		saveLabel = this.getLabel();
		text=this.getText();
		text.setFont(new Font("宋体", Font.BOLD, 30));
		text.setDocument(new MyDocument(8));
		dealmoney = 0;

		saveLabel[0].setText("请输入您的存入钱款");
		saveLabel[0].setFont(new Font("宋体", Font.BOLD, 30));
		tips="<html>"+"本机只接收面额为100的纸币"+"<br>"
				+"单笔存款最大10000元"+"<br>"
				+"单日存款最大50000元"+"<br>";
		saveLabel[1].setText(tips);
		saveLabel[1].setFont(new Font("宋体", Font.BOLD, 20));
		label=new JLabel[2];
		for (int i = 0; i < 2; i++) {
			label[i]=new JLabel();
			this.add(label[i]);
		}
		//设置按钮图片
		saveButton[0].setIcon( "image/确认左1.png",  "image/确认左2.png", "image/确认左3.png", label[0]);
		saveButton[1].setIcon( "image/回主菜单右1.png",  "image/回主菜单右2.png", "image/回主菜单右3.png", label[1]);
		// Label设置布局
		this.getTipLabel().setBounds(0, 340,810, 40);
		saveLabel[0].setBounds(250, 230, 320, 50);
		saveLabel[1].setBounds(250, 360, 320, 200);
		// 设置TExt布局
		this.getText().setBounds(250, 280, 300, 50);
		// 设置背景图

		// 添加组件
		for (int i = 0; i < this.getButtonNumber(); i++) {
			this.add(saveButton[i]);
		}
		this.add(this.getTipLabel());
		this.add(saveLabel[0]);
		this.add(saveLabel[1]);
		this.add(text);
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
		// TODO Auto-generated method stub
		if (e.getSource() == this.saveButton[0]) {

			System.out.println("确认存款");
			UserAction useraction = new UserAction();
if(!this.getText().getText().trim().equals("")){
	int dealmoney = Integer.parseInt(this.getText().getText());
			float onedayDeal=0;
		
			if (dealmoney % 100 == 0 && dealmoney > 0 && dealmoney <= 10000) {
				if(useraction.checkSaveMoney(this.getUser().getAccount(),dealmoney)){
					panel = new Savemoneypage2(frame);
				panel.setDeal(dealmoney);

				changepanel();

				}else{

this.getTipLabel().setText("单日存款不得超过50000！");
				}
				
			} else {
				
				this.getTipLabel().setText("你输入的金额不正确,请重新输入！");
			}
}else{
		this.getTipLabel().setText("存款不得为空！");
}
			
		} else if (e.getSource() == saveButton[1]) {
			System.out.println("主界面");
			panel = new MainPanel1(frame);
			changepanel();

		}
	}

}
