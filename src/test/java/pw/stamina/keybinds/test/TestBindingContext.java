package pw.stamina.keybinds.test;

import it.unimi.dsi.fastutil.ints.Int2BooleanOpenHashMap;
import pw.stamina.keybinds.core.Keybind;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author Foundry
 */
public final class TestBindingContext implements UpdatableBindingContext<ExampleKey> {

    private final Set<ExampleKey> keys;
    private final Consumer<Set<ExampleKey>> onRemoveLastBind;

    private final Int2BooleanOpenHashMap keyStates;

    private final Set<Keybind<ExampleKey>> keybindObjects;
    private final List<Runnable> actions;

    private int keysDown;

    public TestBindingContext(final Set<ExampleKey> keys, final Consumer<Set<ExampleKey>> onRemoveLastBind) {
        this.keys = keys;
        this.onRemoveLastBind = onRemoveLastBind;

        this.keyStates = new Int2BooleanOpenHashMap(keys.size());
        for (final ExampleKey key : keys) {
            this.keyStates.put(key.getExampleKeycode(), false);
        }

        this.keybindObjects = new HashSet<>();
        this.actions = new ArrayList<>();

        this.keysDown = 0;
    }

    @Override
    public Keybind<ExampleKey> bind(final Runnable action) {
        final Keybind<ExampleKey> keybind = new TestKeybind(action, this.keys, self -> {
            this.actions.remove(self.getAction());
            this.keybindObjects.remove(self);
            if (this.keybindObjects.isEmpty()) {
                this.onRemoveLastBind.accept(keys);
            }
        });
        this.keybindObjects.add(keybind);

        this.actions.add(action);

        return keybind;
    }

    @Override
    public Set<Keybind<ExampleKey>> getKeybinds() {
        return Collections.unmodifiableSet(this.keybindObjects);
    }

    @Override
    public void onKeyPressed(final ExampleKey key) {
        final int keycode = key.getExampleKeycode();
        if (this.keyStates.containsKey(keycode)) {
            if (!this.keyStates.get(keycode)) {
                this.keyStates.put(keycode, true);
                if (++this.keysDown == this.keyStates.size()) {
                    for (final Runnable action : this.actions) {
                        action.run();
                    }
                }
            }
        }
    }

    @Override
    public void onKeyReleased(final ExampleKey key) {
        final int keycode = key.getExampleKeycode();
        if (this.keyStates.containsKey(keycode)) {
            if (this.keyStates.get(keycode)) {
                this.keyStates.put(keycode, false);
                --this.keysDown;
            }
        }
    }
}
