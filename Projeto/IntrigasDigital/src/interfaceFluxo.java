import java.util.ArrayList;

import javax.swing.JTextField;

public interface interfaceFluxo {
	void ativaTelaInicial();
	void ativaTelaCadastro();
	void ativaTelaGame();
	void ativaTelaAcaoPersonagem();
	void ativaTelaAcaoCombinada();
	//void btnCadastroContinuar(ArrayList<JTextField> jogadoresTextFields); eh static na classe mesmo
	void btnBaralho(Jogador solicitante);
	void clickCartaJogadorEsq();
	void passaVez();
	void chamaMetodoComAlvo(EnumTipoAcao funcao, Jogador solicitante, Jogador alvo);
	void encerraGame();
	void btnAcaoPersonagem(Jogador solicitante, EnumPersonagem p);
	void veSeTemDuvidaEChamaAcaoPers(EnumPersonagem p, Jogador solicitante);
	boolean sePahUsaMagnus();
	void coreBtnAcaoPersonagem(Jogador solicitante, EnumPersonagem p);
}
