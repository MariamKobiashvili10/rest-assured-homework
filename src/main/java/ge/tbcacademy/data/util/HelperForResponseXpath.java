package ge.tbcacademy.data.util;

import javax.xml.namespace.NamespaceContext;
import java.util.*;

public class HelperForResponseXpath implements NamespaceContext {
    private final Map<String, String> prefixToUri;
    private final Map<String, String> uriToPrefix;

    public HelperForResponseXpath() {
        Map<String, String> map = new HashMap<>();
        map.put("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
        map.put("ns2", "http://interfaces.soap.springboot.example.com");
        prefixToUri = Collections.unmodifiableMap(map);
        uriToPrefix = createReverseMap(map);
    }

    private Map<String, String> createReverseMap(Map<String, String> original) {
        Map<String, String> reversed = new HashMap<>();
        for (Map.Entry<String, String> entry : original.entrySet()) {
            reversed.put(entry.getValue(), entry.getKey());
        }
        return Collections.unmodifiableMap(reversed);
    }

    @Override
    public String getNamespaceURI(String prefix) {
        return prefixToUri.get(prefix);
    }

    @Override
    public String getPrefix(String namespaceURI) {
        return uriToPrefix.get(namespaceURI);
    }

    @Override
    public Iterator<String> getPrefixes(String namespaceURI) {
        String prefix = uriToPrefix.get(namespaceURI);
        return (prefix != null) ? Collections.singleton(prefix).iterator() : Collections.emptyIterator();
    }
}