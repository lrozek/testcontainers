package pl.lrozek.containers;

import static com.github.dockerjava.core.DefaultDockerClientConfig.createDefaultConfigBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DockerTest {

    private DockerClientConfig config = createDefaultConfigBuilder().build();

    private DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
            .dockerHost(config.getDockerHost())
            .sslConfig(config.getSSLConfig())
            .maxConnections(100)
            .connectionTimeout(Duration.ofSeconds(30))
            .responseTimeout(Duration.ofSeconds(45))
            .build();;

    private DockerClient dockerClient = DockerClientImpl.getInstance(config, httpClient);

    @Test
    public void itShouldListImages() {
        // given

        // when
        log.info("about to list images");
        List<Image> images = dockerClient.listImagesCmd().exec();
        log.info("images listed");

        // then
        assertThat(images, not(empty()));
        images.forEach(image -> log.info("following image found: {}", image));
    }

    @Test
    public void itShouldListContainers() {
        // given

        // when
        log.info("about to list containers");
        List<Container> containers = dockerClient.listContainersCmd().exec();
        log.info("containers listed");

        // then
        assertThat(containers, not(empty()));
        containers.forEach(container -> log.info("following container found: {}", container));
    }

}
