/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package shaded.org.apache.commons.lang3.text;

import java.util.Formattable;
import java.util.FormattableFlags;
import java.util.Formatter;

import shaded.org.apache.commons.lang3.ObjectUtils;
import shaded.org.apache.commons.lang3.StringUtils;
import shaded.org.apache.commons.lang3.Validate;

/**
 * Provides utilities for working with the {@link Formattable} interface.
 *
 * <p>The {@link Formattable} interface provides basic control over formatting
 * when using a {@link Formatter}. It is primarily concerned with numeric precision
 * and padding, and is not designed to allow generalised alternate formats.</p>
 *
 * @since 3.0
 * @deprecated As of 3.6, use Apache Commons Text
 * <a href="https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/FormattableUtils.html">
 * FormattableUtils</a> instead
 */
@Deprecated
public class FormattableUtils {

    /**
     * A format that simply outputs the value as a string.
     */
    private static final String SIMPLEST_FORMAT = "%s";

    /**
     * {@link FormattableUtils} instances should NOT be constructed in
     * standard programming. Instead, the methods of the class should be invoked
     * statically.
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    public FormattableUtils() {
    }

    /**
     * Gets the default formatted representation of the specified
     * {@link Formattable}.
     *
     * @param formattable  the instance to convert to a string, not null
     * @return the resulting string, not null
     */
    public static String toString(final Formattable formattable) {
        return String.format(SIMPLEST_FORMAT, formattable);
    }

    /**
     * Handles the common {@link Formattable} operations of truncate-pad-append,
     * with no ellipsis on precision overflow, and padding width underflow with
     * spaces.
     *
     * @param seq  the string to handle, not null
     * @param formatter  the destination formatter, not null
     * @param flags  the flags for formatting, see {@link Formattable}
     * @param width  the width of the output, see {@link Formattable}
     * @param precision  the precision of the output, see {@link Formattable}
     * @return the {@code formatter} instance, not null
     */
    public static Formatter append(final CharSequence seq, final Formatter formatter, final int flags, final int width,
            final int precision) {
        return append(seq, formatter, flags, width, precision, ' ', null);
    }

    /**
     * Handles the common {@link Formattable} operations of truncate-pad-append,
     * with no ellipsis on precision overflow.
     *
     * @param seq  the string to handle, not null
     * @param formatter  the destination formatter, not null
     * @param flags  the flags for formatting, see {@link Formattable}
     * @param width  the width of the output, see {@link Formattable}
     * @param precision  the precision of the output, see {@link Formattable}
     * @param padChar  the pad character to use
     * @return the {@code formatter} instance, not null
     */
    public static Formatter append(final CharSequence seq, final Formatter formatter, final int flags, final int width,
            final int precision, final char padChar) {
        return append(seq, formatter, flags, width, precision, padChar, null);
    }

    /**
     * Handles the common {@link Formattable} operations of truncate-pad-append,
     * padding width underflow with spaces.
     *
     * @param seq  the string to handle, not null
     * @param formatter  the destination formatter, not null
     * @param flags  the flags for formatting, see {@link Formattable}
     * @param width  the width of the output, see {@link Formattable}
     * @param precision  the precision of the output, see {@link Formattable}
     * @param ellipsis  the ellipsis to use when precision dictates truncation, null or
     *  empty causes a hard truncation
     * @return the {@code formatter} instance, not null
     */
    public static Formatter append(final CharSequence seq, final Formatter formatter, final int flags, final int width,
            final int precision, final CharSequence ellipsis) {
        return append(seq, formatter, flags, width, precision, ' ', ellipsis);
    }

    /**
     * Handles the common {@link Formattable} operations of truncate-pad-append.
     *
     * @param seq  the string to handle, not null
     * @param formatter  the destination formatter, not null
     * @param flags  the flags for formatting, see {@link Formattable}
     * @param width  the width of the output, see {@link Formattable}
     * @param precision  the precision of the output, see {@link Formattable}
     * @param padChar  the pad character to use
     * @param ellipsis  the ellipsis to use when precision dictates truncation, null or
     *  empty causes a hard truncation
     * @return the {@code formatter} instance, not null
     */
    public static Formatter append(final CharSequence seq, final Formatter formatter, final int flags, final int width,
            final int precision, final char padChar, final CharSequence ellipsis) {
        Validate.isTrue(ellipsis == null || precision < 0 || ellipsis.length() <= precision,
                "Specified ellipsis '%1$s' exceeds precision of %2$s", ellipsis, Integer.valueOf(precision));
        final StringBuilder buf = new StringBuilder(seq);
        if (precision >= 0 && precision < seq.length()) {
            final CharSequence actualEllipsis = ObjectUtils.defaultIfNull(ellipsis, StringUtils.EMPTY);
            buf.replace(precision - actualEllipsis.length(), seq.length(), actualEllipsis.toString());
        }
        final boolean leftJustify = (flags & FormattableFlags.LEFT_JUSTIFY) == FormattableFlags.LEFT_JUSTIFY;
        for (int i = buf.length(); i < width; i++) {
            buf.insert(leftJustify ? i : 0, padChar);
        }
        formatter.format(buf.toString());
        return formatter;
    }

}
