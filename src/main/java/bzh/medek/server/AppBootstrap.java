package bzh.medek.server;

import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;

public class AppBootstrap {

    public static void main(String[] args) {
        try {
            Swarm app = new Swarm().fraction(new DatasourcesFraction().jdbcDriver("mysql", (d) -> {
                d.driverClassName("com.mysql");
                d.xaDatasourceClass("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
                d.driverModuleName("com.mysql");
            }).dataSource("medekserver", (ds) -> {
                ds.driverName("mysql");
                ds.connectionUrl("jdbc:mysql://localhost/meddb");
                ds.userName("medekdb");
                ds.password("medekdb");
            }));
            app.start().deploy();
        } catch (Exception e) {
            // TODO
        }
    }

}
