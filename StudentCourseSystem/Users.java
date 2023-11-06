
import java.util.Scanner;
import java.util.Vector;

public class Users {
    static User admin = new User("admin", "123456");

    static Vector<Student> studentList = new Vector<Student>();

    static Vector<Teacher> teacherList = new Vector<Teacher>();

    // 添加老师或学生
    public static void addUsers() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("你要添加什么用户?\n1.老师\n2.学生\n0.结束");
            int choose = sc.nextInt();
            if (choose == 0) {
                System.out.println("添加完成,再见!");
                break;
            } else if (choose < 1 || choose > 2) {
                System.out.println("输入错误!请重新输入:");
                continue;
            } else {
                switch (choose) {
                    case 1: {
                        Users.teacherList.add(Teacher.inputTeacher());
                        break;
                    }

                    case 2: {
                        Users.studentList.add(Student.inputStudent());
                        break;
                    }
                }
            }
        }
    }

    // 删除老师或学生
    public static void deleteUsers() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("你要删除什么用户?\n1.老师\n2.学生\n0.结束");
            int choose = sc.nextInt();
            if (choose == 0) {
                System.out.println("删除完成,再见!");
                break;
            } else if (choose < 1 || choose > 2) {
                System.out.println("输入错误!请重新输入:");
                continue;
            } else {
                switch (choose) {
                    case 1: {
                        Teacher.deleteTeacher();
                        break;
                    }

                    case 2: {
                        Student.deleteStudent();
                        break;
                    }
                }
            }
        }
    }

    public static void showStudents() {
        System.out.println("学号    名字    班级");
//        for (int i = 0; i < Users.studentList.size(); i++) {
//            Users.studentList.get(i).show();
//        }
        for (Student student : studentList) {
            student.show();
        }
    }

    public static void showTeachers() {
        System.out.println("工号    名字    职称");
//        for (int i = 0; i < Users.teacherList.size(); i++) {
//            Users.teacherList.get(i).show();
//        }
        for (Teacher teacher : teacherList){
            teacher.show();
        }
    }

    public static Teacher loginTeacher(int inputWorkId, String inputPass) {
        for (Teacher teacher : teacherList) {
            if (teacher.workId == inputWorkId && teacher.pass.equals(inputPass)) {
                System.out.println(teacher.name + " " + teacher.level + ",你好！");
                return teacher;
            }
        }
        return null;
    }

    public static Student loginStudent(int inputId, String inputPass) {
        for (Student student : studentList) {
            if (student.id == inputId && student.pass.equals(inputPass)) {
                System.out.println(student.Class + " " + student.name + ",你好！");
                return student;
            }
        }
        return null;
    }


    public static void changeStudentPass() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要设置的学生学号:");
        int inputId = sc.nextInt();
        System.out.println("请输入原始密码:");
        String inputPass = sc.next();
        int flag = 0;
        for (Student student : studentList) {
            if (student.id == inputId && student.pass.equals(inputPass)) {
                System.out.println("请输入新密码:");
                String inputPass1 = sc.next();
                System.out.println("请再次输入新密码：");
                //String inputPass2 = sc.next();
                if (inputPass1.equals(sc.next())) {
                    flag =1;
                    if (inputPass.equals(inputPass1)) {
                        System.out.println("新密码不得与原密码相同!");
                    } else {
                        student.pass = inputPass1;
                        System.out.println("新密码修改成功!");
                    }
                }
            }

        }
        if(flag == 0){
            System.out.println("查询不到该学生信息或者密码错误!");
        }
    }

    public static void changeTeacherPass() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要设置的教师工号:");
        int inputWorkId = sc.nextInt();
        System.out.println("请输入原始密码:");
        String inputPass = sc.next();
        int flag=0;
        for (Teacher teacher : teacherList) {
            if (teacher.workId == inputWorkId && teacher.pass.equals(inputPass)) {
                System.out.println("请输入新密码:");
                String inputPass1 = sc.next();
                System.out.println("请再次输入新密码!");
                String inputPass2 = sc.next();
                if (inputPass1.equals(inputPass2)) {
                    flag=1;
                    if (inputPass.equals(inputPass1)) {
                        System.out.println("新密码不得与原密码相同!");
                    } else {
                        teacher.pass = inputPass1;
                        System.out.println("新密码修改成功!");
                    }
                }
            }
        }
        if(flag == 0){
            System.out.println("查询不到该老师信息或者密码错误!");
        }
    }

    // admin重置密码
    public static void setOriginpass() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("你要重置谁的密码?\n1.老师\n2.学生\n0.结束");
            int choose = sc.nextInt();
            if (choose == 0) {
                System.out.println("重置完成,再见!");
                break;
            } else if (choose < 1 || choose > 2) {
                System.out.println("输入错误!请重新输入:");
                continue;
            } else {
                int flag =0;
                switch (choose) {
                    case 1: {
                        System.out.println("请输入要设置的教师工号:");
                        int workId = sc.nextInt();
                        for (Teacher teacher : teacherList) {
                            if (teacher.workId == workId) {
                                teacher.pass = "123456";
                                flag=1;
                                System.out.println("重置完成!");
                                break;
                            }
                        }
                        if(flag==0)
                        {
                            System.out.println("查询不到该教师信息, 重置密码失败!");
                        }
                        break;
                    }

                    case 2: {
                        System.out.println("请输入要设置的学生学号:");
                        int id = sc.nextInt();
                        for (Student student : studentList) {
                            if (student.id == id) {
                                student.pass = "123456";
                                flag =1;
                                System.out.println("重置完成!");
                                break;
                            }
                        }
                        if(flag==0)
                        {
                            System.out.println("查询不到该学生信息, 重置密码失败!");
                        }
                        break;
                    }
                }
            }
        }
    }
}
