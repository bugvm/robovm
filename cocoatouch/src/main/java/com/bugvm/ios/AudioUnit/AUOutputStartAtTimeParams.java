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
import com.bugvm.rt.bro.annotation.*;
import com.bugvm.rt.bro.ptr.*;
import com.bugvm.apple.coreaudio.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AUOutputStartAtTimeParams/*</name>*/ 
    extends /*<extends>*/Struct<AUOutputStartAtTimeParams>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AUOutputStartAtTimeParamsPtr extends Ptr<AUOutputStartAtTimeParams, AUOutputStartAtTimeParamsPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AUOutputStartAtTimeParams() {}
    public AUOutputStartAtTimeParams(AudioTimeStamp timestamp, int flags) {
        this.setTimestamp(timestamp);
        this.setFlags(flags);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @ByVal AudioTimeStamp getTimestamp();
    @StructMember(0) public native AUOutputStartAtTimeParams setTimestamp(@ByVal AudioTimeStamp timestamp);
    @StructMember(1) public native int getFlags();
    @StructMember(1) public native AUOutputStartAtTimeParams setFlags(int flags);
    /*</members>*/
    /*<methods>*//*</methods>*/
}