package learn.inheritance;

//Licensed to the Software Freedom Conservancy (SFC) under one
//or more contributor license agreements.  See the NOTICE file
//distributed with this work for additional information
//regarding copyright ownership.  The SFC licenses this file
//to you under the Apache License, Version 2.0 (the
//"License"); you may not use this file except in compliance
//with the License.  You may obtain a copy of the License at
//
//http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing,
//software distributed under the License is distributed on an
//"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
//KIND, either express or implied.  See the License for the
//specific language governing permissions and limitations
//under the License.


import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
* The main interface to use for testing, which represents an idealised web browser. The methods in
* this class fall into three categories:
* <ul>
* <li>Control of the browser itself</li>
* <li>Selection of {@link WebElement}s</li>
* <li>Debugging aids</li>
* </ul>
* <p>
* Key methods are {@link WebDriver#get(String)}, which is used to load a new web page, and the
* various methods similar to {@link WebDriver#findElement(By)}, which is used to find
* {@link WebElement}s.
* <p>
* Currently, you will need to instantiate implementations of this class directly. It is hoped that
* you write your tests against this interface so that you may "swap in" a more fully featured
* browser when there is a requirement for one.
* <p>
* Note that all methods that use XPath to locate elements will throw a {@link RuntimeException}
* should there be an error thrown by the underlying XPath engine.
*/
public interface WebDriver extends SearchContext {
 // Navigation

 /**
  * Load a new web page in the current browser window. This is done using an HTTP GET operation,
  * and the method will block until the load is complete. This will follow redirects issued either
  * by the server or as a meta-redirect from within the returned HTML. Should a meta-redirect
  * "rest" for any duration of time, it is best to wait until this timeout is over, since should
  * the underlying page change whilst your test is executing the results of future calls against
  * this interface will be against the freshly loaded page. Synonym for
  * {@link org.openqa.selenium.WebDriver.Navigation#to(String)}.
  *
  * @param url The URL to load. It is best to use a fully qualified URL
  */
 void get(String url);

 /**
  * Get a string representing the current URL that the browser is looking at.
  *
  * @return The URL of the page currently loaded in the browser
  */
 String getCurrentUrl();

 // General properties

 /**
  * The title of the current page.
  *
  * @return The title of the current page, with leading and trailing whitespace stripped, or null
  *         if one is not already set
  */
 String getTitle();

 /**
  * Find all elements within the current page using the given mechanism.
  * This method is affected by the 'implicit wait' times in force at the time of execution. When
  * implicitly waiting, this method will return as soon as there are more than 0 items in the
  * found collection, or will return an empty list if the timeout is reached.
  *
  * @param by The locating mechanism to use
  * @return A list of all {@link WebElement}s, or an empty list if nothing matches
  * @see org.openqa.selenium.By
  * @see org.openqa.selenium.WebDriver.Timeouts
  */
  void findElements();


 /**
  * Find the first {@link WebElement} using the given method.
  * This method is affected by the 'implicit wait' times in force at the time of execution.
  * The findElement(..) invocation will return a matching row, or try again repeatedly until
  * the configured timeout is reached.
  *
  * findElement should not be used to look for non-present elements, use {@link #findElements(By)}
  * and assert zero length response instead.
  *
  * @param by The locating mechanism
  * @return The first matching element on the current page
  * @throws NoSuchElementException If no matching elements are found
  * @see org.openqa.selenium.By
  * @see org.openqa.selenium.WebDriver.Timeouts
  */
  void findElement();

 // Misc

 /**
  * Get the source of the last loaded page. If the page has been modified after loading (for
  * example, by Javascript) there is no guarantee that the returned text is that of the modified
  * page. Please consult the documentation of the particular driver being used to determine whether
  * the returned text reflects the current state of the page or the text last sent by the web
  * server. The page source returned is a representation of the underlying DOM: do not expect it to
  * be formatted or escaped in the same way as the response sent from the web server. Think of it as
  * an artist's impression.
  *
  * @return The source of the current page
  */
 String getPageSource();

 /**
  * Close the current window, quitting the browser if it's the last window currently open.
  */
 void close();

 /**
  * Quits this driver, closing every associated window.
  */
 void quit();

 /**
  * Return a set of window handles which can be used to iterate over all open windows of this
  * WebDriver instance by passing them to {@link #switchTo()}.{@link Options#window()}
  *
  * @return A set of window handles which can be used to iterate over all open windows.
  */
 Set<String> getWindowHandles();

 /**
  * Return an opaque handle to this window that uniquely identifies it within this driver instance.
  * This can be used to switch to this window at a later date
  *
  * @return the current window handle
  */
 String getWindowHandle();

}
