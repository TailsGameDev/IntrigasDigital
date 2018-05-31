import java.awt.*;
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
		add(K);
		arrayBtns.add(K);
		
		M = new JButton("M");
		add(M);
		arrayBtns.add(M);
		
		L = new JButton("L");
		add(L);
		arrayBtns.add(L);
		
		N = new JButton("N");
		add(N);
		arrayBtns.add(N);
		
		J = new JButton("J");
		add(J);
		arrayBtns.add(J);
		
		P = new JButton("P");
		add(P);
		arrayBtns.add(P);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,960);
		setVisible(true);
	}
	
	public static void main (String args[]) {
		TelaAcaoPersonagem telaAcaoCombinada = new TelaAcaoPersonagem();
	}
}