package org.example.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(
        basePackages = {"org.example.dao"},
        sqlSessionFactoryRef = DatabaseConfig.SESSION_FACTORY
)
public class DatabaseConfig {
    static final String DATASOURCE = "DS";
    static final String SESSION_FACTORY = "SessionFac";
    static final String TRANSACTION_MANAGER = "TransactionManager";
    static final String HIKARI_CONFIG = "HikariConfig";

    @Value("${mybatis.mapper-location}")
    private String mapperLocation;

    @Bean(name = HIKARI_CONFIG)
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariDataSource() {
        return new HikariConfig();
    }

    @Bean(name = DATASOURCE)
    public HikariDataSource dataSource(@Qualifier(HIKARI_CONFIG) HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = TRANSACTION_MANAGER)
    public DataSourceTransactionManager transactionManager(@Qualifier(DATASOURCE) HikariDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory(@Qualifier(DATASOURCE) HikariDataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        return sessionFactory.getObject();
    }
}