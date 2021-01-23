package com.pozharsky.dmitri.comparator;

import com.pozharsky.dmitri.entity.Cargo;
import com.pozharsky.dmitri.entity.Wagon;

import java.util.Comparator;
import java.util.Optional;

public enum WagonComparator implements Comparator<Wagon> {
    PERISHABLE {
        @Override
        public int compare(Wagon o1, Wagon o2) {
            Optional<Cargo> optionalCargo1 = o1.getCargo();
            Optional<Cargo> optionalCargo2 = o2.getCargo();
            Boolean isPerishable1;
            Boolean isPerishable2;
            isPerishable1 = optionalCargo1.map(Cargo::isPerishable).orElse(false);
            isPerishable2 = optionalCargo2.map(Cargo::isPerishable).orElse(false);
            return isPerishable2.compareTo(isPerishable1);
        }
    }
}
