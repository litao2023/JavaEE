package util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * JedisPool工具类
 * 加载配置文件，其中配置有Jedis连接池相关参数
 * 提供获取连接的方法
 */
public class JedisPoolUtils {

    private static JedisPool jedisPool;

    static {
        //读取配置文件
        InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        //创建Properties对象
        Properties pro = new Properties();
        //关联文件
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取数据，设置到JedisPoolConfig中
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        jedisPoolConfig.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));

        //初始化JedisPool
        jedisPool = new JedisPool(jedisPoolConfig, pro.getProperty("host"), Integer.parseInt(pro.getProperty("port")));

    }

    /**
     * 获取连接的方法
     */
    public static Jedis getJedis(){

        return jedisPool.getResource();
    }

}
