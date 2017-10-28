package view.manager;
import javax.swing.*;

import model.md.Card;
import model.md.Customerinfo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChangeInfoPanel2 extends JPanel implements ActionListener{
	JTextField nameField;
	JTextField phoneNumberField;
	JTextField idCardNumberField;
	JTextField addressField;
	JTextField bankCardNumberField;
	JPasswordField passwordField;
	JButton changeInfoBut;
	String acct;
	
	public ChangeInfoPanel2(String acct){
		
		this.acct = acct;
		initChangeInfoPanel();
		System.out.println("this.acct: " + this.acct);
	}
	
	public void initChangeInfoPanel(){
		//初始化panel
		this.setSize(500, 410);
		this.setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);
		
		//初始化JLabel
		JLabel nameLabel = new JLabel("姓名", JLabel.RIGHT);
		JLabel phoneNumberLabel = new JLabel("预留手机号码", JLabel.RIGHT);
		JLabel idCardNumberLabel = new JLabel("身份证号码", JLabel.RIGHT);
		JLabel addressLabel = new JLabel("现住地址", JLabel.RIGHT);
		JLabel bankCardNumberLabel = new JLabel("银行卡号", JLabel.RIGHT);
		JLabel passwordLabel = new JLabel("银行卡密码", JLabel.RIGHT);
		
		//设置JLabel字体
		Font font = new Font("楷体", Font.BOLD+Font.ITALIC, 18);
		nameLabel.setFont(font);
		phoneNumberLabel.setFont(font);
		idCardNumberLabel.setFont(font);
		addressLabel.setFont(font);
		bankCardNumberLabel.setFont(font);
		passwordLabel.setFont(font);
		
//		//设置JLabel字体颜色
//		Color color = Color.BLACK;
//		nameLabel.setForeground(color);
//		phoneNumberLabel.setForeground(color);
//		idCardNumberLabel.setForeground(color);
//		addressLabel.setForeground(color);
//		bankCardNumberLabel.setForeground(color);
//		passwordLabel.setForeground(color);
		
		
		//初始化JTextField
		initTextField();
		
		JPanel labelPanel = new JPanel(new GridLayout(7, 1, 0, 10));
		labelPanel.add(nameLabel);
		labelPanel.add(phoneNumberLabel);
		labelPanel.add(idCardNumberLabel);
		labelPanel.add(addressLabel);
		labelPanel.add(bankCardNumberLabel);
		labelPanel.add(passwordLabel);
		
		JPanel textPanel = new JPanel(new GridLayout(7, 1, 0, 20));
		textPanel.add(nameField);
		textPanel.add(phoneNumberField);
		textPanel.add(idCardNumberField);
		textPanel.add(addressField);
		textPanel.add(bankCardNumberField);
		textPanel.add(passwordField);
		
		labelPanel.setOpaque(false);
		textPanel.setOpaque(false);
		
		changeInfoBut = new JButton("修改信息");
		changeInfoBut.setFont(font);
		
		//500, 400
		labelPanel.setBounds(0, 10, 150, 350);
		this.add(labelPanel);
		textPanel.setBounds(160, 10, 300, 350);
		this.add(textPanel);
		changeInfoBut.setBounds(220, 320, 120, 40);
		this.add(changeInfoBut);
		
		//注册监听器
		changeInfoBut.addActionListener(this);
	}
	
	//获取客户原信息，初始化JTextField
	public void initTextField(){
		nameField = new JTextField();
		phoneNumberField = new JTextField();
		idCardNumberField = new JTextField();
		bankCardNumberField = new JTextField();
		addressField = new JTextField();
		passwordField = new JPasswordField();
		
		//设置JTextField字体，以及某些文本框只能输入数字
		Font font1 = new Font("楷体", Font.BOLD, 22);
		nameField.setFont(font1);
		phoneNumberField.setFont(font1);
		idCardNumberField.setFont(font1);
		bankCardNumberField.setFont(font1);
		addressField.setFont(font1);
		passwordField.setFont(font1);
		phoneNumberField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				int keyChar=e.getKeyChar();
				
				if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
					
				} else {
					e.consume();  
				}
			}
		});
		passwordField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				int keyChar=e.getKeyChar();
				
				if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
					
				} else {
					e.consume();  
				}
			}
		});
		
		Card account = new Card();
		System.out.println("acct: " + acct);
		String infoString = account.toString(acct);
		System.out.println(infoString);
		String[] info = infoString.split(":");

		//设置文本框原内容
		nameField.setText(info[4]);
		idCardNumberField.setText(info[5]);
		phoneNumberField.setText(info[6]);
		bankCardNumberField.setText(info[0]);
		addressField.setText(info[7]);
		passwordField.setText(info[1]);
		
		//身份证号，银行卡号，状态不能更改
		idCardNumberField.setEditable(false);
		bankCardNumberField.setEditable(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == changeInfoBut){
			try{
				System.out.println("修改信息");
				Customerinfo user = new Customerinfo();
				user.changeName(acct, nameField.getText()); //修改姓名
				user.changePhone(acct, phoneNumberField.getText()); //修改手机
				user.changeAddress(acct, addressField.getText()); //修改地址
				Card ac=new Card();
				char[] ch = passwordField.getPassword();
				String pwd = "";
				for(char c: ch){
					pwd += c;
				}
				ac.changePassword(acct, Integer.parseInt(pwd)); //修改密码
				JOptionPane.showMessageDialog(this, "修改信息成功");
			}catch(Exception ee){
				System.out.println("修改信息异常");
			}
			
		}
	}
}
