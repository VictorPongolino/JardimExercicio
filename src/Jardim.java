import java.util.*;
import java.util.function.Function;
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
     * Obtem todas a instância de plantas não modificável.
     * @return um nova lista com todas as plantas, não modifivável.
     */
    public List<Planta> getPlantas () {
        return Collections.unmodifiableList(this.plantas);
    }

    /***
     * Remove uma planta
     * @param planta a instância a ser removida.
     * @return verdadeiro se a instância passada foi removida com sucesso.
     */
    public boolean desplantar (Planta planta) {
        return this.plantas.remove(planta);
    }

    /***
     * Remove a primeira planta que encontrar com base em um nome.
     * Não é Case sensitive.
     * @param nome da planta a ser removida do jardim
     * @return verdadeiro se foi removida com sucesso, se não, false.
     */
    public boolean desplantar (String nome) {
        Optional<Planta> planta = this.plantas.stream()
                .filter((Planta x) -> x.getNome().equalsIgnoreCase(nome))
                .findFirst();

        if (planta.isPresent()) {
            return desplantar(planta.get());
        }

        return false;
    }

    public void removerPlantas() {
        this.plantas.clear();
        System.out.println("Todas as plantas de seu Jardim foram removidas !");
    }

    /**
     * Filtra e retorna todas as plantas com o nome informado.
     * Ignora case sensitive.
     * @param nome da planta
     * @return Lista de Plantas encontradas.
     */
    public List<Planta> getPlantasByName (String nome) {
        List<Planta> plantas = this.plantas
                .stream()
                .filter((Planta x) -> x.getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());

        return plantas;
    }

    /***
     * Informa todas as plantas de forma não-repetida seguido de seu número total de repetições.
     */
    public void printPlantas() {
        List<Planta> plantasUnique = this.plantas.stream()
                .distinct()
                .collect(Collectors.toList());

        if (plantasUnique.size() == 0) {
            System.out.println("~X~> Não existe nenhuma planta plantada em seu Jardim :( ");
            return;
        }

        for (Planta planta : plantasUnique) {
            String nomePlanta = planta.getNome();
            int quantidade = getPlantasByName(nomePlanta).size();
            System.out.println("Você tem " + quantidade + " do tipo " + planta.getNome() + " plantadas !");
        }

        System.out.println("\n");
    }



    /***
     * Retorna o numero total sem filtro se plantas plantadas.
     * @return o número total de plantas plantadas.
     */
    public int totalPlantas() {
        return this.plantas.size();
    }

}
