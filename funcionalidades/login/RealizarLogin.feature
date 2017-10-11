#language: pt
# encoding: iso-8859-1
# Autor: Paulo Giovani
# Data: 31/08/2017

@login
Funcionalidade: Realizar Login
   Afim de validar a autenticação
   Como Administrador do sistema
   Eu quero poder logar na aplicação
   Para que eu possa acessar o sistema
   
   Regras:
     1. Deve aceitar somente usuário e senha válidos, previamente cadastrados e com permissão ao sistema.

  Contexto: 
    Dado que acesso a aplicacao sem estar autenticado
    E estou na tela de Login

  @positivo @unitario @aceitacao 
  Esquema do Cenario: 01- Login Sucesso
    Quando informar os dados do login <usuario> <senha>
    E clicar na opcao Entrar
    Entao o sistema deve autenticar 
    E apresentar a tela Home
    
     Exemplos: 
      | usuario    | senha    |
      | "admin"    | "admin"  |

  @negativo
  Esquema do Cenario: 02- Login Invalido
    Quando informar os dados do login <usuario> <senha>
    E clicar na opcao Entrar
    Entao o sistema nao autentica 
    E apresenta mensagem de erro <msg>
    # ME007

    Exemplos: 
      | usuario    | senha      | msg       |
      | "errado"  | "admin"    | "ME007"   |
      | "admin"    | "errado" | "ME007"   |

  @negativo
   Esquema do Cenario: 03- Usuario e Senha nao informado
    Quando informar os dados do login <usuario> <senha>
    E clicar na opcao Entrar
    Entao apresenta mensagem de erro <msg>
    
    Exemplos: 
      | usuario    | senha      | msg       |
      | ""         | ""         | "ME003"   |
      | "admin"    | ""         | "ME003"   |
      | ""         | "admin"    | "ME003"   |
