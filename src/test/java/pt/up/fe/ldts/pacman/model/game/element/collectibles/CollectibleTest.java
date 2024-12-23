package pt.up.fe.ldts.pacman.model.game.element.collectibles;

import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectibleTest {

    private static class TestCollectible extends Collectible {
        public TestCollectible(Position pos, int value) {
            super(pos, value);
        }
    }

    @Test
    void testCollectibleInitialization() {
        TestCollectible collectible = new TestCollectible(new Position(5, 5), 10);

        assertEquals(10, collectible.getValue());

        assertEquals(new Position(5, 5), collectible.getPosition());
    }

    @Test
    void testCollectibleValue() {
        TestCollectible collectible = new TestCollectible(new Position(1, 1), 20);

        assertEquals(20, collectible.getValue());
    }
}
