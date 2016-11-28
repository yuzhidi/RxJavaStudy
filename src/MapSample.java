import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by wangliang on 28/11/16.
 */
public class MapSample {
    public static void println(String s) {
        System.out.println(s);
    }
    class  Course {
        private String name;//课程名
        private String id;
    }
    static class Student {
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
    }
    public static final void main(String[] args) {
        testMapBasic();
        testMapTwice();
    }

    static void testMapBasic() {
        List<String> nameList = new ArrayList<>();

        Observable.just(new Student("kaka") , new Student("haha"), new Student("rolo"))
                //使用map进行转换，参数1：转换前的类型，参数2：转换后的类型
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student i) {
                        String name = i.getName();//获取Student对象中的name
                        return name;//返回name
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        println("name:"+s);
                        nameList.add(s);
                    }
                });
    }

    static void testMapTwice() {
        //多次使用map，想用几个用几个
        Observable.just("Hello", "World")
                .map(new Func1<String, Integer>() {//将String类型的转化为Integer类型的哈希码
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .map(new Func1<Integer, String>() {//将转化后得到的Integer类型的哈希码再转化为String类型
                    @Override
                    public String call(Integer integer) {
                        return integer.intValue() + "";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        println(s);
                    }
                });
    }
}
