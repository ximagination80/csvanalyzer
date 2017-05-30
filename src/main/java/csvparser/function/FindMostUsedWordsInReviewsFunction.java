package csvparser.function;

import csvparser.model.MostUsedWordsInReviews;

import javax.sql.DataSource;

public class FindMostUsedWordsInReviewsFunction extends SQLBeanFunction<MostUsedWordsInReviews> {

    public FindMostUsedWordsInReviewsFunction(DataSource dataSource) {
        super(dataSource, MostUsedWordsInReviews.class);
    }

    @Override
    String getSQL() {
        return " SELECT word as name, nentry as count FROM ts_stat('SELECT to_tsvector(text) FROM Reviews') " +
                " ORDER BY nentry DESC " +
                " LIMIT 1000;";

   /*
   //        OR OTHER OPTION LESS ERR
        return " SELECT DISTINCT name, count(*) " +
                " FROM ( " +
                "   SELECT regexp_split_to_table(text, '\\s') as name " +
                "   FROM Reviews " +
                " ) t " +
                " GROUP BY name " +
                " ORDER BY count DESC " +
                " LIMIT 1000";
    */
    }
}