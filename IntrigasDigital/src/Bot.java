import java.security.SecureRandom;

public class Bot extends Jogador {
	
	public Bot(String vnome) {
		super(vnome);
	}
	
	public String decideAcao() { //esse metodo eh chamado pelo metodo passaVez() da Fluxo
		
		SecureRandom random = new SecureRandom();
		
		int chave = random.nextInt(2);
		String acao = "pegar1Torrao";
		
		switch(chave) {
			default:
				break;
		}
		
		if(torroes>6) {
			acao="ataqueIndefensavel";
		}
		
		return acao;
	}
}
