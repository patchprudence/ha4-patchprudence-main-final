package htw.berlin.wi.prog2.parsing;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountingInputParser implements ExtendableInputParser {

    @Override
    public Map<Long, Integer> idsAndCountFromInput(String inputLine, Map<String, Long> keywordsToIds) {

        Map<Long, Integer> result = new HashMap<>();

        if (inputLine != null && !inputLine.isEmpty() && keywordsToIds != null) {

            for (Map.Entry<String, Long> entry : keywordsToIds.entrySet()) {

                Matcher matcher = Pattern.compile(entry.getKey()).matcher(inputLine);

                int count = 0;

                while (matcher.find()) {
                    count++;
                }

                if (count > 0) {

                    result.put(entry.getValue(), count);

                }

            }

        }

        return result;

    }
}
