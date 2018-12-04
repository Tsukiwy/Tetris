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
	 int length=12;//��
	 int wide=22;//��
	 
	 int type=0;//����˹��������
	 int shape=0;//����˹������״
	 
	 int x=0;//������
	 int y=0;//������
	 
	 int score=0;//�÷�
	 int flag=0;//�жϼ�ʱ���ǹ�������ֹͣ��Ϊ0��ֹͣ��Ϊ1������
   	 //����ʱ��������ʱ��֪ͨ����ʱ�󣬻Ὣ��֪ͨ�Զ��Ӽ�ʱ��֪ͨ�б����Ƴ���
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
    	  * Χǽ����ֵΪ2
    	  * С��������ֵΪ1
    	  * ����ֵΪ0
    	  */
    	 int[][] games = new int[23][13];//12X22����
    	 private final int types[][][] = new int[][][] {//С�����������ͣ�����״̬
    		//����
    		{ { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
    		{ 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } },
    		//��z��
    		{ { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
    		{ 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
    		//z��
    		{ { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
    		//��L��
    		{ { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
    		{ 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
    		//��
    		{ { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
    		//L��
    		{ { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
    		{ 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
    		//t��
    		{ { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
    		{ 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 } } };
    	 
    	
    	 public TetrisPanel(){
    		 clean();//ȫ������
    		 drawWall();//��Χǽ
    		 newblock();//�µ�С����
    		 tim();
    	 }
    	 
    	 public void tim(){
    		 timer=new Timer(500,new time());//С����ÿ1������һ��
    		 timer.start();
    	 }
    	 
    	 //��������Ԫ������
    	 public void clean(){
    		 for (int i = 0; i < wide-1; i++) {//0-20��
				for (int j = 1; j < length-1; j++) {//1-10��
					games[i][j]=0;
				}
			}
    	 }
    	 
    	 //�¿�
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
    	 
    	 //��Χǽ
    	 public void drawWall(){
    		 //���һ��
    		 for (int i = 0; i < length; i++) {
				games[21][i]=2;  //�У���
			}
    		 
    		 for (int j = 0; j < wide; j++) {
				games[j][0]=2;//��һ��
				games[j][11]=2;//���һ��
			}
    	 }
    	 
    	 
    	 /*
    	  * ��дJPanel  paintComponent���������л����
    	  */
    	 //������
    	@Override
    	 public void paintComponent(Graphics g){
    		super.paintComponent(g);//���ø��෽�������������Ӱ��
    		 for (int i = 0; i < wide; i++) {
				for (int j = 0; j < length; j++) {
					if(games[i][j]==2){//Χǽ 
						g.drawRect(j*28, i*28, 28, 28);//����һ�����ķ���
					}
					if(games[i][j]==1){ //�Ѿ�������ķ��� 
						//����һ���õ�ǰ��ɫ���� 3-D ������ʾ���Ρ�
						g.fill3DRect(j*28, i*28, 28, 28,true);//����һ��ʵ�ķ���
					}
				}
			}
    		 
    		 //�ƶ���С����
    		 for (int i = 0; i < 16; i++) {
				if(types[type][shape][i]==1){
					g.fill3DRect((x+i%4)*28, (y+i/4)*28, 28, 28,true);//����һ��ʵ�ķ���
				}
			}
 
    	 }
         
    	//�ж��Ƿ�Խ��Χǽ���Լ��Ƿ��֮ǰ��������
	     public boolean istrue(int x,int y){
	    	 for (int a = 0; a < 4; a++) {
	    		 for (int b = 0; b < 4; b++) {
	    			 //������С������ײ
					if((types[type][shape][a*4+b]==1)&&games[y+a][x+b]==1){
						return true;
					}
					//��ǽ����ײ
					else if((types[type][shape][a*4+b]==1)&&games[y+a][x+b]==2){
						return true;
					}
				}
				
			}
	    	 return false;
	     }

	    //���̼���
		@Override
		public void keyPressed(KeyEvent e) {
		    switch (e.getKeyCode()) {//��ȡ����
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
	        case KeyEvent.VK_SPACE://��ͣ���߼���
	        	 isstop();
		    	 break;
	        case KeyEvent.VK_SHIFT://����Ϸ
	        	 newgame();
	        	 break;
	        }
		    
		}
		
		//����Ϸ
		public void newgame(){
			 clean();//ȫ������
    		 drawWall();//��Χǽ
    		 newblock();//�µ�С����
    		 score = 0;
	         tf.setText("0");
	         repaint();
		}
		
		//��ͣ���߼���
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
        
		//���½����½���
		public void down(){
			if(!istrue(x,y+1)){//û�з�����ײ
				y++;
			}
			//���������
			else{
				addblock();//�������С������ӵ���Ϸ������
				remove();//��������
				newblock();//�¿�
			}
			repaint();
		}
		
		//��Ȼ�½���
		public void naturedown(){
			if(!istrue(x,y+1)){//û�з�����ײ
			}
			//���������
			else{
				addblock();//�������С������ӵ���Ϸ������
				remove();//��������
				newblock();//�¿�
			}
			repaint();
		}
		
		//�任
		public void turn(){
			int temp=shape;
			shape=(shape+1)%4;
			if(istrue(x,y)){
				shape=temp;
			}
			repaint();
		}
		
		//����
		public void right(){
			if(!istrue(x+1,y)){//�����ƽ���Ԥ��
				x++;
			}
			repaint();
		}
		
		//����
		public void left(){
			if(!istrue(x-1,y)){
				x--;
			}
			repaint();
		}
		
		//�������С������ӵ���Ϸ������
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
		
		//��������
	     public void remove(){
	    	 int count=0;
	    	 //����������ͼ��ȫΪһ���ú�һ��Ԫ�ظ��Ǵ���Ԫ��
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
	     
	     
	     // �ж���Ϸ�����ķ���
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
		
		
		//��ʱ��
		class time implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!istrue(x,y+1)){
					y++;
					remove();
				}
				naturedown();
				repaint();//ˢ�½��棬���������д��paintComponent����
			}

		}

    }
    
    

	public static void main(String[] args) {
		 Tetris frame = new Tetris();
		 frame.setLayout(null);
		 frame.setLocation(400,50);
	     frame.setSize(560,645);
	     frame.setTitle("����˹����");
	     frame.setVisible(true);
	     frame.setResizable(false);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	     Label l1=new Label("�÷֣�");
	     l1.setFont(new Font("�����п�",Font.BOLD,22));
	     l1.setForeground(Color.blue);
	     tf=new TextField(50);
	     
	     Label l2=new Label("��Ϸ˵����");
	     l2.setFont(new Font("�����п�",Font.BOLD,22));
	     l2.setForeground(Color.blue);
	     
	     Label l3=new Label("1��W��/�ϣ��任����");
	     Label l4=new Label("2��S��/�£����䷽��");
	     Label l5=new Label("3��A��/�����Ʒ���");
	     Label l6=new Label("4��D��/�ң����Ʒ���");
	     Label l7=new Label("5���ո������ͣ/������Ϸ");
	     Label l8=new Label("6��shift��������Ϸ");
	     
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



