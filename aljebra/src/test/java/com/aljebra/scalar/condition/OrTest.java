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
package com.aljebra.scalar.condition;

import com.aljebra.field.mock.MkField;
import java.util.Arrays;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Or}.
 * @since 0.1
 */
public final class OrTest {

    /**
     * {@link Or} returns true if at least one predicate resolves to true.
     */
    @Test
    public void resolvesToTrueWhenOnePredicateTrue() {
        MatcherAssert.assertThat(
            new Or<>(
                Arrays.asList(new True<>(), new False<>(), new False<>())
            ).resolve(new MkField<>(new Object(), new Object())),
            Matchers.is(true)
        );
    }

    /**
     * {@link Or} resolves to false if all predicates resolve to false.
     */
    @Test
    public void resolvesToFalseWhenAllPredicatesFalse() {
        MatcherAssert.assertThat(
            new Or<>(
                new False<>(), new False<>()
            ).resolve(new MkField<>(new Object(), new Object())),
            Matchers.is(false)
        );
    }
}
