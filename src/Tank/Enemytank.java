/**
    * @author 韩橹航
    * @version 1.0
    * 地方tank
*/
package Tank;

import java.util.Vector;

public class Enemytank extends Tank1 implements Runnable {
    //在敌人的tank使用vetor，保存多个Shot
    Vector<shot> shots = new Vector<>();
    //增加成员 ，enemytahk, 可以得到敌人tank 的Vector
    Vector<Enemytank> enemytanks = new Vector<>();
    //设置一个方法，可以将Mypanel中的enemytanks赋值给此处的enemytank

    public void setEnemytanks(Vector<Enemytank> enemytanks) {
        this.enemytanks = enemytanks;
    }

    //编写方法判断此时tank，是否和enemytanks中的其他tank重叠
    public boolean isTouchEnemytanks() {
        //判断当前敌人tank（this）方向
        switch (this.getDirect()) {
            case 0:
                for (int i = 0; i < enemytanks.size(); i++) {
                    //从中取出一个敌方tank
                    Enemytank enemytank = enemytanks.get(i);
                    //不和自己比较
                    if (enemytank != this) {
                        //如果敌人tank是上/下
                        if (enemytank.getDirect() == 0 || enemytank.getDirect() == 2) {
                            //判断己方tank的范围以及敌方tank的范围
                            if (this.getX() >= enemytank.getX() && this.getX() <= enemytank.getX() + 40
                                    && this.getY() >= enemytank.getY() && this.getY() <= enemytank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 40 >= enemytank.getX() && this.getX() + 40 <= enemytank.getX() + 40
                                    && this.getY() >= enemytank.getY() && this.getY() <= enemytank.getY() + 60) {
                                return true;
                            }
                        }
                        if (enemytank.getDirect() == 1 || enemytank.getDirect() == 3) {
                            if (this.getX() >= enemytank.getX() && this.getX() <= enemytank.getX() + 60
                                    && this.getY() >= enemytank.getY() && this.getY() <= enemytank.getY() + 40) {
                                return true;
                            }
                        }
                        if (this.getX() + 40 >= enemytank.getX() && this.getX() + 40 <= enemytank.getX() + 60
                                && this.getY() >= enemytank.getY() && this.getY() <= enemytank.getY() + 40) {
                            return true;
                        }
                    }
                }
                break;
            case 1://右
                for (int i = 0; i < enemytanks.size(); i++) {
                    //从中取出一个敌方tank
                    Enemytank enemytank = enemytanks.get(i);
                    //不和自己比较
                    if (enemytank != this) {
                        //如果敌人tank是上/下
                        if (enemytank.getDirect() == 0 || enemytank.getDirect() == 2) {
                            //判断己方tank的范围以及敌方tank的范围
                            if (this.getX() + 60 >= enemytank.getX() && this.getX() + 60 <= enemytank.getX() + 40
                                    && this.getY() >= enemytank.getY() && this.getY() <= enemytank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 60 >= enemytank.getX() && this.getX() + 60 <= enemytank.getX() + 40
                                    && this.getY() + 40 >= enemytank.getY() && this.getY() + 40 <= enemytank.getY() + 60) {
                                return true;
                            }
                        }
                        if (enemytank.getDirect() == 1 || enemytank.getDirect() == 3) {
                            if (this.getX() + 60 >= enemytank.getX() && this.getX() + 60 <= enemytank.getX() + 60
                                    && this.getY() >= enemytank.getY() && this.getY() <= enemytank.getY() + 40) {
                                return true;
                            }
                        }
                        if (this.getX() + 60 >= enemytank.getX() && this.getX() + 60 <= enemytank.getX() + 60
                                && this.getY() + 40 >= enemytank.getY() && this.getY() + 40 <= enemytank.getY() + 40) {
                            return true;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < enemytanks.size(); i++) {
                    //从中取出一个敌方tank
                    Enemytank enemytank = enemytanks.get(i);
                    //不和自己比较
                    if (enemytank != this) {
                        //如果敌人tank是上/下
                        if (enemytank.getDirect() == 0 || enemytank.getDirect() == 2) {
                            //判断己方tank的范围以及敌方tank的范围
                            if (this.getX() >= enemytank.getX() && this.getX() <= enemytank.getX() + 40
                                    && this.getY()+60 >= enemytank.getY() && this.getY()+60 <= enemytank.getY() + 60) {
                                return true;
                            }
                            if (this.getX()+40 >= enemytank.getX() && this.getX()+40 <= enemytank.getX() + 40
                                    && this.getY() + 60 >= enemytank.getY() && this.getY() +60 <= enemytank.getY() + 60) {
                                return true;
                            }
                        }
                        if (enemytank.getDirect() == 1 || enemytank.getDirect() == 3) {
                            if (this.getX() >= enemytank.getX() && this.getX() <= enemytank.getX() + 60
                                    && this.getY()+60 >= enemytank.getY() && this.getY()+60 <= enemytank.getY() + 40) {
                                return true;
                            }
                        }
                        if (this.getX()+40 >= enemytank.getX() && this.getX()+40 <= enemytank.getX() + 60
                                && this.getY() + 60 >= enemytank.getY() && this.getY() + 60 <= enemytank.getY() + 40) {
                            return true;
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < enemytanks.size(); i++) {
                    //从中取出一个敌方tank
                    Enemytank enemytank = enemytanks.get(i);
                    //不和自己比较
                    if (enemytank != this) {
                        //如果敌人tank是上/下
                        if (enemytank.getDirect() == 0 || enemytank.getDirect() == 2) {
                            //判断己方tank的范围以及敌方tank的范围
                            if (this.getX() >= enemytank.getX() && this.getX() <= enemytank.getX() + 40
                                    && this.getY() >= enemytank.getY() && this.getY() <= enemytank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() >= enemytank.getX() && this.getX() <= enemytank.getX() + 40
                                    && this.getY() + 40 >= enemytank.getY() && this.getY() +40 <= enemytank.getY() + 60) {
                                return true;
                            }
                        }
                        if (enemytank.getDirect() == 1 || enemytank.getDirect() == 3) {
                            if (this.getX() >= enemytank.getX() && this.getX() <= enemytank.getX() + 60
                                    && this.getY() >= enemytank.getY() && this.getY() <= enemytank.getY() + 40) {
                                return true;
                            }
                        }
                        if (this.getX() >= enemytank.getX() && this.getX() <= enemytank.getX() + 60
                                && this.getY() + 40 >= enemytank.getY() && this.getY() + 40 <= enemytank.getY() + 40) {
                            return true;
                        }
                    }
                }
                break;
        }
        return false;
    }


    boolean islive=true;
    public Enemytank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while(true)
        {
            //这里判断如果shots size()=0 创建一颗子弹，放入到
            //shots集合，并启动
            if(islive && shots.size()==0)
            {
                shot s=null;
                //判断tank的方向，创建对应的子弹
                switch (getDirect())
                {
                    case 0:
                        s=new shot(getX()+20,getY(),0);
                        break;
                    case 1:
                        s=new shot(getX()+60,getY()+20,1);
                        break;
                    case 2:
                        s=new shot(getX()+20,getY()+60,2);
                        break;
                    case 3:
                        s=new shot(getX(),getY()+20,3);
                        break;
                }
                shots.add(s);
                //启动
                new Thread(s).start();
            }
            //根据tank的方向来继续移动
            switch (getDirect()){
                case 0: //向上
                    for (int i=0;i<30;i++) {
                        if(getY()>0&&!isTouchEnemytanks()) {
                            moveup();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1: //向右
                    for (int i=0;i<30;i++) {
                        if (getX()+60<1000&&!isTouchEnemytanks()) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2: //向下
                    for (int i=0;i<30;i++) {
                        if (getY()+60<750&&!isTouchEnemytanks()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3: //向左
                    for (int i=0;i<30;i++) {
                        if(getX()>0&&!isTouchEnemytanks()) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
            //休眠50毫秒

            //然后随机改变tank的方向
            setDirect((int)(Math.random()*4));
            if (!islive)
            {
                break;//退出线程
            }
        }
    }
}
