package threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyThreadLocal <T>{

    private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<>());

    public void set(T t) {
        this.container.put(Thread.currentThread(), t);
    }

    public T get() {
        Thread thread = Thread.currentThread();
        T value = this.container.get(thread);
        // 容器中没有对应key 初始化对应key
        if (value==null&&!container.containsKey(thread)) {
            value = initValue();
            this.container.put(thread, value);
        }
        return value;
    }

    protected T initValue() {
        return null;
    }

    public void remove() {
        this.container.remove(Thread.currentThread());
    }
}
