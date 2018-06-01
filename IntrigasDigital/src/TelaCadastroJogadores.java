import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class TelaCadastroJogadores extends JFrame{
	
	private JComboBox numJogadoresComboBox;
	private static final String numJogadores[] = {"3","4","5","6"};
	private JPanel topPanel, centerPanel;
	
	private JLabel numJogadoresLabel;
	private ArrayList<JPanel> jogadoresPanels;
	private ArrayList<JLabel> jogadoresLabels;
	private ArrayList<JTextField> jogadoresTextFields;
	
	private JButton continuar;
	
	public TelaCadastroJogadores(){
		super("Intrigas Digital - Cadastro Jogadores");
		
		topPanel= new JPanel();
		
			numJogadoresLabel = new JLabel("OBS: vc eh o zero. Informe o numero de Jogadores: ");
			topPanel.add(numJogadoresLabel);
			
			numJogadoresComboBox = new JComboBox(numJogadores);
			numJogadoresComboBox.addItemListener(
					new ItemListener() {
						//@override
						public void itemStateChanged(ItemEvent i){
							//salva o numero escolhido no game.
							System.out.println(Integer.valueOf((numJogadores[numJogadoresComboBox.getSelectedIndex()])));
							Main.game.setNumInicialJogadores(Integer.valueOf((numJogadores[numJogadoresComboBox.getSelectedIndex()])));
							remove(centerPanel);
							renderizaJogadores();
							validate();
						}
					}
			);
			topPanel.add(numJogadoresComboBox);
			
		add(topPanel, BorderLayout.NORTH);
		
		renderizaJogadores();
			
		continuar = new JButton("Continuar");
		continuar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Fluxo.btnCadastroContinuar(jogadoresTextFields);
				
			}

		});
		add(continuar, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setVisible(true);
	}
	
	void renderizaJogadores() {
		jogadoresPanels = new ArrayList<JPanel>();
		jogadoresLabels = new ArrayList <JLabel>();
		jogadoresTextFields = new ArrayList<JTextField>();
		centerPanel = new JPanel(new GridLayout(2,3));
		for(int i = 0; i< Main.game.getNumInicialJogadores(); i++) {
			jogadoresPanels.add(new JPanel(new BorderLayout()));
			jogadoresLabels.add(new JLabel("Jogador "+i+":"));
			jogadoresPanels.get(i).add(jogadoresLabels.get(i), BorderLayout.NORTH);
			jogadoresTextFields.add(new JTextField("nomeDoJogador"+i));
			jogadoresPanels.get(i).add(jogadoresTextFields.get(i));//center
			centerPanel.add(jogadoresPanels.get(i));
		}
	add(centerPanel);
	}
	
	public static void main(String[] args) {
		Main.game = new ControlGame();
		Main.fluxo = new Fluxo();
		TelaCadastroJogadores t = new TelaCadastroJogadores();
	}
}
