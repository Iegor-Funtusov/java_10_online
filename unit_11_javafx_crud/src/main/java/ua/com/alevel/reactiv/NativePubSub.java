package ua.com.alevel.reactiv;

import ua.com.alevel.config.LoaderPage;

import java.util.function.Consumer;

public class NativePubSub {

    private static final NativePubSub instance = new NativePubSub();
    private Consumer<LoaderPage> publisher;

    private NativePubSub() { }

    public static NativePubSub getInstance() {
        return instance;
    }

    public void publish(LoaderPage page) {
        publisher.accept(page);
    }

    public void subscribe(Consumer<LoaderPage> consumer) {
        this.publisher = consumer;
    }
}
