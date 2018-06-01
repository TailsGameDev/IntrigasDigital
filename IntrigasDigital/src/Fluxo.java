import java.util.ArrayList;

import javax.swing.JTextField;

public class Fluxo {
	
	public void instanciaTelaInit() {
		TelaInit telaInit = new TelaInit();
	}
	
	public void instanciaTelaGame() {
		TelaGame telaGame = new TelaGame();
	}
	
	public void instanciaTelaCadastroJogadores() {
		TelaCadastroJogadores t = new TelaCadastroJogadores();
	}
	
	//quando for apertado o botao de cadastrar
	public static void btnCadastroContinuar(ArrayList<JTextField> jogadoresTextFields) {
		
		for(JTextField txt : jogadoresTextFields) { //para cada texto
			Jogador j = new Jogador(txt.getSelectedText()); //cadastre um jogador com esse nome
			
			ArrayList<Carta> cards = new ArrayList<Carta>();
			//System.out.println(Main.game.getBaralho().getCartas().get(0));
			cards.add(Main.game.getBaralho().getCartas().get(0));//de para ele duas cartas
			Main.game.getBaralho().getCartas().remove(0);
			cards.add(Main.game.getBaralho().getCartas().get(0));
			Main.game.getBaralho().getCartas().remove(0);
			
			j.setCartasNaMao(cards);
			
			Main.game.jogadores.add(j);//adicione ele no game
		}
		
		TelaGame telaGame = new TelaGame(); //crie a tela do game
		
	}
	
}
