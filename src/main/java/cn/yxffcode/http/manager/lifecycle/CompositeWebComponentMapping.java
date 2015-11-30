package cn.yxffcode.http.manager.lifecycle;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class CompositeWebComponentMapping implements WebComponentMapping {
    private final Iterable<WebComponentMapping> mappings;

    public CompositeWebComponentMapping(Iterable<WebComponentMapping> mappings) {
        this.mappings = mappings;
    }

    public Class<?> map(String resource) {
        for (WebComponentMapping mapping : mappings) {
            Class<?> type = mapping.map(resource);
            if (type != null) {
                return type;
            }
        }
        return null;
    }
}
