package com.company.controle;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import com.company.modelo.Cliente;
import com.company.modelo.Estado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import com.company.relatorio.*;
import com.company.util.Linguage;
import com.company.util.TextFieldFormatter;
import com.company.util.ValidaCPF;
import com.company.dao.DaoCliente;

public class ControleCliente implements Initializable{
//começo das variaveis dos componentes do cadastro de cliente
	@FXML private Label lbl_titulo_cliente;
	@FXML private Tab cadastrar;
    @FXML private Label lbl_nome;
    @FXML private TextField txtNome;
    @FXML private Label lbl_cpf;
    @FXML private TextField txtCpf;
    @FXML private Label lbl_nascimento;
    @FXML private DatePicker txtData;
    @FXML private Label lbl_email; 
    @FXML private TextField txtemail;
    @FXML private Label lbl_numero;
    @FXML private TextField txtnumero;
    @FXML private Label lbl_titulo;
    @FXML private Label lbl_endereco;
    @FXML private TextField txtendereco;
    @FXML private Label lbl_cidade;
    @FXML private TextField txtcidade;
    @FXML private Label lbl_cep;
    @FXML private TextField txtcep;
    @FXML private Label lbl_uf;
    @FXML private ComboBox<Estado> cbestado;
    @FXML  private Button btn_cadastrar;
    // fim das variaveis da  tela de cadastro de cliente
    
    // Tap pane de consulta---------------------------------------------------------------------------------------------
    @FXML private Tab consultar;
    @FXML private Label lbl_nome_consulta;
    @FXML private TextField txtConsultaNome;
    @FXML private Button btnConsultar;
    @FXML private Button btnAtualizar;
    @FXML private Button btnRelatorio;
    @FXML private Button btnDeletar;
    @FXML private TableColumn<Cliente, Integer> cln_id;
    @FXML private TableColumn<Cliente, String> cln_nome;
    @FXML private TableColumn<Cliente, String> cln_cpf;
    @FXML private TableColumn<Cliente, Date  > cln_datanascimento;
    @FXML private TableColumn<Cliente, String> cln_email;
    @FXML private TableColumn<Cliente, String> cln_telefone;
    @FXML private TableColumn<Cliente, String> cln_endereco;
    @FXML private TableColumn<Cliente, String> cln_estado;
    @FXML private TableColumn<Cliente, String> cln_cidade;
    @FXML private TableColumn<Cliente, String> cln_cep;
    @FXML private TableView<Cliente> cliente;
    @FXML private TabPane abas;
    //------------------------------------------------------------------------------------------------------------------
    // começo das variaveis dos componentes de atualizar cliente
    @FXML private Tab atualizar;
    @FXML private Label lbl_nome1;
    @FXML private TextField txtNome1;
    @FXML private Label lbl_cpf1;
    @FXML private TextField txtCpf1;
    @FXML private Label lbl_nascimento1;
    @FXML private DatePicker txtData1;
    @FXML private Label lbl_email1;
    @FXML private TextField txtemail1;
    @FXML private Label lbl_numero1;
    @FXML private TextField txtnumero1;
    @FXML private Label lbl_titulo1;
    @FXML private Label lbl_endereco1;
    @FXML private TextField txtendereco1;
    @FXML private Label lbl_cidade1;
    @FXML private TextField txtcidade1;
    @FXML private Label lbl_cep1;
    @FXML private TextField txtcep1;
    @FXML private Label lbl_uf1;
    @FXML private ComboBox<Estado> cbestado1;
    @FXML private Button btn_atualizar;
    // fim das variaveis da tela de atualizar cliente---------------------------------------------------------------------
    
    private ObservableList<Cliente> obsCliente;
    private ObservableList<Estado> obsEstado;
    @FXML private AnchorPane anchor;
	private ArrayList<Estado> lista = new ArrayList<>();
	DaoCliente daoc = new DaoCliente();
	private Cliente clienteSelecionado = null;
	private ResourceBundle bundle;
	private Locale locale;
    
