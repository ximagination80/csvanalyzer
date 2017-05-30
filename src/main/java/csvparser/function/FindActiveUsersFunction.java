package csvparser.function;

import csvparser.model.ActiveUser;

import javax.sql.DataSource;

public class FindActiveUsersFunction extends SQLBeanFunction<ActiveUser> {

    public FindActiveUsersFunction(DataSource dataSource) {
        super(dataSource, ActiveUser.class);
    }

    @Override
    String getSQL() {
        return " SELECT UserId, ProfileName, count(*) as count " +
                " FROM Reviews " +
                " GROUP BY UserId, ProfileName " +
                " ORDER BY count DESC " +
                " LIMIT 1000";
    }
}
