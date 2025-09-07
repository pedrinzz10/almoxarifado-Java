package almoxarifado.model;

import java.util.Objects;

public class ItemEstoque {
    private final int codigo;
    private String nome;
    private String categoria;
    private String unidadeMedida;
    private String localizacao;
    private int quantidade;
    private int estoqueMinimo;

    public ItemEstoque(int codigo, String nome, String categoria,
                       String unidadeMedida, String localizacao,
                       int quantidade, int estoqueMinimo) {
        if (codigo <= 0) throw new IllegalArgumentException("Código deve ser maior que 0");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome obrigatório");
        if (unidadeMedida == null || unidadeMedida.isBlank()) throw new IllegalArgumentException("Unidade de medida obrigatória");
        if (quantidade < 0) throw new IllegalArgumentException("Quantidade não pode ser negativa");
        if (estoqueMinimo < 0) throw new IllegalArgumentException("Estoque mínimo não pode ser negativo");

        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.unidadeMedida = unidadeMedida;
        this.localizacao = localizacao;
        this.quantidade = quantidade;
        this.estoqueMinimo = estoqueMinimo;
    }

    // ---------- getters e setters () ----------

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório");
        }
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        // categoria pode ser nula ou vazia — opcional.
        this.categoria = categoria;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        if (unidadeMedida == null || unidadeMedida.isBlank()) {
            throw new IllegalArgumentException("Unidade obrigatória");
        }
        this.unidadeMedida = unidadeMedida;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        this.quantidade = quantidade;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        if (estoqueMinimo < 0) {
            throw new IllegalArgumentException("Estoque mínimo não pode ser negativo");
        }
        this.estoqueMinimo = estoqueMinimo;
    }

    // ---------- métodos de negócio () ----------

    public void repor(int qtd) {
        if (qtd <= 0) throw new IllegalArgumentException("Reposição deve ser > 0");
        this.quantidade += qtd;
    }

    public void baixar(int qtd) {
        if (qtd <= 0) throw new IllegalArgumentException("Baixa deve ser > 0");
        if (qtd > this.quantidade) throw new IllegalArgumentException("Sem saldo suficiente para baixa");
        this.quantidade -= qtd;
    }

    public boolean abaixoDoMinimo() {
        return this.quantidade < this.estoqueMinimo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemEstoque)) return false;
        ItemEstoque that = (ItemEstoque) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return String.format(
                "#%d - %s (%s) | %d %s | mín: %d | loc: %s%s",
                codigo,
                nome,
                (categoria != null && !categoria.isBlank()) ? categoria : "sem categoria",
                quantidade,
                unidadeMedida,
                estoqueMinimo,
                (localizacao != null && !localizacao.isBlank()) ? localizacao : "N/D",
                abaixoDoMinimo() ? " ⚠ abaixo do mínimo" : ""
        );
    }
}