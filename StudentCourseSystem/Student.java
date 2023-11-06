import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Student extends User {
    int id;
    String Class;
    static Vector<Course> sclist = new Vector(); // 用于存储学生的所有课程
    static Vector<Course> sOptionalclist = new Vector(); // 用于存储学生的选修课课程

    public Student(String name, String pass, int id, String Class) {
        this.name = name;
        this.pass = pass;
        this.id = id;
        this.Class = Class;
    }

    public void show() {
        System.out.println(id + "    " + name + "    " + Class);
    }

    public static void showStudentCourses(int id) {
        System.out.println(id + "的全部课程:");
        for (Course sCourse : sclist) {
            sCourse.show();
        }
//        System.out.println("必修课:\n");
//        RequiredCourse.showRequiredCourses();// 先展示必修课课程
//        System.out.println("选修课:\n");
//        for (Course sOptionalcourse : sOptionalclist) {
//            sOptionalcourse.show();
//        }
    }

    //判断是否选了某课程
    public static boolean isEnrolledInCourse(int cId){
        return sOptionalclist.contains(cId);
    }
    // 学生信息输入
    public static Student inputStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生姓名:");
        String name = sc.next();
        System.out.println("请输入学生密码:");
        String pass = sc.next();
        System.out.println("请输入学生学号:");
        int id = sc.nextInt();
        System.out.println("请输入学生班级:");
        String Class = sc.next();
        Student s;
        s = new Student(name, pass, id, Class);
        return s;
    }

    // 删除一位学生信息
    public static void deleteStudent() {
        System.out.println("请输入要删除的学生学号:");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        for (int i = 0; i < Users.studentList.size(); i++) {
            if (((Student) (Users.studentList.get(i))).id == id) {
                Users.studentList.remove(i);
                System.out.println("删除成功!");
                break;
            }
            System.out.println("查询不到该学生信息, 删除失败!");
        }
    }

    public String toString() {
        return id + " " +pass+" "+ Class + " " + name;
        // 将学生的相关信息合并为字符串返回
    }

    // 学生选课
    public static void studentCourses() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("学校总选修课列表如下:");
            for (Course oCourse : OptionalCourse.oclist) {
                oCourse.show();
            }
            System.out.println("请输入想上的选修课的课号(输入0退出):");
            int inputId = sc.nextInt();
            if(inputId != 0){
                int flag = 0;
                for (Course oCourse : OptionalCourse.oclist) {// 在总的选修课列表里面匹配
                    if (oCourse.id == inputId) {
                        flag = 1;
                        boolean isAlreadyEnrolled = false;

                        for (Course sOptionalcourse : sOptionalclist) {// 在已选选修中匹配 看是否已选
                            if (oCourse.id == sOptionalcourse.id) {
                                System.out.println("此选修课程已经选过啦!");
                                isAlreadyEnrolled = true;
                                break;
                            }
                        }
                        if (!isAlreadyEnrolled) {//未被选
                            if (oCourse.stuNum < oCourse.getMaxStuNum()) {// 看选修课人数是否超标
                                oCourse.stuNum += 1;
                                sOptionalclist.add(oCourse);//选修
                                sclist.add(oCourse);//总共
                                System.out.println("选课成功!");
                            } else {
                                System.out.println("此选修课程选课人数已经达到上限!");
                            }
                        }
                        break;
                    }
                }
                if(flag ==0){
                    System.out.println("请输入正确的选修课的课号!");
                    break;
                }
            }
            System.out.println("结束选课!");
            break;
        }

    }

    // 将studentList中所有student信息写入文件
    public static void saveStudents() {
        File file = new File("Files/Students.txt");
        try {// BufferedReader对象out按行向文件写入，写入文件可能会发生异常，因此需要用try catch捕获异常
            if (!file.exists()) {
                file.createNewFile();// 创建新文件
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            for (int i = 0; i < Users.studentList.size(); i++) {// 遍历student列表，调用student的show方法显示信息
                out.write(((Student) (Users.studentList.get(i))).toString() + "\r\n");// \r\n换行
                // 由于添加课程时按照选修课和必修课添加课程，所以toString方法会调用选修课和必修课相应的toString方法
            }

            out.flush();// 把缓存区内容压入文件
            out.close();// 关闭文件
        } catch (IOException e) {
            // TODO Auto-generated catch bllock
            e.printStackTrace();
        }
    }

    public static void readStudent() {
        try {// 使用BufferedReader对象从文件流中读取文件内容
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Files/Students.txt")));
            String data = null;
            while ((data = br.readLine()) != null)// 按行读取文件
            {
                String[] ps = data.split(" ");
                int id = Integer.parseInt(ps[0]);
                String pass = ps[1];
                String Class = ps[2];
                String name = ps[3];
                Users.studentList.add(new Student(name, pass, id, Class));
            }
            br.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
