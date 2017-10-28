package view.userpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.md.Deal;
import model.md.GETDate;
import model.md.MyButton;
import view.father.FatherPanel;
import view.father.MainFrame;

public class Querypage2 extends FatherPanel {
	private MyButton querypanelpage2Button[];
	private MainFrame frame;
	private Date nowdate;
	private SimpleDateFormat format;
	private List<Deal> list;
	private GETDate Gdate;
	private JLabel Plabel[];


	public List<Deal> getList() {
		return list;
	}

	public void setList(List<Deal> list) {
		this.list = list;
	}

	private FatherPanel panel;
	private JTable table;
	private JLabel label[];

	public Querypage2(MainFrame f) {
		super(f);
		String name7[] = { "更多查询", "回主菜单" };
		this.setButtonName(name7);


		this.setLabel(2);
		// TODO Auto-generated constructor stub
		this.frame = f;
	}

	public void init() {
		querypanelpage2Button = this.getButton();
		Gdate=new GETDate();
		// TODO Auto-generated method stub
		label = this.getLabel();

		for (int i = 0; i < this.getButtonNumber(); i++) {
			this.add(querypanelpage2Button[i]);
		}

		String[] columnNames = { "序号", "交易日期", "交易类型",

		"交易金额" };// 表头
		int resultsize = 0;
		nowdate = new Date();
		Plabel = new JLabel[2];
		for (int i = 0; i < 2; i++) {
			Plabel[i] = new JLabel();
			this.add(Plabel[i]);
		}

		querypanelpage2Button[0].setIcon("image/更多查询1.png", "image/更多查询2.png",
				"image/更多查询3.png", Plabel[0]);
		querypanelpage2Button[1].setIcon("image/回主菜单右1.png", "image/回主菜单右2.png",
				"image/回主菜单右3.png", Plabel[1]);

		format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);//格式化日期
		for (int i = 0; i < list.size(); i++) {//获取对应日期表的大小
			if (this.getqueryTime() == 3) {

				try {
					nowdate=format.parse(Gdate.getNowYear()+"-"+(Integer.parseInt(Gdate.getNowMonth())-3)+"-"+Gdate.getNowDay());
					Date checkDate = list.get(i).getDealDate();

					if (nowdate.before(checkDate)) {
						resultsize++;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// 现在的时间

			} else if (this.getqueryTime() == 6) {
				try {
					nowdate=format.parse(Gdate.getNowYear()+"-"+(Integer.parseInt(Gdate.getNowMonth())-6)+"-"+Gdate.getNowDay());
					Date checkDate = list.get(i).getDealDate();

					if (nowdate.before(checkDate)) {
						resultsize++;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//
			} else if (this.getqueryTime() == 12) {

				try {
					nowdate=format.parse((Integer.parseInt(Gdate.getNowYear())-1)+"-"+Gdate.getNowMonth()+"-"+Gdate.getNowDay());
					Date checkDate = list.get(i).getDealDate();

					if (nowdate.before(checkDate)) {
						resultsize++;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//
			} else {
				try {
					nowdate=format.parse(Gdate.getNowYear()+"-"+(Integer.parseInt(Gdate.getNowMonth())-1)+"-"+Gdate.getNowDay());
					Date checkDate = list.get(i).getDealDate();

					if (nowdate.before(checkDate)) {
						resultsize++;	
						System.out.println(checkDate.toString());
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}//
			}
		}

		Object[][] rowData = new Object[resultsize][4];//根据resultsize创建适当的data

		System.out.println("resultsize=" + resultsize);
		System.out.println("nowdate=" + nowdate);
		int number = 0;
		for (int i = 0; i < list.size(); i++) {// 每个页面只加17个记录
		boolean next=false;
		for (int j = 0; j < 4; j++) {
				
				if (this.getqueryTime() == 3) {
					
					Date checkDate = list.get(i).getDealDate();
					if (nowdate.before(checkDate)) {// 获取数据库时间，如果达到要求则将记录加入表格
						rowData[number][j] = new Object();
						switch (j) {
						case 0:
							rowData[number][j] = number;
							break;
							
						case 1:
							rowData[number][j] = list.get(i).getDealDate();
							break;
						case 2:
							if(list.get(i).getDealType().equals("4")){
								rowData[number][j] = "存入";
							}else if(list.get(i).getDealType().equals("3")){
								rowData[number][j] = "支出";
							}else if(list.get(i).getDealType().equals("5")){
								rowData[number][j] = "支出";
							}else if(list.get(i).getDealType().equals("6")){
								rowData[number][j] = "存入";
							}
							
							break;
						case 3:
							rowData[number][j] = list.get(i).getDealMoney();
							break;

						}
						next=true;
					}
				} else if (this.getqueryTime() == 6) {

					
					Date checkDate = list.get(i).getDealDate();
					if (nowdate.before(checkDate)) {// 获取数据库时间，如果达到要求则将记录加入表格
						rowData[number][j] = new Object();
						switch (j) {
						case 0:
							rowData[number][j] = number;
							break;

						case 1:
							rowData[number][j] = list.get(i).getDealDate();
							break;
						case 2:

							if(list.get(i).getDealType().equals("7")){
								rowData[number][j] = "存入";
							}else{
								rowData[number][j] = "支出";
							}
							
							break;
						case 3:
							rowData[number][j] = list.get(i).getDealMoney();
							break;

						}next=true;
					}
				} else if (this.getqueryTime() == 12) {
				
					Date checkDate = list.get(i).getDealDate();
					if (nowdate.before(checkDate)) {// 获取数据库时间，如果达到要求则将记录加入表格
						rowData[number][j] = new Object();
						switch (j) {
						case 0:
							rowData[number][j] = number;
							break;

						case 1:
							rowData[number][j] = list.get(i).getDealDate();
							break;
						case 2:

							if(list.get(i).getDealType().equals("7")){
								rowData[number][j] = "存入";
							}else{
								rowData[number][j] = "支出";
							}
							
							break;
						case 3:
							rowData[number][j] = list.get(i).getDealMoney();
							break;

						}next=true;
					}
				} else
				
				
				if (this.getqueryTime() == 1){//標志著一个月内的数据才获取
				
					
				
					Date checkDate = list.get(i).getDealDate();
					if (nowdate.before(checkDate)) {// 获取数据库时间，如果达到要求则将记录加入表格
	rowData[number][j] = new Object();
						switch (j) {
						case 0:
							
							rowData[number][j] = number;
							
							break;

						case 1:
							rowData[number][j] = list.get(i).getDealDate();
							break;
						case 2:

							if(list.get(i).getDealType().equals("7")){
								rowData[number][j] = "存入";
							}else{
								rowData[number][j] = "支出";
							}
							
							break;
						case 3:
							rowData[number][j] = list.get(i).getDealMoney();
							break;

						}next=true;
					}
				}
				
			}
		if(next){
			number++;
		}
			System.out.println("number="+number);
		}

		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);

		table = new JTable(model);

		// TODO
		for (int i = 0; i < this.getLabelNumber(); i++) {
			this.add(label[i]);
		}

		label[0].setText("历史交易明细");
		label[0].setHorizontalAlignment(SwingConstants.CENTER);
		label[0].setFont(new Font("宋体", Font.BOLD, 40));
		label[0].setBounds(0, 80, 813, 60);
		label[1].setText("本查询结果为卡主账户历史交易，但不包含银行收取交易费用交易。");
		label[1].setHorizontalAlignment(SwingConstants.CENTER);
		label[1].setFont(new Font("宋体", Font.BOLD, 25));
		label[1].setBounds(0, 450, 813, 40);

		JScrollPane scrollpane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		this.add(scrollpane);
		scrollpane.setBounds(0, 150, 813, 300);
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

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.querypanelpage2Button[0]) {

			panel = new Querypage3(frame);
			panel.setList(this.getList());
			changepanel();
		} else if (e.getSource() == querypanelpage2Button[1]) {

			panel =new MainPanel1(frame);
			panel.setList(this.getList());

			changepanel();
			;
		}
	}
}
