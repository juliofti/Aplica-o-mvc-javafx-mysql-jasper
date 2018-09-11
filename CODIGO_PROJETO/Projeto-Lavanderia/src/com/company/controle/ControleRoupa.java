package com.company.controle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import com.company.dao.DaoRoupa;
import com.company.modelo.Cliente;
import com.company.modelo.Roupa;
import com.company.util.Linguage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ControleRoupa implements Initializable{
  //-----------------------------------------------------------------
    @FXML private Label lbl_tipo_de_roupa;
    @FXML private Tab tp_cadastrar_roupa;
    @FXML private Label lbl_descricao_roupa;
    @FXML private TextField txtDescricao;
    @FXML private Label lbl_cor_roupa;
    @FXML private TextField txtCor;
    @FXML private Label lbl_tecido_roupa;
    @FXML private TextField txtTecido;
    @FXML private Button btn_cadastrar;
    //---------------------------------------------------------------
    @FXML private Tab tp_consultar_roupa;
    @FXML private Label lbl_roupa_consulta;
    @FXML private TextField txtConsultaNome;
    @FXML private Button btn_Consultar;
    @FXML private Button btn_Atualizar;
    @FXML private Button btn_Deletar;
    @FXML private TableView<Roupa> roupa;
    @FXML private TableColumn<Roupa, Integer> cln_id_roupa;
    @FXML private TableColumn<Roupa, String> cln_descricao_roupa;
    @FXML private TableColumn<Roupa, String> cln_cor_roupa;
    @FXML private TableColumn<Roupa,String>cln_tecido_roupa;
    //---------------------------------------------------------------
    @FXML private Tab tp_atualizar_roupa;
    @FXML private Label lbl_descricao_roupa1;
    @FXML private TextField txtDescricao1;
    @FXML private Label lbl_cor_roupa1;
    @FXML private TextField txtCor1;
    @FXML private Label lbl_tecido_roupa1;
    @FXML private TextField txtTecido1;
    @FXML private Button btn_alterar;
    //--------------------------------------------------------------
    @FXML  private AnchorPane anchor;
    @FXML  private TabPane abas;
    DaoRoupa daor = new DaoRoupa();
    private ObservableList<Roupa> obsRoupa;
    private Roupa roupaSelecionada = null;
	private ResourceBundle bundle;
	private Locale locale;
	

	@FXML
	void gerenciarAbas() throws SQLException {
		if (tp_cadastrar_roupa.isSelected() || tp_consultar_roupa.isSelected()) {
			tp_atualizar_roupa.setDisable(true);
		
		}
		if (tp_cadastrar_roupa.isSelected() || tp_atualizar_roupa.isSelected()) {
			ArrayList<Roupa> listaVazia = new ArrayList<Roupa>();
			this.obsRoupa = FXCollections.observableArrayList(listaVazia);
			this.roupa.setItems(obsRoupa);
		}

	}


    @FXML
    void btnOnActionCadastrar() {
    
    	
    if(validarEntradaDeDados()) {  
    Roupa r = new Roupa();
    r.setSt_pv_descricao(txtDescricao.getText());
    r.setSt_pv_cor(txtCor.getText());
    r.setSt_pv_tecido(txtTecido.getText());
  
    	daor.cadastrar(r);
    	com.company.util.Metodos.msgInformacao(txtDescricao.getText(), "Roupa Cadastrada com Sucesso");
    	limparCamposCadastrar();   
    }

    }


    @FXML
    void btnConsultarOnActiion(ActionEvent event) {
    	alimentarTableView();
    }

    @FXML
    void abrirAbaAtualizacao(ActionEvent event) {
roupaSelecionada = roupa.getSelectionModel().getSelectedItem();
if(roupaSelecionada == null) {
	com.company.util.Metodos.msgErro("Erro", "Não há roupa selecionada");
	}else {
		tp_atualizar_roupa.setDisable(false); // # ASSIM QUE UMA ROUPA FOR SELECIONADO A OPÇÃO ATUALIZAR E ATIVADA#
		abas.getSelectionModel().select(tp_atualizar_roupa);// # ABRIR ABA DE ATUALIZAÇÃO
	txtDescricao1.setText(roupaSelecionada.getSt_pv_descricao());
	txtCor1.setText(roupaSelecionada.getSt_pv_cor());
	txtTecido1.setText(roupaSelecionada.getSt_pv_tecido());
	}

    }

    @FXML
    void deletarCliente(ActionEvent event) {
    	if (roupa.getSelectionModel().getSelectedItem() == null) {
			com.company.util.Metodos.msgErro("Erro", "Não há roupa selecionado");
		} else {
			if (com.company.util.Metodos.msgConfirmacao("Confirma a exclusão da roupa selecionada?") == true) {
				try {
					daor.deletar(roupa.getSelectionModel().getSelectedItem().getIn_pv_id_roupa());
					com.company.util.Metodos.msgInformacao(null, "Roupa Deletada Com Sucesso");
					alimentarTableView();
				} catch (Exception e) {
					com.company.util.Metodos.msgErro(null, "Erro na Exclusão");
			}}}
			}

    @FXML
    void btnOnActionAlterar(ActionEvent event) {
    	if(validarEntradaDeDadosAtualizacao()) {
    	roupaSelecionada.setSt_pv_descricao(txtDescricao1.getText());
    	roupaSelecionada.setSt_pv_cor(txtCor1.getText());
    	roupaSelecionada.setSt_pv_tecido(txtTecido1.getText());
    	
    	try {
    		daor.atualizar(roupaSelecionada);
    		com.company.util.Metodos.msgInformacao(null, "Atualizado Com Sucesso");
    		abas.getSelectionModel().select(tp_consultar_roupa); // #ASSIM QUE ATUALIZAR VOLTA PARA ABA CONSULTAR#//
			alimentarTableView();
			tp_atualizar_roupa.setDisable(true);// # DESABILITAR UMA JANELA
    	}catch(Exception e) {
    		com.company.util.Metodos.msgErro(null, "Falha ao Atualizar!");
    	}
    	}
    }

    public void limparCamposCadastrar() {
    	txtDescricao.setText("");
    	txtTecido.setText("");
    	txtCor.setText("");
    }
    
    public void alimentarTableView() {
    	ArrayList<Roupa> listaRoupa = new ArrayList<Roupa>();
    	try {
			listaRoupa = daor.consultar(txtConsultaNome.getText());
			if(listaRoupa.isEmpty()) {
				com.company.util.Metodos.msgInformacao(txtDescricao.getText(), "Não existe este tipo de  roupa!");
			}else {
				obsRoupa = FXCollections.observableArrayList(listaRoupa);
			roupa.setItems(obsRoupa);
			}
		} catch (Exception e) {
			com.company.util.Metodos.msgErro("Erro", "Falha na Busca");
		}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		carregarLinguage(Linguage.getLingua());
	 cln_id_roupa.setCellValueFactory(new PropertyValueFactory<>("in_pv_id_roupa"));
	 cln_descricao_roupa.setCellValueFactory(new PropertyValueFactory<>("st_pv_descricao"));
	 cln_cor_roupa.setCellValueFactory(new PropertyValueFactory<>("st_pv_cor"));
	 cln_tecido_roupa.setCellValueFactory(new PropertyValueFactory<>("st_pv_tecido"));
	
	 
	}
	
	  //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (txtDescricao.getText() == null || txtDescricao.getText().length() < 0 || txtDescricao.getText().equals("") || txtDescricao.getText().trim().isEmpty()) {
            errorMessage += "Descrição invalida!\n";
        }
        if (txtTecido.getText() == null || txtTecido.getText().length() < 0 || txtTecido.getText().equals("") || txtTecido.getText().trim().isEmpty()) {
            errorMessage += "Tecido Invalido!\n";
        }
        if (txtCor.getText() == null || txtCor.getText().length() < 0 || txtCor.getText().equals("") || txtCor.getText().trim().isEmpty()) {
            errorMessage += "Cor Invalida!\n";
        }
       
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            com.company.util.Metodos.msgErro("Campos invalidos, por favor, corrija...", errorMessage);
            return false;
        }
        }
    
	  //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDadosAtualizacao() {
        String errorMessage = "";

        if (txtDescricao1.getText() == null || txtDescricao1.getText().length() < 0 || txtDescricao1.getText().equals("") || txtDescricao1.getText().trim().isEmpty()) {
            errorMessage += "Descrição invalida!\n";
        }
        if (txtTecido1.getText() == null || txtTecido1.getText().length() < 0 || txtTecido1.getText().equals("") || txtTecido1.getText().trim().isEmpty()) {
            errorMessage += "Tecido Invalido!\n";
        }
        if (txtCor1.getText() == null || txtCor1.getText().length() < 0 || txtCor1.getText().equals("") || txtCor1.getText().trim().isEmpty()) {
            errorMessage += "Cor Invalida!\n";
        }
       
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            com.company.util.Metodos.msgErro("Campos invalidos, por favor, corrija...", errorMessage);
            return false;
        }
        }


    public void carregarLinguage(String lingua) {
		locale = new  Locale(lingua);
		bundle = ResourceBundle.getBundle("com.company.idioma.lang",locale);
	    lbl_tipo_de_roupa.setText(bundle.getString("lbl_tipo_de_roupa"));
	    	    tp_cadastrar_roupa.setText(bundle.getString("tp_cadastrar_roupa"));
	    	   	lbl_descricao_roupa.setText(bundle.getString("lbl_descricao_roupa"));
	    	    lbl_cor_roupa.setText(bundle.getString("lbl_cor_roupa"));
	    	    lbl_tecido_roupa.setText(bundle.getString("lbl_tecido_roupa"));
	    	    btn_cadastrar.setText(bundle.getString("btn_cadastrar"));
	    	    
	    	     tp_consultar_roupa.setText(bundle.getString("tp_consultar_roupa"));
	    	     lbl_roupa_consulta.setText(bundle.getString("lbl_roupa_consulta"));
	    	    btn_Consultar.setText(bundle.getString("btn_Consultar"));
	    	    btn_Atualizar.setText(bundle.getString("btn_Atualizar"));
	    	    btn_Deletar.setText(bundle.getString("btn_Deletar"));
	    	    cln_id_roupa.setText(bundle.getString("cln_id_roupa"));
	    	    cln_descricao_roupa.setText(bundle.getString("cln_descricao_roupa"));
	    	    cln_cor_roupa.setText(bundle.getString("cln_cor_roupa"));
	    	    cln_tecido_roupa.setText(bundle.getString("cln_tecido_roupa"));
	    	     tp_atualizar_roupa.setText(bundle.getString("tp_atualizar_roupa"));
	    	    lbl_descricao_roupa1.setText(bundle.getString("lbl_descricao_roupa1"));
	    	    lbl_cor_roupa1.setText(bundle.getString("lbl_cor_roupa1"));
	    	    lbl_tecido_roupa1.setText(bundle.getString("lbl_tecido_roupa1"));
	    	    btn_alterar.setText(bundle.getString("btn_alterar"));
	    }
}
 