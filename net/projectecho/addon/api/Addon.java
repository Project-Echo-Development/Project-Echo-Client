package net.projectecho.addon.api;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;

@Getter
public class Addon {
    public Minecraft mc = Minecraft.getMinecraft();
    // Identifier must be unique
    private String identifier, displayName;
    @Setter
    private boolean state;
    public boolean hovered; // Public so it can function with the custom gui

    public Addon (String identifier, String displayName){
        this.identifier = identifier;
        this.displayName = displayName;
    }

    public void displayHook(){}
    public void entityHook(){}
    public void packetHook(Packet packet){}
}
