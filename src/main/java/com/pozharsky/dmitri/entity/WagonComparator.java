package com.pozharsky.dmitri.entity;

import java.util.Comparator;

public enum WagonComparator implements Comparator<Wagon> {
    PERISHABLE {
        @Override
        public int compare(Wagon o1, Wagon o2) {
            Boolean isPerishable1 =  o1.getCargo().isPerishable;
            Boolean isPerishable2 =  o2.getCargo().isPerishable;
            return isPerishable1.compareTo(isPerishable2);
        }
    }
}
