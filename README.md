# discourse
PARA EXECUTAR PROCESSO DE TESTE:
1 - Extrair o arquivo discourse.zip
2 - Imporar o arquivo extraído no eclipse como um maven projet: Import/Maven/Existing Maven Projets.
3 - No excluise executar o .java contido em discorse/src/teste/java/discourse/TesteDiscourse.java
4 - Clicar em Run

O fluxo execurado será esse:

1. Acessar a página (​https://www.discourse.org/​)  
2. Clicar na opção Demo disponível no menu principal  
3. Fazer scroll até o final da página  
4. Imprimir:  
a. A descrição de todos os tópicos fechados (são os que tem um cadeado ao lado esquerdo do título)  
b. Quantidade de itens de cada categoria e dos que não possui categoria  
c. O título do tópico que contém o maior número de views  
TECNOLOGIAS UTILIZADAS: ● Maven ● JUnit ● Selenium ● ChromeDriver ● ChromeDriverManager ● Java
