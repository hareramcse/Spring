package com.hs.authorizationserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Value("${config.oauth2.clientid}")
	private String clientid;

	@Value("${config.oauth2.clientSecret}")
	private String clientSecret;

	private String privateKey="-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpQIBAAKCAQEAz9OYWoEPhYkpxJaMAG0qlIoQfTyvrWB8V51lSejhujeytpdX\r\n" + 
			"gcrtiKx7OqBDvfyoac8o8C0MDp/uA6qltislEvtpj3Uoa+PLOnuF3WHZxioh1B2h\r\n" + 
			"DBWrVXsM4KDDbi3DcdKroOusUTJp49ZNp/N/ogCdrkkPSGhtZqWnM3iBGarL0yBR\r\n" + 
			"czksiMjHIXQ6yukybjci0a4n5vwDRHQrsa+/HFODF5y5WImnaTUM+xs5BHlX9ITW\r\n" + 
			"JpbFZnbsba2ch7823iX2k6S+oSoWJoPWlbs7UqQAitKW6eHXcxK4GGUA+aoWrJOU\r\n" + 
			"K4CkOQRRkLZS1wO9MIv7fcFS2hL9WiOd/qZ9twIDAQABAoIBAQCERjiB4gLvCw36\r\n" + 
			"gZc57kFbqUQ1cZqezvpVCWieUgrZavnkHQ8lHPNyOdV0yuxSz6yhK6jytLkn1cK3\r\n" + 
			"srUl7O/71Oiq1faUNLf00QmyjcT9y3Mcfn8yIdia32ai0a+RQK9UkrGKCnXyeBPN\r\n" + 
			"X2itrN1fTuUNOJLxkwWOf4Cb0ebZXiTfgI4FHXeBoK6sH4tLtME+wJlR4jm3zj1a\r\n" + 
			"JlKrPS/THimqMG2Z/Px3oZjuNqQoAyHZWb6bYMsK7VVghk3i9mlUbjtHdAgfZyyq\r\n" + 
			"kdJIhab5qG1rpduqKodeKshLR7hoKziKXjplYYc52mQYgcRwIUujixpStjbBNWTx\r\n" + 
			"3YR4A++BAoGBAPzpeElVUVzgvNdgK3ZumBzUMUPBkcFbijOgXKkOeFKK5dgg1BTq\r\n" + 
			"Jt+jgtX1zMu1KIgRh0eFXjiGPpA5VH0nworsijryc85mrbbIRouZBg2EHXDn6uhk\r\n" + 
			"IMtrWjThF3ticDVdELayeVnEl4RRlJX/jF7Cv0NSIBatFZ8XK5bxSazVAoGBANJd\r\n" + 
			"M3ykXXjbDxO2/AeOfsPIKg56F8/yZ5Ei14FZ9lmEFf9cv6iW+XkvqasXR3DBRyDS\r\n" + 
			"nlryVd8w/CnXrTBnhZTNCFc8vVzpYRkMWi6kk4AZpm6E1ihij74bP7C+B3wQ9+Mk\r\n" + 
			"XHDYNOAJk5oqkpW/X29KA7kRT0fQzmRnSWTlDNZbAoGBAKi2+qY5AyDpTzCCGBxW\r\n" + 
			"ZKT+rKqdi6UocsZD2OxyGbQ+slt+ctxM44fW5SXkZi0SeapjvxHoF1WvwDan+APR\r\n" + 
			"rUbNytzN9pEfEA1aBy2C9HW5x4YVQDCs8u+gernB2s9VeLMwlUC7SJaovcboQS3b\r\n" + 
			"HwHr+nlnZL6b0kuhwlNcPSDNAoGAPQ7MOOtELiX29FPyRwc3CAB7JISPkM3c/aVN\r\n" + 
			"Q8Am3siV0e7LulVHx8LrzjsNg062JHK9f8xC3GtNZZ+yJxCFFzGDsD5zABrFEg4h\r\n" + 
			"xtQB8/Z99iocNtCUxCsIi3Y8mega6xTMt/wpLLXa365nqYtIWfbzqa26gy+6GC5a\r\n" + 
			"gSxyU20CgYEAqo8TFMNZPRpYP84sKIZNadSpfel4+j51op80XWLY75t77w6ym8XL\r\n" + 
			"ldLIaOI/JrBjWeLA38s6uUQTZzdcSlZdxLHs6pP5BPZbuQilW1bQMaYH+IP1kIVX\r\n" + 
			"ya/d+5AI9ntcGzJbUbsqtVLfblIXN5y8AR0C/kK9Ms1xi6+yzmXuFmA=\r\n" + 
			"-----END RSA PRIVATE KEY-----";

	private String publicKey="-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz9OYWoEPhYkpxJaMAG0q\r\n" + 
			"lIoQfTyvrWB8V51lSejhujeytpdXgcrtiKx7OqBDvfyoac8o8C0MDp/uA6qltisl\r\n" + 
			"Evtpj3Uoa+PLOnuF3WHZxioh1B2hDBWrVXsM4KDDbi3DcdKroOusUTJp49ZNp/N/\r\n" + 
			"ogCdrkkPSGhtZqWnM3iBGarL0yBRczksiMjHIXQ6yukybjci0a4n5vwDRHQrsa+/\r\n" + 
			"HFODF5y5WImnaTUM+xs5BHlX9ITWJpbFZnbsba2ch7823iX2k6S+oSoWJoPWlbs7\r\n" + 
			"UqQAitKW6eHXcxK4GGUA+aoWrJOUK4CkOQRRkLZS1wO9MIv7fcFS2hL9WiOd/qZ9\r\n" + 
			"twIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(clientid).secret(clientSecret).scopes("read", "write")
				.authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(3600)
				.refreshTokenValiditySeconds(18000);
	}
	
	
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
       endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore()).accessTokenConverter(tokenEnhancer());
    }

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(tokenEnhancer());
	}

	@Bean
	public JwtAccessTokenConverter tokenEnhancer() {
		JwtAccessTokenConverter converter = new CustomTokenEnhancer();
		converter.setSigningKey(privateKey);
		converter.setVerifierKey(publicKey);
		return converter;
	}
		
}
