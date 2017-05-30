package csvparser.model;

public class Reviews extends BaseModel {
    private int Id;
    private String ProductId;
    private String UserId;
    private String ProfileName;
    private int HelpfulnessNumerator;
    private int HelpfulnessDenominator;
    private int Score;
    private int Time;
    private String Summary;
    private String Text;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getProfileName() {
        return ProfileName;
    }

    public void setProfileName(String profileName) {
        ProfileName = profileName;
    }

    public int getHelpfulnessNumerator() {
        return HelpfulnessNumerator;
    }

    public void setHelpfulnessNumerator(int helpfulnessNumerator) {
        HelpfulnessNumerator = helpfulnessNumerator;
    }

    public int getHelpfulnessDenominator() {
        return HelpfulnessDenominator;
    }

    public void setHelpfulnessDenominator(int helpfulnessDenominator) {
        HelpfulnessDenominator = helpfulnessDenominator;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}