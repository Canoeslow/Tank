/**
    * @author 韩橹航
    * @version 1.1
    * 第二次修订版
*/
package Tank;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class tankgame01 extends JFrame {
    //第二次修改
    //定义一个mypanel
    mypanel mp=null;
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        tankgame01 tank1=new tankgame01();
    }
    public tankgame01(){
        System.out.println("请输入选择 1:新游戏 2:继续上局");
        String key=scanner.next();
        mp=new mypanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);//把面板加载到窗口之中
        this.setSize(1400,750);
        this.addKeyListener(mp);//需要在窗口中添加事件监听的部分
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //在JFrame 中增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
