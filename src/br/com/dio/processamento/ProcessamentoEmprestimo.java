package br.com.dio.processamento;

import br.com.dio.cadastro.Emprestimo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProcessamentoEmprestimo {
    private List<Emprestimo> emprestimos;

    public void cadastrarNovoEmprestimo(String nomeCliente) {
        Scanner scan = new Scanner(System.in);
        ProcessamentoEmprestimo processamento = new ProcessamentoEmprestimo();

        double valorParcela;
        double renda;
        Date primeiraParcela = null;
        int quantidadeParcelas;
        double valorEmprestimo;
        int prazoPrimeiraParcela;

        System.out.println("Preencha as informações abaixo: ");
        Emprestimo emprestimo = new Emprestimo();

        emprestimo.setNomeCliente(nomeCliente);

        System.out.println("VALOR DO EMPRÉSTIMO:");
        valorEmprestimo = (scan.nextDouble());
        emprestimo.setValor(valorEmprestimo);

        System.out.println("PRAZO PARA VENCIMENTO DA PRIMEIRA PARCELA (MÁXIMO 90 DIAS): ");
        prazoPrimeiraParcela = scan.nextInt();

        while (prazoPrimeiraParcela > 90) {
            System.out.println("Opção Inválida! Máximo 90 dias. \n Digite o prazo para vencimento da primeira parcela");
            prazoPrimeiraParcela = scan.nextInt();
        }
        LocalDate dataPrimeiraParcela = LocalDate.now().plusDays(prazoPrimeiraParcela);;
        emprestimo.setPrimeiraParcela(dataPrimeiraParcela);

        System.out.println("QUANTIDADE DE PARCELAS (MÁXIMO 60): ");
        quantidadeParcelas = scan.nextInt();

        while (quantidadeParcelas > 60) {
            System.out.println("Opção Inválida! Máximo 60 parcelas. \n Digite a quantidade e parcelas");
            quantidadeParcelas = scan.nextInt();
        }

        emprestimo.setQuantidadeParcelas(quantidadeParcelas);

        valorParcela = valorEmprestimo * 1.2 / quantidadeParcelas;
        System.out.println("Valor da parcela: R$ " + valorParcela);
        emprestimo.setValorParcela(valorParcela);

        salvaEmprestimo(emprestimo);
    }

    public int salvaEmprestimo(Emprestimo emprestimo) {
        // se a lista ainda nao foi inicializada, crio ela
        if (emprestimos == null) {
            emprestimos = new ArrayList<Emprestimo>();
        }
        emprestimos.add(emprestimo);
        return emprestimos.size();
    }

    public List<Emprestimo> listaEmprestimos(String nomeCliente) {
        List<Emprestimo> emprestimosCliente = new ArrayList<Emprestimo>();
        // Aqui estou percorrendo toda a lista de empréstimos feitos para capturar os empréstimos do cliente que
        // está solicitando a visualização. Por isso que criei outra lista internamente para conseguir guardar

        for (Emprestimo emprestimo : emprestimos){
            if (emprestimo.getNomeCliente().equals(nomeCliente)){
                emprestimosCliente.add(emprestimo);
            }
        }

        return emprestimosCliente;
    }
}