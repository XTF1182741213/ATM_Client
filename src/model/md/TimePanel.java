package model.md;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

public class TimePanel extends JPanel{
	int time = 61;
	JLabel label;
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setLayout(null);
		TimePanel panel = new TimePanel();
		frame.add(panel);
		
		frame.setBounds(100, 20, 100, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
	
	public TimePanel() {
		this.setBackground(Color.BLACK);
		this.setBounds(480, 2, 80, 80);
		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(Color.BLACK);
		Font font = getDefinedFont(60);
		label.setForeground(Color.GREEN);
		label.setFont(font);
		this.setLayout(new BorderLayout());
		this.add(label);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				time--;
				if (time <= 0) {
					return;
				}
				updateTimer(time);
			}

		}, 0, 1000);
		
	}

	public void updateTimer(int time) {
		time--;
		label.setText(time + "");
	}

    public Font getDefinedFont(float size){
    	// createFont(int fontFormat, InputStream fontStream)
        InputStream input = TimePanel.class.getResourceAsStream("lcd.TTF");
        Font font = null;
        try {
			font = Font.createFont(Font.TRUETYPE_FONT, input);
			font = font.deriveFont(size);
			
		} catch (FontFormatException | IOException e1) {
			e1.printStackTrace();
		}
        return font;
    }
 
}



