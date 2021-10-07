package jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import util.JedisPoolUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis的测试类
 */
public class JedisTest {

    /**
     * 快速入门
     */
    @Test
    public void test1(){
        //1. 获取连接
        Jedis jedis = new Jedis("localhost", 6379);

        //2. 操作
        jedis.set("username", "zhangsan");

        //3. 关闭连接，释放资源
        jedis.close();
    }

    /**
     * String 数据结构操作
     */
    @Test
    public void test2(){
        //1. 获取连接
        Jedis jedis = new Jedis(); //空参构造，默认值："localhost", "6379"

        //2. 操作
        // 存储
        jedis.set("username", "zhangsan");
        // 获取并打印
        String username = jedis.get("username");
        System.out.println(username);

        //3. 关闭连接，释放资源
        jedis.close();
    }

    /**
     * String 数据结构操作 使用setex()方法存储指定过期时间的key-value
     */
    @Test
    public void test3(){
        //1. 获取连接
        Jedis jedis = new Jedis(); //空参构造，默认值："localhost", "6379"

        //2. 操作
        // 存储，将"password":"123"键值对存入redis，并且20秒后自动删除该键值对
        jedis.setex("password", 20, "123");

        //3. 关闭连接，释放资源
        jedis.close();
    }

    /**
     * Hash 数据结构操作
     */
    @Test
    public void test4(){
        //1. 获取连接
        Jedis jedis = new Jedis(); //空参构造，默认值："localhost", "6379"

        //2. 操作
        // 存储
        jedis.hset("user", "name", "lisi");
        jedis.hset("user", "age", "23");
        jedis.hset("user", "gender", "man");

        // 获取并打印
        String name = jedis.hget("user", "name");
        System.out.println(name);

        // 获取hash中所有map数据
        Map<String, String> user = jedis.hgetAll("user");
        for (String s : user.keySet()) {
            System.out.println(s + "---" + user.get(s));
        }

        //3. 关闭连接，释放资源
        jedis.close();
    }

    /**
     * List 数据结构操作
     */
    @Test
    public void test5(){
        //1. 获取连接
        Jedis jedis = new Jedis(); //空参构造，默认值："localhost", "6379"

        //2. 操作
        // 存储，结果：wangwu、lisi、zhangsan、zhaoliu、tianqi
        jedis.lpush("username", "zhangsan", "lisi", "wangwu"); //从左边存
        jedis.rpush("username", "zhaoliu", "tianqi"); //从右边存

        // 获取并打印
        List<String> username = jedis.lrange("username", 0, -1);
        System.out.println(username);

        //List 弹出
        String element = jedis.lpop("username");
        System.out.println(element);

        //3. 关闭连接，释放资源
        jedis.close();
    }

    /**
     * Set 数据结构操作
     */
    @Test
    public void test6(){
        //1. 获取连接
        Jedis jedis = new Jedis(); //空参构造，默认值："localhost", "6379"

        //2. 操作
        // 存储
        jedis.sadd("usernames", "zhangsan", "lisi", "wangwu");
        // 获取并打印
        Set<String> usernames = jedis.smembers("usernames");
        System.out.println(usernames);

        //3. 关闭连接，释放资源
        jedis.close();
    }

    /**
     * Sortedset 数据结构操作
     */
    @Test
    public void test7(){
        //1. 获取连接
        Jedis jedis = new Jedis(); //空参构造，默认值："localhost", "6379"

        //2. 操作
        // 存储
        jedis.zadd("usernames", 60, "zhangsan");
        jedis.zadd("usernames", 80, "lisi");
        jedis.zadd("usernames", 50, "wangwu");
        // 获取并打印
        Set<String> usernames = jedis.zrange("usernames", 0, -1);
        System.out.println(usernames);

        //3. 关闭连接，释放资源
        jedis.close();
    }

    /**
     * Jedis 连接池
     */
    @Test
    public void test8(){
        //0. 创建连接池配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50); // 设置最大允许连接数
        jedisPoolConfig.setMaxIdle(10); // 设置最大空闲连接数

        //1. 创建Jedis连接池对象
        JedisPool jedisPool = new JedisPool(jedisPoolConfig);//空参构造，默认值："localhost", "6379"

        //2. 获取连接
        Jedis jedis = jedisPool.getResource();


        //3. 操作
        // 存储
        jedis.zadd("usernames", 60, "zhangsan");
        jedis.zadd("usernames", 80, "lisi");
        jedis.zadd("usernames", 50, "wangwu");
        // 获取并打印
        Set<String> usernames = jedis.zrange("usernames", 0, -1);
        System.out.println(usernames);

        //4. 关闭，归还到连接池中
        jedis.close();
    }

    /**
     * Jedis连接池工具类 JedisPoolUtils 测试
     */
    @Test
    public void test9(){

        //1. 通过JedisPoolUtils获取连接
        Jedis jedis = JedisPoolUtils.getJedis();

        //2. 操作
        // 存储
        jedis.zadd("usernames", 60, "zhangsan");
        jedis.zadd("usernames", 80, "lisi");
        jedis.zadd("usernames", 50, "wangwu");
        // 获取并打印
        Set<String> usernames = jedis.zrange("usernames", 0, -1);
        System.out.println(usernames);

        //4. 关闭，归还到连接池中
        jedis.close();
    }

}
