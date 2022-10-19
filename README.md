<h1 align="center">MRBlog</h1>

<p align='center'> 
    <img src="https://img.shields.io/badge/Spring_Boot  V3.0M5-F2F4F9?style=for-the-badge&logo=spring-boot"/>
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>  
    <img src="https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB"/>
    <img src="https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white"/>
    <img src="https://img.shields.io/badge/Heroku-430098?style=for-the-badge&logo=heroku&logoColor=white"/>
    <img src="https://img.shields.io/badge/Netlify-00C7B7?style=for-the-badge&logo=netlify&logoColor=white"/>
</p>    

Projeto desenvolvido teve o objetivo de criar um API Rest de um blog, utilizando os padrões de arquitetura em camadas, onde e possível cadastrar um usuário e esse usuário pode criar vários posts com seus comentários e sua respectivas categoria, realizando um CRUD em todas as entidades, e com o tratamento de suas exceções.

Projeto implementado na nuvem, com backend no heroku e frontend no netlify.

<h2>Veja o projeto</h2>

Experimente live demo completa [aqui](https://i.imgur.com/rok7mYv.mp4) ou deploy no netlify [aqui](https://mrblog-joosecj.netlify.app/).

#### FrontEnd
![FrontEnd](https://i.imgur.com/e0KqViv.gif)

#### Backend
![Bakcend](https://i.imgur.com/lapXMkh.gif)


<h2>Como criar e executar o MRBlog localmente</h2>

Criar e executar o projeto em seu ambiente de desenvolvimento local é muito fácil. Certifique-se de ter o Git, Node 16.17.1 e JDK17 instalados e siga as instruções abaixo. Precisa de informações adicionais? entre em contato no e-mail josecarloscjj@gmail.com 
(Estas instruções pressupõem que você esteja instalando como um usuário root.)

### Backend

1. Clone o código fonte
   ```bash
   git@github.com:joosecj/MRBlog.git
   ```

2. Em sua IDE de preferência(utilizei Intellij), importe a pasta **backend** e faça o update das dependências do **maven**.

3. Ao executar o projeto, pode ser acessado um navegador da Web em http://localhost:8080/ 

4. Collections do postman para fazer as requisições GET/PUT/DELETE E UPDATE para criação da conta, lançar as transações e consultar por movimentações por conta. 

   - Link da Collections do postman: https://www.getpostman.com/collections/77d9b404e0cd264cd454

## Requisições (Endpoints)

#### Obs: Para testar as requisições, poderá usar o URL na nuvem ou local que e http://localhost:8080.

   - *Pessoa By Id* - **GET**

   ```bash
   https://joosecj-mrblog.herokuapp.com/pessoa/1
   ```
   ##

- *Posts By Pagead* - **GET**

   ```bash
   https://joosecj-mrblog.herokuapp.com/posts?size=10&page=0&sort=title
   ```

   ##

- *New Post -* **POST**

   ```bash
   https://joosecj-mrblog.herokuapp.com/posts
   ```

   ##

- *Post -* **PUT**

   ```bash
   https://joosecj-mrblog.herokuapp.com/posts/4
   ```

   ##

- *Posts -* **DELETE**

   ```bash
   https://joosecj-mrblog.herokuapp.com/pessoa/6
   ```

   ##

- *Corpo da Requisição(Body) -* **JSON** - **PUT** e **POST**

   ```bash
      {
         "title": "Abelha Abelha",
         "titleDescription": "novo posto realizado para testesta",
         "description": "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution  fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution",
         "category": {
            "id": 1
         },
            "user": {
            "id": 1
         }
      }
   ```

### Frontend

1. Abra o terminal dentro da pasta **frontend** e rode o comando:
   ```bash
   yarn start
   ```
   ##

   <h2>Tecnologias utlizadas</h2>

   - [Java](https://docs.oracle.com/en/java/javase/17/)
   - [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
   - [JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
   - [Maven](https://maven.apache.org/guides/)
   - [H2 Database](https://www.h2database.com/html/main.html)
   - [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
   - [Postman](https://www.postman.com/api-documentation-tool/)

   - [React](https://reactjs.org/docs/getting-started.html)
   - [TypeScript](https://www.typescriptlang.org/docs/)
   - [Bootstrap](https://getbootstrap.com/)
   - [react-router-dom](https://v5.reactrouter.com/web/guides/quick-start)
   - [Axios](https://yarnpkg.com/package/axios)

   ##

   <div align="center">
   <h2>Autor: José Carlos</h2>
      <img align="center" alt="Jose-Js" height="190" width="190" src="https://avatars.githubusercontent.com/u/100246121?s=400&u=b15a545fb2c49f97f84e25aa0520b8b525631384&v=4"
   </div>
   </br> </br>
   <div align="center">
      <a href="https://instagram.com/joosecj" target="_blank"><img src="https://img.shields.io/badge/-Instagram-%23E4405F?style=for-the-badge&logo=instagram&logoColor=white" target="_blank"></a>
      <a href = "mailto:josecarloscjj@gmail.com"><img src="https://img.shields.io/badge/-Gmail-%23333?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
      <a href="https://www.linkedin.com/in/jos%C3%A9-carlos-a79736a0/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a> 
   </div>