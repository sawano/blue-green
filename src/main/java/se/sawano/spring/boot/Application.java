/*
 * Copyright 2017 Daniel Sawano
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package se.sawano.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import se.sawano.java.commons.lang.validate.dbc.Invariant;

import java.net.InetAddress;
import java.net.UnknownHostException;


@SpringBootApplication
@Controller
public class Application implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final Environment environment;

    @Autowired
    public Application(final Environment environment) {
        this.environment = Invariant.notNull(environment);
    }

    @RequestMapping("/")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("color", colorMode());
        modelMap.addAttribute("host", serverId());
        return "index-template";
    }

    private String serverId() {
        try {
            final InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostName() + ":" + localHost.getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private String colorMode() {
        final String color = environment.getProperty("color");
        return "green".equals(color) ? "green" : "blue";
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        System.out.println("Running in '" + colorMode() + "' mode");
    }
}
