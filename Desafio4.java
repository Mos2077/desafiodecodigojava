import java.util.Scanner;

// Classe Produto
class Produto {
    private String nome;
    private int quantidadeEstoque;

    public Produto(String nome, int quantidadeEstoque) {
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
}

// Interface EstoqueStrategy:
interface EstoqueStrategy {
    String verificar(Produto produto);
}

// Classe VerificarEstoqueStrategy que implementa a interface EstoqueStrategy
class VerificarEstoqueStrategy implements EstoqueStrategy {
    private static final int LIMITE_MINIMO_ESTOQUE = 10;

    @Override
    public String verificar(Produto produto) {
        if (produto.getQuantidadeEstoque() < LIMITE_MINIMO_ESTOQUE) {
            return "Alerta: Estoque baixo de " + produto.getNome();
        } else {
            return "Estoque de " + produto.getNome() + " esta em nivel adequado";
        }
    }
}

// Classe principal
public class VerificacaoEstoque {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String nomeProduto = scanner.nextLine();
        int quantidadeEstoque = scanner.nextInt();

        // Criação da entidade Produto
        Produto produto = new Produto(nomeProduto, quantidadeEstoque);

        // Criação e execução da estratégia de verificação de estoque
        EstoqueStrategy estoqueStrategy = new VerificarEstoqueStrategy(); 
        String resultado = estoqueStrategy.verificar(produto);

        System.out.println(resultado);

        scanner.close();
    }
}
