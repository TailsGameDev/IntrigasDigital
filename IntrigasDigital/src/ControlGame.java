import java.util.ArrayList;

public class ControlGame {
	
	int numInicialJogadores = 3;
	ArrayList<Jogador> jogadores = new ArrayList<Jogador>(); 
	Jogador jogadorDaVez, jogadorDuvidando, jogadorDeQuemSeDuvida, jogadorVencedor;
	EnumPersonagem ultimoPersUsado;
	ClassAcaoComb ultimaAcaoComb;
	EnumTipoAcao ultimoTipoAcao;
	Baralho baralho = new Baralho();
	
	public boolean checarFimDeJogo() {
		boolean sohTemUmPlayer = false;
		if(jogadores.size() == 1) {
			sohTemUmPlayer = true;
		}
		return sohTemUmPlayer; //acaba quando soh tem um player na mesa
	}
	
	public void sePerdeuTira(Jogador j) {
		if(j.getCartasNaMao().size() == 0) {
			jogadores.remove(j);
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
