package pw.stamina.keybinds.test;

import pw.stamina.keybinds.core.BindingContext;
import pw.stamina.keybinds.core.KeyboardKey;

/**
 * @author Foundry
 */
public interface UpdatableBindingContext<KeyType extends KeyboardKey> extends BindingContext<KeyType> {
    void onKeyPressed(final KeyType key);


    void onKeyReleased(final KeyType key);
}
