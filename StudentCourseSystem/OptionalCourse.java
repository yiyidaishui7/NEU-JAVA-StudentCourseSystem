import java.util.Vector;

public class OptionalCourse extends Course {
    public int maxStuNum;
    // 对OptionalCourse进行列表存储
    static Vector<Course> oclist = new Vector();

    public OptionalCourse(int i, String s) {
        this.id = i;
        this.name = s;
        type = 1;
    }

    public String toString() {
        return super.toString() + " " + this.maxStuNum;
        // super.toString将调用父类Course类的toString方法
        // 将必修课的相关信息合并为字符串返回
    }

    // 子类的方法，提供具体实现
    public int getMaxStuNum() {
        return maxStuNum;
    }

    public void show() {
        System.out.println(id + "    " + name + "    选修    " + teacher + "    " + stuNum + "    " + maxStuNum);
    }

    // 课程列表查看
    public static void showOptionalCourses() {
        System.out.println("编号  课程    类型    教师    选课人数    最大选课人数");
        for (Course oCourse : oclist) {
            oCourse.show();
        }
    }

    public OptionalCourse(int id, String name, int type, int stuNum, String teacher, int maxStuNum) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.teacher = teacher;
        this.stuNum = stuNum;
        this.maxStuNum = maxStuNum;
    }

}
