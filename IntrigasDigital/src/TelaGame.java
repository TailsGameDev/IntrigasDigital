import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaGame extends JFrame{
	//coisas mais imediatas
	JPanel bottomPanel, leftPanel, topPanel, rightPanel, centerPanel;
	JButton acaoPersonagemBtn, acaoCombinadaBtn, duvidaBtn;
	JLabel cartaLeft, cartaRight;
	String nomeCartaLeft, nomeCartaRight;
	
	//jogadores
	ArrayList<Jogador> jogadores = Main.game.getJogadores();
	JLabel[] nomesLabel, c1Label, c2Label, torroesLabel;
	JPanel[] jogadoresPanels;
	
	//banco e baralho
	JPanel bancoPanel, baralhoPanel;
	JButton bancoBtn, baralhoBtn; 
	
	public TelaGame() {
		super("Intrigas Digital - Game");
		
		nomeCartaLeft=jogadores.get(0).getCartasNaMao().get(0).getImgFileGed();
		nomeCartaRight=jogadores.get(0).getCartasNaMao().get(1).getImgFileGed();
		//para fins de teste: -----------------------------------------------------------------------

		/*
		jogadores = new ArrayList<Jogador>();
		
		Jogador j0 = new Jogador("j0");
		jogadores.add(j0);
		
		Jogador j1 = new Jogador("j1");
		jogadores.add(j1);
		
		Jogador j2 = new Jogador("j2");
		jogadores.add(j2);
		*/
		//para fins de teste: -----------------------------------------------------------------------
		
		bottomPanel=new JPanel(new FlowLayout());
		
			acaoPersonagemBtn = new JButton("Acao Personagem");
			acaoCombinadaBtn = new JButton("Acao Combinada");
			duvidaBtn = new JButton("Duvidar");
			
			bottomPanel.add(acaoPersonagemBtn);
			bottomPanel.add(acaoCombinadaBtn);
			bottomPanel.add(duvidaBtn);
		
		add(bottomPanel, BorderLayout.SOUTH);
		
		leftPanel= new JPanel(new BorderLayout());
			
			Icon iconCartaLeft = new ImageIcon(getClass().getClassLoader().getResource(nomeCartaLeft));
			cartaLeft = new JLabel(iconCartaLeft);
			leftPanel.add(cartaLeft);
			
		add(leftPanel, BorderLayout.WEST);
		
		rightPanel= new JPanel(new BorderLayout());
		
			Icon iconCartaright = new ImageIcon(getClass().getClassLoader().getResource(nomeCartaRight));
			cartaRight = new JLabel(iconCartaright);
			rightPanel.add(cartaRight);
			
		add(rightPanel, BorderLayout.EAST);
		
		topPanel = new JPanel(new FlowLayout());
		
			nomesLabel = new JLabel[jogadores.size()];
			jogadoresPanels = new JPanel[jogadores.size()];
			c1Label = new JLabel[jogadores.size()];
			c2Label = new JLabel[jogadores.size()];
			torroesLabel = new JLabel[jogadores.size()];
			
			for (int i = 1 ; i<jogadores.size() && i<6 ; i++) {
				jogadoresPanels[i] = new JPanel(new BorderLayout());
				
					nomesLabel[i] = new JLabel(jogadores.get(i).getNome());
					jogadoresPanels[i].add(nomesLabel[i], BorderLayout.NORTH);
				
					Icon verso = new ImageIcon(getClass().getClassLoader().getResource("versinho.png"));
					c1Label[i]= new JLabel(verso);
					c2Label[i]= new JLabel(verso);
					jogadoresPanels[i].add(c1Label[i]);
					jogadoresPanels[i].add(c2Label[i], BorderLayout.EAST);
					
					torroesLabel[i] = new JLabel("Torroes: " + jogadores.get(i).getTorroes());
					jogadoresPanels[i].add(torroesLabel[i], BorderLayout.SOUTH);
					
				topPanel.add(jogadoresPanels[i]);
			}
		
		add(topPanel, BorderLayout.NORTH);
		
		centerPanel = new JPanel(new FlowLayout());
			bancoPanel = new JPanel(new BorderLayout());
				Icon sugar = new ImageIcon(getClass().getResource("5acucares.png"));
				bancoBtn = new JButton(sugar);
				bancoPanel.add(bancoBtn);
				
			centerPanel.add(bancoPanel);
			
			baralhoPanel = new JPanel(new BorderLayout());
				Icon baralho = new ImageIcon(getClass().getResource("versinho.png"));
				baralhoBtn = new JButton(baralho);
				baralhoPanel.add(baralhoBtn);
				
			centerPanel.add(baralhoPanel);
			
		add(centerPanel, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,960);
		setVisible(true);
	}
	
	public static void main (String args[]) {
		TelaGame telaGame = new TelaGame();
	}
}
