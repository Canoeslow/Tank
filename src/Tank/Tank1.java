/**
    * @author 韩橹航
    * @version 1.0
    * 坦克父类
*/
package Tank;

public class Tank1 {
    private int x;//坦克的横坐标
    private int y;//坦克的纵坐标
    boolean islive=true;
    private int speed=5;//用来控制tank移动的速度大小
    private int direct;//tank的方向
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void moveup(){
        y-=speed;
    }
    public void moveRight(){
        x+=speed;
    }
    public void moveDown(){
        y+=speed;
    }
    public void moveLeft(){
        x-=speed;
    }
    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tank1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
