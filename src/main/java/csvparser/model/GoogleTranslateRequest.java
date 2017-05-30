package csvparser.model;

public class GoogleTranslateRequest extends BaseModel {

    private String inputLang;
    private String outputLang;
    private String text;

    public GoogleTranslateRequest(String inputLang, String outputLang, String text) {
        this.inputLang = inputLang;
        this.outputLang = outputLang;
        this.text = text;
    }

    public String getInputLang() {
        return inputLang;
    }

    public void setInputLang(String inputLang) {
        this.inputLang = inputLang;
    }

    public String getOutputLang() {
        return outputLang;
    }

    public void setOutputLang(String outputLang) {
        this.outputLang = outputLang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
