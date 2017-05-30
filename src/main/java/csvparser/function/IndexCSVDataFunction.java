package csvparser.function;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileReader;

public class IndexCSVDataFunction extends DataSourceFunction<String> {
    private final File csv;

    public IndexCSVDataFunction(DataSource dataSource, File csv) {
        super(dataSource);
        this.csv = csv;
    }

    @Override
    public String get() throws Exception {
        BaseConnection connection = (BaseConnection) dataSource.getConnection(); // unsafe
        CopyManager copyManager = new CopyManager(connection);
        copyManager.copyIn(
                "COPY Reviews FROM STDIN WITH CSV HEADER DELIMITER AS ',';",
                new FileReader(csv));
        return "OK";
    }

}
