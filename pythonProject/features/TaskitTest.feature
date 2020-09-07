Feature: Meu primeiro teste

  Scenario: Adicionar mais informações ao usuario
    Given O usuario abre o navegador
    And Faz login
    When Clica na aba More About You
    And Clica no botao Add More Data About You
    And Escolhe o tipo de contato
    And Digita o contato escolhido
    And Clica em salvar
    Then uma mensagem deve ser exibida