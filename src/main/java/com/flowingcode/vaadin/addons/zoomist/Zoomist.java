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
 * This component is a wrapper for Zoomist Component
 * (https://npm.io/package/zoomist)
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

  /**
   * Creates a new instance of Zoomist for the specified source url.
   * 
   * @param src the source url
   */
  public Zoomist(String src) {
    setSrc(src);
  }

  /**
   * Sets the source url of image.
   * 
   * @param src the source url
   */
  public void setSrc(String src) {
    getElement().setProperty("src", src);
  }

  /**
   * Sets whether slider is visible or not. If true slider will be visible and
   * initialized with default options. *
   * 
   * @param visible if true, slider is visible
   *                if false, slider is not visible
   */
  public void setSlider(boolean visible) {
    getElement().setProperty("slider", visible);
  }

  /**
   * Sets slider's options.
   * 
   * @param el        string with CSS selector or querySelector of slider
   * @param direction direction of the slider
   * @param maxRatio  max ratio of the image
   */
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

  /**
   * Returns if slider is visible
   * 
   * @return true, slider is visible
   *         false, slider is not visible
   */
  public boolean isSlider() {
    return getElement().getProperty("slider", false);
  }

  /**
   * Sets whether zoomer is visible or not. If true zoomer will be visible and
   * initialized with default options.
   * 
   * @param visible if true, zoomer is visible
   *                if false, zoomer is not visible
   */
  public void setZoomer(boolean visible) {
    getElement().setProperty("zoomer", visible);
  }

  /**
   * Sets zoomer's options.
   * 
   * @param inEl            string with CSS selector or querySelector of zoom in
   *                        element
   * @param outEl           string with CSS selector or querySelector of zoom out
   *                        element
   * @param disableOnBounds zoomer will be disabled when image can't be larger or
   *                        smalle
   */
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

  /**
   * Returns if zoomer is visible
   * 
   * @return true, zoomer is visible
   *         false, zoomer is not visible
   */
  public boolean isZoomer() {
    return getElement().getProperty("zoomer", false);
  }

  /**
   * Returns the image fill type.
   * 
   * @return the image fill type
   */
  public Fill getFill() {
    return Fill.valueOf(getElement().getProperty("fill", Fill.NONE.value));
  }

  /**
   * Sets the image fill type. Possible values 'cover', 'contain', 'none'.
   * 
   * @param fill the image fill type
   */
  public void setFill(Fill fill) {
    Objects.requireNonNull(fill);
    getElement().setProperty("fill", fill.value);
  }

  /**
   * Set if image is draggable or not
   * 
   * @param draggable if true, image is draggable
   *                  if false, image is not draggable
   */
  public void setDraggable(boolean draggable) {
    getElement().setProperty("draggable", draggable);
  }

  /**
   * Returns if image is draggable or not.
   * 
   * @return true, image is draggable
   *         false, image is not draggable
   */
  public boolean isDraggable() {
    return getElement().getProperty("draggable", false);
  }

  /**
   * Sets whether image is zoomable when mouse wheeling.
   * 
   * @param wheelable if true, image is wheelable
   *                  if false, image is not wheelable
   */
  public void setWheelable(boolean wheelable) {
    getElement().setProperty("wheelable", wheelable);
  }

  /**
   * Returns if image is wheelable or not.
   * 
   * @return true, image is wheelable
   *         false, image is not wheelable
   */
  public boolean isWheelable() {
    return getElement().getProperty("wheelable", false);
  }

  /**
   * Sets whether image is pinchable when pinching.
   * This feature only works on mobile device.
   * 
   * @param pinchable true, image is pinchable
   *                  if false, image is not pinchable
   */
  public void setPinchable(boolean pinchable) {
    getElement().setProperty("pinchable", pinchable);
  }

  /**
   * Returns if image is pinchable or not.
   * 
   * @return true, image is pinchable
   *         false, image is not pinchable
   */
  public boolean isPinchable() {
    return getElement().getProperty("pinchable", false);
  }

  /**
   * Sets whether image can be drag out of bounds or not.
   * 
   * @param bounds if true, image can be drag out of bounds
   *               if false, image cannot be drag out of bounds
   */
  public void setBounds(boolean bounds) {
    getElement().setProperty("bounds", bounds);
  }

  /**
   * Returnd if image can be drag out of bounds or not.
   * 
   * @return true, image can be drag out of bounds
   *         false, image cannot be drag out of bounds
   */
  public boolean isBounds() {
    return getElement().getProperty("bounds", false);
  }

  /**
   * Sets the ratio of a zoom.
   * 
   * @param value the zoom ratio
   */
  public void setZoomRatio(double value) {
    getElement().setProperty("zoomRatio", value);
  }

  /**
   * Returns the ratio of a zoom.
   * 
   * @return the zoom ratio
   */
  public double getZoomRatio() {
    return getElement().getProperty("zoomRatio", 0.1d);
  }

  /**
   * Sets the max ratio of the image.
   * 
   * @param value the image max ratio
   */
  public void setMaxRatio(double value) {
    getElement().setProperty("maxRatio", value);
  }

  /**
   * Returns the max ratio of the image.
   * 
   * @return the image max ratio
   */
  public double getMaxRatio() {
    return getElement().getProperty("maxRatio", 1);
  }

  /**
   * Sets the height of the container.
   * 
   * @param value the height of the container
   */
  public void setHeight(String value) {
    getElement().setProperty("height", value);
  }

  /**
   * Returns the height of the container.
   * 
   * @return the height of the container
   */
  public String getHeight() {
    return getElement().getProperty("height");
  }

  /**
   * Zooms the image with a relative ratio.
   * 
   * @param ratio the ratio of the zooming
   */
  public void zoom(double ratio) {
    getElement().callJsFunction("zoom", ratio);
  }

  /**
   * Moves the image with a relative position.
   * 
   * @param x x position
   * @param y y position
   */
  public void move(double x, double y) {
    getElement().callJsFunction("move", x, y);
  }

  /**
   * Moves the image with a absolute position.
   * 
   * @param x x position
   * @param y y position
   */
  public void moveTo(double x, double y) {
    getElement().callJsFunction("moveTo", x, y);
  }

  /**
   * Resets the image to initial state.
   */
  public void reset() {
    getElement().callJsFunction("reset");
  }

  /**
   * Gets the width, height and aspectRatio of the container.
   * 
   * @param consumer callback with container data
   */
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

  /**
   * Adds a {@link ReadyEvent} listener to zoomist.
   * 
   * @param listener a ready event listener
   * @return a handle for the listener
   */
  public Registration addReadyListener(ComponentEventListener<ReadyEvent> listener) {
    return addListener(ReadyEvent.class, listener);
  }

  /**
   * Adds a {@link ZoomEvent} listener to zoomist.
   * 
   * @param listener a zoom event listener
   * @return a handle for the listener
   */
  public Registration addZoomListener(ComponentEventListener<ZoomEvent> listener) {
    return addListener(ZoomEvent.class, listener);
  }

  /**
   * Adds a {@link WheelEvent} listener to zoomist.
   * 
   * @param listener a wheel event listener
   * @return a handle for the listener
   */
  public Registration addWheelListener(ComponentEventListener<WheelEvent> listener) {
    return addListener(WheelEvent.class, listener);
  }

   /**
   * Adds a {@link DragStartEvent} listener to zoomist.
   * 
   * @param listener a drag start event listener
   * @return a handle for the listener
   */
  public Registration addDragStartListener(ComponentEventListener<DragStartEvent> listener) {
    return addListener(DragStartEvent.class, listener);
  }

  /**
   * Adds a {@link DragEvent} listener to zoomist.
   * 
   * @param listener a drag event listener
   * @return a handle for the listener
   */
  public Registration addDragListener(ComponentEventListener<DragEvent> listener) {
    return addListener(DragEvent.class, listener);
  }

  /**
   * Adds a {@link DragEndEvent} listener to zoomist.
   * 
   * @param listener a drag end event listener
   * @return a handle for the listener
   */
  public Registration addDragEndListener(ComponentEventListener<DragEndEvent> listener) {
    return addListener(DragEndEvent.class, listener);
  }

   /**
   * Adds a {@link SlideStartEvent} listener to zoomist.
   * 
   * @param listener a slide start event listener
   * @return a handle for the listener
   */
  public Registration addSlideStartListener(ComponentEventListener<SlideStartEvent> listener) {
    return addListener(SlideStartEvent.class, listener);
  }

  /**
   * Adds a {@link SlideEvent} listener to zoomist.
   * 
   * @param listener a slide event listener
   * @return a handle for the listener
   */
  public Registration addSlideListener(ComponentEventListener<SlideEvent> listener) {
    return addListener(SlideEvent.class, listener);
  }

  /**
   * Adds a {@link SlideEndEvent} listener to zoomist.
   * 
   * @param listener a slide end event listener
   * @return a handle for the listener
   */
  public Registration addSlideEndListener(ComponentEventListener<SlideEndEvent> listener) {
    return addListener(SlideEndEvent.class, listener);
  }

  /**
   * Adds a {@link PinchStartEvent} listener to zoomist.
   * 
   * @param listener a pinch start event listener
   * @return a handle for the listener
   */
  public Registration addPinchStartListener(ComponentEventListener<PinchStartEvent> listener) {
    return addListener(PinchStartEvent.class, listener);
  }
 
  /**
   * Adds a {@link PinchEvent} listener to zoomist.
   * 
   * @param listener a pinch event listener
   * @return a handle for the listener
   */
  public Registration addPinchListener(ComponentEventListener<PinchEvent> listener) {
    return addListener(PinchEvent.class, listener);
  }

  /**
   * Adds a {@link PinchEndEvent} listener to zoomist.
   * 
   * @param listener a pinch end event listener
   * @return a handle for the listener
   */
  public Registration addPinchEndListener(ComponentEventListener<PinchEndEvent> listener) {
    return addListener(PinchEndEvent.class, listener);
  }

  /**
   * Adds a {@link ResizeEvent} listener to zoomist.
   * 
   * @param listener a resize event listener
   * @return a handle for the listener
   */
  public Registration addResizeListener(ComponentEventListener<ResizeEvent> listener) {
    return addListener(ResizeEvent.class, listener);
  }

}
