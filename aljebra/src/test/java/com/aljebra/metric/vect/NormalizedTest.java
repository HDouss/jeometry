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
package com.aljebra.metric.vect;

import com.aljebra.field.Field;
import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.field.impl.doubles.Dot;
import com.aljebra.metric.InnerProduct;
import com.aljebra.scalar.Random;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Normalized}.
 * @since 0.1
 */
public final class NormalizedTest {

    /**
     * {@link Normalized} gives a normalized vector.
     */
    @Test
    public void normalizesVector() {
        final InnerProduct<Double> pdt = new Dot();
        final Field<Double> dec = new Decimal();
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            dec.actual(
                pdt.norm(
                    new Normalized<Double>(
                        new FixedVector<>(new Random<>(), new Random<>())
                    )
                )
            ),
            Matchers.closeTo(1., error)
        );
        MatcherAssert.assertThat(
            dec.actual(
                pdt.norm(
                    new Normalized<Double>(
                        new FixedVector<>(new Random<>(), new Random<>())
                    )
                )
            ),
            Matchers.closeTo(1., error)
        );
    }

    /**
     * {@link Normalized} toString prints coordinates.
     */
    @Test
    public void printsCoords() {
        final Normalized<Double> vect = new Normalized<>(
            new FixedVector<>(new Random<>(), new Random<>())
        );
        for (final Scalar<Double> scalar : vect.coords()) {
            MatcherAssert.assertThat(
                vect.toString(), Matchers.containsString(scalar.toString())
            );
        }
    }
}
