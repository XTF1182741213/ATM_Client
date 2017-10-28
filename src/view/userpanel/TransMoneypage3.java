package view.userpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;

import model.md.MyButton;
import control.UserAction.UserAction;
import view.father.FatherPanel;
import view.father.MainFrame;

public class TransMoneypage3 extends FatherPanel {
	private MyButton transferpanelpage2Button[];
	private float chargemoney = 0;// 转账手续费
	private MainFrame frame;
	private FatherPanel panel;
	private String tips;
	private JLabel label[];
	DecimalFormat df = new DecimalFormat("######0.00");

	public TransMoneypage3(MainFrame f) {
		super(f);
		String name91[] = { "确认", "返回" };
		this.setButtonName(name91);
		this.setText(1);
		this.setLabel(1);

		// TODO Auto-generated constructor stub
		frame = f;
	}

	public void changepanel(FatherPanel pnext) {
		this.timerTask.cancel();
		this.setVisible(false);
		this.change(frame, pnext);
	}

	@Override
	public void init() {

		this.transferpanelpage2Button = this.getButton();
		// TODO Auto-generated method stub
		for (int i = 0; i < this.getButtonNumber(); i++) {
			this.add(transferpanelpage2Button[i]);
		}

		tips = "<html>" + "&nbsp;&nbsp;交易金额:"
				+ this.getUser().getDealMoney()
				+ "<br><br>"// 如果是中国银行账号，不用手续费
				+ "&nbsp;&nbsp;交易费用:" + df.format(this.getChargemoney())
				+ "<br>";
		label = new JLabel[2];
		for (int i = 0; i < 2; i++) {
			label[i] = new JLabel();
			this.add(label[i]);
		}
		transferpanelpage2Button[0].setIcon("image/确认左1.png", "image/确认左2.png",
				"image/确认左3.png", label[0]);
		transferpanelpage2Button[1].setIcon("image/取消1.png", "image/取消2.png",
				"image/取消3.png", label[1]);
		this.getLabel()[0].setText(tips);
		this.getLabel()[0].setFont(new Font("宋体", Font.BOLD, 30));
		this.getLabel()[0].setBounds(230, 150, 400, 150);// 提示输入金额
		this.add(this.getLabel()[0]);
		this.setImg("image/背景图.png");
		this.setJl_bg(this.getImg());
		this.getJl_bg().setBounds(-3, -20, 815, 625); // 设置位置和大小，先setLayout(null)一下。
		this.add(this.getJl_bg(), new Integer(Integer.MIN_VALUE));// 添加背景,需要最后添加，不然会不显示其他组件

	}

	public float getChargemoney() {
		return chargemoney;
	}

	public void setChargemoney(float chargemoney) {
		this.chargemoney = chargemoney;
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
			System.out.println("确认转账");
			UserAction useraction = new UserAction();
			try {
				System.out.println("登录账号:" + this.getUser().getAccount());
				System.out.println("对方账号:" + this.getUser().getAcctothers());
				System.out.println("转账金额:" + this.getUser().getDealMoney());
				// if (att.transferMoney(getAcct(), this.getAcount(),
				// this.getTransmoney()))
				panel = new TransMoneypage4(frame);
				changepanel(panel);
				useraction.transferMoney(this.getChargemoney(), this.getUser()
						.getAccount(), this.getUser().getAcctothers(), this
						.getUser().getDealMoney());

				useraction.log(this.getChargemoney(), this.getUser()
						.getDealMoney() + this.getChargemoney(), this.getUser()
						.getAcctothers(), this.getUser().getAccount());

				panel.setChargemoney(getChargemoney());

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == transferpanelpage2Button[1]) {
			System.out.println("取消转账");
			panel = new TransMoneypage2(frame);
			changepanel();

		}
	}

}
