package com.project.apiGateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@Configuration
public class SpringCloudGatewayRouting {
    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder)
    {
        return (RouteLocator) builder.routes()
                .route("Train-management-service",r-> r.path("/train/**").uri("lb://Train-management-service"))
                .route("Admin-management-service",r->r.path("/admin/**","/admin/auth/test").uri("lb://Admin-management-service"))
                .route("User-management-service",r->r.path("/user/**","/user/auth/validate").uri("lb://User-management-service"))
                .route("Booking-management-service",r->r.path("/book/**").uri("lb://Booking-management-service"))
                .build();
    }

}