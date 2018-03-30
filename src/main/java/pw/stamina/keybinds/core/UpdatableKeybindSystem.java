package pw.stamina.keybinds.core;

/**
 * @author Foundry
 */
public interface UpdatableKeybindSystem<KeyType extends KeyboardKey> extends KeybindSystem<KeyType> {
    void onKeyPressed(KeyType key);

    void onKeyReleased(KeyType key);
}
