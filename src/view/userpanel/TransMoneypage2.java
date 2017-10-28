package view.userpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

public class TransMoneypage2 extends FatherPanel {
	private MyButton transferpanelpage2Button[];
	private JTextField text;
	private JLabel label[];

	private MainFrame frame;
	private FatherPanel panel;
	  private float chargemoney=0;//转账手续费
	public float getChargemoney() {
		return chargemoney;
	}

	public void setChargemoney(float chargemoney) {
		this.chargemoney = chargemoney;
	}

	public TransMoneypage2(MainFrame f) {
		super(f);
		String name9[] = { "确认", "返回" };
		this.setButtonName(name9);
		this.setText(1);
		this.setLabel(2);
		// TODO Auto-generated constructor stub
		frame = f;
	}

	@Override
	public void init() {
		text=new JTextField();
		this.transferpanelpage2Button = this.getButton();
		// TODO Auto-generated method stub
		for (int i = 0; i < this.getButtonNumber(); i++) {
			this.add(transferpanelpage2Button[i]);
		}
		for (int i = 0; i < this.getTextNumber(); i++) {
			this.add(text);
		}

		label = new JLabel[2];
			for (int i = 0; i < 2; i++) {
				label[i] = new JLabel();
				this.add(label[i]);
			}
			transferpanelpage2Button[0].setIcon("image/确认左1.png", "image/确认左2.png",
					"image/确认左3.png", label[0]);
			transferpanelpage2Button[1].setIcon("image/取消1.png", "image/取消2.png",
					"image/取消3.png", label[1]);

		this.getLabel()[0].setText("请输入您的转账金额:");
		this.getLabel()[0].setFont(new Font("宋体", Font.BOLD, 30));
		text.setDocument(new MyDocument(12));
		text.addKeyListener(new KeyAdapter(){
			  public void keyTyped(KeyEvent e){
					int keyChar=e.getKeyChar();
					boolean has=false;
					char[] arrayChar=text.getText().toCharArray();
					int local=0;
					for (int i = 0; i < arrayChar.length; i++) {
						local++;//小数点所在位置
						if(arrayChar[i]=='.'){
							has=true;
							
							break;
						}
					}
					System.out.println("local="+local);
					if(has){//如果存在小数点则判断是否已经有两位小数
						
						if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {//如果不是数字则不允许输入
							if(arrayChar.length-local>=2){//如果小数位大于等于两位则不允许输入
								System.out.println("小数位大于2");
								e.consume(); 
							}else{
							System.out.println("小数位小于2");
							}
						} else {
							e.consume();  
						}
					}else{//不存在则可以输入小数点
						if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9||e.getKeyChar()=='.') {
							
						} else {
							e.consume();  
						}
					}
					
		
				  }
				});
		text.setFont(new Font("宋体", Font.BOLD, 30));
		// Label设置布局
		this.getTipLabel().setBounds(0, 340,810, 40);
		this.getLabel()[0].setBounds(250, 230, 320, 50);
		this.add(this.getLabel()[0]);
		this.add(this.getTipLabel());
		// 设置TExt布局
		text.setBounds(250, 280, 300, 50);
		this.add(text);
		
		// 设置背景图
		
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
		/* "回主菜单", "选择常用收款", "维护常用收款人", "更正", "确认" */
		if (e.getSource() == this.transferpanelpage2Button[0]) {
		System.out.println("转账确认");
		if (text.getText().trim().equals("")) {
			this.getTipLabel().setText( "输入金额不能为空！");
		} else {
			UserAction useraction=new UserAction();
			System.out.println("输入的金额是:" + text.getText());
			System.out.println("账号:" + this.getUser().getAcctothers());
				this.getUser().setDealMoney(
						Float.parseFloat(text.getText()));// 读取输入要转的金额
			try {
				if (useraction.queryBlank(this.getUser().getAccount())) {//判断用户是不是中国银行
					if (useraction.queryBlank(this.getUser().getAcctothers())) {//判断转账用户是不是中国银行
						try {
							if (this.getUser().getDealMoney() > 0
									&& useraction.getBalance(this.getUser().getAccount()) >= this.getUser().getDealMoney()) {
								
								panel = new TransMoneypage3(frame);
								panel.setChargemoney(0);
								changepanel();
							}else{
								this.getTipLabel().setText("余额不足！");
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						try {
							float chargemoney= (float) (this.getUser().getDealMoney()*0.02);
							if(chargemoney>=50){
								chargemoney=50;
							}
							if (this.getUser().getDealMoney() > 0
									&& useraction.getBalance(this.getUser().getAcctothers()) >= this.getUser().getDealMoney() +chargemoney) {
								panel = new TransMoneypage3(frame);
								panel.setChargemoney(chargemoney);
								changepanel();
							}else{
								this.getTipLabel().setText("余额不足！");
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				} else {
					try {
						float chargemoney= (float) (this.getUser().getDealMoney()*0.02);
						if(chargemoney>=50){
							chargemoney=50;
						}
						if (this.getUser().getDealMoney() > 0
								&& useraction.getBalance(this.getUser().getAcctothers()) >= this.getUser().getDealMoney() +chargemoney) {
							panel = new TransMoneypage3(frame);
							panel.setChargemoney(chargemoney);
							changepanel();
						}else{
							this.getTipLabel().setText("余额不足！");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		} else if (e.getSource() == transferpanelpage2Button[1]) {
			
	System.out.println("返回");
			panel =new TransMoneypage1(frame);
			changepanel();
		}
	}

}
