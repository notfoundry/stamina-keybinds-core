package pw.stamina.keybinds.core;

import java.util.Set;

/**
 * @author Foundry
 */
public abstract class AbstractKeybind<KeyType extends KeyboardKey> implements Keybind<KeyType> {

    private final Runnable action;

    private final Set<KeyType> boundKeys;

    public AbstractKeybind(final Runnable action, final Set<KeyType> boundKeys) {
        this.action = action;
        this.boundKeys = boundKeys;
    }

    @Override
    public Runnable getAction() {
        return action;
    }

    @Override
    public Set<KeyType> getBoundKeys() {
        return boundKeys;
    }

    @Override
    public abstract void cancel();

    @Override
    public abstract boolean isCancelled();
}
