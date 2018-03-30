package pw.stamina.keybinds.test;

import org.junit.Test;
import pw.stamina.keybinds.core.BindingContext;
import pw.stamina.keybinds.core.Keybind;
import pw.stamina.keybinds.core.UpdatableKeybindSystem;

/**
 * @author Foundry
 */
public class KeybindTests {

    @Test
    public void givenXXX_whenXXX_thenXXX() {
        UpdatableKeybindSystem<ExampleKey> system = new TestKeybindSystem();

        BindingContext<ExampleKey> binder = system.binderFor(
                ExampleKey.of('Q'), ExampleKey.of('W')
        );

        Keybind<ExampleKey> bind1 = binder.bind(() -> System.out.println("foo"));

        Keybind<ExampleKey> bind2 = binder.bind(() -> System.out.println("bar"));

        system.onKeyPressed(ExampleKey.of('Q'));
        system.onKeyPressed(ExampleKey.of('W'));

        bind1.cancel();

        system.onKeyReleased(ExampleKey.of('W'));
        system.onKeyPressed(ExampleKey.of('W'));

        bind2.cancel();
    }
}
