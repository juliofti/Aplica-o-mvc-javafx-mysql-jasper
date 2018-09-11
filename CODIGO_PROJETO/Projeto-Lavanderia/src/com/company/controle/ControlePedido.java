package com.company.controle;


import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.company.dao.DaoCliente;
import com.company.dao.DaoItemPedido;
import com.company.dao.DaoPedido;
import com.company.dao.DaoRoupa;
import com.company.dao.DaoServico;
import com.company.modelo.Cliente;
import com.company.modelo.FiltroPesquisa;
import com.company.modelo.Itens_servico_roupa;
import com.company.relatorio.Relatorio;
import com.company.modelo.Pedido_servico;
import com.company.modelo.Roupa;
import com.company.modelo.Servico;
import com.company.util.ConversorData;
import com.company.util.PassarObjeto;

import groovy.ui.SystemOutputInterceptor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

public class ControlePedido implements Initializable{

	
    @FXML private Label lbl_cadastro_pedido;
    @FXML private Tab consultar_pedido;
    @FXML private Label lbl_filtro; 
    @FXML private ComboBox<FiltroPesquisa> cbfiltropedido;
    @FXML private TextField txtConsulta;
    @FXML private Button btn_consultar_pedido;
    @FXML private TableView<Pedido_servico> tb_pedido;
    @FXML private TableColumn<Pedido_servico, Integer> cln_n_pedido;
    @FXML private TableColumn<Pedido_servico, String> cln_cliente_pedido2;
    @FXML private TableColumn<Pedido_servico, String> cln_roupa_pedido2;
    @FXML private TableColumn<Pedido_servico, String> cln_servico_pedido2;
    @FXML private TableColumn<Pedido_servico, Date> cln_data_pedido2;
    @FXML private TableColumn<Pedido_servico, Double> cln_valor_pedido2;
    @FXML private TableColumn<Pedido_servico, Double> cln_pedido_situacao;	
    @FXML private Button btn_inserir_pedido;
    @FXML private Button btn_alterar_pedido2;
    @FXML private Button btn_relatorio_pedido;
  

//-------------------------------------------------------------------------------
    @FXML private Label lbl_cliente_pedido;
    @FXML private Tab cadastrar_pedido;
    @FXML private ComboBox<Cliente>cb_pedido_cliente;
    @FXML private Label lbl_roupa_pedido;
    @FXML private ComboBox<Roupa> cb_pedido_roupa;
    @FXML private Label lbl_servico_pedido; 
    @FXML private ComboBox <Servico>cb_pedido_servico;
    @FXML private Label lbl_quantidade_pedido;
    @FXML private TextField txtquantidade;
    @FXML private Label lbl_data_pedido;
    @FXML  private DatePicker data_cadastrar;
    @FXML private CheckBox check_pg;
    @FXML  private TableView<Itens_servico_roupa> tb_item;
    @FXML private TableColumn<Itens_servico_roupa, Integer> cln_id_pedido;
    @FXML private TableColumn<Itens_servico_roupa, String> cln_roupa_pedido;
    @FXML private TableColumn<Itens_servico_roupa, String> cln_servico_pedido;
    @FXML private TableColumn<Itens_servico_roupa, Double> cln_valor_pedido;
    @FXML private TableColumn<Itens_servico_roupa, Integer> cln_quantidade_pedido;
    @FXML private Button btn_add;
    @FXML private Button btn_dell;
    @FXML private Label lbl_total_pedido;
    @FXML private Label preco;
    @FXML private Button btn_cadastrar_pedido;
    @FXML private TextField txtClientePedido;
 
    
//------------------------------------------------------------------------------------
    @FXML private Tab atualizar_pedido;
    @FXML private Label lbl_cliente_pedido1;
    @FXML private ComboBox<Cliente> cb_pedido_cliente1;
    @FXML private Label lbl_roupa_pedido1;
    @FXML private ComboBox <Roupa>cb_pedido_roupa1;
    @FXML private Label lbl_servico_pedido1;
    @FXML private ComboBox <Servico>cb_pedido_servico1;
    @FXML private Label lbl_quantidade_pedido1;
    @FXML private TextField txtquantidade1;
    @FXML private Label lbl_data_pedido1;
    @FXML  private DatePicker data_atualizar;
    @FXML private CheckBox checkpg1;
    @FXML private TableView<Itens_servico_roupa> tb_item1;
    @FXML private TableColumn<Itens_servico_roupa,Integer>cln_n_pedido1;
    @FXML private TableColumn<Itens_servico_roupa, String> cln_roupa_pedido1;
    @FXML private TableColumn<Itens_servico_roupa, String> cln_servico_pedido1;
    @FXML private TableColumn<Itens_servico_roupa, Integer> cln_quantidade_pedido1;
    @FXML private TableColumn<Itens_servico_roupa, Double> cln_valor_pedido1;
    @FXML private Button btn_add1;
    @FXML private Button btn_dell1;
    @FXML private Label lbl_total_pedido1;
    @FXML private Label preco1;
    @FXML private Button btn_atualizar_pedido1;
//-------------------------------------------------------------------------------------
    @FXML private TabPane abas;
    @FXML  private AnchorPane anchor;
    private ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
    private ArrayList<Roupa> listaRoupa = new ArrayList<Roupa>();
    private ArrayList<Servico> listaServico = new ArrayList<Servico>();
   private  ArrayList<FiltroPesquisa> lista = new ArrayList<FiltroPesquisa>();
    private ArrayList<Itens_servico_roupa> listaItens = new ArrayList<Itens_servico_roupa>();
   private ArrayList<Pedido_servico> listaPedido;
    private DaoCliente daoc = new DaoCliente();
    private DaoRoupa daor = new DaoRoupa();
    private DaoServico daos = new DaoServico();
    private DaoPedido daop = new DaoPedido();
    private DaoItemPedido daoi = new DaoItemPedido();
    private ObservableList<Cliente> obsCliente = null;
    private ObservableList<Roupa> obsRoupa = null;
    private ObservableList<Servico> obsServico = null;
    private ObservableList<FiltroPesquisa> obsFiltro = null;
    private ObservableList<Itens_servico_roupa> obsItensPedido;
    private ObservableList<Pedido_servico> obsPedido;
    private Pedido_servico pedidoSelecionado = null;
    private Itens_servico_roupa itemSelecionado = null;
    int i = 0;

	

