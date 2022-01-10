package br.com.dio;

import java.util.Scanner;

import br.com.dio.processamento.ProcessamentoClientes;

public class AnaliseCredito {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ProcessamentoClientes clientes = new ProcessamentoClientes();
        int opcaoEscolhida = 0;

        while (opcaoEscolhida != 3) {
            System.out.println("Escolha uma das opções abaixo: ");
            System.out.println("[1] CADASTRAR CLIENTE");
            System.out.println("[2] FAZER LOGIN");
            System.out.println("[3] ENCERRAR");
            opcaoEscolhida = scan.nextInt();

            if (opcaoEscolhida == 1) {
                clientes.cadastrarNovoCliente();
            } else if (opcaoEscolhida == 2){
                clientes.fazerLogin();
            } else if (opcaoEscolhida != 3){
                System.out.println("Opção inválida!!!");
            }


        }


    }
}

