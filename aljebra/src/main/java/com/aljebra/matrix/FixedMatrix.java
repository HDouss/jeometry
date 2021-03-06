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
package com.aljebra.matrix;

import com.aljebra.metric.scalar.Product;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Represents a matrix defined by fixed coordinates.
 * @param <T> scalar types
 * @since 0.1
 */
@EqualsAndHashCode
@ToString
public final class FixedMatrix<T> implements Matrix<T> {

    /**
     * Coordinates.
     */
    private final Scalar<T>[] coors;

    /**
     * Source vector space dimension corresponding to the columns count.
     */
    private final int source;

    /**
     * Target vector space dimension corresponding to the rows (lines) count.
     */
    private final int target;

    /**
     * Constructor.
     * @param lines Matrix lines count
     * @param columns Matrix columns count
     * @param coor Matrix coordinates to be given in a column by column order
     */
    @SuppressWarnings("unchecked")
    public FixedMatrix(final int lines, final int columns,
        final Iterable<? extends Scalar<T>> coor) {
        this.source = columns;
        this.target = lines;
        this.coors = this.valid(Lists.newArrayList(coor)).toArray(new Scalar[1]);
    }

    @Override
    public Scalar<T>[] coords() {
        return Arrays.copyOf(this.coors, this.coors.length);
    }

    @Override
    public Scalar<T>[] column(final int index) {
        final int first = this.index(1, index);
        return Arrays.copyOfRange(this.coors, first, first + this.target);
    }

    @Override
    public Scalar<T>[] line(final int index) {
        final int first = this.index(index, 1);
        final Scalar<T>[] result = Arrays.copyOf(this.coors, this.source);
        for (int idx = 0; idx < this.source; ++idx) {
            result[idx] = this.coors[first + idx * this.target];
        }
        return result;
    }

    @Override
    public Vect<T> apply(final Vect<T> input) {
        if (input.coords().length != this.source) {
            throw new IllegalArgumentException(
                String.format(
                    "Incompatible vector dimension %s with matrix dimension %s",
                    input.coords().length, this.source
                )
            );
        }
        final List<Scalar<T>> result = new ArrayList<>(this.target);
        for (int idx = 0; idx < this.target; ++idx) {
            result.add(this.product(input, idx + 1));
        }
        return new FixedVector<T>(result);
    }

    @Override
    public int columns() {
        return this.source;
    }

    @Override
    public int lines() {
        return this.target;
    }

    /**
     * Returns the single array index based on line and column indices.
     * @param lin Line index (1-based)
     * @param col Column index (1-based)
     * @return The single array index
     */
    private int index(final int lin, final int col) {
        return this.target * (col - 1) + lin - 1;
    }

    /**
     * Calculates the product of the given vector by a matrix line.
     * @param input Given vector
     * @param idx Matrix line index
     * @return Product
     */
    private Scalar<T> product(final Vect<T> input, final int idx) {
        return new Product<T>(input, new FixedVector<T>(Arrays.asList(this.line(idx))));
    }

    /**
     * Checks if this scalar array could be took as matrix coordinate, regarding
     * the matrix lines and columns count. Throws
     * {@link IllegalArgumentException} if the scalar array is not valid.
     * @param coor Scalar list to check
     * @return The scalar array if it is valid
     */
    private List<? extends Scalar<T>> valid(final List<? extends Scalar<T>> coor) {
        final int expected = this.source * this.target;
        Preconditions.checkArgument(
            expected == coor.size(),
            "Expected %d scalars for a matrix with %d lines and %d columns",
            expected, this.source, this.target
        );
        return coor;
    }

}
