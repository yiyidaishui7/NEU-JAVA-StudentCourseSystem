import java.util.Scanner;
public abstract class Course {
    int id; // 课程ID
    String name; // 课程名称
    int type; // 课程类型（必修 选修 其他）
    String teacher; // 上课教师信息
    int stuNum; // 选课人数

    public Course() {// 默认的构造方法
        // 有子类的父类一定要写出无参的构造方法，在子类对象创建时系统默认调用
    }

    // 初始化
    public Course(int id, String name, int type, String thr, int stuNum) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.teacher = thr;
        this.stuNum = stuNum;
    }

    public abstract int getMaxStuNum();//抽象，为了从父类中获取子类属性

    public String toString() {
        return id + " " + name + " " + type + " " + stuNum + " " + teacher;
        // 将课程的相关信息合并为字符串返回
    }

    // 课程信息输入
    public static Course inputCourse(int id) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入课程名:");
        String name = sc.next();
        System.out.println("请输入课程类型(0必修, 1选修):");
        int type = sc.nextInt();
        System.out.println("请输入授课老师信息:");
        String thr = sc.next();
        System.out.println("请输入选课人数:");
        int stuNum = sc.nextInt();
        Course c;
        if (type == 0)// 必修
        {
            System.out.println("请输入学分:");
            int credit = sc.nextInt();
            c = new RequiredCourse(id, name, type, stuNum, thr, credit);
            RequiredCourse.rclist.add(c);
            Student.sclist.add(c);
        } else {// 选修
            System.out.println("请输入最大选课人数:");
            int maxStuNum = sc.nextInt();
            c = new OptionalCourse(id, name, type, stuNum, thr, maxStuNum);
            OptionalCourse.oclist.add(c);
        }
        return c;
    }

    public void show() {
        System.out.print(id);
        System.out.print("    " + name + "    ");
        if (type == 0)
            System.out.print("必修");
        else if (type == 1)
            System.out.print("选修");
        else
            System.out.print("其他");
        System.out.println("    " + teacher + "    " + stuNum);
    }

}