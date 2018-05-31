import java.security.SecureRandom;
import java.util.ArrayList;

public class Baralho {
	ArrayList<Carta> cartas;

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
