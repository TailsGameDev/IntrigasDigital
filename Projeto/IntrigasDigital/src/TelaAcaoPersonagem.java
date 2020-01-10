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
		
		setLayout(new GridLayout(2,3));
		arrayBtns = new ArrayList<JButton>();
		
		K = new JButton("K");
		Carta c = new Carta(EnumPersonagem.KANE);
		Icon kane = c.getGedIcon();
		K.setIcon(kane);
		K.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.fluxo.btnAcaoPersonagem(Main.game.getJogadores().get(0), EnumPersonagem.KANE);
				fechaEssaEAbreAGame();
			}
		});
		add(K);
		arrayBtns.add(K);
		
		M = new JButton("M");
		Carta m = new Carta(EnumPersonagem.MAGNUS);
		Icon magnus = m.getGedIcon();
		M.setIcon(magnus);
		M.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent arg0) {
			Main.fluxo.btnAcaoPersonagem(Main.game.getJogadores().get(0), EnumPersonagem.MAGNUS);
			fechaEssaEAbreAGame();
		}
		});
		add(M);
		arrayBtns.add(M);
		
		L = new JButton("L");
		Carta l = new Carta(EnumPersonagem.LAURA);
		Icon laura = l.getGedIcon();
		L.setIcon(laura);
		L.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent arg0) {
			Main.fluxo.btnAcaoPersonagem(Main.game.getJogadores().get(0), EnumPersonagem.LAURA);
			fechaEssaEAbreAGame();
		}
		});
		add(L);
		arrayBtns.add(L);
		
		N = new JButton("N");
		Carta n = new Carta(EnumPersonagem.NINETA);
		Icon nineta = n.getGedIcon();
		N.setIcon(nineta);
		N.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent arg0) {
			Main.fluxo.btnAcaoPersonagem(Main.game.getJogadores().get(0), EnumPersonagem.NINETA);
			fechaEssaEAbreAGame();
		}
		});
		add(N);
		arrayBtns.add(N);
		
		J = new JButton("J");
		Carta j = new Carta(EnumPersonagem.JULIUS);
		Icon julius = j.getGedIcon();
		J.setIcon(julius);
		J.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent arg0) {
			Main.fluxo.btnAcaoPersonagem(Main.game.getJogadores().get(0), EnumPersonagem.JULIUS);
			fechaEssaEAbreAGame();
		}
		});
		add(J);
		arrayBtns.add(J);
		
		P = new JButton("P");
		Carta p = new Carta(EnumPersonagem.PISTONE);
		Icon pistone = p.getGedIcon();
		P.setIcon(pistone);
		P.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent arg0) {
			Main.fluxo.btnAcaoPersonagem(Main.game.getJogadores().get(0), EnumPersonagem.PISTONE);
			fechaEssaEAbreAGame();
		}
		});
		add(P);
		arrayBtns.add(P);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,960);
		//setVisible(true);
	}
	
	void fechaEssaEAbreAGame() {
		Main.fluxo.ativaTelaGame();
		setVisible(false);
	}
}