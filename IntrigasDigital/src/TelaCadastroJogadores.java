import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class TelaCadastroJogadores extends JFrame{
	
	private JComboBox numJogadoresComboBox; //eh o campo para escolher a quantidade de jogadores
	private static final String numJogadores[] = {"3","4","5","6"}; //sao os valores possiveis
	private JPanel topPanel, centerPanel; //paineis containteres
	
	private JLabel numJogadoresLabel; //eh o que diz Informe o num de jogadores
	private ArrayList<JPanel> jogadoresPanels;
	private ArrayList<JLabel> jogadoresLabels; //o que identifica jogador 1, 2, etc
	private ArrayList<JTextField> jogadoresTextFields; //os lugars pra por os nomes... textfields
	
	private JButton continuar;
	
	public TelaCadastroJogadores(){
		super("Intrigas Digital - Cadastro Jogadores");
		
		//topPanel contem a comboBox e o texto informe o numero de jogadores
		topPanel= new JPanel(); //a identacao comeca com a construcao de um painel, e termina com ele sendo adicionado
		
			numJogadoresLabel = new JLabel("OBS: vc eh o zero. Informe o numero de Jogadores: ");
			topPanel.add(numJogadoresLabel);
			
			numJogadoresComboBox = new JComboBox(numJogadores);
			numJogadoresComboBox.addItemListener( //a comboBox tem esse ItemListener que...
					new ItemListener() { //funciona chamando o metodo abaixo
						//@override
						public void itemStateChanged(ItemEvent i){ //esse metodo
							//salva o numero escolhido no game.
							Main.game.setNumInicialJogadores(Integer.valueOf((numJogadores[numJogadoresComboBox.getSelectedIndex()])));
							remove(centerPanel);
							renderizaJogadores(); //e renderiza de novo os jogadores necessarios
						}
					}
			);
			topPanel.add(numJogadoresComboBox);
			
		add(topPanel, BorderLayout.NORTH);
		
		renderizaJogadores(); // a parte dos jogadores foi concentrada nesse metodo, que tambem eh chamado quando se atualiza a comboBox
			
		continuar = new JButton("Continuar"); //no mais, resta o botao de continuar, e sua implementacao
		continuar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Fluxo.btnCadastroContinuar(jogadoresTextFields); //a sua logica foi colocada na classe de controle Fluxo, ao inves de ficar aqui no limite
				//a logica dos botoes deve ficar em metodos da classe fluxo e serem acessados a partir de Main.fluxo para condizer com o UML
			}

		});
		add(continuar, BorderLayout.SOUTH);
		
		//configuracoes necessarias de JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setVisible(true);
	}
	
	void renderizaJogadores() {
		jogadoresPanels = new ArrayList<JPanel>(); //primeiro limpam-se os arrays
		jogadoresLabels = new ArrayList <JLabel>();
		jogadoresTextFields = new ArrayList<JTextField>();
		
		centerPanel = new JPanel(new GridLayout(2,3)); //configuramos um grid Layout, 2 linhas, 3 colunas
		
		for(int i = 0; i< Main.game.getNumInicialJogadores(); i++) { //para cada jogador que vai ter
			jogadoresPanels.add(new JPanel(new BorderLayout())); //adicione um painel no vetor de paineis
			jogadoresLabels.add(new JLabel("Jogador "+i+":")); //e uma label que identifique o jogador no de labels
			jogadoresPanels.get(i).add(jogadoresLabels.get(i), BorderLayout.NORTH); //adiciona essa label no painel
			jogadoresTextFields.add(new JTextField("nomeDoJogador"+i)); //adiciona um textField no vetor
			jogadoresPanels.get(i).add(jogadoresTextFields.get(i));//adiciona o textField no panel
			centerPanel.add(jogadoresPanels.get(i)); //adiciona o panel no panel maior
		}
		add(centerPanel); //adiciona o panel maior no frame
		validate();//renderiza
	}
	
	/*
	public static void main(String[] args) {
		Main.game = new ControlGame();
		Main.fluxo = new Fluxo();
		TelaCadastroJogadores t = new TelaCadastroJogadores();
	}
	*/
}
