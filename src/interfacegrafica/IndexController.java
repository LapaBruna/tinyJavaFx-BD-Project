package interfacegrafica;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class IndexController {
	@FXML
	private Button btnFuncionario;
	@FXML
	private Button btnPaciente;
	@FXML
	private Button btnMedico;
	@FXML
	private Label lblMensagem;
	private String mensagem = "Clicou";

	public void onBtnFuncionarioClick() throws Exception {
		System.out.println(mensagem);
		Parent p = FXMLLoader.load(getClass().getResource("/interfacegrafica/Funcionario.fxml"));
		Stage window = (Stage) btnFuncionario.getScene().getWindow();
		window.setScene(new Scene(p));
	}

	public void onBtnPacienteClick() throws Exception {
		System.out.println(mensagem);
		Parent p = FXMLLoader.load(getClass().getResource("/interfacegrafica/Paciente.fxml"));
		Stage window = (Stage) btnPaciente.getScene().getWindow();
		window.setScene(new Scene(p));
	}

	public void onBtnMedicoClick() throws Exception {
		System.out.println(mensagem);
		Parent p = FXMLLoader.load(getClass().getResource("/interfacegrafica/Medico.fxml"));
		Stage window = (Stage) btnMedico.getScene().getWindow();
		window.setScene(new Scene(p));
	}
}