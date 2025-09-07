# 📦 Sistema de Almoxarifado em Java

Aplicação simples em **Java** para gerenciamento de estoque de um almoxarifado.  
O sistema foi desenvolvido com foco em **Orientação a Objetos** e utiliza **coleções Java** para armazenar os dados.

---

## 🚀 Funcionalidades

- ✅ Cadastrar item no estoque  
- ✅ Listar todos os itens cadastrados  
- ✅ Buscar item por **código** ou **nome**  
- ✅ Atualizar informações de um item  
- ✅ Remover item do estoque  
- ✅ Movimentar estoque (**entrada e saída de produtos**)  
- ✅ Relatório de itens abaixo do estoque mínimo  

---

## 🛠️ Tecnologias Utilizadas

- Java 17+  
- Collections API (`List`, `Map`, `Set`)  
- Programação Orientada a Objetos (POO)

---

## 📂 Estrutura do Projeto

```bash
src/
 └── almoxarifado/
      ├── model/
      │    └── ItemEstoque.java
      ├── dao/
      │    └── ItemEstoqueDao.java
      └── view/
           └── Main.java

model → classes de modelo (entidades do sistema, como ItemEstoque)

dao → classes de manipulação de dados (operações CRUD)

view → classe principal com o menu interativo no console
```

## ▶️ Como Executar

Clone o repositório:
```bash
git clone https://github.com/seu-usuario/almoxarifado-java.git
```

Compile os arquivos:
```bash
javac -d bin src/almoxarifado/**/*.java
```

Execute a aplicação:
```bash
java -cp bin almoxarifado.view.Main
```
---

## 🖥️ Exemplo de Uso

```yaml
--- MENU ---
1 - Cadastrar item
2 - Listar itens
3 - Buscar item
4 - Atualizar item
5 - Remover item
6 - Movimentar estoque
7 - Relatório de itens abaixo do mínimo
8 - Sair
Escolha: 1

Digite o nome do item: Caneta Azul
Digite a categoria: Material Escolar
Digite a quantidade: 100
Digite o estoque mínimo: 20
Item cadastrado com sucesso!

```
