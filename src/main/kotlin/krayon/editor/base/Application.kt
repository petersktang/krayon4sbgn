/*
 * Copyright (c) 2018 Roland Wiese
 * This software is licensed under the Apache License, Version 2.0 (the "License"); you may not use this software except
 * in compliance with the License. You may obtain a copy of the License at  http://www.apache.org/licenses/LICENSE-2.0.
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *  an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package krayon.editor.base

import com.yworks.yfiles.view.GraphComponent
import javax.swing.JFrame
import javax.swing.SwingUtilities

class ApplicationEvent(val source:Any, val type:String, val param:Any? = null)
typealias ApplicationListener = (ApplicationEvent) -> Unit

object Application {
    var focusedGraphComponent:GraphComponent? = null
    val applicationFrame:JFrame? get() = focusedGraphComponent?.let {
        SwingUtilities.getWindowAncestor(focusedGraphComponent) as? JFrame
    }


    val applicationListeners = mutableListOf<ApplicationListener>()
    fun fireApplicationEvent(event:ApplicationEvent) {
        for (listener in applicationListeners) {
            listener.invoke(event)
        }
    }
}
