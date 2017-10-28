package view.manager;
import javax.swing.*;

import model.md.Card;
import view.father.MainFrame;
import view.userpanel.LoginFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel implements ActionListener{
	JButton addUserBut, changeInfoBut;
	JButton freezeBut, reportLossBut, delUserBut;
	JButton unFreezeBut, unReportLossBut, unDelUserBut, exitBut;
	JPanel changeInfoPanel;
	AddUserPanel addUserPanel;
	SearchPanel functionPanel;
	 JFrame frame;
	static String page = "";
	JButton button;
	String statusInfo[] = {"冻结", "正常", "挂失", "销户"};
	
	
	public HomePanel(MainFrame f){
		initHomePanel();
		functionPanel = new SearchPanel();
		functionPanel.setVisible(false);
		
		this.add(functionPanel);
		
		frame=f;
		f.setResizable(false); //不可改变大小
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		f.add(this);
	}
	//初始化HomePanel
	public void initHomePanel(){
		this.setLayout(null);
		this.setSize(1000, 600);
		this.setOpaque(false);
		
		addUserBut = new JButton();
		changeInfoBut = new JButton();
		freezeBut = new JButton();
		unFreezeBut = new JButton();
		reportLossBut = new JButton();
		unReportLossBut = new JButton();
		exitBut = new JButton();
		delUserBut = new JButton();
		button = new JButton("");
		
		//设置按钮坐标
		addUserBut.setBounds(0, 0, 150, 150);
		changeInfoBut.setBounds(150, 0, 150, 150);
		freezeBut.setBounds(0, 150, 150, 150);
		unFreezeBut.setBounds(150, 150, 150, 150);
		reportLossBut.setBounds(0, 300, 150, 150);
		unReportLossBut.setBounds(150, 300, 150, 150);
		exitBut.setBounds(0, 450, 150, 150);
		delUserBut.setBounds(150, 450, 150, 150);
		button.setBounds(810, 100, 80, 40);
		button.setFont(new Font("楷体", Font.BOLD+Font.ITALIC, 18));
		
		//设置按钮不可见
		addUserBut.setContentAreaFilled(false);  
		addUserBut.setBorderPainted(false);   
		addUserBut.setFocusPainted(false);
		changeInfoBut.setContentAreaFilled(false);  
		changeInfoBut.setBorderPainted(false);   
		changeInfoBut.setFocusPainted(false);
		freezeBut.setContentAreaFilled(false);  
		freezeBut.setBorderPainted(false);   
		freezeBut.setFocusPainted(false);
		unFreezeBut.setContentAreaFilled(false);  
		unFreezeBut.setBorderPainted(false);   
		unFreezeBut.setFocusPainted(false);
		reportLossBut.setContentAreaFilled(false);  
		reportLossBut.setBorderPainted(false);   
		reportLossBut.setFocusPainted(false);
		unReportLossBut.setContentAreaFilled(false);  
		unReportLossBut.setBorderPainted(false);   
		unReportLossBut.setFocusPainted(false);
		exitBut.setContentAreaFilled(false);  
		exitBut.setBorderPainted(false);   
		exitBut.setFocusPainted(false);
		delUserBut.setContentAreaFilled(false);  
		delUserBut.setBorderPainted(false);   
		delUserBut.setFocusPainted(false);
		button.setVisible(false);
		
		//按钮注册监听器
		addUserBut.addActionListener(this);
		changeInfoBut.addActionListener(this);
		freezeBut.addActionListener(this);
		unFreezeBut.addActionListener(this);
		reportLossBut.addActionListener(this);
		unReportLossBut.addActionListener(this);
		exitBut.addActionListener(this);
		delUserBut.addActionListener(this);
		button.addActionListener(this);
		
		this.add(addUserBut);
		this.add(changeInfoBut);
		this.add(freezeBut);
		this.add(unFreezeBut);
		this.add(reportLossBut);
		this.add(unReportLossBut);
		this.add(exitBut);
		this.add(delUserBut);
		this.add(button);
		
	}
	//设置背景图片
	public void paint(Graphics g){
		String path = "1000x600.png";
		ImageIcon icon = new ImageIcon(path);
		g.drawImage(icon.getImage(), 0, 0, this.getSize().width,this.getSize().height,this);
		super.paint(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addUserBut){ //点击“开户”按钮
			button.setVisible(false);
			if(addUserPanel != null){
				addUserPanel.setVisible(false);
			}
			if(functionPanel != null){
				functionPanel.setVisible(false);
			}
			
			
			
			System.out.println("开户按钮！");
			addUserPanel = new AddUserPanel();
			addUserPanel.setBounds(350, 50, 600, 500);
			this.add(addUserPanel);

		}else if(e.getSource() == exitBut){ //点击“退出”按钮
			button.setVisible(false);
			int i = JOptionPane.showConfirmDialog(frame, "确认退出吗？", "", JOptionPane.YES_NO_OPTION);
			if(i == 0){
				frame.dispose();
				LoginFrame login=new LoginFrame();
				login.timepanel();
				login.setVisible(true);
			}
		}else{
			button.setVisible(true); //恢复按钮可见
			if(addUserPanel != null){
				addUserPanel.setVisible(false);
			}
			if(functionPanel != null){
				functionPanel.setVisible(false);
			}
			
			functionPanel.setVisible(true);
			
			
			if(e.getSource() == changeInfoBut){ //点击“修改信息”按钮
				button.setText("修改信息");
				button.setSize(110, 40);
				page = "changeInfo";
			}else if(e.getSource() == freezeBut){ //点击“冻结”按钮
				button.setText("冻结");
				button.setSize(80, 40);
				page = "freeze";
				
			}else if(e.getSource() == unFreezeBut){ //点击“解除冻结”按钮
				button.setText("解除冻结");
				button.setSize(110, 40);
				page = "unFreeze";
			}else if(e.getSource() == reportLossBut){ //点击“挂失”按钮
				button.setText("挂失");
				button.setSize(80, 40);
				page = "reportLoss";
			}else if(e.getSource() == unReportLossBut){ //点击“解除挂失”按钮
				button.setText("解除挂失");
				button.setSize(110, 40);
				page = "unReportLoss";
			}else if(e.getSource() == delUserBut){ //点击“销户”按钮
				button.setText("销户");
				button.setSize(80, 40);
				page = "delUser";
			}else if(e.getSource() == button){
				try{
					String account = functionPanel.getAccount(); //获取卡号
					Card ac = new Card(); 
					int status = ac.getStatus(account); //获取当前状态
					
					if(page.equals("changeInfo")){
						System.out.println("equals");
					}
					if(page.equals("changeInfo")){
						System.out.println("account: " + account);//修改信息
						changeInfo(account); //弹出修改信息frame
					}else if(page.equals("freeze")){ //冻结
						if(status == 1){
							if(ac.changeStatus(account, 0))
								JOptionPane.showMessageDialog(null, "冻结成功！","冻结结果",  JOptionPane.INFORMATION_MESSAGE);
							functionPanel.updateTable();
						}
						else
							JOptionPane.showMessageDialog(frame, "不能从把" + statusInfo[status] + "状态的卡冻结");
					}else if(page.equals("unFreeze")){ //解除冻结
						if(status == 0)
						{
							ac.changeStatus(account, 1);
							functionPanel.updateTable();
						}
							
						else
							JOptionPane.showMessageDialog(frame, "不能从把" + statusInfo[status] + "状态的卡解除冻结");
					}else if(page.equals("reportLoss")){ //挂失
						if(status == 1)
						{
							if(ac.changeStatus(account, 2))
								JOptionPane.showMessageDialog(null, "挂失成功！","挂失结果",  JOptionPane.INFORMATION_MESSAGE);
							functionPanel.updateTable();
						}
							
						else
							JOptionPane.showMessageDialog(frame, "不能从把" + statusInfo[status] + "状态的卡挂失");
					}else if(page.equals("unReportLoss")){ //解除挂失
						if(status == 2)
						{
							if(ac.changeStatus(account, 1))
								JOptionPane.showMessageDialog(null, "该卡恢复正常！","结果",  JOptionPane.INFORMATION_MESSAGE);
							functionPanel.updateTable();
						}
							
						else
							JOptionPane.showMessageDialog(frame, "不能从把" + statusInfo[status] + "状态的卡解除挂失");
					}else if(page.equals("delUser")){ //销户
						if(status == 1)
						{
							if(ac.changeStatus(account, 3))
								JOptionPane.showMessageDialog(null, "销户成功！","销户结果",  JOptionPane.INFORMATION_MESSAGE);
							functionPanel.updateTable();
						}
							
						else
							JOptionPane.showMessageDialog(frame, "不能从把" + statusInfo[status] + "状态的卡销户");
					}
				}catch(Exception ee){
					System.out.println("点击button异常");
				}

			}

		}
	}

	public void changeInfo(String acct){
		JFrame frame1 = new JFrame();
		frame1.setBounds(200, 20, 500, 410);
		JPanel panel = new ChangeInfoPanel2(acct);
		frame1.add(panel);
		
		frame1.setVisible(true);
	}
}
