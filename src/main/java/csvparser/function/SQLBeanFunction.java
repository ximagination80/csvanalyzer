package csvparser.function;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.util.stream.Collectors;

abstract class SQLBeanFunction<E> extends DataSourceFunction<String> {

    private final Class<E> cls;
    private final QueryRunner runner;

    SQLBeanFunction(DataSource dataSource, Class<E> cls) {
        super(dataSource);
        this.cls = cls;
        this.runner = new QueryRunner(dataSource);
    }

    @Override
    public String get() throws Exception {
        return runner.query(getSQL(), new BeanListHandler<>(cls)).stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

    abstract String getSQL();

}
