package calculator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputString {

    private String source;
    private List<Integer> numberList = new ArrayList<>();
    private List<String> basicDelimiter = List.of(",", ":");
    private String customDelimiter;
    private int result;

    public InputString(String stringList) {
        this.source = stringList;
    }

    public String getCustomDelimiter(String input) {
        if (!input.startsWith("//")) {
            return null;
        }

        int endIndex = input.indexOf("\n");
        if (input.substring(2, endIndex).length() != 1) {
            throw new IllegalArgumentException("커스텀 구분자는 문자열이 아닌 하나의 문자여야 합니다.");
        }

        customDelimiter = input.substring(2, endIndex);
        return customDelimiter;
    }

    private void parseNumbers() {
        String regex = totalSplitRegex(getCustomDelimiter(source));
        for (String s : source.split(regex)) {
            numberList.add(Integer.parseInt(s));
        }
    }

    private String totalSplitRegex(String customDelimiter) {
        List<String> list = new ArrayList<>();
        if (customDelimiter != null) {
            list.add(customDelimiter);
        }

        return list.stream().map(Pattern::quote).collect(Collectors.joining("|"));
    }

    public int getResult() {
        for (Integer number : numberList) {
            if (number <= 0) {
                throw new IllegalArgumentException("숫자는 양의 정수여야 합니다.");
            }
            result += number;
        }

        return result;
    }
}
