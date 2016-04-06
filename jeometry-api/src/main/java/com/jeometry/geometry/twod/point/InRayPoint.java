/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2016, Hamdi Douss
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
package com.jeometry.geometry.twod.point;

import com.jeometry.geometry.twod.ray.Ray;
import com.jeometry.model.algebra.field.OrderedField;
import com.jeometry.model.algebra.scalar.Scalar;
import com.jeometry.model.algebra.vector.Sum;
import com.jeometry.model.algebra.vector.Times;
import com.jeometry.model.algebra.vector.Vect;
import lombok.ToString;

/**
 * A point defined by belonging to a ray. The point is fixed upon
 * construction, which means that a modification to the underlying ray does
 * not ensure that this point is still inside the ray.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(callSuper = true)
public final class InRayPoint extends XyPoint {

    /**
     * Constructor.
     * @param ray The ray to belong to
     * @param field Field for scalar randomization
     */
    public InRayPoint(final Ray ray, final OrderedField<?> field) {
        this(InRayPoint.vector(ray, field));
    }

    /**
     * Constructor.
     * @param vector Point belonging to the ray
     */
    private InRayPoint(final Vect vector) {
        super(vector.coords()[0], vector.coords()[1]);
    }

    /**
     * Builds a vector belonging to the ray.
     * @param ray The ray to belong to
     * @param field Field for scalar randomization
     * @return A point belonging to the ray
     */
    private static Vect vector(final Ray ray, final OrderedField<?> field) {
        final Scalar random = field.greater(field.addIdentity());
        return new Sum(
            new Times(ray.direction(), random), ray.origin()
        );
    }

}
