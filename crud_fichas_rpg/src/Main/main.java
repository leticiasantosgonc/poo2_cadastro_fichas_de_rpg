package Main;

import DAO.ContaDAO;
import DAO.RacaDAO;
import Model.Conta;
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
    
    static ContaDAO contaDAO = new ContaDAO();
    static RacaDAO racaDAO = new RacaDAO();

    public static void main(String[] args) {
        int op;
        String aux;
        
        conn = conexao.getConexaoMySQL();
        System.out.println("Bem vindo(a) ao cadastro de fichas de rpg :D");
        if(conn == null){
            System.out.println("Erro: não foi possível conectar ao banco");
        }else{
            do {
                System.out.println("\n--MENU CONTA--\n1. Inserir conta\n2. Listar contas\n3. Procurar conta\n4. Atualizar conta\n5. Deletar conta"
                        + "\n\n--MENU RACA--\n6. Inserir raca\n7. Listar racas\n8. Procurar raca\n9. Atualizar raca\n10. Deletar raca");
                System.out.println("0. Sair\nEscolha uma opcao -> ");          
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
                        System.out.println("\nLista de contas\n");
                        ArrayList<Conta> contas = contaDAO.list();
                        for (int i = 0; i < contas.size(); i++) {
                            Conta cta = contas.get(i);
                            System.out.println("id: " + cta.getIdConta());
                            System.out.println("login: " + cta.getLogin());
                            System.out.println("________________________________");
                        }   
                        break;
                    case 3:
                        //procurar uma conta
                        break;
                    case 4:
                        //alterar uma conta
                        break;
                    case 5:
                        System.out.println("\nDeletar conta\n");
                        System.out.println("Qual o id da conta?");
                        aux = scan.nextLine();                
                        op = Integer.parseInt(aux);
                        if (contaDAO.delete(op) > 0) 
                            System.out.println("\nVoce ira fazer falta :(");                         
                        break;
                    case 6:
                        System.out.println("\nCadastrar raca\n");
                        System.out.println("Digite o nome: ");
                        String nome = scan.nextLine();
                        
                        System.out.println("Digite a descricao: ");
                        String descricao = scan.nextLine();
                        
                        System.out.println("\nQuem é a fraqueza? digite o id ");
                        System.out.println("1. Paladino\n2. Arqueiro\n3. Mago\n4. Bruxo\n5. Druida\n6. Ladino\n7. Bardo");
                        aux = scan.nextLine();                
                        int fraqueza = Integer.parseInt(aux);
                        
                        if(fraqueza > 0 && fraqueza < 8){
                            System.out.println("\nDigite o id da classe: ");    
                            System.out.println("1. Paladino\n2. Arqueiro\n3. Mago\n4. Bruxo\n5. Druida\n6. Ladino\n7. Bardo");
                            aux = scan.nextLine();                
                            int classe = Integer.parseInt(aux);

                            if(classe > 0 && classe < 8){
                                Raca raca = new Raca(nome, descricao, fraqueza, classe);
                                if (racaDAO.insert(raca) > 0) {
                                    System.out.println("\nRaca cadastrada com sucesso :)!");
                                } else {
                                    System.out.println("\nErro: operação não funcionou como esperado");
                                }
                            } else { 
                                System.out.println("Inválido, tente novamente: ");
                                }
                            }
                        
                        else {
                            System.out.println("Inválido, tente novamente: ");
                        }
                        break;
                    case 7:
                        System.out.println("\nLista de racas\n");
                        ArrayList<Raca> racas = racaDAO.list();
                        for (int i = 0; i < racas.size(); i++) {
                            Raca rca = racas.get(i);
                            System.out.println("id: " + rca.getIdRaca());
                            System.out.println("nome: " + rca.getNome());
                            System.out.println("descricao: " + rca.getDescricao());
                            if(rca.getFraqueza() == 1){
                                System.out.println("fraqueza: Paladino");
                            }
                            if(rca.getFraqueza() == 2){
                                 System.out.println("fraqueza: Arqueiro");
                            }                            
                            if(rca.getFraqueza() == 3){
                                System.out.println("fraqueza: Mago");
                            }
                            if(rca.getFraqueza() == 4){
                                System.out.println("fraqueza: Bruxo");
                            }
                            if(rca.getFraqueza() == 5){
                                System.out.println("fraqueza: Druida");
                            }
                            if(rca.getFraqueza() == 6){
                                System.out.println("fraqueza: Ladino");
                            } else if(rca.getFraqueza() == 7) {
                                System.out.println("fraqueza: Bardo");
                            }
                            
                            if(rca.getClasse() == 1){
                                System.out.println("classe: Paladino");
                            }
                            if(rca.getClasse() == 2){
                                 System.out.println("classe: Arqueiro");
                            }                            
                            if(rca.getClasse() == 3){
                                System.out.println("classe: Mago");
                            }
                            if(rca.getClasse() == 4){
                                System.out.println("classe: Bruxo");
                            }
                            if(rca.getClasse() == 5){
                                System.out.println("classe: Druida");
                            }
                            if(rca.getClasse() == 6){
                                System.out.println("classe: Ladino");
                            } else if(rca.getClasse() == 7){
                                System.out.println("classe: Bardo");
                            }
                            System.out.println("________________________________");
                        } 
                        break;
                    case 8:
                        //procurar uma raca
                        break;
                    case 9:
                        //alterar raca
                        break;
                    case 10:
                        System.out.println("\nDeletar raca\n");
                        System.out.println("Qual o id da raca?");
                        aux = scan.nextLine();                
                        op = Integer.parseInt(aux);
                        if (contaDAO.delete(op) > 0) 
                            System.out.println("\nRaca não faz mais parte do mundo");                         
                        break;
                    //TODO: essa parte pode funcionar se estiver logado no sistema
                    case 11:
                        //cadastrar pesonagem
                        break;
                    case 12:
                        //listar personagens
                        break;
                    case 13:
                        //procurar um personagem
                        break;
                    case 14:
                        //alterar personagem
                        break;
                    case 15:
                        //deletar personagem
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente :)");
                }
            } while (op != 0);
        }
    scan.close();
    }      
}
