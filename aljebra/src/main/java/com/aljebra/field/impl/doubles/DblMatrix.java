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
package com.aljebra.field.impl.doubles;

import com.aljebra.matrix.FixedMatrix;
import com.aljebra.matrix.Matrix;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;
import java.util.ArrayList;
import java.util.List;

/**
 * A convenient class for double matrices.
 * @since 0.1
 */
public final class DblMatrix implements Matrix<Double> {

    /**
     * Actual representation of this double matrix.
     */
    private final Matrix<Double> actual;

    /**
     * Constructor.
     * @param lines Matrix lines count
     * @param cols Matrix columns count
     * @param coords Double matrix coordinates
     */
    public DblMatrix(final int lines, final int cols, final Double... coords) {
        this.actual = new FixedMatrix<>(lines, cols, DblMatrix.wrap(coords));
    }

    @Override
    public Scalar<Double>[] coords() {
        return this.actual.coords();
    }

    @Override
    public Scalar<Double>[] column(final int index) {
        return this.actual.column(index);
    }

    @Override
    public Scalar<Double>[] line(final int index) {
        return this.actual.line(index);
    }

    @Override
    public Vect<Double> apply(final Vect<Double> input) {
        return this.actual.apply(input);
    }

    @Override
    public int columns() {
        return this.actual.columns();
    }

    @Override
    public int lines() {
        return this.actual.lines();
    }

    /**
     * Wraps double array into scalar list.
     * @param coords Double array
     * @return A scalar list wrapping the doubles
     */
    private static List<Scalar<Double>> wrap(final Double... coords) {
        final List<Scalar<Double>> result = new ArrayList<>(coords.length);
        for (final Double coord : coords) {
            result.add(DblMatrix.wrap(coord));
        }
        return result;
    }

    /**
     * Wraps a double into a scalar.
     * @param num A double
     * @return A scalar wrapping the double
     */
    private static Scalar<Double> wrap(final Double num) {
        return new Scalar.Default<Double>(num);
    }

}
