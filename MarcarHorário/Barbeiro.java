import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Barbeiro {
    private ArrayList<String> horariosDisponiveis;
    private ArrayList<Usuario> usuarios;
    private static Barbeiro instance = null;

    private Barbeiro() {
        horariosDisponiveis = new ArrayList<>();
        usuarios = new ArrayList<>();
        inicializarHorarios();
    }

    public static Barbeiro getInstance() {
        if (instance == null) {
            instance = new Barbeiro();
            instance.carregarAgendamentos();
        }
        return instance;
    }

    private void inicializarHorarios() {
        for (int hora = 8; hora < 18; hora++) {
            for (int minuto = 0; minuto < 60; minuto += 30) {
                String horario = String.format("%02d:%02d", hora, minuto);
                horariosDisponiveis.add(horario);
            }
        }
    }

    public void DiasSemana() {
        System.out.println("Escolha um dia da semana:");
        System.out.println("1. Segunda-feira");
        System.out.println("2. Terça-feira");
        System.out.println("3. Quarta-feira");
        System.out.println("4. Quinta-feira");
        System.out.println("5. Sexta-feira");
    }

    public void mostrarHorariosDisponiveis(String diaEscolhido) {
        System.out.println("Horários disponíveis para " + diaEscolhido + ":");
        for (String horario : horariosDisponiveis) {
            System.out.print(horario + " ");
        }
        System.out.println();
    }

    public boolean marcarHorario(Usuario usuario, String horario, String diaEscolhido) {
        if (horariosDisponiveis.contains(horario) && usuario.getHorarioMarcado().isEmpty()) {
            for (Usuario u : usuarios) {
                if (u.getHorarioMarcado().equals(horario)) {
                    System.out.println("Horário já marcado por outro usuário. Escolha outro horário.");
                    return false;
                }
            }

            usuario.marcarHorario(horario);
            horariosDisponiveis.remove(horario);
            usuarios.add(usuario);
            salvarAgendamentos();  // Adiciona essa linha para salvar após cada marcação
            return true;
        } else {
            System.out.println("Horário indisponível. Escolha outro horário.");
            return false;
        }
    }

    public ArrayList<String> listarAgendamentos() {
        ArrayList<String> agendamentos = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            agendamentos.add(usuario.toString());
        }
        return agendamentos;
    }

    public List<String> listarTodosHorarios() {
        List<String> todosHorarios = new ArrayList<>();
        todosHorarios.addAll(horariosDisponiveis);
        for (Usuario usuario : usuarios) {
            todosHorarios.add(usuario.getHorarioMarcado());
        }
        return todosHorarios;
    }

    private void salvarAgendamentos() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("agendamentos.txt"))) {
            for (Usuario usuario : usuarios) {
                writer.println(usuario.getNome() + "," + usuario.getHorarioMarcado());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarAgendamentos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("agendamentos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Usuario usuario = new Usuario(parts[0]);
                    usuario.marcarHorario(parts[1]);
                    usuarios.add(usuario);
                    horariosDisponiveis.remove(parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}