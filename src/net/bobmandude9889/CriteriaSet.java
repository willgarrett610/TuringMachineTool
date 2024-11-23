package net.bobmandude9889;

import java.util.ArrayList;
import java.util.List;

public class CriteriaSet {

    List<Criteria> criteriaList;
    List<String> labels;
    List<Boolean> valid;
    List<CriteriaSet> invalidGiven;

    public CriteriaSet(List<Criteria> criteriaList, List<String> labels) {
        this.criteriaList = criteriaList;
        this.labels = labels;
        this.valid = new ArrayList<>();
        this.invalidGiven = new ArrayList<>();
        for (int i = 0; i < criteriaList.size(); i++) {
            this.valid.add(true);
            this.invalidGiven.add(null);
        }
    }

}
