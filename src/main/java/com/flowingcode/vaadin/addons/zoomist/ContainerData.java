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

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * Container data representation.
 */
@Getter
@Setter
public class ContainerData implements Serializable {

  private static final long serialVersionUID = 1L;

  private double width;
  private double height;
  private double aspectRatio;

}
