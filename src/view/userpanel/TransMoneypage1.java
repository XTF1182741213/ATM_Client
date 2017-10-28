package view.userpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.md.MyButton;
import model.md.MyDocument;
import control.UserAction.UserAction;
import view.father.FatherPanel;
import view.father.MainFrame;

public class TransMoneypage1 extends FatherPanel {
	private MyButton transferpanelButton[];
	private JLabel transLabel[];
	private MainFrame frame;
	private FatherPanel panel;
	private String tips;
	private JTextField text;
	private JLabel label[];
	public void changepanel(){
		this.timerTask.cancel();
		panel.init();
		frame.add(panel);
		this.setVisible(false);
	}
	public TransMoneypage1(MainFrame f) {
		super(f);
		String name8[] = { "确认", "更正", "回主菜单" };
		this.setButtonName(name8);
		this.setText(20);
		this.setLabel(3);
		this.setText(20);
		// TODO Auto-generated constructor stub
		frame=f;
	}

	@Override
	public void init() {
		this.getText().setEditable(true);
		text=this.getText();
		text.setFont(new Font("宋体", Font.BOLD, 30));
		text.setDocument(new MyDocument(20));
		this.transLabel=this.getLabel();
		this.transferpanelButton=this.getButton();

		// TODO Auto-generated method stub
		for (int i = 0; i < this.getButtonNumber(); i++) {
			this.add(transferpanelButton[i]);
		}
		label = new JLabel[3];
		for (int i = 0; i < 3; i++) {
			label[i] = new JLabel();
			this.add(label[i]);
		}		

		transLabel[0].setText("请输入转入卡号");
		transLabel[0].setFont(new Font("宋体", Font.BOLD, 30));
		
		tips="<html>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;安全提示"+"<br>"

				+"操作前请先确认收款账户的真实性,"+"<br>"
				+"不要轻信他人假冒的银行张贴的通知"+"<br>"
				
				+"不给陌生人汇款、转账,谨防被骗,"+"<br>"
				
				+"造成损失。"+"<br>";
		transLabel[1].setText(tips);
		transLabel[1].setFont(new Font("宋体", Font.BOLD, 23));
		
		transLabel[1].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		this.add(transLabel[1]);
		//设置按钮图片
		transferpanelButton[0].setIcon("image/确认左1.png", "image/确认左2.png",
				"image/确认左3.png", label[0]);
		transferpanelButton[1].setIcon("image/更正1.png", "image/更正2.png",
				"image/更正3.png", label[1]);
		transferpanelButton[2].setIcon("image/回主菜单右1.png", "image/回主菜单右2.png",
				"image/回主菜单右3.png", label[2]);

		//Label设置布局

this.getTipLabel().setBounds(180, 490,440, 40);
		transLabel[0].setBounds(250, 150, 320, 50);
		transLabel[1].setBounds(210, 260, 390, 190);//提示输入金额
		//设置TExt布局
		this.getText().setBounds(250, 205, 300, 50);
		this.add(text);
		this.add(this.getTipLabel());
		this.add(transLabel[0]);
		this.add(transLabel[1]);
		this.setImg("image/背景图.png");
		this.setJl_bg(this.getImg());
		this.getJl_bg().setBounds(-3, -20, 815, 625); // 设置位置和大小，先setLayout(null)一下。
		this.add(this.getJl_bg(), new Integer(Integer.MIN_VALUE));//添加背景,需要最后添加，不然会不显示其他组件
		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
/*		"回主菜单", "选择常用收款",  "维护常用收款人", "更正", "确认"*/
		if (e.getSource() == this.transferpanelButton[0]) {
			System.out.println("转账确认界面");	
			if(!this.getText().getText().trim().equals("")){
				
			UserAction useraction=new UserAction();
			if(useraction.checkAccount(this.getText().getText())){
				if(!this.getText().getText().equals(this.getUser().getAccount())){
						panel=new TransMoneypage2(frame);
					
						this.getUser().setAcctothers(this.getText().getText());
			changepanel();
				}else{
					this.getTipLabel().setText("不允许转账给自己！");
				}
			
			}else{
				this.getTipLabel().setText( "账户不存在，请重新输入！");
			}
			}else{
				this.getTipLabel().setText( "账户不允许为空，请重新输入！");
			}
		
			
		}  else if (e.getSource() == transferpanelButton[1]) {
		
				if(!this.getText().getText().equals(""))
					this.getText().setText(this.getText().getText().substring(0,this.getText().getText().length()-1));
			
		} else if (e.getSource() == transferpanelButton[2]) {	System.out.println("主界面");
		panel=new MainPanel1(frame);
		changepanel();

		

			
		} 
	}

}
