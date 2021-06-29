package interfacegrafica;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
		System.out.println("Chamou o método INCLUIR");

		Connection banco = abreBanco();
		PreparedStatement ps;
		ResultSet rs = null;
		String sql;
		boolean achou = false;

		try {
			sql = "Select * from funcionario where cpfFunc=" + cpfFunc;
			ps = (PreparedStatement) banco.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
		if (!achou) {
			lblMensagem.setText("Código não encontrado no banco de");
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
		System.out.println("Chamou o método CONSULTAR");

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
		txtCpfFunc.setDisable(false);
		txtNomeFunc.setDisable(false);
		txtNascimentoFunc.setDisable(false);
		txtEnderecoFunc.setDisable(false);
		txtSenhaFunc.setDisable(false);
		txtEmailFunc.setDisable(false);
		txtFoneFunc.setDisable(false);
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
			
			if (!txtCpfFunc.getText().matches("[0-9]*")) {
				System.out.println("Digite um CPF valido.");
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
		System.out.println("Voltando para indexPage");
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
		} else if (evento.equalsIgnoreCase("alterar")) {
		}
	}

	@Override
	public void initialize(URL locatioin, ResourceBundle resource) {
		cboFuncaoFunc.getItems().addAll("Auxiliar", "Marketing", "Médico", "Produção", "Recepcionista", "RH", "Vendas");
	}

}
