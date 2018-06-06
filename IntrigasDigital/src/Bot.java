import java.security.SecureRandom;

public class Bot extends Jogador {
	
	public Bot(String vnome) {
		super(vnome);
	}
	
	public EnumTipoAcao decideAcao() { //esse metodo eh chamado pelo metodo passaVez() da Fluxo
		
		SecureRandom random = new SecureRandom();
		
		int chave = random.nextInt(2);
		EnumTipoAcao acao = EnumTipoAcao.PEGAR1TORRAO;
		
		switch(chave) {
		case 0:
			acao=EnumTipoAcao.PEGAR1TORRAO;
			break;
			default:
				break;
		}
		
		if(torroes>6) {
			acao=EnumTipoAcao.ATAQUEINDEFENSAVEL;
		}
		
		return acao;
	}
}
