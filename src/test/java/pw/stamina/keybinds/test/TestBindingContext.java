package pw.stamina.keybinds.test;

import pw.stamina.keybinds.core.Keybind;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author Foundry
 */
public final class TestBindingContext implements UpdatableBindingContext<ExampleKey> {

    private final Set<ExampleKey> keys;
    private final Consumer<Set<ExampleKey>> onRemoveLastBind;

    private final int[] keyCodes;
    private final int lowestKeycode;
    private final int highestKeycode;
    private final boolean[] keyStates;

    private final Set<Keybind<ExampleKey>> boundActions;
    private final List<Runnable> actions;

    private int keysDown;

    public TestBindingContext(final Set<ExampleKey> keys, final Consumer<Set<ExampleKey>> onRemoveLastBind) {
        this.keys = keys;
        this.onRemoveLastBind = onRemoveLastBind;

        this.keyCodes = keys.stream().mapToInt(ExampleKey::getExampleKeycode).toArray();
        this.lowestKeycode = getLowerKeycodeBound();
        this.highestKeycode = getUpperKeycodeBound();
        this.keyStates = new boolean[this.highestKeycode + 1];

        this.boundActions = new HashSet<>();
        this.actions = new ArrayList<>();

        this.keysDown = 0;
    }

    private int getLowerKeycodeBound() {
        return Arrays.stream(this.keyCodes).min().orElseThrow(() -> new IllegalStateException("Keyset had zero elements"));
    }

    private int getUpperKeycodeBound() {
        return Arrays.stream(this.keyCodes).max().orElseThrow(() -> new IllegalStateException("Keyset had zero elements"));
    }

    @Override
    public Keybind<ExampleKey> bind(final Runnable action) {
        final Keybind<ExampleKey> keybind = new TestKeybind(action, this.keys, self -> {
            this.actions.remove(self.getAction());
            this.boundActions.remove(self);
            if (this.boundActions.isEmpty()) {
                this.onRemoveLastBind.accept(keys);
            }
        });
        this.boundActions.add(keybind);

        this.actions.add(action);

        return keybind;
    }

    @Override
    public Set<Keybind<ExampleKey>> getBoundActions() {
        return Collections.unmodifiableSet(this.boundActions);
    }

    @Override
    public void onKeyPressed(final ExampleKey key) {
        final int keycode = key.getExampleKeycode();
        if (keycode >= this.lowestKeycode && keycode <= this.highestKeycode) {
            if (!this.keyStates[keycode]) {
                this.keyStates[keycode] = true;
                if (++this.keysDown == this.keyCodes.length) {
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
        if (keycode >= this.lowestKeycode && keycode <= this.highestKeycode) {
            if (this.keyStates[keycode]) {
                this.keyStates[keycode] = false;
                --this.keysDown;
            }
        }
    }
}
