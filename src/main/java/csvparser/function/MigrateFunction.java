package csvparser.function;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class MigrateFunction extends DataSourceFunction<String> {

    public MigrateFunction(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String get() throws Exception {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
        return "OK";
    }
}
