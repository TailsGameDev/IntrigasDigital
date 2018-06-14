
public class ControlAcaoPersonagem {
	
	//o jogador ganha 3 torroes
	public void acaoKane(ControlGame game) {
		game.jogadorDaVez.setTorroes(game.jogadorDaVez.getTorroes() + 3);
		if(game.getJogadorDaVez()==Main.game.getJogadores().get(0))
			Main.fluxo.passaVez();
	}
	
	//defende um ataque
	public void acaoMagnus() {
		//essa acao nao faz nada aqui. O Poder do Magnus estah implementado na Fluxo.btnAcaoPersonagem
		Main.fluxo.passaVez();
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
	public void acaoNineta() {
		Jogador alvo = Main.game.getJogadores().get(Main.telaGame.indexAlvo);
		ControlGame game = Main.game;
		if(alvo.getTorroes()>=2) {
			game.jogadorDaVez.setTorroes(game.jogadorDaVez.getTorroes()+2);
			alvo.setTorroes(alvo.getTorroes()-2);
			Main.fluxo.passaVez();
		}
	}
	
	//ataca um jogador gastando 3 torroes
	public void acaoJulius() {
		if(Main.game.jogadorDaVez.getTorroes()>=3) {
			Main.game.jogadorDaVez.setTorroes(Main.game.jogadorDaVez.getTorroes()-3+7);
			Main.game.ultimoTipoAcao = EnumTipoAcao.ATAQUEINDEFENSAVEL;
			Main.controlJogador.naVddNaoEhUmIdefensavel = true;
			try {
			Main.fluxo.chamaMetodoComAlvo(EnumTipoAcao.ATAQUEINDEFENSAVEL, Main.game.getJogadorDaVez(), Main.game.getJogadores().get(Main.telaGame.indexAlvo));
			} catch (IndexOutOfBoundsException e) {
				System.out.println("nao deu de chamar o metodoComAlvo na controlAcaoPers.acaoJulius");
			}
			Main.controlJogador.naVddNaoEhUmIdefensavel = false;
		}
	}
	
	//olha uma carta de alguem
	public void acaoPistone(Jogador solicitante) {
		//System.out.println("Chamou acaoPistone! Solicitante: "+solicitante.getNome()+" jogadorDaVez: "+Main.game.getJogadorDaVez().getNome());
		
		if(Main.game.getJogadorDaVez()==solicitante) {
			if(solicitante instanceof Bot) {
				if(solicitante.getTorroes()>=2) {
					System.out.println("Um bot teria visto uma carta do outro jogador, se isso fizesse algum sentido");
				}
			} else {
				if(solicitante.getTorroes()>=2) {
					Main.telaGame.exibeCartaDoJogador();
					Main.game.ultimoTipoAcao = EnumTipoAcao.NAODUVIDAVEL;
					Main.controlJogador.perdeTorroes(2, solicitante);
				}
			}
		} else {
			//estah sendo usado como defesa contra roubo
			Main.fluxo.passaVez();
		}
	}
}
