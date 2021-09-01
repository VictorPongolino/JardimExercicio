import java.util.*;

/***
 * Uma classe abstrata que registra uma operação e sua breve descrição do que será realizada nesta operação.
 * Não foi utilizado interface por conta de que em interfaces os campos são estáticos finais e o objetivo é poder
 * cadastrar comandos de instâncias independentes.
 */
abstract class Command {
    /***
     * Uma descrição breve do que o comando irá realizar.
     */
    public final String DESCRICAO;
    public Command (String descricao) {
        this.DESCRICAO = descricao;
    }

    /***
     * Cria um comando, uma vez buscado e encontrado a operação, será este método que será chamado.
     */
    public abstract void create();
}

/***
 * Uma classe que contem um Map contendo a Operação e o seu respectivo Comando.
 * Encapsula as operações dentro da classe.
 */
public final class MenuCommand {
    private final Map<String, Command> SUB_MENUS;
    private final Jardim jardim;

    public MenuCommand(Jardim jardim) {
        this.jardim = jardim;
    }

    {
        // Um Map temporário para registrar todos os conteúdos antes de depositar em um Map FINAL.
        final Map<String, Command> regCmd = new HashMap<>();
        /*
         * Operações Encapsuladas em um HashMap;
         * Lembre-se de que não é aceito valores duplicados.
         * */

        // Comando
        regCmd.put("1", new Command("Para adicionar uma planta nova") {
            @Override
            public void create() {
                Scanner sc = new Scanner(System.in);
                System.out.println("Informe o nome de sua planta!");
                String nomePlanta = sc.nextLine();

                Planta planta = new Planta(nomePlanta);
                jardim.plantar(planta);
            }
        });

        // Comando
        regCmd.put("2", new Command("Para remover uma planta pelo nome") {
            @Override
            public void create() {
                Scanner sc = new Scanner(System.in);
                System.out.println("Informe o nome de sua planta!");
                String nomePlanta = sc.nextLine();
                if (jardim.desplantar(nomePlanta)) {
                    System.out.println("~~> Removido a planta cujo nome '" + nomePlanta + "'");
                } else {
                    System.out.println("~X~> Não foi removido a planta cujo nome '" + nomePlanta + "'");
                }
            }
        });

        // Comando
        regCmd.put("3", new Command("Para pegar o número total de plantas pelo nome") {
            @Override
            public void create() {
                Scanner sc = new Scanner(System.in);
                System.out.println("Informe o nome de sua planta!");
                String nomePlanta = sc.nextLine();
                List<Planta> plantas = jardim.getPlantasByName(nomePlanta);
                System.out.println("No momento você tem " + plantas.size() + " plantas do tipo '" + nomePlanta + "'");
            }
        });

        // Comando
        regCmd.put("4", new Command("Para pegar o número total de plantas") {
            @Override
            public void create() {
                System.out.println("No momento você tem " + jardim.totalPlantas() + " plantas em seu jardim!");
            }
        });

        // Registra todos os comandos em uma lista final não modificável.
        SUB_MENUS = Collections.unmodifiableMap(regCmd);
    }


    /***
     * Imprime o catalogo do menu.
     */
    public void printMenu() {
        for (Map.Entry<String, Command> it : this.SUB_MENUS.entrySet()) {
            System.out.println(it.getKey() + " - " + it.getValue().DESCRICAO);
        }
    }

    /***
     * Chama um comando encapsulado.
     * @param sub_menu uma operação a ser buscada
     * @return verdadeiro se a operação foi encontrada, false do contrário.
     */
    public boolean callSubmenu (String sub_menu) {
        Command command = this.SUB_MENUS.get(sub_menu);
        if (command == null) {
            return false;
        }

        command.create();
        return true;
    }
}
