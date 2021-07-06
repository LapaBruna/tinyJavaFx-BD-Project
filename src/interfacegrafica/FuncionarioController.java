package interfacegrafica;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.management.JMRuntimeException;

import com.mysql.jdbc.PreparedStatement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FuncionarioController implements Initializable {
	@FXML
	private TextField txtCpfFunc;
	@FXML
	private TextField txtNomeFunc;
	@FXML
	private TextField txtNascimentoFunc;
	@FXML
	private TextField txtEnderecoFunc;
	@FXML
	private TextField txtSenhaFunc;
	@FXML
	private TextField txtEmailFunc;
	@FXML
	private TextField txtFoneFunc;
	@FXML
	private ComboBox<String> cboFuncaoFunc;
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

	private int cpfFunc;
	private String nomeFunc;
	private String nasciFunc;
	private String enderecoFunc;
	private String senhaFunc;
	private String emailFunc;
	private String foneFunc;
	private String funcaoFunc;
	private String evento = "";

	public void onBtnIncluirClick() {
		evento = "incluir";

		txtCpfFunc.setDisable(false);
		txtNomeFunc.setDisable(false);
		txtNascimentoFunc.setDisable(false);
		txtEnderecoFunc.setDisable(false);
		txtSenhaFunc.setDisable(false);
		txtEmailFunc.setDisable(false);
		txtFoneFunc.setDisable(false);

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

		cpfFunc = Integer.parseInt(txtCpfFunc.getText());
		nomeFunc = txtNomeFunc.getText();
		nasciFunc = txtNascimentoFunc.getText();
		enderecoFunc = txtEnderecoFunc.getText();
		senhaFunc = txtSenhaFunc.getText();
		emailFunc = txtEmailFunc.getText();
		foneFunc = txtFoneFunc.getText();
		funcaoFunc = cboFuncaoFunc.getPromptText();

		try {
			sql = "Insert into funcionario (cpfFunc, nomeFunc, nasciFunc, enderecoFunc, senhaFunc, emailFunc, foneFunc, funcaoFunc) values (?,?,?,?,?,?,?,?)";
			ps = (PreparedStatement) banco.prepareStatement(sql);
			ps.setInt(1, cpfFunc);
			ps.setString(2, nomeFunc);
			ps.setString(3, nasciFunc);
			ps.setString(4, enderecoFunc);
			ps.setString(5, senhaFunc);
			ps.setString(6, emailFunc);
			ps.setString(7, foneFunc);
			ps.setString(8, funcaoFunc);

			int rsAltera = ps.executeUpdate();
			add = true;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if (!add) {
			lblMensagem.setText(cpfFunc + " Não incluiu.");
		} else {
			lblMensagem.setText(cpfFunc + " Incluiu novo funcionario.");
		}
	}

	public void onBtnConsultarClick() {
		evento = "consultar";

		txtCpfFunc.setDisable(false);
		txtNomeFunc.setDisable(true);
		txtNascimentoFunc.setDisable(true);
		txtEnderecoFunc.setDisable(true);
		txtSenhaFunc.setDisable(true);
		txtEmailFunc.setDisable(true);
		txtFoneFunc.setDisable(true);

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

		cpfFunc = Integer.parseInt(txtCpfFunc.getText());
		try {
			sql = "Select * from funcionario where cpfFunc=" + cpfFunc;
			ps = (PreparedStatement) banco.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				txtCpfFunc.setText(rs.getString("cpfFunc"));
				txtNomeFunc.setText(rs.getString("nomeFunc"));
				txtNascimentoFunc.setText(rs.getString("nasciFunc"));
				txtEnderecoFunc.setText(rs.getString("enderecoFunc"));
				txtSenhaFunc.setText(rs.getString("senhaFunc"));
				txtEmailFunc.setText(rs.getString("emailFunc"));
				txtFoneFunc.setText(rs.getString("foneFunc"));
				cboFuncaoFunc.setValue(rs.getString("funcaoFunc"));

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

		cpfFunc = Integer.parseInt(txtCpfFunc.getText());
		nomeFunc = txtNomeFunc.getText();
		try {
			sql = "Delete from funcionario where cpfFunc= " + cpfFunc;
			ps = (PreparedStatement) banco.prepareStatement(sql);
			int rsAltera = ps.executeUpdate();

		} catch (Exception e) {
			throw new JMRuntimeException();
		}
		if (exclui != false) {
			lblMensagem.setText(nomeFunc + " foi excluido(a).");
		} else {
			lblMensagem.setText(nomeFunc + " não foi excluido(a).");
		}
	}

	public void onBtnAlterarClick() {
		evento = "alterar";
		txtCpfFunc.setDisable(false);
		txtNomeFunc.setDisable(false);
		txtNascimentoFunc.setDisable(false);
		txtEnderecoFunc.setDisable(false);
		txtSenhaFunc.setDisable(false);
		txtEmailFunc.setDisable(false);
		txtFoneFunc.setDisable(false);
		btnConfirmar.setDisable(false);
		txtCpfFunc.setDisable(true);
	}

	public void alterar() {
		Connection banco = abreBanco();
		PreparedStatement ps;
		ResultSet rs = null;
		String sql;
		boolean modificou = false;

		cpfFunc = Integer.parseInt(txtCpfFunc.getText());
		nomeFunc = txtNomeFunc.getText();
		nasciFunc = txtNascimentoFunc.getText();
		enderecoFunc = txtEnderecoFunc.getText();
		senhaFunc = txtSenhaFunc.getText();
		emailFunc = txtEmailFunc.getText();
		foneFunc = txtFoneFunc.getText();
		funcaoFunc = cboFuncaoFunc.getPromptText();

		try {
			sql = "Update funcionario set nomeFunc = ?, nasciFunc = ?, enderecoFunc = ?, senhaFunc = ?, emailFunc = ?, foneFunc = ?, funcaoFunc = ? "
					+ "where cpfFunc=?";

			ps = (PreparedStatement) banco.prepareStatement(sql);
			ps.setString(1, nomeFunc);
			ps.setString(2, nasciFunc);
			ps.setString(3, enderecoFunc);
			ps.setString(4, senhaFunc);
			ps.setString(5, emailFunc);
			ps.setString(6, foneFunc);
			ps.setString(7, funcaoFunc);
			ps.setInt(8, cpfFunc);
			ps.executeUpdate();
			ps.close();
			banco.close();
			modificou = true;

		} catch (Exception e) {
			throw new RuntimeException();
		}
		if (modificou != false) {
			lblMensagem.setText(nomeFunc + " foi alterado(a).");
		} else {
			lblMensagem.setText(nomeFunc + " Não foi alterado(a).");
		}
	}

	public void onBtnConfirmarClick() {
		if (evento.equalsIgnoreCase("incluir")) {
			if (!txtCpfFunc.getText().matches("[0-9]*")) {
				lblMensagem.setText("Digite um CPF valido.");

			} else {
				incluir();
				onBtnCancelarClick();
			}

		} else if (evento.equalsIgnoreCase("consultar")) {
			if (!txtCpfFunc.getText().matches("[0-9]*")) {
				lblMensagem.setText("Digite um CPF valido.");

			} else {
				txtCpfFunc.setDisable(true);
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

		txtCpfFunc.setText("");
		txtNomeFunc.setText("");
		txtNascimentoFunc.setText("");
		txtEnderecoFunc.setText("");
		txtSenhaFunc.setText("");
		txtEmailFunc.setText("");
		txtFoneFunc.setText("");

		txtCpfFunc.setDisable(true);
		txtNomeFunc.setDisable(true);
		txtNascimentoFunc.setDisable(true);
		txtEnderecoFunc.setDisable(true);
		txtSenhaFunc.setDisable(true);
		txtEmailFunc.setDisable(true);
		txtFoneFunc.setDisable(true);

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
			confirmar = txtCpfFunc.getText().isEmpty() | txtNomeFunc.getText().isEmpty()
					| txtNascimentoFunc.getText().isEmpty() | txtEnderecoFunc.getText().isEmpty()
					| txtSenhaFunc.getText().isEmpty() | txtEmailFunc.getText().isEmpty()
					| txtFoneFunc.getText().isEmpty();
			btnConfirmar.setDisable(confirmar);

		} else if (evento.equalsIgnoreCase("consultar")) {
			confirmar = txtCpfFunc.getText().isEmpty();
			btnConfirmar.setDisable(confirmar);
		}
	}

	@Override
	public void initialize(URL locatioin, ResourceBundle resource) {
		cboFuncaoFunc.getItems().addAll("Auxiliar", "Marketing", "Médico", "Produção", "Recepcionista", "RH", "Vendas");
	}
}
