package ELS;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tetris extends JFrame{
	 int length=12;//列
	 int wide=22;//行
	 
	 int type=0;//俄罗斯方块类型
	 int shape=0;//俄罗斯方块形状
	 
	 int x=0;//横坐标
	 int y=0;//纵坐标
	 
	 int score=0;//得分
	 int flag=0;//判断计时器是工作还是停止，为0需停止，为1需启动
   	 //当计时器发出计时器通知并过时后，会将该通知自动从计时器通知列表中移除。
	 Timer timer;
	public  static TextField tf;
    public Tetris(){
    	TetrisPanel tp=new TetrisPanel();
    	tp.setBounds(0,0,360,645);
     	this.add(tp);
    	this.addKeyListener(tp);
     }
    
    public class TetrisPanel extends JPanel implements KeyListener{
    	 
    	 /*
    	  * 围墙数组值为2
    	  * 小方块数组值为1
    	  * 画布值为0
    	  */
    	 int[][] games = new int[23][13];//12X22矩阵
    	 private final int types[][][] = new int[][][] {//小方块七种类型，四种状态
    		//条形
    		{ { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
    		{ 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } },
    		//反z型
    		{ { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
    		{ 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
    		//z型
    		{ { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
    		//反L形
    		{ { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
    		{ 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
    		//块
    		{ { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
    		//L型
    		{ { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
    		{ 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
    		//t型
    		{ { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 } } };
    	 
    	
    	 public TetrisPanel(){
    		 clean();//全部清零
    		 drawWall();//画围墙
    		 newblock();//新的小方块
    		 tim();
    	 }
    	 
    	 public void tim(){
    		 timer=new Timer(500,new time());//小方块每1秒下落一次
    		 timer.start();
    	 }
    	 
    	 //，将数组元素清零
    	 public void clean(){
    		 for (int i = 0; i < wide-1; i++) {//0-20行
				for (int j = 1; j < length-1; j++) {//1-10列
					games[i][j]=0;
				}
			}
    	 }
    	 
    	 //新块
    	 public void newblock(){
    		 type=(int)(Math.random()*1000)%7;
    	     shape=(int)(Math.random()*1000)%4;
    	     x=length/2-1;
    	     y=0;
    	     if (gameover() == 1) {
    	    	    clean();
    	            drawWall();
    	            score = 0;
    	            tf.setText("0");
    	            JOptionPane.showMessageDialog(null, "GAME OVER");
    	            repaint();
    	        }
    	 }
    	 
    	 
    	  /*
    	  * int length=12;
    	    int wide=22;
    	  */
    	 
    	 //画围墙
    	 public void drawWall(){
    		 //最后一行
    		 for (int i = 0; i < length; i++) {
				games[21][i]=2;  //行，列
			}
    		 
    		 for (int j = 0; j < wide; j++) {
				games[j][0]=2;//第一列
				games[j][11]=2;//最后一列
			}
    	 }
    	 
    	 
    	 /*
    	  * 重写JPanel  paintComponent方法，进行画组件
    	  */
    	 //画方块
    	@Override
    	 public void paintComponent(Graphics g){
    		super.paintComponent(g);//调用父类方法清除画布残留影像
    		 for (int i = 0; i < wide; i++) {
				for (int j = 0; j < length; j++) {
					if(games[i][j]==2){//围墙 
						g.drawRect(j*28, i*28, 28, 28);//建立一个空心方格
					}
					if(games[i][j]==1){ //已经下落完的方块 
						//绘制一个用当前颜色填充的 3-D 高亮显示矩形。
						g.fill3DRect(j*28, i*28, 28, 28,true);//建立一个实心方格
					}
				}
			}
    		 
    		 //移动的小方格
    		 for (int i = 0; i < 16; i++) {
				if(types[type][shape][i]==1){
					g.fill3DRect((x+i%4)*28, (y+i/4)*28, 28, 28,true);//建立一个实心方格
				}
			}
 
    	 }
         
    	//判断是否越出围墙，以及是否和之前方块相碰
	     public boolean istrue(int x,int y){
	    	 for (int a = 0; a < 4; a++) {
	    		 for (int b = 0; b < 4; b++) {
	    			 //和已有小方块碰撞
					if((types[type][shape][a*4+b]==1)&&games[y+a][x+b]==1){
						return true;
					}
					//和墙壁碰撞
					else if((types[type][shape][a*4+b]==1)&&games[y+a][x+b]==2){
						return true;
					}
				}
				
			}
	    	 return false;
	     }

	    //键盘监听
		@Override
		public void keyPressed(KeyEvent e) {
		    switch (e.getKeyCode()) {//获取按键
	        case KeyEvent.VK_DOWN:
	            down();
	            break;
	        case KeyEvent.VK_UP:
	            turn();
	            break;
	        case KeyEvent.VK_RIGHT:
	            right();
	            break;
	        case KeyEvent.VK_LEFT:
	            left();
	            break;
	        case KeyEvent.VK_S:
	            down();
	            break;
	        case KeyEvent.VK_W:
	            turn();
	            break;
	        case KeyEvent.VK_D:
	            right();
	            break;
	        case KeyEvent.VK_A:
	            left();
	            break;
	        case KeyEvent.VK_SPACE://暂停或者继续
	        	 isstop();
		    	 break;
	        case KeyEvent.VK_SHIFT://新游戏
	        	 newgame();
	        	 break;
	        }
		    
		}
		
		//新游戏
		public void newgame(){
			 clean();//全部清零
    		 drawWall();//画围墙
    		 newblock();//新的小方块
    		 score = 0;
	         tf.setText("0");
	         repaint();
		}
		
		//暂停或者继续
        public void isstop(){
        	if(flag==0){
        		timer.stop();
        		flag=1;
        	}
        	else if(flag==1){
        		timer.start();
        		flag=0;
        	}
        }
        
		//按下降键下降、
		public void down(){
			if(!istrue(x,y+1)){//没有发生碰撞
				y++;
			}
			//到画布最底
			else{
				addblock();//将下落的小方块添加到游戏界面中
				remove();//满行消除
				newblock();//新块
			}
			repaint();
		}
		
		//自然下降、
		public void naturedown(){
			if(!istrue(x,y+1)){//没有发生碰撞
			}
			//到画布最底
			else{
				addblock();//将下落的小方块添加到游戏界面中
				remove();//满行消除
				newblock();//新块
			}
			repaint();
		}
		
		//变换
		public void turn(){
			int temp=shape;
			shape=(shape+1)%4;
			if(istrue(x,y)){
				shape=temp;
			}
			repaint();
		}
		
		//右移
		public void right(){
			if(!istrue(x+1,y)){//向右移进行预判
				x++;
			}
			repaint();
		}
		
		//左移
		public void left(){
			if(!istrue(x-1,y)){
				x--;
			}
			repaint();
		}
		
		//将下落的小方块添加到游戏界面中
		public void addblock(){
			int i=0;
			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					if(games[y+a][x+b]==0){
						games[y+a][x+b]=types[type][shape][i];
					}
					i++;
				}
				
			}
		}
		
		//满行消除
	     public void remove(){
	    	 int count=0;
	    	 //遍历整个地图，全为一则让后一组元素覆盖此行元素
	    	 for (int i = 0; i < wide; i++) {//22
	    		 count=0;
				for (int j = 0; j < length; j++) {//12
					if(games[i][j]==1){
						count++;
					}
				}
				if(count==length-2){
					score=score+10;
					tf.setText(String.valueOf(score));;
					for (int k = i; k > 0; k--) {
						for (int p = 1; p < length-1; p++) {
							games[k][p]=games[k-1][p];
							
						}
						
					}
					for (int c = 1; c < length-1; c++) {
						games[0][c]=0;
					}
				}
			}
	     }
	     
	     
	     // 判断游戏结束的方法
	     public int gameover() {
	    	 for (int i = 1; i < length-1; i++) {
	    		  if (games[0][i]==1) {
	 	             return 1;
	 	         }
			}
	         return 0;
	     }
		
	     
		@Override
		public void keyReleased(KeyEvent e) {
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
		}
		
		
		//计时器
		class time implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!istrue(x,y+1)){
					y++;
					remove();
				}
				naturedown();
				repaint();//刷新界面，并会调用重写的paintComponent方法
			}

		}

    }
    
    

	public static void main(String[] args) {
		 Tetris frame = new Tetris();
		 frame.setLayout(null);
		 frame.setLocation(400,50);
	     frame.setSize(560,645);
	     frame.setTitle("俄罗斯方块");
	     frame.setVisible(true);
	     frame.setResizable(false);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	     Label l1=new Label("得分：");
	     l1.setFont(new Font("华文行楷",Font.BOLD,22));
	     l1.setForeground(Color.blue);
	     tf=new TextField(50);
	     
	     Label l2=new Label("游戏说明：");
	     l2.setFont(new Font("华文行楷",Font.BOLD,22));
	     l2.setForeground(Color.blue);
	     
	     Label l3=new Label("1、W键/上：变换方块");
	     Label l4=new Label("2、S键/下：下落方块");
	     Label l5=new Label("3、A键/左：左移方块");
	     Label l6=new Label("4、D键/右：右移方块");
	     Label l7=new Label("5、空格键：暂停/继续游戏");
	     Label l8=new Label("6、shift键：新游戏");
	     
    	 l1.setBounds(380,100,200,30);
    	 tf.setBounds(380,150,120,30);
    	 l2.setBounds(380,250,200,30);
    	 l3.setBounds(380,300,200,30);
    	 l4.setBounds(380,340,200,30);
    	 l5.setBounds(380,380,200,30);
    	 l6.setBounds(380,420,200,30);
    	 l7.setBounds(380,460,200,30);
    	 l8.setBounds(380,500,200,30);
    	 
    	 tf.setText("0");
    	 frame.add(l1);
    	 frame.add(tf);
    	 frame.add(l2);
    	 frame.add(l3);
    	 frame.add(l4);
    	 frame.add(l5);
    	 frame.add(l6);
    	 frame.add(l7);
    	 frame.add(l8);
    	
	}


}



