package view.userpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import control.UserAction.UserAction;
import view.father.FatherPanel;
import view.father.MainFrame;
import model.db.AdminDAO;
import model.db.CustnfoDAO;
import model.md.Card;
import model.md.MyButton;
import model.md.MyDocument;
import model.md.TimePanel;
import model.md.User;

public class LoginFrame extends JFrame implements ActionListener {
	private JLabel userlabel;
	private JLabel paswlabel;
	
	private JRadioButton JRbutton[] = new JRadioButton[2];
	private ButtonGroup group;
	private Font font;
	private String account = new String();
	private String password = new String();
	private MyButton Button[] = new MyButton[2];
	private JTextArea text[] = new JTextArea[2];
	private JPasswordField passwordText;
	private JPanel p = new JPanel();
	private MainFrame mainGUI;
	private boolean who = true;
	private ImageIcon img;
	private JLabel jl_bg; // 背景
	private int lablenumber = 2;
	private JLabel lable[] = new JLabel[lablenumber];
	private int times=1;
	private CustnfoDAO logindao = new CustnfoDAO();
	public TimePanel timePanel;// 计时器
	public TimerTask timerTask;// 计时任务
	private JFrame that = this;
	private JLabel tipLabel;
	public void timepanel(){
			timePanel = new TimePanel();
		this.add(timePanel);
		// 设置60秒无操作退出程序
				Timer timer = new Timer();
				timerTask = new TimerTask() {
					public void run() {
						System.exit(0);
					}
				};
				timer.schedule(timerTask, 60000, 1000);
	}
	public LoginFrame() {// 构造方法
		// 初始化代表值
				who = true;
				// 初始化组件
		this.setLayout(null);
		this.setUndecorated(true);
	
		setResizable(false);// 设置不可改变大小
		font = new Font("宋体", Font.BOLD, 30);
		userlabel = new JLabel("用户名:", SwingConstants.RIGHT);
		paswlabel = new JLabel("密码:", SwingConstants.RIGHT);
		userlabel.setFont(font);
		paswlabel.setFont(font);
		tipLabel=new JLabel("",SwingConstants.CENTER);
		tipLabel.setFont(new Font("宋体", Font.BOLD,25));	
		tipLabel.setForeground(Color.red);
		text[0] = new JTextArea();
		text[0].setDocument(new MyDocument(18));
		
		// text[1] = new TextField(50);
		passwordText = new JPasswordField(50);
		passwordText.setDocument(new MyDocument(6));
		text[0].setFont(new Font("宋体", Font.BOLD, 25));
		text[0].setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		passwordText.setFont(new Font("宋体", Font.BOLD, 20));
		JRbutton[0] = new JRadioButton("普通用户");
		JRbutton[1] = new JRadioButton("管理员");
		JRbutton[0].setOpaque(false);
		JRbutton[1].setOpaque(false);

		img = new ImageIcon("image/背景图.png");
		jl_bg = new JLabel(img); // 背景
		group = new ButtonGroup();

		lable[0] = new JLabel(new ImageIcon("image/登录1.png"));
		lable[1] = new JLabel(new ImageIcon("image/退出1.png"));

		Button[0] = new MyButton();// 登陆
		Button[0].setIcon("image/登录1.png", "image/登录2.png", "image/登录3.png",
				lable[0]);
		Button[1] = new MyButton();// "重置"
		Button[1].setIcon("image/退出1.png", "image/退出2.png", "image/退出3.png",
				lable[1]);
		
	

		group.add(JRbutton[0]);
		group.add(JRbutton[1]);
		JRbutton[0].setSelected(true);

		// 添加事件
		Button[0].addActionListener(this);
		Button[1].addActionListener(this);

		JRbutton[0].addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				if (JRbutton[0].isSelected()) {
					who = true;
					System.out.println("0");
				}
			}

		});
		JRbutton[1].addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				if (JRbutton[1].isSelected()) {
					who = false;
					System.out.println("1");
				}
			}
		});
