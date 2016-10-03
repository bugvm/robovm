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
import com.bugvm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVCaptureOutput/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVCaptureOutputPtr extends Ptr<AVCaptureOutput, AVCaptureOutputPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVCaptureOutput.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVCaptureOutput() {}
    protected AVCaptureOutput(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "connections")
    public native NSArray<com.bugvm.ios.AVFoundation.AVCaptureConnection> getConnections();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "connectionWithMediaType:")
    public native com.bugvm.ios.AVFoundation.AVCaptureConnection getConnection(AVMediaType mediaType);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "transformedMetadataObjectForMetadataObject:connection:")
    public native com.bugvm.ios.AVFoundation.AVMetadataObject getTransformedMetadataObject(com.bugvm.ios.AVFoundation.AVMetadataObject metadataObject, com.bugvm.ios.AVFoundation.AVCaptureConnection connection);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "metadataOutputRectOfInterestForRect:")
    public native @ByVal CGRect getRectOfInterestInOutputCoordinates(@ByVal CGRect rectInOutputCoordinates);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "rectForMetadataOutputRectOfInterest:")
    public native @ByVal CGRect getRectOfInterestInMetadataOutputCoordinates(@ByVal CGRect rectInMetadataOutputCoordinates);
    /*</methods>*/
}