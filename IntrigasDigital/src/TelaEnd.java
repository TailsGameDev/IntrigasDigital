import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class TelaEnd extends JFrame{
	JButton jogarDeNovoBtn;
	JLabel acabouLabel;
	
	TelaEnd(){
		super("Intrigas Digital - Fim do Jogo");
		
		acabouLabel= new JLabel("Fim de Jogo.");
		
		jogarDeNovoBtn = new JButton("Jogar novamente. Main.game.getJogadorVencedor(): " + Main.game.getJogadorVencedor().getNome());
		add(jogarDeNovoBtn);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,960);
		setVisible(true);
	}
	
}