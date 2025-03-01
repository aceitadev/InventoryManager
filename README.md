# :package: InventoryManager (Bukkit) 
**Um sistema simples para criar menus paginados no Minecraft!**  

---

## :rocket: **Como Começar**  
1. **Criar um Inventário**  
   ```java
   InventoryManager manager = new InventoryManager("Título do Menu", 54); // Slots: 9, 18, 27, 36, 45, 54
   ```

2. **Configurar uma Página**  
   ```java
   List<ItemStack> itens = new ArrayList<>(); // Adicione seus itens aqui
   InventoryManager.PageFormat formato = new InventoryManager.PageFormat(28, 1, itens); // 28 itens por página, página 1
   
   // Adicionar botões de navegação
   formato.setButtons(List.of(
       InventoryManager.PageButtons.NEXT,
       InventoryManager.PageButtons.PREVIOUS,
       InventoryManager.PageButtons.CLOSE
   ));
   
   manager.setPageFormat(formato);
   ```

3. **Abrir o Menu para um Jogador**  
   ```java
   manager.openInventory(jogador, Sound.LEVEL_UP); // Som opcional!
   ```

---

## :art: **Personalização**  
### :radio_button: **Botões Padrão**  
| Botão      | Item Padrão      | Slot | Emoji |  
|------------|------------------|------|-------|  
| `NEXT`     | Seta (`ARROW`)   | 53   | :arrow_right:     |  
| `PREVIOUS` | Seta (`ARROW`)   | 45   | :arrow_left:     |  
| `EMPTY`      | Livro (`BOOK`)   | 49   | :book:     |  
| `CLOSE`    | Barreira (`BARRIER`) | 48 | :x:     |  

**Exemplo de Personalização:**  
```java
// Mudar item do botão "Próxima Página"
ItemStack novoItem = new ItemStack(Material.EMERALD);
InventoryManager.PageButtons.NEXT.setItem(novoItem);

// Mudar posição do botão "Fechar"
InventoryManager.PageButtons.CLOSE.setSlot(50);
```

---

## :warning: **Dicas Importantes**  
- **Slots Válidos**: Use múltiplos de 9 (ex: 9, 18, 27... 54).  
- **Índices de Página**: A página é ajustada automaticamente se for maior que o total.  
- **Itens por Página**: Defina `itemsPerPage` conforme o layout do seu inventário (ex: 28 para 54 slots).  

---

## :tools: **Solução de Problemas**  
- **Botões Não Aparecem?**  
  Verifique se `setButtons()` foi chamado e os slots estão corretos.  
- **Itens Sumindo?**  
  Confira se o `itemsPerPage` não excede o espaço do inventário.  
- **Erros ao Abrir?**  
  Garanta que o jogador não esteja com inventários abertos (ex: baú).  

---

## :scroll: **Exemplo Completo**  
```java
// Criar manager
InventoryManager manager = new InventoryManager("Loja de Itens", 54);

// Adicionar itens à loja
List<ItemStack> itensLoja = new ArrayList<>();
itensLoja.add(new ItemStack(Material.*DIAMOND_SWORD*));
// ... (adicione mais itens)

// Configurar página
PageFormat formato = new PageFormat(28, 1, itensLoja);
formato.setButtons(List.of(PageButtons.NEXT, PageButtons.PREVIOUS, PageButtons.CLOSE));

// Personalizar botão "Fechar"
PageButtons.CLOSE.setItem(new ItemStack(Material.REDSTONE_BLOCK));

// Aplicar formato e abrir menu
manager.setPageFormat(formato);
manager.openInventory(player, Sound.CLICK);
```
