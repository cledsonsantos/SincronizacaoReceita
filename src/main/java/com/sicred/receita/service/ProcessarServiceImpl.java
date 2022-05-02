package com.sicred.receita.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.sicred.receita.model.Conta;
import com.sicred.receita.model.ResultadoReceita;


/*
 * Classe responsável na implementação dos métodos ja interface
 */

public class ProcessarServiceImpl implements ProcessarArquivoCsvService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessarServiceImpl.class);
    
    private ReceitaService receitaService;

    public ProcessarServiceImpl() {
    }

    public ProcessarServiceImpl(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }
    
	@Override
	public void escreverArquivo(List<Conta> data, String path) {
		 LOGGER.info("Escrevendo o arquivo: {} ", path);
		  
		String arquivo = path.replace(".csv", "_processado.csv");
	        
	        try {
	            FileWriter fileWriter = new FileWriter(arquivo);
	            CSVWriter csvWriter = new CSVWriter(fileWriter, ';', 
	            		CSVWriter.NO_ESCAPE_CHARACTER, 
	            		CSVWriter.DEFAULT_ESCAPE_CHARACTER, 
	            		CSVWriter.DEFAULT_LINE_END);

	            String[] header = {"agencia", "conta", "saldo", "status", "resultado"};
	            List<String[]> dados = new ArrayList<>();
	            dados.add(header);

	            data.forEach(reg -> {
	                String[] item = {reg.getAgencia(), 
	                		reg.getConta(), 
	                		String.valueOf(reg.getSaldo()), 
	                		reg.getStatus(), 
	                		reg.getResultado()};
	                
	                dados.add(item);
	            });

	            csvWriter.writeAll(dados);
	            
	            LOGGER.info("Processamento enviado com sucesso. Foi gravado no arquivo novo: {} ", arquivo);
	            csvWriter.close();
	            fileWriter.close();
	            
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            e.printStackTrace();
	        }		
	}

	@Override
	public List<Conta> lerArquivo(File filePath) {
 		 try {
  			    LOGGER.info("Lendo o aquivo: {} ", filePath);
			 	
	            CSVParser parser 	= new CSVParserBuilder().withSeparator(';').build();
	            CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath)).withCSVParser(parser).build();
	            List<Conta> lines 	= new ArrayList<>();

	            String[] cols 		= null;
	            csvReader.readNext();
	            
	            while ((cols = csvReader.readNext()) != null) {
	                Conta fields = new Conta(cols);
	                lines.add(fields);
	            }

	            csvReader.close();
	            return lines;
	            
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            e.printStackTrace();
	        }
	        return null;
	}

	
	@Override
	public List<Conta> validarArquivo(List<Conta> data) {
		 LOGGER.info("Validando os dados do arquivo.");
		 
		  data.forEach(cols -> {
		        try {
		            boolean resultado = receitaService.atualizarConta(
		            		cols.getAgencia(), 
		            		cols.getConta(),
		            		cols.getSaldo(), 
		            		cols.getStatus());
		            
		            		if(resultado) {
		            			cols.setResultado(String.valueOf(ResultadoReceita.ENVIADO));
		            		} else {
		            			cols.setResultado(String.valueOf(ResultadoReceita.REJEITADO));
		            		}
		            		
		        } catch (InterruptedException e) {
		            LOGGER.error(e.getMessage());
		            e.printStackTrace();
		        }
		    });
		
		    return data;
		}
   
}