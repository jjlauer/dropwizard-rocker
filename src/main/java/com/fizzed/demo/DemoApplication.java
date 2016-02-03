/*
 * Copyright 2016 Fizzed, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fizzed.demo;

import com.fizzed.rocker.runtime.RockerRuntime;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DemoApplication extends Application<DemoConfiguration> {
    
    public static void main(String[] args) throws Exception {
        new DemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "demo";
    }

    @Override
    public void initialize(Bootstrap<DemoConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DemoConfiguration configuration,
                    Environment environment) {
        
        final DemoResource resource = new DemoResource(
            configuration.getTemplate(),
            configuration.getDefaultName()
        );
        environment.jersey().register(resource);
        
        environment.jersey().register(new RockerMessageBodyWriter());
        environment.jersey().register(new RockerResource());
        RockerRuntime.getInstance().setReloading(true);
    }

}