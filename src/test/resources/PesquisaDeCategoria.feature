# language: pt
@tag
  Funcionalidade: API permiter consultar categoria

    @tag1
    Cenário: O cliente faz uma requisição GET para /categoria
      Dado Que a Categoria está inicializada
      Quando o cliente chamar /categoria/ "<code>"
      Entao o cliente recebe a Categoria

      Exemplo:
        | code |
        |  1   |
        |  2   |