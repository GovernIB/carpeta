package es.caib.carpeta.commons.i18n;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Implementació d'un {@link ResourceBundle} que permet delegar, i per tant cercar etiquetes dins una llista
 * de bundles.
 *
 * @author anadal
 */
public class MultipleResourceBundle extends ResourceBundle {

    private final List<ResourceBundle> delegates;

    public MultipleResourceBundle(List<ResourceBundle> resourceBundles) {
        this.delegates = resourceBundles == null ? new ArrayList<>() : resourceBundles;
    }

    @Override
    protected Object handleGetObject(String key) {
        Optional<Object> firstPropertyValue = this.delegates.stream()
                .filter(delegate -> delegate != null && delegate.containsKey(key))
                .map(delegate -> delegate.getObject(key))
                .findFirst();

        return firstPropertyValue.orElse(null);
    }

    @Override
    public Enumeration<String> getKeys() {
        List<String> keys = this.delegates.stream()
                .filter(Objects::nonNull)
                .flatMap(delegate -> Collections.list(delegate.getKeys()).stream())
                .collect(Collectors.toList());

        return Collections.enumeration(keys);
    }
}
