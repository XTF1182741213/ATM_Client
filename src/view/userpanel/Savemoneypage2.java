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

public class Savemoneypage2 extends FatherPanel {
	private MyButton Save2Button[];
	private JLabel saveLabel[];
	private FatherPanel panel;
	private String tips;
	private double chargemoney = 0;
	private JLabel label[];

	DecimalFormat df = new DecimalFormat("######0.00");
	private MainFrame frame;

	public Savemoneypage2(MainFrame f) {
		super(f);
		// TODO Auto-generated constructor stub
		String name2[] = { "确认存款", "返回" };
		this.setButtonName(name2);
		this.setLabel(1);
		this.frame = f;
		/* factory.createPanel(frame,this.getAccount()); */

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		Save2Button = this.getButton();
		saveLabel = this.getLabel();
		label = new JLabel[2];
		for (int i = 0; i < 2; i++) {
			label[i] = new JLabel();
			this.add(label[i]);
		}

		for (int i = 0; i < this.getButtonNumber(); i++) {
			this.add(Save2Button[i]);
		}
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
		Save2Button[0].setIcon("image/确认左1.png", "image/确认左2.png",
				"image/确认左3.png", label[0]);
		Save2Button[1].setIcon("image/取消1.png", "image/取消2.png",
				"image/取消3.png", label[1]);

		saveLabel[0].setText(tips);
		saveLabel[0].setFont(new Font("宋体", Font.BOLD, 30));

		// Label设置布局
		
		saveLabel[0].setBounds(215, 175, 425, 200);
		saveLabel[0].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				Color.BLACK));
		this.add(saveLabel[0]);
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
		UserAction useraction = new UserAction();
		if (e.getSource() == this.Save2Button[0]) {
			panel = new Savemoneypage3(frame);
			panel.getUser().setDealMoney(this.getDeal());
			changepanel(panel);
			this.getUser().setBalance(
					useraction.changeMoney(
							(float) (this.getDeal() - chargemoney),
							this.getAccount()));
			useraction.log(this.getDeal(), this.getAccount());

		} else if (e.getSource() == Save2Button[1]) {
			panel = new Savemoneypage1(frame);

			changepanel();
		}
	}
}
