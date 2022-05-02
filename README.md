# Sincronização de arquivos CSV com a Receita Federal

<ul>
  <li>
    Tecnologias utilizadas:
  </li>
  <li>
    Java 8	
  </li>
  <li>
	Maven
  </li>
  <li>
	Spring Data - Lombok
  </li>
  <li>
	Opencsv 5.2	
  </li>
</ul>

 <ul>
 <li>
    Passos para execução:
  </li>
  
  <li>
    Crie um arquivo com extensão .csv igual o que consta na raíz do projeto --> "receita.csv"
  </li>
  
  
  <li>
    1. Entre na raiz do projeto
  </li>
  
  <li>
    2. Gere o arquivo .jar -->  mvnw package
  </li>
     
  <li>
    3. Execute:
  </li>
 
```json
  java -jar SincronizacaoReceita-0.0.1-SNAPSHOT.jar "caminho-arquivo-criado.csv"
```
  
  
  <li>
    Como resultado, será gerado outro arquivo,"receita_processado.csv", com uma coluna a mais.
  </li>
  
</ul>








