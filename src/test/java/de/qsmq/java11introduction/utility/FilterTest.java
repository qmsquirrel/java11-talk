package de.qsmq.java11introduction.utility;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


class FilterTest {

    @Test
    void filterSet() {
        Set<String> testSet = new HashSet<>();
        Set<String> copyOfTestSet = Collections.unmodifiableSet(testSet);
        testSet.add("A");
        testSet.add("AB");
        testSet.add("ABC");
        testSet.add("ABCD");
        testSet.add("ABCDE");
        testSet.add("ABCDEF");

        Set<String> result = Filter.filterSet(testSet, s -> s.length() > 3);
        assertThat(result.size()).isEqualTo(3);
        assertThat(copyOfTestSet).hasSameElementsAs(testSet);
    }

    @Test
    void filterList() {
        List<String> testList = new ArrayList<>();
        List<String> copyOfTestList = Collections.unmodifiableList(testList);
        testList.add("A");
        testList.add("AB");
        testList.add("ABC");
        testList.add("ABCD");
        testList.add("ABCDE");
        testList.add("ABCDEF");

        List<String> result = Filter.filterList(testList, s -> s.length() > 3);
        assertThat(result.size()).isEqualTo(3);
        assertThat(copyOfTestList).hasSameElementsAs(testList);
    }

    @Test
    void filterMapByKey() {
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("A", 1);
        testMap.put("AB", 0);
        testMap.put("ABC", 1);
        testMap.put("ABCD", 0);
        testMap.put("ABCDE", 1);
        testMap.put("ABCDEF", 0);
        Map<String, Integer> copyOfTestMap = Collections.unmodifiableMap(testMap);

        Map<String, Integer> result = Filter.filterMapByKey(testMap, s -> s.length() > 3);
        assertThat(result.size()).isEqualTo(3);
        assertThat(copyOfTestMap).containsAllEntriesOf(testMap);
    }

    @Test
    void filterMapByValue() {
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("A", 1);
        testMap.put("AB", 0);
        testMap.put("ABC", 1);
        testMap.put("ABCD", 0);
        testMap.put("ABCDE", 1);
        testMap.put("ABCDEF", 0);
        Map<String, Integer> copyOfTestMap = Collections.unmodifiableMap(testMap);

        Map<String, Integer> result = Filter.filterMapByValue(testMap, s -> s == 0);
        assertThat(result.size()).isEqualTo(3);
        assertThat(copyOfTestMap).containsAllEntriesOf(testMap);
    }
}