package sample3.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 *
 * @author dariaman.siagian
 */
@Configuration
@EnableWebMvc
@ComponentScan({"sample3"})
public class WebConfig {
    
}
