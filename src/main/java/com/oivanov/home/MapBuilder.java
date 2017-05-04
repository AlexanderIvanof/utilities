package com.oivanov.home;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * {@link AbstractMapBuilder} implementation for any map types
 *
 * @author oleivano
 * @since 1.0.0
 */
public class MapBuilder<K, V> extends AbstractMapBuilder<Map<K, V>, K, V> {

    private boolean isModifiable = true;

    public MapBuilder(Map<K, V> map) {
        super(map);
    }

    /**
     * Return instance of this class to build any map type.
     * <p>
     * Note: you must explicitly add generic types before the method call.
     * E.g.
     * <p>
     * {@code Map<String, Integer> someMap = MapBuilder.<String, Integer>withMapType(HashMap::new)}
     * otherwise you will get
     * <p>
     * {@code Map<Object, Object> someMap = MapBuilder.withMapType(HashMap::new)}
     *
     * @param mapSupplier type of desired map
     * @param <K>    type of key in {@link AbstractMapBuilder#delegateMap}
     * @param <V>    type of value in {@link AbstractMapBuilder#delegateMap}
     * @return builder for map
     */
    public static <K, V> MapBuilder<K, V> withMapType(Supplier<Map<K, V>> mapSupplier) {
        return new MapBuilder<>(mapSupplier.get());
    }

    /**
     * Same as MapBuilder#withMapType(HashMap::new)
     *
     * @param <K>    type of key in {@link AbstractMapBuilder#delegateMap}
     * @param <V>    type of value in {@link AbstractMapBuilder#delegateMap}
     * @return builder for map
     */
    public static <K, V> MapBuilder<K, V> create() {
        return withMapType(HashMap::new);
    }

    /**
     * Sets if result map should be modifiable or not default value is {@code true}.
     *
     * @param isModifiable value
     * @return according to pattern builder {@code this} instance
     */
    public MapBuilder<K, V> setModifiable(boolean isModifiable) {
        this.isModifiable = isModifiable;
        return this;
    }

    @Override
    public MapBuilder<K, V> put(K key, V value) {
        super.put(key, value);
        return this;
    }

    @Override
    public Map<K, V> build() {
        final Map<K, V> resultMap = super.build();
        return isModifiable ? resultMap : Collections.unmodifiableMap(resultMap);
    }
}
