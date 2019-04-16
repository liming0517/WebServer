package main.webapp.com.testExercise.test3;


public class test5 {
    //四种整数类型
    byte b=127;
    short s =19999;
    int i =1;
    long l =889898l;

    //两种浮点类型
    float f=10.5f;
    double d =18.21212d;


    //一种布尔类型
    boolean b1 = false;

    //数据类型转换
    public byte nums(){
        byte mychar =(byte)(b+i);//char与byte相加返回int
        return mychar;
    }


    public static void main(String[] args) {
        //字符类型 一种
        char c ='0';
        Character character ='1';
        String s =new String();


        test5 test=new test5();
        System.out.println(test.nums());

    }

}
