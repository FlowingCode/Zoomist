/*-
 * #%L
 * Zoomist Add-on
 * %%
 * Copyright (C) 2023 - 2026 Flowing Code
 * %%
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
 * #L%
 */
package com.flowingcode.vaadin.addons.zoomist;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DebounceSettings;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.dom.DebouncePhase;

/**
 * Event that is fired when wheeling.
 */
@DomEvent(value = "zoomist-wheel",
    debounce = @DebounceSettings(timeout = 250, phases = DebouncePhase.TRAILING))
public class WheelEvent extends ComponentEvent<Zoomist> {

  private static final long serialVersionUID = 1L;

  public WheelEvent(Zoomist source, boolean fromClient) {
    super(source, fromClient);
  }

}
