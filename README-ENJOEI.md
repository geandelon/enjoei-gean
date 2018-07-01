## objetivo

O objetivo deste teste é verificar os conhecimentos dos candidatos a vaga de [programador android](https://jobs.kenoby.com/enjoei/job/android-developer/5ab558c1985d163e33515c68) no [enjoei :P](https://www.enjoei.com.br/)

## o que deve ser feito

*criar um aplicativo Android de listagem/detalhe de produtos.*

este aplicativo deverá:
* reproduzir a interface definida no layout fornecido
* consumir a api para apresentar a listagem
* tratar loading e erro de conexão
* clique no item da lista deve ir para a tela de detalhe

os items acima são o sufiente para analisarmos se o candidato se encaixa no que estamos esperando; porém, se você quiser demonstrar mais do seu conhecimento e implementar outras funcionalidades, fique a vontade.

algumas sugestões: 
* ter a tabbar funcional (o primeiro item mostra a lista de produtos, os demais mostram uma label do item)
* scroll horizontal das fotos na tela de detalhe
* adicionar pull to refresh para atualizar os dados da lista
* inserir paginação automática no fim da lista
* salvar produtos em um banco de dados e recuperá-los em caso de falha de conexão
* testes unitários e de interface

e lembre-se: **tudo que for enviado será avaliado**

## onde encontrar

### interface

todo o material necessário para reproduzir as telas pode ser encontrado na pasta *./design/*, **a correta aplicação da interface é fundamental**, portanto recomendamos que utilize o arquivo *./design/android-test-2017.sketch** para verificar dimensões, bordas, cores, estilos e tamanhos corretos.

<sub>*o Sketch é um app pago, mas tem versão trial em https://www.sketchapp.com <sub>

### api

a documentação é encontrada em: 
 http://docs.enjoeitest.apiary.io/#reference/. 

### imagens

as fotos dos produtos estão referenciadas no JSON como objetos do cloudinary, a documentação necessária para renderizar estas imagens esta disponivel em: http://cloudinary.com/documentation/image_transformations.

ex: 
```javascript
{
	"public_id": "sample",
	"crop": "fill",
	"gravity": "auto"
}
```
    
url da imagem (150x200): http://res.cloudinary.com/demo/image/upload/c_fill,g_auto,w_150,h_200/sample.jpg

## instruções de envio

enviar para anndressa@enjoei.com.br:
* assunto "Teste programador Android - Nome do candidato":
* zip do código fonte ou compartilhar repositório com @anndressa ( [github](https://github.com/anndressa), [bitbucket](https://bitbucket.org/anndressa/), [gitlab](https://gitlab.com/anndressa)) 
* executável 

## o que será avaliado

* organização do projeto (arquitetura, estrutura de pacotes)
* clareza do código (classes, metódos e atributos auto explicativos)
* escolha de estruturas e bibliotecas
* atenção aos detalhes de UI
* ausência de crashs e bugs
* tratamento de erros

## dúvidas

entre em contato com anndressa@enjoei.com.br