import java.util.Vector;

public class RequiredCourse extends Course {
    private int credit;// 学分
    // 对RequiredCourse进行列表存储
    static Vector<Course> rclist = new Vector();

    public RequiredCourse(int i, String s) {
        this.id = i;
        this.name = s;
        type = 0;
    }

    public int getMaxStuNum() {
        return 0;
    }

    public String toString() {
        return super.toString() + " " + this.credit;
        // super.toString将调用父类Course类的toString方法
        // 将必修课的相关信息合并为字符串返回
    }

    public void show() {
        System.out.println(id + "    " + name + "    必修    " + teacher + "    " + stuNum + "    " + credit);
    }

    // 课程列表查看
    public static void showRequiredCourses() {
        System.out.println("编号  课程    类型    教师    选课人数    学分");
        for (Course rCourse : rclist) {
            rCourse.show();

        }
    }

    public RequiredCourse(int id, String name, int type, int stuNum, String teacher, int credit) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.teacher = teacher;
        this.stuNum = stuNum;
        this.credit = credit;
    }

}