//		// 排版界面
		// 获取屏幕大小并设置大小
		int windowWidth = this.getWidth(); // 获得窗口宽

		int windowHeight = this.getHeight(); // 获得窗口高

		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包

		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸

		int screenWidth = screenSize.width; // 获取屏幕的宽

		int screenHeight = screenSize.height; // 获取屏幕的高
		// 添加组件进面板并控制位置
		// 815, 625
		p.setLayout(null);
		Button[0].setBounds(0, 500, 179, 55);
		Button[1].setBounds(630, 500, 179, 55);
		userlabel.setBounds(205, 200, 130, 40);
		text[0].setBounds(340, 200, 250, 40);
		paswlabel.setBounds(205, 250, 130, 40);
		passwordText.setBounds(340, 250, 250, 40);
		JRbutton[0].setBounds(315, 300, 90, 30);
		JRbutton[1].setBounds(410, 300, 90, 30);
		tipLabel.setBounds(0, 350, 810, 40);
		Button[0].setContentAreaFilled(false);
		Button[0].setBorder(null);
		Button[1].setContentAreaFilled(false);
		Button[1].setBorder(null);
		text[0].addKeyListener(new KeyAdapter(){
			  public void keyTyped(KeyEvent e){
					int keyChar=e.getKeyChar();
					
					if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
						
					} else {
						e.consume();  
					}
		
				  }
				});
		passwordText.addKeyListener(new KeyAdapter(){
			  public void keyTyped(KeyEvent e){
					int keyChar=e.getKeyChar();
					
					if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
						
					} else {
						e.consume();  
					}
		
				  }
				});
		Icon s = new ImageIcon("image/登录.png");
		/* Button[0].setIcon(new ImageIcon("image/登录.png")); */
		lable[0].setBounds(0, 500, 179, 55);
		lable[1].setBounds(630, 500, 179, 55);
		p.add(Button[0]);
		p.add(Button[1]);
		p.add(text[0]);
		p.add(passwordText);
		p.add(userlabel);
		p.add(paswlabel);
		p.add(JRbutton[0]);
		p.add(JRbutton[1]);
		p.add(tipLabel);
		p.add(lable[0]);
		p.add(lable[1]);
		jl_bg.setBounds(-3, -20, 815, 625); // 设置位置和大小，先setLayout(null)一下。
		p.add(jl_bg, new Integer(Integer.MIN_VALUE));
		p.setOpaque(false); // 设置透明
		
		this.setBounds(screenWidth / 4 + 100, screenHeight / 4, 809, 590);// 设置界面位置以及大小
		p.setBounds(0, 0, 814, 600);

		this.add(p);

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 事件处理

		if (e.getSource() == Button[0]) {
			account = text[0].getText();
			password = passwordText.getText();
			User user = new User();
			UserAction action = new UserAction();
			AdminDAO admindao = new AdminDAO();
			int status = action.checkStatus(account);
			if (who) {
				if(action.checkAccount(account)){
							if (action.findUser(account, password)) {// 链接数据库查询
					// 账户密码正确则读取卡信息，并转换界面
					
					if (status == 1) {
						this.timerTask.cancel();
						dispose();

						FatherPanel.setAccount(account);
						user.setAccount(account);
						user.setPassword(password);
						user.setUsername(action.getUserName(account));
						user.setStatus(1);
						FatherPanel.setUser(user);

						mainGUI = new MainFrame(this.getX(), this.getY(),
								this.getWidth(), this.getHeight(), who);
						mainGUI.display(mainGUI);

					} else if (status == 0) {
						this.timerTask.cancel();

						dispose();

						FatherPanel.setAccount(account);
						user.setAccount(account);
						user.setPassword(password);
						user.setUsername(action.getUserName(account));
						user.setStatus(0);
						FatherPanel.setUser(user);

						mainGUI = new MainFrame(this.getX(), this.getY(),
								this.getWidth(), this.getHeight(), who);
						mainGUI.display(mainGUI);
						JOptionPane.showMessageDialog(null, "账户冻结！只可进行查询与存款！", "提示", JOptionPane.ERROR_MESSAGE); 
//						JOptionPane.showMessageDialog(null, "账户冻结！只可进行查询与存款！",
//								"提示", JOptionPane.ERROR_MESSAGE);
					} else if (status == 2) {

						JOptionPane.showMessageDialog(null, "账户已挂失！", "提示",
								JOptionPane.ERROR_MESSAGE);
					} else if (status == 3) {
						tipLabel.setText(  "账户不存在！请重新输入！");
//						JOptionPane.showMessageDialog(null, "账户或密码错误！请重新输入！",
//								"提示", JOptionPane.ERROR_MESSAGE);
					}

				} else {
					 if (status == 3) {
							tipLabel.setText(  "账户不存在！请重新输入！");
//							JOptionPane.showMessageDialog(null, "账户或密码错误！请重新输入！",
//									"提示", JOptionPane.ERROR_MESSAGE);
						}else{
							if(times==1){
						tipLabel.setText( "账户或密码错误！连续三次错误将冻结卡！");

					}else if(times==2){
						tipLabel.setText("账户或密码错误！再错一次将冻结卡！");
					}else if(times==3){
						Card card=new Card();
						try {
							card.changeStatus(account, 0);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						tipLabel.setText("账户或密码错误！此卡已冻结！");
					}
					
					// 账户密码错误则弹窗提示错误
					times++;
						}

					
					

				}
				}else{
					tipLabel.setText( "账户不存在！请重新输入！");
				}
		

			} else if (admindao.findUser(account, password)){
				this.timerTask.cancel();
				dispose();
			
					mainGUI = new MainFrame(this.getX() - 100, this.getY(),
							1000, 600, who);
					mainGUI.display(mainGUI);

			}else {
					// 账户密码错误则弹窗提示错误
				tipLabel.setText( "账户或密码错误！请重新输入！");
//					JOptionPane.showMessageDialog(null, "账户或密码错误！请重新输入！", "提示",
//							JOptionPane.ERROR_MESSAGE);

				}

		} else {
			System.exit(0);
		}
	}

	public void resetLoginFrame() {
		this.timePanel.setVisible(false);
		this.timePanel = new TimePanel();
		this.add(timePanel);
		this.setVisible(true);
	}

	public void timerTaskCencal() {
		timerTask.cancel();
	}

}
