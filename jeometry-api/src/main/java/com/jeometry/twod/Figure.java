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

import com.google.common.base.Preconditions;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Represents a figure composed of shapes to output.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Figure implements Iterable<Shape> {

    /**
     * Anonymous shapes list.
     */
    private final List<Shape> anonymous = new ArrayList<>(10);

    /**
     * Named shapes map.
     */
    private final Map<String, Shape> shaps = new HashMap<>();

    /**
     * Adds a shape to the figure.
     * @param shape The shape to add
     * @return This figure instance
     */
    public Figure add(final Shape shape) {
        final String name = shape.name();
        Preconditions.checkArgument(
            !this.shaps.containsKey(name),
            "A shape with name [%s] already exists in the figure", name
        );
        if (shape.anonymous()) {
            this.anonymous.add(shape);
        } else {
            this.shaps.put(name, shape);
        }
        return this;
    }

    /**
     * Adds an anonymous renderable to the figure.
     * @param shape The renderable to add
     * @return This figure instance
     */
    public Figure add(final Renderable shape) {
        this.anonymous.add(new Shape(shape));
        return this;
    }

    /**
     * Adds an anonymous renderable to the figure.
     * @param shape The renderable to add
     * @param color The color of render
     * @return This figure instance
     */
    public Figure add(final Renderable shape, final Color color) {
        this.anonymous.add(new Shape(shape, color));
        return this;
    }

    /**
     * Adds an named renderable to the figure.
     * @param shape The renderable to add
     * @param name The renderable name
     * @return This figure instance
     */
    public Figure add(final Renderable shape, final String name) {
        final Shape shap = new Shape(shape, name, Color.BLACK);
        return this.add(shap);
    }

    /**
     * Retrieves a shape by its name.
     * @param name Shape name
     * @return The shape
     */
    public Optional<Shape> shape(final String name) {
        return Optional.ofNullable(this.shaps.get(name));
    }

    @Override
    public Iterator<Shape> iterator() {
        final List<Shape> all =
            new ArrayList<>(this.shaps.size() + this.anonymous.size());
        all.addAll(this.shaps.values());
        all.addAll(this.anonymous);
        return all.iterator();
    }
}
