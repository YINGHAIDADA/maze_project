package robot;

import data.Point;

/**
 * 描述: 迷宫的类型定义
 *
 * 
 */
public class RobotMaze {
    // 迷宫所有的路径存储在二维数组当中
    private Point[][] maze;
    // 存储迷宫路径节点的队列结构，采用层层扩张的方式，寻找迷宫最优的路径信息
    private LinkQueue<Point> queue;
    // 迷宫的行数
    private int row;
    // 迷宫的列数
    private int col;
    // 记录迷宫路径节点的行走信息
    private Point[] pathrecord;

    /**
     * 迷宫初始化
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
     * 修改迷宫所有节点四个方向的行走状态信息
     */
    public void initMazeNodePathState() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                 //右
                if(j<col-1 && this.maze[i][j+1].isRoad()){
                    this.maze[i][j].state[Constant.RIGHT] = true;
                }
                //下
                if(i<row-1 && this.maze[i+1][j].isRoad()){
                    this.maze[i][j].state[Constant.DOWN] = true;
                }
                //左
                if(j>0 && this.maze[i][j-1].isRoad()){
                    this.maze[i][j].state[Constant.LEFT] = true;
                }
                //上
                if(i>0 && this.maze[i-1][j].isRoad()){
                    this.maze[i][j].state[Constant.UP] = true;
                }
            }
        }
        //System.out.println("修改好方向");
    }

    /**
     * 寻找迷宫路径
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
            	//System.out.println("出口入口相同");
                return;
            }
            
            // 往右方向走
            if(maze[x][y].state[Constant.RIGHT]){
                maze[x][y].state[Constant.RIGHT] = false;
                maze[x][y+1].state[Constant.LEFT] = false;
                queue.offer(maze[x][y+1]);
                pathrecord[x*col+y+1] = maze[x][y];
            }

            // 往下方向走
            if(maze[x][y].state[Constant.DOWN]){
                maze[x][y].state[Constant.DOWN] = false;
                maze[x+1][y].state[Constant.UP] = false;
                queue.offer(maze[x+1][y]);
                pathrecord[(x+1)*col+y] = maze[x][y];
            }

            // 往左方向走
            if(maze[x][y].state[Constant.LEFT]){
                maze[x][y].state[Constant.LEFT] = false;
                maze[x][y-1].state[Constant.RIGHT] = false;
                queue.offer(maze[x][y-1]);
                pathrecord[x*col+y-1] = maze[x][y];
            }

            // 往上方向走
            if(maze[x][y].state[Constant.UP]){
                maze[x][y].state[Constant.UP] = false;
                maze[x-1][y].state[Constant.DOWN] = false;
                queue.offer(maze[x-1][y]);
                pathrecord[(x-1)*col+y] = maze[x][y];
            }

            queue.poll();
        }
        //System.out.println("找到最短路径");
    }

    /**
     * 打印迷宫路径搜索的结果
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
            //System.out.println("打印完成");
        return maze;
    }

}