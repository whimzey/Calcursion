
class Parser {
    private String expression;
    private int position = 0;

    double parse(String exp) {
        this.expression = exp;
        position = 0;
        return expression(term(power((factor()))));
    }

    private double factor() {
        char symbol = getChar();
        if (Character.isDigit(symbol)) {
            return getValue(symbol);
        }
        if (symbol == '(') {
            Parser p = new Parser();
            position--;
            return p.parse(getExpression());
        }

        return 0;
    }

    private double term(double left) {
        char symbol = getChar();
        if (symbol != '*' && symbol != '/') {
            position--;
            return left;

        }
        double right = power(factor());
        if (symbol == '*') {
            return term(left * right);
        }
        if (right == 0) {
            System.err.println("Деление на ноль!");
            return 0;
        }
        return term(left / right);
    }

    private double power(double left) {
        char symbol = getChar();

        if (symbol != '^') {
            position--;
            return left;
        }
        double right = factor();
        return power(Math.pow(left, right));
    }

    private double expression(double left) {
        char symbol = getChar();
        if (symbol != '+' && symbol != '-') {
            position--;
            return left;

        }
        double right = term(power(factor()));
        if (symbol == '-') {
            return expression(left - right);
        }

        return expression(left + right);
    }

    private double getValue(char symbol) {
        String result = Character.toString(symbol);
        while (true) {
            symbol = getChar();

            if (symbol == '.') {
                result += Character.toString(symbol);
            } else if (Character.isDigit(symbol)) {

                result += Character.toString(symbol);
            } else {
                break;
            }
        }
        position--;
        return Double.parseDouble(result);

    }

    private char getChar() {
        if (position < expression.length()) {
            char symbol = expression.charAt(position);
            position++;
            return symbol;
        }
        return '\0';
    }

    private String getExpression() {
        String expression = "";
        int opened = 0;
        while (true) {
            char symbol = getChar();
            if (symbol == '(') {
                opened++;

            } else if (symbol == ')') {
                if (opened != 0)
                    opened--;
                else break;

            }
            expression += Character.toString(symbol);

        }
        return expression;
    }
}
