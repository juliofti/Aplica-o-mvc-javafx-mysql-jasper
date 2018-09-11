package com.company.controle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.company.dao.DaoUsuario;
import com.company.modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ControleUsuario implements Initializable{

    @FXML private TableView<Usuario> tb_usuario;
    @FXML private Label lbl_perfil_usuario;
    @FXML private Label lbl_perfil_usuario1;
    @FXML private TableColumn<Usuario, String> cln_senha_usuario;
    @FXML private PasswordField txtSenha1;
    @FXML private Label lbl_titulo_usuario;
    @FXML private Label lbl_descricao_consulta;
    @FXML private Label lbl_senha_usuario;
    @FXML private TextField txtConsultaDescricao;
    @FXML private Button btnAtualizar;
    @FXML private Button btn_atualizar_usuario1;
    @FXML private TableColumn<Usuario, String> cln_perfil_usuario;
    @FXML private ComboBox<Usuario> cb_perfil_usuario;
    @FXML private ComboBox<Usuario> cb_perfil_usuario1;
    @FXML private Label lbl_nome_usuario;
    @FXML private TableColumn<Usuario, Integer> cln_id_usuario;
    @FXML  private Label lbl_nome_usuario1;
    @FXML private Button btn_cadastrar_usuario;
    @FXML private PasswordField txtSenha;
    @FXML private TextField txtNome;
    @FXML private Button btnConsultar;
    @FXML private Button btnDeletar;
    @FXML private TextField txtNome1;
    @FXML private Tab cadastrar;
    @FXML private TableColumn<Usuario, String> cln_nome_usuario;
    @FXML private Tab atualizar;
    @FXML  private AnchorPane anchor;
    @FXML private Tab consultar;
    @FXML private TabPane abas;
    @FXML private Label lbl_senha_usuario1;

    
    private DaoUsuario daou = new DaoUsuario();
    private Usuario usuarioSelecionado = null;
    private ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
    private ArrayList<Usuario> listaUsuario1 = new ArrayList<Usuario>();
    private ObservableList<Usuario> obsLista =null;
    private ObservableList<Usuario> obsLista1 = null ;
    
    @FXML
    void gerenciarAbas() {
    	if(cadastrar.isSelected() || consultar.isSelected()) {
    		atualizar.setDisable(true);
    			}
    	if(cadastrar.isSelected() || atualizar.isSelected()) {
    		this.tb_usuario.setItems(null);
    	}
    }

public void combobox() {
Usuario user = new Usuario(1,"Administrador");
Usuario user1 = new Usuario(2,"Atendente");
listaUsuario.add(user);
listaUsuario.add(user1);
obsLista = FXCollections.observableArrayList(listaUsuario);
cb_perfil_usuario.setItems(obsLista);
}
public void combobox2() {
Usuario user = new Usuario(1,"Administrador");
Usuario user1 = new Usuario(2,"Atendente");
listaUsuario1.add(user);
listaUsuario1.add(user1);
obsLista1 = FXCollections.observableArrayList(listaUsuario1);
cb_perfil_usuario1.setItems(obsLista1);
}

    @FXML
    void btnOnActionCadastrar() {
    	if(validarEntradaDeDados()) {
    	Usuario user = new Usuario();
    	user.setSt_pv_nome_usuario(txtNome.getText());
    	user.setSt_pv_senha_usuario(txtSenha.getText());
    	user.setSt_pv_perfil_usuario(cb_perfil_usuario.getSelectionModel().getSelectedItem().getSt_pv_perfil_usuario());
    	
    	try {
    	daou.cadastrar(user);
    	com.company.util.Metodos.msgInformacao("Cadastro Realizado com Sucesso", "");
    	 limparCampoUsuario();
    	}catch(Exception e) {
    		com.company.util.Metodos.msgErro(null, "Falha no Cadastro");
    	}}
    }

    public void limparCampoUsuario() {
    	txtNome.setText("");
    	txtSenha.setText("");
    	cb_perfil_usuario.setValue(null);
    }

    @FXML
    void btnConsultarOnActiion() throws Exception {
    	listaUsuario = daou.consultar(txtConsultaDescricao.getText());
    	obsLista = FXCollections.observableArrayList(listaUsuario);
    	tb_usuario.setItems(obsLista);
    }

    @FXML
    void abrirAbaAtualizacao() {
    	combobox2();
    	usuarioSelecionado = tb_usuario.getSelectionModel().getSelectedItem();
    	if(usuarioSelecionado == null) {
    		com.company.util.Metodos.msgInformacao("Selecione um usuário na Tabela", "");
    	}else {
    		atualizar.setDisable(false); // # ASSIM QUE UM USUARIO FOR SELECIONADO A OPÇÃO ATUALIZAR E ATIVADA#
			abas.getSelectionModel().select(atualizar);// # ABRIR ABA DE ATUALIZAÇÃO	
			txtNome1.setText(usuarioSelecionado.getSt_pv_nome_usuario());
	    	txtSenha1.setText(usuarioSelecionado.getSt_pv_senha_usuario());
	    	cb_perfil_usuario1.setValue(usuarioSelecionado);
    	}
    	
    	
    	
    }

    @FXML
    void deletar() throws Exception {
    	usuarioSelecionado = tb_usuario.getSelectionModel().getSelectedItem();
    	if(usuarioSelecionado == null) {
    		com.company.util.Metodos.msgInformacao("Selecione um usuário na Tabela", "");
    	}
    	
    	try {
			daou.deletar(usuarioSelecionado.getIn_pv_id_usuario());
			 btnConsultarOnActiion();
			 com.company.util.Metodos.msgInformacao("Sucesso", "Usuário Excluido");
			
		} catch (SQLException e) {
			com.company.util.Metodos.msgErro("Erro", "Falha ao Excluir o Usuario");
		}
    }


    @FXML
    void btnOnActionAtualizar() {
    	
    	try {
    	usuarioSelecionado.setSt_pv_nome_usuario(txtNome1.getText());
    	usuarioSelecionado.setSt_pv_senha_usuario(txtSenha1.getText());
    	usuarioSelecionado.setSt_pv_perfil_usuario(cb_perfil_usuario1.getValue().getSt_pv_perfil_usuario());
    	daou.atualizar(usuarioSelecionado);
    	 com.company.util.Metodos.msgInformacao("Sucesso", "Usuário Alterado");
    	 abas.getSelectionModel().select(consultar);
    	 btnConsultarOnActiion();
    	 atualizar.setDisable(true);
    	}catch(Exception e) {
    		com.company.util.Metodos.msgErro("Erro", "Falha na Alteração do Usuario");
    	}
    	
    }

	  //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";
        
        if (txtNome.getText() == null || txtNome.getText().length() < 0 || txtNome.getText().equals("") || txtNome.getText().trim().isEmpty()) {
            errorMessage += "Nome invalido!\n";
        }
        if (txtSenha.getText() == null || txtSenha.getText().length() < 0 || txtSenha.getText().equals("") || txtSenha.getText().trim().isEmpty()) {
            errorMessage += "Senha Invalida!\n";
        }
        if (cb_perfil_usuario.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Perfil invalido!\n";
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
combobox();
		cln_id_usuario.setCellValueFactory(new PropertyValueFactory<>("in_pv_id_usuario"));
		cln_nome_usuario.setCellValueFactory(new PropertyValueFactory<>("st_pv_nome_usuario"));
		cln_perfil_usuario.setCellValueFactory(new PropertyValueFactory<>("st_pv_perfil_usuario"));
		cln_senha_usuario.setCellValueFactory(new PropertyValueFactory<>("st_pv_senha_usuario"));
		
	}



}
