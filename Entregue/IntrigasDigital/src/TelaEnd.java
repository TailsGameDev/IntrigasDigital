import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class TelaEnd extends JFrame{
	JButton jogarDeNovoBtn;
	JLabel acabouLabel;
	
	TelaEnd(){
		super("Intrigas Digital - Fim do Jogo");
		
		setLayout(new FlowLayout());
		
		acabouLabel= new JLabel("Fim de Jogo. Vencedor: "+ Main.game.getJogadorVencedor().getNome());
		add(acabouLabel);
		
		jogarDeNovoBtn = new JButton("Jogar novamente.");
		jogarDeNovoBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Main.game = new ControlGame();
				Main.fluxo= new Fluxo();
				Main.telaInit = new TelaInit();
				Main.controlJogador = new ControlJogador();
				
				Main.telaCadastroJogadores = new TelaCadastroJogadores();
				Main.telaGame = null;
				Main.telaAcaoPersonagem = new TelaAcaoPersonagem();
				Main.telaAcaoCombinada = new TelaAcaoCombinada();
				Main.telaEnd.setVisible(false);

				
				Main.fluxo.ativaTelaInit();
				Main.telaCadastroJogadores.setVisible(false);
				Main.telaAcaoPersonagem.setVisible(false);
				Main.telaAcaoCombinada.setVisible(false);
				Main.telaEnd.setVisible(false);
				setVisible(false);
				
				Main.telaEnd = null;
			}
			
		});
		add(jogarDeNovoBtn);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,960);
		setVisible(true);
	}
	
}