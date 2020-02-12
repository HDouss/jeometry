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

import com.aljebra.vector.Vect;
import com.jeometry.twod.point.RandomPoint;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link PtDirRay}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class PtDirRayTest {

    /**
     * {@link PtDirRay} builds a with the given origin and the given direction.
     */
    @Test
    public void buildsPointDirRay() {
        final Vect origin = new RandomPoint();
        final Vect dir = new RandomPoint();
        final Ray ray = new PtDirRay(origin, dir);
        MatcherAssert.assertThat(ray.origin(), Matchers.equalTo(origin));
        MatcherAssert.assertThat(ray.direction(), Matchers.equalTo(dir));
    }

    /**
     * {@link PtDirRay} toString prints origin and direction.
     */
    @Test
    public void toStringContainsOriginAndDirection() {
        final Vect origin = new RandomPoint();
        final Vect dir = new RandomPoint();
        MatcherAssert.assertThat(
            new PtDirRay(origin, dir).toString(),
            Matchers.allOf(
                Matchers.containsString(origin.toString()),
                Matchers.containsString(dir.toString())
            )
        );
    }
}
