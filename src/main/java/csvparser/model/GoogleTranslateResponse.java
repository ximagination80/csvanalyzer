package csvparser.model;

public class GoogleTranslateResponse extends BaseModel {

    private String text;

    public GoogleTranslateResponse(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
