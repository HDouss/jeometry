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

import java.util.Arrays;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Scalar}.
 * @since 0.1
 */
public final class ScalarTest {

    /**
     * Scalar.Default respects equals on value.
     */
    @Test
    public void respectsEqual() {
        MatcherAssert.assertThat(
            new Scalar.Default<Double>(1.),
            Matchers.equalTo(new Scalar.Default<Double>(1.))
        );
        final String test = "test";
        MatcherAssert.assertThat(
            new Scalar.Default<String>(test),
            Matchers.equalTo(new Scalar.Default<String>(test))
        );
    }

    /**
     * Scalar.Default toString prints underlying object.
     */
    @Test
    public void printsUnderlying() {
        final String str = "whatever";
        MatcherAssert.assertThat(
            new Scalar.Default<String>(str).toString(),
            Matchers.containsString(str)
        );
    }

    /**
     * Scalar can build addition.
     */
    @Test
    public void buildsAddition() {
        final Scalar<Object> scalar = new Scalar.Default<>(new Object());
        final Scalar<Object> other = new Scalar.Default<>(new Object());
        final Scalar<Object> third = new Scalar.Default<>(new Object());
        MatcherAssert.assertThat(
            scalar.add(other),
            Matchers.equalTo(new Add<>(scalar, other))
        );
        MatcherAssert.assertThat(
            scalar.add(Arrays.asList(other, third)),
            Matchers.equalTo(new Add<>(Arrays.asList(scalar, other, third)))
        );
    }

    /**
     * Scalar can build multiplication.
     */
    @Test
    public void buildsMultiplication() {
        final Scalar<Object> scalar = new Scalar.Default<>(new Object());
        final Scalar<Object> other = new Scalar.Default<>(new Object());
        final Scalar<Object> third = new Scalar.Default<>(new Object());
        MatcherAssert.assertThat(
            scalar.mult(other),
            Matchers.equalTo(new Multiplication<>(scalar, other))
        );
        MatcherAssert.assertThat(
            scalar.mult(Arrays.asList(other, third)),
            Matchers.equalTo(new Multiplication<>(Arrays.asList(scalar, other, third)))
        );
    }
}
