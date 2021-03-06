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
package com.jeometry.twod.style.impl;

import com.jeometry.twod.style.Dash;
import com.jeometry.twod.style.Stroke;
import java.awt.Color;

/**
 * Fixed stroke style class.
 * @since 0.1
 */
public final class FixedStroke implements Stroke {

    /**
     * Stroke color.
     */
    private final Color clr;

    /**
     * Stroke dashing style.
     */
    private final Dash dsh;

    /**
     * Stroke width.
     */
    private final float wdth;

    /**
     * Ctor. Build a stroke style with the passed color, dash pattern and width.
     * @param clr Color
     * @param dash Dash pattern
     * @param width Width
     */
    public FixedStroke(final Color clr, final Dash dash, final float width) {
        this.clr = clr;
        this.dsh = dash;
        this.wdth = width;
    }

    /**
     * Ctor. Builds a stroke style with default values.
     */
    public FixedStroke() {
        this(new DefaultStroke());
    }

    /**
     * Ctor. Build a fixed stroke from the passed stroke characteristics.
     * @param stroke Stroke to take the properties from
     */
    public FixedStroke(final Stroke stroke) {
        this(stroke.color(), stroke.dash(), stroke.width());
    }

    @Override
    public float width() {
        return this.wdth;
    }

    @Override
    public Color color() {
        return this.clr;
    }

    @Override
    public Dash dash() {
        return this.dsh;
    }

}