	@FXML
    void gerenciarAbas() throws Exception{
    	if (consultar_pedido.isSelected() ) {
			atualizar_pedido.setDisable(true);
			cadastrar_pedido.setDisable(true);
			tb_pedido.setItems(null);
			limparCadastro();
    	}if(atualizar_pedido.isSelected()) {
    		cadastrar_pedido.setDisable(true);
    	}
		

    }

    @FXML
    void btnConsultarOnActiion(ActionEvent event) throws Exception {
    	DaoPedido daopedido = new DaoPedido();
    	FiltroPesquisa filtro = new FiltroPesquisa();
    	if(cbfiltropedido.getSelectionModel().getSelectedItem() == null){
    		filtro.setSt_filtro("cliente.nome");
    		filtro.setNome(txtConsulta.getText());
    		listaPedido = daopedido.listaFiltro(filtro.getSt_filtro(), filtro.getNome());
    		
    		if(listaPedido.size() == 0) {
    			com.company.util.Metodos.msgInformacao("Não existe Pedido Cadastrado", null);
    		}
    		carregarTableViewPedido(listaPedido);
    	}
    	else if(cbfiltropedido.getSelectionModel().getSelectedItem().getSt_filtro().equals("Nome de Cliente")){
    		filtro.setSt_filtro("cliente.nome");
    		filtro.setNome(txtConsulta.getText());
    		listaPedido = daopedido.listaFiltro(filtro.getSt_filtro(), filtro.getNome());
    		if(listaPedido.size() == 0) {
    			com.company.util.Metodos.msgInformacao("Não existe Pedido Cadastrado", null);
    		}
    		carregarTableViewPedido(listaPedido);
    		
    	}else if(cbfiltropedido.getSelectionModel().getSelectedItem().getSt_filtro().equals("Tipo de Serviço")) {
    		filtro.setSt_filtro("servico.descricao_servico");
    		filtro.setNome(txtConsulta.getText());
    		listaPedido = daopedido.listaFiltro(filtro.getSt_filtro(), filtro.getNome());
    		if(listaPedido.size() == 0) {
    			com.company.util.Metodos.msgInformacao("Não existe Pedido Cadastrado", null);
    		}
    		carregarTableViewPedido(listaPedido);
    		
    	}else if(cbfiltropedido.getSelectionModel().getSelectedItem().getSt_filtro().equals("Tipo de Roupa")) {
    		filtro.setSt_filtro("tipo_roupa.descricao");
    		filtro.setNome(txtConsulta.getText());
    		listaPedido = daopedido.listaFiltro(filtro.getSt_filtro(), filtro.getNome());
    		if(listaPedido.size() == 0) {
    			com.company.util.Metodos.msgInformacao("Não existe Pedido Cadastrado", null);
    		}
    		carregarTableViewPedido(listaPedido);
    	
    	}
    
    	
    	
    }

