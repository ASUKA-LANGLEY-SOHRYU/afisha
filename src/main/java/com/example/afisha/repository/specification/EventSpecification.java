package com.example.afisha.repository.specification;

import com.example.afisha.models.Event;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventSpecification {

    public static Specification<Event> getEventSpecification(EventSpecificationFilter esf){
        return (Root<Event> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (esf.getName() != null && !esf.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%"+esf.getName()+"%"));
            }

            if (esf.getPriceFrom() != null && esf.getPriceFrom() >= 0){
                predicates.add(criteriaBuilder.ge(root.get("price"), esf.getPriceFrom()));
            }
            if (esf.getPriceTo() != null && esf.getPriceTo() >= 0){
                predicates.add(criteriaBuilder.le(root.get("price"), esf.getPriceTo()));
            }

            if (esf.getDateFrom() != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateTime"), esf.getDateFrom()));
            }
            if (esf.getDateTo() != null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateTime"), esf.getDateTo()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
