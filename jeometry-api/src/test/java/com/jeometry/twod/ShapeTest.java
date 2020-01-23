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

import com.jeometry.twod.style.Style;
import com.jeometry.twod.style.impl.DefaultStyle;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Shape}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ShapeTest {

    /**
     * {@link Shape} gives access to renderable, style and name.
     */
    @Test
    public void exposesRenderableNameAndStyle() {
        final Renderable rend = Mockito.mock(Renderable.class);
        final Style style = Mockito.mock(Style.class);
        final String name = "hello";
        final Shape shape = new Shape(rend, name, style);
        MatcherAssert.assertThat(shape.renderable(), Matchers.equalTo(rend));
        MatcherAssert.assertThat(shape.name(), Matchers.equalTo(name));
        MatcherAssert.assertThat(shape.anonymous(), Matchers.equalTo(false));
        MatcherAssert.assertThat(shape.style(), Matchers.equalTo(style));
    }

    /**
     * {@link Shape} accepts anonymous renderable and become anonymous shape.
     */
    @Test
    public void acceptsAnonymousRenderable() {
        final Renderable rend = Mockito.mock(Renderable.class);
        final Shape shape = new Shape(rend);
        MatcherAssert.assertThat(shape.renderable(), Matchers.equalTo(rend));
        MatcherAssert.assertThat(shape.anonymous(), Matchers.equalTo(true));
    }

    /**
     * {@link Shape} accepts anonymous renderable with a style.
     */
    @Test
    public void acceptsAnonymousRenderableWithStyle() {
        final Renderable rend = Mockito.mock(Renderable.class);
        final Style style = Mockito.mock(Style.class);
        final Shape shape = new Shape(rend, style);
        MatcherAssert.assertThat(shape.renderable(), Matchers.equalTo(rend));
        MatcherAssert.assertThat(shape.anonymous(), Matchers.equalTo(true));
        MatcherAssert.assertThat(shape.style(), Matchers.equalTo(style));
    }

    /**
     * {@link Shape} accepts named renderable and gives access to a default
     * style.
     */
    @Test
    public void acceptsNamedRenderableWithoutStyle() {
        final Renderable rend = Mockito.mock(Renderable.class);
        final String name = "default";
        final Shape shape = new Shape(rend, name);
        MatcherAssert.assertThat(shape.renderable(), Matchers.equalTo(rend));
        MatcherAssert.assertThat(shape.anonymous(), Matchers.equalTo(false));
        MatcherAssert.assertThat(shape.name(), Matchers.equalTo(name));
        MatcherAssert.assertThat(
            shape.style(), Matchers.instanceOf(DefaultStyle.class)
        );
    }
}
