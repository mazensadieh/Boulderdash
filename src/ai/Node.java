package ai;
// Feld Eigenschaften
public class Node {
	public int col;
	public int row;
	public Node parent;
	public int gCost;
	public int hCost;
	public int fCost;
	public boolean solid;
	public boolean open;
	public boolean checked;

	public Node(int col, int row) {
		this.col = col;
		this.row = row;
	}
}
