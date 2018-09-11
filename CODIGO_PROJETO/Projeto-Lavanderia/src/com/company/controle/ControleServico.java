package com.company.controle;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import com.company.dao.DaoServico;
import com.company.modelo.Cliente;
import com.company.modelo.Servico;
import com.company.util.Linguage;
import com.company.util.TextFieldFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ControleServico  implements Initializable{
//----------------------------------------------------------
	@FXML private Label lbl_titulo_servico;
    @FXML private Tab cadastrar;
    @FXML private Label lbl_descricao_servico;
    @FXML private TextField txtDescricao;
    @FXML private Label lbl_valor_servico;
    @FXML private TextField txtValorServico;
	@FXML private Button btn_cadastrar_servico;
//-----------------------------------------------------------
    @FXML private Tab consultar;
    @FXML private Label lbl_descricao_consulta;
    @FXML private TextField txtConsultaDescricao ;
    @FXML private Button btnConsultar;
    @FXML private TableColumn<?, ?> cln_id_servico;
    @FXML private TableColumn<?, ?> cln_descricao_servico;
    @FXML private TableColumn<?, ?> cln_valor_servico;
    @FXML private Button btnAtualizar;
    @FXML private Button btnDeletar;
    @FXML private TableView<Servico> servico;
 //-----------------------------------------------------------
    @FXML private Tab Atualizar;
    @FXML private Label lbl_descricao_servico1;
    @FXML private TextField txtDescricao1;
    @FXML private Label lbl_valor_servico1;
    @FXML private TextField txtValorServico1;
    @FXML private Button btn_atualizar_servico1;
//------------------------------------------------------------
    
    @FXML private AnchorPane anchor;
    @FXML private TabPane abas;
    ObservableList<Servico> obsServico;
    DaoServico daos = new DaoServico();
    private Servico servicoSelecionado = null;
	private ResourceBundle bundle;
	private Locale locale;
	
    @FXML void btnOnActionCadastrar(ActionEvent event) {
    	try {
    	if(validarEntradaDeDados()) {
    	Servico  s = new Servico();
    	s.setSt_pv_descricao(txtDescricao.getText());
    	double valor = Double.parseDouble( txtValorServico.getText());
    	s.setDb_pv_valor_servico(valor);
    	daos.cadastrar(s);
    	com.company.util.Metodos.msgInformacao(s.getSt_pv_descricao(), "Cadastrado com Sucesso");
    	limparCampos();
    	}
    	}catch(Exception e) {
    		com.company.util.Metodos.msgErro("Falha", "Valor do Serviço invalido");
    	}
    	
    }
    
    @FXML void btnOnActionAtualizar(ActionEvent event) throws SQLException {
    	if(validarEntradaDeDadosAtualizacao()) {
    	double preco = Double.parseDouble(txtValorServico1.getText());
    	servicoSelecionado.setSt_pv_descricao(txtDescricao1.getText());
    	servicoSelecionado.setDb_pv_valor_servico(preco);
		try {
			
			daos.atualizar(servicoSelecionado);
			com.company.util.Metodos.msgInformacao(null, "Atualizado Com Sucesso");
			abas.getSelectionModel().select(consultar); // #ASSIM QUE ATUALIZAR VOLTA PARA ABA CONSULTAR#//
			Atualizar.setDisable(true);// # DESABILITAR UMA JANELA
			alimentarTableView();
		} catch (Exception e) {
			com.company.util.Metodos.msgErro(null, "Falha ao Atualizar...");
		}
    	}
    }
    @FXML void btnConsultarOnActiion() throws Exception {
    	alimentarTableView();
    }

    @FXML  void abrirAbaAtualizacao(ActionEvent event) {
    	servicoSelecionado = servico.getSelectionModel().getSelectedItem();
		if (servicoSelecionado == null) {
			com.company.util.Metodos.msgErro("Erro", "Não há Serviço selecionado");
		} else {
			Atualizar.setDisable(false); // # ASSIM QUE UM CLIENTE FOR SELECIONADO A OPÇÃO ATUALIZAR E ATIVADA#
			abas.getSelectionModel().select(Atualizar);// # ABRIR ABA DE ATUALIZAÇÃO
		txtDescricao1.setText(servicoSelecionado.getSt_pv_descricao());
		txtValorServico1.setText(String.valueOf(servicoSelecionado.getDb_pv_valor_servico()));
		}
    }

    @FXML void deletar(ActionEvent event) {
    	if (servico.getSelectionModel().getSelectedItem() == null) {
			com.company.util.Metodos.msgErro("Erro", "Não há servico selecionado");
		} else {
			if (com.company.util.Metodos.msgConfirmacao("Confirma a exclusão do serviço selecionado?") == true) {
				try {
					daos.deletar(servico.getSelectionModel().getSelectedItem().getIn_pv_id_servico());
					com.company.util.Metodos.msgInformacao(null, "Serviço  Deletado Com Sucesso");
					alimentarTableView();
				} catch (Exception e) {
					com.company.util.Metodos.msgErro(null, "Erro na Exclusão");
			}}}
    }
  
    @FXML void gerenciarAbas() {
    	if (cadastrar.isSelected() || consultar.isSelected()) {
			Atualizar.setDisable(true);
		
		}
		if (cadastrar.isSelected() || Atualizar.isSelected()) {
			ArrayList<Servico> listaVazia = new ArrayList<Servico>();
			this.obsServico = FXCollections.observableArrayList(listaVazia);
			this.servico.setItems(obsServico);
		}
    }
	
    public void limparCampos() {
    	txtDescricao.setText("");
    	txtValorServico.setText("");
    }
    
    public void alimentarTableView() throws Exception {
		try {
			ArrayList<Servico> listaServico = new ArrayList<Servico>();
			listaServico = daos.consultar(txtConsultaDescricao.getText());
			if (listaServico.isEmpty()) {
				com.company.util.Metodos.msgInformacao(txtConsultaDescricao.getText(), "Nenhum Serviço  Encontrado com este nome");
			} else {
				obsServico = FXCollections.observableArrayList(listaServico);
				servico.setItems(obsServico);
			}
		} catch (SQLException e1) {
			com.company.util.Metodos.msgErro("Erro", "Falha na Busca");
		}

	}
    
    
  //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (txtDescricao.getText() == null || txtDescricao.getText().length() < 0 || txtDescricao.getText().equals("") || txtDescricao.getText().trim().isEmpty()) {
            errorMessage += "Descrição invalida!\n";
        }
        if (txtValorServico.getText() == null || txtValorServico.getText().length() < 0 || txtValorServico.getText().equals("") || txtValorServico.getText().trim().isEmpty()) {
            errorMessage += "Tecido Invalido!\n";
        }
       
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            com.company.util.Metodos.msgErro("Campos invalidos, por favor, corrija...", errorMessage);
            return false;
        }
        }

    //Validar entrada de dados da atualização
    private boolean validarEntradaDeDadosAtualizacao() {
        String errorMessage = "";

        if (txtDescricao1.getText() == null || txtDescricao1.getText().length() < 0 || txtDescricao1.getText().equals("") || txtDescricao1.getText().trim().isEmpty()) {
            errorMessage += "Descrição invalida!\n";
        }
        if (txtValorServico1.getText() == null || txtValorServico1.getText().length() < 0 || txtValorServico1.getText().equals("") || txtValorServico1.getText().trim().isEmpty()) {
            errorMessage += "Valor Invalido!\n";
        }
       
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            com.company.util.Metodos.msgErro("Campos invalidos, por favor, corrija...", errorMessage);
            return false;
        }
        }

    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	carregarLinguage(Linguage.getLingua());
	 cln_id_servico.setCellValueFactory(new PropertyValueFactory<>("in_pv_id_servico"));
     cln_descricao_servico.setCellValueFactory(new PropertyValueFactory<>("st_pv_descricao"));
     cln_valor_servico.setCellValueFactory(new PropertyValueFactory<>("db_pv_valor_servico"));
	}
    

    public void carregarLinguage(String lingua) {
		locale = new  Locale(lingua);
		bundle = ResourceBundle.getBundle("com.company.idioma.lang",locale);
	    lbl_titulo_servico.setText(bundle.getString("lbl_titulo_servico"));
	    	    cadastrar.setText(bundle.getString("cadastrar"));
	    	    lbl_descricao_servico.setText(bundle.getString("lbl_descricao_servico"));
	    	    lbl_valor_servico.setText(bundle.getString("lbl_valor_servico"));
	    	    btn_cadastrar_servico.setText(bundle.getString("btn_cadastrar_servico"));
	    	    consultar.setText(bundle.getString("consultar"));
	    	    lbl_descricao_consulta.setText(bundle.getString("lbl_descricao_consulta"));
	    	    btnConsultar.setText(bundle.getString("btnConsultar"));
	    	    cln_id_servico.setText(bundle.getString("cln_id_servico"));
	    	    cln_descricao_servico.setText(bundle.getString("cln_descricao_servico"));
	    	    cln_valor_servico.setText(bundle.getString("cln_valor_servico"));
	    	    btnAtualizar.setText(bundle.getString("btnAtualizar"));
	    	    btnDeletar.setText(bundle.getString("btnDeletar"));
	    	    Atualizar.setText(bundle.getString("Atualizar"));
	    	    lbl_descricao_servico1.setText(bundle.getString("lbl_descricao_servico1"));
	    	    lbl_valor_servico1.setText(bundle.getString("lbl_valor_servico1"));
	    	    btn_atualizar_servico1.setText(bundle.getString("btn_atualizar_servico1"));
	}
}
