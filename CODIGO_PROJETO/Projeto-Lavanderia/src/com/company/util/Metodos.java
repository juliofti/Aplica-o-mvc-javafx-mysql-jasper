package com.company.util;

import java.util.Optional;

import javax.swing.text.html.Option;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Metodos {

public static void msgErro(String headtext, String contenttext) {
	Alert alert = new Alert(AlertType.ERROR);
	alert.setTitle("Erro");
	alert.setHeaderText(headtext);
	alert.setContentText(contenttext);
	alert.show();
}

public static void msgInformacao(String headtext, String contenttext) {
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Informação");
	alert.setHeaderText(headtext);
	alert.setContentText(contenttext);
	alert.show();
}

public static boolean msgConfirmacao(String confirmacao) {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Confirmação");
	alert.setContentText(confirmacao);
	Optional<ButtonType> opcao = alert.showAndWait(); //retorna um boolean

if(opcao.get() == ButtonType.OK) {
	return true;
}
return false;
}

}
