import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Entidade Sala representa a entidade do domínio
class Sala {
    private int numero;
    private int temperatura;

    public Sala(int numero, int temperatura) {
        this.numero = numero;
        this.temperatura = temperatura;
    }

    public int getNumero() {
        return numero;
    }

    public int getTemperatura() {
        return temperatura;
    }
}

// Interface de entrada/saída
interface MonitoramentoTemperaturaPresenter {
    void apresentarResultado(String resultado);
}

// Classe DataCenter contém uma lista de salas
class DataCenter {
    private List<Sala> salas;

    public DataCenter(List<Sala> salas) {
        this.salas = salas;
    }

    public List<Sala> getSalas() {
        return salas;
    }
}

// Interface do caso de uso
interface MonitoramentoTemperaturaUseCase {
    String monitorar(DataCenter dataCenter);
}

// Implementação do caso de uso
class MonitoramentoTemperaturaUseCaseImpl implements MonitoramentoTemperaturaUseCase {
    private static final int LIMITE_TEMPERATURA = 30;

    @Override
    public String monitorar(DataCenter dataCenter) {
        StringBuilder alertas = new StringBuilder();

        // Monitoramento das temperaturas das salas
        for (Sala sala : dataCenter.getSalas()) {
            if (sala.getTemperatura() > LIMITE_TEMPERATURA) {
                alertas.append("Alerta: Sala ").append(sala.getNumero()).append(" excedeu o limite de temperatura!").append(System.lineSeparator());
            }
        }

        // Verificação se houve algum alerta
        if (alertas.length() == 0) {
            return "Nenhuma sala excedeu o limite de temperatura.";
        } else {
            return alertas.toString();
        }
    }
}

// Implementação do controlador
class MonitoramentoTemperaturaController {
    private final MonitoramentoTemperaturaUseCase useCase;
    private final MonitoramentoTemperaturaPresenter presenter;

    public MonitoramentoTemperaturaController(MonitoramentoTemperaturaUseCase useCase, MonitoramentoTemperaturaPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    public void monitorar(DataCenter dataCenter) {
        String resultado = useCase.monitorar(dataCenter);
        presenter.apresentarResultado(resultado);
    }
}

// Classe principal que funciona como interface de entrada/saída e execução do caso de uso
public class MonitoramentoTemperatura implements MonitoramentoTemperaturaPresenter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leitura do número de salas
        int numeroSalas = scanner.nextInt();

        // Leitura das temperaturas das salas
        List<Sala> salas = new ArrayList<>();
        for (int i = 0; i < numeroSalas; i++) {
            int temperatura = scanner.nextInt();
            salas.add(new Sala(i + 1, temperatura));
        }

        // Criação da entidade DataCenter
        DataCenter dataCenter = new DataCenter(salas);

        // Criação e execução do caso de uso
        MonitoramentoTemperaturaUseCase useCase = new MonitoramentoTemperaturaUseCaseImpl();

        // Criação e execução do apresentador
        MonitoramentoTemperaturaPresenter presenter = new MonitoramentoTemperatura();

        // Criação do controlador
        MonitoramentoTemperaturaController controller = new MonitoramentoTemperaturaController(useCase, presenter);
        
        // Execução do controlador
        controller.monitorar(dataCenter);

        scanner.close();
    }

    @Override
    public void apresentarResultado(String resultado) {
        System.out.println(resultado);
    }
}
