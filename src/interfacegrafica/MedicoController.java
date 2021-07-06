package interfacegrafica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.JMRuntimeException;

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
		Connection banco = abreBanco();
		PreparedStatement ps;
		ResultSet rs = null;
		String sql;
		boolean add = false;

		crmMed = Integer.parseInt(txtCrmMed.getText());
		nomeMed = txtNomeMed.getText();
		senhaMed = txtSenhaMed.getText();
		emailMed = txtEmailMed.getText();
		foneMed = txtFoneMed.getText();
		if (rdoGargMed.isSelected()) {
			espeMed = "Garganta";
		} else if (rdoNariMed.isSelected()) {
			espeMed = "Nariz";
		} else {
			espeMed = "Ouvido";
		}

		try {
			sql = "Insert into medico (crmMed, nomeMed, senhaMed, emailMed, foneMed, espeMed) values (?,?,?,?,?,?)";
			ps = (PreparedStatement) banco.prepareStatement(sql);
			ps.setInt(1, crmMed);
			ps.setString(2, nomeMed);
			ps.setString(3, senhaMed);
			ps.setString(4, emailMed);
			ps.setString(5, foneMed);
			ps.setString(6, espeMed);

			int rsAltera = ps.executeUpdate();
			add = true;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if (!add) {
			lblMensagem.setText(crmMed + " Não incluiu.");
		} else {
			lblMensagem.setText(crmMed + " Incluiu novo funcionario.");
		}
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
				achou = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if (achou != false) {
			lblMensagem.setText("Consulta.");

		} else {
			lblMensagem.setText("CPF não encontrado.");
			onBtnCancelarClick();
		}
	}

	public void onBtnExcluirClick() {
		btnConfirmar.setDisable(false);
		lblMensagem.setText("Confirme para escluir.");
		evento = "excluir";

	}

	public void excluir() {
		Connection banco = abreBanco();
		PreparedStatement ps;
		ResultSet rs = null;
		String sql;
		boolean exclui = false;

		crmMed = Integer.parseInt(txtCrmMed.getText());
		nomeMed = txtNomeMed.getText();
		try {
			sql = "Delete from medico where crmMed= " + crmMed;
			ps = (PreparedStatement) banco.prepareStatement(sql);
			int rsAltera = ps.executeUpdate();
			
			exclui = true;

		} catch (Exception e) {
			throw new JMRuntimeException();
		}
		if (exclui != false) {
			lblMensagem.setText(nomeMed + " foi excluido(a).");
		} else
			lblMensagem.setText(nomeMed + " não foi excluido(a).");
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
		Connection banco = abreBanco();
		PreparedStatement ps;
		ResultSet rs = null;
		String sql;
		boolean modificou = false;

		crmMed = Integer.parseInt(txtCrmMed.getText());
		nomeMed = txtNomeMed.getText();
		senhaMed = txtSenhaMed.getText();
		emailMed = txtEmailMed.getText();
		foneMed = txtFoneMed.getText();

		if (rdoGargMed.isSelected()) {
			espeMed = "Garganta";
		} else if (rdoNariMed.isSelected()) {
			espeMed = "Nariz";
		} else {
			espeMed = "Ouvido";
		}

		try {
			sql = "Update medico set nomeMed = ?, senhaMed = ?, emailMed = ?, foneMed = ?, espeMed = ?"
					+ "where crmMed=?";

			ps = (PreparedStatement) banco.prepareStatement(sql);
			ps.setString(1, nomeMed);
			ps.setString(2, senhaMed);
			ps.setString(3, emailMed);
			ps.setString(4, foneMed);
			ps.setString(5, espeMed);
			ps.setInt(6, crmMed);
			ps.executeUpdate();
			ps.close();
			banco.close();

			modificou = true;
			
		} catch (Exception e) {
			throw new RuntimeException();
		}

		if (modificou != false) {
			lblMensagem.setText(nomeMed + " foi alterado(a).");
		} else {
			lblMensagem.setText(nomeMed + " Não foi alterado(a).");
		}
	}

	public void onBtnConfirmarClick() {
		if (evento.equalsIgnoreCase("incluir")) {
			if (!txtCrmMed.getText().matches("[0-9]*")) {
				lblMensagem.setText("Digite um CRM valido.");

			} else {
				incluir();
				onBtnCancelarClick();
			}

		} else if (evento.equalsIgnoreCase("consultar")) {
			if (!txtCrmMed.getText().matches("[0-9]*")) {
				lblMensagem.setText("Digite um CRM valido.");

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

		} else {
			excluir();
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
		Parent p = FXMLLoader.load(getClass().getResource("/interfacegrafica/Index.fxml"));
		Stage window = (Stage) btnVoltar.getScene().getWindow();
		window.setScene(new Scene(p));
	}

	public void onKeyRelesead() {
		boolean confirmar;

		if (evento.equalsIgnoreCase("incluir")) {
			confirmar = txtCrmMed.getText().isEmpty() | txtNomeMed.getText().isEmpty() | txtSenhaMed.getText().isEmpty()
					| txtEmailMed.getText().isEmpty() | txtFoneMed.getText().isEmpty();
			btnConfirmar.setDisable(confirmar);

		} else if (evento.equalsIgnoreCase("consultar")) {
			confirmar = txtCrmMed.getText().isEmpty();
			btnConfirmar.setDisable(confirmar);

		}
	}
}
