
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

![Alt text](https://arquivo.devmedia.com.br/naoexclusivo/EduardoSpinola/OqueeSpringMVC/images/fluxo_springmvc.png?raw=true "Figura 2. Fluxo de funcionamento do Spring MVC")
