package almoxarifado.view;

import almoxarifado.dao.ItemEstoqueDao;
import almoxarifado.model.ItemEstoque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ItemEstoqueDao dao = new ItemEstoqueDao();
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n--- MENU ALMOXARIFADO ---");
            System.out.println("1 - Cadastrar item");
            System.out.println("2 - Listar itens");
            System.out.println("3 - Buscar por código");
            System.out.println("4 - Atualizar item");
            System.out.println("5 - Remover item");
            System.out.println("6 - Buscar por nome");
            System.out.println("7 - Repor estoque");
            System.out.println("8 - Baixar estoque");
            System.out.println("9 - Listar abaixo do mínimo");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int opcao = sc.nextInt();
            sc.nextLine(); // consumir ENTER

            switch (opcao) {
                case 1 -> {
                    System.out.print("Código: ");
                    int cod = sc.nextInt(); sc.nextLine();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Categoria: ");
                    String cat = sc.nextLine();
                    System.out.print("Unidade de medida: ");
                    String un = sc.nextLine();
                    System.out.print("Localização: ");
                    String loc = sc.nextLine();
                    System.out.print("Quantidade: ");
                    int qtd = sc.nextInt();
                    System.out.print("Estoque mínimo: ");
                    int min = sc.nextInt();

                    try {
                        dao.cadastrar(new ItemEstoque(cod, nome, cat, un, loc, qtd, min));
                        System.out.println("✅ Item cadastrado!");
                    } catch (Exception e) {
                        System.out.println("⚠ Erro: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("--- ITENS CADASTRADOS ---");
                    dao.listar().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Código: ");
                    int cod = sc.nextInt();
                    ItemEstoque item = dao.buscarPorCodigo(cod);
                    System.out.println(item != null ? item : "❌ Item não encontrado.");
                }
                case 4 -> {
                    System.out.print("Código do item a atualizar: ");
                    int cod = sc.nextInt(); sc.nextLine();
                    System.out.print("Novo nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Nova categoria: ");
                    String cat = sc.nextLine();
                    System.out.print("Nova unidade: ");
                    String un = sc.nextLine();
                    System.out.print("Nova localização: ");
                    String loc = sc.nextLine();
                    System.out.print("Nova quantidade: ");
                    int qtd = sc.nextInt();
                    System.out.print("Novo estoque mínimo: ");
                    int min = sc.nextInt();

                    boolean ok = dao.atualizar(cod, nome, cat, un, loc, qtd, min);
                    System.out.println(ok ? "✅ Atualizado!" : "❌ Item não encontrado.");
                }
                case 5 -> {
                    System.out.print("Código do item a remover: ");
                    int cod = sc.nextInt();
                    boolean ok = dao.remover(cod);
                    System.out.println(ok ? "✅ Removido!" : "❌ Item não encontrado.");
                }
                case 6 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    var encontrados = dao.buscarPorNome(nome);
                    if (encontrados.isEmpty()) {
                        System.out.println("❌ Nenhum item encontrado.");
                    } else {
                        encontrados.forEach(System.out::println);
                    }
                }
                case 7 -> {
                    System.out.print("Código: ");
                    int cod = sc.nextInt();
                    System.out.print("Quantidade a repor: ");
                    int qtd = sc.nextInt();
                    boolean ok = dao.repor(cod, qtd);
                    System.out.println(ok ? "✅ Estoque atualizado!" : "❌ Item não encontrado.");
                }
                case 8 -> {
                    System.out.print("Código: ");
                    int cod = sc.nextInt();
                    System.out.print("Quantidade a baixar: ");
                    int qtd = sc.nextInt();
                    try {
                        boolean ok = dao.baixar(cod, qtd);
                        System.out.println(ok ? "✅ Estoque atualizado!" : "❌ Item não encontrado.");
                    } catch (Exception e) {
                        System.out.println("⚠ Erro: " + e.getMessage());
                    }
                }
                case 9 -> {
                    System.out.println("--- ITENS ABAIXO DO MÍNIMO ---");
                    var lista = dao.listarAbaixoDoMinimo();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum item precisa reposição.");
                    } else {
                        lista.forEach(System.out::println);
                    }
                }
                case 0 -> rodando = false;
                default -> System.out.println("❌ Opção inválida.");
            }
        }
        sc.close();
    }
}
