package com.cloudE.pay.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;



@Service
public class RedisService {

    private static String redisCode = "utf-8";

    public RedisService() {
    }

    @Autowired
    private RedisTemplate redisTemplate;

    public long del(final String... keys) {
        return (long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                for (int i = 0; i < keys.length; i++) {
                    result = connection.del(keys[i].getBytes());
                }
                return result;
            }
        });
    }

    /**
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }

    /**
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key, String value, long liveTime) {
        this.set(key.getBytes(), value.getBytes(), liveTime);
    }

    /**
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        this.set(key, value, 0L);
    }

    /**
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value) {
        this.set(key, value, 0L);
    }

    /**
     * @param key
     * @return
     */
    public String get(final String key) {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    return new String(connection.get(key.getBytes()), redisCode);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return "";
            }
        });
    }

//    /**
//     * @param pattern
//     * @return
//     */
//    public Setkeys(String pattern) {
//        return redisTemplate.keys(pattern);
//
//    }

    /**
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return (boolean) redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }

    /**
     * @return
     */
    public String flushDB() {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    /**
     * @return
     */
    public long dbSize() {
        return (long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    /**
     * @return
     */
    public String ping() {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {

                return connection.ping();
            }
        });
    }

    public void hSet(String key, String field, String value) {
        redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                Boolean isSuccess = connection.hSet(key.getBytes(), field.getBytes(), value.getBytes());
                return isSuccess;
            }
        });
    }

    public void hSet(String key, String field, String value, Long liveTime) {
        redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                Boolean isSuccess = connection.hSet(key.getBytes(), field.getBytes(), value.getBytes());
                if (liveTime > 0) {
                    connection.expire(key.getBytes(), liveTime);
                }
                return isSuccess;
            }
        });
    }

    public String hGet(String key, String field) {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                if(exists(key)){
                    return new String(connection.hGet(key.getBytes(), field.getBytes()));
                }else {
                    return null;
                }

            }
        });
    }

    public Map<String, String> hGetAll(String key) {
        return (Map<String, String>) redisTemplate.execute(new RedisCallback() {
            public Map<String, String> doInRedis(RedisConnection connection) throws DataAccessException {
                if(exists(key)){
                    Map<byte[], byte[]> map = connection.hGetAll(key.getBytes());
                    Map<String, String> resMap = new HashMap<>();
                    map.forEach((key, value) -> resMap.put(new String(key), new String(value)));
                    return resMap;
                }else {
                    return new HashMap<>();
                }

            }
        });
    }


}
