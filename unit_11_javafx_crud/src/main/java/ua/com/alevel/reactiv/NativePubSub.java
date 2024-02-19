package ua.com.alevel.reactiv;

import ua.com.alevel.config.LoaderPage;

import java.util.function.Consumer;

public class NativePubSub {

    private static final NativePubSub instance = new NativePubSub();
    private Consumer<LoaderPage> publisher;
    private Consumer<Boolean> publisherGroup;

    private NativePubSub() { }

    public static NativePubSub getInstance() {
        return instance;
    }

    public void publish(LoaderPage page) {
        publisher.accept(page);
    }

    public void publishGroup(Boolean publishGroup) {
        publisherGroup.accept(publishGroup);
    }

    public void subscribe(Consumer<LoaderPage> consumer) {
        this.publisher = consumer;
    }

    public void subscribeGroup(Consumer<Boolean> consumer) {
        this.publisherGroup = consumer;
    }
}
