package view.manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.md.Card;
import model.md.Customerinfo;

public class SearchPanel extends JPanel implements ActionListener{
	ButtonGroup butGroup;
	JRadioButton bankCardNumber, idCardNumber;
	JTextField searchField;
	JButton searchBut;
	DefaultTableModel model;
	JTable table;
	JScrollPane scrollPane;
	
	public SearchPanel(){
		initSearchPanel();
	}
	
	public void initSearchPanel(){
		this.setLayout(null);
		this.setBounds(350, 50, 600, 500);
		this.setOpaque(false);
		
		butGroup = new ButtonGroup();
		bankCardNumber = new JRadioButton("银行卡号", true);
		idCardNumber = new JRadioButton("身份证号码");
		butGroup.add(bankCardNumber);
		butGroup.add(idCardNumber);
		
		searchField = new JTextField();
		searchBut = new JButton("查找");
		Font font = new Font("楷体", Font.BOLD+Font.ITALIC, 18);
		searchBut.setFont(font);
		
		//设置文本框字体，以及只能输入数字
		searchField.setFont(new Font("楷体", Font.BOLD, 22));
		searchField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				int keyChar=e.getKeyChar();
				
				if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
					
				} else {
					e.consume();  
				}
			}
		});
		
		JPanel panel = new JPanel();
		panel.add(bankCardNumber);
		panel.add(idCardNumber);
		bankCardNumber.setOpaque(false);
		idCardNumber.setOpaque(false);
		Color color = Color.CYAN;
		bankCardNumber.setForeground(color);
		idCardNumber.setForeground(color);
		panel.setOpaque(false);
		
		table = new JTable();
		
		panel.setBounds(0, 0, 300, 30);
		searchField.setBounds(40, 50, 300, 40);
		searchBut.setBounds(360, 50, 80, 40);
		table.setBounds(40, 110, 500, 400);
		this.add(panel);
		this.add(searchField);
		this.add(searchBut);
		this.add(table);
		
		//注册监听器
		searchBut.addActionListener(this);
		
		model = new DefaultTableModel();
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 150, 600, 300);
		this.add(scrollPane);
		scrollPane.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchBut){//点击“查找”按钮
			updateTable();
		}
		
	}
	public void updateTable(){
			Vector<String> head = new Vector<String>(); //表头
			//账号 /密码/余额/状态/姓名/身份证/手机号/地址
			String headString[] = {"账号", "密码", "余额", "状态", "姓名", "身份证", "预留手机", "地址"};
			for(String i: headString){
				head.add(i);
			}
			
			Vector<Vector> data = new Vector<Vector>();
			
			//根据银行卡号查找
			if(bankCardNumber.isSelected()){
				String bankAccount = searchField.getText(); //获取银行卡号
				try{
					
					Card find = new Card();
					String accountInfo = find.toString(bankAccount); //根据银行卡号获取其他信息
					String[] info = accountInfo.split(":");
					Vector vec = new Vector();
					for(String str: info){
						vec.add(str);
					}
					data.add(vec);
				}catch(NumberFormatException ee){
					 System.out.println("卡号非数字");
				}
				
			}
			//根据身份证号查找
			if(idCardNumber.isSelected()){
				String personId = searchField.getText(); //获取身份证号
				try{
					Customerinfo custnfo = new Customerinfo();
					ArrayList<String> accountInfos = custnfo.toString(personId); //根据身份证号获取其他信息
					for(String i: accountInfos){
						String[] info = i.split(":");
						Vector vec = new Vector();
						for(String str: info){
							vec.add(str);
						}
						data.add(vec);
					}
				}catch(NumberFormatException ee){
					 System.out.println("身份证号非数字");
				}
			}
			
			//初始化，添加表格
			model = new DefaultTableModel(data, head);
			table.setModel(model);
			if(table.getRowCount() > 0){
				//如果table有内容，设置table可见
				scrollPane.setVisible(true);
			}
		
	}
	//提供银行卡号
	public String getAccount(){
		System.out.println(table.getRowCount());
		int row = table.getSelectedRow();
		System.out.println("row:" + row);
		String account = (String)table.getValueAt(row, 0);
		System.out.println("account" + account);
		return account;
	}
}