    @FXML
    void InserirPedido() {
    	cadastrar_pedido.setDisable(false); // assim que pressionar butão inserir desabilitar cadastro
    	abas.getSelectionModel().select(cadastrar_pedido);// # ABRIR ABA DE CADASTRO DE ITENS
    	
    }

    @FXML
    void abrirAbaAtualizacao(ActionEvent event) throws Exception {
    	colunaAtualizacao();
    	carregarComboBoxAtualizacao();
    	pedidoSelecionado = tb_pedido.getSelectionModel().getSelectedItem();
    
    	if(pedidoSelecionado == null) {
    		com.company.util.Metodos.msgErro("Erro", "Não há pedido selecionado");
    		}else {
    			atualizar_pedido.setDisable(false); // # ASSIM QUE UM CLIENTE FOR SELECIONADO A OPÇÃO ATUALIZAR E ATIVADA#
    			abas.getSelectionModel().select(atualizar_pedido);// # ABRIR ABA DE ATUALIZAÇÃO
    			cb_pedido_cliente1.setValue(pedidoSelecionado.getObj_cliente());
    			cb_pedido_roupa1.setValue(pedidoSelecionado.getObj_roupa());
    			cb_pedido_servico1.setValue(pedidoSelecionado.getObj_servico());
    			if(pedidoSelecionado.getIn_pv_pago() == 1) {
    				checkpg1.setSelected(true);	
    			}else {
    				checkpg1.setSelected(false);	
    			}
    			listaItens = daoi.listaPorPedido(pedidoSelecionado);
    			obsItensPedido = FXCollections.observableArrayList(listaItens);
    			tb_item1.setItems(obsItensPedido);
    			data_atualizar.setValue(ConversorData.converter(pedidoSelecionado.getDt_pv_data()));
    		
    			preco1.setText(String.format("%.2f",calculaValorServico(listaItens)));
    			checkpg1.getText();
    		}
    	
    }
    
    @FXML
    void Add_OnAction() {
    
    	try {
    		 itemSelecionado = tb_item1.getSelectionModel().getSelectedItem();
    		 
    	}catch(Exception e) {
    		com.company.util.Metodos.msgErro("Erro", "Selecione o item");
    	}
    }
    
