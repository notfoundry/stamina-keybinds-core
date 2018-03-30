package pw.stamina.keybinds.test;

import pw.stamina.keybinds.core.KeyboardKey;

import java.util.Objects;

/**
 * @author Foundry
 */
public final class ExampleKey implements KeyboardKey {

    private final String representation;

    private final int exampleKeycode;

    private ExampleKey(final String representation, final int exampleKeycode) {
        this.representation = representation;
        this.exampleKeycode = exampleKeycode;
    }

    @Override
    public String getKeyRepresentation() {
        return representation;
    }

    public int getExampleKeycode() {
        return exampleKeycode;
    }

    public static ExampleKey of(final char key) {
        return new ExampleKey(String.valueOf(key), (int) key);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ExampleKey exampleKey = (ExampleKey) o;
        return exampleKeycode == exampleKey.exampleKeycode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exampleKeycode);
    }
}
