/**
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
package com.aljebra.matrix;

import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link VectorMatrix}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class VectorMatrixTest {

    /**
     * Max random integer.
     */
    private static final int INT_RANDOM = 10;

    /**
     * {@link VectorMatrix} returns right coordinates.
     */
    @Test
    public void calculatesCoordinates() {
        final int dim = new Random().nextInt(VectorMatrixTest.INT_RANDOM);
        final Scalar[] coorsa = VectorMatrixTest.scalars(dim);
        final Matrix scalarmat = new VectorMatrix(coorsa);
        final Matrix vectmat = new VectorMatrix(new FixedVector(coorsa));
        MatcherAssert.assertThat(scalarmat.lines(), Matchers.equalTo(1));
        MatcherAssert.assertThat(scalarmat.columns(), Matchers.equalTo(dim));
        MatcherAssert.assertThat(
            scalarmat.coords(), Matchers.equalTo(coorsa)
        );
        MatcherAssert.assertThat(vectmat.lines(), Matchers.equalTo(1));
        MatcherAssert.assertThat(vectmat.columns(), Matchers.equalTo(dim));
        MatcherAssert.assertThat(
            vectmat.coords(), Matchers.equalTo(coorsa)
        );
    }

    /**
     * {@link VectorMatrix} instances are considered equals if they have the
     * same coordinates.
     */
    @Test
    public void considersEqualsIfSameCoordinates() {
        final Scalar[] coords = VectorMatrixTest.scalars(
            new Random().nextInt(VectorMatrixTest.INT_RANDOM)
        );
        MatcherAssert.assertThat(
            new VectorMatrix(coords), Matchers.equalTo(new VectorMatrix(coords))
        );
    }

    /**
     * {@link VectorMatrix} instances have same hashcode if they have the same
     * coordinates.
     */
    @Test
    public void sameHashCodeIfSameCoordinates() {
        final Scalar[] coords = VectorMatrixTest.scalars(
            new Random().nextInt(VectorMatrixTest.INT_RANDOM)
        );
        MatcherAssert.assertThat(
            new VectorMatrix(coords).hashCode(),
            Matchers.equalTo(new VectorMatrix(coords).hashCode())
        );
    }

    /**
     * Mocks an array of {@link Scalar} with a given length.
     * @param length Array length
     * @return An array of scalars
     */
    private static Scalar[] scalars(final int length) {
        final Scalar[] result = new Scalar[length];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = Mockito.mock(Scalar.class);
        }
        return result;
    }

}
