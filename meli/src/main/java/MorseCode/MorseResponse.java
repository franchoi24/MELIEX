package MorseCode;

import java.io.Serializable;

public class MorseResponse implements Serializable {

    private static final long serialVersionUID = 5529613997641578535L;

    private String text;

    public MorseResponse(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    public void setText(String user) {
        this.text = text;
    }

}