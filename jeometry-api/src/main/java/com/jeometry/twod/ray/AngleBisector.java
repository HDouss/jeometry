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
package com.jeometry.twod.ray;

import com.aljebra.metric.vect.BisectorVect;
import com.aljebra.vector.Vect;
import com.jeometry.twod.angle.Angle;
import lombok.ToString;

/**
 * A ray defined by being an angle bisector.
 * @param <T> scalar types
 * @since 0.1
 */
@ToString
public final class AngleBisector<T> implements Ray<T> {

    /**
     * Angle oto bisect.
     */
    private final Angle<T> angle;

    /**
     * Constructor.
     * @param angle Angle to bisect
     */
    public AngleBisector(final Angle<T> angle) {
        this.angle = angle;
    }

    @Override
    public Vect<T> direction() {
        return new BisectorVect<>(this.angle.start(), this.angle.end());
    }

    @Override
    public Vect<T> origin() {
        return this.angle.origin();
    }

}
