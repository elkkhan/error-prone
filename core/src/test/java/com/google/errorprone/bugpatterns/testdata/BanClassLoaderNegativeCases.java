/*
 * Copyright 2023 The Error Prone Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.errorprone.bugpatterns.testdata;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

class BanClassLoaderPositiveCases {
  public static final Class<?> overrideURLClassLoader()
      throws ClassNotFoundException, MalformedURLException {
    URLClassLoader loader =
        new URLClassLoader(new URL[] {new URL("eval.com")}) {
          @Override
          protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            return super.loadClass(name);
          }
        };
    return loader.loadClass("BadClass");
  }

  private class NotClassLoader {
    protected void loadClass() {}
  }
}