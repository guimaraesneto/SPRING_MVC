
# SPRING_MVC

# 1. Introdução

Criar uma aplicação web utilizando o Spring MVC. Para isso, demonstraremos como configurar o Spring MVC e criar um projeto simples explorando os recursos desse framework, programando, até mesmo, um cadastro de usuários em memória. Como você poderá notar, a relação desse framework com o padrão de projetos MVC faz dele uma das soluções Java mais adotadas para o desenvolvimento web, simplificando não apenas a organização do projeto, mas também o funcionamento do mesmo.

Nesta aula conheceremos o escopo que será abordado no curso, no qual vamos implementar uma aplicação web usando o Spring MVC. A nossa aplicação consiste em um CRUD de produtos em memória com validação e conversão de dados. Além disso, também será abordado como configurar o Spring MVC para utilizar recursos estáticos no layout das páginas, a exemplo do Bootstrap.

Conteúdo de apoio
O padrão de projetos Model-View-Controller separa as responsabilidades da aplicação em três componentes:

- Model - contém a camada de persistência de dados, as regras de negócio e as classes de domínio;
- Controller - interpreta requisições vindas do usuário através da View e faz a chamada do Model necessário para interpretar a requisição;
- View - é a interface que o usuário interage, representando o front-end de uma aplicação.

Baseado no padrão MVC temos um framework web chamado Spring MVC, o qual implementa os conceitos do MVC para fornecer ao desenvolvedor uma maior produtividade durante a construção de projetos.

Com o Spring MVC as funcionalidades necessárias para atender as requisições web (HTTP), delegar as responsabilidades que serão processadas a partir delas, bem como retornar a resposta dessas solicitações já são fornecidas pelo framework.

O Spring MVC fornece também a possibilidade de integração com diversos recursos, tais como:

- Bean Validation, para validação de formulários no lado servidor da aplicação;
- Recursos Ajax via DWR, jQuery, entre outros;
- Templates web como Thymeleaf, Velocity, FreeMarker, JSTL, Apache Tiles e templates baseados em Javascript;
- JasperReports para visualização de relatórios.

# 2. Visão geral do projeto

Conhecer a estrutura do projeto é uma importante etapa para que você tenha uma visão geral do que será apresentado ao longo do curso. Além disso, facilita na compreensão dos recursos da tecnologia em análise, o Spring MVC, e como esses recursos são empregados para atender à demanda.

