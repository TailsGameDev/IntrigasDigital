import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.function.Consumer;

public class TelaGame extends JFrame{
	//coisas mais imediatas
	JPanel bottomPanel, leftPanel, topPanel, rightPanel, centerPanel; //Panels servem como containers para outros componentes
	JButton atkIndefensavelBtn, acaoPersonagemBtn, acaoCombinadaBtn, duvidaBtn, proximoBtn; 
	JLabel cartaLeft, cartaRight; //sao as cartas do jogador principal
	String nomeCartaLeft, nomeCartaRight; //guardam o nome dos arquivos das imagens, nao dos personagens.
	
	//jogadores
	ArrayList<Jogador> jogadores = Main.game.getJogadores(); //informacoes
	JLabel[] nomesLabel, c1Label, c2Label, torroesLabel;
	JButton[] alvoBtns;
	JPanel[] jogadoresPanels;
	
	//centro
	JPanel bancoPanel, baralhoPanel, cemiterioPanel;
	JButton bancoBtn, baralhoBtn; 
	JTextArea console;
	ArrayList<JLabel> cartasMortasLabels = new ArrayList<JLabel>();
	ArrayList<Carta> cartasMortas = new ArrayList<Carta>();
	JScrollPane scrollConsole;
	
	//
	//EnumTipoAcao funcaoAlvo;
	JLabel seusTorroesLabel = new JLabel("vai ser atualizada pelo metodo exibeSeusTorroes");
	int indexAlvo;
	
