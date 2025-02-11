package parser;

public class LexerHelper {

    public static int lexemeToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return -1;
    }

    public static char lexemeToChar(String str) {
        try {
            char converted = str.charAt(1);
            char next = str.charAt(2);

            // Regular character
            if (converted != '\\' || next != '\'')
                return converted;

            // Special characters
            if (str.equals("'\\t'"))
                return '\t';
            if (str.equals("'\\n'"))
                return '\n';

            // Unicode character
            String unicode = str.substring(2, str.length() - 1);
            return (char) Integer.parseInt(unicode, 16);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        return 'i';
    }

    public static double lexemeToReal(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return -1.0;
    }

}
