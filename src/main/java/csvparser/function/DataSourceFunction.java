package csvparser.function;

import javax.sql.DataSource;

abstract class DataSourceFunction<T> implements Function<T> {

    final DataSource dataSource;

    DataSourceFunction(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
