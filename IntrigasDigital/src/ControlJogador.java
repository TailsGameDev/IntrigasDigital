import java.util.ArrayList;

public class ControlJogador {
	public void pegar1Torrao(Jogador jogadorParaVerificar) {
		if(jogadorParaVerificar == Main.game.getJogadorDaVez()) {
			int torroes = Main.game.getJogadorDaVez().getTorroes();
			torroes+=1;
			Main.game.getJogadorDaVez().setTorroes(torroes);
			Main.fluxo.passaVez();
		}
	}
	
	public void ataqueIndefensavel(Jogador solicitante, Jogador alvo) {
		Jogador j = Main.game.getJogadorDaVez();
		if(j.getTorroes()>=7 && solicitante ==j) {
			perdeTorroes(3,j);
			mataCartaEMostraAGUI(alvo);
			Main.fluxo.passaVez();
		}
	}
	
	//troca a carta zero do baralho com a carta c do jogador solicitante, embaralha, e reconfigura a GUI
	public void trocarCarta(Jogador solicitante, Carta c) {
		ArrayList<Carta> cartasDoJogador = solicitante.getCartasNaMao();
		cartasDoJogador.add(Main.game.getBaralho().getCartas().get(0));
		Main.game.getBaralho().getCartas().add(c);
		Main.game.getBaralho().getCartas().remove(0);
		cartasDoJogador.remove(c);
		Main.fluxo.setOlhandoCartaDoBaralho(false);
		Main.game.getBaralho().embaralhar();
		Main.telaGame.baralhoBtn.setIcon(Main.game.getBaralho().versinho);
		Main.telaGame.exibeLeftPanel();
		Main.telaGame.exibeRightPanel();
		Main.fluxo.passaVez();
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
	
	public void mataCartaEMostraAGUI(Jogador alvo) {
		Carta c = alvo.perdeCartaAleatoria();
		Main.telaGame.addCartaMorta(c);
		Main.game.sePerdeuTira(alvo);
		Main.telaGame.removeCartaDoUltimoAlvo();
	}
	
	void perdeTorroes(int qtdd, Jogador j) {
		j.setTorroes(j.getTorroes()-qtdd);
		Main.telaGame.exibeSeusTorroes();
	}
}
