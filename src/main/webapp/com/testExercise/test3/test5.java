package main.webapp.com.testExercise.test3;


public class test5 {
    //������������
    byte b=127;
    short s =19999;
    int i =1;
    long l =889898l;

    //���ָ�������
    float f=10.5f;
    double d =18.21212d;


    //һ�ֲ�������
    boolean b1 = false;

    //��������ת��
    public byte nums(){
        byte mychar =(byte)(b+i);//char��byte��ӷ���int
        return mychar;
    }


    public static void main(String[] args) {
        //�ַ����� һ��
        char c ='0';
        Character character ='1';
        String s =new String();


        test5 test=new test5();
        System.out.println(test.nums());

    }

}
