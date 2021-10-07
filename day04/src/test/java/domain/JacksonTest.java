package domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JacksonTest {

    //Java对象转为JSON字符串
    @Test
    public void test1() throws IOException {
        //1. 创建Person对象
        Person person = new Person();
        person.setName("张三");
        person.setAge(23);
        person.setGender("男");

        //2. 创建Jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //3. 转换
        /*
            转换方法：
                writeValue(参数1 obj):
                    参数1:
                        File: 将obj转为JSON字符串，并保存到指定的文件中
                        Writer: 将obj转为JSON字符串，并将JSON字符串填充到字符输出流中
                        OutputStream: 将obj转为JSON字符串，并将JSON字符串填充到字节输出流中
                writeValueAsString(Object value):将Object对象转为JSON字符串
         */

//        String json = mapper.writeValueAsString(person);
//        System.out.println(json); // {"name":"张三","age":23,"gender":"男"}

//        //使用writeValue方法，将数据写入d://a.txt文件中
//        mapper.writeValue(new File("d://a.txt"), person);

        //使用writeValue方法，将数据关联到Writer对象中
        mapper.writeValue(new FileWriter("d://b.txt"), person);
    }

    @Test
    public void test2() throws IOException {
        //1. 创建Person对象
        Person person = new Person();
        person.setName("张三");
        person.setAge(23);
        person.setGender("男");
        person.setBirthday(new Date());

        //2. 创建Jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //3. 转换
        String json = mapper.writeValueAsString(person);
        System.out.println(json);
    }

    @Test
    public void test3() throws IOException {
        //1. 创建Person对象
        Person person1 = new Person();
        person1.setName("张三");
        person1.setAge(23);
        person1.setGender("男");
        person1.setBirthday(new Date());

        Person person2 = new Person();
        person2.setName("李四");
        person2.setAge(24);
        person2.setGender("男");
        person2.setBirthday(new Date());

        Person person3 = new Person();
        person3.setName("王五");
        person3.setAge(25);
        person3.setGender("男");
        person3.setBirthday(new Date());

        //2. 创建List集合
        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);

        //2. 创建Jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //3. 转换
        String json = mapper.writeValueAsString(persons);
        System.out.println(json);
    }

    @Test
    public void test4() throws IOException {
        //1. 创建Map对象
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 23);
        map.put("gender", "男");

        //2. 创建Jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //3. 转换
        String json = mapper.writeValueAsString(map);
        System.out.println(json);
    }


    //JSON字符串转为Java对象
    @Test
    public void test5() throws IOException {
        //1. 初始化JSON字符串
        String json = "{\"name\":\"张三\",\"age\":23,\"gender\":\"男\"}";

        //2. 创建Jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(json, Person.class);
        System.out.println(person);
    }
}
