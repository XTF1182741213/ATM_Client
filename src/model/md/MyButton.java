package model.md;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MyButton extends JButton {

	public MyButton(){
	
			
	

		
		
	}

	public void setIcon(final String first,final String second,final String third,final JLabel lable) {
		this.setContentAreaFilled(false);
		this.setBorder(null);
	
		lable.setIcon(new ImageIcon(first));
		lable.setBounds(this.getBounds());
		// TODO Auto-generated method stub
		this.addMouseListener(new   MouseAdapter()   {   
            public   void   mouseEntered(MouseEvent   e)   {   
            	lable.setIcon(new ImageIcon(second));
            }   
            public   void   mousePressed(MouseEvent e) {
            	lable.setIcon(new ImageIcon(third));
            }
            public   void   mouseExited(MouseEvent   e)   {   
            	lable.setIcon(new ImageIcon(first));
            }   
            public   void   mouseReleased(MouseEvent   e)   {   
            	lable.setIcon(new ImageIcon(first));
            }  
        });
		
	}
	
	
}
