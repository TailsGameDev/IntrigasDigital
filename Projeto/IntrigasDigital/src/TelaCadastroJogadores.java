import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class TelaCadastroJogadores extends JFrame{
	
	private JComboBox escolhaNumeroDeJogadoresComboBox;
	private static final String possiveisNumerosDeJogadores[] = {"3","4","5","6"};
	private JPanel topPanel, centerPanel;
	
	private JLabel informeNumeroDeJogadoresLabel;

	private ArrayList<JPanel> jogadoresPanels;
	private ArrayList<JLabel> jogadoresLabels;
	private ArrayList<JTextField> jogadoresTextFields;
	
	private JButton continuarBtn;
	
	public TelaCadastroJogadores(){
		super("Intrigas Digital - Cadastro de Jogadores");
		
		MakeTopPanel();
		MakeInformeNumeroDeJogadoresLabel();
		MakeEscolhaNumeroDeJogadoresComboBox();
		MakeContinuarBtn();

		renderizaJogadores();

		ConfigurarJFrame();
	}

	void MakeTopPanel(){
		topPanel= new JPanel();
		add(topPanel, BorderLayout.NORTH);
	}

	void MakeInformeNumeroDeJogadoresLabel(){
		informeNumeroDeJogadoresLabel = new JLabel("OBS: vc eh o zero. Informe o numero de Jogadores: ");
		topPanel.add(informeNumeroDeJogadoresLabel);
	}

	void MakeEscolhaNumeroDeJogadoresComboBox(){
		escolhaNumeroDeJogadoresComboBox = new JComboBox(possiveisNumerosDeJogadores);
		SetComboBoxAction();
		topPanel.add(escolhaNumeroDeJogadoresComboBox);
	}

	void SetComboBoxAction(){
		escolhaNumeroDeJogadoresComboBox.addItemListener(
			new ItemListener() {
				public void itemStateChanged(ItemEvent i){ 

					int numeroDeJogadores = getComboBoxNumber();
					Main.game.setNumInicialJogadores(numeroDeJogadores);
					remove(centerPanel);
					renderizaJogadores();

				}
			}
		);
	}

	int getComboBoxNumber(){
		int selectedIndex = escolhaNumeroDeJogadoresComboBox.getSelectedIndex();
		String numeroDeJogadoresString = possiveisNumerosDeJogadores[selectedIndex];
		int numeroDeJogadores = Integer.valueOf((numeroDeJogadoresString));
		return numeroDeJogadores;
	}

	void MakeContinuarBtn(){
		continuarBtn = new JButton("Continuar");
		SetContinuarBtnAction();
		add(continuarBtn, BorderLayout.SOUTH);
	}

	void SetContinuarBtnAction(){
		continuarBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Fluxo.btnCadastroContinuar(jogadoresTextFields);
			}

		});
	}

	void ConfigurarJFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
	}
	
	void renderizaJogadores() {
		InicializaEstruturasDeDados();
		MakeCenterPanel();
		PovoaEstruturasDeDadosECenterPanel();
		validate();//renderiza
	}
	
	void InicializaEstruturasDeDados(){
		jogadoresPanels = new ArrayList<JPanel>();
		jogadoresLabels = new ArrayList <JLabel>();
		jogadoresTextFields = new ArrayList<JTextField>();
	}

	void MakeCenterPanel(){
		centerPanel = new JPanel(new GridLayout(2,3)); //(lines,columns)
		add(centerPanel);
	}

	void PovoaEstruturasDeDadosECenterPanel(){
		for(int indexJogador = 0; indexJogador< Main.game.getNumInicialJogadores(); indexJogador++) { //para cada jogador que vai ter
			MakePanel(indexJogador);
			MakeLabel(indexJogador);
			MakeTextField(indexJogador);
		}
	}

	void MakePanel(int indexJogador){
		jogadoresPanels.add(new JPanel(new BorderLayout()));
		centerPanel.add(jogadoresPanels.get(indexJogador));
	}

	void MakeLabel(int indexJogador){
		JLabel jogadorLabel = new JLabel("Jogador "+indexJogador+":");
		jogadoresLabels.add(jogadorLabel);
		JPanel jogadorPanel = jogadoresPanels.get(indexJogador);
		jogadorPanel.add(jogadorLabel, BorderLayout.NORTH);
	}

	void MakeTextField(int indexJogador){
		JTextField jogadorTextField = new JTextField("nomeDoJogador"+indexJogador);
		jogadoresTextFields.add(jogadorTextField);
		jogadoresPanels.get(indexJogador).add(jogadorTextField);
	}
}
