package com.cg.creditcardpayment.config;
/**
* <h1>SecurityConfig</h1>
* The Securityconfig is the configuration which provides Bean for the jasypt encryptor
* the user can provide Account details and send the details to entity with help of Parser
* and perform some Validations.
* <p>
* 
*
* @author  P Venkata Sai Reddy
* @version 1.0
* @since   2021-03-31 
*/
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

	/**
	 * This is the bean for jasypt encryptor
	 * @return config which is object of SimpleStringPBEConfig
	 */
    @Bean
    @ConfigurationProperties("jasypt.encryptor")
    public SimpleStringPBEConfig jasypConfig() {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        return config;
    }

    /**
     * Default Bean for jasyptEncryptor
     * @return encryptor which is object of PooledPBEStringEncryptor
     */
    @Bean
    public StringEncryptor jasyptEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(jasypConfig());
        return encryptor;
    }
}