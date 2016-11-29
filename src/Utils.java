import java.util.List;

/**
 * Created by wangliang on 28/11/16.
 */
public class Utils {
    public static void println(String s) {
        System.out.println(s);
    }
}

class  Course {
    private String name;//课程名
    private String id;

    public Course(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Student {
    String name;
    private List<Course> coursesList;//所修的课程

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }
}
