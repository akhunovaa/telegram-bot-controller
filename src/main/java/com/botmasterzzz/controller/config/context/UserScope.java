package com.botmasterzzz.controller.config.context;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserScope implements Scope {

    private final Map<UserContext, Map<String, Object>> scopedObjects
            = Collections.synchronizedMap(new HashMap<UserContext, Map<String, Object>>());

    private final Map<UserContext, Map<String, Runnable>> destructionCallbacks
            = Collections.synchronizedMap(new HashMap<UserContext, Map<String, Runnable>>());


    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> contextScopedObjects = getContextScopedObjects();

        if (!contextScopedObjects.containsKey(name)) {
            contextScopedObjects.put(name, objectFactory.getObject());
        }

        return contextScopedObjects.get(name);
    }

    @Override
    public Object remove(String name) {
        Map<String, Runnable> contextDestructionCallbacks = getContextDestructionCallbacks();
        Map<String, Object> contextScopedObjects = getContextScopedObjects();

        contextDestructionCallbacks.remove(name);
        return contextScopedObjects.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable runnable) {
        Map<String, Runnable> contextDestructionCallbacks = getContextDestructionCallbacks();
        contextDestructionCallbacks.put(name, runnable);
    }

    @Override
    public Object resolveContextualObject(String name) {
        return null;
    }

    @Override
    public String getConversationId() {
        return "telegram";
    }

    private Map<String, Object> getContextScopedObjects() {
        UserContext context = UserContextHolder.currentContext();

        if (!scopedObjects.containsKey(context)) {
            scopedObjects.put(context, new HashMap<>());
        }

        return scopedObjects.get(context);
    }

    private Map<String, Runnable> getContextDestructionCallbacks() {
        UserContext context = UserContextHolder.currentContext();

        if (!destructionCallbacks.containsKey(context)) {
            destructionCallbacks.put(context, new HashMap<>());
        }

        return destructionCallbacks.get(context);
    }

}
