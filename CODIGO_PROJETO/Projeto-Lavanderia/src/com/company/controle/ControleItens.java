package com.company.controle;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.company.dao.DaoItemPedido;
import com.company.modelo.Itens_servico_roupa;
import com.company.modelo.Pedido_servico;
import com.company.util.PassarObjeto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ControleItens implements Initializable{

    @FXML private TextField txtPedido;
    @FXML private TableColumn<Itens_servico_roupa, Integer> cln_id_itens;
    @FXML private Label lbl_cliente_itens;
    @FXML private TableColumn<Itens_servico_roupa, Integer> cln_quantidade;
    @FXML private AnchorPane anchor;
    @FXML private TableView<Itens_servico_roupa> tb_itens;
    @FXML private TableColumn<Itens_servico_roupa, String> cln_tipo_servico;
    @FXML private TableColumn<Itens_servico_roupa, Double> cln_valorUn;
    @FXML private TextField txtCliente;
    @FXML private TableColumn<Itens_servico_roupa, String> cln_tipo_roupa;
    @FXML private Label lbl_pedido_itens;
    @FXML private Label lbl_data_itens;
    @FXML private TextField txtSituacao;
    @FXML private Label lbl_situacao_itens;
    @FXML private TextField txtDataItens;
    
    private Pedido_servico pedido = null;
    private ObservableList<Itens_servico_roupa> obsItens = null;
	private ArrayList<Itens_servico_roupa> listaItens = new ArrayList<Itens_servico_roupa>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
				PassarObjeto po = new PassarObjeto();
				txtPedido.setText(String.valueOf(po.getPedido().getIn_pv_n_pedido()));
				txtCliente.setText(String.valueOf(po.getPedido().getObj_cliente().getSt_pv_nome()));
				 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
				 String data = f.format(po.getPedido().getDt_pv_data());
				txtDataItens.setText(data);
				String situacao = null;
				if(po.getPedido().getIn_pv_pago() == 1 ) {
					situacao = "Pago";
				}else if(po.getPedido().getIn_pv_pago() == 0) {
					situacao = "Falta Pagar";
				}
				txtSituacao.setText(situacao);
				
				pedido = po.getPedido();
				DaoItemPedido daoi = new DaoItemPedido();
				try {
				listaItens = 	daoi.listaPorPedido(pedido);
				
				obsItens = FXCollections.observableArrayList(listaItens);
				tb_itens.setItems(obsItens);
				} catch (SQLException e) {
				}
				cln_id_itens.setCellValueFactory(new PropertyValueFactory<>("in_pv_id_item_servico"));
				cln_tipo_roupa.setCellValueFactory(new PropertyValueFactory<>("obj_pv_roupa"));
				cln_tipo_servico.setCellValueFactory(new PropertyValueFactory<>("obj_pv_servico"));
				cln_quantidade.setCellValueFactory(new PropertyValueFactory<>("in_pv_quantidade"));
				cln_valorUn.setCellValueFactory(new PropertyValueFactory<>("db_pv_valorUnt"));
			
	}

	
	
}
