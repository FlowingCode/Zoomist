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

import com.flowingcode.vaadin.addons.demo.DemoSource;
import com.flowingcode.vaadin.addons.zoomist.Zoomist.Direction;
import com.flowingcode.vaadin.addons.zoomist.Zoomist.Fill;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@DemoSource
@PageTitle("Zoomist Demo")
@Route(value = "zoomist/demo", layout = ZoomistDemoView.class)
public class ZoomistDemo extends Div {

  public ZoomistDemo() {
    Zoomist zoomist = new Zoomist("images/helsinki.jpg");
    zoomist.setSlider(null, Direction.VERTICAL, 10d);
    zoomist.setZoomer(true);
    zoomist.setDraggable(false);
    zoomist.setFill(Fill.COVER);

    zoomist.addDragStartListener(
        e -> Notification.show("Drag started at (" + e.getOffsetX() + ", " + e.getOffsetY() + ")"));
    zoomist.addDragEndListener(
        e -> Notification.show("Drag ended at (" + e.getOffsetX() + ", " + e.getOffsetY() + ")"));

    ComboBox<Direction> sliderDirection = new ComboBox<>("Slider Direction", Direction.values());
    sliderDirection.setValue(Direction.VERTICAL);
    sliderDirection.addValueChangeListener(e -> {
      zoomist.setSlider(null, e.getValue(), null);
    });

    Checkbox slider = new Checkbox("Show slider", zoomist.isSlider());
    slider.addValueChangeListener(e -> {
      if (e.getValue()) {
        zoomist.setSlider(null, sliderDirection.getValue(), null);
      } else {
        zoomist.setSlider(false);
      }
    });

    Checkbox zoomer = new Checkbox("Show zoomer", zoomist.isZoomer());
    zoomer.addValueChangeListener(e -> zoomist.setZoomer(e.getValue()));

    Checkbox draggable = new Checkbox("Draggable", false);
    draggable.addValueChangeListener(e -> zoomist.setDraggable(e.getValue()));

    Checkbox pinchable = new Checkbox("Pinchable", true);
    pinchable.addValueChangeListener(e -> zoomist.setPinchable(e.getValue()));

    Checkbox wheelable = new Checkbox("Wheelable", true);
    wheelable.addValueChangeListener(e -> zoomist.setWheelable(e.getValue()));

    TextField zoom = new TextField("Zoom");
    zoom.addValueChangeListener(e -> zoomist.zoom(Double.valueOf(e.getValue())));

    TextField zoomRatio = new TextField("Zoom Ratio");
    zoomRatio.addValueChangeListener(e -> zoomist.setZoomRatio(Double.valueOf(e.getValue())));
    zoomist.addZoomListener(e -> zoomRatio.setValue(String.valueOf(e.getRatio())));

    Button reset = new Button("Reset");
    reset.addClickListener(e -> zoomist.reset());

    Button displayZoomRatio = new Button("Display zoom ratio");
    displayZoomRatio
        .addClickListener(e -> Notification.show(String.valueOf(zoomist.getZoomRatio())));

    Span displayContainerData = new Span();
    Button getContainerData = new Button("Container Data");
    getContainerData.addClickListener(e -> {
      zoomist.getContainerData(containerData -> {
        displayContainerData.removeAll();
        displayContainerData.add("Height " + containerData.getHeight());
        displayContainerData.add(" - Width " + containerData.getWidth());
        displayContainerData.add(" - Aspect Ratio " + containerData.getAspectRatio());
      });
    });

    add(zoomist, new HorizontalLayout(slider, zoomer, draggable, pinchable, wheelable),
        new HorizontalLayout(sliderDirection, zoom, zoomRatio),
        new HorizontalLayout(reset, getContainerData, displayZoomRatio), displayContainerData);
  }
}
