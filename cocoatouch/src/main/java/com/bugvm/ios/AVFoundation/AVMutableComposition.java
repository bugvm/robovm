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
import com.bugvm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMutableComposition/*</name>*/ 
    extends /*<extends>*/AVComposition/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVMutableCompositionPtr extends Ptr<AVMutableComposition, AVMutableCompositionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVMutableComposition.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVMutableComposition() {}
    protected AVMutableComposition(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 9.0 and later.
     */
    public AVMutableComposition(com.bugvm.ios.AVFoundation.AVURLAssetOptions urlAssetInitializationOptions) { super(create(urlAssetInitializationOptions)); retain(getHandle()); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "tracks")
    public native NSArray<com.bugvm.ios.AVFoundation.AVAssetTrack> getTracks();
    @Property(selector = "naturalSize")
    public native @ByVal CGSize getNaturalSize();
    @Property(selector = "setNaturalSize:")
    public native void setNaturalSize(@ByVal CGSize v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Method(selector = "compositionWithURLAssetInitializationOptions:")
    protected static native @Pointer long create(com.bugvm.ios.AVFoundation.AVURLAssetOptions urlAssetInitializationOptions);
    public boolean insertTimeRange(@ByVal CMTimeRange timeRange, com.bugvm.ios.AVFoundation.AVAsset asset, @ByVal CMTime startTime) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = insertTimeRange(timeRange, asset, startTime, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "insertTimeRange:ofAsset:atTime:error:")
    private native boolean insertTimeRange(@ByVal CMTimeRange timeRange, com.bugvm.ios.AVFoundation.AVAsset asset, @ByVal CMTime startTime, NSError.NSErrorPtr outError);
    @Method(selector = "insertEmptyTimeRange:")
    public native void insertEmptyTimeRange(@ByVal CMTimeRange timeRange);
    @Method(selector = "removeTimeRange:")
    public native void removeTimeRange(@ByVal CMTimeRange timeRange);
    @Method(selector = "scaleTimeRange:toDuration:")
    public native void scaleTimeRange(@ByVal CMTimeRange timeRange, @ByVal CMTime duration);
    @Method(selector = "addMutableTrackWithMediaType:preferredTrackID:")
    public native com.bugvm.ios.AVFoundation.AVMutableCompositionTrack addMutableTrack(com.bugvm.ios.AVFoundation.AVMediaType mediaType, int preferredTrackID);
    @Method(selector = "removeTrack:")
    public native void removeTrack(com.bugvm.ios.AVFoundation.AVCompositionTrack track);
    @Method(selector = "mutableTrackCompatibleWithTrack:")
    public native com.bugvm.ios.AVFoundation.AVMutableCompositionTrack getCompatibleMutableTrack(com.bugvm.ios.AVFoundation.AVAssetTrack track);
    @Method(selector = "trackWithTrackID:")
    public native com.bugvm.ios.AVFoundation.AVMutableCompositionTrack getTrack(int trackID);
    @Method(selector = "tracksWithMediaType:")
    public native NSArray<com.bugvm.ios.AVFoundation.AVCompositionTrack> getTracksForMediaType(com.bugvm.ios.AVFoundation.AVMediaType mediaType);
    @Method(selector = "tracksWithMediaCharacteristic:")
    public native NSArray<com.bugvm.ios.AVFoundation.AVCompositionTrack> getTracksForMediaCharacteristic(com.bugvm.ios.AVFoundation.AVMediaCharacteristic mediaCharacteristic);
    /*</methods>*/
}