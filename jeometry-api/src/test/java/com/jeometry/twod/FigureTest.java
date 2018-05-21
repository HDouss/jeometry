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
package com.jeometry.twod;

import java.awt.Color;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Tests for {@link Figure}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FigureTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link Figure} can add an anonymous shape.
     */
    @Test
    public void addsAnonymousShape() {
        final Shape shape = new Shape(Mockito.mock(Renderable.class));
        MatcherAssert.assertThat(
            new Figure().add(shape), Matchers.contains(shape)
        );
    }

    /**
     * {@link Figure} can add a named shape.
     */
    @Test
    public void addsNamedShape() {
        final Shape shape = new Shape(
            Mockito.mock(Renderable.class), "name", Color.BLACK
        );
        MatcherAssert.assertThat(
            new Figure().add(shape), Matchers.contains(shape)
        );
    }

    /**
     * {@link Figure} throws exception if a shape with the same name
     * already exists.
     */
    @Test
    public void errorsWhenShapeAlreadyExists() {
        this.thrown.expect(IllegalArgumentException.class);
        final String name = "twice";
        new Figure().add(
            new Shape(Mockito.mock(Renderable.class), name, Color.BLACK)
        ).add(new Shape(Mockito.mock(Renderable.class), name, Color.BLACK));
    }

    /**
     * {@link Figure} can add a named renderable.
     */
    @Test
    public void addsNamedRenderable() {
        final Renderable shape = Mockito.mock(Renderable.class);
        final String name = "renderable";
        MatcherAssert.assertThat(
            new Figure().add(shape, name).shape(name).isPresent(),
            Matchers.equalTo(true)
        );
    }

    /**
     * {@link Figure} can add an anonymous renderable.
     */
    @Test
    public void addsAnonymousRenderable() {
        final Renderable shape = Mockito.mock(Renderable.class);
        MatcherAssert.assertThat(
            new Figure().add(shape).iterator().next().renderable(),
            Matchers.equalTo(shape)
        );
    }

    /**
     * {@link Figure} can retrieve a shape by its name.
     */
    @Test
    public void retrievesShapeByName() {
        final String name = "hello";
        final Shape shape = new Shape(
            Mockito.mock(Renderable.class), name, Color.BLACK
        );
        MatcherAssert.assertThat(
            new Figure().add(shape).shape(name).get(), Matchers.equalTo(shape)
        );
    }

}
