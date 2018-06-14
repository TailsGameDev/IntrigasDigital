import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class TelaAcaoCombinada extends JFrame{
	JButton KM, ML, LK, NJ, JJ, PP;
	ArrayList<JButton> arrayBtns;
	
	TelaAcaoCombinada(){
		super("Intrigas Digital - Acao Combinada");
		
		setLayout(new GridLayout(2,3));
		arrayBtns = new ArrayList<JButton>();
		
		KM = new JButton("KM");
		Carta c = new Carta(EnumPersonagem.KANE);
		Icon kane = c.getGedIcon();
		KM.setIcon(kane);
		add(KM);
		arrayBtns.add(KM);
		
		ML = new JButton("ML");
		Carta m = new Carta(EnumPersonagem.MAGNUS);
		Icon magnus = m.getGedIcon();
		ML.setIcon(magnus);
		add(ML);
		arrayBtns.add(ML);
		
		LK = new JButton("LK");
		Carta l = new Carta(EnumPersonagem.LAURA);
		Icon laura = l.getGedIcon();
		LK.setIcon(laura);
		add(LK);
		arrayBtns.add(LK);
		
		NJ = new JButton("NJ");
		Carta n = new Carta(EnumPersonagem.NINETA);
		Icon nineta = n.getGedIcon();
		NJ.setIcon(nineta);
		add(NJ);
		arrayBtns.add(NJ);
		
		JJ = new JButton("JJ");
		Carta j = new Carta(EnumPersonagem.JULIUS);
		Icon julius = j.getGedIcon();
		JJ.setIcon(julius);
		add(JJ);
		arrayBtns.add(JJ);
		
		PP = new JButton("PP");
		Carta p = new Carta(EnumPersonagem.PISTONE);
		Icon pistone = p.getGedIcon();
		PP.setIcon(pistone);
		add(PP);
		arrayBtns.add(PP);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,960);
		//setVisible(true);
	}
	
	public static void main (String args[]) {
		TelaAcaoCombinada telaAcaoCombinada = new TelaAcaoCombinada();
	}
}
