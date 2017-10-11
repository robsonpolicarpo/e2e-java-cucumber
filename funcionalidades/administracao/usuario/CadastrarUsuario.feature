# language: pt
# encoding: iso-8859-1
# Autor: Paulo Giovani
# Data: 06/09/2017

@administracao @usuario
Funcionalidade: Cadastrar Usuario
   Afim de manter os dados de usuarios
   Como administrador do sistema
   Eu quero poder cadastrar as informacoes de novos usuarios.
   
   Regras:
     1. Nao aceitar cadastros duplicados.
     2. Informar email válido conforme expressão regular definida.

  Contexto: 
    Dado que estou conectado no sistema como administrador
    E acesso o Menu: "Administração > Usuário"
    E clico na opcao Novo usuário
    E estou na tela de Cadastro de Usuário

  @positivo
  Esquema do Cenario: 01- Cadastro Sucesso
    Quando eu preencho as informacoes
    E clico na opcao Salvar
    Entao o sistema grava os dados na base
    E o sitema apresenta mensagem <mensagem>

    Exemplos: 
      | mensagem   |
      | "MN001"    |

  @positivo
  Cenario: 02- Limpar o cadastro de usuario
    Quando eu preencho as informacoes
    E clico na opcao Limpar
    Entao o sistema limpa as informações inseridas nos campos

  @negativo
  Esquema do Cenario: 03- Campo obrigatorio nao informado
    Dado que após preenche todos os campos do cadastro
    Quando eu nao informo dados nos <campos> obrigatorio
    Entao o sitema apresenta mensagem <mensagem>

    Exemplos: 
      | campos             | mensagem   |
      | "Login"            | "ME003"    |
      | "Email"            | "ME003"    |
      | "Perfil de acesso" | "ME003"    |
      | "Senha"            | "ME003"    |
      | "Confirme a senha" | "ME003"    |

  @negativo
  Esquema do Cenario: 04- Registro Duplicado
    Dado que já exista um usuario cadastrado
    Quando eu informo os mesmos dados no cadastro de novo usuario
    E clico na opcao Salvar
    Entao o sitema apresenta mensagem <mensagem>

    Exemplos: 
      | mensagem   |
      | "ME004"    |

  @negativo
  Esquema do Cenario: 05- Email inválido
    Quando informo um Email invalido <email>
    Entao o sitema apresenta mensagem <mensagem>

    Exemplos: 
      | email               | mensagem   |
      | "asdgfds"           | "ME006"    |
      | "pgiovani@"         | "ME006"    |
      | "paulo@gmail."      | "ME006"    |
      | "pgiovanigmail.com" | "ME006"    |

  @negativo
  Esquema do Cenario: 06- Senha e Confirme a Senha divergentes
    Quando eu informo os dados para a 'Senha' e 'Confirme a Senha' diferentes
    Entao o sitema apresenta mensagem <mensagem>

    Exemplos: 
      | mensagem |
      | "ME005"  |
