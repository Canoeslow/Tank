/**
    * @author 韩橹航
    * @version 1.0
    * 该类用于记录相关信息的.和文件交互
*/
package Tank;

import java.io.*;
import java.util.Vector;

public class Recorder {
    //定义变量，记录我方击毁敌人坦克数
    private static int allEnemytankNum=0;
    //定义IO对象，准备写数据到文件中
    private static BufferedReader br=null;
    private static FileWriter fw=null;
    private static BufferedWriter bw=null;
    //定义一个敌人的集合
    private static Vector<Enemytank> enemytanks=null;

    public static void setEnemytanks(Vector<Enemytank> enemytanks) {
        Recorder.enemytanks = enemytanks;
    }
    //返回记录文件的目录
    public static String getRecordFile(){
        return recordFile;
    }
    //定义一个Node 的Vector，用于保存敌人的信息node
    private static Vector<Node> nodes=new Vector<>();
    //把记录文件保存到src中去
    private static String recordFile="src\\myRecord.txt";
    //编辑方法，当游戏退出时，我们将allEnemytankNum 保存到 recordFile
    //保存敌人tank的坐标和方向
    //编辑一个方法，用于读取文件，恢复相关信息
    //该方法，在继续上局的时候调用
    public static Vector<Node> getNodes(){
        try {
            br=new BufferedReader(new FileReader(recordFile));
            allEnemytankNum=Integer.parseInt(br.readLine());
            //循环读取文件，生成nodes集合
            String line="";
            while((line=br.readLine())!=null){
                String[] xyd=line.split(" "); //line的split方法通过参数中的格式，按照此格式将字符串分割返回到字符串数组中
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (br!=null){
                    br.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return nodes;
    }
    public static void keepRecord(){ //idea捕获异常快捷键 ctrl+alt+t
        try {
             bw = new BufferedWriter(new FileWriter(recordFile));
             bw.write(allEnemytankNum+"\r\n");
             //遍历敌人tank的Vector，然后根据情况保存即可
            for (int i = 0; i <enemytanks.size(); i++) {
                //取出敌人的tank
                Enemytank enemytank=enemytanks.get(i);
                if(enemytank.islive){
                    //保存tank 的信息
                    String record=enemytank.getX()+" "+enemytank.getY()+" "+enemytank.getDirect();
                    //写入到文件
                    bw.write(record+"\r\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(bw!=null){
                    bw.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static int getAllEnemytankNum() {
        return allEnemytankNum;
    }

    public static void setAllEnemytankNum(int allEnemytankNum) {
        Recorder.allEnemytankNum = allEnemytankNum;
    }
    //当我方tank击毁一个敌人tank，就应当对坦克数进行增加处理
    public static void addAllEnemytankNum(){
        Recorder.allEnemytankNum++;
    }
}
