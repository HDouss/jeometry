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
package com.jeometry.twod.ray;

import com.aljebra.field.impl.doubles.Dot;
import com.aljebra.metric.InnerProduct;
import com.aljebra.vector.Vect;
import com.jeometry.twod.angle.Angle;
import com.jeometry.twod.angle.VectsAngle;
import com.jeometry.twod.point.RandomPoint;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link AngleBisector}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class AngleBisectorTest {

    /**
     * {@link AngleBisector} builds a ray bisecting an angle.
     */
    @Test
    public void buildsBisectorRay() {
        final Vect origin = new RandomPoint();
        final Vect start = new RandomPoint();
        final Vect end = new RandomPoint();
        final Ray bisector = new AngleBisector(
            new VectsAngle(origin, start, end)
        );
        final Vect dir = bisector.direction();
        final InnerProduct dot = new Dot();
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            dot.angle(start, dir).resolve(dot).doubleValue(),
            Matchers.closeTo(
                dot.angle(dir, end).resolve(dot).doubleValue(), error
            )
        );
        MatcherAssert.assertThat(bisector.origin(), Matchers.equalTo(origin));
    }

    /**
     * {@link AngleBisector} toString prints the bisected angle.
     */
    @Test
    public void toStringContainsAngle() {
        final Angle angle = Mockito.mock(Angle.class);
        MatcherAssert.assertThat(
            new AngleBisector(angle).toString(),
            Matchers.containsString(angle.toString())
        );
    }
}
