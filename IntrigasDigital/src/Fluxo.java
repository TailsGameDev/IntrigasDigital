import java.security.SecureRandom;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Fluxo {
	
	boolean olhandoCartaDoBaralho = false;
	int umaDuvidaACadaQuantasJogadas = 6;
	int umMagnusACadaQuantosAtks = 1;
	
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
	
	public void chamaMetodoComAlvo(EnumTipoAcao funcao,Jogador solicitante, Jogador alvo) {//o comentario abaixo ta tipo muito questionavel
		//esse metodo eh chamado no comeco de um atk do Julius, no caso do Jogador0, mas isso pressupoe que ele nao vai fazer nada. Eh soh pra alterar o index na tela.
		switch(funcao) { //nesse caso mencionado acima, a funcao deve valer PERSONAGEM
			case ATAQUEINDEFENSAVEL:
				//System.out.println("entrou no switch");
				Main.controlJogador.ataqueIndefensavel(solicitante, alvo);
				break;
			case PERSONAGEM:
				if(Main.game.ultimoPersUsado == EnumPersonagem.JULIUS) {
					if(solicitante == Main.game.getJogadores().get(0)) { 
						boolean usouMagnus=false;
						//System.out.println("Sepah vai usar o magnus");
						usouMagnus = sePahUsaMagnus();//aqui eh verificada a vez de quem que eh e se pode usar. Por isso nao faz nada se nao deve fazer
						if( ! usouMagnus) {
							System.out.println("nao usou o magnus");
							veSeTemDuvidaEChamaAcaoPers(EnumPersonagem.JULIUS, solicitante);
						}
					} 
				} else if (Main.game.ultimoPersUsado==EnumPersonagem.NINETA){
					if(solicitante == Main.game.getJogadores().get(0)) {
						boolean usouPistone = false;
						usouPistone=sePahUsaPistone();
						if( ! usouPistone) {
							veSeTemDuvidaEChamaAcaoPers(EnumPersonagem.NINETA, solicitante);
						}
					}
				}
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
			
			Main.telaGame.renderizaConsole(" ");//partiu pular uma linha de vez em vez
			
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
	
	void coreBtnAcaoPersonagem(Jogador solicitante, EnumPersonagem p) {
		Main.telaGame.renderizaConsole(solicitante.getNome() + " declara ter e usarah x personagem " + p);
		Main.game.setJogadorDeQuemSeDuvida(solicitante);
		Main.game.setUltimoTipoAcao( EnumTipoAcao.PERSONAGEM );
		//EnumPersonagem pAntigo = Main.game.getUltimoPersUsado();
		Main.game.setUltimoPersUsado(p);
		//System.out.println("o personagem e o "+p);
		//System.out.println(Main.game.getUltimoPersUsado()+"eh o ultimo usado do game");
		if(p == EnumPersonagem.JULIUS || p == EnumPersonagem.NINETA) {
			Main.game.ultimoAtacando = Main.game.getJogadorDaVez();
			if(solicitante instanceof Bot) {
				Main.telaGame.indexAlvo = Main.controlJogador.calculaAlvoAleatorioCoerente();
				Main.telaGame.renderizaConsole("E o alvo serah "+Main.game.getJogadores().get(Main.telaGame.indexAlvo).getNome() );
				//Main.fluxo.chamaMetodoComAlvo(Main.game.getUltimoTipoAcao(), solicitante, Main.game.getJogadores().get(Main.telaGame.indexAlvo)); vai ter q continuar a exec a partir do proximoBtn
			} else {
				Main.telaGame.exibeAlvoBtns(); // a exe
				//System.out.println("imprimiu alvosbtn");
			}
		} else { //se nao eh o julius
			//note que se for um bot, Main.game.setUltimpTipoAcao fica como personagem. Isso eh usado no botao proximo da TelaGame
			//System.out.println(Main.game.getJogadorDaVez().getNome()+" se eh o zero vai ver se tem duvidas");
			boolean mOuP = p ==EnumPersonagem.MAGNUS || p==EnumPersonagem.PISTONE;
			if(Main.game.getJogadorDaVez()==Main.game.getJogadores().get(0) && p!= EnumPersonagem.MAGNUS) {
				veSeTemDuvidaEChamaAcaoPers(p, solicitante);
				System.out.println("Chamou rodada de duvidas");
			} else 
				//a intencao do if eh ser o caso especifico do jogador0 estar usando o magnus para se defender
				//ou o pistone
				if(Main.game.getJogadorDaVez()!=Main.game.getJogadores().get(0) && solicitante == Main.game.getJogadores().get(0) && mOuP) {
					veSeTemDuvidaEChamaAcaoPers(p, solicitante);
					System.out.println("Chamou rodada de duvidas");
				}
			
			
		}
		/*
		if(solicitante == Main.game.getJogadores().get(0)) { 
			boolean usouMagnus=false;
			usouMagnus = sePahUsaMagnus();
			if( ! usouMagnus) {
				veSeTemDuvidaEChamaAcaoPers(p);
			}
		}*/
	}
	
	//esse metodo vai ser usado pelo jogador e pelos bots para comecar a execucao de uma acao personagem.
	public void btnAcaoPersonagem(Jogador solicitante, EnumPersonagem p) {
		//System.out.println(Main.game.podeUsarPistone(solicitante, p));
		if(solicitante == Main.game.getJogadorDaVez()) { //if para quando nao tem defesa
			
			coreBtnAcaoPersonagem(solicitante, p);
		
		}	else if(
					//if para o MAGNUS obs: essa eh toda a implementacao referente ao magnus. A acao dele nao vai fazer nada. Soh vai trocar o Main.game.ultimoPersUsado
						Main.game.podeUsarMagnus(solicitante, p) ||
						Main.game.podeUsarPistone(solicitante, p)
					) {
			coreBtnAcaoPersonagem(solicitante, p);
		}
	}
	
	void veSeTemDuvidaEChamaAcaoPers(EnumPersonagem p, Jogador solicitante) {
		boolean alguemDuvidou = rodadaDeDuvidas();
		boolean ehBlefe = false;
		if(alguemDuvidou) {
			ehBlefe = Main.controlJogador.duvidar(Main.game);
		}
		Main.game.setUltimoTipoAcao(EnumTipoAcao.NAODUVIDAVEL);
		if( ! ehBlefe ) {//a acao personagem soh eh chamada se nao eh blefe ou se nao duvidaram, entao nao sera chamada caso o jogador tenha morrido por blefar
			p=Main.game.ultimoPersUsado;
			Main.controlJogador.acaoPersonagem(p, solicitante);
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
	
	public boolean sePahUsaMagnus() { //por algum motivo esse metodo nao eh chamado toda vez que deveria. Quando nao eh, ocorre duvida ou nao, e segue o jogo
		boolean usouMagnus = false;
		SecureRandom random = new SecureRandom();
		System.out.println("se Pah usa magnus chamada");
		for (int i = 1; i<Main.game.getJogadores().size(); i++) {//ve de todos os jogadores
			if(Main.game.podeUsarMagnus(Main.game.getJogadores().get(i), EnumPersonagem.MAGNUS)) { //se algum pode usar o MAGNUS
				System.out.println(Main.game.getJogadores().get(i).getNome() +" Pode usar o magnus");
				int chave = random.nextInt(umMagnusACadaQuantosAtks);// e sorteia se vai usar
				//chave=0;//testeeeeee
				if (chave == 0) {//o indexAlvo eh subintendido para um personagem que pode usar o MAGNUS
					btnAcaoPersonagem(Main.game.getJogadores().get(Main.telaGame.indexAlvo),EnumPersonagem.MAGNUS); //dae se pah usa
					usouMagnus = true; //e avisa
				}
			}
		}
		if( ! usouMagnus) {
			//System.out.println("em fluxo.sePahUsaMagnus, nao usaram o magnus");
		}
		return usouMagnus;
	}
	
	public boolean sePahUsaPistone() {
		//System.out.println("Se pah vai usar Pistone");
		boolean usouPistone = false;
		SecureRandom random = new SecureRandom();
		for (int i = 1; i<Main.game.getJogadores().size(); i++) {//ve de todos os jogadores
			//System.out.println("Pode usar pistone: "+Main.game.podeUsarPistone(Main.game.getJogadores().get(i), EnumPersonagem.PISTONE));
			if(Main.game.podeUsarPistone(Main.game.getJogadores().get(i), EnumPersonagem.PISTONE)) { //se algum pode usar o Pistone
				//System.out.println(Main.game.getJogadores().get(i).getNome() +" Pode usar o Pistone");
				int chave = random.nextInt(umMagnusACadaQuantosAtks);// e sorteia se vai usar
				if (chave == 0) {//o indexAlvo eh subintendido para um personagem que pode usar o Pistone
					btnAcaoPersonagem(Main.game.getJogadores().get(Main.telaGame.indexAlvo),EnumPersonagem.PISTONE); //dae se pah usa
					usouPistone = true; //e avisa
				}
			}
		}
		if( ! usouPistone) {
			//System.out.println("em fluxo.sePahUsaPistone, nao usaram o Pistone");
		}
		return usouPistone;
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
