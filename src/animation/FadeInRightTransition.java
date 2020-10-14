package animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.scene.Node;
import javafx.util.Duration;

public class FadeInRightTransition extends animation.CachedTimelineTransition {
    
    public FadeInRightTransition(final Node node) {
        super(
            node,
            TimelineBuilder.create()
                .keyFrames(
                    new KeyFrame(Duration.millis(0),    
                    new KeyValue(node.opacityProperty(), 0, WEB_EASE),
                    new KeyValue(node.translateXProperty(), 100, WEB_EASE)
                    ),
                    new KeyFrame(Duration.millis(500),    
                    new KeyValue(node.opacityProperty(), 1, WEB_EASE),
                    new KeyValue(node.translateXProperty(), 0, WEB_EASE)
                    )
                )
                .build()
            );
        setCycleDuration(Duration.seconds(0.5));
        setDelay(Duration.seconds(0.2));
        node.toFront();
    }
}
