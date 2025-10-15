package calculator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputString {

    private List<Integer> numberList = new ArrayList<>();
    private List<String> basicDelimiter = List.of(",", ":");
    private String customDelimiter;
    private int result;

    private String getCustomDelimiter(String source) {
        if (!source.startsWith("//")) {
            return null;
        }

        int endIndex = getEndIndex(source);
        System.out.println("endIndex = " + endIndex);

        if (source.substring(2, endIndex - 2).length() != 1) {
            throw new IllegalArgumentException("커스텀 구분자는 문자열이 아닌 하나의 문자여야 합니다.");
        }

        customDelimiter = source.substring(2, endIndex - 2);
        System.out.println(customDelimiter);  // 커스텀 구분자만 빼냄
        return customDelimiter;
    }

    private int getEndIndex(String source) {
        int endIndex = 0;

        String s = "\\n";
        if (source.contains(s)) {
            endIndex = source.indexOf(s) + 2;
        }

        return endIndex;
    }

    // //?\n 이후의 문자열에 대해서만 작업해야 할 듯
    public void parseNumbers(String source) {
        String regex = totalSplitRegex(getCustomDelimiter(source));  // 기본 구분자 + 커스텀 구분자
        String substring = source.substring(getEndIndex(source));

        for (String s : substring.split(regex)) {
            String trim = s.trim();
            if (trim.isEmpty()) continue;
            try {
                int number = Integer.parseInt(s);
                numberList.add(number);
                if (number < 0) {
                    throw new IllegalArgumentException("양의 정수만 더할 수 있습니다.");
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("숫자로 변환할 수 없습니다.");
            }
        }
    }

    private String totalSplitRegex(String customDelimiter) {
        List<String> list = new ArrayList<>(basicDelimiter);
        if (customDelimiter != null) {
            list.add(customDelimiter);
        }

        return list.stream().map(Pattern::quote).collect(Collectors.joining("|"));
    }

    public int getResult() {

        if (numberList.isEmpty()) {
            result = 0;
        }

        for (Integer number : numberList) {
            result += number;
        }

        return result;
    }
}
