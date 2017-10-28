package view.userpanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.md.Deal;
import model.md.MyButton;
import view.father.FatherPanel;
import view.father.MainFrame;

public class Querypage3 extends FatherPanel {
	private MyButton querypanelpage3Button[];
	private MainFrame frame;
	private int querytime;
	private FatherPanel panel;
	private JLabel label[];
	private List<Deal> list;
	private JLabel Plabel[];

	public List<Deal> getList() {
		return list;
	}

	public void setList(List<Deal> list) {
		this.list = list;
	}
	public Querypage3(MainFrame f) {
		super(f);
		String name71[]={"三个月","半年内","一年内","返回"};
		
		this.setButtonName(name71);
		
		this.setLabel(1);
		this.frame=f;
		// TODO Auto-generated constructor stub
	}


	public void init() {
		querypanelpage3Button = this.getButton();
		// TODO Auto-generated method stub
		label=this.getLabel();

		for (int i = 0; i < this.getButtonNumber(); i++) {
			this.add(querypanelpage3Button[i]);
		}

		 	
		Plabel = new JLabel[4];
		for (int i = 0; i < 4; i++) {
			Plabel[i] = new JLabel();
			this.add(Plabel[i]);
		}		

		querypanelpage3Button[0].setIcon("image/三个月内1.png", "image/三个月内2.png",
				"image/三个月内3.png", Plabel[0]);
		querypanelpage3Button[1].setIcon("image/半年内1.png", "image/半年内2.png",
				"image/半年内3.png", Plabel[1]);
		querypanelpage3Button[2].setIcon("image/一年内1.png", "image/一年内2.png",
				"image/一年内3.png", Plabel[2]);
		querypanelpage3Button[3].setIcon("image/回主菜单右1.png", "image/回主菜单右2.png",
				"image/回主菜单右3.png", Plabel[3]);
		// TODO
		for (int i = 0; i < this.getLabelNumber(); i++) {
			this.add(label[i]);
		}
		
		label[0].setText("交易明细时间选择");
		label[0].setHorizontalAlignment(SwingConstants.CENTER);
		label[0].setFont(new Font("宋体", Font.BOLD, 40));
		label[0].setBounds(0, 80, 813, 60);
				this.setImg("image/背景图.png");
		this.setJl_bg(this.getImg());
		this.getJl_bg().setBounds(-3, -20, 815, 625); // 设置位置和大小，先setLayout(null)一下。
		this.add(this.getJl_bg(), new Integer(Integer.MIN_VALUE));//添加背景,需要最后添加，不然会不显示其他组件

	}
	
	public void changepanel(){
		this.timerTask.cancel();
		panel.init();
		frame.add(panel);
		this.setVisible(false);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.querypanelpage3Button[0]) {
			
			System.out.println("三个月交易明细");
			panel=new Querypage1(frame);
			panel.setList(this.getList());
		panel.setqueryTime(3);
		
		changepanel();	
			
		}  else if (e.getSource() == querypanelpage3Button[1]) {
	
			System.out.println("六个月交易明细");
			panel=new Querypage1(frame);
			panel.setList(this.getList());
			panel.setqueryTime(6);
		changepanel();
		}  else if (e.getSource() == querypanelpage3Button[2]) {
			System.out.println("一年交易明细");
			
			panel=new Querypage1(frame);
			panel.setList(this.getList());
			panel.setqueryTime(12);
		changepanel();
		}  else if (e.getSource() == querypanelpage3Button[3]) {
			
			panel=new Querypage1(frame);
			panel.setList(this.getList());
				panel.setqueryTime(1);
		changepanel();
		}  
	}

}
