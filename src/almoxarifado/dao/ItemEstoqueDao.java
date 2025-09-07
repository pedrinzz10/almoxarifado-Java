package almoxarifado.dao;

import almoxarifado.model.ItemEstoque;
import java.util.*;

public class ItemEstoqueDao {

    private Map<Integer, ItemEstoque> itens;

    public ItemEstoqueDao(){
        this.itens = new HashMap<>();
    }

    public void cadastrar(ItemEstoque item){
        if (itens.containsKey(item.getCodigo())){
            throw new IllegalArgumentException("Já existe um item com código " + item.getCodigo());
        }
        itens.put(item.getCodigo(), item);
    }

    public Collection<ItemEstoque> listar(){
        return itens.values();
    }
    public ItemEstoque buscarPorCodigo(int codigo){
        return itens.get(codigo);
    }

    public boolean atualizar(int codigo, String novoNome, String novaCategoria,
                             String novaUnidade, String novaLocalizacao,
                             int novaQtd, int novoEstoqueMinimo) {
        ItemEstoque item = itens.get(codigo);
        if (item != null) {
            item.setNome(novoNome);
            item.setCategoria(novaCategoria);
            item.setUnidadeMedida(novaUnidade);
            item.setLocalizacao(novaLocalizacao);
            item.setQuantidade(novaQtd);
            item.setEstoqueMinimo(novoEstoqueMinimo);
            return true;
        }
        return false;
    }

    public boolean remover(int codigo) {
        return itens.remove(codigo) != null;
    }

    public List<ItemEstoque> buscarPorNome(String nome){
        List<ItemEstoque> encontrados = new ArrayList<>();
        for (ItemEstoque item : itens.values()){
            if (item.getNome().equalsIgnoreCase(nome)){
                encontrados.add(item);
            }
        }
        return encontrados;
    }

    public boolean repor(int codigo, int qtd) {
        ItemEstoque item = itens.get(codigo);
        if (item != null) {
            item.repor(qtd);
            return true;
        }
        return false;
    }

    public boolean baixar(int codigo, int qtd) {
        ItemEstoque item = itens.get(codigo);
        if (item != null) {
            item.baixar(qtd);
            return true;
        }
        return false;
    }

    public List<ItemEstoque> listarAbaixoDoMinimo() {
        List<ItemEstoque> abaixo = new ArrayList<>();
        for (ItemEstoque item : itens.values()) {
            if (item.abaixoDoMinimo()) {
                abaixo.add(item);
            }
        }
        return abaixo;
    }
}
