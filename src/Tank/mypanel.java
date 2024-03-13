
/**
    * @author 韩橹航
    * @version 1.0
    * 坦克的绘图区域
*/
package Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

//为了让Panel 不停的重绘子弹，需要将Mypanel实现Runnable，当作一个线程使用

public class mypanel extends JPanel implements KeyListener,Runnable {
    //定义自己的坦克
    Hero hero=null;
    //定义一个vector，用于存放炸弹
    Vector<Bomb> bombs=new Vector<>();
    //定义敌人的tank
    Vector<Enemytank> enemytanks=new Vector<>();
    //定义一个存放Node 对象的Vector，用于恢复敌人tank的坐标和方向
    Vector<Node> nodes=new Vector<>();

    int enemytanksize=3;
    //定义三张炸弹的图片，用于显示爆炸
    Image image1=null;
    Image image2=null;
    Image image3=null;
    //给一个选择参数，用以选择是否上一句还是重新开始
    public  mypanel(String key){
        File file=new File(Recorder.getRecordFile());
        if(file.exists()) { //如果这个文件是存在的，exists()判断这个函数是否存在

            nodes = Recorder.getNodes();
        }else {
            System.out.println("文件不存在，只能开启新的游戏");
            key="1";
        }
        //将Mypanel对象的 enemytanks 设置给 Recorder 的enemytanks
        Recorder.setEnemytanks(enemytanks);
        hero=new Hero(700,500);//初始化一个tank
        hero.setSpeed(1);
        switch (key){
            case "1":
                Recorder.setAllEnemytankNum(0);
                for (int i = 0; i < enemytanksize; i++) {
                    Enemytank enemytank = new Enemytank(100 * (i + 1), 0);
                    //将enemytanks设置给enemytank对象
                    enemytank.setEnemytanks(enemytanks);
                    enemytank.setDirect(2);
                    new Thread(enemytank).start();
                    //给该enemyTank 加入一颗子弹
                    shot shot = new shot(enemytank.getX() + 20, enemytank.getY() + 60, enemytank.getDirect());
                    //加入enemytanK的vector成员
                    enemytank.shots.add(shot);
                    //启动 shot 对象
                    enemytanks.add(enemytank);
                }
                break;
            case "2":
                for (int i = 0; i < nodes.size(); i++) {
                    Node node=nodes.get(i);
                    Enemytank enemytank = new Enemytank(node.getX(), node.getY());
                    //将enemytanks设置给enemytank对象
                    enemytank.setEnemytanks(enemytanks);
                    enemytank.setDirect(node.getDirect());
                    new Thread(enemytank).start();
                    //给该enemyTank 加入一颗子弹
                    shot shot = new shot(enemytank.getX() + 20, enemytank.getY() + 60, enemytank.getDirect());
                    //加入enemytanK的vector成员
                    enemytank.shots.add(shot);
                    //启动 shot 对象
                    enemytanks.add(enemytank);
                }
                break;
            default:
                System.out.println("你的输入有误...");
        }
        //初始化敌人的坦克,设置每个tank的方法
        //初始化图片对象
        image1=Toolkit.getDefaultToolkit().getImage(mypanel.class.getResource("bomb_1.png"));
        image2=Toolkit.getDefaultToolkit().getImage(mypanel.class.getResource("bomb_2.png"));
        image3=Toolkit.getDefaultToolkit().getImage(mypanel.class.getResource("bomb_3.png"));
    }
    //编写方法，显示我方击毁敌方tank的信息
    public void showInfo(Graphics g){
        g.setColor(Color.BLACK);
        Font font=new Font("宋体",Font.BOLD,25);
        g.setFont(font);
        g.drawString("您累计击毁敌方坦克",1020,30);
        drawtank(1020,60,g,0,0); //画出一个敌方坦克
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemytankNum()+" ",1080,100);
    }
   //进行坦克的绘制
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        showInfo(g);
        g.fillRect(0,0,1000,750);//填充矩形默认黑色
        //画出坦克-封装方法
        if(hero.islive&&hero!=null) {
            drawtank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        }
        //画出hero射击的子弹
