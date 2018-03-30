package pw.stamina.keybinds.test;

import pw.stamina.keybinds.core.BindingContext;
import pw.stamina.keybinds.core.UpdatableKeybindSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Foundry
 */
public final class TestKeybindSystem implements UpdatableKeybindSystem<ExampleKey> {

    private final Map<Set<ExampleKey>, UpdatableBindingContext<ExampleKey>> keyBinders = new HashMap<>();

    @Override
    public BindingContext<ExampleKey> binderFor(final Set<ExampleKey> keys) {
        return keyBinders.computeIfAbsent(keys, keysIn -> new TestBindingContext(keysIn, keyBinders::remove));
    }

    @Override
    public void onKeyPressed(final ExampleKey key) {
        for (final UpdatableBindingContext<ExampleKey> context : keyBinders.values()) {
            context.onKeyPressed(key);
        }
    }

    @Override
    public void onKeyReleased(final ExampleKey key) {
        for (final UpdatableBindingContext<ExampleKey> context : keyBinders.values()) {
            context.onKeyReleased(key);
        }
    }
}
