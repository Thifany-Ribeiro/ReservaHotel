import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Reserva[] reservas = new Reserva[10];
        int totalReservas = 0;

        System.out.println("=== Cadastro de Funcionário ===");

        System.out.println("Nome do funcionário:");
        String nomeFunc = sc.nextLine();

        System.out.println("Salário:");
        double salario = sc.nextDouble();
        sc.nextLine();

        System.out.println("Cargo:");
        String nomeCargo = sc.nextLine();

        Cargo cargo = new Cargo(nomeCargo);
        Funcionario func = new Funcionario(nomeFunc, salario, cargo);

        System.out.println("\nFuncionário cadastrado!\n");

        int opcao;

        do {
            System.out.println("\n1 - Criar reserva");
            System.out.println("2 - Listar reservas");
            System.out.println("3 - Buscar reserva");
            System.out.println("0 - Sair");
            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao) {

                case 1:
                    System.out.println("Data entrada:");
                    String entrada = sc.nextLine();

                    System.out.println("Data saída:");
                    String saida = sc.nextLine();

                    System.out.println("Número do quarto:");
                    int numero = sc.nextInt();

                    System.out.println("Andar:");
                    int andar = sc.nextInt();
                    sc.nextLine();

                    Quarto quarto = new Quarto(numero, andar);

                    System.out.println("Possui veículo? (s/n)");
                    String resp = sc.nextLine();

                    Veiculo veiculo = null;

                    if(resp.equalsIgnoreCase("s")) {
                        System.out.println("Placa:");
                        String placa = sc.nextLine();

                        System.out.println("Modelo:");
                        String modelo = sc.nextLine();

                        veiculo = new Veiculo(placa, modelo);
                    }

                    System.out.println("Quantos hóspedes?");
                    int qtd = sc.nextInt();
                    sc.nextLine();

                    Hospede[] hospedes = new Hospede[qtd];

                    for(int i = 0; i < qtd; i++) {
                        System.out.println("\nHospede " + (i+1));

                        System.out.println("Nome:");
                        String nome = sc.nextLine();

                        System.out.println("Endereço:");
                        String endereco = sc.nextLine();

                        System.out.println("Documento:");
                        String doc = sc.nextLine();

                        hospedes[i] = new Hospede(nome, endereco, doc);
                    }

                    Reserva r = new Reserva(entrada, saida, quarto, veiculo);
                    r.checkin(hospedes);

                    reservas[totalReservas++] = r;

                    System.out.println("Reserva criada com sucesso!");
                    break;

                case 2:
                    for(int i = 0; i < totalReservas; i++) {
                        System.out.println("\n--- Reserva " + (i+1) + " ---");
                        System.out.println("Hóspedes: " + reservas[i].getQuantidadeHospedes());
                    }

                    System.out.println("\nFuncionário responsável: " + func.getNome());
                    System.out.println("Cargo: " + func.getCargo());
                    break;

                case 3:
                    System.out.println("Buscar por quantidade de hóspedes:");
                    int busca = sc.nextInt();

                    for(int i = 0; i < totalReservas; i++) {
                        if(reservas[i].getQuantidadeHospedes() == busca) {
                            System.out.println("Reserva encontrada!");
                        }
                    }
                    break;
            }

        } while(opcao != 0);

        sc.close();
    }
}