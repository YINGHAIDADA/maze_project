package data;

import java.util.LinkedList;

public class MazeByDefined implements MazeMaker {
	public Point[][] point; // point[i][j]是迷宫中的点
	int row, column;
	LinkedList<Point> isMountainPoint;

	public MazeByDefined(int row, int column) {
		this.row = row;
		this.column = column;
		isMountainPoint = new LinkedList<Point>();
		point = new Point[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				point[i][j] = new Point();// 创建迷宫中的点
				point[i][j].setX(j);
				point[i][j].setY(i);
			}
		}
	}

	public Point[][] initMaze() {
		initRoad();
		point[0][0].setIsEnter(true);
		point[0][0].setIsRoad(true);
		return point;
	}

	private void initRoad() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				point[i][j].setIsRoad(false); // 设置点都不是路
			}
		}
	}
}



