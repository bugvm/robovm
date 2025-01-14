/*
 * Copyright (C) 2014-2016 Samuel Audet
 *
 * Licensed either under the Apache License, Version 2.0, or (at your option)
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation (subject to the "Classpath" exception),
 * either version 2, or any later version (collectively, the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     http://www.gnu.org/licenses/
 *     http://www.gnu.org/software/classpath/license.html
 *
 * or as provided in the LICENSE.txt file that accompanied this code.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bugvm.javacpp.indexer;

import java.nio.Buffer;
import java.nio.LongBuffer;

/**
 * An indexer for a {@link LongBuffer}.
 *
 * @author Samuel Audet
 */
public class LongBufferIndexer extends LongIndexer {
    /** The backing buffer. */
    protected LongBuffer buffer;

    /** Calls {@code LongBufferIndexer(buffer, { buffer.limit() }, { 1 })}. */
    public LongBufferIndexer(LongBuffer buffer) {
        this(buffer, new long[] { buffer.limit() }, ONE_STRIDE);
    }

    /** Constructor to set the {@link #buffer}, {@link #sizes} and {@link #strides}. */
    public LongBufferIndexer(LongBuffer buffer, long[] sizes, long[] strides) {
        super(sizes, strides);
        this.buffer = buffer;
    }

    @Override public Buffer buffer() {
        return buffer;
    }

    @Override public long get(long i) {
        return buffer.get((int)i);
    }
    @Override public LongIndexer get(long i, long[] l, int offset, int length) {
        for (int n = 0; n < length; n++) {
            l[offset + n] = buffer.get((int)i * (int)strides[0] + n);
        }
        return this;
    }
    @Override public long get(long i, long j) {
        return buffer.get((int)i * (int)strides[0] + (int)j);
    }
    @Override public LongIndexer get(long i, long j, long[] l, int offset, int length) {
        for (int n = 0; n < length; n++) {
            l[offset + n] = buffer.get((int)i * (int)strides[0] + (int)j * (int)strides[1] + n);
        }
        return this;
    }
    @Override public long get(long i, long j, long k) {
        return buffer.get((int)i * (int)strides[0] + (int)j * (int)strides[1] + (int)k);
    }
    @Override public long get(long... indices) {
        return buffer.get((int)index(indices));
    }
    @Override public LongIndexer get(long[] indices, long[] l, int offset, int length) {
        for (int n = 0; n < length; n++) {
            l[offset + n] = buffer.get((int)index(indices) + n);
        }
        return this;
    }

    @Override public LongIndexer put(long i, long l) {
        buffer.put((int)i, l);
        return this;
    }
    @Override public LongIndexer put(long i, long[] l, int offset, int length) {
        for (int n = 0; n < length; n++) {
            buffer.put((int)i * (int)strides[0] + n, l[offset + n]);
        }
        return this;
    }
    @Override public LongIndexer put(long i, long j, long l) {
        buffer.put((int)i * (int)strides[0] + (int)j, l);
        return this;
    }
    @Override public LongIndexer put(long i, long j, long[] l, int offset, int length) {
        for (int n = 0; n < length; n++) {
            buffer.put((int)i * (int)strides[0] + (int)j * (int)strides[1] + n, l[offset + n]);
        }
        return this;
    }
    @Override public LongIndexer put(long i, long j, long k, long l) {
        buffer.put((int)i * (int)strides[0] + (int)j * (int)strides[1] + (int)k, l);
        return this;
    }
    @Override public LongIndexer put(long[] indices, long l) {
        buffer.put((int)index(indices), l);
        return this;
    }
    @Override public LongIndexer put(long[] indices, long[] l, int offset, int length) {
        for (int n = 0; n < length; n++) {
            buffer.put((int)index(indices) + n, l[offset + n]);
        }
        return this;
    }

    @Override public void release() { buffer = null; }
}
