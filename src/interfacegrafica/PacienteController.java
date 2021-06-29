package interfacegrafica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PacienteController {
	@FXML
	private TextField txtCpfPac;
	@FXML
	private TextField txtNomePac;
	@FXML
	private TextField txtNascimentoPac;
	@FXML
	private TextField txtEnderecoPac;
	@FXML
	private TextField txtEmailPac;
	@FXML
	private TextField txtFonePac;
	@FXML
	private Button btnIncluir;
	@FXML
	private Button btnConsultar;
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnAlterar;
	@FXML
	private Button btnConfirmar;
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnVoltar;
	@FXML
	private Label lblMensagem;

	public static Connection abreBanco() {
		final String BANCO = "jdbc:mysql://localhost:3306/cacupe";

		try {
			return DriverManager.getConnection(BANCO, "root", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private int cpfPac;
	private String nomePac;
	private String nasciPac;
	private String enderecoPac;
	private String emailPac;
	private String fonePac;
	private String evento = "";

	public void onBtnIncluirClick() {
		evento = "incluir";

		txtCpfPac.setDisable(false);
		txtNomePac.setDisable(false);
		txtNascimentoPac.setDisable(false);
		txtEnderecoPac.setDisable(false);
		txtEmailPac.setDisable(false);
		txtFonePac.setDisable(false);

		btnIncluir.setDisable(true);
		btnConsultar.setDisable(true);
		btnExcluir.setDisable(true);
		btnAlterar.setDisable(true);
		btnCancelar.setDisable(false);
	}

	public void incluir() {
		System.out.println("Chamou o método INCLUIR");
	}

	public void onBtnConsultarClick() {
		evento = "consultar";

		txtCpfPac.setDisable(false);
		txtNomePac.setDisable(true);
		txtNascimentoPac.setDisable(true);
		txtEnderecoPac.setDisable(true);
		txtEmailPac.setDisable(true);
		txtFonePac.setDisable(true);

		btnIncluir.setDisable(true);
		btnConsultar.setDisable(true);
		btnExcluir.setDisable(true);
		btnAlterar.setDisable(true);

		btnCancelar.setDisable(false);
	}

	public void consultar() {
		System.out.println("Chamou o método CONSULTAR");

		Connection banco = abreBanco();
		PreparedStatement ps;
		ResultSet rs = null;
		String sql;
		boolean achou = false;

		cpfPac = Integer.parseInt(txtCpfPac.getText());

		try {
			sql = "Select * from paciente where cpfPac=" + cpfPac;
			ps = (PreparedStatement) banco.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				txtCpfPac.setText(rs.getString("cpfPac"));
				txtNomePac.setText(rs.getString("nomePac"));
				txtNascimentoPac.setText(rs.getString("nasciPac"));
				txtEnderecoPac.setText(rs.getString("enderecoPac"));
				txtEmailPac.setText(rs.getString("emailPac"));
				txtFonePac.setText(rs.getString("fonePac"));
				lblMensagem.setText("");
				achou = true;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if (!achou) {
			lblMensagem.setText("Código não encontrado no banco de");
		}
	}

	public void onBtnExcluirClick() {
		excluir();
	}

	public void excluir() {
		System.out.println("Chamou o método EXCLUIR");
		onBtnCancelarClick();
	}

	public void onBtnAlterarClick() {
		evento = "alterar";
		txtCpfPac.setDisable(false);
		txtNomePac.setDisable(false);
		txtNascimentoPac.setDisable(false);
		txtEnderecoPac.setDisable(false);
		txtEmailPac.setDisable(false);
		txtFonePac.setDisable(false);
		btnConfirmar.setDisable(false);

	}

	public void alterar() {
		System.out.println("Chamou o método ALTERAR");
	}

	public void onBtnConfirmarClick() {
		if (evento.equalsIgnoreCase("incluir")) {
			incluir();
			onBtnCancelarClick();

		} else if (evento.equalsIgnoreCase("consultar")) {
			if (!txtCpfPac.getText().matches("[0-9]*")) {
				System.out.println("Digite um CPF valido.");
			} else {
				txtCpfPac.setDisable(true);
				btnExcluir.setDisable(false);
				btnAlterar.setDisable(false);
				btnConfirmar.setDisable(true);

				consultar();
			}
		} else if (evento.equalsIgnoreCase("alterar")) {
			alterar();
			onBtnCancelarClick();
		}
	}

	public void onBtnCancelarClick() {
		btnIncluir.setDisable(false);
		btnConsultar.setDisable(false);
		btnExcluir.setDisable(true);
		btnAlterar.setDisable(true);

		txtCpfPac.setText("");
		txtNomePac.setText("");
		txtNascimentoPac.setText("");
		txtEnderecoPac.setText("");
		txtEmailPac.setText("");
		txtFonePac.setText("");

		txtCpfPac.setDisable(true);
		txtNomePac.setDisable(true);
		txtNascimentoPac.setDisable(true);
		txtEnderecoPac.setDisable(true);
		txtEmailPac.setDisable(true);
		txtFonePac.setDisable(true);

		btnConfirmar.setDisable(true);
		btnCancelar.setDisable(true);

	}
	
	public void onBtnVoltar() throws Exception {
		System.out.println("Voltando para indexPage");
		Parent p = FXMLLoader.load(getClass().getResource("/interfacegrafica/Index.fxml"));
		Stage window = (Stage) btnVoltar.getScene().getWindow();
		window.setScene(new Scene(p));
	}

	public void onKeyRelesead() {
		boolean confirmar;

		if (evento.equalsIgnoreCase("incluir")) {
			confirmar = txtCpfPac.getText().isEmpty() | txtNomePac.getText().isEmpty()
					| txtNascimentoPac.getText().isEmpty() | txtEnderecoPac.getText().isEmpty()
					| txtEmailPac.getText().isEmpty() | txtFonePac.getText().isEmpty();
			btnConfirmar.setDisable(confirmar);
		} else if (evento.equalsIgnoreCase("consultar")) {
			confirmar = txtCpfPac.getText().isEmpty();
			btnConfirmar.setDisable(confirmar);
		} else if (evento.equalsIgnoreCase("alterar")) {
		}
	}
}
