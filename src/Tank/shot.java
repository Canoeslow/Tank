/**
    * @author 韩橹航
    * @version 1.0
    *  子弹类
*/
package Tank;

public class shot implements Runnable{
    int x;//子弹的横坐标
    int y;//子弹的纵坐标

    public shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }


    int direct=0;//子弹的方向    
    int speed=60;//子弹的速度
    boolean islive=true;//子弹是否存活

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //根据方向来改变x,y坐标
            switch(direct){
                case 0://上
                    y-=speed;
                    break;
                case 1://右
                    x+=speed;
                    break;
                case 2://下
                    y+=speed;
                    break;
                case 3://左
                    x-=speed;
                    break;
            }
            //当子弹碰到边界和碰到敌人时应该结束线程
            if(!(x>=0&&x<=1000&&y>=0&&y<=750&& islive)){
                islive=false;
                break;
            }
        }
    }
}
