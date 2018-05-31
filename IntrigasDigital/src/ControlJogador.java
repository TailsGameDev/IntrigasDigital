import java.util.ArrayList;

public class ControlJogador {
	public void pegar1Torrao(ControlGame game) {
		int torroes = game.getJogadorDaVez().getTorroes();
		torroes+=1;
		game.getJogadorDaVez().setTorroes(torroes);
	}
	
	public void ataqueIndefensavel(ControlGame game, Jogador alvo) {
		Jogador j = game.getJogadorDaVez();
		if(j.getTorroes()>=7) {
			j.setTorroes(j.getTorroes()-7);
			alvo.perdeCartaAleatoria();
			game.sePerdeuTira(alvo);
		}
	}
	
	public void duvidar(ControlGame game) {
		if(game.ultimoTipoAcao == EnumTipoAcao.PERSONAGEM) { //se a ultima acao foi acao personagem
			if(game.jogadorDeQuemSeDuvida.possuiPersonagem(game.ultimoPersUsado)) { //se o jogador que fez a acao tem o personagem
				game.jogadorDuvidando.perdeCartaAleatoria();
				game.sePerdeuTira(game.jogadorDuvidando);
			}
		} else { //se nao, entao foi uma acao combinada, ae tem que
			ArrayList<EnumPersonagem> comboPers = game.ultimaAcaoComb.getPersonagens(); //pegar os personagens da acao combinada
				if(game.jogadorDeQuemSeDuvida.possuiPersonagens(comboPers.get(0), comboPers.get(1))){ //ver se o alvo da duvida possui eles
					//caso possua, quem duvidou perde carta
					game.jogadorDuvidando.perdeCartaAleatoria();
					game.sePerdeuTira(game.jogadorDuvidando);
				} else {
					//caso nao possua, ele perde carta
					game.jogadorDeQuemSeDuvida.perdeCartaAleatoria();
					game.sePerdeuTira(game.jogadorDeQuemSeDuvida);
				}
		}
	}
}
