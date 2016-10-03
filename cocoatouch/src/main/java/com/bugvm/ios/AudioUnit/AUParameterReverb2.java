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
package com.bugvm.ios.AudioUnit;

/*<imports>*/
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public enum /*<name>*/AUParameterReverb2/*</name>*/ implements AUParameterType {
    /*<values>*/
    DryWetMix(0L),
    Gain(1L),
    MinDelayTime(2L),
    MaxDelayTime(3L),
    DecayTimeAt0Hz(4L),
    DecayTimeAtNyquist(5L),
    RandomizeReflections(6L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AUParameterReverb2/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AUParameterReverb2/*</name>*/ valueOf(long n) {
        for (/*<name>*/AUParameterReverb2/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AUParameterReverb2/*</name>*/.class.getName());
    }
}