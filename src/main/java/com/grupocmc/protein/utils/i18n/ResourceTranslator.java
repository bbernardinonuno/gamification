package com.grupocmc.protein.utils.i18n;

import java.util.List;

public interface ResourceTranslator {

    String resolve(String identifier);

    List<String> resolve(List<String> identifier);

    String resolve(String identifier, String... replacers);


}
