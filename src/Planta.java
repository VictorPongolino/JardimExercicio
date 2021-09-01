import java.util.Objects;

public class Planta {
    private String nome;

    public Planta (String nome) {
        this.setNome(nome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Planta planta = (Planta) o;
        return Objects.equals(nome, planta.nome);
    }

    @Override
    public int hashCode() {
        return nome != null ? nome.hashCode() : 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
