import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class TelaAcaoPersonagem extends JFrame{
	JButton K, M, L, N, J, P;
	ArrayList<JButton> arrayBtns;
	
	TelaAcaoPersonagem(){
		super("Intrigas Digital - Acao Personagem");
		
		setLayout(new FlowLayout());
		arrayBtns = new ArrayList<JButton>();
		
		K = new JButton("K");
		K.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.fluxo.btnAcaoPersonagem(Main.game.getJogadores().get(0), EnumPersonagem.KANE);
				fechaEssaEAbreAGame();
			}
		});
		add(K);
		arrayBtns.add(K);
		
		M = new JButton("M");
		M.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent arg0) {
			Main.fluxo.btnAcaoPersonagem(Main.game.getJogadores().get(0), EnumPersonagem.MAGNUS);
			fechaEssaEAbreAGame();
		}
		});
		add(M);
		arrayBtns.add(M);
		
		L = new JButton("L");
		L.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent arg0) {
			Main.fluxo.btnAcaoPersonagem(Main.game.getJogadores().get(0), EnumPersonagem.LAURA);
			fechaEssaEAbreAGame();
		}
		});
		add(L);
		arrayBtns.add(L);
		
		N = new JButton("N");
		add(N);
		arrayBtns.add(N);
		
		J = new JButton("J");
		J.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent arg0) {
			Main.fluxo.btnAcaoPersonagem(Main.game.getJogadores().get(0), EnumPersonagem.JULIUS);
			fechaEssaEAbreAGame();
		}
		});
		add(J);
		arrayBtns.add(J);
		
		P = new JButton("P");
		add(P);
		arrayBtns.add(P);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,960);
		setVisible(true);
	}
	
	void fechaEssaEAbreAGame() {
		Main.fluxo.ativaTelaGame();
		setVisible(false);
	}
}