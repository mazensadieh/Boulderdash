package ai;

import main.GamePanel;

import java.util.ArrayList;
import java.util.Objects;

// wo konnen der Spieler und Das Monster bewegen
public class PathFinder {
	public ArrayList<Node> pathList = new ArrayList<>();
	private final GamePanel gp;
	private Node[][] node;
	private final ArrayList<Node> openList = new ArrayList<>();
	private Node startNode, goalNode, currentNode;
	private boolean goalReached = false;
	private int step = 0;
	public PathFinder(GamePanel gp) {
		this.gp = gp;
		instantiateNode();
	}
	private void instantiateNode() {
		node = new Node[ gp.maxscreencol ][ gp.maxscreenrow ];
		int col = 0;
		int row = 0;
		while(col < gp.maxscreencol && row < gp.maxscreenrow) {
			node[ col ][ row ] = new Node(col, row);
			col++;
			if(col == gp.maxscreencol) {
				col = 0;
				row++;
			}
		}
	}
	private void resetNodes() {
		int col = 0;
		int row = 0;
		while(col < gp.maxscreencol && row < gp.maxscreenrow) {
			node[ col ][ row ].open = false;
			node[ col ][ row ].checked = false;
			node[ col ][ row ].solid = false;
			col++;
			if(col == gp.maxscreencol) {
				col = 0;
				row++;
			}
		}
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
	}

	// Überprufe ob der Spieler zum Ziel kommen kann
	public void reachablesetNode(int startCol, int startRow, int goalCol, int goalRow) {
		resetNodes();
		startNode = node[ startCol ][ startRow ];
		currentNode = startNode;
		goalNode = node[ goalCol ][ goalRow ];
		openList.add(currentNode);
		int col = 0;
		int row = 0;
		while(col < gp.maxscreencol && row < gp.maxscreenrow) {
			int tileNum = gp.mapM.mapTileNum[ gp.mapM.CourrentMap ][ col ][ row ];
			if(gp.mapM.tile[ tileNum ].collision) {
				node[ col ][ row ].solid = true;
			}
			for(int i = 0 ; i < gp.mapM.obj.length ; i++) {
				if(gp.mapM.obj[ i ] != null) {
					if(Objects.equals(gp.mapM.obj[ i ].name, "Rock")) {
						int itCol = gp.mapM.obj[ i ].XPosition / gp.tilesize;
						int itRow = gp.mapM.obj[ i ].YPosition / gp.tilesize;
						node[ itCol ][ itRow ].solid = true;
						int botomindex = gp.checker.checkObject(gp.mapM.obj[ i ], false);
						if(botomindex != 999) {
							if(gp.mapM.obj[ botomindex ] != null && !Objects.equals(gp.mapM.obj[ botomindex ].name, "Rock")) {
								itCol = gp.mapM.obj[ i ].XPosition / gp.tilesize;
								itRow = gp.mapM.obj[ i ].YPosition / gp.tilesize;
								node[ itCol ][ itRow ].solid = false;
							}
						}
					}
				}
			}
			getCost(node[ col ][ row ]);
			col++;
			if(col == gp.maxscreencol) {
				col = 0;
				row++;
			}
		}
	}

	// Überprufe ob das Monster zum Ziel kommen kann
	public void setNode(int startCol, int startRow, int goalCol, int goalRow) {
		resetNodes();
		startNode = node[ startCol ][ startRow ];
		currentNode = startNode;
		goalNode = node[ goalCol ][ goalRow ];
		openList.add(currentNode);
		int col = 0;
		int row = 0;
		while(col < gp.maxscreencol && row < gp.maxscreenrow) {
			int tileNum = gp.mapM.mapTileNum[ gp.mapM.CourrentMap ][ col ][ row ];
			if(gp.mapM.tile[ tileNum ].collision) {
				node[ col ][ row ].solid = true;
			}
			for(int i = 0 ; i < gp.mapM.obj.length ; i++) {
				if(gp.mapM.obj[ i ] != null) {
					int itCol = gp.mapM.obj[ i ].XPosition / gp.tilesize;
					int itRow = gp.mapM.obj[ i ].YPosition / gp.tilesize;
					node[ itCol ][ itRow ].solid = true;
				}
			}
			getCost(node[ col ][ row ]);
			col++;
			if(col == gp.maxscreencol) {
				col = 0;
				row++;
			}
		}
	}
	private void getCost(Node node) {
		//G cost
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;
		//H cost
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost = xDistance + yDistance;
		// F cost
		node.fCost = node.gCost + node.hCost;
	}

	// finde beste Weg
	public boolean search() {
		while(!goalReached && step < 500) {
			int col = currentNode.col;
			int row = currentNode.row;
			currentNode.checked = true;
			openList.remove(currentNode);
			if(row - 1 >= 0) {
				openNode(node[ col ][ row - 1 ]);
			}
			if(col - 1 >= 0) {
				openNode(node[ col - 1 ][ row ]);
			}
			if(row + 1 < gp.maxscreenrow) {
				openNode(node[ col ][ row + 1 ]);
			}
			if(col + 1 < gp.maxscreencol) {
				openNode(node[ col + 1 ][ row ]);
			}
			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			for(int i = 0 ; i < openList.size() ; i++) {
				if(openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost;
				} else if(openList.get(i).fCost == bestNodefCost) {
					if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
			}
			if(openList.size() == 0) {
				break;
			}
			currentNode = openList.get(bestNodeIndex);

			if(currentNode == goalNode) {
				goalReached = true;
				trackThePath();
			}
			step++;
		}
		return goalReached;
	}
	private void openNode(Node node) {
		if(!node.open && !node.checked && !node.solid) {
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
	}
	private void trackThePath() {
		Node current = goalNode;
		while(current != startNode) {
			pathList.add(0, current);
			current = current.parent;
		}
	}
}
