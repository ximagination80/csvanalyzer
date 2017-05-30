package csvparser.function;

import csvparser.model.MostCommentedFoodItems;

import javax.sql.DataSource;

public class FindMostCommentedFoodItemsFunction extends SQLBeanFunction<MostCommentedFoodItems> {

    public FindMostCommentedFoodItemsFunction(DataSource dataSource) {
        super(dataSource, MostCommentedFoodItems.class);
    }

    @Override
    String getSQL() {
        return " SELECT ProductId, count(*) as count " +
                " FROM Reviews " +
                " GROUP BY ProductId " +
                " ORDER BY count DESC " +
                " LIMIT 1000";
    }
}
