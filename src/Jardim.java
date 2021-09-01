import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/***
 * Representa um Jardim que conterá todas as plantas.
 */
public class Jardim {
    private List<Planta> plantas = new ArrayList<>();

    /***
     * Adiciona uma nova planta no jardim.
     * @param planta uma instância de Planta a ser adicionado.
     */
    public void plantar(Planta planta) {
        this.plantas.add(planta);
    }

    /***
     * Remove uma planta
     * @param planta a instância a ser removida.
     * @return retorna verdadeiro se
     */
    public boolean desplantar (Planta planta) {
        return this.plantas.remove(planta);
    }

    /***
     * Remove a primeira planta que encontrar com base em um nome.
     * Não é Case sensitive.
     * @param nome
     * @return
     */
    public boolean desplantar (String nome) {
        Optional<Planta> planta = this.plantas.stream()
                .filter((Planta x) -> x.getNome().equalsIgnoreCase(nome))
                .findFirst();

        if (planta.isPresent()) {
            desplantar(planta.get());
        }

        return false;
    }

    /**
     * Filtra e retorna todas as plantas com o nome informado.
     * Ignora case sensitive.
     * @param nome nome da planta
     * @return retorna uma Lista de Plantas encontradas.
     */
    public List<Planta> getPlantasByName (String nome) {
        List<Planta> plantas = this.plantas
                .stream()
                .filter((Planta x) -> x.getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());

        return plantas;
    }

    /***
     * Retorna o numero total sem filtro se plantas plantadas.
     * @return o número total de plantas plantadas.
     */
    public int totalPlantas() {
        return this.plantas.size();
    }
}
