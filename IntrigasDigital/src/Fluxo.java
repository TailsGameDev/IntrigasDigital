import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Fluxo {
	
	boolean olhandoCartaDoBaralho = false;
	
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
			
			if(solicitante == Main.game.getJogadores().get(0)) {
				if(!olhandoCartaDoBaralho) {
					Main.telaGame.baralhoBtn.setIcon(Main.game.baralho.getCartas().get(0).getPeqIcon());
					olhandoCartaDoBaralho = true;
				} else {
					Main.telaGame.baralhoBtn.setIcon(Main.game.baralho.getVersinho());
					olhandoCartaDoBaralho = false;
					Main.game.baralho.embaralhar();
					passaVez();
				}
			} //else se for Bot tem que implementar
		}
	}
	
	public void clickCartaJogadorEsq() {
		if(olhandoCartaDoBaralho) { //essa booleana deve ser verdadeira soh na vez do jogador0
			Main.controlJogador.trocarCarta(Main.game.getJogadorDaVez(), Main.game.getJogadorDaVez().getCartasNaMao().get(0));
			olhandoCartaDoBaralho = false;
		}
	}
	
	public void clickCartaJogadorDir() {
		if(olhandoCartaDoBaralho) { //essa booleana deve ser verdadeira soh na vez do jogador0
			Main.controlJogador.trocarCarta(Main.game.getJogadorDaVez(), Main.game.getJogadorDaVez().getCartasNaMao().get(1));
			olhandoCartaDoBaralho = false;
		}
	}
	
	public void chamaMetodoComAlvo(String funcao,Jogador solicitante, Jogador alvo) {
		//System.out.println("entrou no fluxo "+funcao);
		switch(funcao) {
			case "atkIndefensavel":
				//System.out.println("entrou no switch");
				Main.controlJogador.ataqueIndefensavel(solicitante, alvo);
				break;
			default:
				break;
		}
	}
	
	public void passaVez() {
		int index=0;
		//passando a vez mesmo
		if(Main.game.getJogadorDaVez()==Main.game.getJogadores().get(Main.game.getJogadores().size()-1)) { //se o da vez eh o ultimo da lista
			Main.game.setJogadorDaVez(Main.game.getJogadores().get(0)); //seta a vez pro jogador zero
			//System.out.println("eh o ultimo da lista no passavez");
		} else { //se nao
			index = 0;
			for (int i=0; i< Main.game.getJogadores().size(); i++) {//descobre o index do jogadorDaVez
				if(Main.game.jogadorDaVez == Main.game.getJogadores().get(i)) {
					index=i;
				}
			}
			Main.game.jogadorDaVez = Main.game.getJogadores().get(index+1);//e atualiza o jogadorDaVez para o proximo na lista
			//System.out.println("jogadorDaVez atualizado para "+ Main.game.jogadorDaVez.getNome());
		}
		
		//se eh bot, poe pra fazer algo
		//System.out.println("pediu pra conferir se eh vez do bot "+index);
		//System.out.println((Main.game.getJogadorDaVez().getNome()));
		if(Main.game.getJogadorDaVez() instanceof Bot) {
			//System.out.println("vez do Bot");
			Bot bot = (Bot) Main.game.getJogadorDaVez();
			String acao = bot.decideAcao();
			Main.controlJogador.fazAcaoDoBot(index+1, acao); //note que eh index+1. index era o index do jogador atual antes de atualizar
		}
		
		
	}

	public boolean isOlhandoCartaDoBaralho() {
		return olhandoCartaDoBaralho;
	}

	public void setOlhandoCartaDoBaralho(boolean olhandoCartaDoBaralho) {
		this.olhandoCartaDoBaralho = olhandoCartaDoBaralho;
	}
	
	
	
}
