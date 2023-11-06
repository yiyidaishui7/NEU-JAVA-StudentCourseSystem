import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请选择您的身份:");
        while (true) {
            System.out.println("请选择身份:\n1.管理员\n2.老师\n3.学生\n0.退出");
            int choose = sc.nextInt();
            if (choose == 0) {
                System.out.println("欢迎使用学生选课系统，再见!");
                break;
            } else if (choose < 1 || choose > 3) {
                System.out.println("输入错误!请重新输入:");
                continue;
            } else {
                switch (choose) {
                    case 1:
                        adminMenu();
                        break;
                    case 2:
                        teacherMenu();
                        break;
                    case 3:
                        stuMenu();
                        break;
                }
            }

        }

    }

    //管理员页面
    public static void adminMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入管理员密码:");
        String pass = sc.next();
        if (pass.equals(Users.admin.pass)) {
            while (true) {
                System.out.println(
                        "请选择操作:\n1.新建课程 \n2.删除课程 \n3.设置课程老师 \n4.查找老师讲授课程 \n" +
                                "5.显示课程列表 \n6.按照选课人数排序 \n7.显示学生列表 \n8.显示教师列表 \n" +
                                "9.添加老师或学生 \n10.删除老师和学生 \n11.恢复学生和教师初始密码 \n0.退出");
                int choose = sc.nextInt();
                if (choose == 0) {
                    System.out.println("欢迎使用学生选课系统，再见!");
                    break;
                } else if (choose < 1 || choose > 11) {
                    System.out.println("输入错误!请重新输入:");
                    continue;
                } else {
                    switch (choose) {
                        case 1:
                            Courses.addCourses();//新建课程
                            break;
                        case 2:
                            Courses.deleteCourses();//删除课程
                            break;
                        case 3:
                            Courses.setCourseTeacher();//设置课程老师
                            break;
                        case 4:
                            Courses.SearchCourseByTeacher();//查找老师讲授课程
                            break;
                        case 5:
                            Courses.showCourses();//显示课程列表
                            break;
                        case 6:
                            Courses.SortCourseList();//按照选课人数排序
                            break;
                        case 7:
                            Users.showStudents();//显示学生列表
                            break;
                        case 8:
                            Users.showTeachers();//显示教师列表
                            break;
                        case 9:
                            Users.addUsers();//添加老师或学生
                            break;
                        case 10:
                            Users.deleteUsers();//删除老师和学生
                            break;
                        case 11:
                            Users.setOriginpass();//恢复学生和教师初始密码
                            break;
                    }
                }
            }
        } else {
            System.out.println("密码错误!");
        }
    }

    //教师页面
    public static void teacherMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入工号：");
        int inputWorkId = sc.nextInt();
        System.out.println("请输入密码：");
        String inputPass = sc.next();
        Teacher thr = Users.loginTeacher(inputWorkId, inputPass);
        if (thr != null) {
            while (true) {
                System.out.println(
                        "请选择操作:\n1.修改登录密码\n2.查看所授课程\n3.查看所授课程的上课学生列表\n0.退出");
                int choose = sc.nextInt();
                if (choose == 0) {
                    System.out.println("欢迎使用学生选课系统，再见!");
                    break;
                } else if (choose < 1 || choose > 3) {
                    System.out.println("输入错误!请重新输入:");
                    continue;
                } else {
                    switch (choose) {
                        case 1:
                            Users.changeTeacherPass();//修改登录密码
                            break;
                        case 2:
                            Teacher.showTeacherCourse(thr.name);//查看所授课程
                            break;
                        case 3:
                            Teacher.showCourseStuNum(thr.name,thr.level);//查看所授课程的上课学生列表
                            break;
                    }
                }
            }
        } else {
            System.out.println("密码错误!");
        }
    }

    //学生页面
    public static void stuMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学号:");
        int inputId = sc.nextInt();
        System.out.println("请输入密码:");
        String inputPass = sc.next();
        Student stu = Users.loginStudent(inputId, inputPass);
        if (stu != null) {//如果不为空
            while (true) {
                System.out.println(
                        "请选择操作:\n1.修改登录密码\n2.查看所上课程\n3.选修课选课\n0.退出");
                int choose = sc.nextInt();
                if (choose == 0) {
                    System.out.println("欢迎使用学生选课系统，再见!");
                    break;
                } else if (choose < 1 || choose > 3) {
                    System.out.println("输入错误!请重新输入:");
                    continue;
                } else {
                    switch (choose) {
                        case 1:
                            Users.changeStudentPass();//修改登录密码
                            break;
                        case 2:
                            Student.showStudentCourses(inputId);//查看所上课程
                            break;
                        case 3:
                            Student.studentCourses();//选修课选课
                            break;
                    }
                }
            }
        } else {
            System.out.println("密码错误!");
        }
    }

    public static void main(String[] args) {

//        Course c1= new OptionalCourse(6, "公路交通", 1, 66, "马俐", 120);
//        Course c2= new OptionalCourse(3, "数据库", 1, 57, "杨三", 80);
//        Course c3= new OptionalCourse(7, "Python", 1, 50, "栗伟", 53);
//        Course c4= new OptionalCourse(4, "JAVA", 1, 49, "吴四", 75);
//

        // Student stu1 = new Student("uuu", "123456", 3434, "计2101");
        // Student stu2 = new Student("www", "123456", 3435, "计2102");
        // Users.studentList.add(stu1);
        // Users.studentList.add(stu2);
        // Teacher t = new Teacher("lee", "888888", 7568, "教授");
        // Users.teacherList.add(t);

        Courses.readCourse();
        Student.readStudent();
        Teacher.readTeacher();
        menu();
        Courses.saveCourse();
        Student.saveStudents();
        Teacher.saveTeachers();
    }
}
