import java.security.SecureRandom;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Fluxo {
	
	boolean olhandoCartaDoBaralho = false;
	int umaDuvidaACadaQuantasJogadas = 12;
	
	public void ativaTelaInit() {
		Main.telaInit.setVisible(true);;
	}
	
	public void ativaTelaCadastro() {
		Main.telaCadastroJogadores.setVisible(true);
	}
	
	public void ativaTelaGame() {
		Main.telaGame.setVisible(true);
	}

	
	public void ativaTelaAcaoPersonagem() {
		Main.telaAcaoPersonagem.setVisible(true);
	}
	
	public void ativaTelaAcaoCombinada() {
		Main.telaAcaoCombinada.setVisible(true);
	}
	
	
	//quando for apertado o botao de cadastrar tem que cadastrar o jogador e os Bots
	public static void btnCadastroContinuar(ArrayList<JTextField> jogadoresTextFields) {
		
		Jogador j = new Jogador(jogadoresTextFields.get(0).getText()); //cadastre um jogador com esse nome
		
		ArrayList<Carta> cards = new ArrayList<Carta>();
		//System.out.println(Main.game.getBaralho().getCartas().get(0));
		cards.add(Main.game.getBaralho().getCartas().get(0));//de para ele duas cartas
		Main.game.getBaralho().getCartas().remove(0);
		cards.add(Main.game.getBaralho().getCartas().get(0));
		Main.game.getBaralho().getCartas().remove(0);
		
		j.setCartasNaMao(cards);
		
		Main.game.jogadores.add(j);//adicione ele no game
		
		for(int i=1; i<jogadoresTextFields.size();i++) {//para cada Bot
			Bot b = new Bot(jogadoresTextFields.get(i).getText()); //cadastre um jogador com esse nome	
			
			cards = new ArrayList<Carta>();
			//System.out.println(Main.game.getBaralho().getCartas().get(0));
			cards.add(Main.game.getBaralho().getCartas().get(0));//de para ele duas cartas
			Main.game.getBaralho().getCartas().remove(0);
			cards.add(Main.game.getBaralho().getCartas().get(0));
			Main.game.getBaralho().getCartas().remove(0);
			
			b.setCartasNaMao(cards);
			
			Main.game.jogadores.add(b);//adicione ele no game
		}
		
		Main.criaTelaGame(); //crie a tela do game
		
	}
	
	public void btnBaralho(Jogador solicitante) {
		if(solicitante == Main.game.getJogadorDaVez()) {
			
			if(!olhandoCartaDoBaralho) Main.controlJogador.perdeTorroes(2, solicitante);
			//System.out.println(Main.game.getJogadorDaVez().getNome());}
			if(solicitante == Main.game.getJogadores().get(0)) { //se eh o jogador zero
				if(!olhandoCartaDoBaralho) {
					//System.out.println(Main.game.getJogadorDaVez().getNome() + "nao estava olhandoCartaDoBaralho");
					Main.telaGame.baralhoBtn.setIcon(Main.game.baralho.getCartas().get(0).getPeqIcon());
					olhandoCartaDoBaralho = true;
					Main.telaGame.renderizaConsole(Main.game.getJogadores().get(0).getNome() + " trocarah ou nao de carta");
				} else {
					Main.telaGame.baralhoBtn.setIcon(Main.game.baralho.getVersinho());
					olhandoCartaDoBaralho = false;
					Main.game.baralho.embaralhar();
					passaVez();
				}
			} else { // se eh bot
				if(!olhandoCartaDoBaralho) {
					Main.telaGame.renderizaConsole(solicitante.getNome() + " trocarah ou nao de carta");
					olhandoCartaDoBaralho = true;
				} else {
					olhandoCartaDoBaralho = false;
					Main.game.baralho.embaralhar();
				}
			}
		}
	}
	
	public void clickCartaJogadorEsq() {
		if(olhandoCartaDoBaralho) { //essa booleana deve ser verdadeira soh na vez do jogador0
			Main.controlJogador.trocarCarta(Main.game.getJogadorDaVez(), Main.game.getJogadorDaVez().getCartasNaMao().get(0));
			olhandoCartaDoBaralho = false;
		}
	}
	
	public void clickCartaJogadorDir() {
		if(olhandoCartaDoBaralho && Main.game.getJogadorDaVez().getCartasNaMao().size()>1) { //essa booleana deve ser verdadeira soh na vez do jogador0.Nas outras muda mt rapido
			Main.controlJogador.trocarCarta(Main.game.getJogadorDaVez(), Main.game.getJogadorDaVez().getCartasNaMao().get(1));
			olhandoCartaDoBaralho = false;
		}
	}
	
	public void chamaMetodoComAlvo(EnumTipoAcao funcao,Jogador solicitante, Jogador alvo) {
		
		switch(funcao) {
			case ATAQUEINDEFENSAVEL:
				//System.out.println("entrou no switch");
				Main.controlJogador.ataqueIndefensavel(solicitante, alvo);
				break;
			default:
				break;
		}
		
	}
	
	public void passaVez() {
		int index = Main.descobreIndexDoJogadorJ(Main.game.getJogadorDaVez());
		//passando a vez mesmo
		if(Main.game.dizSeOJogoAcabou() == false) {
			
			if(Main.game.getJogadorDaVez() == Main.game.ultimoJogadorMorto) //se o jogadorDaVez morreu, o index dele vem como zero. Dae tem que atualizar
				index = Main.game.indexDoUltimoMorto -1;
			
			if(Main.game.getJogadores().get(index)==Main.game.getJogadores().get(Main.game.getJogadores().size()-1)) { //se o da vez eh o ultimo da lista
				//vai depender se o zero jah perdeu ou nao, mas a ideia eh ir pro comeco da lista
				Jogador j = Main.game.getJogadores().get(0).getCartasNaMao().size()==0 ? Main.game.getJogadores().get(1) : Main.game.getJogadores().get(0);
				Main.game.setJogadorDaVez(j); //seta a vez pro primeiro jogador
			} else { //se nao  
				//System.out.println("Passou a vez de "+Main.game.getJogadores().get(index).getNome()+" para "+Main.game.getJogadores().get(index+1).getNome());
				Main.game.jogadorDaVez = Main.game.getJogadores().get(index+1);//e atualiza o jogadorDaVez para o proximo na lista
			}
			
			//se eh bot, poe pra fazer algo
			if(Main.game.getJogadorDaVez() instanceof Bot) {
				Bot bot = (Bot) Main.game.getJogadorDaVez();
				EnumTipoAcao acao = bot.decideAcao();
				if (acao == EnumTipoAcao.PERSONAGEM) Main.game.setUltimoPersUsado(bot.decidePersonagem());
				//System.out.println(acao);
				Main.game.setUltimoTipoAcao(acao);
				Main.controlJogador.fazAcaoDoBot(acao); //note que eh index+1. index era o index do jogador atual antes de atualizar
			}
		
		} else {
			encerraGame();
		}
			
	}
	
	
	//esse metodo vai ser usado pelo jogador e pelos bots para comecar a execucao de uma acao personagem.
	public void btnAcaoPersonagem(Jogador solicitante, EnumPersonagem p) {
		if(solicitante == Main.game.getJogadorDaVez()) {
			Main.telaGame.renderizaConsole(solicitante.getNome() + " declara ter e usarah x personagem " + p);
			Main.game.setJogadorDeQuemSeDuvida(solicitante);
			Main.game.setUltimoTipoAcao( EnumTipoAcao.PERSONAGEM );
			Main.game.setUltimoPersUsado(p);
			
			//note que se for um bot, Main.game.setUltimpTipoAcao fica como personagem. Isso eh usado no botao proximo da TelaGame
			if(solicitante == Main.game.getJogadores().get(0)) { 
				veSeTemDuvidaEChamaAcaoPers(p);
			}
		}/*	else if(	(Main.game.getUltimoPersUsado() == EnumPersonagem.JULIUS ||
					/*	Main.game.getUltimaAcaoComb() == EnumAcaoComb.JJ ||    //precisa implementar sistema de 2 alvos
						Main.game.getUltimaAcaoComb() == EnumAcaoComb.KM ||
						Main.game.getUltimaAcaoComb() == EnumAcaoComb.NJ ) && */
					
	}
	
	void veSeTemDuvidaEChamaAcaoPers(EnumPersonagem p) {
		boolean alguemDuvidou = rodadaDeDuvidas();
		boolean ehBlefe = false;
		if(alguemDuvidou) {
			ehBlefe = Main.controlJogador.duvidar(Main.game);
		}
		Main.game.setUltimoTipoAcao(EnumTipoAcao.NAODUVIDAVEL);
		if( ! ehBlefe ) {//a acao personagem soh eh chamada se nao eh blefe ou se nao duvidaram, entao nao sera chamada caso o jogador tenha morrido por blefar
			p=Main.game.ultimoPersUsado;
			Main.controlJogador.acaoPersonagem(p);
		} else {
			passaVez();
		}
		Main.telaGame.renderizaQuaseTudo();
	}
	
	boolean rodadaDeDuvidas() { //percorre os bots, gerando um i aleatorio. Se i==0, ele vira o jogadorDuvidando
		SecureRandom random = new SecureRandom();
		boolean alguemDuvidou = false;
		for (int k=1; k<Main.game.getJogadores().size(); k++) {
			if(Main.game.getJogadores().get(k) != Main.game.getJogadorDaVez()){
				int i = random.nextInt(umaDuvidaACadaQuantasJogadas);
				if(i==0) {
					Main.game.setJogadorDuvidando(Main.game.getJogadores().get(k));
					alguemDuvidou = true;
				}
			}
		}
		return alguemDuvidou;
	}
	
	void encerraGame() {
		Main.game.calculaVencedor();
		Main.telaGame.setVisible(false);
		Main.criaTelaEnd();
	}
	
	public boolean isOlhandoCartaDoBaralh() {
		return olhandoCartaDoBaralho;
	}

	public void setOlhandoCartaDoBaralh(boolean olhandoCartaDoBaralho) {
		this.olhandoCartaDoBaralho = olhandoCartaDoBaralho;
	}
	
	
	
}
