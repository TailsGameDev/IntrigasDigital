
public class Main{
	
	public static ControlGame game;
	public static Fluxo fluxo;
	
	public static void main(String args[]) {
		Main.game = new ControlGame();
		Main.fluxo = new Fluxo();
		Main.fluxo.instanciaTelaInit();
	}
}
