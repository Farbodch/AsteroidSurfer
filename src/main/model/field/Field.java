package src.main.model.field;



public class Field {
    private int fieldHeight;
    private int fieldWidth;

    public Field(int height, int width) {
        this.fieldHeight = height;
        this.fieldWidth = width;

    }

    public int getFieldWidth() {
        return this.fieldWidth;
    }

    public int getFieldHeight() {
        return this.fieldHeight;
    }

}
