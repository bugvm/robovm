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
import java.util.*;

import com.bugvm.objc.annotation.*;
import com.bugvm.rt.bro.annotation.*;
import com.bugvm.apple.foundation.*;
import com.bugvm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/AVPlayerItemLegibleOutputPushDelegate/*</name>*/ 
    /*<implements>*/extends AVPlayerItemOutputPushDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "legibleOutput:didOutputAttributedStrings:nativeSampleBuffers:forItemTime:")
    void didOutputAttributedStrings(com.bugvm.ios.AVFoundation.AVPlayerItemLegibleOutput output, NSArray<NSAttributedString> strings, @com.bugvm.rt.bro.annotation.Marshaler(NSArray.AsListMarshaler.class) List<CMSampleBuffer> nativeSamples, @ByVal CMTime itemTime);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}