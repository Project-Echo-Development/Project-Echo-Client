package net.projectecho.manager;

import lombok.Getter;
import net.projectecho.addon.api.Addon;
import net.projectecho.addon.impl.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class AddonManager {
    private final List<Addon> addons = new ArrayList<>();

    public AddonManager() {
    insertAddon(new InfoHud());
    insertAddon(new Keystrokes());
    insertAddon(new ToggleSprint());
    insertAddon(new BedwarsHud());
    insertAddon(new AutoGG());
    insertAddon(new ItemPhysics());
    insertAddon(new JoinGames());
    insertAddon(new PotionHud());
    insertAddon(new CustomHotbar());
    insertAddon(new CustomChat());
    }

    public void insertAddon(Addon addon){
        addons.add(addon);
    }

    public Addon getAddon(String identifier){
        return addons.stream().filter(addon -> Objects.equals(addon.getIdentifier(), identifier)).findFirst().orElse(null);
    }
}
