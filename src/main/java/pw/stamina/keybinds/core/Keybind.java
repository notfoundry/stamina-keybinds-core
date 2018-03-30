package pw.stamina.keybinds.core;

import java.util.Set;

/**
 * @author Foundry
 */
public interface Keybind<KeyType extends KeyboardKey> {
    Runnable getAction();

    Set<KeyType> getBoundKeys();

    void cancel();

    boolean isCancelled();
}
