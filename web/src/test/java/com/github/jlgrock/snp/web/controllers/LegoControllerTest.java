package com.github.jlgrock.snp.web.controllers;

import com.github.jlgrock.snp.apis.connection.configuration.WebConfiguration;
import com.github.jlgrock.snp.web.configuration.ApplicationConfig;
import com.github.jlgrock.snp.web.configuration.ApplicationObjectMapper;
import com.github.jlgrock.snp.web.configuration.JacksonConfig;
import io.dropwizard.jersey.jackson.JacksonMessageBodyProvider;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTestNg;
import org.glassfish.jersey.test.TestProperties;
import org.jvnet.testing.hk2testng.HK2;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.validation.Validation;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@HK2(populate = false)
public class LegoControllerTest extends JerseyTestNg.ContainerPerClassTest {

    WebConfiguration webconfiguration = new WebConfiguration() {
        @Override
        public Path fileLocation() {
            return Mockito.mock(Path.class);
        }
    };

    @Mock
    Path path;

    @Mock
    MultiPartFileUtils multiPartFileUtils;

    @BeforeMethod
    public void setUpTests() throws Exception {
        // Required to make this work on TestNG
        MockitoAnnotations.initMocks(this);
    }

    @Override
    protected void configureClient(ClientConfig config) {
        config.register(MultiPartFeature.class);
        config.register(
                new JacksonMessageBodyProvider(JacksonConfig.newObjectMapper(), Validation.buildDefaultValidatorFactory().getValidator())).
                register(ApplicationObjectMapper.class);
    }

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        ResourceConfig application = ApplicationConfig.createApp();

        // register the WebConfiguration in HK2
        application.registerInstances(new AbstractBinder() {
            @Override
            protected void configure() {
                bindFactory(new Factory<WebConfiguration>() {
                    @Override
                    public WebConfiguration provide() {
                        return webconfiguration;
                    }

                    @Override
                    public void dispose(WebConfiguration instance) {
                    }
                }).to(WebConfiguration.class);
            }
        });

        // register the WebConfiguration in HK2
        application.registerInstances(new AbstractBinder() {
            @Override
            protected void configure() {
                bindFactory(new Factory<MultiPartFileUtils>() {
                    @Override
                    public MultiPartFileUtils provide() {
                        return multiPartFileUtils;
                    }

                    @Override
                    public void dispose(MultiPartFileUtils instance) {
                    }
                }).to(MultiPartFileUtils.class);
            }
        });

        //return all of the rest endpoints
        return application;
    }

    @Test
    public void testPartWithFile() {
        final String value = "CONTENT";
        final WebTarget target = target().path("lego/upload");

        final InputStream inputStream = new ByteArrayInputStream("CONTENT_STREAM".getBytes(StandardCharsets.UTF_8));
        final FormDataMultiPart mp = new FormDataMultiPart();
        final FormDataContentDisposition formDataContentDisposition = FormDataContentDisposition
                .name("file")
                .fileName("test.txt")
                .size(value.getBytes().length)
                .build();
        final FormDataBodyPart formDataBodyPart = new FormDataBodyPart(formDataContentDisposition, inputStream, MediaType.APPLICATION_OCTET_STREAM_TYPE);
        mp.bodyPart(formDataBodyPart);

        final Entity<FormDataMultiPart> form = Entity.entity(mp, MediaType.MULTIPART_FORM_DATA_TYPE);
        final Response response = target.request().post(form);

        // Verify that this writes to disk
        Mockito.verify(multiPartFileUtils).writeToFile(Mockito.any(), Mockito.any());

        //Verify the "OK" return type
        Assert.assertEquals(response.getStatus(), 200);
    }


    @Test
    public void testMultiPartFileEmpty() {
        final WebTarget target = target().path("lego/upload");

        final FormDataMultiPart mp = new FormDataMultiPart();
        final FormDataContentDisposition formDataContentDisposition = FormDataContentDisposition
                .name("file")
                .fileName("test.txt")
                .size(0)
                .build();
        final FormDataBodyPart formDataBodyPart = new FormDataBodyPart(formDataContentDisposition, new ByteArrayInputStream(new byte[] {}), MediaType.APPLICATION_OCTET_STREAM_TYPE);
        mp.bodyPart(formDataBodyPart);

        final Entity<FormDataMultiPart> form = Entity.entity(mp, MediaType.MULTIPART_FORM_DATA_TYPE);
        final Response response = target.request().post(form);

        Assert.assertEquals(response.getStatus(), 200);
    }

}
