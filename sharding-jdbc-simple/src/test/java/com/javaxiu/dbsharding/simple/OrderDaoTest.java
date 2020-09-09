package com.javaxiu.dbsharding.simple;

import com.javaxiu.dbsharding.simple.dao.DictDao;
import com.javaxiu.dbsharding.simple.dao.OrderDao;
import com.javaxiu.dbsharding.simple.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShardingJdbcSimpleBootstrap.class})
public class OrderDaoTest {

    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    @Autowired
    DictDao dictDao;

    /**
     * @Description: 水平分表新增测试
     *
     * @Author: java秀 javaxiu@javaxiu.com
     * @Date: 2020/9/9 15:48
     * @Version V1.0
     */
    @Test
    public void testInsertOrder(){
        for(int i=1;i<20;i++){
            orderDao.insertOrder(new BigDecimal(i),4L,"SUCCESS");
        }
    }

    /**
     * @Description: 水平分表查询测试
     *
     * @Author: java秀 javaxiu@javaxiu.com
     * @Date: 2020/9/9 15:48
     * @Version V1.0
     */
    @Test
    public void testSelectOrderbyIds(){
        List<Long> ids = new ArrayList<>();
        ids.add(510452600953372673L);
        ids.add(510452600550719488L);

        List<Map> maps = orderDao.selectOrderbyIds(ids);
        System.out.println(maps);
    }

    /**
     * @Description: 水平分库测试
     *
     * @Author: java秀 javaxiu@javaxiu.com
     * @Date: 2020/9/9 15:47
     * @Version V1.0
     */
    @Test
    public void testSelectOrderbyUserId(){
        long userId1 = 4;
        long userId2 = 3;

        List<Map> maps1 = orderDao.selectOrderbyUserId(userId1);
        List<Map> maps2 = orderDao.selectOrderbyUserId(userId2);
        System.out.println(maps1);
        System.out.println(maps2);
    }

    @Test
    public void testSelectOrderbyUserAndIds(){
        List<Long> ids = new ArrayList<>();
        ids.add(510452600953372673L);
//        ids.add(510452600550719488L);

        List<Map> maps = orderDao.selectOrderbyUserAndIds(4L,ids);
        System.out.println(maps);
    }

    /**
     * @Description: sharding-jdbc读写分离-写
     *
     * @Author: java秀 javaxiu@javaxiu.com
     * @Date: 2020/9/9 18:10
     * @Version V1.0
     */
    @Test
    public void testInsertUser(){
        for (int i = 10 ; i<14; i++){
            Long id = i + 1L;
            userDao.insertUser(id,"姓名"+ id );
        }
    }

    /**
     * @Description: 垂直分表查询测试/sharding-jdbc读写分离-读
     *
     * @Author: java秀 javaxiu@javaxiu.com
     * @Date: 2020/9/9 16:10
     * @Version V1.0
     */
    @Test
    public void testSelectUserbyIds(){
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        List<Map> users = userDao.selectUserbyIds(userIds);
        System.out.println(users);
    }

    @Test
    public void testSelectUserInfobyIds(){
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        List<Map> users = userDao.selectUserInfobyIds(userIds);
        System.out.println(users);
    }

    /**
     * @Description: Sharding-JDBC实现公共表测试
     *
     * @Author: java秀 javaxiu@javaxiu.com
     * @Date: 2020/9/9 16:39
     * @Version V1.0
     */
    @Test
    public void testInsertDict(){
        dictDao.insertDict(3L,"user_type","2","超级管理员");
        dictDao.insertDict(4L,"user_type","3","二级管理员");
    }
    @Test
    public void testDeleteDict(){
        dictDao.deleteDict(3L);
        dictDao.deleteDict(4L);
    }
}
