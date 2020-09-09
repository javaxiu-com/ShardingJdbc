package com.javaxiu.dbsharding.simple.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;


/**
 * 根据id 进行分表
 */
public class AdminSingleKeyTableShardingAlgorithm implements PreciseShardingAlgorithm<String> {


    @Override
    public String doSharding(final Collection<String> availableTargetNames, final PreciseShardingValue<String> shardingValue) {

        String col = shardingValue.getColumnName();  // sharding  配置的 id 名称

        String value = shardingValue.getValue();//value 为代码中 id 的真实数据，比如 插入，id 为7,则 value 为7
        String values = value.replaceAll("", "");
        Long aLong = Long.valueOf(values);
        Long aLongs = aLong % 2 + 1;
        String s = aLongs.toString();
        return s;
    }
}
