package csvparser.function;

public class HelpFunction implements Function<String> {

    public static final String HELP_TEXT = "List of available functions:\n" +
            "Use [1] for finding 1000 most active users (profile names)\n" +
            "Use [2] for finding 1000 most commented food items (item ids)\n" +
            "Use [3] for finding 1000 most used words in the reviews\n" +
            "Use [4] for translating all the reviews using Google Translate API\n" +
            "or [help/stop/exit/migrate/clear/index]\n";

    @Override
    public String get() {
        return HELP_TEXT;
    }
}