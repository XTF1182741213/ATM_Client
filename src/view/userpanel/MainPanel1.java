package view.userpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.UserAction.UserAction;
import model.db.CustnfoDAO;
import model.md.MyButton;
import model.md.User;
import view.father.FatherPanel;
import view.father.MainFrame;

public class MainPanel1 extends FatherPanel {
	private MyButton mainButton[];
	private JLabel mainLabel[];
	private MainFrame frame;
	private FatherPanel panel;
	private FatherPanel that = this;
	
		private JLabel label[];


		public MainPanel1(MainFrame f) {
		
		super(f);
		this.setLayout(null);
		String name0[] = { "存款", "取款", "查询", "转账", "修改密码", "退卡" };
		this.setButtonName(name0);
		this.setText(20);
		this.setLabel(2);// 1.欢迎语句 2.
		this.frame=f;

		
	}
	
		//重构父类方法时zh
		
		public void init(){//在初始化的时候看是否有必要获取父类处理过的对象
			
			
	
			//"存款", "取款", "查询", "转账", "修改密码", "退卡" };
			mainButton=this.getButton();
			mainLabel=this.getLabel();
			for (int i = 0; i < this.getButtonNumber(); i++) {
				this.add(mainButton[i]);
			}
			for (int i = 0; i < this.getLabelNumber(); i++) {
				this.add(mainLabel[i]);
			}
			label=new JLabel[6];
			for (int i = 0; i < 6; i++) {
				label[i]=new JLabel();
				this.add(label[i]);
			}
			//为按钮设置背景
			mainButton[0].setIcon( "image/存款1.png",  "image/存款2.png", "image/存款3.png", label[0]);
			mainButton[1].setIcon( "image/取款1.png",  "image/取款2.png", "image/取款3.png", label[1]);
			mainButton[2].setIcon( "image/查询1.png",  "image/查询2.png", "image/查询3.png", label[2]);
			mainButton[3].setIcon( "image/转账1.png",  "image/转账2.png", "image/转账3.png", label[3]);
			mainButton[4].setIcon( "image/改密1.png",  "image/改密2.png", "image/改密3.png", label[4]);
			mainButton[5].setIcon( "image/退卡1.png",  "image/退卡2.png", "image/退卡3.png", label[5]);
			this.add(this.getText());
			mainLabel[0].setText(this.getUser().getUsername().substring(0, 1)+"先生"+",您好！");
			mainLabel[0].setFont(new Font("宋体", Font.BOLD, 30));
			mainLabel[1].setText("请您选择交易");
			mainLabel[1].setFont(new Font("宋体", Font.BOLD, 30));
			//按钮组设置布局0, 500, 179, 55
			//630, 500, 179, 55
			//Label设置布局
			mainLabel[0].setBounds(300, 230, 350, 50);
			mainLabel[1].setBounds(300, 290, 350, 50);
			//设置背景图
			this.setImg("image/背景图.png");
			this.setJl_bg(this.getImg());
			this.getJl_bg().setBounds(-3, -20, 815, 625); // 设置位置和大小，先setLayout(null)一下。
			
			this.add(this.getJl_bg(), new Integer(Integer.MIN_VALUE));//添加背景,需要最后添加，不然会不显示其他组件
			this.setOpaque(false); //设置透明 
			
			
		}
		
	public void changepanel(){
		this.timerTask.cancel();
		panel.init();
		frame.add(panel);
		this.setVisible(false);
	}
//		public void changepanel(FatherPanel pnext){
//			this.setVisible(false);
//	this.change(frame, pnext);
//	
//}
	public void actionPerformed(ActionEvent e) {
		// 按钮事件处理
		if(e.getSource()==this.mainButton[0]){
/*			String name[] = {"存款", "取款", "查询", "转账", "修改密码", "退卡" };*/
				System.out.println("存款");
				panel=new Savemoneypage1(frame);
				changepanel();
			
		}else if(e.getSource()==mainButton[1]){
			if(this.getUser().getStatus()==1){
				System.out.println("取款");
				panel = new TakeMoneypage1(frame);
				changepanel();
			}else if(this.getUser().getStatus()==0){
				
				JOptionPane.showMessageDialog(null, "账户冻结！只可进行查询与存款！", "提示", JOptionPane.ERROR_MESSAGE); 
			}
			
	
		}else if(e.getSource()==mainButton[2]){
				System.out.println("查询");
			UserAction useraction=new UserAction();
			
			
			this.getUser().setBalance(useraction.getBalance(this.getAccount()));//从数据库获取余额
			
			panel=new Querypage1(frame);
			changepanel();	
		}else if(e.getSource()==mainButton[3]){
			if(this.getUser().getStatus()==1){
				System.out.println("转账");
			panel=new TransMoneypage1(frame);
			changepanel();		
			}else if(this.getUser().getStatus()==0){
				
				JOptionPane.showMessageDialog(null, "账户冻结！只可进行查询！", "提示", JOptionPane.ERROR_MESSAGE); 
			}
			
			}else if(e.getSource()==mainButton[4]){
				if(this.getUser().getStatus()==1){
				System.out.println("修改密码");
				panel = new ChangePassword1(frame);
				changepanel();
				}else if(this.getUser().getStatus()==0){
					
					JOptionPane.showMessageDialog(null, "账户冻结！只可进行查询！", "提示", JOptionPane.ERROR_MESSAGE); 
				}
				
		}else if(e.getSource()==mainButton[5]){
			System.out.println("退卡");
			frame.dispose();
			LoginFrame login=new LoginFrame();
			login.timepanel();
			login.setVisible(true);
		}


	}

}
