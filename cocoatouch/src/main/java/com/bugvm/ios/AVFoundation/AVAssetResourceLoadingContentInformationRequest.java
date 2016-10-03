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
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetResourceLoadingContentInformationRequest/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAssetResourceLoadingContentInformationRequestPtr extends Ptr<AVAssetResourceLoadingContentInformationRequest, AVAssetResourceLoadingContentInformationRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAssetResourceLoadingContentInformationRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAssetResourceLoadingContentInformationRequest() {}
    protected AVAssetResourceLoadingContentInformationRequest(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "contentType")
    public native String getContentType();
    @Property(selector = "setContentType:")
    public native void setContentType(String v);
    @Property(selector = "contentLength")
    public native long getContentLength();
    @Property(selector = "setContentLength:")
    public native void setContentLength(long v);
    @Property(selector = "isByteRangeAccessSupported")
    public native boolean isByteRangeAccessSupported();
    @Property(selector = "setByteRangeAccessSupported:")
    public native void setByteRangeAccessSupported(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "renewalDate")
    public native NSDate getRenewalDate();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setRenewalDate:")
    public native void setRenewalDate(NSDate v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}