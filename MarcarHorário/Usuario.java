class Usuario {
    private String nome;
    private String horarioMarcado;

    public Usuario(String nome) {
        this.nome = nome;
        this.horarioMarcado = "";
    }

    public void marcarHorario(String horario) {
        this.horarioMarcado = horario;
        System.out.println("Seu horário foi marcado!\n" + this.nome + " - " + horario);
    }

    public String getHorarioMarcado() {
        return this.horarioMarcado;
    }

    public String getNome() {
        return this.nome;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Horário Marcado: " + horarioMarcado;
    }
}