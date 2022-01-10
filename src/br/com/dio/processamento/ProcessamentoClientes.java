package br.com.dio.processamento;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.dio.cadastro.Cliente;
import br.com.dio.cadastro.Emprestimo;

public class ProcessamentoClientes {
    private List<Cliente> clientes;
    private ProcessamentoEmprestimo emprestimos = new ProcessamentoEmprestimo();

    public void cadastrarNovoCliente() {
        Scanner scan = new Scanner(System.in);


        System.out.println("Preencha as informações abaixo: ");
        Cliente cliente = new Cliente();

        System.out.println("NOME");
        cliente.setNome((scan.nextLine()));

        System.out.println("EMAIL");
        cliente.setEmail((scan.nextLine()));

        System.out.println("CPF");
        cliente.setCPF((scan.nextLine()));

        System.out.println("RG");
        cliente.setRG((scan.nextLine()));

        System.out.println("ENDERECO");
        cliente.setEndereco((scan.nextLine()));

        System.out.println("SENHA");
        cliente.setSenha((scan.nextLine()));

        try {
            salvaCliente(cliente);
            System.out.println("CADASTRO REALIZADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO CADASTRAR CLIENTE. MOTIVO: " + e.getMessage());
        }
    }

    public int salvaCliente(Cliente cliente) throws Exception {
        // se a lista ainda nao foi inicializada, crio ela
        if (clientes == null) {
            clientes = new ArrayList<Cliente>();
        }
        if (cadastroValido(cliente)) {
            //adiciona o cliente numa lista de clientes
            clientes.add(cliente);

            //retorna o código do cliente (como e o último cadastrado, retorno o tamanho da lista)
            return clientes.size();
        }
        throw new Exception("Cadastro inválido, por favor, verificar!");
    }

    public void fazerLogin() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Preencha as informações abaixo: ");
        String nome = "";
        String senha = "";

        System.out.println("NOME");
        nome = scan.nextLine();

        System.out.println("SENHA");
        senha = scan.nextLine();

        if (loginValido(nome, senha)){
            int opcaoEscolhida = 0;

            while (opcaoEscolhida != 3) {
                System.out.println("Escolha uma das opções abaixo: ");
                System.out.println("[1] SOLICITAR NOVO EMPRESTIMO");
                System.out.println("[2] LISTAR EMPRESTIMOS");
                System.out.println("[3] VOLTAR");
                opcaoEscolhida = scan.nextInt();

                if (opcaoEscolhida == 1){
                    emprestimos.cadastrarNovoEmprestimo(nome); //  chama a rotina de criar novo emprestimo
                } else if (opcaoEscolhida == 2){
                    List<Emprestimo> lista = emprestimos.listaEmprestimos(nome); // lista os empréstimos solicitados
                    if (lista == null || lista.size() == 0) {
                        System.out.println("Lista de emprestimos vazia");
                    } else {
                        for (Emprestimo emprestimo : lista) {
                            System.out.println("-----------------------------------");
                            System.out.println(emprestimo.getNomeCliente() + " \n Valor parcela: R$ " + emprestimo.getValorParcela() + "\n Parcelas: " + emprestimo.getQuantidadeParcelas() + " \n Vencimento: " + emprestimo.getPrimeiraParcela());
                            System.out.println("-----------------------------------");
                        }
                    }
                } else if (opcaoEscolhida != 3){
                    System.out.println("Opção inválida!!!");
                }


            }
        } else {
            System.out.println("Login inválido");
        }
    }

    public Cliente buscaCliente(int codigo) throws Exception {
        //se a lista nao foi inicializada ou nao inserimos nenhum cliente, retorno erro
        if (clientes == null || clientes.size() <= 0) {
            throw new Exception("Lista de clientes vazia");
        }

        //vai buscar na lista o código informado
        // obs.: coloquei código -1 pq a lista em java começa contando do 0... então as posições são 0, 1, 2...
        return clientes.get(clientes.indexOf(codigo - 1));
    }

    public List<Cliente> listaClientes() {
        //se a lista nao foi inicializada ou nao inserimos nenhum cliente, retorno erro
        if (clientes == null || clientes.size() <= 0) {
            System.out.println("Lista de clientes vazia");
        }

        return clientes;
    }

    public boolean loginValido(String nome, String senha) {
        //vai ler cada uma das linhas para achar o usuário informado
        for (Cliente cliente : clientes) {
            // se o nome for igual e a senha também, aceita o login
            if (cliente.getNome().equals(nome) && cliente.getSenha().equals(senha)) {
                return true;
            }
        }
        // se não achou, não aceita o login
        return false;
    }

    public boolean cadastroValido(Cliente cliente) {
        for (Cliente clienteLista : clientes) {
            // Se achou um nome ja cadastrado, nao deixa
            if (clienteLista.getNome().equals(cliente.getNome())) {
                return false;
            }
        }
        // qualquer outra validação do cadastro pode ser feita aqui. Por exemplo, se um campo obrigatório nao foi preenchido
        return true;
    }
}
