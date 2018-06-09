
public class ControlAcaoPersonagem {
	
	//o jogador ganha 3 torroes
	public void acaoKane(ControlGame game) {
		game.jogadorDaVez.setTorroes(game.jogadorDaVez.getTorroes() + 3);
		Main.fluxo.passaVez();
	}
	
	//defende um ataque
	public void acaoMagnus() {
		
	}
	
	//troca de carta gratuitamente
	public void acaoLaura(ControlGame game) {
		game.jogadorDaVez.setTorroes(game.jogadorDaVez.getTorroes() + 2);
		if(game.jogadorDaVez instanceof Bot) {
			Main.controlJogador.fazAcaoDoBot(EnumTipoAcao.TROCARCARTA);
		} else {
			Main.fluxo.btnBaralho(game.getJogadorDaVez());
		}
	}
	
	//rouba 2 torroes de um jogador
	public void acaoNineta(ControlGame game, Jogador alvo) {
		if(alvo.getTorroes()>=2) {
			game.jogadorDaVez.setTorroes(game.jogadorDaVez.getTorroes()+2);
			alvo.setTorroes(alvo.getTorroes()-2);
		}
	}
	
	//ataca um jogador gastando 3 torroes
	public void acaoJulius(ControlGame game, Jogador alvo) {
		if(game.jogadorDaVez.getTorroes()>=3) {
			game.jogadorDaVez.setTorroes(game.jogadorDaVez.getTorroes()-3);
			alvo.perdeCartaAleatoria();
			game.sePerdeuTira(alvo);
		}
	}
	
	//olha uma carta de alguem
	public void acaoPistone() {
		
	}
}
