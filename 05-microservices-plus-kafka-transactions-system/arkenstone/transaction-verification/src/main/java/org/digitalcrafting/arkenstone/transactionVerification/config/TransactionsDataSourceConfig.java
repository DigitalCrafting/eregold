package org.digitalcrafting.arkenstone.transactionVerification.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
//Scan mapper interface and container management
@MapperScan(basePackages = TransactionsDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "transactionsSqlSessionFactory")
public class TransactionsDataSourceConfig {

    //Accurate to the master directory for isolation from other data sources
    static final String PACKAGE = "org.digitalcrafting.arkenstone.transactionVerification.repository.transactions";
    static final String MAPPER_LOCATION = "classpath:mybatis/mappers/transactions/*.xml";
    static final String CONFIG_LOCATION = "classpath:mybatis/mybatis-config.xml";

    @Value("${arkenstone.datasource.transactions.url}")
    private String url;

    @Value("${arkenstone.datasource.transactions.username}")
    private String user;

    @Value("${arkenstone.datasource.transactions.password}")
    private String password;

    @Value("${arkenstone.datasource.transactions.driver-class-name}")
    private String driverClass;

    @Bean(name = "transactionsDataSource")
    public DataSource transactionsDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "transactionsTransactionManager")
    public DataSourceTransactionManager transactionsTransactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(transactionsDataSource());
    }

    @Bean(name = "transactionsSqlSessionFactory")
    public SqlSessionFactory transactionsSqlSessionFactory(@Qualifier("transactionsDataSource") DataSource transactionsDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(transactionsDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources(MAPPER_LOCATION));
        sessionFactory.setConfigLocation(resolver.getResource(CONFIG_LOCATION));
        return sessionFactory.getObject();
    }
}
