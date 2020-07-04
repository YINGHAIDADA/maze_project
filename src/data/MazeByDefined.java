package data;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class MazeByDefined implements MazeMaker {
	public Point[][] point; // point[i][j]���Թ��еĵ�
	int row, column;
	LinkedList<Point> isMountainPoint;

	public MazeByDefined(int row, int column) {
		this.row = row;
		this.column = column;
		isMountainPoint = new LinkedList<Point>();
		point = new Point[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				point[i][j] = new Point();// �����Թ��еĵ�
				point[i][j].setX(j);
				point[i][j].setY(i);
			}
		}
	}

	public Point[][] initMaze() {
		initRoad();
		point[0][0].setIsEnter(true);
		point[0][0].setIsRoad(true);
		point[0][0].setIsMountain(true);
		return point;
	}

	private void initRoad() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				point[i][j].setIsRoad(false); // ���õ㶼����·
				point[i][j].setHaveFlag(false); // ���õ㶼û����
				point[i][j].setIsMountain(false); // ���õ㶼����ɽ

			}
		}
	}
}



