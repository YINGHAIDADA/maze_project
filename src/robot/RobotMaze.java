package robot;

import data.Point;

/**
 * ����: �Թ������Ͷ���
 *
 */
public class RobotMaze {
    // �Թ����е�·���洢�ڶ�ά���鵱��
    private Point[][] maze;
    // �洢�Թ�·���ڵ�Ķ��нṹ�����ò�����ŵķ�ʽ��Ѱ���Թ����ŵ�·����Ϣ
    private LinkQueue<Point> queue;
    // �Թ�������
    private int row;
    // �Թ�������
    private int col;
    // ��¼�Թ�·���ڵ��������Ϣ
    private Point[] pathrecord;

    /**
     * �Թ���ʼ��
     * @param row
     * @param col
     */
    public RobotMaze(Point[][] p) {
    	row=p.length;
    	col=p[0].length;
        maze=p;
        this.queue = new LinkQueue<>();
        this.pathrecord = new Point[row*col];
    }

    /**
     * �޸��Թ����нڵ��ĸ����������״̬��Ϣ
     */
    public void initMazeNodePathState() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                 //��
                if(j<col-1 && this.maze[i][j+1].isRoad()){
                    this.maze[i][j].state[Constant.RIGHT] = true;
                }
                //��
                if(i<row-1 && this.maze[i+1][j].isRoad()){
                    this.maze[i][j].state[Constant.DOWN] = true;
                }
                //��
                if(j>0 && this.maze[i][j-1].isRoad()){
                    this.maze[i][j].state[Constant.LEFT] = true;
                }
                //��
                if(i>0 && this.maze[i-1][j].isRoad()){
                    this.maze[i][j].state[Constant.UP] = true;
                }
            }
        }
        //System.out.println("�޸ĺ÷���");
    }

    /**
     * Ѱ���Թ�·��
     */
    public void findMazePath(int in_x,int in_y,int out_x,int out_y) {
    	if(!maze[in_x][in_y].isRoad())
    		return;
    	
        queue.offer(maze[in_x][in_y]);
        while(!queue.isEmpty()){
            Point top = queue.peek();
            int x = top.getI();
            int y = top.getJ();
            if(x == out_x && y == out_y){
            	//System.out.println("���������ͬ");
                return;
            }
            
            // ���ҷ�����
            if(maze[x][y].state[Constant.RIGHT]){
                maze[x][y].state[Constant.RIGHT] = false;
                maze[x][y+1].state[Constant.LEFT] = false;
                queue.offer(maze[x][y+1]);
                pathrecord[x*col+y+1] = maze[x][y];
            }

            // ���·�����
            if(maze[x][y].state[Constant.DOWN]){
                maze[x][y].state[Constant.DOWN] = false;
                maze[x+1][y].state[Constant.UP] = false;
                queue.offer(maze[x+1][y]);
                pathrecord[(x+1)*col+y] = maze[x][y];
            }

            // ��������
            if(maze[x][y].state[Constant.LEFT]){
                maze[x][y].state[Constant.LEFT] = false;
                maze[x][y-1].state[Constant.RIGHT] = false;
                queue.offer(maze[x][y-1]);
                pathrecord[x*col+y-1] = maze[x][y];
            }

            // ���Ϸ�����
            if(maze[x][y].state[Constant.UP]){
                maze[x][y].state[Constant.UP] = false;
                maze[x-1][y].state[Constant.DOWN] = false;
                queue.offer(maze[x-1][y]);
                pathrecord[(x-1)*col+y] = maze[x][y];
            }

            queue.poll();
        }
        //System.out.println("�ҵ����·��");
    }
    /**
     * ��ӡ�Թ�·�������Ľ��
     */
    public Point[][] showMazePath(int out_x,int out_y) {
            int x = out_x;
            int y = out_y;
            for(;;){
                maze[x][y].setisLuJing(true);;
                Point node = pathrecord[x*col+y];
                if(node == null){
                    break;
                }
                x = node.getI();
                y = node.getJ();
            }
            for(int i=0;i<maze.length;i++)
            {
            	for(int j=0;j<maze[0].length;j++)
            	{
            		if(maze[i][j].isRoad())
            		{
            			if(maze[i][j].getisLuJing())
            			{
            				System.out.print("*");
            			}
            			else {
							System.out.print("0");
						}
            		}
            		else {
						System.out.print("1");
					}
            	}
            	System.out.println();
            }
            System.out.println();
            //System.out.println("��ӡ���");
        return maze;
    }

}