	@FXML
	void gerenciarAbas() throws SQLException {
		if (cadastrar.isSelected() || consultar.isSelected()) {
			atualizar.setDisable(true);
		
		}
		if (cadastrar.isSelected() || atualizar.isSelected()) {
			ArrayList<Cliente> listaVazia = new ArrayList<Cliente>();
			this.obsCliente = FXCollections.observableArrayList(listaVazia);
			this.cliente.setItems(obsCliente);
		}

	}


public String formatarCpf(String cpf) {
	cpf = cpf.replace( "-" , ""); //tira hífen
	return cpf;
}
    @FXML
    void btnOnActionCadastrar(ActionEvent event) {
    	try {
    	ValidaCPF vc = new  ValidaCPF();
		Cliente c = new Cliente();
		c.setSt_pv_nome(txtNome.getText());
		c.setSt_pv_cpf(txtCpf.getText());
		c.setDt_pv_nascimento(Date.valueOf(txtData.getValue()));
		c.setSt_pv_email(txtemail.getText());
		c.setSt_pv_telefone(txtnumero.getText());
		c.setSt_pv_endereco(txtendereco.getText());
		c.setSt_pv_cidade(txtcidade.getText());
		c.setSt_pv_cep(txtcep.getText());
		c.setSt_pv_estado(cbestado.getSelectionModel().getSelectedItem().getNome());

		boolean resultado = vc.validarCpf(formatarCpf(txtCpf.getText()));

		try {
			if (resultado == true) {
				daoc.cadastrar(c);
				
				com.company.util.Metodos.msgInformacao(c.getSt_pv_nome(), "Usuário Cadastrado com Sucesso");
				limparCamposCadastrar();
			} else {
				com.company.util.Metodos.msgErro(txtCpf.getText(), "Favor Digite um Cpf Valido!!!");
			}

		} catch (Exception e) {
			com.company.util.Metodos.msgErro(null, "Falha ao Cadastrar Cliente");
		}
    	}catch(Exception e) {
    		com.company.util.Metodos.msgErro(null, "Falta preencher campos obrigatorios");
    	}
		
		
    }



    @FXML
    void btnConsultarOnActiion(ActionEvent event) {    
    	alimentarTableView();
    }
    
    @FXML
    void btnOnActionAtualizar(ActionEvent event) {
    	
		clienteSelecionado.setSt_pv_nome(txtNome1.getText());
		clienteSelecionado.setSt_pv_cpf(txtCpf1.getText());
		clienteSelecionado.setDt_pv_nascimento(Date.valueOf(txtData1.getValue()));
		clienteSelecionado.setSt_pv_email(txtemail1.getText());
		clienteSelecionado.setSt_pv_telefone(txtnumero1.getText());
		clienteSelecionado.setSt_pv_endereco(txtendereco1.getText());
		clienteSelecionado.setSt_pv_cidade(txtcidade1.getText());
		clienteSelecionado.setSt_pv_cep(txtcep1.getText());
		clienteSelecionado.setSt_pv_estado(cbestado1.getSelectionModel().getSelectedItem().getNome());
		try {
			daoc.alterar(clienteSelecionado);
			com.company.util.Metodos.msgInformacao(null, "Atualizado Com Sucesso");
			abas.getSelectionModel().select(consultar); // #ASSIM QUE ATUALIZAR VOLTA PARA ABA CONSULTAR#//
			alimentarTableView();
			atualizar.setDisable(true);// # DESABILITAR UMA JANELA
		} catch (Exception e) {
			com.company.util.Metodos.msgErro(null, "Falha ao Atualizar...");
		}
    }

