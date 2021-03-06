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
package com.aljebra.metric.angle;

import com.aljebra.metric.InnerProduct;
import com.aljebra.metric.MkProduct;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Complementary}.
 * @since 0.1
 */
public final class ComplementaryTest {

    /**
     * {@link Complementary} resolves to a complementary angle.
     */
    @Test
    public void resolvesComplementaryAngle() {
        final Degrees<Double> first = new Degrees.Default<>(Math.random());
        final Degrees<Double> second = new Degrees.Default<>(Math.random());
        final Degrees<Double> third = new Degrees.Default<>(Math.random());
        final InnerProduct<Double> pdt = new MkProduct<>();
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            new Complementary<>(first).resolve(pdt).doubleValue()
                + first.resolve(pdt).doubleValue(),
            Matchers.closeTo(Math.PI / 2, error)
        );
        MatcherAssert.assertThat(
            new Complementary<>(second).resolve(pdt).doubleValue()
                + second.resolve(pdt).doubleValue(),
            Matchers.closeTo(Math.PI / 2, error)
        );
        MatcherAssert.assertThat(
            new Complementary<>(third).resolve(pdt).doubleValue()
                + third.resolve(pdt).doubleValue(),
            Matchers.closeTo(Math.PI / 2, error)
        );
    }

    /**
     * {@link Complementary} toString prints underlying degrees.
     */
    @Test
    public void toStringPrintsDegrees() {
        final Degrees<Double> deg = new Degrees.Default<>(Math.random());
        MatcherAssert.assertThat(
            new Complementary<>(deg).toString(),
            Matchers.containsString(deg.toString())
        );
    }

    /**
     * {@link Complementary} respects equality with the same underlying degrees.
     */
    @Test
    public void equalsRespectsDegrees() {
        final Degrees<Double> deg = new Degrees.Default<>(Math.random());
        MatcherAssert.assertThat(
            new Complementary<>(deg),
            Matchers.equalTo(new Complementary<>(deg))
        );
    }

    /**
     * {@link Complementary} respects hashcode with the same underlying degrees.
     */
    @Test
    public void hashCodeRespectsDegrees() {
        final Degrees<Double> deg = new Degrees.Default<>(Math.random());
        MatcherAssert.assertThat(
            new Complementary<>(deg).hashCode(),
            Matchers.equalTo(new Complementary<>(deg).hashCode())
        );
    }
}
