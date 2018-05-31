import java.util.ArrayList;

public class ClassAcaoComb {
	EnumAcaoComb acaoComb;
	
	public ArrayList<EnumPersonagem> getPersonagens(){
		ArrayList<EnumPersonagem> personagens = new ArrayList<EnumPersonagem>();		
		switch(acaoComb) {
		case KM:
			personagens.add(EnumPersonagem.KANE);
			personagens.add(EnumPersonagem.MAGNUS);
			break;
		case ML:
			personagens.add(EnumPersonagem.MAGNUS);
			personagens.add(EnumPersonagem.LAURA);
			break;
		case LK:
			personagens.add(EnumPersonagem.LAURA);
			personagens.add(EnumPersonagem.KANE);
			break;
		case NJ:
			personagens.add(EnumPersonagem.NINETA);
			personagens.add(EnumPersonagem.JULIUS);
			break;
		case JJ:
			personagens.add(EnumPersonagem.JULIUS);
			personagens.add(EnumPersonagem.JULIUS);
			break;
		case PP:
			personagens.add(EnumPersonagem.PISTONE);
			personagens.add(EnumPersonagem.PISTONE);
			break;
		default:
			System.out.println("Caso Default Atingido na classe ClassAcaoComb, metodo getPersonagens.");
			break;
		}
		
		return personagens;
		
	}
	
	public EnumAcaoComb getAcaoComb() {
		return acaoComb;
	}

	public void setAcaoComb(EnumAcaoComb acaoComb) {
		this.acaoComb = acaoComb;
	}
	
	
}
