package EntityTest;

import Entity.MonsterA;
import main.GamePanel;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
public class MonsterATest {

	@Test
	public void TestUpdate_UP() {
		GamePanel gp = new GamePanel();
		MonsterA a = new MonsterA(gp, 500, 500);
		a.collisionOn = false;

		do{
			a.MovingCounter = 119;
			a.update();
			if(Objects.equals(a.direction, "down"))
				a.YPosition = a.YPosition - 2;
		}while(!Objects.equals(a.direction, "up"));

		assertEquals("up" , a.direction );
		assertEquals(498 , a.YPosition );
	}

	@Test
	public void TestUpdate_DOWN() {
		GamePanel gp = new GamePanel();
		MonsterA a = new MonsterA(gp, 500, 500);
		a.collisionOn = false;
		do {
			a.MovingCounter = 119;
			a.update();
			if(Objects.equals(a.direction, "up"))
				a.YPosition = a.YPosition + 2;
		}while(!Objects.equals(a.direction, "down"));

		assertEquals("down" , a.direction );
		assertEquals(502 , a.YPosition );
	}
	@Test
	public void TestUpdate_LEFT() {
		GamePanel gp = new GamePanel();
		MonsterA a = new MonsterA(gp, 500, 500);
		a.collisionOn= false;
		do {
			a.MovingCounter = 119;
			a.update();
			if(Objects.equals(a.direction, "right"))
				a.XPosition = a.XPosition - 2;
		}while(!Objects.equals(a.direction, "left"));

		assertEquals("left" , a.direction );
		assertEquals(498 , a.XPosition );
	}
	@Test
	public void TestUpdate_RIGHT() {
		GamePanel gp = new GamePanel();
		MonsterA a = new MonsterA(gp, 500, 500);
		a.collisionOn= false;
		do {
			a.MovingCounter = 119;
			a.update();
			if(Objects.equals(a.direction, "left"))
				a.XPosition = a.XPosition + 2;
		}while(!Objects.equals(a.direction, "right"));

		assertEquals("right" , a.direction );
		assertEquals(502 , a.XPosition );
	}

}