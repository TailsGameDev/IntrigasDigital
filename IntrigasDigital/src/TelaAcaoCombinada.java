import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class TelaAcaoCombinada extends JFrame{
	JButton KM, ML, LK, NJ, JJ, PP;
	ArrayList<JButton> arrayBtns;
	
	TelaAcaoCombinada(){
		super("Intrigas Digital - Acao Combinada");
		
		setLayout(new FlowLayout());
		arrayBtns = new ArrayList<JButton>();
		
		KM = new JButton("KM");
		add(KM);
		arrayBtns.add(KM);
		
		ML = new JButton("ML");
		add(ML);
		arrayBtns.add(ML);
		
		LK = new JButton("LK");
		add(LK);
		arrayBtns.add(LK);
		
		NJ = new JButton("NJ");
		add(NJ);
		arrayBtns.add(NJ);
		
		JJ = new JButton("JJ");
		add(JJ);
		arrayBtns.add(JJ);
		
		PP = new JButton("PP");
		add(PP);
		arrayBtns.add(PP);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,960);
		setVisible(true);
	}
	
	public static void main (String args[]) {
		TelaAcaoCombinada telaAcaoCombinada = new TelaAcaoCombinada();
	}
}