    @FXML
    void Dell_OnAction() {
    	
    }
    
   
    @FXML  
    void onAction_btn_add() {
    	
    	if(cb_pedido_cliente.getSelectionModel().getSelectedItem() != null) {
    		cb_pedido_cliente.setDisable(true);
    	}
    	
    	try {
    	txtClientePedido.setText(cb_pedido_cliente.getSelectionModel().getSelectedItem().getSt_pv_nome());
    	Itens_servico_roupa itens = new Itens_servico_roupa();	
    	i=i+1;
    	itens.setIn_pv_id_item_servico(i);
    	itens.setObj_pv_roupa(cb_pedido_roupa.getSelectionModel().getSelectedItem());
    	itens.setObj_pv_servico(cb_pedido_servico.getSelectionModel().getSelectedItem());
    	int quantidade = Integer.parseInt(txtquantidade.getText());
    	itens.setIn_pv_quantidade(quantidade);
    	while(quantidade <= 0 ) {
    		com.company.util.Metodos.msgErro("Erro", "Campo quantidade não pode ser Vazio!!");
    	}
    	
    	itens.setDb_pv_valorUnt((cb_pedido_servico.getSelectionModel().getSelectedItem().getDb_pv_valor_servico()));
    	itens.setDt_pv_data(ConversorData.converter(data_cadastrar.getValue()));
    	listaItens.add(itens);
    
    	preco.setText(String.format("%.2f",calculaValorServico(listaItens)));
       	obsItensPedido = FXCollections.observableArrayList(listaItens);
    	tb_item.setItems(obsItensPedido);
    	}catch(Exception e) {
    		com.company.util.Metodos.msgErro("Erro", "Selecione os Campos Obrigatorios!!!");
    	}   	
 
    }
    
    
    
    @FXML
    void onActionbtn_dell() {
  
    try {
    	for(int i =0; i < listaItens.size(); i++) {
    		if(listaItens.get(i).getIn_pv_id_item_servico() == tb_item.getSelectionModel().getSelectedItem().getIn_pv_id_item_servico()) {
    			listaItens.remove(i);
    		}
    	}
    	preco.setText(String.format("%.2f",calculaValorServico(listaItens)));
    	obsItensPedido = FXCollections.observableArrayList(listaItens);
    	tb_item.setItems(obsItensPedido);
 
      	if(preco.getText().equals("0,00")) {
    		cb_pedido_cliente.setDisable(false);
    	}
    }catch(Exception e) {
    	com.company.util.Metodos.msgErro("Erro", "Selecione o item que deseja Excluir");
    }
    }
    
    @FXML
    void RelatorioOnAction()  {
    
    }


    @FXML
    void btnOnActionCadastrar(ActionEvent event) throws Exception {
    	Pedido_servico pedido = new Pedido_servico();
    	Cliente cliente = new Cliente();
    	
    	try {
    		
    		if(listaItens.size() == 0 || cb_pedido_roupa.getSelectionModel().getSelectedItem() == null || cb_pedido_servico.getSelectionModel().getSelectedItem() == null || txtquantidade.getText().equals("")) {
        		com.company.util.Metodos.msgErro("Campos nulos", "Favor preencha e adicione a Tabela");
        		
        	}else {
    	cliente = cb_pedido_cliente.getSelectionModel().getSelectedItem();
    	cliente = daoc.buscar(cliente);
    	pedido.setObj_cliente(cliente);
    	pedido.setDt_pv_data(Date.valueOf(data_cadastrar.getValue()));
    	pedido.setDb_pv_valor_total(calculaValorServico(listaItens));
    	if(check_pg.isSelected()) {
    		pedido.setIn_pv_pago(1);
    	}else {
    		pedido.setIn_pv_pago(0);
    	}
    	pedido.setLt_pv_listaItens(listaItens);
    
    		daop.inserir(pedido);
    	for(int i = 0; i < listaItens.size(); i++) {
    		pedido = daop.buscarUltimoPedido();
    		Itens_servico_roupa itens = new Itens_servico_roupa();
    		itens.setIn_pv_quantidade(listaItens.get(i).getIn_pv_quantidade());
    		itens.setDb_pv_valorUnt(listaItens.get(i).getDb_pv_valorUnt());
    		itens.setObj_pv_roupa(listaItens.get(i).getObj_pv_roupa());
    		itens.setObj_pv_servico(listaItens.get(i).getObj_pv_servico());
    		itens.setObj_pv_pedido(pedido);
    		daoi.inserir(itens);
    	}
    	com.company.util.Metodos.msgInformacao("Cadastrado com Sucesso", null);
		limparCadastro();}
    	}catch(Exception e) {
    		com.company.util.Metodos.msgErro("Falha no Cadastro", "Campos nulos");
    	}
    }
    
    
    public void limparCadastro() throws Exception {
    	cb_pedido_cliente.setDisable(false);
    	cb_pedido_cliente.setItems(null);
    	cb_pedido_roupa.setItems(null);
    	cb_pedido_servico.setItems(null);
    	txtquantidade.setText("");
    	check_pg.setSelected(false);;
    	txtClientePedido.setText(null);
    	tb_item.setItems(null);
    	listaItens = new ArrayList<>();
    	preco.setText("0,00");
    	carregarComboBoxCliente();
    	 carregarComboBoxRoupa();
    }

