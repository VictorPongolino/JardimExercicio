import java.util.Arrays;
import java.util.List;

public class JardimTest {
    public static void main(String[] args) {
        List<String> nomesPlantas = Arrays.asList("Bananeira", "Caju", "Videira", "Bananeira", "Bananeira", "Videira", "Videira", "Videira", "Tomate");
        Jardim jardim = new Jardim();
        for(String nomePlanta : nomesPlantas)
            jardim.plantar(new Planta(nomePlanta));

        // ---
        jardim.printPlantas();
    }
}
