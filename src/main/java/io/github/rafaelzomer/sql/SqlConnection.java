package io.github.rafaelzomer.sql;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;

public class SqlConnection {
  public Connection getConnection() throws Exception {
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUser("epiz_22829390");
    dataSource.setPassword("N93jGqiSQ");
    dataSource.setServerName("sql312.epizy.com");
    return dataSource.getConnection();
  }
}
