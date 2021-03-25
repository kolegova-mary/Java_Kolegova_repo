package com.griddynamics.compareArrayLinkedTask;

import java.util.*;
import java.util.stream.Collectors;


public class CompareArrayLinked {
    final static Integer CAPACITY = 100500;
    static List<Integer> list;
    static LinkedList<Integer> linkedList;

    public static void main(String[] args) {
        list = new ArrayList<>(CAPACITY);
        linkedList = new LinkedList<>();
        long timeListGenerate = generateList();
        long timeLinkedListGenerate = generateLinkedList();
        long timeInsertIntoList = insertIntoList();
        long timeInsertIntoLinkedList = insertIntoLinkedList();
        long timeToCheckList = checkIfAnyDuplicatesInList();
        long timeToCheckLinked = checkIfAnyDuplicatesInLinked();
        long timeToFindMaxAndMinInList = findMaxAndMinInList();
        long timeToFindMaxAndMinInLinked = findMaxAndMinInLinked();
        long timeSortInReverseOrderList = sortInReverseOrderList();
        long timeSortInReverseOrderLinked = sortInReverseOrderLinked();
        compareListWithLinked(timeListGenerate,
                timeLinkedListGenerate,
                timeInsertIntoList,
                timeInsertIntoLinkedList,
                timeToCheckList,
                timeToCheckLinked,
                timeToFindMaxAndMinInList,
                timeToFindMaxAndMinInLinked,
                timeSortInReverseOrderList,
                timeSortInReverseOrderLinked);
    }

    public static void compareListWithLinked(long timeListGenerate,
                                             long timeLinkedListGenerate,
                                             long timeInsertIntoList,
                                             long timeInsertIntoLinkedList,
                                             long timeToCheckList,
                                             long timeToCheckLinked,
                                             long timeToFindMaxAndMinInList,
                                             long timeToFindMaxAndMinInLinked,
                                             long timeSortInReverseOrderList,
                                             long timeSortInReverseOrderLinked) {
        if (timeListGenerate > timeLinkedListGenerate) {
            System.out.println("LinkedList generates faster!");
        } else {
            if (timeListGenerate == timeLinkedListGenerate) {
                System.out.println("Generating takes the same time!");
            } else {
                System.out.println("List generates faster!");
            }
        }

        if (timeInsertIntoList > timeInsertIntoLinkedList) {
            System.out.println("LinkedList inserts faster!");
        } else {
            if (timeListGenerate == timeLinkedListGenerate) {
                System.out.println("Inserting takes the same time!");
            } else {
                System.out.println("List inserts faster!");
            }
        }

        if (timeToCheckList > timeToCheckLinked) {
            System.out.println("LinkedList faster remove duplicates!");
        } else {
            if (timeListGenerate == timeLinkedListGenerate) {
                System.out.println("Removing duplicates takes the same time!");
            } else {
                System.out.println("List faster remove duplicates!");
            }
        }

        if (timeToFindMaxAndMinInList > timeToFindMaxAndMinInLinked) {
            System.out.println("LinkedList faster finds min and max!");
        } else {
            if (timeListGenerate == timeLinkedListGenerate) {
                System.out.println("Finding min and max takes the same time!");
            } else {
                System.out.println("List faster finds min and max!");
            }
        }

        if (timeSortInReverseOrderList > timeSortInReverseOrderLinked) {
            System.out.println("LinkedList faster sorts in reverse order!");
        } else {
            if (timeListGenerate == timeLinkedListGenerate) {
                System.out.println("Sorting in reverse order takes the same time!");
            } else {
                System.out.println("List faster faster sorts in reverse order!");
            }
        }
    }


    public static long sortInReverseOrderList() {
        long timeBefore = System.currentTimeMillis();
        list.sort(Collections.reverseOrder());
        long timeAfter = System.currentTimeMillis();
        return timeAfter - timeBefore;
    }

    public static long sortInReverseOrderLinked() {
        long timeBefore = System.currentTimeMillis();
        linkedList.sort(Collections.reverseOrder());
        long timeAfter = System.currentTimeMillis();
        return timeAfter - timeBefore;
    }

    public static long findMaxAndMinInList() {
        long timeBefore = System.currentTimeMillis();
        int max = Collections.max(list);
        int min = Collections.min(list);
        long timeAfter = System.currentTimeMillis();
        return timeAfter - timeBefore;
    }

    public static long findMaxAndMinInLinked() {
        long timeBefore = System.currentTimeMillis();
        int max = Collections.max(linkedList);
        int min = Collections.min(linkedList);
        long timeAfter = System.currentTimeMillis();
        return timeAfter - timeBefore;
    }

    public static long checkIfAnyDuplicatesInList() {
        long timeBefore = System.currentTimeMillis();
        list = list.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        long timeAfter = System.currentTimeMillis();
        return timeAfter - timeBefore;
    }

    public static long checkIfAnyDuplicatesInLinked() {
        long timeBefore = System.currentTimeMillis();
        linkedList = linkedList.stream().distinct().collect(Collectors.toCollection(LinkedList::new));
        long timeAfter = System.currentTimeMillis();
        return timeAfter - timeBefore;
    }

    public static long insertIntoList() {
        long timeBefore = System.currentTimeMillis();
        Random rand = new Random();
        for (int i = CAPACITY / 2; i < CAPACITY / 2 + 10000; i++) {
            Integer r = rand.nextInt();
            list.add(i, r);
        }
        long timeAfter = System.currentTimeMillis();
        return timeAfter - timeBefore;
    }


    public static long insertIntoLinkedList() {
        long timeBefore = System.currentTimeMillis();
        Random rand = new Random();
        for (int i = CAPACITY / 2; i < CAPACITY / 2 + 10000; i++) {
            Integer r = rand.nextInt();
            linkedList.add(i, r);
        }
        long timeAfter = System.currentTimeMillis();
        return timeAfter - timeBefore;
    }

    public static long generateList() {
        long timeBefore = System.currentTimeMillis();
        Random rand = new Random();
        for (int i = 0; i < CAPACITY; i++) {
            Integer r = rand.nextInt();
            list.add(r);
        }
        long timeAfter = System.currentTimeMillis();
        return timeAfter - timeBefore;
    }

    public static long generateLinkedList() {
        long timeBefore = System.currentTimeMillis();
        Random rand = new Random();
        for (int i = 0; i < CAPACITY; i++) {
            Integer r = rand.nextInt();
            linkedList.add(r);
        }
        long timeAfter = System.currentTimeMillis();
        return timeAfter - timeBefore;
    }

}
