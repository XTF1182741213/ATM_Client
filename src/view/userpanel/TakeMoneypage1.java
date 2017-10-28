package view.userpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
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

public class TakeMoneypage1 extends FatherPanel {
	private MyButton takeButton[];
	private MainFrame frame;
	private int dealmoney;
	private FatherPanel panel;
	private String tips;
	private JTextField text;
	private JLabel label[];
	public TakeMoneypage1(MainFrame f) {
		super(f);
		String name3[] = { "100", "500", "1000", "确认", "2500", "5000",
				"10000", "回主菜单" };
		this.setButtonName(name3);
		this.setText(20);
		this.setLabel(2);
		frame = f;

		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		takeButton = this.getButton();
		text=this.getText();
		text.setText(" ");
		text.setText("");
		text.setDocument(new MyDocument(8));
		this.getLabel()[0].setText("请选择或输入取款金额");
		this.getLabel()[0].setFont(new Font("宋体", Font.BOLD, 30));
		tips="<html>"+"本机现在提供面额为100的纸币"+"<br>"

				+"取款金额须为面额100的整数倍"+"<br>"
				+"单笔最大取款金额为10000元"+"<br>";
	
		this.getLabel()[1].setText(tips);
		this.getLabel()[1].setFont(new Font("宋体", Font.BOLD, 20));
		text.setFont(new Font("宋体", Font.BOLD, 30));
		dealmoney = 0;
		for (int i = 0; i < this.getButtonNumber(); i++) {
			this.add(takeButton[i]);
		}
		label = new JLabel[8];
		for (int i = 0; i < 8; i++) {
			label[i]=new JLabel();
			this.add(label[i]);
		}
		//设置按钮背景
		takeButton[0].setIcon( "image/100-1.png",  "image/100-2.png", "image/100-3.png", label[0]);
		takeButton[1].setIcon( "image/500-1.png",  "image/500-2.png", "image/500-3.png", label[1]);
		takeButton[2].setIcon( "image/1000-1.png",  "image/1000-2.png", "image/1000-3.png", label[2]);
		takeButton[3].setIcon( "image/确认左1.png",  "image/确认左2.png", "image/确认左3.png", label[3]);
		
		takeButton[4].setIcon( "image/2500-1.png",  "image/2500-2.png", "image/2500-3.png", label[4]);
		takeButton[5].setIcon( "image/5000-1.png",  "image/5000-2.png", "image/5000-3.png", label[5]);
		takeButton[6].setIcon( "image/10000-1.png",  "image/10000-2.png", "image/10000-3.png", label[6]);
		takeButton[7].setIcon( "image/回主菜单右1.png",  "image/回主菜单右2.png", "image/回主菜单右3.png", label[7]);
		// Label设置布局
		this.getTipLabel().setBounds(180, 250,440, 40);
		this.getLabel()[0].setBounds(250, 150, 320, 50);// 提示输入金额
		this.getLabel()[1].setBounds(250, 270, 320,220);
		// 设置TExt布局
		this.getText().setBounds(250, 200, 300, 50);

this.add(this.getTipLabel());
		this.add(this.getLabel()[0]);
		this.add(this.getLabel()[1]);
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
		if (e.getSource() == this.takeButton[0]) {
			dealmoney = 100;
			this.getText().setText(dealmoney + "");

		} else if (e.getSource() == takeButton[1]) {
			dealmoney = 500;
			this.getText().setText(dealmoney + "");

		} else if (e.getSource() == takeButton[2]) {
			dealmoney = 1000;
			this.getText().setText(dealmoney + "");

		} else if (e.getSource() == takeButton[3]) {
			if (this.getText().getText().trim().equals("")) {
				this.getTipLabel().setText( "取款金额不能为空！");
			} else {
				int dealmoney = Integer.parseInt(this.getText().getText());
				if (dealmoney % 100 == 0 && dealmoney > 0 && dealmoney <= 10000) {
					UserAction useraction = new UserAction();
					
					if(useraction.checkTakeMoney(this.getUser().getAccount(),dealmoney)){
						double balance = useraction.getBalance(this.getAccount());

						if (balance < dealmoney) {
							this.getTipLabel().setText("余额不足！");
						} else {
							panel =new TakeMoneypage2(frame);
							panel.setDeal(dealmoney);

						
							changepanel();
						}
					}else {
						this.getTipLabel().setText("单日取款金额不得超过20000！");
					}
				

				} else {
					this.getTipLabel().setText( "你输入的金额不正确,请重新输入！");

				}

			}

		} else if (e.getSource() == takeButton[4]) {
			dealmoney = 2500;
			this.getText().setText(dealmoney + "");

		} else if (e.getSource() == takeButton[5]) {

			dealmoney= 5000;
			this.getText().setText(dealmoney + "");
		} else if (e.getSource() == takeButton[6]) {

			dealmoney = 10000;
			this.getText().setText(dealmoney + "");
		} else if (e.getSource() == takeButton[7]) {

			panel = new MainPanel1(frame);
			changepanel();
		}
	}
}
