package csvparser.function;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class ClearDatabaseFunction extends DataSourceFunction<String> {

    public ClearDatabaseFunction(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String get() throws Exception {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.clean();
        return "OK";
    }
}
