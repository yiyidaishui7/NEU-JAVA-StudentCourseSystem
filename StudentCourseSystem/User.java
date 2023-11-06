import java.util.Scanner;

public class User {
    String name;
    protected String pass;


    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public User() {// 默认的构造方法
        // 有子类的父类一定要写出无参的构造方法，在子类对象创建时系统默认调用
    }

    public void show() {
        System.out.println("用户名：" + name);
    }

    public String GetPass(){
        return pass;
    }

    public void SetPass(String pass){
        if(pass.length()==6)
            this.pass = pass;

    }
    public void SetPassWord() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("请输入新密码6位:");
            String xpass1 = sc.next();
            System.out.print("请再次输入密码6位:");
            String xpass2 = sc.next();
            if (xpass1.equals(xpass2) && xpass1.length() == 6) {
                this.pass = xpass1;
                break;
            } else
                continue;
        }
    }


}
