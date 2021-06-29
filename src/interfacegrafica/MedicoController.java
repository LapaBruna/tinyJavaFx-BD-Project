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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MedicoController {
	@FXML
	private TextField txtCrmMed;
	@FXML
	private TextField txtNomeMed;
	@FXML
	private TextField txtSenhaMed;
	@FXML
	private TextField txtEmailMed;
	@FXML
	private TextField txtFoneMed;
	@FXML
	private RadioButton rdoOuviMed;
	@FXML
	private RadioButton rdoGargMed;
	@FXML
	private RadioButton rdoNariMed;
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

	private int crmMed;
	private String nomeMed;
	private String enderecoMed;
	private String senhaMed;
	private String emailMed;
	private String foneMed;
	private String espeMed;
	private String ouvido;
	private String garganta;
	private String nariz;

	private String evento = "";

	public void onBtnIncluirClick() {
		evento = "incluir";

		txtCrmMed.setDisable(false);
		txtNomeMed.setDisable(false);
		txtSenhaMed.setDisable(false);
		txtEmailMed.setDisable(false);
		txtFoneMed.setDisable(false);

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

		txtCrmMed.setDisable(false);
		txtNomeMed.setDisable(true);
		txtSenhaMed.setDisable(true);
		txtEmailMed.setDisable(true);
		txtFoneMed.setDisable(true);

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

		crmMed = Integer.parseInt(txtCrmMed.getText());

		try {
			sql = "Select * from medico where crmMed=" + crmMed;
			ps = (PreparedStatement) banco.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				txtCrmMed.setText(rs.getString("crmMed"));
				txtNomeMed.setText(rs.getString("nomeMed"));
				txtSenhaMed.setText(rs.getString("senhaMed"));
				txtEmailMed.setText(rs.getString("emailMed"));
				txtFoneMed.setText(rs.getString("foneMed"));

				String especialidade = rs.getString("espeMed");
				if (especialidade.equalsIgnoreCase("Ouvido")) {
					rdoOuviMed.setSelected(true);
				} else if (especialidade.equalsIgnoreCase("Garganta")) {
					rdoGargMed.setSelected(true);
				} else {
					rdoNariMed.setSelected(true);
				}

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
		txtCrmMed.setDisable(false);
		txtNomeMed.setDisable(false);
		txtSenhaMed.setDisable(false);
		txtEmailMed.setDisable(false);
		txtFoneMed.setDisable(false);
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
			if (!txtCrmMed.getText().matches("[0-9]*")) {
				System.out.println("Digite um CRM valido.");
			} else {
				txtCrmMed.setDisable(true);
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

		txtCrmMed.setText("");
		txtNomeMed.setText("");
		txtSenhaMed.setText("");
		txtEmailMed.setText("");
		txtFoneMed.setText("");

		txtCrmMed.setDisable(true);
		txtNomeMed.setDisable(true);
		txtSenhaMed.setDisable(true);
		txtEmailMed.setDisable(true);
		txtFoneMed.setDisable(true);

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
			confirmar = txtCrmMed.getText().isEmpty() | txtNomeMed.getText().isEmpty()
					|  txtSenhaMed.getText().isEmpty()
					| txtEmailMed.getText().isEmpty() | txtFoneMed.getText().isEmpty();
			btnConfirmar.setDisable(confirmar);
		} else if (evento.equalsIgnoreCase("consultar")) {
			confirmar = txtCrmMed.getText().isEmpty();
			btnConfirmar.setDisable(confirmar);
		} else if (evento.equalsIgnoreCase("alterar")) {
		}
	}
}
