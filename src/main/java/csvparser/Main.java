package csvparser;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import csvparser.function.*;
import org.apache.commons.lang3.StringUtils;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    @Parameter(names = {"-url"}, required = true,
            description = "JDBC url (jdbc:postgresql://host:port/database)")
    public String jdbcUrl;

    @Parameter(names = {"-userName"}, required = true,
            description = "User name for obtaining jdbc connection")
    public String userName;

    @Parameter(names = {"-password"}, required = true,
            description = "User password for obtaining jdbc connection")
    public String password;

    @Parameter(names = {"-csvPath"}, required = true,
            description = "Path to csv file", converter = FileConverter.class)
    public File csv;

    public static void main(String[] args) throws IOException, SQLException {
        Main main = new Main();
        JCommander.newBuilder().addObject(main).build().parse(args);
        main.run();
    }

    private void run() throws SQLException {
        DataSource dataSource = createDataSourceOrExit();
        testDatasource(dataSource);
        communicateWithUser(dataSource);
    }

    private void communicateWithUser(DataSource dataSource) {
        Map<Object, Function<String>> functions = createFunctions(dataSource);

        print(HelpFunction.HELP_TEXT);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                print("ready>");
                String key = StringUtils.trim(br.readLine());

                if ("stop".equals(key) || "exit".equals(key)) {
                    break;
                }

                Function<String> result = functions.get(key);

                if (result == null) {
                    print(String.format("Unable to find function for key %s in list of available functions. " +
                            "Please use help for getting list of all functions", key));
                } else {
                    long start = System.currentTimeMillis();
                    String value = result.get();
                    long end = System.currentTimeMillis();

                    print("Execution time millis: " + (end - start));
                    print(String.format("Function %s has been executed with result:", key));
                    print(value);
                }
            } catch (Exception e) {
                print("Oh sorry, error occurs>");
                e.printStackTrace();
            }
        }

        print("Done.");
    }

    private void testDatasource(DataSource dataSource) throws SQLException {
        try (Connection ignored = dataSource.getConnection()) {
            ignored.close();
        }
    }

    private DataSource createDataSourceOrExit() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setUser(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    private Map<Object, Function<String>> createFunctions(DataSource dataSource) {
        Map<Object, Function<String>> functions = new LinkedHashMap<>();
        functions.put("1", new FindActiveUsersFunction(dataSource));
        functions.put("2", new FindMostCommentedFoodItemsFunction(dataSource));
        functions.put("3", new FindMostUsedWordsInReviewsFunction(dataSource));
        functions.put("4", new GoogleTranslateFunction(dataSource));
        functions.put("migrate", new MigrateFunction(dataSource));
        functions.put("clear", new ClearDatabaseFunction(dataSource));
        functions.put("index", new IndexCSVDataFunction(dataSource, csv));
        functions.put("help", new HelpFunction());
        return functions;
    }

    private static void print(String string) {
        System.out.println(string);
    }

}
