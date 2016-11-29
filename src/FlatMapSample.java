import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangliang on 29/11/16.
 */
public class FlatMapSample {
    static List<Student> sStudents = new ArrayList<Student>();
    static int i;
    static String getCourse() {
       return "course" + i;
    }
    static String getId() {
        return "id:" +i++;
    }

    static {
        Utils.println("init");
        Student student0 = new Student("leo");
        List<Course> courses = new ArrayList<>();
        courses.add(new Course(getCourse(),getId()));
        courses.add(new Course(getCourse(),getId()));
        courses.add(new Course(getCourse(),getId()));
        student0.setCoursesList(courses);

        Student student1 = new Student("lel");
        List<Course> courses1 = new ArrayList<>();
        courses1.add(new Course(getCourse(),getId()));
        courses1.add(new Course(getCourse(),getId()));
        courses1.add(new Course(getCourse(),getId()));
        student1.setCoursesList(courses1);

        Student student2 = new Student("nen");
        List<Course> courses2 = new ArrayList<>();
        courses2.add(new Course(getCourse(),getId()));
        courses2.add(new Course(getCourse(),getId()));
        courses2.add(new Course(getCourse(),getId()));
        student2.setCoursesList(courses2);

        sStudents.add(student0);
        sStudents.add(student1);
        sStudents.add(student2);
    }
    public static final void main(String[] args) {
        useMap();
        Utils.println("============= divide ===============");
        useFlatMap();
    }

    private static void useMap() {
        Action1<List<Course>> action1 = new Action1<List<Course>>() {
            @Override
            public void call(List<Course> courses) {
                Utils.println("action1 call");
                //遍历courses，输出cuouses的name
                for (int i = 0; i < courses.size(); i++){
                     Utils.println(courses.get(i).getName());
                }
            }
        };
        Observable.from(sStudents)
                .map(new Func1<Student, List<Course>>() {
                    @Override
                    public List<Course> call(Student student) {
                        Utils.println("map call");
                        //返回coursesList
                        return student.getCoursesList();
                    }
                })
                .subscribe(action1);
    }

    private static void useFlatMap() {
        Observable.from(sStudents)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCoursesList());
                    }
                })
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        Utils.println(course.getName());
                    }
                });
    }
}
