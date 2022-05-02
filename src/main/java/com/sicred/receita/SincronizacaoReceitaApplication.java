package com.sicred.receita;

import java.io.File;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sicred.receita.model.Conta;
import com.sicred.receita.service.ProcessarServiceImpl;
import com.sicred.receita.service.ReceitaService;

@SpringBootApplication
public class SincronizacaoReceitaApplication {

	public static void main(String[] args) {
		
		String caminhoArquivo 			= args[0];
		ProcessarServiceImpl processar 	= new ProcessarServiceImpl(new ReceitaService());
		List<Conta> readData 			= processar.lerArquivo(new File(caminhoArquivo));
		List<Conta> validacao 			= processar.validarArquivo(readData);
		processar.escreverArquivo(validacao, caminhoArquivo);
		
	}

}
