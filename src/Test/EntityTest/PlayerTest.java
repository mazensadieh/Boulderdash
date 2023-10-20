package EntityTest;

import Entity.Player;
import main.GamePanel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class PlayerTest {

	@Test
	public void Testupdate_UP() {
		GamePanel gp = new GamePanel();
		Player p = new Player(gp, 500, 500);
		gp.keyh.up= true;
		String MapFileTest = "Obj and Map File/Maps/Map7.txt";
		p.update();
		assertEquals("up" , p.direction );
		assertEquals(492 , p.YPosition );
	}

	@Test
	public void Testupdate_DOWN() {
		GamePanel gp = new GamePanel();
		Player p = new Player(gp, 500, 500);
		gp.keyh.down= true;
		p.update();
		assertEquals("down" , p.direction );
		assertEquals(508 , p.YPosition );
	}

	@Test
	public void Testupdate_LEFT() {
		GamePanel gp = new GamePanel();
		Player p = new Player(gp, 500, 500);
		gp.keyh.left= true;
		p.update();
		assertEquals("left" , p.direction );
		assertEquals(492 , p.XPosition );
	}

	@Test
	public void Testupdate_RIGHT() {
		GamePanel gp = new GamePanel();
		Player p = new Player(gp, 500, 500);
		gp.keyh.right= true;
		p.update();
		assertEquals("right" , p.direction );
		assertEquals(508 , p.XPosition );
	}
}