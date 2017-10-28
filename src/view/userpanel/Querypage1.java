package view.userpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.md.Deal;
import model.md.MyButton;
import control.UserAction.UserAction;
import view.father.FatherPanel;
import view.father.MainFrame;

public class Querypage1 extends FatherPanel {
	private MyButton querypanelButton[];
	private MainFrame frame;
	private int querytime;
	private String tips;
	private FatherPanel panel;
	private JLabel label[];
	DecimalFormat df = new DecimalFormat("#######0.00");

	public Querypage1(MainFrame f) {
		super(f);
		String name6[] = { "交易明细", "回主菜单" };
		this.setButtonName(name6);
		this.setLabel(1);
		// TODO Auto-generated constructor stub
		this.frame = f;

	}

	@Override
	public void init() {

		querypanelButton = this.getButton();
		for (int i = 0; i < this.getButtonNumber(); i++) {
			this.add(querypanelButton[i]);
		}
		for (int i = 0; i < this.getLabelNumber(); i++) {
			this.add(this.getLabel()[i]);
		}

		label = new JLabel[2];
		for (int i = 0; i < 2; i++) {
			label[i] = new JLabel();
			this.add(label[i]);
		}
		// 切换面板前已经获取数据库的信息并存入user的balance属性中
		tips = "<html>" + "您的账户余额信息" + "<br>"

		+ "账户余额:" + df.format(this.getUser().getBalance()) + "<br>" + "可用余额:"
				+ df.format(this.getUser().getBalance()) + "<br>"

				+ "可取现余额:" + df.format(this.getUser().getBalance()) + "<br>"

				+ "可转账余额:" + df.format(this.getUser().getBalance()) + "<br>";
		this.getLabel()[0].setText(tips);
		this.getLabel()[0].setFont(new Font("宋体", Font.BOLD, 30));
		this.getLabel()[0].setBounds(250, 150, 430, 300);// 提示输入金额
		// 设置按钮图片
		querypanelButton[0].setIcon("image/明细1.png", "image/明细2.png",
				"image/明细3.png", label[0]);
		querypanelButton[1].setIcon("image/回主菜单右1.png", "image/回主菜单右2.png",
				"image/回主菜单右3.png", label[1]);

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

	/* "交易明细", "存款", "取款", "转账", "回主菜单" */
	public void actionPerformed(ActionEvent e) {
		// 按钮事件处理
		if (e.getSource() == this.querypanelButton[0]) {
			System.out.println("交易明细");

			UserAction useraction = new UserAction();

			panel = new Querypage2(frame);
			panel.setList(useraction.getDealDetail(this.getUser()));
			panel.setqueryTime(1);
			changepanel();
		} else if (e.getSource() == querypanelButton[1]) {

			System.out.println("回主界面");
			panel =new MainPanel1(frame);
			changepanel();
		}

	}

}