![Alt text](https://github.com/guimaraesneto/SPRING_MVC/blob/master/img/estrutura.png?raw=true "Figura 1. Estrutura do projeto
")

**Figura 1. Estrutura do projeto**

Analisando essa estrutura, é possível destacar algumas informações importantes:

- **br.com.devmedia.curso.config**: Pacote que contém as classes de configuração dos recursos do Spring;
- **br.com.devmedia.curso.dao**: Pacote que armazena as classes referentes à persistência de dados. É válido destacar que o pacote dao está presente na camada Model do MVC;
- **br.com.devmedia.curso.domain**: Aqui temos a classe de domínio (modelo) da aplicação, a qual também faz parte do Model do MVC;
- **br.com.devmedia.curso.web.controller**: Neste pacote vamos adicionar as classes que representam os controllers no padrão MVC;
- **br.com.devmedia.curso.web.conversor**: Aqui são adicionas as classes para realizar a conversão de tipos de dados entre recursos utilizados na view e no controller;
- **src/main/resources**: Neste diretório adicionamos o arquivo de propriedades que contém as mensagens de validação do Bean Validation;
- **/webapp/WEB-INF/resources**: É nesta pasta que adicionamos os arquivos estáticos, como CSS, JavaScript ou imagens;
- **/webapp/WEB-INF/views**: Como o nome indica, é nesta pasta que adicionamos as páginas web da aplicação;
- **pom.xml**: Arquivo que contém as configurações do Maven para gerenciamento das dependências e build.

# 3. Configurando o ambiente de desenvolvimento

Aprenda a configurar o ambiente de desenvolvimento do projeto que será usado como exemplo no curso. Esse ambiente vai ser configurado no Eclipse Neon, usando um projeto no formato Maven com o Tomcat Embedded.

**Conteúdo de apoio**

Neste curso será utilizado o Maven para gerenciamento das dependências e build. As informações referentes a essas funcionalidades são adicionadas no arquivo pom.xml. No código a seguir, apresentamos a configuração de duas dependências:

- **spring-context**: Dependência responsável por fornecer os recursos referentes a contexto do Spring Framework. A declaração do Spring Context também vai incluir no projeto outras bibliotecas do Spring como o Spring Core, Spring Beans, entre outras;
- **spring-webmvc**: Dependência responsável por incluir no projeto os recursos do Spring Web e Spring MVC.

```xml
<dependency>
   <groupId>org.springframework</groupId>
   <artifactId>spring-context</artifactId>
   <version>4.3.8.RELEASE</version>
</dependency>
<dependency>
   <groupId>org.springframework</groupId>
   <artifactId>spring-webmvc</artifactId>
   <version>4.3.8.RELEASE</version>
</dependency>
```
Essas são as dependências mínimas para o desenvolvimento de um projeto Spring MVC.

Além disso, o projeto abordado no curso também vai utilizar JSTL junto a JSP nas páginas web. Para fazer uso das mesmas, mais algumas dependências são necessárias:

- **javax.servlet-api**: Responsável por incluir na aplicação os recursos de Servlets, necessário para uso do Spring MVC;
- **jstl**: Essa dependência fornece os recursos da biblioteca JSTL;
- **javax.servlet.jsp-api**: Biblioteca responsável por adicionar na aplicação os recursos ligados a JavaServer Pages.

```xml
<dependency>
   <groupId>javax.servlet</groupId>
   <artifactId>javax.servlet-api</artifactId>
   <version>3.1.0</version>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>
<dependency>
     <groupId>javax.servlet.jsp</groupId>
     <artifactId>javax.servlet.jsp-api</artifactId>
     <version>2.3.1</version>
</dependency>
```

Além das dependências apresentadas, vamos utilizar o Maven para gerenciar o servidor Tomcat. Desta forma, não será necessário fazer o download, instalar e configurar na IDE o servidor, o próprio Maven se encarregará dessas ações. Para isso, incluímos na tag de build/plugins do pom.xml, a seguinte configuração:

- **groupId**: Indica ao Maven o grupo de dependências referentes ao Tomcat no qual ele vai localizar a dependência indicada na tag artifactId;
- **artifactId**: O nome da dependência do Tomcat referente ao grupo indicado;
- **version**: A versão da dependência do Tomcat;
- **port**: A porta em que o Tomcat vai rodar. Este valor pode ser alterado para, por exemplo, 8080, 8090, 9080, ou qualquer outro;
- **path**: Aqui é configurado o nome dado ao contexto da aplicação. Neste caso, a instrução captura o nome da tag finalNamee a URL base da aplicação será: http://localhost:9090/intro-spring-mvc.

```xml
<build>
   <finalName>intro-spring-mvc</finalName>
   <plugins>
      <plugin>
         <groupId>org.apache.tomcat.maven</groupId>
         <artifactId>tomcat7-maven-plugin</artifactId>
         <version>2.2</version>
         <configuration>
           <port>9090</port>
           <path>/${project.build.finalName}</path>
         </configuration>
      </plugin>
  </plugins>
</build>
```

# 4. Configurando o Spring MVC

A configuração de frameworks é uma etapa que pode levantar muitas dúvidas. O Spring MVC, assim como os demais, requer algumas configurações. Conheça, então, os passos iniciais para a configuração básica do Spring MVC junto ao Spring Framework. Essa configuração será totalmente baseada em código Java e será dividida em três classes principais.

**Conteúdo de apoio**

Quando trabalhamos com o Spring MVC é necessário definir qual o tipo de recurso será usado nas páginas web (JSP, JSTL, Thymeleaf, etc.). Então, para informar o recurso escolhido devemos configurar um beancom essa informação. No caso, para uso de JSP com JSTL o bean a ser utilizado é o InternalResourceViewResolver, como descrito a seguir:

```java
@Bean
public InternalResourceViewResolver viewResolver() {
   InternalResourceViewResolver resolver = new InternalResourceViewResolver();
   resolver.setPrefix("/WEB-INF/views/");
   resolver.setSuffix(".jsp");
   resolver.setViewClass(JstlView.class);
   return resolver;
}
```

**Linha 01**: Define o método como um bean gerenciado pelo Spring Framework;

**Linha 03**: A instância da classe InternalResourceViewResolver é necessária para a configuração do view template do Spring MVC baseado em JSTL;

**Linha 04**: Configuração do prefixo da páginas JSPs. Assim, o Spring MVC sabe onde encontrar as páginas e não se faz necessário digitar esse caminho sempre que for necessário acessar uma dessas páginas;

**Linha 05**: Define o tipo de arquivo das páginas web. Assim, não se faz necessário digitar a extensão do arquivo sempre que se quiser acessar um desses arquivos através dos controllers;

**Linha 06**: A classe JstlView informa ao Spring MVC que o view template será baseado no framework JSTL.

# 5. Meu primeiro Controller

Para dar os primeiros passos no Spring MVC e verificar se o projeto está configurado corretamente, vamos aprender como criar uma classe do tipo controller. A partir disso, será possível adicionar uma página JSP à aplicação, página essa que será acessada através desse controller.


**Conteúdo de apoio**

Veja a seguir um exemplo básico de uma classe que representa um controller gerenciado pelo Spring MVC:


```java
 @Controller
 public class WelcomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }

     @RequestMapping(value = "/teste", method = RequestMethod.GET)
     public ModelAndView teste() {
         ModelAndView view = new ModelAndView("welcome");
         view.addObject("teste", "Olá, eu sou o spring MVC.");
         return view;
      }
}
```

O controller WelcomeController tem as seguintes características:

**Linha 01**: Anotação responsável por informar ao Spring MVC que está é uma classe (bean) do tipo controller;

**Linhas 04 e 09**: Esta anotação tem como função mapear o path de acesso, via URL, ao método. Outro objetivo é definir o verbo HTTP (POST, GET, ...) referente à solicitação;

**Linha 11**: O objeto ModelAndView é usado como recurso do Spring MVC para enviar valores para as páginas através de variáveis.

# 6. Criando o model

A criação das classes de domínio é um importante e simples passo em grande parte dos projetos. Com o Spring MVC isso não muda. Criaremos agora a classe Usuario, que, como o nome indica, vai representar o modelo de dados de um usuário.

**Conteúdo de apoio**

Basicamente uma aplicação é construída em torno de uma ou mais classes de domínio, que é a representação de uma entidade de negócio. Por exemplo, para representar uma entidade de usuário, temos a classe Usuario, como descrita a seguir:

```java
public class Usuario {
  
  private Long id;   
  private String nome;          
  private String sobrenome;
                 
  public Usuario() {
     super();
  }              
  
   public Usuario(Long id, String nome, String sobrenome) {
     super();
     this.id = id;
     this.nome = nome;
     this.sobrenome = sobrenome;
   }
   // métodos getters/setters e toString()             
}
```

Note que o código dessa classe é bastante simples. Ele é composto por alguns atributos, dois construtores, os getters e setters e o método toString().

# 7. Apresentando o DAO

Para simular o armazenamento de dados e assim poder focar nos recursos do Spring MVC, vamos criar um DAO, padrão de acesso a dados que, em nosso exemplo, vai abstrair o acesso a dados em memória. Esse DAO é constituído pela interface UsuarioDao, a qual será implementada pela classe concreta UsuarioDaoImpl.

**Conteúdo de apoio**

Uma forma rápida de simular uma base de dados em uma aplicação é utilizando uma lista estática para o armazenamento dos registros. Desta forma, é possível inicializar a lista com alguns dados e ainda incluir, remover ou alterar os registros durante a execução da aplicação. Um exemplo pode ser visto a seguir:

```java
private static List<Usuario> us;       
                 
private List<Usuario> createUserList() {
   if (us == null) {
     us = new LinkedList<>();
     us.add(new Usuario(System.currentTimeMillis()+1L, "Ana", "da Silva"));
     us.add(new Usuario(System.currentTimeMillis()+2L, "Luiz", "dos Santos"));
     us.add(new Usuario(System.currentTimeMillis()+3L, "Mariana", "Mello"));
     us.add(new Usuario(System.currentTimeMillis()+4L, "Caren", "Pereira"));
     us.add(new Usuario(System.currentTimeMillis()+5L, "Sonia", "Fagundes"));
     us.add(new Usuario(System.currentTimeMillis()+6L, "Norberto", "de Souza"));
   }
   return us;
}
```

Para interagir com a lista e simular as operações de um CRUD, vamos utilizar os recursos do Java 8. Como exemplo, vejamos o código do método excluir(), apresentado a seguir:

```java
@Override
public void excluir(Long id) {
    us.removeIf((u) -> u.getId().equals(id));                   
}
```

O método removeIf() recebe como parâmetro uma expressão lambda para localizar na lista o objeto Usuarioque deve ser removido. Esse usuário será encontrado a partir do atributo id. Em seguida, o objeto é excluído.

# 8. Criando a tela de listagem

A implementação da listagem de dados normalmente é a primeira funcionalidade a ser implementada de um CRUD. Você aprenderá aqui a desenvolver a página JSP responsável por exibir a lista de usuários cadastrados na aplicação. Essa lista será exibida em uma tabela com recursos de JSTL.

09:32 min

Conteúdo de apoio
Para listar os dados armazenados em uma aplicação, comumente são usadas tabelas nas páginas web. Essas tabelas são baseadas em código HTML para definir suas estruturas como o cabeçalho (colunas) e o corpo (linhas). E para que os registros sejam exibidos na tabela, podemos usar o componente foreach da biblioteca JSTL, como apresentado a seguir:
```html
<table class="table table-striped table-condensed">
   <thead>
      <tr>
         <th>ID</th>
         <th>NOME</th>
         <th>AÇÃO</th>
      </tr>
   </thead>
   <tbody>
      <c:forEach var="usuario" items="${usuarios }">
         <tr>
            <td>${usuario.id }</td>
            <td>${usuario.nome } ${usuario.sobrenome }</td>
            <td>                        
               <a class="btn btn-info" href="#" >Editar</a>
               <a class="btn btn-danger" href="#" >Excluir</a>
            </td>
         </tr>
      </c:forEach>
   </tbody>
</table>
```

Entre as **Linhas 10 e 18** temos o processo que lista os usuários na tabela com auxílio da biblioteca JSTL:

**Linha 10**: A taglib core da JSTL utiliza o recurso foreach para a listar os dados. Esse recurso tem duas propriedades: items, que contém o objeto enviado pelo controller com a lista de usuários; e a propriedade var, que é o nome da variável que vai armazenar o objeto usuario da posição atual no loop da lista;

**Linha 12**: Imprime na tabela o id do usuário na posição atual do loop;

**Linha 13**: Imprime na tabela o nome e sobrenome do usuário na posição atual do loop;

**Linha 18**: Fechamento da tag do processo foreach.

# 9. Listando os registros em uma tabela

Aprenda como preparar um controller que vai enviar para uma página JSP um objeto contendo uma lista de registros, para que essa lista seja exibida na página de listagem dentro de uma tabela.

**Conteúdo de apoio**

No Spring MVC a classe que representa um controller possui algumas particularidades que são importantes para os componentes View e Controller se comunicar. Além disso, essa classe será gerenciada pelo Spring Framework, fazendo uso dos recursos de injeção de dependências. Veja a seguir um breve exemplo da classe UsuarioController.

@Controller
@RequestMapping("usuario")
public class UsuarioController {
   @Autowired
   private UsuarioDao dao;

   @RequestMapping(value = "/todos", method = RequestMethod.GET)
   public ModelAndView listaTodos(ModelMap model) {
      model.addAttribute("usuarios", dao.getTodos());
      return new ModelAndView("/user/list", model);
   }
}

Os principais pontos da classe UsuarioController são analisados a seguir:

**Linha 01**: Anotação responsável por transformar a classe em um bean do tipo controller;

**Linha 02**: Esta anotação mapeia o controller com o path usuario;

**Linha 04**: Anotação para injeção da dependência UsuarioDao;

**Linha 07**: Esta anotação vai definir o path para acesso ao método listaTodos() e também define este método como do tipo GET;

**Linha 08**: O argumento ModelMap é utilizado para armazenar os dados que serão enviados à página JSP;

**Linha 09**: O método addAttribute() recebe a variável que vai armazenar a lista de usuarios que será enviada para a página JSP;

**Linha 10**: A instância de ModelViewController retorna um objeto contendo a página JSP a ser exibida ao usuário e o objeto model com os dados enviados para a página.

# 10. Criando a tela de cadastro e edição

ma tela presente em qualquer aplicação é a de cadastro/edição dos dados referentes às classes de domínio. Assim, você aprenderá agora a criar uma nova página JSP que vai conter o formulário para as ações de cadastro e atualização dos registros armazenados na aplicação. Este formulário vai ser desenvolvido com recursos nativos do Spring MVC.

Conteúdo de apoio
Para cadastrar ou alterar dados em uma aplicação web utilizamos um formulário HTML. No Spring MVC há um formulário próprio para esse tipo de ação e ele se difere do formulário HTML padrão devido alguns detalhes, como pode ser visto a seguir:

<form:form modelAttribute="usuario" action="${save }" method="post">
   <div class="form-group">
      <label for="nome">Nome: </label>
      <form:input path="nome" class="form-control"/>
   </div>
   <div class="form-group">
      <label for="sobrenome">Sobrenome: </label>
      <form:input path="sobrenome"  class="form-control"/>
   </div>
   <div class="form-group">
      <button type="submit" class="btn btn-primary">Confirmar</button>
   </div>
</form:form>

Neste formulário temos alguns recursos nativos do Spring MVC, que são:

**Linha 01**: A tag <form:form> cria um formulário baseado em recursos do Spring, como é o caso do atributo modelAttribute, que realiza o bind entre objeto modelo (usuario), a view e o controller;

**Linhas 04 e 08**: A tag <form:input> representa um componente do tipo input que automaticamente identifica e faz o bind entre o atributo adicionado no atributo path e o objeto de domínio declarado em modelAttribute.

# 11. Cadastrando novos registros

Para completar a tela de cadastro, é necessário adicionar ao respectivo controller o método que recebe os dados enviados de um formulário a partir do front-end da aplicação para realizar a operação de inserção via DAO.

**Conteúdo de apoio**

Quando trabalhamos com Servlet, sem o auxílio de um framework MVC como o Spring, os dados enviados pelo formulário chegam ao lado servidor da aplicação em um objeto do tipo Request. Assim, devemos capturar do objeto Request os valores do formulário para depois atribuí-los a um objeto de domínio. Já no Spring MVC esse trabalho é minimizado. Os dados enviados via formulário para o controller já chegam no back-end no formato de um objeto de domínio, como pode ser visto a seguir:

@PostMapping("/save")
   public String save(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes attr) {
      dao.salvar(usuario);
      attr.addFlashAttribute("message", "Usuário salvo com sucesso.");
      return "redirect:/usuario/todos";
   }

**Linha 01**: Esta anotação é exclusiva para operações via POST e deve conter como parâmetro o path referente à solicitação do lado cliente;

**Linha 02**: Na declaração do método save passamos dois argumentos, com as seguintes funções:
○ @ModelAttribute: Realiza o bind entre o objeto de domínio enviado pelo formulário e o objeto esperado no controller;
○ RequestAttributes: Este objeto serve para atribuir valores em uma ação de redirect que parte do controller.

**Linha 03**: O objeto dao salva o usuário recebido do formulário;

**Linha 04**: Método para atribuir os valores que serão enviados via redirect;

**Linha 05**: Realiza um redirecionamento para o path /usuario/todos.

# 12. Editando os registros

Para a edição de registros, assim como para as demais ações na camada de visão, vamos criar um controller. Aprenda nesta aula como preparar um controller que vai receber uma solicitação para atualização de registros a partir de um formulário.

**Conteúdo de apoio**

Antes de realizar a edição de dados em um formulário no lado cliente, é necessário enviar ao formulário os dados que serão editados. Para isso, temos o seguinte método no controller:

```java
@GetMapping("/update/{id}")
public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {
   Usuario usuario = dao.getId(id);
   model.addAttribute("usuario", usuario);
   return new ModelAndView("/user/add", model);
}
```

**Linha 01**: Esta anotação mapeia uma solicitação enviada para o path /update/id;

**Linha 02**: @PathVariable captura na URL da solicitação o valor correspondente a seu parâmetro, neste caso, o valor referente ao id enviado na solicitação;

**Linha 03**: A partir do id recuperado na URL fazemos a consulta no dao pelo usuário que será editado;

**Linha 04**: Adicionamos na resposta da solicitação o usuário retornado pelo dao;

**Linha 05**: Como resposta à solicitação, a página do formulário será aberta com os dados do usuário que são entregues a ela pelo objeto model.


# 13. Excluindo itens da listagem

Aprenda agora a implementar um controller com Spring MVC para receber como solicitação o pedido de exclusão de um usuário cadastrado na base.

**Conteúdo de apoio**

Em uma classe do tipo controller há diversos métodos que recebem requisições do lado cliente da aplicação. Entre esses métodos, temos o responsável por receber o pedido de exclusão de um objeto de domínio, como pode ser visto a seguir:

```java
@GetMapping("/delete/{id}")
public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
   dao.excluir(id);
   attr.addFlashAttribute("message", "Usuário excluído com sucesso.");
   return "redirect:/usuario/todos";
}
```

O método apresentado realiza a operação de exclusão de um registro via identificador recebido como parâmetro na URL da solicitação. Como resposta, realiza um redirecionamento, contendo uma mensagem de sucesso que vai ser exibida na página de resposta.

# 14. Conversão de data entre view e controller

A conversão de dados é uma necessidade cada vez mais comum, principalmente quando buscamos entregar para o usuário mais facilidades. Pensando nisso, o Spring MVC também nos traz recursos que simplificam esse trabalho. Saiba, neste vídeo, como converter uma data, enviada por um formulário no formato texto, em um objeto Java que representa um tipo data (Date, Calendar ou LocalDate), e também, veja como formatar uma data para ser exibida na página web.

**Conteúdo de apoio**

Para converter uma data enviada por um formulário em um tipo Date, Calendar ou LocalDate, o Spring MVC possui um conversor padrão, baseado na anotação @DateTimeFormat.

Para fazer uso desse recurso, basta adicionar a anotação na classe de domínio, junto ao atributo do tipo data que se deseja converter.

Para confirmar como é simples fazer essa conversão, observe o código necessário a seguir:

```java
@DateTimeFormat
private LocalDate dtNascimento;
```

# 15. Conversão de enum entre view e controller

O Spring MVC nos fornece mais de uma opção para conversão de dados. Neste vídeo você saberá como converter um valor do tipo texto, enviado a partir do formulário, em um objeto Java do tipo enum, utilizando os recursos do framework para automatizar essa mudança no tipo de dados.

**Conteúdo de apoio**

Para converter um valor enviado por um formulário em um objeto Java complexo, que deve ser recebido em um controller, o processo é um pouco mais complexo do que o visto na aula anterior. É necessário habilitar a conversão na configuração do Spring MVC. Para isso, podemos implementar o método addFormatters(), como exposto abaixo:

```java
@Override
public void addFormatters(FormatterRegistry registry) {
   registry.addConverter(new TipoSexoConverter());
}
```

Esse método é herdado e sobrescrito da classe WebMvcConfigurerAdapter. O argumento registry fornece acesso ao método addConverter(), no qual se deve adicionar como parâmetro a instância da classe que contém o conversor de tipos. Esse conversor deve implementar a classe Converter, do pacote org.springframework.core.convert.converter.

# 16. Validação com Bean Validation

A validação de dados é um passo importante para a segurança e correto funcionamento da aplicação. Com o Spring MVC esse processo é facilitado devido ao suporte à Bean Validation. Aqui, será demonstrado como realizar a validação, no back-end, de dados enviados a partir de um formulário e também, como exibir as mensagens de validação na página.

**Conteúdo de apoio**

O sistema de validação de formulários pelo lado servidor, em aplicações com Spring MVC, pode ser realizado com Bean Validation. Para isso, primeiro é necessário adicionar uma biblioteca que implementa a Bean Validation, como o Hibernate-Validator. Um exemplo, usando o gerenciador de dependências Maven, pode ser visualizado a seguir:

```xml
<dependency>
   <groupId>org.hibernate</groupId>
   <artifactId>hibernate-validator</artifactId>
   <version>5.3.2.Final</version>
</dependency>
```

Dessa forma, você terá acesso, nas classe de domínio, às anotações de validação - tanto aquelas que seguem a especificação Bean Validation, quanto as da implementação Hibernate-Validator.

# 17. Mensagens de validação via arquivos

Para reduzir o acoplamento entre seu código e as mensagens de validação, é recomendado, muitas vezes, separar as mensagens de validação em um arquivo de propriedades. Este vídeo ensinará como fazer essa mudança.

**Conteúdo de apoio**

Mensagens de validação podem ser adicionadas nas próprias anotações ou então, de forma considerada mais profissional, em um arquivo de propriedades. Desse modo, se tem uma maior facilidade para atualização ou mesmo internacionalização das mensagens.

No Spring MVC, para carregar as mensagens de um arquivo de propriedades, é necessário criar um bean que diga ao Spring que ele deve carregar o arquivo, e este, por sua vez, deve estar no diretório “resources” no classpath da aplicação.

O bean apresentado a seguir é um exemplo. Em seu código, o método setBasename() recebe como valor o nome do arquivo de propriedades, sem a extensão “.properties”.

@Bean
public MessageSource messageSource() {
   ResourceBundleMessageSource source = new ResourceBundleMessageSource();
   source.setBasename("messages");
   return source;
}

# 18. Incluindo o Bootstrap

Aprenda como adicionar arquivos estáticos em aplicações que utilizam o Spring MVC. Como exemplo, vamos usar o framework Bootstrap e seu arquivo “bootstrap.css” para apresentar a técnica que deve ser adotada nestas situações.

**Conteúdo de apoio**

Em aplicações web é muito comum trabalhar com arquivos estáticos no front-end. CSS, JS e JPEG são exemplos de arquivos considerados estáticos. Para fazer uso desses arquivos no Spring MVC é preciso habilitar essa funcionalidade a partir de um método herdado da classe WebMvcConfigurerAdapter, como exposto a seguir:

```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
   registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/resources/");
}
```

O método addResourceHandlers() deve ser sobrescrito com informações sobre o local onde os arquivos estáticos se encontram;

**Linha 03**: O método addResourceHandler() recebe uma instrução que vai trabalhar como um atalho dentro da página JSP para alcançar o arquivo que deve ser carregado;

**Linha 04**: O método addResourceLocations() deve receber como parâmetro o local raiz de onde os arquivos estáticos estão localizados. Normalmente esse local é o diretório WEB-INF/resources.














































