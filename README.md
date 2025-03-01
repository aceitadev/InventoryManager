```markdown
# InventoryManager 📦

O `InventoryManager` é uma classe utilitária para facilitar o gerenciamento de inventários paginados em plugins Bukkit/Spigot para Minecraft. Com ela, você pode criar inventários personalizados, implementar paginação e adicionar botões de navegação de forma simples e intuitiva.

## Funcionalidades ✨

- Criação de inventários com título e número de slots customizados.
- Paginação de itens utilizando a classe `PageFormat`.
- Botões de navegação integrados (Próximo, Anterior, Vazio, Fechar).
- Abertura de inventário para jogadores com opção de som de notificação.

## Instalação 🔧

Basta adicionar o arquivo `InventoryManager.java` ao seu projeto de plugin ou compilá-lo como parte da sua biblioteca.

## Exemplos de Uso 📖

### Criando um Inventário

Você pode criar um inventário a partir de uma instância já existente ou instanciá-lo diretamente com um título e quantidade de slots.

```java
// Utilizando uma instância existente de Inventory
Inventory inv = Bukkit.createInventory(null, 54, "Meu Inventário");
InventoryManager invManager = new InventoryManager(inv);

// Ou criando um novo inventário com título e slots
InventoryManager invManager = new InventoryManager("Meu Inventário", 54);
```

### Configurando a Paginação

A classe `PageFormat` permite paginar os itens do seu inventário. Basta definir a quantidade de itens por página, o número da página atual e a lista de itens que serão exibidos.

```java
List<ItemStack> items = new ArrayList<>();
// Adiciona itens à lista
items.add(new ItemStack(Material.DIAMOND));
items.add(new ItemStack(Material.GOLD_INGOT));
// ... adicione outros itens conforme necessário

// Cria um PageFormat com 10 itens por página, na página 1
InventoryManager.PageFormat format = new InventoryManager.PageFormat(10, 1, items);

// Opcional: Definindo botões de navegação
List<InventoryManager.PageButtons> buttons = new ArrayList<>();
buttons.add(InventoryManager.PageButtons.NEXT);
buttons.add(InventoryManager.PageButtons.PREVIOUS);
format.setButtons(buttons);

// Aplica a formatação de página ao inventário
invManager.setPageFormat(format);
```

### Abrindo o Inventário para um Jogador

Abra o inventário para um jogador e, se desejar, reproduza um som ao abrir.

```java
// Abrir o inventário sem som
invManager.openInventory(player);

// Abrir o inventário com efeito sonoro
invManager.openInventory(player, Sound.BLOCK_CHEST_OPEN);
```

## Referência da API 📚

### Construtores

- `InventoryManager(Inventory inventory)`: Cria um InventoryManager a partir de um inventário já existente.
- `InventoryManager(String title, int slots)`: Cria um novo inventário com o título especificado e número de slots. O número de slots deve ser múltiplo de 9 (entre 9 e 54).

### Métodos Principais

- `void setPageFormat(PageFormat format)`: Configura a paginação e distribui os itens no inventário conforme o formato definido.
- `void setItem(int slot, ItemStack item)`: Define um item específico em um slot determinado.
- `void openInventory(Player player, Sound sound)`: Abre o inventário para o jogador e reproduz um som opcional.
- `void openInventory(Player player)`: Abre o inventário sem som.

### Tipos Aninhados

#### Enum: PageButtons

Representa os botões de navegação no inventário:

- **NEXT**: Avança para a próxima página.
- **PREVIOUS**: Volta para a página anterior.
- **EMPTY**: Botão vazio (usado para espaçamento ou placeholder).
- **CLOSE**: Fecha o inventário.

Cada botão possui um `ItemStack` associado e um slot específico.

#### Classe: PageFormat

Armazena a configuração para paginação dos itens:

- **Construtor**: `PageFormat(int itemsPerPage, int pageNumber, List<ItemStack> items)`
- **Métodos**:
  - `PageButtons getButton(PageButtons button)`: Retorna o botão de navegação solicitado, se existir.
- **Setters**:
  - `void setButtons(List<PageButtons> buttons)`: Define os botões de navegação a serem utilizados.
```
