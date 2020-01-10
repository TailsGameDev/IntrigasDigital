import java.security.SecureRandom;
import java.util.ArrayList;

public class Jogador {
	String nome;
	ArrayList<Carta> cartasNaMao;
	int torroes;
	
	public Jogador(String vnome) {
		nome=vnome;
		torroes = 0;
	}
	
	public Carta perdeCartaAleatoria() {

		SecureRandom random = new SecureRandom();
		int aleat = random.nextInt(cartasNaMao.size());
		Carta perdida=cartasNaMao.get(aleat);
		cartasNaMao.remove(aleat);
		
		return perdida;
	}
	
	//esse metodo verifica se o jogador possui uma carta do personagem p
	public boolean possuiPersonagem(EnumPersonagem p) {
		boolean possui = false;
		for(Carta c : cartasNaMao) {
			if (c.getPersonagem() == p) {
				possui = true;
			}
		}
		return possui;
	}
	
	//esse metodo verifica se o jogador possui ambos os personagens p1 e p2
	public boolean possuiPersonagens(EnumPersonagem p1,EnumPersonagem p2) {
		
		boolean resposta = false; //parte do pressuposto que nao possui
		
		if(possuiPersonagem(p1) && possuiPersonagem(p2)) { //se ele possui os 2, resta verificar se
			if(p1.equals(p2)) { //p1 eh igual a p2. Porque se for
				int possuiX = 0;
				for(Carta c : cartasNaMao) {
					if (c.getPersonagem().equals(p1)) {
						possuiX += 1;
					}
				}
				if(possuiX==2) { //ele tem que possuir duas cartas do personagem X
					resposta=true;
				}
			} else { //caso sejam diferentes, eh porque ele tem duas cartas, com os dois personagens.
				resposta = true;
			}
		}
		
		return resposta;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ArrayList<Carta> getCartasNaMao() {
		return cartasNaMao;
	}
	public void setCartasNaMao(ArrayList<Carta> cartasNaMao) {
		this.cartasNaMao = cartasNaMao;
	}
	public int getTorroes() {
		return torroes;
	}
	public void setTorroes(int torroes) {
		this.torroes = torroes;
	}

}
