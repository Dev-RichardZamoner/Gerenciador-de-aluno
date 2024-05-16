# Sistema de Login com Java e JFrame

## Descrição
Este projeto é um sistema de login desenvolvido utilizando a plataforma Eclipse, na linguagem Java com a biblioteca JFrame para a interface gráfica. O sistema permite que os usuários criem uma conta, façam login com suas credenciais e interajam com um banco de dados para gerenciar informações.

## Funcionalidades
- **Registro de Usuário**: Permite que novos usuários se registrem fornecendo um nome de usuário e senha.
- **Autenticação de Usuário**: Os usuários podem fazer login usando suas credenciais registradas.
- **Interface Gráfica de Cadastro**: Uma tela de cadastro simples que permite o registro de novos usuários e uma opção para voltar à tela de login.
- **Interface Gráfica Principal**: Após o login, os usuários acessam uma tela principal onde podem buscar, excluir e incluir dados no banco de dados.
- **Gerenciamento de Banco de Dados**: Integração com um banco de dados MySQL para gerenciamento de dados de usuários, incluindo id, usuário, senha, email e idade.

## Tecnologias Utilizadas
- **Eclipse**: IDE utilizada para o desenvolvimento do projeto.
- **Java**: Linguagem de programação.
- **JFrame**: Biblioteca para criação da interface gráfica.
- **MySQL**: Sistema de gerenciamento de banco de dados.

## Configuração do Banco de Dados
Para que o sistema funcione corretamente, é necessário configurar uma conexão com o banco de dados MySQL. O arquivo `conexao.java` deve ser configurado com as seguintes credenciais padrão:
- **Usuário**: root
- **Senha**: pass

Certifique-se de que o serviço MySQL esteja em execução em sua máquina local com as credenciais acima para testar o projeto.

## Como Executar
Para executar este projeto, siga os passos abaixo:
1. Clone o repositório para sua máquina local.
2. Configure o arquivo `conexao.java` com as credenciais do seu banco de dados MySQL.
3. Abra o Eclipse e importe o projeto.
4. Execute o arquivo `Tela_de_acesso.java` para iniciar a aplicação.

## Contribuições
Contribuições são sempre bem-vindas! Se você tem alguma sugestão para melhorar o sistema, sinta-se à vontade para criar um 'pull request'.

## Imagens Funcional
Tela de Acesso:

![image](https://github.com/Dev-RichardZamoner/Gerenciador-de-aluno/assets/130820445/d57ea538-41d3-4a4f-a1d2-0c956ca0ddc9)

Tela de Cadastro:

![image](https://github.com/Dev-RichardZamoner/Gerenciador-de-aluno/assets/130820445/0992d60e-4614-4150-a018-61d8698375d6)

Tela Principal(Dashboard):

![image](https://github.com/Dev-RichardZamoner/Gerenciador-de-aluno/assets/130820445/a7e4c2e7-fa4a-4856-b1cc-307e1135874e)


## Licença
Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](https://github.com/Dev-RichardZamoner/Gerenciador-de-aluno/blob/main/LICENSE.md) para mais detalhes.
