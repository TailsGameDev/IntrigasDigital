

public class Fluxo {
	Main main = new Main();
	
	public void instanciaTelaInit() {
		TelaInit telaInit = new TelaInit();
	}
	
	public void instanciaTelaGame() {
		TelaGame telaGame = new TelaGame();
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	
	
}
