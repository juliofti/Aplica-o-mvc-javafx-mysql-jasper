package com.company.controle;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import com.company.dao.DaoUsuario;
import com.company.util.Linguage;
import com.company.util.PassarObjeto;
import com.company.visao.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControleLogin implements Initializable {

	@FXML
	private TextField txtSenha;
	@FXML
	private TextField txtUsuario;
	@FXML
	private Button btn_cancelar;
	@FXML
	private Label lbl_lavanderia;
	@FXML
	private Button btn_entrar;
	@FXML
	private Label lbl_usuario;
	@FXML
	private Label lbl_senha;

	
	private ResourceBundle bundle;
	private Locale locale;

	 @FXML
	    void btn_pt(ActionEvent event) {
		 Linguage ling = new Linguage();
		 ling.setLingua("pt");
		 carregarLinguage("pt");
	    }

	    @FXML
	    void btn_en(ActionEvent event) {
	    	 Linguage ling = new Linguage();
	    	 ling.setLingua("en");
	    	carregarLinguage("en");
	    }
	
	@FXML
	void btnLogar(ActionEvent event) throws Exception {

		DaoUsuario dao = new DaoUsuario();
		boolean res = dao.checkLogin(txtUsuario.getText(), txtSenha.getText());

		if (res == true) {
			fechar();
			Linguage ling = new Linguage();
			PassarObjeto user = new PassarObjeto();
			user.setUsuario(txtUsuario.getText());
			
			Parent root = FXMLLoader.load(getClass().getResource("/com/company/visao/principal.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Sistema (JavaFX MVC)");
			stage.setResizable(false);
			stage.show();
		} else {
			com.company.util.Metodos.msgErro("Erro de Usuário",
					"O erro Ocorreu devido o Usuário / Senha estão Incorretos");
		}

	}

	@FXML
	void btnCancelar(ActionEvent event) {
		fechar();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		
	}

	public void fechar() {
		main.getStage().close();
	}

	public void carregarLinguage(String lingua) {
		locale = new  Locale(lingua);
		bundle = ResourceBundle.getBundle("com.company.idioma.lang",locale);
		lbl_lavanderia.setText(bundle.getString("lbl_lavanderia"));
		lbl_usuario.setText(bundle.getString("lbl_usuario"));
		lbl_senha.setText(bundle.getString("lbl_senha"));
		btn_entrar.setText(bundle.getString("btn_entrar"));
		btn_cancelar.setText(bundle.getString("btn_cancelar"));
	}
}
