package com.acuo.collateral.transform.utils;

import com.acuo.collateral.transform.TransformerOutput;
import com.acuo.common.model.results.Error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class OutputBuilder<OUTPUT> {

    private final List<OUTPUT> results;
    private final List<Error> errors;

    public static <OUTPUT> OutputBuilder<OUTPUT> of(Object[] results, Object[] errors) {
        return new OutputBuilder<>(results, errors);
    }

    public OutputBuilder(Object[] results, Object[] errors) {
        this.results = results(results);
        this.errors = errors(errors);
    }

    public OutputBuilder<OUTPUT> addResults(Object[] results) {
        this.results.addAll(results(results));
        return this;
    }

    public OutputBuilder<OUTPUT> addErrors(Object[] errors) {
        this.errors.addAll(errors(errors));
        return this;
    }

    private List<OUTPUT> results(Object[] results) {
        if (results == null) return new ArrayList<>();
        return Arrays.stream(results)
                .map(value -> (OUTPUT)value)
                .collect(toList());
    }

    private List<Error> errors(Object[] errors) {
        if (errors == null) return new ArrayList<>();
        return Arrays.stream(errors)
                .map(value -> (Error) value)
                .collect(toList());
    }

    public TransformerOutput<OUTPUT> build() {
        return new TransformerOutput<OUTPUT>() {
            @Override
            public List<OUTPUT> results() {
                return results;
            }
            @Override
            public List<Error> errors() {
                return errors;
            }
        };
    }
}
