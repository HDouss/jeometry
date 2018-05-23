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
package com.jeometry.render.awt;

import com.aljebra.field.Field;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import com.jeometry.twod.Figure;
import com.jeometry.twod.Shape;
import com.jeometry.twod.line.Line;
import java.awt.Component;
import java.awt.Graphics2D;
import java.util.concurrent.CountDownLatch;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Tests for {@link Awt}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class AwtTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link Awt} becomes visible when rendering a figure.
     */
    @Test
    public void becomesVisible() {
//        final Awt awt = new Awt();
//        awt.render(new Figure());
//        MatcherAssert.assertThat(awt.isVisible(), Matchers.is(true));
    }

    /**
     * {@link Awt} delegates size modifications to drawable surface.
     */
    @Test
    public void modifiesDrawableSurfaceSize() {
//        final Awt awt = new Awt();
//        final int width = 1401;
//        final int height = 2011;
//        awt.render(new Figure());
//        awt.withSize(width, height);
//        final AwtDrawableSurface surface = AwtTest.drawable(awt);
//        final double scale = surface.context().scale();
//        MatcherAssert.assertThat(
//            surface.getWidth(), Matchers.equalTo((int) scale * width)
//        );
//        MatcherAssert.assertThat(
//            surface.getHeight(), Matchers.equalTo((int) scale * height)
//        );
    }

    /**
     * {@link Awt} adds a painter, and delegates figure rendering
     * to this painter.
     * @throws InterruptedException If fails.
     */
    @Test
    public void addsPainter() throws InterruptedException {
//        final Awt awt = new Awt();
//        final CountDownLatch latch = new CountDownLatch(1);
//        @SuppressWarnings("unchecked")
//        final AbstractAwtPaint painter = new AbstractAwtPaint(
//            Mockito.mock(Field.class), Line.class
//        ) {
//            @Override
//            protected void draw(final Shape renderable,
//                final Graphics2D graphic, final AwtContext ctx) {
//                ((Line) renderable.renderable()).direction();
//                latch.countDown();
//            }
//        };
//        final Line line = Mockito.mock(Line.class);
//        Mockito.when(line.point()).thenReturn(
//            new FixedVector(
//                new Scalar.Default<Double>(0.), new Scalar.Default<Double>(0.)
//            )
//        );
//        Mockito.when(line.direction()).thenReturn(
//            new FixedVector(
//                new Scalar.Default<Double>(1.), new Scalar.Default<Double>(1.)
//            )
//        );
//        awt.add(painter).render(new Figure().add(new Shape(line)));
//        latch.await();
//        Mockito.verify(line, Mockito.atLeastOnce()).direction();
    }

    /**
     * Gets the drawable surface from Awt.
     * @param awt Awt
     * @return Drawable surface
     */
    private static AwtDrawableSurface drawable(final Awt awt) {
        final Component[] comps = awt.getContentPane().getComponents();
        AwtDrawableSurface result = null;
        for (int idx = 0; idx < comps.length; ++idx) {
            if (comps[idx] instanceof AwtDrawableSurface) {
                result = (AwtDrawableSurface) comps[idx];
                break;
            }
        }
        return result;
    }
}