    @FXML
    void abrirAbaAtualizacao(ActionEvent event) {
		clienteSelecionado = cliente.getSelectionModel().getSelectedItem();
		if (clienteSelecionado == null) {
			com.company.util.Metodos.msgErro("Erro", "Não há Cliente selecionado");
		} else {
			atualizar.setDisable(false); // # ASSIM QUE UM CLIENTE FOR SELECIONADO A OPÇÃO ATUALIZAR E ATIVADA#
			abas.getSelectionModel().select(atualizar);// # ABRIR ABA DE ATUALIZAÇÃO
		txtNome1.setText(clienteSelecionado.getSt_pv_nome());
    	txtCpf1.setText(clienteSelecionado.getSt_pv_cpf());
    	txtData1.setValue(clienteSelecionado.getDt_pv_nascimento().toLocalDate());
    	txtemail1.setText(clienteSelecionado.getSt_pv_email());
    	txtnumero1.setText(clienteSelecionado.getSt_pv_telefone());
    	txtendereco1.setText(clienteSelecionado.getSt_pv_endereco());
    	txtcidade1.setText(clienteSelecionado.getSt_pv_cidade());
    	txtcep1.setText(clienteSelecionado.getSt_pv_cep());

		}
    }

    @FXML
    void deletarCliente(ActionEvent event) {
    	if (cliente.getSelectionModel().getSelectedItem() == null) {
			com.company.util.Metodos.msgErro("Erro", "Não há cliente selecionado");
		} else {
			if (com.company.util.Metodos.msgConfirmacao("Confirma a exclusão do cliente selecionado?") == true) {
				try {
					daoc.excluir(cliente.getSelectionModel().getSelectedItem().getIn_pv_idcliente());
					com.company.util.Metodos.msgInformacao(null, "Cliente Deletado Com Sucesso");
					alimentarTableView();
				} catch (Exception e) {
					com.company.util.Metodos.msgErro(null, "Erro na Exclusão");
				}
			}
		}
    }

    @FXML
    void RelatorioOnAction() throws Exception {
    	Relatorio relatorio = new Relatorio();
		relatorio.gerarRelatorio();
    }
    
	public void carregarEstado()  {
		Estado estado = new Estado(1,"GO");
		Estado estado1 = new Estado(2,"MA");
		lista.add(estado);
		lista.add(estado1);
		obsEstado = FXCollections.observableArrayList(lista);
		cbestado.setItems(obsEstado);
		cbestado1.setItems(obsEstado);
		}


public void limparCamposCadastrar() {
	txtNome.setText("");
	txtCpf.setText("");
	txtData.setValue(null);
	txtemail.setText("");
	txtnumero.setText("");
	txtendereco.setText("");
	txtcidade.setText("");
	txtcep.setText("");
	
}

public void alimentarTableView() {
	try {
		
		ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
		listaCliente = daoc.consultar(txtConsultaNome.getText());
		if (listaCliente.isEmpty()) {
			com.company.util.Metodos.msgInformacao(txtConsultaNome.getText(), "Nenhum Cliente Encontrado com este nome");
		} else {
			obsCliente = FXCollections.observableArrayList(listaCliente);
			cliente.setItems(obsCliente);
		}
	} catch (SQLException e1) {
		com.company.util.Metodos.msgErro("Erro", "Falha na Busca");
	}

}

@FXML
void txtnumeroReleased() {
TextFieldFormatter txt = new TextFieldFormatter();
txt.setMask("(##)#####-####");
txt.setCaracteresValidos("0123456789");
txt.setTf(txtnumero);
txt.formatter();
}

@FXML
void txtCpfReleased() {
	TextFieldFormatter txt = new TextFieldFormatter();
	txt.setMask("###-###-###-##");
	txt.setCaracteresValidos("0123456789");
	txt.setTf(txtCpf);
	txt.formatter();
}
@FXML
void txtCepReleased() {
	TextFieldFormatter txt = new TextFieldFormatter();
	txt.setMask("####-####");
	txt.setCaracteresValidos("0123456789");
	txt.setTf(txtcep);
	txt.formatter();
}



@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	
	carregarEstado();

