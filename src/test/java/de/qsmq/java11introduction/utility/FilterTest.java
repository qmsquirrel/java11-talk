package de.qsmq.java11introduction.utility;

import org.junit.jupiter.api.Test;


import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


class FilterTest {

    @Test
    void filterSet() {
        Set<String> testSet = Set.of("A", "AB", "ABC", "ABCD", "ABCDE", "ABCDEF");
        Set<String> copyOfTestSet = Set.copyOf(testSet);

        Set<String> result = Filter.filterSet(testSet, s -> s.length() > 3);
        assertThat(result.size()).isEqualTo(3);
        assertThat(copyOfTestSet).hasSameElementsAs(testSet);
    }

    @Test
    void filterList() {
        List<String> testList = List.of("A", "AB", "ABC", "ABCD", "ABCDE", "ABCDEF");
        List<String> copyOfTestList = List.copyOf(testList);

        List<String> result = Filter.filterList(testList, s -> s.length() > 3);
        assertThat(result.size()).isEqualTo(3);
        assertThat(copyOfTestList).hasSameElementsAs(testList);
    }

    @Test
    void filterMapByKey() {
        Map<String, Integer> testMap = Map.of("A", 1, "AB", 0, "ABC", 1, "ABCD", 0,
                "ABCDE", 1, "ABCDEF", 0);
        Map<String, Integer> copyOfTestMap = Map.copyOf(testMap);

        Map<String, Integer> result = Filter.filterMapByKey(testMap, s -> s.length() > 3);
        assertThat(result.size()).isEqualTo(3);
        assertThat(copyOfTestMap).containsAllEntriesOf(testMap);
    }

    @Test
    void filterMapByValue() {
        Map<String, Integer> testMap = Map.of("A", 1, "AB", 0, "ABC", 1, "ABCD", 0,
                "ABCDE", 1, "ABCDEF", 0);
        Map<String, Integer> copyOfTestMap = Map.copyOf(testMap);

        Map<String, Integer> result = Filter.filterMapByValue(testMap, s -> s == 0);
        assertThat(result.size()).isEqualTo(3);
        assertThat(copyOfTestMap).containsAllEntriesOf(testMap);
    }
}