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
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.dom.DebouncePhase;
import lombok.Getter;

/**
 * Event that is fired when the image is dragging.
 */
@DomEvent(value = "zoomist-drag",
    debounce = @DebounceSettings(timeout = 250, phases = DebouncePhase.TRAILING))
public class DragEvent extends ComponentEvent<Zoomist> {

  private static final long serialVersionUID = 1L;

  @Getter
  private final double offsetX;
  @Getter
  private final double offsetY;

  public DragEvent(Zoomist source, boolean fromClient,
      @EventData("event.detail.offsetX") double offsetX,
      @EventData("event.detail.offsetY") double offsetY) {
    super(source, fromClient);
    this.offsetX = offsetX;
    this.offsetY = offsetY;
  }

}

