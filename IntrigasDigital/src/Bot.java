import java.security.SecureRandom;

public class Bot extends Jogador {
	
	public Bot(String vnome) {
		super(vnome);
	}
	
	public EnumTipoAcao decideAcao() { //esse metodo eh chamado pelo metodo passaVez() da Fluxo
		
		SecureRandom random = new SecureRandom();
		
		if(torroes<0) torroes = 0; //certificando-me que nao tah bugado.
		
		int chave = random.nextInt(4);
		int torroesNecessarios = 0;
		EnumTipoAcao acao = EnumTipoAcao.PEGAR1TORRAO;
		//teste
		//chave=3;
		
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
			break;
		case 3:
			acao = EnumTipoAcao.PERSONAGEM;
			break;
		default:
				break;
		}
		
		//se nao os torroes nao sao suficientes, chama esse metodo de novo ateh serem.
		if (torroes<torroesNecessarios) //mae, eu sei recursao o/
			acao = decideAcao();
		
		/*if(torroes>6) {
			acao=EnumTipoAcao.ATAQUEINDEFENSAVEL;
		}*/
		
		return acao;
	}
	
	public EnumPersonagem decidePersonagem() {
		EnumPersonagem p = EnumPersonagem.KANE;
		SecureRandom random = new SecureRandom();
		int chave = random.nextInt(2);
		switch(chave) {
		case 0:
			break;
		case 1:
			p=EnumPersonagem.LAURA;
			break;
		default:
			System.out.println("caso default atingido no Bot.decidePersonagem");	
		}
		
		//teste
		//p = EnumPersonagem.LAURA;
		
		/*
		switch (chave) {
		case 0:
			break;
		case 1:
			p=EnumPersonagem.MAGNUS;
			break;
		case 2:
			p=EnumPersonagem.LAURA;
			break;
		case 3:
			p=EnumPersonagem.NINETA;
			break;
		case 4:
			p=EnumPersonagem.JULIUS;
			break;
		case 5:
			p=EnumPersonagem.PISTONE;
			break;
		default:
			System.out.println("caso default atingido no Bot.decidePersonagem");	
		} */
		return p;
	}
}
