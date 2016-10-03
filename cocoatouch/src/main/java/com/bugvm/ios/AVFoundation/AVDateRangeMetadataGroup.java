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
package com.bugvm.ios.AVFoundation;

/*<imports>*/

import com.bugvm.objc.*;
import com.bugvm.objc.annotation.*;
import com.bugvm.rt.bro.annotation.*;
import com.bugvm.rt.bro.ptr.*;
import com.bugvm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVDateRangeMetadataGroup/*</name>*/ 
    extends /*<extends>*/AVMetadataGroup/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVDateRangeMetadataGroupPtr extends Ptr<AVDateRangeMetadataGroup, AVDateRangeMetadataGroupPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVDateRangeMetadataGroup.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVDateRangeMetadataGroup() {}
    protected AVDateRangeMetadataGroup(SkipInit skipInit) { super(skipInit); }
    public AVDateRangeMetadataGroup(NSArray<com.bugvm.ios.AVFoundation.AVMetadataItem> items, NSDate startDate, NSDate endDate) { super((SkipInit) null); initObject(init(items, startDate, endDate)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "startDate")
    public native NSDate getStartDate();
    @Property(selector = "endDate")
    public native NSDate getEndDate();
    @Property(selector = "items")
    public native NSArray<com.bugvm.ios.AVFoundation.AVMetadataItem> getItems();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithItems:startDate:endDate:")
    protected native @Pointer long init(NSArray<com.bugvm.ios.AVFoundation.AVMetadataItem> items, NSDate startDate, NSDate endDate);
    /*</methods>*/
}