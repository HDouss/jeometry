/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2020, Hamdi Douss
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.aljebra.scalar;

import com.aljebra.field.OrderedField;
import java.util.Optional;
import lombok.ToString;

/**
 * A random scalar that is greater than another one.
 * @param <T> scalar types
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public final class Greater<T> extends OrderedScalar<T> {

    /**
     * Random generated scalar.
     */
    private Optional<Scalar<T>> generated;

    /**
     * Scalar to be greater to.
     */
    private final Scalar<T> lower;

    /**
     * Constructor.
     * @param lower Scalar to be greater to
     */
    public Greater(final Scalar<T> lower) {
        this.lower = lower;
        this.generated = Optional.empty();
    }

    @Override
    public T value(final OrderedField<T> field) {
        if (!this.generated.isPresent()) {
            this.generated = Optional.of(field.greater(this.lower));
        }
        return field.actual(this.generated.get());
    }

}
