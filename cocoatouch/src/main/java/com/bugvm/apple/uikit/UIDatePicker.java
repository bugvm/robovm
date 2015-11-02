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
package com.bugvm.apple.uikit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import com.bugvm.objc.*;
import com.bugvm.objc.annotation.*;
import com.bugvm.objc.block.*;
import com.bugvm.rt.*;
import com.bugvm.rt.annotation.*;
import com.bugvm.rt.bro.*;
import com.bugvm.rt.bro.annotation.*;
import com.bugvm.rt.bro.ptr.*;
import com.bugvm.apple.foundation.*;
import com.bugvm.apple.coreanimation.*;
import com.bugvm.apple.coregraphics.*;
import com.bugvm.apple.coredata.*;
import com.bugvm.apple.coreimage.*;
import com.bugvm.apple.coretext.*;
import com.bugvm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIDatePicker/*</name>*/ 
    extends /*<extends>*/UIControl/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class UIDatePickerPtr extends Ptr<UIDatePicker, UIDatePickerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIDatePicker.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIDatePicker() {}
    protected UIDatePicker(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public UIDatePicker(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    @Property(selector = "datePickerMode")
    public native UIDatePickerMode getDatePickerMode();
    @Property(selector = "setDatePickerMode:")
    public native void setDatePickerMode(UIDatePickerMode v);
    @Property(selector = "locale")
    public native NSLocale getLocale();
    @Property(selector = "setLocale:")
    public native void setLocale(NSLocale v);
    @Property(selector = "calendar")
    public native NSCalendar getCalendar();
    @Property(selector = "setCalendar:")
    public native void setCalendar(NSCalendar v);
    @Property(selector = "timeZone")
    public native NSTimeZone getTimeZone();
    @Property(selector = "setTimeZone:")
    public native void setTimeZone(NSTimeZone v);
    @Property(selector = "date")
    public native NSDate getDate();
    @Property(selector = "setDate:")
    public native void setDate(NSDate v);
    @Property(selector = "minimumDate")
    public native NSDate getMinimumDate();
    @Property(selector = "setMinimumDate:")
    public native void setMinimumDate(NSDate v);
    @Property(selector = "maximumDate")
    public native NSDate getMaximumDate();
    @Property(selector = "setMaximumDate:")
    public native void setMaximumDate(NSDate v);
    @Property(selector = "countDownDuration")
    public native double getCountDownDuration();
    @Property(selector = "setCountDownDuration:")
    public native void setCountDownDuration(double v);
    @Property(selector = "minuteInterval")
    public native @MachineSizedSInt long getMinuteInterval();
    @Property(selector = "setMinuteInterval:")
    public native void setMinuteInterval(@MachineSizedSInt long v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setDate:animated:")
    public native void setDate(NSDate date, boolean animated);
    /*</methods>*/
}