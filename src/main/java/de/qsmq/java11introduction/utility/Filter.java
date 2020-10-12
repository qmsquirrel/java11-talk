package de.qsmq.java11introduction.utility;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Filter {

    public static <E> Set<E> filterSet(Set<E> originalSet, Predicate<E> predicate) {
        return originalSet
                .stream()
                .filter(predicate)
                .collect(Collectors.toSet());
    }

    public static <E> List<E> filterList(List<E> originalList, Predicate<E> predicate) {
        return originalList
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static <K,V> Map<K,V> filterMapByKey(Map<K,V> originalMap, Predicate<K> predicate) {
        return originalMap.entrySet()
                .stream()
                .filter(entry -> predicate.test(entry.getKey()))
                .collect(Collectors
                        .toMap(mapEntry -> mapEntry.getKey(),
                                mapEntry -> mapEntry.getValue()));
    }

    public static <K,V> Map<K,V> filterMapByValue(Map<K,V> originalMap, Predicate<V> predicate) {
        return originalMap.entrySet()
                .stream()
                .filter(entry -> predicate.test(entry.getValue()))
                .collect(Collectors
                        .toMap(k -> k.getKey(),
                                k -> k.getValue()));
    }
}
