package com.donata.generics;

import java.util.Arrays;
import java.util.List;


public class Generics {

    public void test() {

        List<Invoice> invoices = List.of(new Invoice(), new Invoice());
        Page<Invoice> invoicePage = new Page<>(invoices);



        Integer max = findMax(Arrays.asList(1, 2, 3, 4, 5));

    }

    public <T extends Comparable<T>> T findMax(List<T> list) {
        T max = null;

        for (T t : list) {
            if (max == null) {
                max = t;
                continue;
            }

            if (t.compareTo(max) > 0) {
                max = t;
            }
        }

        return max;
    }
}
