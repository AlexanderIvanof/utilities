package com.oivanov.home;

import java.util.Map;

/**
 * Basic class to create any builders for {@link java.util.Map} types.
 *
 * @author oleivano
 * @since 1.0.0
 */
abstract class AbstractMapBuilder<M extends Map<K,V>, K, V> {

    protected final M delegateMap;

    AbstractMapBuilder(M delegateMap) {
        this.delegateMap = delegateMap;
    }

    /**
     * Method add pair {@code key} and {@code value} to {@link AbstractMapBuilder#delegateMap}
     *
     * @param key key to add
     * @param value value to add
     * @return according to pattern builder {@code this} instance
     * @see Map#put(Object, Object)
     */
    public AbstractMapBuilder<M, K, V> put(K key, V value) {
        delegateMap.put(key, value);
        return this;
    }

    /**
     * Builds map with all data.
     *
     * @return map with current state
     */
    public M build() {
        return delegateMap;
    }
}
