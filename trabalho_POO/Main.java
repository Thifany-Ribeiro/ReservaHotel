import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Reserva[] reservas = new Reserva[2];
        int totalReservas = 0;

         String nomeFunc;
        double salario;
        String nomeCargo;

        do { 
            System.out.println("=== Cadastro de Funcionário ===");

            System.out.println("Nome do funcionário:");
            nomeFunc = sc.nextLine();

            do { 
                System.out.println("Salário:");
            salario = sc.nextDouble();
            sc.nextLine();
            if(salario <= 0) {
                System.out.println("Salário inválido!");
            }   

            } while (salario <= 0);

            System.out.println("Cargo:");
            nomeCargo = sc.nextLine();

        } while (nomeCargo.isEmpty() || nomeFunc.isEmpty());

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
                    Reserva r = criarReserva(sc, null, null, null, null);   
                    Hospede[] hospedes = r.getHospedes();

                    if(totalReservas < reservas.length) {

                        System.out.println("Reserva realizada para " + hospedes.length + " pessoas");

                        reservas[totalReservas++] = r;
                        System.out.println("Reserva criada com sucesso!");   

                    } else {
                        System.out.println("Limite de reservas atingido!");
                    }
                    break;


                case 2:

                    if(totalReservas == 0) {
                        System.out.println("Nenhuma reserva cadastrada!");
                        
                    } else {
                        Main.listarReservas(reservas, totalReservas, func);
                    }
                    break;

                case 3:
                    if(totalReservas == 0) {
                        System.out.println("Nenhuma reserva cadastrada!");
                    } else {
                        System.out.println("Quantos hóspedes?");
                        int qtd = sc.nextInt();
                        sc.nextLine();
                        Main.buscarReserva(reservas, totalReservas, qtd);
                    }
                    break;
            }

        } while(opcao != 0);

        sc.close();
    }

    public static Reserva criarReserva(Scanner sc, String dataEntrada, String dataSaida, Quarto quarto, Veiculo veiculo ) {
          String entrada;
            String saida = null;
            int numero;
            int andar;
            String resp;
            int qtd;

            // DATA ENTRADA
            boolean valido = false;

            do {
                System.out.println("Data entrada (dd/mm):");
                entrada = sc.nextLine();

                try {
                    String[] partes = entrada.split("/");

                    int dia = Integer.parseInt(partes[0]);
                    int mes = Integer.parseInt(partes[1]);

                    if(dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12) {
                        valido = true;
                    } else {
                        System.out.println("Data inválida!");
                    }

                } catch(Exception e) {
                    System.out.println("Formato inválido!");
                }

            } while(!valido);
            valido = false;
            do {
                System.out.println("Data de saida(dd/mm):");
                saida = sc.nextLine();

                try {
                    String[] partes = saida.split("/");

                    int dia = Integer.parseInt(partes[0]);
                    int mes = Integer.parseInt(partes[1]);

                    if(dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12) {
                        valido = true;
                    } else {
                        System.out.println("Data inválida!");
                    }

                } catch(Exception e) {
                    System.out.println("Formato inválido!");
                }

            } while(!valido);
            // NÚMERO DO QUARTO
            do {
                System.out.println("Número do quarto:");
                numero = sc.nextInt();

                if(numero <= 0) {
                    System.out.println("Número inválido!");
                }
            } while(numero <= 0);

            // ANDAR
            do {
                System.out.println("Andar:");
                andar = sc.nextInt();

                if(andar <= 0) {
                    System.out.println("Andar inválido!");
                }
            } while(andar <= 0);

            sc.nextLine();

            quarto = new Quarto(numero, andar);

            // VEÍCULO
            do {
                System.out.println("Possui veículo? (s/n)");
                resp = sc.nextLine();

            } while(!resp.equalsIgnoreCase("s") && !resp.equalsIgnoreCase("n"));

            veiculo = null;

            if(resp.equalsIgnoreCase("s")) {

                String placa;
                String modelo;

                do {
                    System.out.println("Placa:");
                    placa = sc.nextLine();

                    if(placa.isEmpty()) {
                        System.out.println("Placa inválida!");
                    }
                } while(placa.isEmpty());

                do {
                    System.out.println("Modelo:");
                    modelo = sc.nextLine();

                    if(modelo.isEmpty()) {
                        System.out.println("Modelo inválido!");
                    }
                } while(modelo.isEmpty());

                veiculo = new Veiculo(placa, modelo);
            }

            // QUANTIDADE DE HÓSPEDES
            do {
                System.out.println("Quantos hóspedes?");
                qtd = sc.nextInt();

                if(qtd <= 0) {
                    System.out.println("Quantidade inválida!");
                }
            } while(qtd <= 0);

            sc.nextLine();

            Hospede[] hospedes = new Hospede[qtd];

            // DADOS DOS HÓSPEDES
            for(int i = 0; i < qtd; i++) {

                String nome;
                String endereco;
                String doc;

                System.out.println("\nHospede " + (i+1));

                do {
                    System.out.println("Nome:");
                    nome = sc.nextLine();

                    if(nome.isEmpty()) {
                        System.out.println("Nome inválido!");
                    }
                } while(nome.isEmpty());

                do {
                    System.out.println("Endereço:");
                    endereco = sc.nextLine();

                    if(endereco.isEmpty()) {
                        System.out.println("Endereço inválido!");
                    }
                } while(endereco.isEmpty());

                do {
                    System.out.println("Documento:");
                    doc = sc.nextLine();

                    if(doc.isEmpty()) {
                        System.out.println("Documento inválido!");
                    }
                } while(doc.isEmpty());

                hospedes[i] = new Hospede(nome, endereco, doc);
            }

            Reserva r = new Reserva(entrada, saida, quarto, veiculo);
                r.checkin(hospedes);
            return r;
            
    }
    public static void buscarReserva(Reserva[] reservas, int totalReservas, int qtdHospedes) {
        for(int i = 0; i < totalReservas; i++) {
            if(reservas[i].getQuantidadeHospedes() == qtdHospedes) {

                System.out.println("\n--- Reserva encontrada ---");
                System.out.println("Entrada: " + reservas[i].getDataEntrada());
                System.out.println("Saída: " + reservas[i].getDataSaida());
                System.out.println("Quarto: " + reservas[i].getQuarto().getNumero());

                Hospede[] h = reservas[i].getHospedes();

                for(int j = 0; j < h.length; j++) {

                    System.out.println("Hospede: " + h[j].getNome());

                }
                if(reservas[i].getVeiculo() != null) {

                    System.out.println("Veículo: " + reservas[i].getVeiculo().getPlaca());

                } else {

                    System.out.println("Sem veículo");

                }
            }
        }
    }
    public static void listarReservas(Reserva[] reservas, int totalReservas, Funcionario func) {

        for(int i = 0; i < totalReservas; i++) {

            System.out.println("\n--- Reserva " + (i+1) + " ---");
            System.out.println("Entrada: " + reservas[i].getDataEntrada());
            System.out.println("Saída: " + reservas[i].getDataSaida());
            System.out.println("Quarto: " + reservas[i].getQuarto().getNumero());
            System.out.println("Hóspedes:");

            Hospede[] h = reservas[i].getHospedes();

            for(int j = 0; j < h.length; j++) {

                System.out.println(" - " + h[j].getNome());
                System.out.println(" Endereço: " + h[j].getEndereco());
                System.out.println(" Documento: " + h[j].getDocumento());

            }
            if(reservas[i].getVeiculo() != null) {

                System.out.println("Veículo: " + reservas[i].getVeiculo().getPlaca());

            } else {

                System.out.println("Sem veículo");

            }
        }
        System.out.println("\nFuncionário: " + func.getNome());
        System.out.println("Cargo: " + func.getCargo().getCargo());
    }
}