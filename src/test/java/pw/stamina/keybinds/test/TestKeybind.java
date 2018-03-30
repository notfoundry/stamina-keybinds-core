package pw.stamina.keybinds.test;

import pw.stamina.keybinds.core.AbstractKeybind;

import java.util.Set;
import java.util.function.Consumer;

/**
 * @author Foundry
 */
public final class TestKeybind extends AbstractKeybind<ExampleKey> {

    private final Consumer<TestKeybind> onCancellation;

    private boolean cancelled;

    public TestKeybind(final Runnable action, final Set<ExampleKey> boundKeys, final Consumer<TestKeybind> onCancellation) {
        super(action, boundKeys);
        this.onCancellation = onCancellation;
    }

    @Override
    public void cancel() {
        if (!cancelled) {
            onCancellation.accept(this);
        } else {
            throw new IllegalStateException("Cannot cancel already-cancelled keybind");
        }
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }
}
