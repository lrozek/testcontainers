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

    @ValueSource(strings = { "ibmcom/mq:9.2.4.0-r1", "mongo:5.0.9-focal", "mongo:6.0.2-focal", "gcr.io/distroless/java17:nonroot" })
    @ParameterizedTest
    public void itShouldStartContainer(String image) {
        // given
        log.info("about to create instance for {}", image);
        GenericContainer<?> ibmMq = new GenericContainer<>(DockerImageName.parse(image));

        // when
        log.info("about to start container");
        ibmMq.start();
        log.info("container started");

        // then
        assertThat(ibmMq.isRunning(), equalTo(true));
    }

}
