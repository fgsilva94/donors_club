# Donors' Club

## Autores:

| Número   | Nome              |
| -------- | ----------------- |
| 20190795 | Felipe Silva      |
| 20190919 | Willian Santa Ana |

## Enquadramento:

Vivemos em um mundo onde as pessoas estão cada vez mais preocupadas em comprar o telemóvel do ano [3], comprar roupas que só vão usar uma ou duas vezes. Na maioria dos casos todos esses produtos acabam sendo descartados [4], mesmo estando em boas condições de utilização.

Em Portugal são descartados de forma incorreta aproximadamente 200 mil toneladas de roupas todos os anos [1], a necessidade por estar sempre na moda faz pessoas comprarem diversas roupas novas ao longo do ano apenas para se manterem na moda. Em 2019 batemos recorde no lixo eletrônico gerado no mundo, foram cerca de 59 milhões de toneladas descartadas com previsão de atingir 81,5 milhões de toneladas em 2030 [2]. E não para por aí, além disso brinquedos, móveis, livros, cosméticos, e muitos outros bens de consumo são despojados aleatoriamente contribuindo para o aumento da poluição e desperdiçando uma enorme quantidade de matéria prima que foi utilizada durante a produção, sendo que para produzir apenas uma T-shirt de algodão são necessários aproximadamente 2700 litros de água ou até 10000 litros para a produção de uma calça de ganga [1].

Enquadrada no ODS 12 a nossa aplicação busca melhorar os padrões de consumo e diminuir a produção e desperdício desnecessária de bens de consumo, móveis, eletrônicos e muitos outro.

Vamos implementar uma plataforma de doações oferecendo uma forma fácil e intuitiva para os utilizadores anunciarem os produtos que não utilizam mais para doações. Assim evitamos o desperdício do produto em boas condições além de ajudar alguém necessitado. Pretendemos oferecer uma forma fácil e intuitiva para os utilizadores publicarem anúncios separados por categorias, assim como pesquisar anúncios de outros utilizadores por categorias e localização. Assim podemos ajudar alguém necessitado e contribuir para diminuição do desperdício de produtos ainda em bom estado.

**Produto similar:** https://www.freecycle.org/ [5]

A Freecycle é uma plataforma para doações, sem fins lucrativos, que existe desde 2003 originalmente fundada no Arizona. Composta por grupos que se dividem por vários países. Nesses grupos pessoas podem publicar anúncios de objetos para doação. Os utilizadores podem trocar mensagens entre si através do anúncio, porém é necessário ser um membro do grupo em que o anúncio foi publicado, e por consequência estar logado.

O funcionamento do Freecycle é muito similar ao nosso, porém ele apresenta alguns problemas que pretendemos corrigir em nosso projeto.

A plataforma deles e pouco intuitiva, e não vai direto ao ponto. São necessário vários passos para ver os anúncios que lá estão publicado.

A mensagens obrigam o utilizador a se tornar membro do grupo em que o anúncio se encontra publicado, e isso é pouco prático. Ainda falando das mensagens, o seu funcionamento está mais algo como troca de "e-mails". E em nosso projeto o chat será em tempo real.

A interface do Freecycle está completamente ultrapassada, e não há responsividade para utilização em telemóveis. Tornando-o pouco intuitivo e desagradável para o uso.

## Casos de utilização:

### Anunciar um produtos

**Descrição:**
O utilizador cria um anúncio de um produto que deseja doar no site.

**Pré-condições:**

1. Possuir um cadastro e estar logado na plataforma.

**Passo a passo:**

1. Na pagina do utilizador, selecionar a opção para publicar um anúncio.
2. Inserir título, descrição, categoria e sub-categoria do anuncio, estes dados são obrigatórios.
3. O utilizador confirma e faz a submissão do anúncio.

**Pós-condições:**

1. Após a submissão do anúncio, os dados passam por uma validação.
2. Se a validação for negativa, uma mensagem é exibida ao utilizador informando o problema.
3. Se a validação for positiva, o anúncio e gravado na base dados.

### Pesquisar um anúncio

**Descrição:**

Pesquisar por anúncios mais específicos.

**Pré-condições:**
NA

**Passo a passo:**

1. Ao selecionar a opção de pesquisa, será listado anúncios cujo o título está relacionado ao texto pesquisado.
1. O resultado para cada anúncio encontrado: foto se houver, título, tipo de anúncio e data de publicação.
1. Há opções de filtro: local, categoria, data de publicação, e ordenação.
1. Um anúncio de interesse pode ser salvo como favorito.

**Pós-condições:**

1. Um anúncio salvo como favorito, e armazenado na base de dados.
2. O texto utilizado para pesquisa também é armazenado na base de dados.
3. Se o anúncio pesquisado for aberto, o número de visualizações do anúncio é incrementado e atualizado na base de dados.

### Contacto entre utilizadores

**Descrição:**
Utilizadores podem trocar mensagens entre si pela plataforma através do anúncio.

**Pré-condições:**

1. Possuir um cadastro e ter feito o login na plataforma.

**Passo a passo:**

1. Na página do anúncio o utilizador pode enviar uma mensagem diretamente ao anunciante através da plataforma.
2. Ao selecionar a opção para "enviar mensagem", o utilizador pode escrever uma mensagem para o anunciante.
3. O chat é iniciado entre ambos através do anúncio e criado na base de dados.
4. O anunciante recebe a mensagem e pode enviar a resposta.

**Pós-condições:**

1. As mensagens trocadas são armazenadas na base de dados.

## Referências:

[1] Diário de notícias (2019). Portugueses deitam fora 200 mil toneladas de roupa por ano. Acedido em: 01/11/2020 em: https://www.dn.pt/edicao-do-dia/08-abr-2019/portugueses-deitam-fora-200-mil-toneladas-de-roupa-por-ano--10764484.html

[2] Jornal de Negócios (2020). Lixo eletrônico atingiu valores recorde em 2019 e vai continuar a aumentar no mundo. Acedido em: 01/11/2020 em: https://www.jornaldenegocios.pt/sustentabilidade/detalhe/lixo-eletronico-atingiu-valores-recorde-em-2019-e-vai-continuar-a-aumentar-no-mundo

[3] Minelgaitė, A., Liobikienė G.(2019). Waste problem in European Union and its influence on waste management behaviours. Science of The Total Environment, Volume 667, Pages 86-93. https://doi.org/10.1016/j.scitotenv.2019.02.313

[4] Eurostat (2020). Waste electrical and electronic equipment (WEEE) by waste management operations. Acedido em: 31/10/2020 em: https://ec.europa.eu/eurostat/web/products-datasets/-/env_waselee

[5] Lisboa Secreta (2018). Encontra borlas para ti ou doa o que já não usas!. Acedido em: 01/11/2020 em: https://lisboasecreta.co/freecycle/
