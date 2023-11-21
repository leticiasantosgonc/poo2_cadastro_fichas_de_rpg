package Main;

import DAO.ContaDAO;
import DAO.ContaPersonagemDAO;
import DAO.PersonagemDAO;
import DAO.PersonagemRacaDAO;
import DAO.RacaDAO;
import ENUM.Classe;
import Model.Conta;
import Model.Personagem;
import Model.Raca;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    
    static Statement stmt = null;
    static ResultSet rs = null;
    static Scanner scan = new Scanner(System.in);
    static PreparedStatement ps = null;
    static Connection conn = null;
    static ConexaoMySQL conexao = new ConexaoMySQL();
    
    static PersonagemDAO personagemDAO = new PersonagemDAO();
    static ContaDAO contaDAO = new ContaDAO();
    static RacaDAO racaDAO = new RacaDAO();
    static ContaPersonagemDAO contaPersonagemDAO = new ContaPersonagemDAO();
    static PersonagemRacaDAO personagemRacaDAO = new PersonagemRacaDAO();
    static Conta loggedAccount;
    static ArrayList<Personagem> listaPersonagens;
            
    public static void main(String[] args) {
        int op;
        String aux;
        
        conn = conexao.getConexaoMySQL();
        System.out.println("Bem vindo(a) ao cadastro de fichas de rpg :D");
        if(conn == null){
            System.out.println("Erro: não foi possível conectar ao banco");
        }else{
            while (true) {
                System.out.println("Ja possui uma conta?");
                System.out.println("1. Sim");
                System.out.println("2. Nao");
                System.out.println("0. Sair");

                try {
                    int count = Integer.parseInt(scan.nextLine());

                    if (count == 1) {
                        System.out.println("\nInsira o login: ");
                        String lgn = scan.nextLine();

                        Conta cnt = contaDAO.readLogin(lgn);

                        if (cnt != null) {
                            System.out.println("Insira a senha: ");
                            String snha = scan.nextLine();

                            if (cnt.getSenha().equals(snha)) {
                                loggedAccount = cnt;  
                                System.out.println("Bem vindo/a " + cnt.getLogin()+ "!");
                                break;  
                            } else {
                                System.out.println("Senha incorreta. Tente novamente.");
                            }
                        } else {
                            System.out.println("Conta não encontrada. Tente novamente ou crie uma nova conta.");
                        }
                    } else if (count == 2) {
                        System.out.println("\nCadastrar nova conta\n");
                        System.out.println("Digite o login desejado: ");
                        String login = scan.nextLine();

                        System.out.println("Digite a senha: ");
                        String senha = scan.nextLine();

                        Conta conta = new Conta(login, senha);

                        if (contaDAO.insert(conta) > 0) {
                            System.out.println("\nBem vindo(a) ao sistema, " + login + "!");
                            loggedAccount = conta;  
                            break;  
                        } else {
                            System.out.println("\nErro: operação não funcionou como esperado");
                        }
                    } else if (count == 0) {
                        System.out.println("Terminando execução.");
                        return;  
                    } else {
                        System.out.println("Opção inválida!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida. Por favor, digite um número válido.");
                }
            }
        }
        if (loggedAccount != null) { 
            do {
                System.out.println("\n----------------------------------------");
                System.out.println("|             MENU USUARIO             |");
                System.out.println("|1. Listar personagens                 |"
                        + "\n|2. Atualizar conta                    |"
                        + "\n|3. Deletar conta                      |"
                        + "\n|4. Inserir personagem                 |"
                        + "\n|5. Procurar personagem                |"
                        + "\n|6. Atualizar personagem               |"
                        + "\n|7. Deletar personagem                 |");                
                System.out.println("----------------------------------------");
                System.out.println("|0. Sair\nEscolha uma opcao -> ");       
                aux = scan.nextLine();                
                op = Integer.parseInt(aux);

                switch (op) {
                    case 0:
                        System.out.println("Volte sempre! Mantenha seus personagens atualizados! :D");
                        break;
                    case 1: 
                        System.out.println("\nListar seus personagens \n");
                        listaPersonagens = contaPersonagemDAO.buscarPersonagemConta(loggedAccount.getIdConta());
                        if(!listaPersonagens.isEmpty()){
                            for(int i = 0; i < listaPersonagens.size(); i++){
                                Personagem personagem = listaPersonagens.get(i);

                                System.out.println("id: "+ personagem.getIdPersonagem());
                                System.out.println("nome: "+ personagem.getNome());
                                System.out.println("nivel: "+ personagem.getNivel());
                                System.out.println("_______________________");
                            }
                        } else {
                            System.out.println("Conta nao tem personagem vinculado :(");
                        }
                        break;
                    case 2:
                        System.out.println("\nAlterar conta\n");
                        System.out.println("\n Deseja alterar login? 1. Sim ou 2. Nao");
                        aux = scan.nextLine();
                        int resp = Integer.parseInt(aux);

                        if (resp == 1) {
                            System.out.println("Digite o novo login: ");
                            String login = scan.nextLine();
                            loggedAccount.setLogin(login);
                            System.out.println("Alterado com sucesso " + loggedAccount.getLogin());
                        }
                        System.out.println("Deseja alterar a senha? 1. Sim ou 2. Nao");
                        aux = scan.nextLine();
                        resp = Integer.parseInt(aux);

                        if (resp == 1) {
                            System.out.println("Digite a nova senha: ");
                            String senha = scan.nextLine();
                            loggedAccount.setSenha(senha);
                        }                                
                        if (contaDAO.update(loggedAccount) > 0) {
                            System.out.println("\nConta atualizada com sucesso! :)");
                        } else {
                            System.out.println("\nErro: operacao nao funcionou como esperado");
                        }
                        break;
                    case 3:
                        System.out.println("\nDeletar conta\n");
                        System.out.println("Insira a senha para prosseguir: ");
                        String snha = scan.nextLine();
                        if (loggedAccount.getSenha().equals(snha)) {
                            if (contaDAO.delete(loggedAccount.getIdConta()) > 0) {
                                loggedAccount = null;
                                System.out.println("\nVoce ira fazer falta :(");
                                op = 0;
                            }else{
                                System.out.println("erro ao deletar conta");
                            } 
                        } else {
                            System.out.println("Senha incorreta. Tente novamente.");
                        }
                            
                        break;
                    case 4:
                        System.out.println("Hora de cadastrar o seu personagem :)\n");
                        System.out.println("Informe o nome do personagem: ");
                        String nomeP = scan.nextLine();

                        System.out.println("Informe o nivel do personagem: ");
                        String niv = scan.nextLine();

                        op = Integer.parseInt(niv);
                        Personagem per = new Personagem(nomeP, op);
                        personagemDAO.insert(per);

                        contaPersonagemDAO.associarPersonagemConta(loggedAccount.getIdConta(), per.getIdPersonagem());

                        System.out.println("Qual raca do personagem? ");
                        String nomeR = scan.nextLine();

                        System.out.println("Descreva o personagem :)");
                        String desc = scan.nextLine();

                        System.out.println("Qual a fraqueza? ");
                        String fraqueza = scan.nextLine();
                        int op2 = Integer.parseInt(fraqueza);

                        System.out.println("Qual classe? ");
                        String classe = scan.nextLine();
                        op = Integer.parseInt(classe);

                        Raca rac = new Raca(nomeR, desc, op2, op);
                        racaDAO.insert(rac);
                        personagemRacaDAO.associarRacaPersonagem(per.getIdPersonagem(), rac.getIdRaca());
                        break;
                    case 5:
                        System.out.println("\nProcurar personagem\n");
                        System.out.println("Digite o ID do personagem: ");
                        aux = scan.nextLine();
                        int idPersonagem = Integer.parseInt(aux);
                        
                        Personagem personagem = personagemDAO.read(idPersonagem);
                        if(personagem != null){
                            Raca raca = personagemRacaDAO.getRacaById(idPersonagem);
                            System.out.println("id: "+ personagem.getIdPersonagem());
                            System.out.println("nome: "+ personagem.getNome());
                            System.out.println("nivel: "+ personagem.getNivel());
                            System.out.println("raca: " + raca.getNome());
                            System.out.println("classe: " + Classe.fromInt(raca.getClasse()));
                            System.out.println("fraqueza: " + Classe.fromInt(raca.getFraqueza()));
                            System.out.println("descricao: " + raca.getDescricao());
                            System.out.println("_______________________");
                        }
                        break;
                    case 6:  
                        System.out.println("\nAlterar personagem\n");
                        listaPersonagens = contaPersonagemDAO.buscarPersonagemConta(loggedAccount.getIdConta());
                        if(!listaPersonagens.isEmpty()){
                            for(int i = 0; i < listaPersonagens.size(); i++){
                                personagem = listaPersonagens.get(i);

                                System.out.println("id: "+ personagem.getIdPersonagem());
                                System.out.println("nome: "+ personagem.getNome());
                                System.out.println("nivel: "+ personagem.getNivel());
                                System.out.println("_______________________");
                            }
                        } else {
                            System.out.println("Conta nao tem personagem vinculado :(");
                        }                        
                        System.out.println("Digite o id do personagem: ");
                        aux = scan.nextLine();
                        idPersonagem = Integer.parseInt(aux);

                        personagem = personagemDAO.read(idPersonagem);

                        if (personagem != null) {
                            System.out.println("Dados do personagem:");
                            System.out.println("id: " + personagem.getIdPersonagem());
                            System.out.println("nome: " + personagem.getNome());
                            System.out.println("nivel: " + personagem.getNivel());

                            System.out.println("Digite o novo nome (ou Enter para manter o mesmo): ");
                            String novoNome = scan.nextLine();
                            if (!novoNome.isEmpty()) {
                                personagem.setNome(novoNome);
                            }

                            System.out.println("Digite o novo nivel (ou Enter para manter o mesmo): ");
                            String novoNivel = scan.nextLine();
                            if (!novoNivel.isEmpty()) {
                                int nivel = Integer.parseInt(novoNivel);
                                personagem.setNivel(nivel);
                            }

                            if (personagemDAO.update(personagem) > 0) {
                                System.out.println("\nPersonagem atualizado com sucesso! :)");
                            } else {
                                System.out.println("\nErro: operacao nao funcionou como esperado");
                            }
                        } else {
                            System.out.println("\nPersonagem não encontrado.");
                        }
                        break;
                    case 7:
                        System.out.println("\nDeletar personagem\n");
                        listaPersonagens = contaPersonagemDAO.buscarPersonagemConta(loggedAccount.getIdConta());
                        if(!listaPersonagens.isEmpty()){
                            for(int i = 0; i < listaPersonagens.size(); i++){
                                personagem = listaPersonagens.get(i);

                                System.out.println("id: "+ personagem.getIdPersonagem());
                                System.out.println("nome: "+ personagem.getNome());
                                System.out.println("nivel: "+ personagem.getNivel());
                                System.out.println("_______________________");
                            }
                        } else {
                            System.out.println("Conta nao tem personagem vinculado :(");
                        }
                        System.out.println("Digite o id do personagem que deseja deletar: ");
                        String aux1 = scan.nextLine();
                        int id = Integer.parseInt(aux1);

                        System.out.println("Insira a senha para prosseguir: ");
                        snha = scan.nextLine();
                        if (loggedAccount.getSenha().equals(snha)) {
                            contaPersonagemDAO.desassociarPersonagemConta(loggedAccount.getIdConta(), id);    
                            personagemRacaDAO.desassociarRacaPersonagem(id);         
                            personagemDAO.delete(id);
                            System.out.println("Personagem deletado com sucesso");
                        } else {
                            System.out.println("Senha incorreta. Tente novamente.");
                        }
                        break;                   
                    default:
                        System.out.println("Opcao invalida! Tente novamente x.x");
                }
            } while (op != 0);
            loggedAccount = null;
        } else {
            System.out.println("Login falhou. Encerrando o programa.");
        }
        scan.close();
    }

}