	public TelaGame() {
		super("Intrigas Digital - Game");
		Main.game.setUltimoTipoAcao(EnumTipoAcao.PEGAR1TORRAO);
		Main.game.jogadorDaVez=jogadores.get(0);
		
		//para fins de teste: -----------------------------------------------------------------------
		/*ArrayList<Carta> c = new ArrayList<Carta>();
		c.add(new Carta(EnumPersonagem.JULIUS));
		c.add(new Carta(EnumPersonagem.NINETA));
		jogadores.get(0).setCartasNaMao(c);
		for(Jogador j : jogadores) {
			j.torroes = 100;
		}*/
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
		
		topPanel = new JPanel(new FlowLayout());
		add(topPanel, BorderLayout.NORTH);
		renderizaTopPanel(); //o TopPanel eh onde ficam os bots. Foi concentrado em um metodo                          //topPanel
		
		
		leftPanel= new JPanel(new BorderLayout()); //contem uma imagem                                               //leftPanel
			
		exibeLeftPanel();
		
		rightPanel= new JPanel(new BorderLayout());//contem uma imagem                                                  //rightPanel

		exibeRightPanel();
		
		
		
		//no centro tem o banco e o baralho
		centerPanel = new JPanel(new FlowLayout());                                                              //centerPanel
			bancoPanel = new JPanel(new BorderLayout());
				Icon sugar = new ImageIcon(getClass().getResource("5acucares.png"));
				bancoBtn = new JButton(sugar);
				
				bancoBtn.addActionListener(new ActionListener() {  @Override
					public void actionPerformed(ActionEvent e) {
						Main.controlJogador.pegar1Torrao(jogadores.get(0));
						exibeSeusTorroes();
					}
				});
				
				bancoPanel.add(bancoBtn);
				
			centerPanel.add(bancoPanel);
			
			baralhoPanel = new JPanel(new BorderLayout());
				Icon baralho = new ImageIcon(getClass().getResource("versinho.png"));
				baralhoBtn = new JButton(baralho);
				baralhoBtn.addActionListener(new ActionListener() {@Override
					public void actionPerformed(ActionEvent arg0) {
						Main.fluxo.btnBaralho(jogadores.get(0));
					}
				});
				baralhoPanel.add(baralhoBtn);
				
			centerPanel.add(baralhoPanel);
			
			renderizaConsole("");
			
			cemiterioPanel = new JPanel(new FlowLayout());
			centerPanel.add(cemiterioPanel);
			
		add(centerPanel, BorderLayout.CENTER);
		
		//contem os botoes de acao                                                                      //bottomPanel
		
		
		bottomPanel=new JPanel(new FlowLayout()); //a identacao comeca na criacao de um painel e termina quando ele eh adicionado
			
			atkIndefensavelBtn = new JButton("Ataque Indefensavel");
			
			atkIndefensavelBtn.addActionListener(new ActionListener() { @Override
				public void actionPerformed(ActionEvent e) {
					if(jogadores.get(0)==Main.game.getJogadorDaVez()) { //essa verificacao estah redundante com o sistema do Fluxo e ControleJogador
						Main.game.setUltimoTipoAcao(EnumTipoAcao.ATAQUEINDEFENSAVEL); //que tambem verifica se o jogador solicitante eh o jogadorDaVez
						exibeAlvoBtns();
					}
				}
			});
			
			
			acaoPersonagemBtn = new JButton("Acao Personagem"); //cria botao
			
			acaoPersonagemBtn.addActionListener(new ActionListener() { //adiciona ActionListener no Botao (quando ele eh apertado executa o metodo abaixo

				@Override
				public void actionPerformed(ActionEvent arg0) {
					Main.fluxo.ativaTelaAcaoPersonagem();
					setVisible(false);
				}
				
			});
			
			acaoCombinadaBtn = new JButton("Acao Combinada");
			acaoCombinadaBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Main.fluxo.ativaTelaAcaoCombinada();
					setVisible(false);
				}
				
			});
			proximoBtn = new JButton("Proximo");
			proximoBtn.addActionListener(new ActionListener() {	@Override
				public void actionPerformed(ActionEvent arg0) {
					boolean botDecidiuSeUsaDefesa = Main.game.botDecidiuSeUsaDefesa;
					//System.out.println(Main.game.getUltimoTipoAcao() + " " + Main.game.getUltimoPersUsado());
					if(Main.game.getUltimoTipoAcao() == EnumTipoAcao.PERSONAGEM) {
						//se um bot usou acao personagem
						EnumPersonagem p = Main.game.ultimoPersUsado;
						Jogador ultimoAlvo = jogadores.get(indexAlvo);
						Jogador jogadorZero = jogadores.get(0);
						if(p==EnumPersonagem.JULIUS || p == EnumPersonagem.NINETA) {
							if(ultimoAlvo == jogadorZero) {//se o alvo foi atacado e nao fez nada, toma o ataque
								Main.fluxo.veSeTemDuvidaEChamaAcaoPers(Main.game.getUltimoPersUsado(), Main.game.getJogadorDaVez());
							} else 
							if(botDecidiuSeUsaDefesa == false) {
								boolean usouDefesa;//esse valor aparentemente nem sera usado. Mas a execucao continua no sepah...
								usouDefesa = p==EnumPersonagem.JULIUS ? Main.fluxo.sePahUsaMagnus() : Main.fluxo.sePahUsaPistone();
								Main.game.botDecidiuSeUsaDefesa =true;
							} else {
								botDecidiuSeUsaDefesa = false;	//o jogadorDaVez so eh usado qd eh o Pistone o ultimo pers.
								Main.fluxo.veSeTemDuvidaEChamaAcaoPers(Main.game.getUltimoPersUsado(), Main.game.getJogadorDaVez());
							}
						} else {	//o jogadorDaVez so eh usado qd eh o Pistone o ultimo pers.
							if(p==EnumPersonagem.PISTONE) {//se for o pistone eh importante dizer que quem chama o pistone eh o alvo, nao o jogadorDaVez
								Main.fluxo.veSeTemDuvidaEChamaAcaoPers(Main.game.getUltimoPersUsado(), ultimoAlvo);
							} else {
								Main.fluxo.veSeTemDuvidaEChamaAcaoPers(Main.game.getUltimoPersUsado(), Main.game.getJogadorDaVez());
							}
						}
					} else if(Main.game.getJogadorDaVez()!=jogadores.get(0)) {
						Main.fluxo.passaVez();
						//se nao for nenhuma dessas paradas, que jah era para ter sido espero que soh reste o caso de o player 0 ter usado o pistone
					} else if(Main.game.getUltimoPersUsado() == EnumPersonagem.PISTONE && Main.game.ultimoTipoAcao == EnumTipoAcao.NAODUVIDAVEL) {
						Main.game.ultimoPersUsado = EnumPersonagem.KANE; //mudando pra nao acontecer de dar proximo caso passe a proxima rodada sem usarem personagem nenhum
						Main.fluxo.passaVez();
					}
				}
			});
			
			duvidaBtn = new JButton("Duvidar");
			duvidaBtn.addActionListener(new ActionListener() {	@Override
				public void actionPerformed(ActionEvent arg0) {
					if(Main.game.getUltimoTipoAcao() != EnumTipoAcao.NAODUVIDAVEL) {
						Main.game.jogadorDuvidando=jogadores.get(0);
						Main.controlJogador.duvidar(Main.game);
						Main.fluxo.passaVez();
					}
				}
			});
			
			bottomPanel.add(atkIndefensavelBtn);
			bottomPanel.add(acaoPersonagemBtn);
			bottomPanel.add(acaoCombinadaBtn);
			bottomPanel.add(duvidaBtn);
			bottomPanel.add(proximoBtn);
			exibeSeusTorroes(); //tive que colocar por ultimo porque vai renderizar mais vezes depois
		
		add(bottomPanel, BorderLayout.SOUTH);
		
		//operacoes normais de JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,960);
		setVisible(true);
	}
	
	public void renderizaTopPanel() {
		remove(topPanel);
		topPanel = new JPanel(new FlowLayout()); //obs jogadores eh Main.game.getJogadores();
		
		nomesLabel = new JLabel[jogadores.size()];//primeiro, limpa-se os vetores
		jogadoresPanels = new JPanel[jogadores.size()];
		alvoBtns = new JButton[jogadores.size()];
		c1Label = new JLabel[jogadores.size()];
		c2Label = new JLabel[jogadores.size()];
		torroesLabel = new JLabel[jogadores.size()];
		
		for (int i = 1 ; i<jogadores.size() ; i++) { //para cada jogador cadastrado no Main.game
			jogadoresPanels[i] = new JPanel(new BorderLayout()); //cria um painel
			
				nomesLabel[i] = new JLabel(jogadores.get(i).getNome()); //cria uma label com o seu nome
				jogadoresPanels[i].add(nomesLabel[i], BorderLayout.NORTH); //adiciona a label no painel
				
				Icon verso = new ImageIcon(getClass().getClassLoader().getResource("versinho.png")); //importa uma imagem de verso de carta
				c1Label[i]= new JLabel(verso); //e duas labels recebem essa imagem
				jogadoresPanels[i].add(c1Label[i]);//adiciona as labels no painel
				if(jogadores.get(i).getCartasNaMao().size()>1) {
					c2Label[i]= new JLabel(verso);
					jogadoresPanels[i].add(c2Label[i], BorderLayout.EAST);
				} else {
					//System.out.println("viu que um jogador nao tinha duas cartas na telaGame topPanel"+c2Label[i]);
				}
				
				torroesLabel[i] = new JLabel("Torroes: " + jogadores.get(i).getTorroes());//cria label com os torroes do jogador
				jogadoresPanels[i].add(torroesLabel[i], BorderLayout.SOUTH); //adiciona a label no painel
				
			topPanel.add(jogadoresPanels[i]);//adiciona o painel no painel maior
		}
		
		//exibeAlvoBtns();
		
		add(topPanel, BorderLayout.NORTH);//adiciona o painel maior no frame
	
		validate(); //renderiza
	}
	
	public void exibeCartaDoJogador() {
		try {
			int i = indexAlvo;
			Icon verso = new ImageIcon(getClass().getClassLoader().getResource("versinho.png")); //importa uma imagem de verso de carta
			Icon cartinha =jogadores.get(i).getCartasNaMao().get(0).getPeqIcon();
			
			jogadoresPanels[i].remove(c1Label[i]);
			c1Label[i] = new JLabel(cartinha);
			jogadoresPanels[i].add(c1Label[i]);//adiciona as labels no painel
			
			if(jogadores.get(i).getCartasNaMao().size()>1) {
				jogadoresPanels[i].remove(c2Label[i]);
				c2Label[i]= new JLabel(verso);
				jogadoresPanels[i].add(c2Label[i], BorderLayout.EAST);
			}
			validate();
		} catch (IndexOutOfBoundsException e) {
			//seria o caso do jogador atacado pelo Pistone ter duvidado e morrido, aae eh soh nao fazer nada mesmo
		}
	}
	
	public void renderizaConsole(String textToAdd) {
		
		String oldText;
		try{
			oldText = console.getText();
			//centerPanel.remove(scrollConsole);
		} catch(java.lang.NullPointerException e) {
			oldText = "Console Intrigas 2.0";
			console = new JTextArea("",20,50);
			console.setEditable(false);
			scrollConsole = new JScrollPane(console);
			scrollConsole.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		}
		
		console.setText(oldText + "\n"+ textToAdd);
		centerPanel.add(scrollConsole);
		validate();
	}
	
	public void exibeAlvoBtns() {
		alvoBtns = new JButton[jogadores.size()];
		for (int i = 1 ; i<jogadores.size() ; i++) { //para cada jogador ali
			alvoBtns[i]=new JButton("Alvo");
			
			//nao consegui usar a variavel i dentro do metodo actionPerformed, entao acabei fazendo esse switch
			switch (i) { //a ideia eh cada botao chamar o seu respectivo jogador como alvo
			case 1:
alvoBtns[i].addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {
	removeAlvoBtns();indexAlvo=1;Main.fluxo.chamaMetodoComAlvo(Main.game.getUltimoTipoAcao(), jogadores.get(0), jogadores.get(1));validate();}});
				break;//note que nem sempre o i eh igual ao index inicial do jogador, e soh importa o index atual dele na chamada mesmo. O botão pega pelo index atual.
			case 2:
alvoBtns[i].addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {
	removeAlvoBtns();indexAlvo=2;Main.fluxo.chamaMetodoComAlvo(Main.game.getUltimoTipoAcao(), jogadores.get(0), jogadores.get(2));validate();}});
				break;//antes onde estah funcaoAlvo=""; estava removeAlvoBtns, mas estava bugando, entao vou deixar os alvoBtns ali mesmo
			case 3:
