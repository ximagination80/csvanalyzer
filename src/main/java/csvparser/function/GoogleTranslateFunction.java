package csvparser.function;

import csvparser.Concurrent;
import csvparser.model.GoogleTranslateRequest;
import csvparser.model.GoogleTranslateResponse;
import csvparser.model.Reviews;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.util.concurrent.ThreadPoolExecutor;

public class GoogleTranslateFunction extends DataSourceFunction<String> {

    private interface GoogleAPIService {
        GoogleTranslateResponse translate(GoogleTranslateRequest request);
    }

    private GoogleAPIService service = request ->
            new GoogleTranslateResponse("Salut Jean, comment vas tu?");

    public GoogleTranslateFunction(DataSource dataSource) {
        super(dataSource);
        ThreadPoolExecutor executor = Concurrent.THREAD_POOL_EXECUTOR;
        executor.prestartAllCoreThreads();
    }

    @Override
    public String get() throws Exception {
        new QueryRunner(dataSource).query("SELECT * FROM Reviews LIMIT 1000",
                new BeanListHandler<>(Reviews.class)).
                forEach(this::doTranslate);
        return "OK";
    }

    private void doTranslate(Reviews reviews) {
        ThreadPoolExecutor executor = Concurrent.THREAD_POOL_EXECUTOR;

        executor.execute(() -> {
            GoogleTranslateResponse result = service.translate(
                    new GoogleTranslateRequest("en", "fr", reviews.getText()));
            System.out.println(reviews.getId() + ":" + result);
        });

        executor.setRejectedExecutionHandler((runnable, ex) -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ex.execute(runnable);
        });
    }
}
