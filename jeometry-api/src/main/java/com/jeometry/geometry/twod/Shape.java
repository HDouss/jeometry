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
package com.jeometry.geometry.twod;

/**
 * Represents a Shape. A shape is a named {@link Renderable}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Shape {

    /**
     * Reserved name for anonymous renderable.
     */
    private static final String NO_NAME = "NO_NAME";

    /**
     * Renderable.
     */
    private final Renderable rndrable;

    /**
     * Renderable name.
     */
    private final String symbol;

    /**
     * Ctor.
     * @param rndrable Renderable
     * @param symbol Renderable name
     */
    public Shape(final Renderable rndrable, final String symbol) {
        this.rndrable = rndrable;
        this.symbol = symbol;
    }

    /**
     * Ctor. Builds an anonymous renderable.
     * @param rndrable Renderable
     */
    public Shape(final Renderable rndrable) {
        this(rndrable, Shape.NO_NAME);
    }

    /**
     * Accessor for the renderbale.
     * @return The renderbale
     */
    public Renderable renderable() {
        return this.rndrable;
    }

    /**
     * Accessor for the renderbale name.
     * @return The renderable name
     */
    public String name() {
        return this.symbol;
    }

    /**
     * Checks if the renderable is anonymous (with no name).
     * @return True if the renderable is anonymous
     */
    public boolean anonymous() {
        return Shape.NO_NAME.equals(this.symbol);
    }
}
