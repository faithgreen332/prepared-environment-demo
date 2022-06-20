package com.funpay.management.configuration.dynamicdatasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Leeko
 * @date 2022/1/20
 **/
@Configuration
public class DataSourceConfig {

    public static final String DS_LOC = "loc";
    public static final String DS_UC = "uc";

    @Bean(DS_LOC)
    public HikariDataSource loc() {
        return new HikariDataSource(new HikariConfig("/loc_datasource.properties"));
    }

//    @Bean(DS_UC)
//    public HikariDataSource uc() {
//        return new HikariDataSource(new HikariConfig("/uc_datasource.properties"));
//    }

    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(loc());
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>(2);
        dsMap.put(DS_LOC, loc());
//        dsMap.put(DS_UC, uc());

        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    static class DynamicDataSource extends AbstractRoutingDataSource {

        @Override
        protected Object determineCurrentLookupKey() {
            return DataSourceContextHolder.getDb();
        }
    }

    static class DataSourceContextHolder {
        static Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);
        /**
         * 默认数据源
         */
        public static final String DEFAULT_DS = DataSourceEnum.LOC.poolName;

        private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

        // 设置数据源名
        public static void setDb(String dbType) {
            CONTEXT_HOLDER.set(dbType);
        }

        // 获取数据源名
        public static String getDb() {
            String dbsource = CONTEXT_HOLDER.get();
            if (dbsource == null) {
                dbsource = DEFAULT_DS;
                logger.info("数据源为NULL 返回默认数据源" + dbsource);
            }
            return dbsource;
        }

        // 清除数据源名
        public static void clearDb() {
            CONTEXT_HOLDER.remove();
        }
    }

    enum DataSourceEnum {
        /**
         * 本地数据源
         */
        LOC(DS_LOC),
        /**
         * 菲律宾沙盒数据源
         */
        UC(DS_UC);

        private final String poolName;

        DataSourceEnum(String poolName) {
            this.poolName = poolName;
        }
    }
}
