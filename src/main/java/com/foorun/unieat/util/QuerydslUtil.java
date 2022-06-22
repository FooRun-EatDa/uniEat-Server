package com.foorun.unieat.util;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public final class QuerydslUtil {
    public static Predicate optAnd(Object object, Supplier<Predicate> expression) {
        if (object == null) {
            return null;
        }
        if (object instanceof Collection) {
            if (((Collection<?>) object).size() == 0) {
                return null;
            }
        }
        return expression.get();
    }

    public static Predicate[] optAnd(Object object, List<Supplier<Predicate>> expression) {
        if (object == null) {
            return null;
        }
        if (object instanceof Collection) {
            if (((Collection<?>) object).size() == 0) {
                return null;
            }
        }
        return expression.stream()
                .map(Supplier::get)
                .toArray(Predicate[]::new);
    }

    /**
     * 조건식이 참(True)일때 expression 반환. 아닐시 null 반환
     */
    public static Predicate optTrueAnd(boolean isTrue, Supplier<Predicate> expression) {
        if (isTrue) {
            return expression.get();
        }
        return null;
    }

    /**
     * 조건식이 참(True)일때 expression 반환. 아닐시 null 반환
     */
    public static Predicate[] optTrueAnd(boolean isTrue, List<Supplier<Predicate>> expressions) {
        if (isTrue) {
            return expressions.stream()
                    .map(Supplier::get)
                    .toArray(Predicate[]::new);
        }
        return new Predicate[] {};
    }

    public static <T> Predicate[] andOfList(List<T> list, Function<T, Predicate> expression) {
        List<Predicate> predicates = new ArrayList<>();
        for (T t : list) {
            predicates.add(expression.apply(t));
        }
        return predicates.toArray(new Predicate[0]);
    }

    public static Predicate and(Supplier<Predicate> expression) {
        return expression.get();
    }
}
