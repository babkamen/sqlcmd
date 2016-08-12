package com.bonnenuit.controler;

import com.bonnenuit.model.DatabaseManager;
import com.bonnenuit.view.View;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;


/**
 * Created by babkamen on 12.08.2016.
 */
public class ControllerTest {
    @Mock
    View reader;
    PrintStream out;
    Controller controller;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DatabaseManager manager = new DatabaseManager();
    public ControllerTest() {
        manager.connect("tantor.db.elephantsql.com:5432/fgjkvtdu" , "fgjkvtdu",
                "6tfdpYqUom7t0Xr5pNUZNRcEUtziT3D_");
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        out = System.out;
        System.setOut(ps);
    }

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        controller = new Controller(reader, manager,false);
        baos.reset();
    }
    @Test
    public void whenShowTablesProductShouldBeInResponse() throws Exception {
        //prepare
        when(reader.read()).thenReturn("tablelist");
        //do
        controller.controllerRunner();
        //verify
        String content = new String(baos.toByteArray(), StandardCharsets.UTF_8);
        out.println("Actual=\n"+content);
        assertThat(content, CoreMatchers.containsString("product"));

    }

}