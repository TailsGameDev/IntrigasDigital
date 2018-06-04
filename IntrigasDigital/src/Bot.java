import java.security.SecureRandom;

public class Bot extends Jogador {
	
	public Bot(String vnome) {
		super(vnome);
	}
	
	public String decideAcao() {
		
		SecureRandom random = new SecureRandom();
		
		int chave = random.nextInt(10);
		String acao = "pegar1Torrao";
		
		switch(chave) {
			default:
				break;
		}
		
		return acao;
	}
}
