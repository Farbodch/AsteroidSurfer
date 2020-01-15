package test.model.playerTest;

import main.model.player.OutOfBoundsException;
import main.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class PlayerTest {
  private Player playerTest;

  @BeforeEach
    void runBefore() {
      playerTest = new Player(600,800);
  }

  @Test
  void testConstructor() {
      assertEquals(playerTest.getSpeed(),0);
      assertEquals(playerTest.getLifePoints(),3);
      assertEquals(playerTest.getScore(),0);
      assertEquals(playerTest.getShipLocation().get(0),600/2);
      assertEquals(playerTest.getShipLocation().get(1),800/2);
  }

  @Test
  void testChangeSpeed() {
      try {
          playerTest.changeSpeed('U');
          playerTest.changeSpeed('U');
          playerTest.changeSpeed('U');
          playerTest.changeSpeed('U');
      } catch (OutOfBoundsException e) {
          fail("Was not supposed to throw exception");
      }

      try {
          playerTest.changeSpeed('U');
          playerTest.changeSpeed('U');
          playerTest.changeSpeed('U');
          playerTest.changeSpeed('U');
          playerTest.changeSpeed('U');
          playerTest.changeSpeed('U');
          playerTest.changeSpeed('U');
          playerTest.changeSpeed('U');
          playerTest.changeSpeed('U');
          playerTest.changeSpeed('U');
          fail("Should not reach this code!");
      } catch (OutOfBoundsException e) {
          System.out.println("Great!");
      }
  }

}
