package aceita.skywars.utils.inventory;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryManager {
    private final Inventory inventory;

    public InventoryManager(Inventory inventory) {
        this.inventory = inventory;
    }

    public InventoryManager(String title, int slots) {
        slots = (slots % 9 == 0 && slots >= 9 && slots <= 54) ? slots : 9;
        this.inventory = Bukkit.createInventory(null, slots, title);
    }

    public void setPageFormat(PageFormat format) {
        List<ItemStack> items = format.getItems();
        int totalItems = items.size();
        int itemsPerPage = format.getItemsPerPage();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
        int pageNumber = Math.min(format.getPageNumber(), totalPages);

        int startIndex = (pageNumber - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

        int indexSlot = 10;
        for (int i = startIndex; i < endIndex; i++) {
            if (i >= items.size()) break;
            if (indexSlot == 17 || indexSlot == 26 || indexSlot == 35 || indexSlot == 44) {
                indexSlot++;
            }
            inventory.setItem(indexSlot, items.get(i));
            indexSlot++;
        }

        List<PageButtons> buttons = format.getButtons() != null ? format.getButtons() : new ArrayList<>();
        buttons.forEach(button -> {
            if (button == PageButtons.PREVIOUS && pageNumber == 1) return;
            if (button == PageButtons.NEXT && pageNumber >= totalPages) return;
            if (button == PageButtons.EMPTY && startIndex < totalItems) return;
            inventory.setItem(button.getSlot(), button.getItem());
        });
    }

    public void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
    }

    public void openInventory(Player player, Sound sound) {
        player.openInventory(inventory);
        if (sound != null) player.playSound(player.getLocation(), sound, 1, 1);
    }

    public void openInventory(Player player) {
        openInventory(player, null);
    }

    public enum PageButtons {
        NEXT(new ItemStack(Material.ARROW), 53),
        PREVIOUS(new ItemStack(Material.ARROW), 45),
        EMPTY(new ItemStack(Material.BOOK), 49),
        CLOSE(new ItemStack(Material.BARRIER), 48);

        private ItemStack item;
        private int slot;

        PageButtons(ItemStack item, int slot) {
            this.item = item;
            this.slot = slot;
        }

        public ItemStack getItem() { return item; }
        public int getSlot() { return slot; }
        public void setItem(ItemStack item) { this.item = item; }
        public void setSlot(int slot) { this.slot = slot; }
    }

    @Getter
    public static class PageFormat {
        private final int itemsPerPage;
        private final int pageNumber;
        private final List<ItemStack> items;
        @Setter
        @Nullable private List<PageButtons> buttons = new ArrayList<>();

        public PageFormat(int itemsPerPage, int pageNumber, List<ItemStack> items) {
            this.itemsPerPage = itemsPerPage;
            this.pageNumber = pageNumber;
            this.items = items;
        }

        @Nullable
        public PageButtons getButton(PageButtons button) {
            return buttons.stream()
                    .filter(b -> b.equals(button))
                    .findFirst()
                    .orElse(null);
        }
    }
}
