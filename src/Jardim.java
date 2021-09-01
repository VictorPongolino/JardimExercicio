import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Jardim {
    private List<Planta> plantas = new ArrayList<>();

    public void plantar(Planta planta) {
        this.plantas.add(planta);
    }

    public boolean desplantar (Planta planta) {
        return this.plantas.remove(planta);
    }

    public boolean desplantar (String nome) {
        Optional<Planta> planta = this.plantas.stream()
                .filter((Planta x) -> x.getNome().equalsIgnoreCase(nome))
                .findFirst();

        if (planta.isPresent()) {
            desplantar(planta.get());
        }

        return false;
    }

    public List<Planta> getPlantasByName (String nome) {
        List<Planta> plantas = this.plantas
                .stream()
                .filter((Planta x) -> x.getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());

        return plantas;
    }

    public int totalPlantas() {
        return this.plantas.size();
    }
}