alvoBtns[i].addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {
	removeAlvoBtns();indexAlvo=3;Main.fluxo.chamaMetodoComAlvo(Main.game.getUltimoTipoAcao(), jogadores.get(0), jogadores.get(3));validate();}});
				break;
			case 4:
alvoBtns[i].addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {
	removeAlvoBtns();indexAlvo=4;Main.fluxo.chamaMetodoComAlvo(Main.game.getUltimoTipoAcao(), jogadores.get(0), jogadores.get(4));validate();}});
				break;
			case 5:
alvoBtns[i].addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {
	removeAlvoBtns();indexAlvo=5;Main.fluxo.chamaMetodoComAlvo(Main.game.getUltimoTipoAcao() , jogadores.get(0), jogadores.get(5));validate();}});	
				break;
			default:
				System.out.println("caso default atingido no botao Alvo da Tela Game");
				break;
			}		
			jogadoresPanels[i].add(alvoBtns[i], BorderLayout.WEST);
		}
		validate();
	}
	
	void removeAlvoBtns() {
		for (int i = 1 ; i<jogadores.size() ; i++) {
			jogadoresPanels[i].remove(alvoBtns[i]);
		}
		alvoBtns=new JButton[jogadores.size()];
		validate();
	}
	
	
	void exibeSeusTorroes() {
		bottomPanel.remove(seusTorroesLabel);
		seusTorroesLabel = new JLabel("Seus Torroes: "+ jogadores.get(0).getTorroes());
		bottomPanel.add(seusTorroesLabel);
		validate();
	}
	
	void addCartaMorta(Carta card) {
		cartasMortasLabels.add(new JLabel(card.getPeqIcon()));
		cemiterioPanel.add(cartasMortasLabels.get(cartasMortasLabels.size()-1));
		//centerPanel.add(comp)
		validate();
		renderizaConsole("Morreu a carta " + card.getPersonagem().name());
		//System.out.println("chamouaddCartaMorta da telaGame");
	}
	
	//recarrega o leftPanel
	public void exibeLeftPanel() {
		remove(leftPanel);
		leftPanel = new JPanel(new BorderLayout());
		
		Icon iconCartaLeft;
		
		try {
			nomeCartaLeft=jogadores.get(0).getCartasNaMao().get(0).getImgFileGed(); //pega o nome do arquivo lah na carta do jogador 
			iconCartaLeft = new ImageIcon(getClass().getClassLoader().getResource(nomeCartaLeft));
		} catch (IndexOutOfBoundsException e) {
			iconCartaLeft = Main.game.getBaralho().getVerso();
		}
		cartaLeft = new JLabel(iconCartaLeft);
		cartaLeft.addMouseListener(new MouseListener() { @Override
			public void mouseClicked(MouseEvent arg0) { 
				Main.fluxo.clickCartaJogadorEsq();
			}//para clicar numa label tive que implementar os eventos de mouse todos. Se pa era melhor fazer um button mesmo.
			@Override public void mouseEntered(MouseEvent arg0) {} @Override public void mouseExited(MouseEvent arg0) {}
			@Override public void mousePressed(MouseEvent arg0) {} @Override public void mouseReleased(MouseEvent arg0) {}	
		});
		
		leftPanel.add(cartaLeft);
		
		add(leftPanel, BorderLayout.WEST);
	}
	
	public void exibeRightPanel() {
		remove(rightPanel);
		rightPanel = new JPanel(new BorderLayout());
		
		Icon iconCartaRight;
		
		try {
			nomeCartaRight=jogadores.get(0).getCartasNaMao().get(1).getImgFileGed();
			iconCartaRight = new ImageIcon(getClass().getClassLoader().getResource(nomeCartaRight));
		} catch (IndexOutOfBoundsException e) {
			iconCartaRight = Main.game.getBaralho().getVerso();
		}
		
		
		cartaRight = new JLabel(iconCartaRight);
		cartaRight.addMouseListener(new MouseListener() { @Override
			public void mouseClicked(MouseEvent arg0) { 
				Main.fluxo.clickCartaJogadorDir();
			}//para clicar numa label tive que implementar os eventos de mouse todos. Se pa era melhor fazer um button mesmo.
			@Override public void mouseEntered(MouseEvent arg0) {} @Override public void mouseExited(MouseEvent arg0) {}
			@Override public void mousePressed(MouseEvent arg0) {} @Override public void mouseReleased(MouseEvent arg0) {}	
		});
		rightPanel.add(cartaRight);
		
	add(rightPanel, BorderLayout.EAST);
	}
	
	public void renderizaQuaseTudo() {
		renderizaTopPanel();
		exibeLeftPanel();
		exibeRightPanel();
		exibeSeusTorroes();
	}
	/*
	public static void main (String args[]) {
		TelaGame telaGame = new TelaGame();
	}
	*/
	/*
	void removeCartaDoUltimoAlvoBot() { //esse metodo remove uma carta, caso o jogador tenha uma carta, e printa que ele devia morrer, caso tenha zero
		if(jogadores.get(indexAlvo).getCartasNaMao().size()==1) { //lembrando que a carta jah foi removida, soh falta atualizar a GUI
			jogadoresPanels[indexAlvo].remove(c2Label[indexAlvo]);
		} else if (jogadores.get(indexAlvo).getCartasNaMao().size()==1) {
			System.out.println("segundo a logica de removeCartaDoUltimoAlvo da classe TelaGame, esse jogador tah morto");
		}
		validate();
	}
	*/
}
