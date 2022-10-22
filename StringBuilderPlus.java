public class StringBuilderPlus {
    private StringBuilder stringBuilder;
    private String indentString = " ";
    private int indentSize = 2;
    private int indentLevel = 0;

    public StringBuilderPlus() {
        stringBuilder = new StringBuilder();
    }

    public StringBuilderPlus(String indentString) {
        stringBuilder = new StringBuilder();

        this.indentString = indentString;
    }

    public String getIndent() {
        return indentString.repeat(indentSize * indentLevel);
    }

    public StringBuilderPlus(String indentString, int indentSize) {
        stringBuilder = new StringBuilder();

        this.indentString = indentString;
        this.indentSize = indentSize;
    }

    public void append(String str) {
        stringBuilder.append(getIndent());
        stringBuilder.append(str);
    }

    public void append(String format, Object... args) {
        append(String.format(format, args));
    }

    public void appendln() {
        appendln("");
    }

    public void appendln(String str) {
        append(str);
        stringBuilder.append("\n");
    }

    public void appendln(String format, Object... args) {
        appendln(String.format(format, args));
    }

    public String toString() {
        return stringBuilder.toString();
    }

    public void push() {
        indentLevel += 1;
    }

    public void pop() {
        indentLevel = Math.max(0, --indentLevel);
    }
}