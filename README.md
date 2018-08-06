
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














