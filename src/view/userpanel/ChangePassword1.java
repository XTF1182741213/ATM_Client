package view.userpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.md.MyButton;
import model.md.MyDocument;
import control.UserAction.UserAction;
import view.father.FatherPanel;
import view.father.MainFrame;

public class ChangePassword1 extends FatherPanel {
	private MyButton changepanelButton[];
	private JLabel label;
	private int frequency;
	private MainFrame frame;
	private FatherPanel panel;
	private JPasswordField text;
	private String password1 = "";
	private	String password2 = "";
	private String password3 = "";
	private JLabel Plabel[];

	public ChangePassword1(MainFrame f) {
		super(f);
		String name10[] = { "退卡", "确定" };
		this.setLabel(1);
		this.setText(50);
		this.setButtonName(name10);

		// TODO Auto-generated constructor stub
		frame = f;
		frequency = 0;
	}

	public void init() {
		text = new JPasswordField(50);
		label = new JLabel("请输入您的原始密码", SwingConstants.LEFT);
		label.setFont(new Font("宋体", Font.BOLD, 28));
		text.setFont(new Font("宋体", Font.BOLD, 30));
		this.changepanelButton = this.getButton();
		// TODO Auto-generated method stub
		for (int i = 0; i < this.getButtonNumber(); i++) {
			this.add(changepanelButton[i]);
		}
		Plabel = new JLabel[2];
		for (int i = 0; i < 2; i++) {
			Plabel[i] = new JLabel();
			this.add(Plabel[i]);
		}	
		text.setDocument(new MyDocument(6));
		text.setBounds(290, 280, 250, 40);
		label.setBounds(290, 240,400, 40);
		this.getTipLabel().setBounds(0, 340,810, 40);
		text.addKeyListener(new KeyAdapter(){
			  public void keyTyped(KeyEvent e){
					int keyChar=e.getKeyChar();
					
					if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
						
					} else {
						e.consume();  
					}
		
				  }
				});
		//设置按钮图片
		changepanelButton[0].setIcon("image/退卡左1.png", "image/退卡左2.png",
				"image/退卡左3.png", Plabel[0]);
		changepanelButton[1].setIcon("image/确认右1.png", "image/确认右2.png",
				"image/确认右3.png", Plabel[1]);
		this.add(text);
		this.add(label);
		this.add(this.getTipLabel());
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
	public void changepanel(FatherPanel pnext){
		this.timerTask.cancel();
	this.setVisible(false);
this.change(frame, pnext);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == this.changepanelButton[0]) {
			System.out.println("退卡");
			frame.dispose();
			LoginFrame login=new LoginFrame();
			login.timepanel();
			login.setVisible(true);

		} else if (e.getSource() == this.changepanelButton[1]) {
			if (frequency == 0) {

				if (text.getText().trim().equals("")) {
					this.getTipLabel().setText("密码不能为空！");
				} else {
					password1 = text.getText();
					System.out.println("原密码:" + password1);
					try {
						if (this.getUser().getPassword().equals(password1)) {
							frequency++;
							text.setText("");
							label.setText("请输入您的新密码");
						} else {
							// 账户密码错误则弹窗提示错误
							this.getTipLabel().setText("密码错误,请重新输入！");
						}

					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else if (frequency == 1) {
				if(text.getText().trim().length()==6){
					if (text.getText().trim().equals("")) {
					this.getTipLabel().setText("密码不能为空！");

				} else {
					password2 = text.getText();
					System.out.println("新密码:" + password2);
					frequency++;
					text.setText("");
					label.setText("请再次输入您的新密码");

				}
				}else{
					this.getTipLabel().setText( "密码必须为6位！");
				}
				
			} else if (frequency == 2) {
				if (text.getText().trim().equals("")) {
					this.getTipLabel().setText( "密码不能为空！");

				} else {
					password3 = text.getText();
					try {
						if (password2.equals(password3)) {
							frequency = 0;
							panel =new ChangePassword2(frame, this.getUser().getAccount());
								
								changepanel(panel);
							UserAction useraction = new UserAction();
							if (useraction.changePassword(this.getAccount(),
									password3)) {
							
							}

						} else {
							this.getTipLabel().setText( "2次输入密码不一样！");

						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		}
	}
}
