/** 
 * [SIMINOV FRAMEWORK]
 * Copyright [2013] [Siminov Software Solution LLP|support@siminov.com]
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
 **/


package siminov.hybrid.events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import siminov.orm.events.IDatabaseEvents;
import siminov.orm.events.ISiminovEvents;
import siminov.orm.log.Log;
import siminov.orm.utils.ClassUtils;


/**
 * Exposes methods to GET and SET Application Event Notifiers
 *
 */
public class EventHandler {

	private static EventHandler eventHandler = null;
	
	private Collection<String> events = new ArrayList<String> ();
	
	private ISiminovEvents siminovEvents = null;
	private IDatabaseEvents databaseEvents = null;
	
	
	private EventHandler() {
	
	}

	/**
	 * It provides an instance of EventHandler class.
	 * 
	 * @return EventHandler instance.
	 */
	public static final EventHandler getInstance() {
		
		if(eventHandler == null) {
			eventHandler = new EventHandler();
		}
		
		return eventHandler;
	}

	
	/**
	 * Check whether Application is registered for any of Events.
	 * @return true/false.
	 */
	public boolean doesEventsRegistered() {
		
		if(this.events.size() > 0) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Add Application Event Notifier.
	 * @param event Event
	 */
	public void addEvent(String event) {
		events.add(event);
	}
	
	
	/**
	 * Get All Event Notifiers Registered By Application.
	 * @return All Events.
	 */
	public Iterator<String> getEvents() {
		return this.events.iterator();
	}
	
	
	/**
	 * Get ISiminov Event Notifier Defined By Application.
	 * @return ISiminov Event Notifier.
	 */
	public ISiminovEvents getSiminovEvent() {
		
		if(siminovEvents != null) {
			return siminovEvents;
		}
		
		
		Iterator<String> events = this.events.iterator();
		while(events.hasNext()) {
			String event = events.next();

			Class<?> classObject = null;
			try {
				classObject = Class.forName(event);
			} catch(Exception exception) {
				Log.logd(ClassUtils.class.getName(), "getSiminovEvent", "Exception caught while creating class object, CLASS-NAME: " + event + ", " + exception.getMessage());
			}
			
			if(classObject == null) {
				continue;
			}
			
			Object object = null;
			try {
				object = classObject.newInstance();
			} catch(Exception exception) {
				Log.logd(ClassUtils.class.getName(), "getSiminovEvent", "Exception caught while creating new instance of class, CLASS-NAME: " + event + ", " + exception.getMessage());
			}

			if(object == null) {
				continue;
			}
			
			if(object instanceof ISiminovEvents) {
				siminovEvents = (ISiminovEvents) object;
			}
			
		}
		

		return siminovEvents;
	}
	
	
	/**
	 * Get IDatabase Event Notifier Registered By Application.
	 * @return IDatabseEvent Notifier.
	 */
	public IDatabaseEvents getDatabaseEvents() {
		
		if(databaseEvents != null) {
			return databaseEvents;
		}
		
		
		Iterator<String> events = this.events.iterator();
		while(events.hasNext()) {
			String event = events.next();

			Class<?> classObject = null;
			try {
				classObject = Class.forName(event);
			} catch(Exception exception) {
				Log.loge(ClassUtils.class.getName(), "getDatabaseEvents", "Exception caught while creating class object, CLASS-NAME: " + event + ", " + exception.getMessage());
			}
			
			if(classObject == null) {
				continue;
			}
			
			Object object = null;
			try {
				object = classObject.newInstance();
			} catch(Exception exception) {
				Log.logd(ClassUtils.class.getName(), "getDatabaseEvents", "Exception caught while creating new instance of class, CLASS-NAME: " + event + ", " + exception.getMessage());
			}

			if(object == null) {
				continue;
			}
			
			if(object instanceof IDatabaseEvents) {
				databaseEvents = (IDatabaseEvents) object;
			}
			
		}
		

		return databaseEvents;
	}
	
}