//        if(hero.shot1!=null&&hero.shot1.islive==true)
//        {
//            g.draw3DRect(hero.shot1.x,hero.shot1.y,2,2,false);
//        }
        //将hero的子弹集合，shots，遍历取出绘制
        for(int i=0;i<hero.shots.size();i++)
        {
            shot shot=hero.shots.get(i);
            if(shot!=null&&shot.islive==true)
        {
            g.draw3DRect(hero.shot1.x,hero.shot1.y,2,2,false);
        }else {//如果该对象无效，就从shots集合中拿掉对象
                hero.shots.remove(shot);
            }
        }
        //如果bombs 集合中有对象，就画出
        for(int i=0;i< bombs.size();i++)
        {
            //取出炸弹
            Bomb bomb=bombs.get(i);
            //根据当前这个bomb对象的life值去画出相对应的图片
            if(bomb.life>6)
            {
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            }else if(bomb.life>3){
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            }else{
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            }
            bomb.lifeDown();
            if (bomb.life==0){
                bombs.remove(bomb);
            }
        }
        //画出敌人的tank，遍历vector
        for (int i = 0; i < enemytanks.size(); i++) {
            Enemytank enemytank=enemytanks.get(i);
            if (enemytank.islive){// 当敌人tank存活
            drawtank(enemytank.getX(),enemytank.getY(),g,enemytank.getDirect(),1);
            //画出tank的所有子弹
            for(int j=0;j<enemytank.shots.size();j++) {
                shot shot = enemytank.shots.get(j);
                if (shot.islive) {//子弹为真
                    g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    new Thread(shot).start();

                } else {
                    enemytank.shots.remove(shot);
                }
            }

            }
        }

    }

    /**
     *
     * @param x 坦克的左上角的横坐标
     * @param y 坦克的右上角的纵坐标
     * @param g 画笔
     * @param direction 坦克的方向
     * @param type 坦克的类型
     */
    public void drawtank(int x,int y,Graphics g,int direction,int type){
        switch(type)
        {
            case 0://我方tank
                g.setColor(Color.cyan);
                break;
            case 1://敌方坦克
                g.setColor(Color.yellow);
                break;
        }
        //统一设定tank的方向direction(0向上,1向右,2向下,3向左)
        //根据tank的方向来绘制tank
        switch (direction)
        {
            case 0://向上
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1://向右
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20); //画出炮筒
                break;
            case 2://向下
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3://向左
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20); //画出炮筒
                break;
        }
    }
    public void hitEnemyTank(){
        for (int j=0;j<hero.shots.size();j++) {
            shot shot=hero.shots.get(j);
            //遍历敌人所有的坦克
            if (hero.shot1 != null && hero.shot1.islive) {//当我的子弹还存活
                //遍历敌人所有的tank
                for (int i = 0; i < enemytanks.size(); i++) {
                    Enemytank enemytank = enemytanks.get(i);
                    hittank(hero.shot1, enemytank);
                }
            }
        }
    }
    //编写方法判断敌人是否击中我方tank
    public void  hithero(){
        //遍历所有的敌人tank
        for(int i=0;i<enemytanks.size();i++)
        {
            //取出敌人的tank
            Enemytank enemytank=enemytanks.get(i);
            //遍历enemytank 对象的所有子弹
            for(int j=0;j<enemytank.shots.size();j++)
            {
                //取出子弹
                shot shot=enemytank.shots.get(j);
                //判断shot是否击中我方tank
                if(hero.islive&&shot.islive){
                    hittank(shot,hero);
                }
            }
        }
    }
    //编写方法，判断我方的子弹是否击中敌人
    public  void hittank(shot s,Tank1 enemytank)
    {
        //判断S 是否击中tank
        switch (enemytank.getDirect())
        {
            case 0://tank向上
            case 2://tank向下
                if(s.x>enemytank.getX() &&s.x<enemytank.getX()+40&&s.y>enemytank.getY()&&s.y<enemytank.getY()+60)
                {
                    s.islive=false;
                    enemytank.islive=false;
                    //当我方子弹击中敌人坦克后，将tank拿掉
                    enemytanks.remove(enemytank);
                    //对累计数据进行累加
                    if(enemytank instanceof Enemytank){
                        Recorder.addAllEnemytankNum();
                    }
                    //创建bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(enemytank.getX(), enemytank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if(s.x>enemytank.getX() &&s.x<enemytank.getX()+60&&s.y>enemytank.getY()&&s.y<enemytank.getY()+40)
                {
                    s.islive=false;
                    enemytank.islive=false;
                    enemytanks.remove(enemytank);
                    if(enemytank instanceof Enemytank){
                        Recorder.addAllEnemytankNum();
                    }
                    //创建bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(enemytank.getX(), enemytank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
 //处理wdsa键按下的功能实现
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){//按下w键
            //改变tank的方向
            hero.setDirect(0);
            if (hero.getY()>0) {
                hero.moveup();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_D){
            hero.setDirect(1);
            if(hero.getX()+60<1000) {
                hero.moveRight();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_S){
            hero.setDirect(2);
            if (hero.getY()+60<750) {
                hero.moveDown();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_A){
            hero.setDirect(3);
            if(hero.getX()>0) {
                hero.moveLeft();
            }
        }

        //如果用户按下的是J，就发射
        if(e.getKeyCode()==KeyEvent.VK_J){
//            if(hero.shot1==null ||!hero.shot1.islive) {
//                hero.shotenemyTank();
//            }
            hero.shotenemyTank();
        }
        //让面板重绘，相当于按键后的刷新页面
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void  run() {//每隔1000毫秒重绘
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //判断是否击中了敌人tank
            hitEnemyTank();
            //判断敌人tank是否击中我们
            hithero();
            this.repaint();
        }
    }
}
