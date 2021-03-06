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
package com.aljebra.vector;

import com.aljebra.scalar.Multiplication;
import com.aljebra.scalar.Scalar;
import com.aljebra.scalar.mock.Scalars;
import java.util.Iterator;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Times}.
 * @since 0.1
 */
public final class TimesTest {

    /**
     * Max scalar array length to generate(mock).
     */
    private static final int COORDS_LENGTH = 10;

    /**
     * {@link Times} coordinates equals to the field multiplication
     * of vector coordinates by the scalar.
     */
    @Test
    public void coordsEqualCoordsMult() {
        final Scalar<Object> factor = new Scalars<>(1).iterator().next();
        final int dim = 1 + new Random().nextInt(TimesTest.COORDS_LENGTH);
        final Iterable<Scalar<Object>> acoords = new Scalars<>(dim);
        final Vect<Object> vecta = new FixedVector<>(acoords);
        final Iterator<Scalar<Object>> aiterator = acoords.iterator();
        final Scalar<Object>[] actual = new Times<>(vecta, factor).coords();
        for (int idx = 0; idx < dim; ++idx) {
            MatcherAssert.assertThat(
                actual[idx], Matchers.equalTo(TimesTest.mult(aiterator.next(), factor))
            );
        }
    }

    /**
     * {@link Times} toString prints vector and factor.
     */
    @Test
    public void printsAttributes() {
        final Scalar<Object> factor = new Scalars<>(1).iterator().next();
        final int dim = 1 + new Random().nextInt(TimesTest.COORDS_LENGTH);
        final Vect<Object> vect = new FixedVector<>(new Scalars<>(dim));
        MatcherAssert.assertThat(
            new Times<>(vect, factor).toString(),
            Matchers.allOf(
                Matchers.containsString(vect.toString()),
                Matchers.containsString(factor.toString())
            )
        );
    }

    /**
     * Calculates the product of two scalars.
     * @param scalar First scalar
     * @param another Second scalar
     * @return A scalar representing the multiplication of two scalars
     */
    private static Scalar<Object> mult(final Scalar<Object> scalar, final Scalar<Object> another) {
        return new Multiplication<>(scalar, another);
    }

}
