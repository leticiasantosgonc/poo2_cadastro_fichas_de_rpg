package Main;

import DAO.ContaDAO;
import DAO.ContaPersonagemDAO;
import DAO.PersonagemDAO;
import DAO.PersonagemRacaDAO;
import DAO.RacaDAO;
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

    public static void main(String[] args) {
        int op;
        String aux;
        
        conn = conexao.getConexaoMySQL();
        System.out.println("Bem vindo(a) ao cadastro de fichas de rpg :D");
        if(conn == null){
            System.out.println("Erro: não foi possível conectar ao banco");
        }else{
            do {
                System.out.println("\n----------------------------------------");
                System.out.println("|             MENU USUARIO             |");
                System.out.println("|1. Inserir conta                      |"
                        + "\n|2. Listar personagens                 |"
                        + "\n|3. Atualizar conta                    |"
                        + "\n|4. Deletar conta                      |"
                        + "\n|5. Inserir personagem                 |"
                        + "\n|6. Procurar personagem                |"
                        + "\n|7. Atualizar personagem               |"
                        + "\n|8. Deletar personagem                 |");                
                System.out.println("----------------------------------------");
                System.out.println("|0. Sair\nEscolha uma opcao -> ");       
                aux = scan.nextLine();                
                op = Integer.parseInt(aux);

                switch (op) {
                    case 0:
                        System.out.println("Volte sempre! Mantenha seus personagens atualizados! :D");
                        break;
                    case 1:   
                        System.out.println("\nCadastrar conta\n");
                        System.out.println("Digite o login: ");
                        String login = scan.nextLine();
                        
                        System.out.println("Digite a senha: ");
                        String senha = scan.nextLine();
                        
                        Conta conta = new Conta(login, senha);
                        if (contaDAO.insert(conta) > 0) {
                            System.out.println("\nBem vindo(a) 1ao sistema :)!");
                        } else {
                            System.out.println("\nErro: operação não funcionou como esperado");
                        }
                        break;
                    case 2: 
                         System.out.println("\nListar seus personagens \n");
                        System.out.println("Informe o id da conta: ");
                        aux = scan.nextLine();
                        op = Integer.parseInt(aux);
                        Conta ct = contaDAO.read(op);
                        
                        if (ct != null) {
                            System.out.println("\nDados da conta\n");
                            System.out.println("id: " + ct.getIdConta());
                            System.out.println("login: " + ct.getLogin());
                            System.out.println("________________________________");
                            
                            System.out.println("Deseja ver os personagens? 1. Sim ou 2. Nao");
                            aux = scan.nextLine();
                            op = Integer.parseInt(aux);
                            
                            if(op == 1){
                                ArrayList<Personagem> listaPersonagens = contaPersonagemDAO.buscarPersonagemConta(op);
                                if(listaPersonagens.size() > 0){
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
                            
                            } else {
                            System.out.println("\nErro: conta nao localizada! Junte-se a nos :)\n");
                            }
                        }
                         
                        break;
                    case 3:
                        System.out.println("\nAlterar conta\n");
                        System.out.println("Digite o id da conta: ");
                        aux = scan.nextLine();
                        int cod = Integer.parseInt(aux);
                        Conta cont = contaDAO.read(cod);
                        if (cont == null) {
                            System.out.println("\n Conta nao localizada em nosso servidor :(\nJunte-se a nos!!");
                        } else {
                                System.out.println("\nInforme a senha: ");
                                aux = scan.nextLine();                            
                                if(aux.equals(cont.getSenha())){
                                    System.out.println("Bem vindo(a) " + cont.getLogin());
                                    System.out.println("\n Deseja alterar login? 1. Sim ou 2. Nao");
                                    aux = scan.nextLine();
                                    int resp = Integer.parseInt(aux);
                                    
                                    if (resp == 1) {
                                        System.out.println("Digite o novo login: ");
                                        login = scan.nextLine();
                                        cont.setLogin(login);
                                        System.out.println("Alterado com sucesso " + cont.getLogin());
                                    }
                                    System.out.println("Deseja alterar a senha? 1. Sim ou 2. Nao");
                                    aux = scan.nextLine();
                                    resp = Integer.parseInt(aux);
                                    
                                    if (resp == 1) {
                                        System.out.println("Digite a nova senha: ");
                                        senha = scan.nextLine();
                                        cont.setSenha(senha);
                                    }                                
                                }
                                if (contaDAO.update(cont) > 0) {
                                    System.out.println("\nConta atualizada com sucesso! :)");
                                } else {
                                    System.out.println("\nErro: operacao nao funcionou como esperado");
                                }
                        }
                        break;
                    case 4:
                       System.out.println("\nDeletar conta\n");
                        System.out.println("Qual o id da conta?");
                        aux = scan.nextLine();                
                        op = Integer.parseInt(aux);
                        if (contaDAO.delete(op) > 0) 
                            System.out.println("\nVoce ira fazer falta :("); 
                        break;
                    case 5:
                        System.out.println("\nInsira o login: \n");
                        String lgn = scan.nextLine();
                        
                        Conta cnt = contaDAO.readLogin(lgn);
                        if(cnt != null){
                            System.out.println("Insira a senha: \n");
                            String snha = scan.nextLine();
                            
                            if(cnt.getSenha().equals(snha)){
                                System.out.println("Hora de cadastrar o seu personagem :)\n");
                                System.out.println("Informe o nome do personagem: ");
                                String nomeP = scan.nextLine();
                                
                                System.out.println("Informe o nivel do personagem: ");
                                String niv = scan.nextLine();
                                
                                op = Integer.parseInt(niv);
                                Personagem per = new Personagem(nomeP, op);
                                personagemDAO.insert(per);
                                
                                contaPersonagemDAO.associarPersonagemConta(cnt.getIdConta(), per.getIdPersonagem());
                               
                                System.out.println("Qual raca do personagem? ");
                                String nomeR = scan.nextLine();
                                
                                System.out.println("Descreva o personagem :)");
                                String desc = scan.nextLine();
                                
                                System.out.println("Qual a fraqueza? ");
                                String fraqueza = scan.nextLine();
                                int op2 = Integer.parseInt(fraqueza);
                                
                                System.out.println("Qual classe? ");
                                String classe = scan.nextLine();
                                op = Integer.parseInt(desc);
                                
                                Raca rac = new Raca(nomeR, desc, op2, op);
                                racaDAO.insert(rac);
                                personagemRacaDAO.associarRacaPersonagem(per.getIdPersonagem(), rac.getIdRaca());
                                
                            } else {
                                System.out.println("Senha invalida!");
                            }
                            
                        } else{ 
                            System.out.println("Conta nao existe :(\nCadastre-se para ter acesso ao sistema");
                        }                         
                        break;
                    case 6:   
                        //procurar personagem
                        break;
                    case 7:  
                        //alterar personagem
                        break;
                    case 8:
                        //deletar personagem 
                        break;                   
                    default:
                        System.out.println("Opcao invalida! Tente novamente :)");
                }
            } while (op != 0);
        }
    scan.close();
    }      
}
