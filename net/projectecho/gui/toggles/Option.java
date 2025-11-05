package net.projectecho.gui.toggles;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Option {
    public String display;
    public boolean val, hovered;

    public Option (String display, boolean val){
        this.display = display;
        this.val = val;
    }
}
