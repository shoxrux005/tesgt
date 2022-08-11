package pdp.uz.javaee_two.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Language {
    UZ("Uzbek"),
    RU("Russian"),
    EN("English");
    private final String key;

    public static Language findByName(String language) {
        return Arrays.stream(Language.values()).filter(lang->lang.key.equals(language)).findFirst().orElse(Language.EN);
    }
    public String getKey() {
        return key;
    }
}
