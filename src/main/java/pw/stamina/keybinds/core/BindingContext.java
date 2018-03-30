package pw.stamina.keybinds.core;

import java.util.Set;

/**
 * @author Foundry
 */
public interface BindingContext<KeyType extends KeyboardKey> {
    Keybind<KeyType> bind(Runnable action);

    Set<Keybind<KeyType>> getKeybinds();
}
