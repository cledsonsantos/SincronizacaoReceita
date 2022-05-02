package com.sicred.receita.service;

import java.io.File;
import java.util.List;

import com.sicred.receita.model.Conta;

/*
 * Interface para facilitar o processo utilizado no tratamento do aqruivo.
 */
public interface ProcessarArquivoCsvService {
	
	public void escreverArquivo(List<Conta> data, String path);
	public List<Conta> lerArquivo(File filePath);
	public List<Conta> validarArquivo(List<Conta> data);

}
