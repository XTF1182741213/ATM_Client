package view.userpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import model.md.MyButton;
import control.UserAction.UserAction;
import view.father.FatherPanel;
import view.father.MainFrame;

public class TakeMoneypage2 extends FatherPanel {
	private MyButton takeinfo1Button[];
	private MainFrame frame;
	private String tips;
	private JLabel label[];

	private FatherPanel panel;
	private double chargemoney = 0;
	DecimalFormat df = new DecimalFormat("#######0.00");

	public TakeMoneypage2(MainFrame f) {
		super(f);
		// TODO Auto-generated constructor stub
		String name4[] = { "确认", "取消" };
		this.setButtonName(name4);
		this.setLabel(3);

		this.frame = f;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		// 初始化组件

		takeinfo1Button = this.getButton();
		for (int i = 0; i < this.getButtonNumber(); i++) {
			this.add(takeinfo1Button[i]);
		}
		// label
		label = new JLabel[2];
		for (int i = 0; i < 2; i++) {
			label[i] = new JLabel();
			this.add(label[i]);
		}
		takeinfo1Button[0].setIcon("image/确认左1.png", "image/确认左2.png",
				"image/确认左3.png", label[0]);
		takeinfo1Button[1].setIcon("image/取消1.png", "image/取消2.png",
				"image/取消3.png", label[1]);

		try {
			UserAction useraction = new UserAction();
			if (useraction.queryBlank(this.getAccount())) {
				tips = "<html>" + "&nbsp;&nbsp;交易金额:"
						+ df.format(this.getDeal()) + "<br><br>"// 如果是中国银行账号，不用手续费
						+ "&nbsp;&nbsp;交易费用:" + df.format(chargemoney) + "<br>";
			} else {
				chargemoney = this.getDeal() * 0.02;
				if (chargemoney > 50)
					chargemoney = 50;// 如果最大手续费为50元
				tips = "<html>" + "&nbsp;&nbsp;交易金额:"
						+ df.format(this.getDeal()) + "<br><br>"// 非中行账号，收2%手续费
						+ "&nbsp;&nbsp;交易费用:" + df.format(chargemoney) + "<br>";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getLabel()[1].setText("请确认您的取款交易信息");
		this.getLabel()[1].setFont(new Font("宋体", Font.BOLD, 30));
		this.getLabel()[1].setBounds(245, 103, 450, 30);

		this.getLabel()[0].setText(tips);
		this.getLabel()[0].setFont(new Font("宋体", Font.BOLD, 28));
		this.getLabel()[0].setBounds(225, 175, 425, 200);// 提示输入金额
		this.getLabel()[0].setBorder(BorderFactory.createMatteBorder(1, 1, 1,
				1, Color.BLACK));

		this.getLabel()[2].setText("   交易费用以实际收取为准。");
		this.getLabel()[2].setFont(new Font("宋体", Font.BOLD, 18));
		this.getLabel()[2].setBounds(250, 490, 450, 30);

		this.add(this.getLabel()[1]);
		this.add(this.getLabel()[0]);
		this.add(this.getLabel()[2]);
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
	public void changepanel(FatherPanel pnext) {
		this.timerTask.cancel();
		this.setVisible(false);
		this.change(frame, pnext);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.takeinfo1Button[0]) {
			System.out.println("取款确认");
			panel =new TakeMoneypage3(frame);
			panel.getUser().setDealMoney(this.getDeal());
			changepanel(panel);
			UserAction useraction = new UserAction();
			this.getUser().setBalance(
					useraction.changeMoney(
							-(float) (chargemoney + this.getDeal()),
							this.getAccount()));// 取款
			useraction.log(-(float) (chargemoney + this.getDeal()),
					this.getAccount());// 取款记录
		

		} else if (e.getSource() == takeinfo1Button[1]) {
			System.out.println("取款取消返回取款界面");
			panel = new TakeMoneypage1(frame);
			changepanel();
		}
	}

}