 public Double calculaValorServico(ArrayList<Itens_servico_roupa> listaRoupa) {
	 double retorno = 0;
	 for(int i = 0; i < listaRoupa.size(); i++) {
		retorno += listaRoupa.get(i).getDb_pv_valorUnt()*listaRoupa.get(i).getIn_pv_quantidade();
	 }

	 return retorno;
 }

    @FXML
    void btnOnActionAtualizar() {
    	
    }
    public void carregarComboBoxCliente() throws SQLException {
        listaCliente = daoc.consultar("") ;
        obsCliente = FXCollections.observableArrayList(listaCliente);
        cb_pedido_cliente.setItems(obsCliente);
   }
    public void carregarComboBoxRoupa() throws Exception {
        listaRoupa = daor.consultar("") ;
        obsRoupa = FXCollections.observableArrayList(listaRoupa);
        cb_pedido_roupa.setItems(obsRoupa);
    }
    public void carregarComboBoxServico(String descricao) throws Exception {
        listaServico = daos.consultar(descricao) ;
        obsServico = FXCollections.observableArrayList(listaServico);
        cb_pedido_servico.setItems(obsServico);
    }
    
    public void carregarComboBoxAtualizacao() {
    	cb_pedido_cliente1.setItems(obsCliente);
    	cb_pedido_roupa1.setItems(obsRoupa);
    	cb_pedido_servico1.setItems(obsServico);
    }
    
  @FXML
	void  btnBuscar() throws Exception {
	  String descricao = cb_pedido_roupa.getSelectionModel().getSelectedItem().getSt_pv_descricao();
	  carregarComboBoxServico(descricao);
	 cb_pedido_roupa.setDisable(true);
}
  @FXML
 	void  AbilitarOnAction() {
	  cb_pedido_roupa.setDisable(false);
	 cb_pedido_servico.setItems(null);
  }
	public void carregarFiltro()  {
		FiltroPesquisa filtro = new FiltroPesquisa("Nome de Cliente");
		FiltroPesquisa filtro1 = new FiltroPesquisa("Tipo de Serviço");
		FiltroPesquisa filtro2 = new FiltroPesquisa("Tipo de Roupa");
		lista.add(filtro);
		lista.add(filtro1);
		lista.add(filtro2);
		obsFiltro = FXCollections.observableArrayList(lista);
		cbfiltropedido.setItems(obsFiltro);
		}

	

