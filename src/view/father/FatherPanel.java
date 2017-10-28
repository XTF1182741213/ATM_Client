package view.father;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import view.userpanel.Client;
import view.userpanel.LoginFrame;
import view.userpanel.TransitionPanel;
import model.md.Deal;
import model.md.MyButton;
import model.md.TimePanel;
import model.md.User;

public abstract class FatherPanel extends JPanel implements ActionListener {
	private String buttonName[];
	private JTextField text;
	private JTextField inputText[];
	private JLabel label[];
	private int TextNumber;
	private int ButtonNumber;
	private int LabelNumber;
	private MyButton button[];
	private ImageIcon img;
	private JLabel jl_bg; // 背景
	private int querytime=1;
	private int dealmoney;
	private static User user;
	public  TimePanel timepanel;
	public TimerTask timerTask;//计时任务
	private JPanel that = this;
	private JLabel tipLabel;

public JLabel getTipLabel() {
		return tipLabel;
	}

	public void setTipLabel(JLabel tipLabel) {
		this.tipLabel = tipLabel;
	}

public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		FatherPanel.user = user;
	}

private static String account; 
	
	public int getqueryTime() {
		return querytime;
	}

	public void setqueryTime(int querytime) {
		this.querytime = querytime;
	}
	public ImageIcon getImg() {
		return img;
	}
	public void setImg(String path) {
		img=new ImageIcon(path);
	}
	public JLabel getJl_bg() {
		return jl_bg;
	}
	public void setJl_bg(ImageIcon image) {
		jl_bg = new JLabel(img); // 背景
	}
	public JTextField[] getInputText() {
		return inputText;
	}
	public JLabel[] getLabel() {
		return label;
	}
	public void setLabel(int number) {
		// 设置按钮名称以及按钮个数,给按钮加事件
		label = new JLabel[number];
						for (int i = 0; i < number; i++) {

							label[i] = new JLabel();

						}
					
		this.LabelNumber=number;
						
	}
	public int getLabelNumber() {
		return LabelNumber;
	}
	public void setLabelNumber(int labelNumber) {
		LabelNumber = labelNumber;
		
	}
	public void setInputText(int lenght,int number) {
		// 设置文本框个数以及长度
		inputText = new JTextField[number];
				for (int i = 0; i < number; i++) {

					inputText[i] = new JTextField(lenght);

				}
			
this.TextNumber=number;
				
	}
	public int getTextNumber() {
		return TextNumber;
	}
	public void setTextNumber(int textNumber) {
		TextNumber = textNumber;
	}


	

	public FatherPanel(final MainFrame f){
//		Client.login.timerTaskCencal();
		
		tipLabel=new JLabel("",SwingConstants.CENTER);
		tipLabel.setFont(new Font("宋体", Font.BOLD,25));
		tipLabel.setForeground(Color.red);
		//设置60秒无操作回主菜单
		Timer timer = new Timer();
		timerTask = new TimerTask() {
			public void run() {
				this.cancel();
				//回登录界面
				LoginFrame login=new LoginFrame();
				f.dispose();
				login.setVisible(true);
			}};
		timer.schedule(timerTask, 60000, 1000);
		
	
	this.setLayout(null);

	
	
	timepanel=new TimePanel();
	this.add(timepanel);
	timepanel.setVisible(true);
	
	
	}
	

	public void resetLoginPanel(){
//		Client.login.resetLoginFrame();
		System.out.println("resetLoginPanel...");
	}
	
	public String[] getButtonName() {// 获取按钮名称
		return buttonName;
	}


	public int getButtonNumber() {
		return ButtonNumber;
	}

	public JTextField getText() {
		return text;
	}

	public void setText(int i) {
		this.text = new JTextField(i);
	
	text.addKeyListener(new KeyAdapter(){
			  public void keyTyped(KeyEvent e){
					int keyChar=e.getKeyChar();
					
					if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
						
					} else {
						e.consume();  
					}
		
				  }
				});
		/*this.getText().setEditable(false);*/
	}
	public void setButtonName(String[] buttonName) {// 设置按钮名称以及按钮个数,给按钮加事件
		button = new MyButton[buttonName.length];
		for (int i = 0; i < buttonName.length; i++) {

			button[i] = new MyButton();

			button[i].addActionListener(this);
		}
		this.buttonName = buttonName;

		ButtonNumber = buttonName.length;
		if(ButtonNumber==1){
			
			button[0].setBounds(0, 490, 179, 55);
		
		}else if(ButtonNumber==2){
			button[0].setBounds(0, 490, 179, 55);
			button[1].setBounds(630, 490, 179, 55);
		}else if(ButtonNumber==3){
			button[0].setBounds(0, 490, 179, 55);
			button[1].setBounds(630, 410, 179, 55);
			button[2].setBounds(630, 490, 179, 55);
		}else if(ButtonNumber==4){
			button[0].setBounds(0, 410, 179, 55);
			button[1].setBounds(0, 490, 179, 55);
			button[2].setBounds(630, 410, 179, 55);
			button[3].setBounds(630, 490, 179, 55);
		}else if(ButtonNumber==5){
			button[0].setBounds(0, 410, 179, 55);
			button[1].setBounds(0, 490, 179, 55);
			button[2].setBounds(630, 330, 179, 55);
			button[3].setBounds(630, 410, 179, 55);
			button[4].setBounds(630, 490, 179, 55);
		}else if(ButtonNumber==6){
			button[0].setBounds(0, 330,179, 55);
			button[1].setBounds(0, 410, 179, 55);
			button[2].setBounds(0, 490, 179, 55);
			button[3].setBounds(630, 330, 179, 55);
			button[4].setBounds(630, 410, 179, 55);
			button[5].setBounds(630, 490, 179, 55);
		}
		else if(ButtonNumber==7){
			button[0].setBounds(0, 330,179, 55);
			button[1].setBounds(0, 410, 179, 55);
			button[2].setBounds(0, 490, 179, 55);
			button[3].setBounds(630, 250, 179, 55);
			button[4].setBounds(630, 330, 179, 55);
			button[5].setBounds(630, 410, 179, 55);
			button[6].setBounds(630, 490, 179, 55);
		}else if(ButtonNumber==8){
			button[0].setBounds(0, 250,179, 55);
			button[1].setBounds(0, 330,179, 55);
			button[2].setBounds(0, 410, 179, 55);
			button[3].setBounds(0, 490, 179, 55);
			button[4].setBounds(630, 250, 179, 55);
			button[5].setBounds(630, 330, 179, 55);
			button[6].setBounds(630, 410, 179, 55);
			button[7].setBounds(630, 490, 179, 55);
		}


	}
	public MyButton[] getButton() {// 获取按钮组
		if(button!= null){
			
		}else{
			System.out.println("空指针！");
		}

		return this.button;
	}
abstract public void init();
abstract public void changepanel();
public void change(JFrame f,FatherPanel nextPanel){
	TransitionPanel transpanel=new TransitionPanel();
	f.add(transpanel);
	
	transpanel.changeNextPanel(f,nextPanel);
	
	
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}	

	public int getDeal() {
		return dealmoney;
	}

	public void setDeal(int deal) {
		this.dealmoney = deal;
	}

	public static String getAccount() {
		return account;
	}

	public static void setAccount(String account) {
		FatherPanel.account = account;
	}

	public void setList(List<Deal> dealDetail) {
		// TODO Auto-generated method stub
		
	}
	public List<Deal> getList() {
		return null;
	}

	public void setChargemoney(float chargemoney) {
		// TODO Auto-generated method stub
		
	}
	
}
