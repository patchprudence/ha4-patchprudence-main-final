package htw.berlin.wi.prog2.parsing;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmartInputParser implements ExtendableInputParser {

    // If needed, this list can be extended.
    private static final Map<String, Integer> REFERENCE = new LinkedHashMap<>();

    public SmartInputParser() {

        REFERENCE.put("doppelt", 2);
        REFERENCE.put("dreifach", 3);
        REFERENCE.put("vierfach", 4);
        REFERENCE.put("fünfach", 5);
        REFERENCE.put("zwei mal", 2);
        REFERENCE.put("drei mal", 3);
        REFERENCE.put("zweimal", 2);
        REFERENCE.put("dreimal", 3);

    }

    @Override
    public Map<Long, Integer> idsAndCountFromInput(String inputLine, Map<String, Long> keywordsToIds) {

        Map<Long, Integer> result = new HashMap<>();

        if (inputLine != null && !inputLine.isEmpty() && keywordsToIds != null) {

            List<SmartInputParserHelper> reference = getReference(keywordsToIds);

            for (SmartInputParserHelper siph : reference) {

                Matcher matcher = Pattern.compile(siph.getIngredientToSearchFor()).matcher(inputLine);

                int count = 0;

                while (matcher.find()) {
                    count++;
                }

                if (count > 0) {

                    count = count * siph.getAmount();

                    if (result.containsKey(keywordsToIds.get(siph.getIngredientName()))) {

                        count = count + result.get(keywordsToIds.get(siph.getIngredientName()));
                        result.replace(keywordsToIds.get(siph.getIngredientName()), count);

                    } else {

                        result.put(keywordsToIds.get(siph.getIngredientName()), count);

                    }

                    inputLine = inputLine.replace(siph.getIngredientToSearchFor(), "");

                }

            }

        }

        return result;

    }

    private List<SmartInputParserHelper> getReference(Map<String, Long> keywordsToIds) {

        List<SmartInputParserHelper> result = new ArrayList<>();

        for (Map.Entry<String, Integer> referenceEntry : REFERENCE.entrySet()) {

            for (String ingredientName : keywordsToIds.keySet()) {

                SmartInputParserHelper smartInputParserHelper = new SmartInputParserHelper(referenceEntry.getKey(), ingredientName, referenceEntry.getValue());
                result.add(smartInputParserHelper);

            }

        }

        for (String ingredientName : keywordsToIds.keySet()) {

            SmartInputParserHelper smartInputParserHelper = new SmartInputParserHelper("", ingredientName, 1);
            result.add(smartInputParserHelper);

        }

        return result;

    }

    private static class SmartInputParserHelper {

        private final String quantity;
        private final String ingredientName;
        private final Integer amount;

        public SmartInputParserHelper(String quantity, String ingredientName, Integer amount) {
            this.quantity = quantity;
            this.ingredientName = ingredientName;
            this.amount = amount;
        }

        public String getIngredientName() {
            return ingredientName;
        }

        public Integer getAmount() {
            return amount;
        }

        public String getIngredientToSearchFor() {
            return this.quantity + " " + this.ingredientName;
        }
    }

}
