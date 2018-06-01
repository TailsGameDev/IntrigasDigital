
public class Carta {
	EnumPersonagem personagem;
	String imgFileGed, imgFilePeq;
	
	public Carta(EnumPersonagem p){
		personagem = p;
		
		switch (p){
			case KANE:
				imgFileGed = "playersKane.png";
				imgFilePeq = "miniKane.png";
				break;
			case MAGNUS:
				imgFileGed = "playersMagnus.png";
				imgFilePeq = "miniMagnus.png";
				break;
			case LAURA:
				imgFileGed = "playersLaura.png";
				imgFilePeq = "miniLaura.png";
				break;
			case NINETA:
				imgFileGed = "playersNineta.png";
				imgFilePeq = "miniNineta.png";
				break;
			case JULIUS:
				imgFileGed = "playersJulius.png";
				imgFilePeq = "miniJulius.png";
				break;
			case PISTONE:
				imgFileGed = "playersPistone.png";
				imgFilePeq = "miniPistone.png";
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

	public String getImgFileGed() {
		return imgFileGed;
	}

	public void setImgFileGed(String imgFileGed) {
		this.imgFileGed = imgFileGed;
	}

	public String getImgFilePeq() {
		return imgFilePeq;
	}

	public void setImgFilePeq(String imgFilePeq) {
		this.imgFilePeq = imgFilePeq;
	}
	
	
	
}
