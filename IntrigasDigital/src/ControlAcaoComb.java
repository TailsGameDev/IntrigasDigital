import java.util.ArrayList;

public class ControlAcaoComb {
	//ataca alguem gastando 3 torroes. Igual ao Julius
	public void acaoKM(ControlGame game, Jogador alvo) {
		ControlAcaoPersonagem cap = new ControlAcaoPersonagem();
		cap.acaoJulius(game, alvo);
	}
	
	//olha as duas cartas de alguem
	public void acaoML() {
		
	}
	
	//jogador ganha 5 torroes
	public void acaoLK(ControlGame game) {
		game.jogadorDaVez.setTorroes(game.jogadorDaVez.getTorroes() + 5);
	}
	
	//ataca o kane dizendo quem tem o kane
	public void acaoNJ(ControlGame game, Jogador alvo) {
		
		if(alvo.possuiPersonagem(EnumPersonagem.KANE)) { //se o alvo tem o kane
			
			ArrayList<Carta> cards = alvo.getCartasNaMao();//pega as cartas do alvo
			boolean jahPerdeu = false;
			
			for(Carta card : cards) {// e tira um kane.
				if(jahPerdeu==false && card.getPersonagem().equals(EnumPersonagem.KANE)) {
					cards.remove(card);
					jahPerdeu=true;
				}
			}
			
		}
	}
	
	//ataca dois jogadores gastando 4 torroes
	public void acaoJJ(ControlGame game, Jogador alvo1, Jogador alvo2) {
		ControlAcaoPersonagem cap = new ControlAcaoPersonagem();
		//o jogador perde 3 torroes com as acoes normais, entao 6 sefor duas vezes. Com a acao combinada tem que perder soh 4 entao
		game.jogadorDaVez.setTorroes(game.jogadorDaVez.getTorroes()+2); //soma dois antes das acoes
		cap.acaoJulius(game, alvo1);
		cap.acaoJulius(game, alvo2);
	}
	
	//prende duas cartas, gastando 3 torroes. Elas nao podem usar poderes ateh o alvo pagar 2 torroes ao banco
	public void acaoPP() {
		
	}
}
