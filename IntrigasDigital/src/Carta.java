
public class Carta {
	EnumPersonagem personagem;
	String imgFile;
	
	public Carta(EnumPersonagem p){
		personagem = p;
		
		switch (p){
			case KANE:
				break;
			case MAGNUS:
				break;
			case LAURA:
				break;
			case NINETA:
				break;
			case JULIUS:
				break;
			case PISTONE:
				break;
			default:
				System.out.println("Nani?");
		}
	}
	
	public EnumPersonagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(EnumPersonagem personagem) {
		this.personagem = personagem;
	}
	
}
