package pl.lrozek.containers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContainersTest {

    @ValueSource(strings = { "9.2.4.0-r1" })
    @ParameterizedTest
    public void itShouldStartContainer(String tag) {
        // given
        log.info("about to create instance for {}", tag);
        GenericContainer<?> ibmMq = new GenericContainer<>(DockerImageName.parse("ibmcom/mq:" + tag));

        // when
        log.info("about to start container");
        ibmMq.start();
        log.info("container started");

        // then
        assertThat(ibmMq.isRunning(), equalTo(true));
    }

}
