package org.digitalcrafting.arkenstone.transactionVerification.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@Profile({"local", "docker"})
//Scan mapper interface and container management
@MapperScan(basePackages = AccountsDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "accountsSqlSessionFactory")
public class AccountsDataSourceConfig {

    //Accurate to the master directory for isolation from other data sources
    static final String PACKAGE = "org.digitalcrafting.arkenstone.transactionVerification.repository.accounts";
    static final String MAPPER_LOCATION = "classpath:mybatis/mappers/accounts/*.xml";
    static final String CONFIG_LOCATION = "classpath:mybatis/mybatis-config.xml";

    @Value("${arkenstone.datasource.accounts.url}")
    private String url;

    @Value("${arkenstone.datasource.accounts.username}")
    private String user;

    @Value("${arkenstone.datasource.accounts.password}")
    private String password;

    @Value("${arkenstone.datasource.accounts.driver-class-name}")
    private String driverClass;

    @Bean(name = "accountsDataSource")
    @Primary
    public DataSource accountsDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "accountsTransactionManager")
    @Primary
    public DataSourceTransactionManager accountsTransactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(accountsDataSource());
    }

    @Bean(name = "accountsSqlSessionFactory")
    @Primary
    public SqlSessionFactory accountsSqlSessionFactory(@Qualifier("accountsDataSource") DataSource accountsDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(accountsDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources(MAPPER_LOCATION));
        sessionFactory.setConfigLocation(resolver.getResource(CONFIG_LOCATION));
        return sessionFactory.getObject();
    }
}
