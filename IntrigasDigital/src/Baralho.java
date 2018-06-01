import java.security.SecureRandom;
import java.util.ArrayList;

public class Baralho {
	ArrayList<Carta> cartas;
	
	public Baralho() {
		ArrayList<Carta> cards = new ArrayList<Carta>();
		for(int i =0; i<3 ; i++) {
			cards.add(new Carta(EnumPersonagem.KANE));
			cards.add(new Carta(EnumPersonagem.JULIUS));
			cards.add(new Carta(EnumPersonagem.LAURA));
			cards.add(new Carta(EnumPersonagem.MAGNUS));
			cards.add(new Carta(EnumPersonagem.NINETA));
			cards.add(new Carta(EnumPersonagem.PISTONE));
		}
		cartas = cards;
		embaralhar();
	}
	
	public ArrayList<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(ArrayList<Carta> cartas) {
		this.cartas = cartas;
	}
	
	public void embaralhar() {
		ArrayList<Carta> embaralhadas = new ArrayList<Carta>();
		SecureRandom random = new SecureRandom();
		
		for (int i=0; i<cartas.size(); i++) {
			int aleat = random.nextInt(cartas.size());
			embaralhadas.add(cartas.get(aleat));
			cartas.remove(aleat);
		}
		
		setCartas(embaralhadas);
	}
}
