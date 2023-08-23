/*-
 * #%L
 * Zoomist Add-on
 * %%
 * Copyright (C) 2023 Flowing Code
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

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.shared.Registration;
import elemental.json.Json;
import elemental.json.JsonObject;
import java.util.Objects;
import lombok.Getter;

/**
 * Java compone.
 *
 * <p>
 * This component is a wrapper for Zoomist Web Component (https://npm.io/package/zoomist)
 *
 * @author Felipe Lang / Flowing Code
 */
@SuppressWarnings("serial")
@NpmPackage(value = "zoomist", version = "1.1.1")
@NpmPackage(value = "sass", version = "1.66.1")
@JsModule("./zoomist/fc-zoomist.ts")
@Tag("fc-zoomist")
public class Zoomist extends Component {

  enum Fill {
    COVER("cover"), CONTAIN("contain"), NONE("none");

    @Getter
    private String value;

    Fill(String value) {
      this.value = value;
    }
  }

  enum Direction {
    HORIZONTAL("horizontal"), VERTICAL("vertical");

    @Getter
    private String value;

    Direction(String value) {
      this.value = value;
    }
  }

  public Zoomist(String src) {
    setSrc(src);
  }

  public void setSrc(String src) {
    getElement().setProperty("src", src);
  }

  public void setSlider(boolean visible) {
    getElement().setProperty("slider", visible);
  }

  public void setSlider(String el, Direction direction, Double maxRatio) {
    JsonObject options = Json.createObject();
    if (el != null) {
      options.put("el", el);
    }
    if (direction != null) {
      options.put("direction", direction.value);
    }
    if (maxRatio != null) {
      options.put("maxRatio", maxRatio);
    }
    getElement().setPropertyJson("slider", options);
  }

  public boolean isSlider() {
    return getElement().getProperty("slider", false);
  }

  public void setZoomer(boolean visible) {
    getElement().setProperty("zoomer", visible);
  }

  public void setZoomer(String inEl, String outEl, boolean disableOnBounds) {
    JsonObject options = Json.createObject();
    if (inEl != null) {
      options.put("inEl", inEl);
    }
    if (outEl != null) {
      options.put("outEl", outEl);
    }
    options.put("disableOnBounds", disableOnBounds);
    getElement().setPropertyJson("zoomer", options);
  }

  public boolean isZoomer() {
    return getElement().getProperty("zoomer", false);
  }

  public Fill getFill() {
    return Fill.valueOf(getElement().getProperty("fill", Fill.NONE.value));
  }

  public void setFill(Fill fill) {
    Objects.requireNonNull(fill);
    getElement().setProperty("fill", fill.value);
  }

  public void setDraggable(boolean draggable) {
    getElement().setProperty("draggable", draggable);
  }

  public boolean isDraggable() {
    return getElement().getProperty("draggable", false);
  }

  public void setWheelable(boolean wheelable) {
    getElement().setProperty("wheelable", wheelable);
  }

  public boolean isWheelable() {
    return getElement().getProperty("wheelable", false);
  }

  public void setPinchable(boolean pinchable) {
    getElement().setProperty("pinchable", pinchable);
  }

  public boolean isPinchable() {
    return getElement().getProperty("pinchable", false);
  }

  public void setBounds(boolean bounds) {
    getElement().setProperty("bounds", bounds);
  }

  public boolean isBounds() {
    return getElement().getProperty("bounds", false);
  }

  public void setZoomRatio(double value) {
    getElement().setProperty("zoomRatio", value);
  }

  public double getZoomRatio() {
    return getElement().getProperty("zoomRatio", 0.1d);
  }

  public void setMaxRatio(double value) {
    getElement().setProperty("maxRatio", value);
  }

  public double getMaxRatio() {
    return getElement().getProperty("maxRatio", 1);
  }

  public void setHeight(String value) {
    getElement().setProperty("height", value);
  }

  public String getHeight() {
    return getElement().getProperty("height");
  }

  public String getContainerData2() {
    return getElement().getProperty("containerData");
  }

  public void zoom(double ratio) {
    getElement().callJsFunction("zoom", ratio);
  }

  public void move(double x, double y) {
    getElement().callJsFunction("move", x, y);
  }

  public void moveTo(double x, double y) {
    getElement().callJsFunction("moveTo", x, y);
  }

  public void reset() {
    getElement().callJsFunction("reset");
  }

  public void getContainerData(SerializableConsumer<ContainerData> consumer) {
    getElement().executeJs("return this.containerData").then(value -> {
      JsonObject data = Json.parse(value.toJson());
      ContainerData containerData = new ContainerData();
      containerData.setWidth(data.getNumber("width"));
      containerData.setHeight(data.getNumber("height"));
      containerData.setAspectRatio(data.getNumber("width"));
      consumer.accept(containerData);
    });
  }

  public Registration addReadyListener(ComponentEventListener<ReadyEvent> listener) {
    return addListener(ReadyEvent.class, listener);
  }

  public Registration addZoomListener(ComponentEventListener<ZoomEvent> listener) {
    return addListener(ZoomEvent.class, listener);
  }

  public Registration addWheelListener(ComponentEventListener<WheelEvent> listener) {
    return addListener(WheelEvent.class, listener);
  }

  public Registration addDragStartListener(ComponentEventListener<DragStartEvent> listener) {
    return addListener(DragStartEvent.class, listener);
  }

  public Registration addDragListener(ComponentEventListener<DragEvent> listener) {
    return addListener(DragEvent.class, listener);
  }

  public Registration addDragEndListener(ComponentEventListener<DragEndEvent> listener) {
    return addListener(DragEndEvent.class, listener);
  }

  public Registration addSlideStartListener(ComponentEventListener<SlideStartEvent> listener) {
    return addListener(SlideStartEvent.class, listener);
  }

  public Registration addSlideListener(ComponentEventListener<SlideEvent> listener) {
    return addListener(SlideEvent.class, listener);
  }

  public Registration addSlideEndListener(ComponentEventListener<SlideEndEvent> listener) {
    return addListener(SlideEndEvent.class, listener);
  }

  public Registration addPinchStartListener(ComponentEventListener<PinchStartEvent> listener) {
    return addListener(PinchStartEvent.class, listener);
  }

  public Registration addPinchListener(ComponentEventListener<PinchEvent> listener) {
    return addListener(PinchEvent.class, listener);
  }

  public Registration addPinchEndListener(ComponentEventListener<PinchEndEvent> listener) {
    return addListener(PinchEndEvent.class, listener);
  }

  public Registration addResizeListener(ComponentEventListener<ResizeEvent> listener) {
    return addListener(ResizeEvent.class, listener);
  }

}
