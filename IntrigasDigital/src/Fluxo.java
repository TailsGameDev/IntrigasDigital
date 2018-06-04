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
	//quando for apertado o botao de cadastrar
	public static void btnCadastroContinuar(ArrayList<JTextField> jogadoresTextFields) {
		
		for(JTextField txt : jogadoresTextFields) { //para cada texto
			Jogador j = new Jogador(txt.getText()); //cadastre um jogador com esse nome
			
			
			
			ArrayList<Carta> cards = new ArrayList<Carta>();
			//System.out.println(Main.game.getBaralho().getCartas().get(0));
			cards.add(Main.game.getBaralho().getCartas().get(0));//de para ele duas cartas
			Main.game.getBaralho().getCartas().remove(0);
			cards.add(Main.game.getBaralho().getCartas().get(0));
			Main.game.getBaralho().getCartas().remove(0);
			
			j.setCartasNaMao(cards);
			
			Main.game.jogadores.add(j);//adicione ele no game
		}
		
		Main.criaTelaGame(); //crie a tela do game
		
	}
	
	public void btnBaralho(Jogador solicitante) {
		if(solicitante == Main.game.getJogadorDaVez()) {
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
		if(Main.game.getJogadorDaVez()==Main.game.getJogadores().get(Main.game.getJogadores().size()-1)) { //se o da vez eh o ultimo da lista
			Main.game.setJogadorDaVez(Main.game.getJogadores().get(0)); //seta a vez pro jogador zero
		} else { //se nao
			int index = 0;
			for (int i=0; i< Main.game.getJogadores().size(); i++) {//descobre o index do jogadorDaVez
				if(Main.game.jogadorDaVez == Main.game.getJogadores().get(i)) {
					index=i;
				}
			}
			Main.game.jogadorDaVez = Main.game.getJogadores().get(index+1);//e atualiza o jogadorDaVez para o proximo na lista
		}
	}

	public boolean isOlhandoCartaDoBaralho() {
		return olhandoCartaDoBaralho;
	}

	public void setOlhandoCartaDoBaralho(boolean olhandoCartaDoBaralho) {
		this.olhandoCartaDoBaralho = olhandoCartaDoBaralho;
	}
	
	
	
}
