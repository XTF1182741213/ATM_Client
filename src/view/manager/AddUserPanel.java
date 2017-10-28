package view.manager;
import javax.swing.*;

import model.md.Card;
import model.md.Customerinfo;
import model.md.MyDocument;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddUserPanel extends JPanel{
	
	private JTextField nameTextField;//姓名
	private JTextField statusTextField;//状态
	private JTextField passwordTextField;//密码
	private JTextField confirmTextField;//确认密码
	private JTextField cardNumberTextField;//卡号
	private JTextField pidTextField;//身份证
	private JTextField homeAddressTextField;//家庭地址
	private JTextField phoneTextField;//手机号
	private JTextField bankAddressTextField;//银行地址
	private String name,homeaddress,bankaddress,idCardNumber;//姓名，地址，身份证
	private String phone;
	private String account ;
	private String bankCardNumber;//手机号，银行卡号
	private int password,confirmpassword;//银行卡密码
	private ButtonGroup group1,group2;
	private JComboBox<String> box;
	private int card1=8;
	private String sex="男";
	private int     status=1;//开户状态
	private JButton addUserBut;
	private JRadioButton radioButton1,radioButton2,radioButton3,radioButton4;
	public AddUserPanel(){
		initAddUserPanel();
	}
	
	public void initAddUserPanel(){
		//初始化panel
		this.setSize(600, 500);
		this.setLayout(null);
		this.setOpaque(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 500);
		add(panel);
		panel.setLayout(null);
		panel.setOpaque(false);
		
		//设置JLabel字体
		Font font = new Font("楷体", Font.BOLD+Font.ITALIC, 18);
		JLabel namelabel = new JLabel("姓名:");
		namelabel.setForeground(Color.CYAN);
		namelabel.setBounds(20, 17, 30, 23);
		panel.add(namelabel);
		
		//设置JTextField字体，以及某些文本框只能输入数字
		Font font1 = new Font("楷体", Font.BOLD, 22);
		nameTextField = new JTextField();
		nameTextField.setBounds(63, 11, 172, 35);
		panel.add(nameTextField);
		nameTextField.setColumns(10);
		nameTextField.setFont(font1);
		
		
		JLabel statuslabel = new JLabel("状态:");
		statuslabel.setForeground(Color.CYAN);
		statuslabel.setBounds(304, 21, 36, 19);
		panel.add(statuslabel);
		
		
		
		
		String[] s={"正常","挂失","冻结"};
	    box = new JComboBox(s); 
	    box.setBounds(348, 11, 90, 35);
	    panel.add(box);
//		statusTextField = new JTextField();
//		statusTextField.setBounds(348, 11, 90, 35);
//		panel.add(statusTextField);
//		statusTextField.setColumns(10);
//		statusTextField.setFont(font1);
	   
		
		
		
		
		
		JLabel passwordlabel = new JLabel("密码:");
		passwordlabel.setForeground(Color.CYAN);
		passwordlabel.setBounds(20, 75, 30, 15);
		panel.add(passwordlabel);
		
		passwordTextField = new JTextField();
		passwordTextField.setBounds(63, 62, 172, 35);
		panel.add(passwordTextField);
		passwordTextField.setColumns(10);
		passwordTextField.setFont(font1);
		
		JLabel confirmPasswordLabel = new JLabel("确认密码:");
		confirmPasswordLabel.setForeground(Color.CYAN);
		confirmPasswordLabel.setBounds(279, 75, 61, 15);
		panel.add(confirmPasswordLabel);
		
		confirmTextField = new JTextField();
		confirmTextField.setBounds(347, 63, 207, 34);
		panel.add(confirmTextField);
		confirmTextField.setFont(font1);
		confirmTextField.setColumns(10);
		
		JLabel cardlabel = new JLabel("卡类型:");
		cardlabel.setForeground(Color.CYAN);
		cardlabel.setBounds(10, 128, 48, 15);
		panel.add(cardlabel);
		
		ButtonGroup buttonGroup1= new ButtonGroup();
		radioButton1 = new JRadioButton("储蓄卡",true);
		radioButton1.setForeground(Color.CYAN);
		radioButton1.setOpaque(false);
		radioButton1.setBounds(63, 124, 79, 23);
		panel.add(radioButton1);
		
		radioButton2 = new JRadioButton("信用卡");
		radioButton2.setBounds(164, 124, 71, 23);
		radioButton2.setForeground(Color.CYAN);
		radioButton2.setOpaque(false);
		panel.add(radioButton2);
		buttonGroup1.add(radioButton1);
		buttonGroup1.add(radioButton2);
		
		JLabel sexlabel = new JLabel("性别:");
		sexlabel.setForeground(Color.CYAN);
		sexlabel.setBounds(304, 128, 35, 15);
		panel.add(sexlabel);
		
		ButtonGroup buttonGroup2= new ButtonGroup();
		radioButton3 = new JRadioButton("男",true);
		radioButton3.setForeground(Color.CYAN);
		radioButton3.setOpaque(false);
		radioButton3.setBounds(344, 124, 48, 23);
		panel.add(radioButton3);
		
		radioButton4 = new JRadioButton("女");
		radioButton4.setBounds(404, 124, 48, 23);
		radioButton4.setForeground(Color.CYAN);
		radioButton4.setOpaque(false);
		panel.add(radioButton4);
		buttonGroup2.add(radioButton3);
		buttonGroup2.add(radioButton4);
		
		
		
		JLabel cardNumberLabel = new JLabel("卡号:");
		cardNumberLabel.setForeground(Color.CYAN);
		cardNumberLabel.setBounds(20, 194, 30, 15);
		panel.add(cardNumberLabel);
		
		cardNumberTextField = new JTextField();
		cardNumberTextField.setEditable(false);
		cardNumberTextField.setBounds(69, 185, 485, 34);
		panel.add(cardNumberTextField);
		cardNumberTextField.setColumns(10);
		cardNumberTextField.setFont(font1);
		
		JLabel pidLabel = new JLabel("身份证:");
		pidLabel.setForeground(Color.CYAN);
		pidLabel.setBounds(10, 258, 42, 15);
		panel.add(pidLabel);
		
		pidTextField = new JTextField();
		pidTextField.setBounds(69, 248, 485, 35);
		panel.add(pidTextField);
		pidTextField.setColumns(10);
		pidTextField.setFont(font1);
		
		JLabel homeAddressLabel = new JLabel("家庭地址:");
		homeAddressLabel.setForeground(Color.CYAN);
		homeAddressLabel.setBounds(10, 323, 61, 23);
		panel.add(homeAddressLabel);
		
		homeAddressTextField = new JTextField();
		homeAddressTextField.setBounds(69, 317, 485, 35);
		panel.add(homeAddressTextField);
		homeAddressTextField.setColumns(10);
		homeAddressTextField.setFont(font1);
		
		JLabel phoneLabel = new JLabel("手机号：");
		phoneLabel.setForeground(Color.CYAN);
		phoneLabel.setBounds(10, 392, 54, 15);
		panel.add(phoneLabel);
		
		phoneTextField = new JTextField();
		phoneTextField.setBounds(71, 382, 256, 35);
		panel.add(phoneTextField);
		phoneTextField.setColumns(10);
		phoneTextField.setFont(font1);
		
		JLabel bankAddressLabel = new JLabel("银行地址:");
		bankAddressLabel.setForeground(Color.CYAN);
		bankAddressLabel.setBounds(348, 392, 64, 15);
		panel.add(bankAddressLabel);
		
		bankAddressTextField = new JTextField();
		bankAddressTextField.setBounds(422, 382, 131, 35);
		panel.add(bankAddressTextField);
		bankAddressTextField.setColumns(10);
		bankAddressTextField.setFont(font1);
		
		addUserBut = new JButton("开户");
		addUserBut.setBounds(261, 448, 79, 29);
		panel.add(addUserBut);
		
		//设置输入框长度
		 nameTextField.setDocument(new MyDocument(15));//姓名
	 passwordTextField.setDocument(new MyDocument(6));//密码
		 confirmTextField.setDocument(new MyDocument(6));//确认密码
		 cardNumberTextField.setDocument(new MyDocument(18));//卡号
		 pidTextField.setDocument(new MyDocument(18));//身份证
		 homeAddressTextField.setDocument(new MyDocument(30));//家庭地址
	 phoneTextField.setDocument(new MyDocument(16));//手机号
		bankAddressTextField.setDocument(new MyDocument(18));//银行地址
		phoneTextField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				int keyChar=e.getKeyChar();
				
				if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
					
				} else {
					e.consume();  
				}
			}
		});
		cardNumberTextField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				int keyChar=e.getKeyChar();
				
				if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
					
				} else {
					e.consume();  
				}
			}
		});
		passwordTextField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				int keyChar=e.getKeyChar();
				
				if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
					
				} else {
					e.consume();  
				}
			}
		});
		confirmTextField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				int keyChar=e.getKeyChar();
				
				if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
					
				} else {
					e.consume();  
				}
			}
		});

	    
	    ButtonListener listener=new ButtonListener();
	    addUserBut.addActionListener(listener);
	    radioButton1.addActionListener(listener);
	    radioButton2.addActionListener(listener);
	    radioButton3.addActionListener(listener);
	    radioButton4.addActionListener(listener);
	    box.addActionListener(listener);
	}

	
	
	
	//监听器
	
			private class ButtonListener implements ActionListener
			{
				
				public void actionPerformed(ActionEvent event){
					//选择开户状态
					  if (event.getSource() == box) {
						   int index = box.getSelectedIndex();
						   switch (index) {
						   case 0:
							   status=1;//开户时银行卡状态，0.冻结 1.正常 2.挂失 3.销户
						    System.out.println(box.getSelectedItem().toString());
						    break;
						   case 1:
							   status=2;
						    System.out.println(box.getSelectedItem().toString());
						    break;
						   case 2:
							   status=0;
						    System.out.println(box.getSelectedItem().toString());
						    break;
						   }
					System.out.println("卡号状态为:"+status);
					  }
					//选择卡的类型
					if(event.getSource()==radioButton1)
					{
						card1=8;//卡的类型。7表示信用卡，8表示储蓄卡
					}
					else if(event.getSource()==radioButton2)
					{
						card1=7;
					}
					
					//选择性别
					if(event.getSource()==radioButton3){
						sex="男";
					}
					else if(event.getSource()==radioButton4){
						sex="女";
					}
					
					
					if(event.getSource()==addUserBut){
						//自动生成账号
					    Date date = new Date();
					    SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
					     account = sdf.format(date);
					    cardNumberTextField.setText("6200"+account);
						bankCardNumber="6200"+account;//银行卡卡号
						
						name=nameTextField.getText();//姓名
						homeaddress=homeAddressTextField.getText();//用户住址
						bankaddress=bankAddressTextField.getText();//银行卡地址
						idCardNumber=pidTextField.getText();//身份证
						phone=phoneTextField.getText();//手机号
					
						if(nameTextField.getText().trim().equals("")||passwordTextField.getText().trim().equals("")
								||confirmTextField.getText().trim().equals("")||homeAddressTextField.getText().trim().equals("")	
							||bankAddressTextField.getText().trim().equals("")||pidTextField.getText().trim().equals("")
							||phoneTextField.getText().trim().equals(""))
						{
							JOptionPane.showMessageDialog(null, "信息不全，请填完整！","出错",  JOptionPane.ERROR_MESSAGE);
						}else{
							password=Integer.parseInt(passwordTextField.getText());//密码
							confirmpassword=Integer.parseInt(confirmTextField.getText());//确认密码
							if(password!=confirmpassword){
								JOptionPane.showMessageDialog(null,  "2次输入密码不一致！","出错", JOptionPane.ERROR_MESSAGE);
							}else if(passwordTextField.getText().length()!=6||confirmTextField.getText().length()!=6){
								
								
								JOptionPane.showMessageDialog(null,  "密码必须为6位数！","出错", JOptionPane.ERROR_MESSAGE);
							}else{
								
							
						double balance=0;//卡户时银行卡余额
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String rdate = df.format(new Date());// 读取现在系统时间
						String blank1="中国银行";
						Card ac = new Card();//银行卡
						Customerinfo information = new Customerinfo();//客户信息
						try {
							if (ac.create(bankCardNumber, password, rdate, balance, status, bankaddress,blank1,card1)
									&& information.createInfo(bankCardNumber,name,sex, idCardNumber, phone, homeaddress)) {
								JOptionPane.showMessageDialog(null, "开户成功！","开户结果",  JOptionPane.INFORMATION_MESSAGE);
								
								System.out.println("开户成功！");
							} else {
								JOptionPane.showMessageDialog(null, "失败", "开户失败", JOptionPane.ERROR_MESSAGE);
								System.err.println("开户失败！");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							}
					}	
				}}
	
			}
		}


