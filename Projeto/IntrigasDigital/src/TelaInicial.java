import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel initBtnPanel;
	private JButton initBtn;

	private JPanel imgPanel;
	private JLabel logoLabel;

	public TelaInicial() {
		super("Intrigas Digital - Inicio");

		MakeInitBtnPanel();
		MakeInitBtn();

		MakeImgPanel();
		MakeLogoLabel();

		ConfigureWindow();

	}

	void MakeInitBtnPanel(){
		initBtnPanel = new JPanel();
		initBtnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 100, 10));
		initBtnPanel.setBackground(Color.black);
		add(initBtnPanel, BorderLayout.SOUTH);
	}

	void MakeInitBtn(){
		initBtn = new JButton("Iniciar");
		SetInitBtnAction();
		initBtnPanel.add(initBtn);
	}

	void SetInitBtnAction(){
		initBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Main.fluxo.ativaTelaCadastro();
			}
		});
	}

	void MakeImgPanel(){
		imgPanel = new JPanel();
		imgPanel.setBackground(Color.black);
		add(imgPanel);
	}

	void MakeLogoLabel(){
		Icon icon = new ImageIcon(getClass().getResource("5acucares.png"));
		logoLabel = new JLabel();
		logoLabel.setIcon(icon);
		imgPanel.add(logoLabel);
	}

	void ConfigureWindow(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setVisible(true);
	}

}
