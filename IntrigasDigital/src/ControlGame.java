import java.util.ArrayList;

public class ControlGame {
	
	int numInicialJogadores = 3;
	ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	ArrayList<Jogador> ultimosAlvos = new ArrayList<Jogador>(); 
	Jogador jogadorDaVez, jogadorDuvidando, jogadorDeQuemSeDuvida, jogadorVencedor;
	EnumPersonagem ultimoPersUsado;
	ClassAcaoComb ultimaAcaoComb;
	EnumTipoAcao ultimoTipoAcao;
	Baralho baralho = new Baralho();
	int indexDoUltimoMorto;
	Jogador ultimoJogadorMorto;
	
	public boolean dizSeOJogoAcabou() {
		boolean sohTemUmPlayer = false;
		boolean tem2MasOZeroPerdeu = false;
		boolean oJogoAcabou = false;
		if(jogadores.size() == 1) {
			sohTemUmPlayer = true;
		}
		
		if(jogadores.size()==2) {
			if(jogadores.get(0).getCartasNaMao().size()==0) {
				tem2MasOZeroPerdeu = true;
			}
		}
		
		oJogoAcabou = sohTemUmPlayer || tem2MasOZeroPerdeu;
		
		return oJogoAcabou; //acaba quando soh tem um player na mesa
	}
	
	public void calculaVencedor() {
		if(jogadores.size() == 1) {
			jogadorVencedor = jogadores.get(0);
		}
		
		if(jogadores.size()==2) {
			if(jogadores.get(0).getCartasNaMao().size()==0) {
				jogadorVencedor = jogadores.get(1);
			}
		}
	}
	
	public void sePerdeuTira(Jogador j) { //nao tira se eh o zero porque em todo o programa, o jogador nao Bot eh o zero.
		if(j.getCartasNaMao().size() == 0 && j!=jogadores.get(0)) {
			indexDoUltimoMorto = Main.descobreIndexDoJogadorJ(j);
			ultimoJogadorMorto = j;
			jogadores.remove(j);
			Main.telaGame.renderizaTopPanel();
		}
	}

	
	
	
	//getters and setters
	
	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}

	public int getNumInicialJogadores() {
		return numInicialJogadores;
	}

	public void setNumInicialJogadores(int numInicialJogadores) {
		this.numInicialJogadores = numInicialJogadores;
	}

	public void setJogadores(ArrayList<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public Jogador getJogadorDaVez() {
		return jogadorDaVez;
	}

	public void setJogadorDaVez(Jogador jogadorDaVez) {
		this.jogadorDaVez = jogadorDaVez;
	}

	public Jogador getJogadorDuvidando() {
		return jogadorDuvidando;
	}

	public void setJogadorDuvidando(Jogador jogadorDuvidando) {
		this.jogadorDuvidando = jogadorDuvidando;
	}

	public Jogador getJogadorDeQuemSeDuvida() {
		return jogadorDeQuemSeDuvida;
	}

	public void setJogadorDeQuemSeDuvida(Jogador jogadorDeQuemSeDuvida) {
		this.jogadorDeQuemSeDuvida = jogadorDeQuemSeDuvida;
	}

	public EnumPersonagem getUltimoPersUsado() {
		return ultimoPersUsado;
	}

	public void setUltimoPersUsado(EnumPersonagem ultimoPersUsado) {
		this.ultimoPersUsado = ultimoPersUsado;
	}

	public ClassAcaoComb getUltimaAcaoComb() {
		return ultimaAcaoComb;
	}

	public void setUltimaAcaoComb(ClassAcaoComb ultimaAcaoComb) {
		this.ultimaAcaoComb = ultimaAcaoComb;
	}

	public Baralho getBaralho() {
		return baralho;
	}

	public void setBaralho(Baralho baralho) {
		this.baralho = baralho;
	}

	public EnumTipoAcao getUltimoTipoAcao() {
		return ultimoTipoAcao;
	}

	public void setUltimoTipoAcao(EnumTipoAcao ultimoTipoAcao) {
		this.ultimoTipoAcao = ultimoTipoAcao;
	}

	public Jogador getJogadorVencedor() {
		return jogadorVencedor;
	}

	public void setJogadorVencedor(Jogador jogadorVencedor) {
		this.jogadorVencedor = jogadorVencedor;
	}
	
	
}
