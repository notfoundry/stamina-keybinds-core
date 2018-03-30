package pw.stamina.keybinds.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Foundry
 */
public interface KeybindSystem<KeyType extends KeyboardKey> {
    BindingContext<KeyType> binderFor(Set<KeyType> keys);

    default BindingContext<KeyType> binderFor(KeyType... keys) {
        if (keys == null) {
            throw new IllegalArgumentException("Key array cannot be null");
        } else if (keys.length == 0) {
            throw new IllegalArgumentException("At least one key to bind must be specified");
        }
        return binderFor(new HashSet<>(Arrays.asList(keys)));
    }
}
