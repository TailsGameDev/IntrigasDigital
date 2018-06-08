import java.security.SecureRandom;

public class Bot extends Jogador {
	
	public Bot(String vnome) {
		super(vnome);
	}
	
	public EnumTipoAcao decideAcao(int torroes) { //esse metodo eh chamado pelo metodo passaVez() da Fluxo
		
		SecureRandom random = new SecureRandom();
		
		if(torroes<0) torroes = 0; //certificando-me que nao tah bugado.
		
		int chave = random.nextInt(3);
		int torroesNecessarios = 0;
		EnumTipoAcao acao = EnumTipoAcao.PEGAR1TORRAO;
		
		switch(chave) {
		case 0:
			acao=EnumTipoAcao.PEGAR1TORRAO;
			break;
		case 1:
			acao = EnumTipoAcao.TROCARCARTA;
			torroesNecessarios = 2;
			break;
		case 2:
			acao = EnumTipoAcao.ATAQUEINDEFENSAVEL;
			torroesNecessarios = 7;
			default:
				break;
		}
		
		//se nao os torroes nao sao suficientes, chama esse metodo de novo ateh serem.
		if (torroes<torroesNecessarios) //mae, eu sei recursao o/
			acao = decideAcao(torroes);
		
		/*if(torroes>6) {
			acao=EnumTipoAcao.ATAQUEINDEFENSAVEL;
		}*/
		
		return acao;
	}
}
