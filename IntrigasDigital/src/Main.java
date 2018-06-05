
public class Main{
	
	public static ControlGame game = new ControlGame();
	public static Fluxo fluxo= new Fluxo();
	public static TelaInit telaInit = new TelaInit();
	public static ControlJogador controlJogador = new ControlJogador();
	
	public static TelaCadastroJogadores telaCadastroJogadores = new TelaCadastroJogadores();
	public static TelaGame telaGame;
	public static TelaAcaoPersonagem telaAcaoPersonagem = new TelaAcaoPersonagem();
	public static TelaAcaoCombinada telaAcaoCombinada = new TelaAcaoCombinada();
	public static TelaEnd telaEnd;
	
	public static void main(String args[]) {
		Main.fluxo.ativaTelaInit();
		telaCadastroJogadores.setVisible(false);
		telaAcaoPersonagem.setVisible(false);
		telaAcaoCombinada.setVisible(false);
	}
	
	public static void criaTelaGame() {	
		game.jogadorDaVez=game.getJogadores().get(0); //o jogo comeca com o player principal
		telaGame = new TelaGame();
	}
	
	public static void criaTelaEnd() {
		telaEnd = new TelaEnd();
	}
	
}
