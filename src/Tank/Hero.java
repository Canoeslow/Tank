package Tank;

import java.util.Vector;

public class Hero extends Tank1{
    //射击对象
    shot shot1=null;
    Vector<shot> shots=new Vector<>();
    public Hero(int x, int y) {
        super(x, y);
    }
    //射击
    public void shotenemyTank(){
        if (shots.size()==5)
        {
            return;
        }
        //创建Shot对象
        switch (getDirect()){//得到Hero对象的方向
            case 0://向上
                shot1=new shot(getX()+20,getY(),0);
                break;
            case 1://向右
                shot1=new shot(getX()+60,getY()+20,1);
                break;
            case 2://向下
                shot1=new shot(getX()+20,getY()+60,2);
                break;
            case 3:
                shot1=new shot(getX(),getY()+20,3);
                break;
        }
        //把新建的shot放入到shots
        shots.add(shot1);
        //启动我们的Shot线程
        new Thread(shot1).start();
    }
}
