import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TelaInit extends JFrame {
	
	private JLabel label;
	private JButton initBtn;
	private JPanel initBtnPanel, imgPanel;
	
	public TelaInit() {
		super("Intrigas Digital - Inicio");
		//setLayout(new FlowLayout());
		
		initBtn = new JButton("Iniciar");
		
		Color color = Color.black;//new Color(64,58,66);
		
		initBtnPanel = new JPanel();
		initBtnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 100, 10));
		initBtnPanel.add(initBtn);
		initBtnPanel.setBackground(color);
		
		initBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//otherFrame.setVisible(true);
			}
		});
		
		add(initBtnPanel, BorderLayout.SOUTH);
		
		imgPanel = new JPanel();
		Icon icon = new ImageIcon(getClass().getResource("5acucares.png"));
		label = new JLabel();
		label.setIcon(icon);
		imgPanel.add(label);
		imgPanel.setBackground(color);
		
		add(imgPanel);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setVisible(true);
	}
	
	public static void main (String args[]) {
		TelaInit telaInit = new TelaInit();
	}
	
}
