/**
    * @author 韩橹航
    * @version 1.0
    * 爆炸相关类
*/
package Tank;

public class Bomb {
    int x,y;//炸弹的坐标
    int life=9; //炸弹的生命周期
    boolean islive=true; //是否存户

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //减少生命值
    public void lifeDown()
    {
        if(life>0)
        {
            life--;
        }else{
            islive=false;
        }
    }
}
