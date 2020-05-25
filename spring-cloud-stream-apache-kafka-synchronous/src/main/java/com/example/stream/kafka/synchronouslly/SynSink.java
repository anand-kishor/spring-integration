package com.example.stream.kafka.synchronouslly;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.binder.PollableMessageSource;

public interface SynSink {
	@Input("input")
	PollableMessageSource source()
;
}
