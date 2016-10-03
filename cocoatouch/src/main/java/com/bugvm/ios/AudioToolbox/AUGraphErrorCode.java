/*
 * Copyright (C) 2013-2015 RoboVM AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bugvm.ios.AudioToolbox;

/*<imports>*/
import com.bugvm.rt.bro.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public enum /*<name>*/AUGraphErrorCode/*</name>*/ implements ValuedEnum {
    /*<values>*/
    NodeNotFound(-10860L),
    InvalidConnection(-10861L),
    OutputNodeErr(-10862L),
    CannotDoInCurrentContext(-10863L),
    InvalidAudioUnit(-10864L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AUGraphErrorCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AUGraphErrorCode/*</name>*/ valueOf(long n) {
        for (/*<name>*/AUGraphErrorCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AUGraphErrorCode/*</name>*/.class.getName());
    }
}