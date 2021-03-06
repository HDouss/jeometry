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

import com.aljebra.field.Field;
import com.aljebra.field.OrderedField;

/**
 * Ordered Scalar abstract class.
 * @param <T> scalar types
 * @since 0.1
 */
public abstract class OrderedScalar<T> implements Scalar<T> {

    /**
     * Return the actual value of the scalar.
     * @param field Scalar field
     * @return An object representing the scalar
     */
    public abstract T value(OrderedField<T> field);

    @Override
    public final T value(final Field<T> field) {
        if (field instanceof OrderedField<?>) {
            return this.value((OrderedField<T>) field);
        } else {
            throw new UnsupportedOperationException(
                String.format("Field %s is not an ordered field", field)
            );
        }
    }
}
