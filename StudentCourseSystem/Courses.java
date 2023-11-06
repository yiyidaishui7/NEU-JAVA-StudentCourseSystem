import java.util.Vector;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Courses {

    // 对所有课程进行列表存储
    static Vector<Course> clist = new Vector();

    // 添加一门课程
    public static void addCourse() {
        if (Courses.clist.size() < 1) {
            Courses.clist.add(Course.inputCourse(1));
        } else {
            Courses.clist.add(Course.inputCourse(Courses.clist.size() + 1));
        }
    }

    // 添加多门课程
    public static void addCourses() {
        int i = Courses.clist.size();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入第" + (++i) + "门课程信息");
            Courses.addCourse();
            System.out.print("是否继续添加课程 y/n");
            String inputFlag = sc.next();
            if (inputFlag.equals("y")||inputFlag.equals("Y"))
                continue;
            else
                break;
        }
    }

    // 删除一门课程
    public static void deleteCourse() {
        System.out.println("请输入要删除的课程名称:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        int flag = 0;
        for (int i = 0; i < Courses.clist.size(); i++) {
            if (((Course) (Courses.clist.get(i))).name.equals(name)) {
                Courses.clist.remove(i);
                flag = 1;
                System.out.println("删除成功!");
            }
        }
        if(flag ==0)
        {
            System.out.println("查询不到该课程信息, 删除失败!");
        }
    }

    // 删除多门课程
    public static void deleteCourses() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            Courses.deleteCourse();
            System.out.print("是否继续删除课程 y/n");
            String inputFlag = sc.next();
            if (inputFlag.equals("y")||inputFlag.equals("Y"))
                continue;
            else{
                System.out.print("结束课程的删除!");
                break;
            }
        }
    }

    // 设置课程教师
    public static void setCourseTeacher() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要设定的课程名称:");
        String name = sc.next();
        int flag=0;
        for (int i = 0; i < Courses.clist.size(); i++) {
            if (((Course) (Courses.clist.get(i))).name.equals(name)) {
                System.out.println("请输入要设定的老师:");
                Courses.clist.get(i).teacher = sc.next();
                flag = 1;
                System.out.println("重设成功!");
            }
        }
        if(flag == 0)
            System.out.println("查询不到该课程信息, 设置失败!");
    }

    // 课程列表查看
    public static void showCourses() {
        System.out.println("编号  课程    类型    教师    选课人数    学分/最大选课人数");
        for (int i = 0; i < Courses.clist.size(); i++) {
            ((Course) (Courses.clist.get(i))).show();
        }
    }

    // 以选课人数进行课程排序
    public static void SortCourseList() {
        Course c, k, m;
        for (int i = 0; i < Courses.clist.size(); i++) {
            for (int j = i + 1; j < Courses.clist.size(); j++) {
                k = (Course) (Courses.clist.get(j));
                c = (Course) (Courses.clist.get(i));
                m = c;
                if (c.stuNum < k.stuNum) {
                    Courses.clist.set(i, k);
                    Courses.clist.set(j, m);
                }
            }
        }
        System.out.print("排序成功!");
    }

    // 以教师姓名查找所授课课程
    public static void SearchCourseByTeacher() {
        System.out.println("请输入需要查找的教师名称:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        for (int i = 0; i < Courses.clist.size(); i++) {
            Course c = ((Course) (Courses.clist.get(i)));
            if (c.teacher.equals(name))
                c.show();
        }
    }

    // 将课程列表中所有课程信息写入文件
    public static void saveCourse() {
        File file = new File("Files/Courses.txt");
        try {// BufferedReader对象out按行向文件写入，写入文件可能会发生异常，因此需要用try catch捕获异常
            if (!file.exists()) {
                file.createNewFile();// 创建新文件
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            for (int i = 0; i < Courses.clist.size(); i++) {// 遍历课程列表，调用课程的show方法显示信息
                out.write(((Course) (Courses.clist.get(i))).toString() + "\r\n");// \r\n换行
                // 由于添加课程时按照选修课和必修课添加课程，所以toString方法会调用选修课和必修课相应的toString方法
            }

            out.flush();// 把缓存区内容压入文件
            out.close();// 关闭文件
        } catch (IOException e) {
            // TODO Auto-generated catch bllock
            e.printStackTrace();
        }
    }

    public static void readCourse() {
        try {// 使用BufferedReader对象从文件流中读取文件内容
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Files/Courses.txt")));
            String data = null;
            while ((data = br.readLine()) != null)// 按行读取文件
            {
                String[] ps = data.split(" ");
                int id = Integer.parseInt(ps[0]);
                String name = ps[1];
                int type = Integer.parseInt(ps[2]);
                int stuNum = Integer.parseInt(ps[3]);
                String teacher = ps[4];
                if (type == 0) {
                    int credit = Integer.parseInt(ps[5]);
                    Courses.clist.add(new RequiredCourse(id, name, type, stuNum, teacher, credit));
                    RequiredCourse.rclist.add(new RequiredCourse(id, name, type, stuNum, teacher, credit));
                    Student.sclist.add(new RequiredCourse(id, name, type, stuNum, teacher, credit));
                } else  if (type == 1){
                    int maxStuNum = Integer.parseInt(ps[5]);
                    Courses.clist.add(new OptionalCourse(id, name, type, stuNum, teacher, maxStuNum));
                    OptionalCourse.oclist.add(new OptionalCourse(id, name, type, stuNum, teacher, maxStuNum));
                }
            }
            br.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
