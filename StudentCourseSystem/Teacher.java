import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Teacher extends User {
    int workId;
    String level;
    static Vector<Course> courseTaught = new Vector(); // 用于存储老师教授的所有课程

    public Teacher(String name, String pass, int id, String level) {
        this.name = name;
        this.pass = pass;
        this.workId = id;
        this.level = level;
    }

    public void show() {
        System.out.println(workId + "    " + name + "    " + level);
    }

    // 教师信息输入
    public static Teacher inputTeacher() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入教师姓名:");
        String name = sc.next();
        System.out.println("请输入教师密码:");
        String pass = sc.next();
        System.out.println("请输入教师工号:");
        int workId = sc.nextInt();
        System.out.println("请输入教师职称:");
        String level = sc.next();
        Teacher t;
        t = new Teacher(name, pass, workId, level);
        return t;
    }

    // 删除一位教师信息
    public static void deleteTeacher() {
        System.out.println("请输入要删除的教师工号:");
        Scanner sc = new Scanner(System.in);
        int workId = sc.nextInt();
        for (int i = 0; i < Users.teacherList.size(); i++) {
            if (((Teacher) (Users.teacherList.get(i))).workId == workId) {
                Users.teacherList.remove(i);
                System.out.println("删除成功!");
                break;
            }
            System.out.println("查询不到该教师信息, 删除失败!");
        }
    }

    // 查找所授课课程
    public static void showTeacherCourse(String name) {
        for (Course tCourse : Courses.clist) {
            if (tCourse.teacher.equals(name)) {
                courseTaught.add(tCourse);
            }
        }
        System.out.println(name + ", 您现在所授课程：");
        System.out.println("编号  课程    类型    教师    选课人数    学分/最大选课人数");
        for (Course tCourse : courseTaught) {
            tCourse.show();
        }
    }

    // 查看某门所授课程的上课学生列表
    public static void showCourseStuNum(String name, String level) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(name + level + "现在所授课程：");
            for (Course tCourse : courseTaught) {
                tCourse.show();
            }
            System.out.println("请输入要查询的课号(输入0取消查询):");
            int inputId = sc.nextInt();
            boolean courseFound = false;

            for (Course tCourse : courseTaught) {
                if(tCourse.id==inputId)//老师开设了这门课
                {
                    courseFound = true;
                    int num = 0;
                    System.out.println("选了课程 " + tCourse.name + " 的学生列表:");
                    for (Student student : Users.studentList) {
                        if (student.isEnrolledInCourse(inputId)) {
                            System.out.print(++num+"   ");
                            student.show();
                        }
                    }
                    break;
                }
            }
            if (inputId == 0) {
                System.out.println("结束课程信息的查询!");
                break;
            }
            else if(!courseFound)
             {
                System.out.println("你没有教授这门课程!");
            }
            break;
        }

    }

    public String toString() {
        return workId + " " +pass+" "+ name + " " + level;
        // 将教师的相关信息合并为字符串返回
    }

    // 将课程列表中所有Teacher写入文件
    public static void saveTeachers() {
        File file = new File("Files/Teachers.txt");
        try {// BufferedReader对象out按行向文件写入，写入文件可能会发生异常，因此需要用try catch捕获异常
            if (!file.exists()) {
                file.createNewFile();// 创建新文件
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            for (int i = 0; i < Users.teacherList.size(); i++) {// 遍历课程列表，调用课程的show方法显示信息
                out.write(((Teacher) (Users.teacherList.get(i))).toString() + "\r\n");// \r\n换行
                // 由于添加课程时按照选修课和必修课添加课程，所以toString方法会调用选修课和必修课相应的toString方法
            }

            out.flush();// 把缓存区内容压入文件
            out.close();// 关闭文件
        } catch (IOException e) {
            // TODO Auto-generated catch bllock
            e.printStackTrace();
        }
    }

    public static void readTeacher() {
        try {// 使用BufferedReader对象从文件流中读取文件内容
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Files/Teachers.txt")));
            String data = null;
            while ((data = br.readLine()) != null)// 按行读取文件
            {
                String[] ps = data.split(" ");
                int id = Integer.parseInt(ps[0]);
                String pass = ps[1];
                String name = ps[2];
                String level = ps[3];
                Users.teacherList.add(new Teacher(name, pass, id, level));
            }
            br.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
