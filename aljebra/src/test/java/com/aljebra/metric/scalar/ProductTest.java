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
package com.aljebra.metric.scalar;

import com.aljebra.field.MetricSpaceField;
import com.aljebra.field.mock.MkField;
import com.aljebra.field.mock.SpyField;
import com.aljebra.metric.MkProduct;
import com.aljebra.scalar.mock.Scalars;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import java.util.List;
import java.util.Optional;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link Product}.
 * @since 0.1
 */
public final class ProductTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link Product} rely on InnerProduct for resolution of actual value.
     */
    @Test
    public void delegatesToInnerProduct() {
        final int dim = 6;
        final Vect<Object> first = new FixedVector<>(new Scalars<>(dim));
        final Vect<Object> second = new FixedVector<>(new Scalars<>(dim));
        final MkProduct<Object> pdt = new MkProduct<>();
        final MetricSpaceField<Object> field = new MkField<>(new Object(), new Object(), pdt);
        new Product<>(first, second).value(field);
        final Optional<List<Vect<Object>>> params = pdt.product();
        MatcherAssert.assertThat(
            "Expecting call to product method with vectors as parameters",
            params.isPresent() && params.get().get(0).equals(first)
                && params.get().get(1).equals(second)
        );
    }

    /**
     * {@link Product} throws exception when not operating on a metric space.
     */
    @Test
    public void errorsWhenNoMetricSpace() {
        this.thrown.expect(UnsupportedOperationException.class);
        final int dim = 6;
        final Vect<Object> first = new FixedVector<>(new Scalars<>(dim));
        final Vect<Object> second = new FixedVector<>(new Scalars<>(dim));
        new Product<>(first, second).value(new SpyField<>(new Object(), new Object()));
    }

    /**
     * {@link Product} toString prints vectors.
     */
    @Test
    public void printsAttributes() {
        final int dim = 6;
        final Vect<Object> first = new FixedVector<>(new Scalars<>(dim));
        final Vect<Object> second = new FixedVector<>(new Scalars<>(dim));
        MatcherAssert.assertThat(
            new Product<>(first, second).toString(),
            Matchers.allOf(
                Matchers.containsString(first.toString()),
                Matchers.containsString(second.toString())
            )
        );
    }
}
