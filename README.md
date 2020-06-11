# web-scraping-forum-alura-api
This API does Web Scraping in the Alura forum

[Link documentation](https://forum-alura-web-scraping.herokuapp.com/swagger-ui.html)

Output example:
```yaml
[
  {
    titulo: "Meu eclipse não cria a biblioteca de JavaWeb",
    categoria: {
      nome: "Infraestrutura",
      url: "http://cursos.alura.com.br/forum/categoria-infraestrutura",
      value: "categoria-infraestrutura"
    },
    subcategoria: {
      nome: "Builds e Controle de versão",
      url: "http://cursos.alura.com.br/forum/subcategoria-build-e-versionamento",
      value: "subcategoria-build-e-versionamento"
    },
    mensagem: "Quando eu crio um projeto Maven diretamente no Eclipse, ele não cria toda a listagem de arquivos base como no vídeo da aula. Por exemplo: Deployment Descriptor, Java Resources, JavaScript Resources, Todos esses itens estão faltando. Imagino que seja um erro de setup do próprio eclipse, porém eu já instalei as extensões para webDev, reinicio o eclipse seguindo o wizard de update e ele continua criando projetos sem essas libs e folders de JS. Alguém sabe dizer como arrumar isso? Estou preso nisso hoje o dia todo. Obrigado!",
    autor: {
    nome: "Gabriel",
    url: "http://cursos.alura.com.br/user/gabrielsousa9"
    },
    dataCriacao: "2020-06-10T21:55:00",
    respostas: [
      {
        dataCriacao: "2020-05-27T23:15:00",
        mensagem: "Também encontrei essa diferença, fiquei pensando que possa ser apenas a diferença de versão do Eclipse, pois na aula é usado um Eclipse antigo.",
        autor: {
          nome: "Marcelo Ferreira Duarte",
          url: "http://cursos.alura.com.br/user/marcelofd"
        },
      solucao: false
    ],
    url: "https://cursos.alura.com.br/forum/topico-meu-eclipse-nao-cria-a-biblioteca-de-javaweb-114231"
  },
]
```

