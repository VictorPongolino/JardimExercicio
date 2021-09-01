import java.util.*;

abstract class Command {
    public final String DESCRICAO;
    public Command (String descricao) {
        this.DESCRICAO = descricao;
    }

    public abstract void create();
}


public final class MenuCommand {
    private final Map<String, Command> SUB_MENUS;
    private final Jardim jardim;

    public MenuCommand(Jardim jardim) {
        this.jardim = jardim;
    }

    {
        final Map<String, Command> regCmd = new HashMap<>();
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

        regCmd.put("4", new Command("Para pegar o número total de plantas") {
            @Override
            public void create() {
                System.out.println("No momento você tem " + jardim.totalPlantas() + " plantas em seu jardim!");
            }
        });

        SUB_MENUS = Collections.unmodifiableMap(regCmd);
    }

    public void printMenu() {
        for (Map.Entry<String, Command> it : this.SUB_MENUS.entrySet()) {
            System.out.println(it.getKey() + " - " + it.getValue().DESCRICAO);
        }
    }


    public boolean callSubmenu (String sub_menu) {
        Command command = this.SUB_MENUS.get(sub_menu);
        if (command == null) {
            return false;
        }

        command.create();
        return true;
    }
}
