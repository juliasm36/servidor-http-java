# Servidor Web HTTP em Java

Projeto acadêmico desenvolvido individualmente na disciplina de **Sistemas Distribuídos**, do curso de Sistemas de Informação da Universidade Feevale, no primeiro semestre de 2026.

O objetivo foi implementar um servidor web simples em Java, capaz de receber requisições HTTP do tipo GET e disponibilizar páginas HTML e imagens por meio de um navegador.

## Tecnologias utilizadas

- Java
- HTML
- Protocolo HTTP
- Sockets (`ServerSocket` e `Socket`)

## Funcionalidades

O servidor implementa as seguintes funcionalidades:

- Recebimento e processamento de requisições HTTP GET.
- Disponibilização de três páginas HTML com navegação entre elas.
- Carregamento de imagens armazenadas no diretório do site.
- Listagem dos arquivos HTML disponíveis quando o endereço raiz é acessado.
- Retorno de respostas HTTP para diferentes situações:
  - **200 OK:** requisição atendida.
  - **403 Forbidden:** tentativa de acesso a caminho não permitido.
  - **404 Not Found:** arquivo solicitado não encontrado.
  - **405 Method Not Allowed:** método HTTP diferente de GET.

## Estrutura do projeto

```text
servidor-http-java/
├── Server.java
├── site/
│   ├── index.html
│   ├── pagina1.html
│   ├── pagina2.html
│   └── img/
│       ├── foto1.png
│       ├── foto2.png
│       └── foto3.png
├── README.md
└── .gitignore
```

## Como executar

**Pré-requisito:** Java JDK instalado.

1. Faça o download ou clone o repositório.
2. Abra um terminal na pasta que contém o arquivo `Server.java`.
3. Compile o código:

   `javac Server.java`

4. Execute o servidor:

   `java Server`

5. Abra o navegador e acesse:

   `http://localhost:8074/`

O servidor utiliza a porta **8074**.

Ao acessar o endereço raiz, será exibida uma lista de arquivos HTML disponíveis. Também é possível acessar diretamente as páginas:

- `/index.html`
- `/pagina1.html`
- `/pagina2.html`
  

## Aprendizados

O desenvolvimento deste trabalho proporcionou a aplicação prática de conceitos relacionados a:

- Comunicação entre cliente e servidor.
- Funcionamento básico do protocolo HTTP.
- Utilização de sockets em Java.
- Leitura de requisições e construção de respostas HTTP.
- Disponibilização de arquivos estáticos por um servidor web.

---

**Projeto acadêmico individual — Sistemas Distribuídos | Universidade Feevale | 2026/01**