	carregarLinguage(Linguage.getLingua());
cln_id.setCellValueFactory(new PropertyValueFactory<>("in_pv_idcliente"));
cln_nome.setCellValueFactory(new PropertyValueFactory<>("st_pv_nome"));
cln_cpf.setCellValueFactory(new PropertyValueFactory<>("st_pv_cpf"));
cln_datanascimento.setCellValueFactory(new PropertyValueFactory<>("dt_pv_nascimento"));
cln_email.setCellValueFactory(new PropertyValueFactory<>("st_pv_email"));
cln_telefone.setCellValueFactory(new PropertyValueFactory<>("st_pv_telefone"));
cln_cidade.setCellValueFactory(new PropertyValueFactory<>("st_pv_cidade"));
cln_estado.setCellValueFactory(new PropertyValueFactory<>("st_pv_estado"));
cln_endereco.setCellValueFactory(new PropertyValueFactory<>("st_pv_endereco"));
cln_cep.setCellValueFactory(new PropertyValueFactory<>("st_pv_cep"));

	
	
}


public void carregarLinguage(String lingua) {
	locale = new  Locale(lingua);
	bundle = ResourceBundle.getBundle("com.company.idioma.lang",locale);
	lbl_titulo_cliente.setText(bundle.getString("lbl_titulo_cliente"));
	cadastrar.setText(bundle.getString("cadastrar"));
    lbl_nome.setText(bundle.getString("lbl_nome"));
    lbl_cpf.setText(bundle.getString("lbl_cpf"));
    lbl_nascimento.setText(bundle.getString("lbl_nascimento"));
    lbl_email.setText(bundle.getString("lbl_email"));
    lbl_numero.setText(bundle.getString("lbl_numero"));
    lbl_titulo.setText(bundle.getString("lbl_titulo"));
    lbl_endereco.setText(bundle.getString("lbl_endereco"));
    lbl_cidade.setText(bundle.getString("lbl_cidade"));
    lbl_cep.setText(bundle.getString("lbl_cep"));
    lbl_uf.setText(bundle.getString("lbl_uf"));
    cbestado.setPromptText(bundle.getString("cbestado"));
    btn_cadastrar.setText(bundle.getString("btn_cadastrar"));
    //---------------------------------------------------------------------------------
    consultar.setText(bundle.getString("consultar"));
    lbl_nome_consulta.setText(bundle.getString("lbl_nome_consulta"));
   btnConsultar.setText(bundle.getString("btnConsultar"));
   cln_id.setText(bundle.getString("cln_id"));
	cln_nome.setText(bundle.getString("cln_nome"));
   cln_cpf.setText(bundle.getString("cln_cpf"));
   cln_datanascimento.setText(bundle.getString("cln_datanascimento"));
   cln_email.setText(bundle.getString("cln_email"));
   cln_telefone.setText(bundle.getString("cln_telefone"));
	cln_endereco.setText(bundle.getString("cln_endereco"));
   cln_estado.setText(bundle.getString("cln_estado"));
   cln_cidade.setText(bundle.getString("cln_cidade"));
   cln_cep.setText(bundle.getString("cln_cep"));
   btnAtualizar.setText(bundle.getString("btn_atualizar"));
   btnConsultar.setText(bundle.getString("btnConsultar"));
   btnRelatorio.setText(bundle.getString("btnRelatorio"));
   btnDeletar.setText(bundle.getString("btnDeletar"));
  //--------------------------------------------------------------------------------------- 
   atualizar.setText(bundle.getString("atualizar"));
   lbl_nome1.setText(bundle.getString("lbl_nome1"));
   lbl_cpf1.setText(bundle.getString("lbl_cpf1"));
   lbl_nascimento1.setText(bundle.getString("lbl_nascimento1"));
   lbl_email1.setText(bundle.getString("lbl_email1"));
   lbl_numero1.setText(bundle.getString("lbl_numero1"));
   lbl_titulo1.setText(bundle.getString("lbl_titulo1"));
   lbl_endereco1.setText(bundle.getString("lbl_endereco"));
   lbl_cidade1.setText(bundle.getString("lbl_cidade1"));
   lbl_cep1.setText(bundle.getString("lbl_cep1"));
   lbl_uf1.setText(bundle.getString("lbl_uf1"));
   cbestado1.setPromptText(bundle.getString("cbestado1"));
   btn_atualizar.setText(bundle.getString("btn_atualizar"));
    
}

}
