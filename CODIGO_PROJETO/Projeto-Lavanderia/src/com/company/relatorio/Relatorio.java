package com.company.relatorio;


import java.awt.Desktop;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import com.company.modelo.Pedido_servico;
import com.company.persistencia.Conexao;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorio {

	
	public static void gerarRelatorio() throws Exception {
		Conexao c = new Conexao();
		java.sql.Connection conn = c.getConexaoMySQL();
		
		JasperDesign jasperDesign;
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		String nomeNovoArquivo = "C:\\Users\\julio\\OneDrive\\Área de Trabalho\\eclipse-workspace\\Projeto-Lavanderia\\src\\clientes_lavanderia.jrxml";
		String local = "D:\\ArquivosSalvos\\Cliente Servidor Vicente\\arquivo.pdf";
		jasperDesign = JRXmlLoader.load(nomeNovoArquivo);
		jasperReport = JasperCompileManager.compileReport(jasperDesign);
		jasperPrint = JasperFillManager.fillReport(jasperReport, null,conn);
		JasperExportManager.exportReportToPdfFile(jasperPrint,local);
		
		File targetFile = new File(local);
		
		if(Desktop.isDesktopSupported()) {
			Desktop.getDesktop().open(targetFile);
		}else {
			System.out.println("erro");
		}
	}
	
	

	

}
