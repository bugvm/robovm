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
import com.bugvm.rt.bro.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public enum /*<name>*/AUReverbRoomType/*</name>*/ implements ValuedEnum {
    /*<values>*/
    SmallRoom(0L),
    MediumRoom(1L),
    LargeRoom(2L),
    MediumHall(3L),
    LargeHall(4L),
    Plate(5L),
    MediumChamber(6L),
    LargeChamber(7L),
    Cathedral(8L),
    LargeRoom2(9L),
    MediumHall2(10L),
    MediumHall3(11L),
    LargeHall2(12L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AUReverbRoomType/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AUReverbRoomType/*</name>*/ valueOf(long n) {
        for (/*<name>*/AUReverbRoomType/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AUReverbRoomType/*</name>*/.class.getName());
    }
}