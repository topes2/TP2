
# <p style="text-align: center;"> Programação II</p>
## <p style="text-align: center;"> Relatorio do Trabalho Prático 2022/23</p>
## <p style="text-align: center;">Feito por: <br> André Banha 52792 <br> Diogo Rodrigues 52164</p>



### Descrição Geral

Foi nos pedido a criação de uma máquina distribuidora de produtos, como as encontradas nos espaços publicos.
Em termos gerais a máquina tem de conseguir lidar com:
- produtos;
- dinheiro.



### Descrição Específica

Em termos mais específicos o programa tem de conseguir lidar com os seguintes objetivos:
- stock de produtos;
- preço de cada produto;
- produtos ditos perishable e nonperishable;
- dinheiro dentro da máquina;
- dinheiro dado pelo user para o pagamento;
- devolver o troco para o user caso a compra aconteça ou não;


Cada produto pode ser perishable ou nonperishable, um produto nonperishable não irá ter uma data de limite porem irá implementar o aspeto de volume. Enquanto o perishable lida com o ‘interface’ freshness e com o aspeto de data limite. 


### Classes criadas
  Como uma penquena nota antes da explicação das classes, para cumprir o requerimento do professor apenas vamos explicar as funções mais importantes ou nos pareçam mais complicadas de perceber a ler o codigo.

- CoinBuffer
  - Nós criamos esta função para lidar com o nosso cenario de utilização onde o user insere moedas, acreditamos que a forma mais eficiente e menos complicada para chegar a este objetivo.
```
   AddCoin
   A funcao recebe um valor float e um valor int, sendo o float o valor da moeda que foi inserida,
   e o int é a quantidade de vezes que foi inserida.
   Antes de acabar a excução calcula o total de moedas para a variavel saldo.   
```  

```
  Reset
  A função apenas esvazia o array de moedas e retorna o valor do saldo a 0.
```

- ElementarMachine
  - A superclass para as subclasses ProductMachine e MoneyMachine, inclui a parte comum a ambas máquinas e necessárias para o funcionamento das várias. O construtor inicia um arraylist de tipo T.
```
  addThings
  A função que é utilizada quando é preciso addicionar algum tipo de input para os arrays de cada máquina
  seja um float no caso da MoneyMachine ou um Product para a ProductMachine. Recebe um int n, a quantidade para adicionar
  ,e um objeto tipo T.
```
```
  removeOneThing
  A função para remover um objeto ou diminuir o contador desse objeto dentro do array da maquina
```

- Product
  - Uma superclass para as classes de Perishable e NonPerishable contem as partes comums e basicas para ambas classes.


- NonPerishable
  - Uma subclass de Product que tem como variavel de instancia `volume` 
```
  getVolume
  Um função que devolve o volume de um objeto NonPerishable
```
- Perishable
  - Uma subclass de Product que tem como variaveis de instância `limitDate` e `dayAdded`. O construtor recebe o nome, o preco e a data de criação.
```
  isFromToday
  Esta função devolve um valor boolean da operação de comparação da data guardada no objeto e a data que o programa esta a correr.
```
```
  isOutDated
  Esta função devolve um valor boolean da operação de comparação da data guardada no objeto como data limte e a data do dia que o programa esta a correr.
```
- MoneyMachine
  - Uma subclass da class ElementarMachine, o seu objetivo é lidar com a parte do dinheiro e do pagamento do programa.
```
  MoneyToCollect
   Esta função devolve o valor que é possivel coleta no modo de admin.
```
```
  giveChange
  Esta função recebe o custo de um objeto e com base nas moedas disponiveis na maquina calcula o troco no
  menor numero de moedas possivel.
```

- ProductMachine
- Uma subclass da class ElementarMachine cujo objetivo é lidar com a gestao dos produtos e a sua dispensao, ordenação e venda.
```
  listForClient
  Uma função cujo unico objetivo é apenas criar um output mais user friendly e esteticamente agradavel.
```
```
  listAllOrdered
  Uma função que chama ao sortProducts e o ListAll para fazer output de uma lista organizada.
```
```
  sortProducts
  Uma função que implementa o bubble sort para fazer sort dos produtos da lista dada de produtos.
```
- VendingMachine
  - Utilizando ambas MoneyMachine e ProductMachine cria se a VendingMachine que vai ser a função que o user vai ter contacto com.
```
  saveMachine
  Guarda a VendingMachine para um ficheiro com o nome dado pelo o user.
```
```
  restoreMachine
  Lê o ficheiro especificado pelo user e lê a MoneyMachine,ProductMachine e respetivos arrays guardados 
  nesse mesmo ficheiro.
```
```
  adminMode
  Tomamos alguma liberdade creativa para poder aproximar este programa mais perto da realidade com um 
  modo de admin, onde se tem acesso a adicionar produtos, ver quanto dinheiro há na maquina
  para se adicionar mais dinheiro.
```

```
  workinkLoop
  Onde juntamos todas as "peças" da nossa Vending Machine de forma a recebermos o pedido do cliente e 
  a acedermos ás restantes funcionalidades da Vendig Machine como o adminMode e as funcionalidades de 
  guardar e restaurar a máquina com ficheiros.
  O nosso objetivo foi para criar, com base nos requisitos do decente, a maquina de distribuição mais realista que
  conseguimos.
  Devolve um Bollean para podermos terminhar a execução do main a partir de comandos
```
- Main
  - Onde iniciamos a Vending Machinhe e também o que trata da chamada das funções de acesso a ficheiros
  


