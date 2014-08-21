/*
   Copyright (c) 2014 Ahome' Innovation Technologies. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.ait.lienzo.client.core.image.filter;

import com.ait.lienzo.client.core.types.ImageData;
import com.google.gwt.canvas.dom.client.CanvasPixelArray;
import com.google.gwt.core.client.JavaScriptObject;

public abstract class AbstractTableImageDataFilter<T extends AbstractTableImageDataFilter<T>> extends AbstractBaseImageDataFilter<T>
{
    @Override
    public ImageData filter(ImageData source, boolean copy)
    {
        if (null == source)
        {
            return null;
        }
        if (copy)
        {
            source = source.copy();
        }
        if (false == isActive())
        {
            return source;
        }
        final CanvasPixelArray data = source.getData();

        if (null == data)
        {
            return source;
        }
        filter_(data, getTable(), source.getWidth(), source.getHeight());

        return source;
    }
    
    private final native void filter_(JavaScriptObject pixa, JavaScriptObject table, int wide, int high)
    /*-{
        var data = pixa;

        for (var y = 0; y < wide; y++) {

            for(var x = 0; x < high; x++) {
            
                var p = (y * wide + x) * 4;
                
                for(var i = 0; i < 3; i++) {
                
                    data[p+i] = table[data[p+i]];
                }
            }
        }
    }-*/;
    
    protected abstract JavaScriptObject getTable();
}
