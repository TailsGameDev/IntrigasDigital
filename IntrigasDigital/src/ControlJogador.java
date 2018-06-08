import java.security.SecureRandom;
import java.util.ArrayList;

public class ControlJogador {
	
	public void pegar1Torrao(Jogador jogadorParaVerificar) {
		if(jogadorParaVerificar == Main.game.getJogadorDaVez()) {
			//System.out.println("Eh o jogador da vez");
			int torroes = Main.game.getJogadorDaVez().getTorroes();
			torroes+=1;
			Main.game.getJogadorDaVez().setTorroes(torroes);
			Main.telaGame.renderizaConsole(jogadorParaVerificar.getNome()+" pegou um torrao de acucar");
//			System.out.println("alguem pegou um torrao");
			if(Main.game.getJogadorDaVez()==Main.game.getJogadores().get(0)) //se nao for o 0 ele tem que clicar em proximo pra passar a vez
				Main.fluxo.passaVez();
		}
		Main.telaGame.renderizaTopPanel();
	}
	
	public void ataqueIndefensavel(Jogador solicitante, Jogador alvo) {
		Jogador j = Main.game.getJogadorDaVez();
		if(j.getTorroes()>=7 && solicitante ==j) {
			perdeTorroes(7,j);
			mataCartaEMostraAGUI(alvo);
			Main.telaGame.renderizaConsole(solicitante.getNome()+" mandou um Ataque Indefensavel em "+alvo.getNome()+"!");
			if(Main.game.getJogadorDaVez()==Main.game.getJogadores().get(0)) //se nao for o 0 ele tem que clicar em proximo pra passar a vez 
				Main.fluxo.passaVez();
		}
	}
	
	//troca a carta zero do baralho com a carta c do jogador solicitante, embaralha, e reconfigura a GUI
	public void trocarCarta(Jogador solicitante, Carta c) {
		ArrayList<Carta> cartasDoJogador = solicitante.getCartasNaMao(); //cria variavel contrndo as cartasDoJogador
		cartasDoJogador.add(Main.game.getBaralho().getCartas().get(0)); //adiciona a carta 0 do baralho aa mao dele
		Main.game.getBaralho().getCartas().add(c); //adiciona a carta c ao baralho
		Main.game.getBaralho().getCartas().remove(0); //remove a carta 0 do baralho
		cartasDoJogador.remove(c); //remove a carta c do jogador
		Main.fluxo.setOlhandoCartaDoBaralho(false); //seta o estado do jogo para nao estah olhando carta do baralho
		Main.game.getBaralho().embaralhar(); //embaralha
		Main.telaGame.baralhoBtn.setIcon(Main.game.getBaralho().versinho); //faz o baralho ter o icone do versinho
		Main.telaGame.exibeLeftPanel(); //carrega os paineis com as cartas do jogador
		Main.telaGame.exibeRightPanel();
		if(Main.game.getJogadorDaVez()==Main.game.getJogadores().get(0)) //se o jogador da vez eh o zero, passa a vez.
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
	
	public void fazAcaoDoBot(int index, EnumTipoAcao acao) { //index do bot na lista Main.game.jogadores
		SecureRandom random = new SecureRandom();
		//System.out.println("facAcao do controlJogador chamada com acao "+acao);
		
		//calculando alvo aleatorio caso precise
		int i = random.nextInt(Main.game.getJogadores().size());
		int k=0;
		
		//ele vai tentar ateh 100 vezes escolher alguem que nao seja ele mesmo e nem o jogador0 sem carta (ou seja, se ele jah tiver morrido)
		while( ( i==index || (i==0 && Main.game.getJogadores().get(0).getCartasNaMao().size()==0) ) && k<100) {//isso pq nao tiro ele qd ele morre
			k++; i=random.nextInt(Main.game.getJogadores().size());
		}
		Jogador alvo = Main.game.getJogadores().get(i);
		
		switch(acao){
			case PEGAR1TORRAO:
				//System.out.println("entrou no switch com index "+index);
				pegar1Torrao(Main.game.getJogadores().get(index));
				break;
			case ATAQUEINDEFENSAVEL:
				Main.fluxo.chamaMetodoComAlvo(EnumTipoAcao.ATAQUEINDEFENSAVEL, Main.game.jogadorDaVez, alvo);
				break;
			case TROCARCARTA:
				Main.fluxo.btnBaralho(Main.game.getJogadores().get(index)); //simula o bot clicando no btn baralho
				int rand = random.nextInt(2); //sorteia entre trocar a carta esquerda ou a direita
				//decide entre trocar ou nao de carta. Sempre troca a da esquerda para nao dar out of bounds e lembrando que IA nao eh o foco
				if (rand==0) { Main.fluxo.clickCartaJogadorEsq(); } else { Main.fluxo.btnBaralho(Main.game.getJogadores().get(index)); }
				break;
			default: System.out.println("caso default atingido no Main.fazAcaoDoBot");
		}
	}
	
	public void mataCartaEMostraAGUI(Jogador alvo) {
		Carta c = alvo.perdeCartaAleatoria();
		Main.telaGame.addCartaMorta(c);
		if(alvo instanceof Bot) {
		Main.telaGame.renderizaTopPanel();
			//Main.telaGame.removeCartaDoUltimoAlvoBot();
		} else {
			Main.telaGame.exibeRightPanel();
			Main.telaGame.exibeLeftPanel();
		}
		Main.game.sePerdeuTira(alvo);
	}
	
	void perdeTorroes(int qtdd, Jogador j) {
		j.setTorroes(j.getTorroes()-qtdd);
		Main.telaGame.exibeSeusTorroes();
	}
}
