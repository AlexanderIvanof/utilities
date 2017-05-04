package com.oivanov.home

import spock.lang.Specification

/**
 * Test cases for {@link MapBuilder}.
 *
 * @author oleivano
 * @since 1.0.0
 */
class MapBuilderTest extends Specification {

    def "Create map with one value"() {
        setup:
        def mapBuilder = MapBuilder.<String, Long> withMapType({new HashMap<>()})
        when:
        def builtMap = mapBuilder.put("one", 1L).build()
        then:
        builtMap in HashMap
        builtMap.size() == 1
    }

    def "Create unmodifiable map"() {
        setup:
        def mapBuilder = MapBuilder.<String, Long> withMapType({new HashMap<>()})
        when:
        def builtMap = mapBuilder.put("one", 1L).setModifiable(false).build()
        builtMap.remove("one")
        then:
        thrown UnsupportedOperationException
        when:
        builtMap.put("two", 2L)
        then:
        thrown UnsupportedOperationException
    }
}
