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
import com.bugvm.rt.annotation.*;
import com.bugvm.rt.bro.annotation.*;
import com.bugvm.rt.bro.ptr.*;
import com.bugvm.apple.foundation.*;
import com.bugvm.apple.corefoundation.*;
import com.bugvm.apple.coregraphics.*;
import com.bugvm.apple.corevideo.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVVideoCompositionRenderContext/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVVideoCompositionRenderContextPtr extends Ptr<AVVideoCompositionRenderContext, AVVideoCompositionRenderContextPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVVideoCompositionRenderContext.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVVideoCompositionRenderContext() {}
    protected AVVideoCompositionRenderContext(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "size")
    public native @ByVal CGSize getSize();
    @Property(selector = "renderTransform")
    public native @ByVal CGAffineTransform getRenderTransform();
    @Property(selector = "renderScale")
    public native float getRenderScale();
    @Property(selector = "pixelAspectRatio")
    public native @ByVal
    AVPixelAspectRatio getPixelAspectRatio();
    @Property(selector = "edgeWidths")
    public native @ByVal
    com.bugvm.ios.AVFoundation.AVEdgeWidths getEdgeWidths();
    @Property(selector = "highQualityRendering")
    public native boolean isHighQualityRendering();
    @Property(selector = "videoComposition")
    public native com.bugvm.ios.AVFoundation.AVVideoComposition getVideoComposition();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @WeaklyLinked
    @Method(selector = "newPixelBuffer")
    public native @com.bugvm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CVPixelBuffer newPixelBuffer();
    /*</methods>*/
}