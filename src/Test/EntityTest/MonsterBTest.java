package EntityTest;

import Entity.MonsterB;
import main.GamePanel;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
public class MonsterBTest {

	@Test
	public void TestUpdate_UP() {
		GamePanel gp = new GamePanel();
		MonsterB b = new MonsterB(gp, 500, 500);
		b.collisionOn = false;

		do{
			b.MovingCounter = 119;
			b.update();
			if(Objects.equals(b.direction, "down"))
				b.YPosition = b.YPosition - 3;
		}while(!Objects.equals(b.direction, "up"));

		assertEquals("up" , b.direction );
		assertEquals(497 , b.YPosition );
	}

	@Test
	public void TestUpdate_DOWN() {
		GamePanel gp = new GamePanel();
		MonsterB b = new MonsterB(gp, 500, 500);
		b.collisionOn = false;
		do {
			b.MovingCounter = 119;
			b.update();
			if(Objects.equals(b.direction, "up"))
				b.YPosition = b.YPosition + 3;
		}while(!Objects.equals(b.direction, "down"));

		assertEquals("down" , b.direction );
		assertEquals(503 , b.YPosition );
	}
	@Test
	public void TestUpdate_LEFT() {
		GamePanel gp = new GamePanel();
		MonsterB b = new MonsterB(gp, 500, 500);
		b.collisionOn= false;
		do {
			b.MovingCounter = 119;
			b.update();
			if(Objects.equals(b.direction, "right"))
				b.XPosition = b.XPosition - 3;
		}while(!Objects.equals(b.direction, "left"));

		assertEquals("left" , b.direction );
		assertEquals(497 , b.XPosition );
	}
	@Test
	public void TestUpdate_RIGHT() {
		GamePanel gp = new GamePanel();
		MonsterB b = new MonsterB(gp, 500, 500);
		b.collisionOn= false;
		do {
			b.MovingCounter = 119;
			b.update();
			if(Objects.equals(b.direction, "left"))
				b.XPosition = b.XPosition + 3;
		}while(!Objects.equals(b.direction, "right"));

		assertEquals("right" , b.direction );
		assertEquals(503 , b.XPosition );
	}

}
