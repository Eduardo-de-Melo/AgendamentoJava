import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class Agendamento {
    private Barbeiro barbeiro;
    private Scanner scanner;

    public Agendamento() {
        this.barbeiro = Barbeiro.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public void iniciarAgendamento() {
        System.out.print("Digite o seu nome: ");
        String nomeUsuario = scanner.nextLine();
        Usuario usuario = new Usuario(nomeUsuario);

        do {
            barbeiro.DiasSemana();
            System.out.print("Escolha o dia da semana (1-5): ");
            int opcaoDia = scanner.nextInt();

            if (opcaoDia >= 1 && opcaoDia <= 5) {
                String diaEscolhido = obterDiaDaSemana(opcaoDia);
                barbeiro.mostrarHorariosDisponiveis(diaEscolhido);

                System.out.print("Digite o horário desejado (HH:mm): ");
                String horarioDesejado = scanner.next();

                try {
                    new SimpleDateFormat("HH:mm").parse(horarioDesejado);
                    boolean sucesso = barbeiro.marcarHorario(usuario, horarioDesejado, diaEscolhido);

                    if (sucesso) {
                        System.out.println("Horário marcado com sucesso para " + diaEscolhido + "!");
                    }
                } catch (ParseException e) {
                    System.out.println("Formato de horário inválido. Use o formato HH:mm.");
                }
            } else {
                System.out.println("Opção inválida. Escolha um número de 1 a 5.");
            }

            System.out.print("Gostaria de marcar outro horário? (s/n): ");
        } while (scanner.next().equalsIgnoreCase("s"));

        scanner.close();
    }

    private static String obterDiaDaSemana(int opcao) {
        switch (opcao) {
            case 1:
                return "Segunda-feira";
            case 2:
                return "Terça-feira";
            case 3:
                return "Quarta-feira";
            case 4:
                return "Quinta-feira";
            case 5:
                return "Sexta-feira";
            default:
                return "";
        }
    }
}