	@FXML 
	void VisualizarOnAction() throws Exception {
		pedidoSelecionado = tb_pedido.getSelectionModel().getSelectedItem();
	    
    	if(pedidoSelecionado == null) {
    		com.company.util.Metodos.msgInformacao("Favor", "Selecione um Pedido para Visualizar");
    		}else {
    				PassarObjeto po = new PassarObjeto();
    				po.setPedido(pedidoSelecionado);
    		    	Parent root = FXMLLoader.load(getClass().getResource("/com/company/visao/Visualizar_itens.fxml"));
    				Scene scene = new Scene(root);
    				Stage stage = new Stage();
    				stage.setScene(scene);
    				stage.setResizable(false);
    				stage.initModality(Modality.APPLICATION_MODAL);
    				stage.show();
    		    }
    			
    		}
	public void carregarTableViewPedido(ArrayList<Pedido_servico> listaFiltro) throws Exception {
		cln_n_pedido.setCellValueFactory(new PropertyValueFactory<>("in_pv_n_pedido"));
		cln_cliente_pedido2.setCellValueFactory( new PropertyValueFactory<>("obj_cliente"));
		cln_roupa_pedido2.setCellValueFactory(new PropertyValueFactory<>("obj_roupa"));
		cln_servico_pedido2.setCellValueFactory(new PropertyValueFactory<>("obj_servico"));
		cln_data_pedido2.setCellValueFactory( new PropertyValueFactory<>("dt_pv_data"));
		cln_valor_pedido2.setCellValueFactory(new PropertyValueFactory<>("db_pv_valor_total"));

		obsPedido = FXCollections.observableArrayList(listaFiltro);
		tb_pedido.setItems(obsPedido);
		
	}
	
	public void colunaAtualizacao() {
	    cln_n_pedido1.setCellValueFactory(new PropertyValueFactory<>("in_pv_id_item_servico"));
	     cln_roupa_pedido1.setCellValueFactory(new PropertyValueFactory<>("obj_pv_roupa"));
	     cln_servico_pedido1.setCellValueFactory(new PropertyValueFactory<>("obj_pv_servico"));
	     cln_quantidade_pedido1.setCellValueFactory(new PropertyValueFactory<>("in_pv_quantidade"));
	     cln_valor_pedido1.setCellValueFactory(new PropertyValueFactory<>("db_pv_valorUnt"));
	}
	

	@FXML 
	void btnExcluirOnAction() throws Exception  {
		pedidoSelecionado =tb_pedido.getSelectionModel().getSelectedItem();
		if(pedidoSelecionado == null) {
			com.company.util.Metodos.msgInformacao("Favor", "Selecione o pedido que deseja excluir");
		}
		if(com.company.util.Metodos.msgConfirmacao("Deseja Excluir o Pedido e seus Itens") == true) {
		try {
			daoi.deletar(pedidoSelecionado.getIn_pv_n_pedido());
			daop.deletar(pedidoSelecionado.getIn_pv_n_pedido());
			com.company.util.Metodos.msgInformacao("Excluido com Sucesso",null);
			 obsPedido= FXCollections.observableArrayList(daop.listaCompleta());
			tb_pedido.setItems(obsPedido);
		} catch (SQLException e) {
			com.company.util.Metodos.msgErro("Erro", "Falha na exclussão do pedido");
		}
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		data_cadastrar.setValue(LocalDate.now());
		
		cln_id_pedido.setCellValueFactory(new PropertyValueFactory<>("in_pv_id_item_servico"));
	    cln_roupa_pedido.setCellValueFactory(new PropertyValueFactory<>("obj_pv_roupa"));
	    cln_servico_pedido.setCellValueFactory(new PropertyValueFactory<>("obj_pv_servico"));
	    cln_valor_pedido.setCellValueFactory(new PropertyValueFactory<>("db_pv_valorUnt"));
	    cln_quantidade_pedido.setCellValueFactory(new PropertyValueFactory<>("in_pv_quantidade"));
	    cln_pedido_situacao.setCellValueFactory(new PropertyValueFactory<>("in_pv_pago"));

	    
		try {
			carregarComboBoxCliente();
			carregarComboBoxRoupa();
			carregarFiltro();
		} catch (Exception e) {
		
		}
		
	}


}