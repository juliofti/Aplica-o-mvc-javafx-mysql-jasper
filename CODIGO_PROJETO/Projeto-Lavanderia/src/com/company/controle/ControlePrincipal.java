package com.company.controle;


import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.company.dao.DaoUsuario;
import com.company.util.Linguage;
import com.company.util.PassarObjeto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControlePrincipal  implements Initializable{

	 	@FXML private Button btn_cliente;
	 	@FXML private Button btn_servico;
	    @FXML private Button btn_pedido;
	    @FXML private Button btn_roupa;
	    @FXML private MenuBar MbConfiguracao;
	    @FXML private MenuItem btncadastrarUsuario;
	    private DaoUsuario daou = new DaoUsuario();
	    private ResourceBundle bundle;
		private Locale locale;
		
	    @FXML void cadastrarCliente(ActionEvent event) throws Exception {
	    	Parent root = FXMLLoader.load(getClass().getResource("/com/company/visao/cadastro_cliente.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
	    }

	    @FXML void cadastrarPedido(ActionEvent event) throws Exception {
	    	Parent root = FXMLLoader.load(getClass().getResource("/com/company/visao/cadastro_pedido.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
	    }

	    @FXML void cadastrarServico(ActionEvent event) throws Exception {
	    	Parent root = FXMLLoader.load(getClass().getResource("/com/company/visao/cadastro_servico.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
	    }

	    @FXML  void cadastrarRoupa(ActionEvent event) throws Exception {
	    	Parent root = FXMLLoader.load(getClass().getResource("/com/company/visao/cadastro_tipo_roupa.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
	    }
	    
	    @FXML  void cadastrarUsuario(ActionEvent event) throws Exception {
	    	Parent root = FXMLLoader.load(getClass().getResource("/com/company/visao/cadastro_usuario.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
	    }

	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		PassarObjeto usuario  = new PassarObjeto();
		try {
			String retorno  = daou.buscarUsuario(usuario.getUsuario());
			if(retorno.equals("Administrador")) {
				btncadastrarUsuario.setDisable(false);
			}else {
				btncadastrarUsuario.setDisable(true);
						
					}
			
		} catch (SQLException e) {
		}
		
		carregarLinguage(Linguage.getLingua());
	}
	
	public void carregarLinguage(String lingua) {
		locale = new  Locale(lingua);
		bundle = ResourceBundle.getBundle("com.company.idioma.lang",locale);
		btn_cliente.setText(bundle.getString("btn_cliente"));
		btn_pedido.setText(bundle.getString("btn_pedido"));
		btn_roupa.setText(bundle.getString("btn_roupa"));
		btn_servico.setText(bundle.getString("btn_servico"));
	}
	
	
}
