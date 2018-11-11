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
			"MIIEpAIBAAKCAQEA5N53sfPXhzKvMjcRlG2CgHq8+oZu/jyq9UOhpeyAn149C2aM\r\n" + 
			"lPXBHBCOKPtoLByj7q4VuVV9bKsPk7LQ1mylgYtoW6ATisWJ3ZC3X1tApwVDaHsa\r\n" + 
			"NU/XjtBc/h3jmXVGKwf8N+r2MLAUpkONyENS0FP9XuhaN5H7SnD/i9JSM1CAqYPY\r\n" + 
			"9RNFxJ9cnmdnYpBocCPdwNGqHGHAq+5Gjl+MvDL4LutkVgKGseeRVt4sRXyAZLaC\r\n" + 
			"vE0NnL+h2iVryPQXZd7n74rDQAcW9Wez69uwVN8ml3JRhvLujSkAi7h2ml9amVbl\r\n" + 
			"pt5Hw7i0+PTAFSGvLTTGC19jYBGn4genUeXRYwIDAQABAoIBAQCU4ifJSo/1ckJI\r\n" + 
			"D0zPWqHfhHi1HIjNuJeZ74ITlxNhGQPrC63+JRSXdimUfV+Z+pOl6/dT1ko+S9VZ\r\n" + 
			"RqTWKF1ONWsQ18R+rNxvlvnC/XDEdgcQy6wT7DmUvPURlk3+AS1BRvOLSL0YlSMJ\r\n" + 
			"ub5c/Xa88TZM7CfsKHnpRjLnOU2h9MxRptqoeNLQZjQamu/qoxw3VHFdxXNGmxIG\r\n" + 
			"e+Qc7GAhy7rQcetKdeKGebiW4rvCFN8y78ogivl7ZlaECGDw0v02FxG4ESL1jeyQ\r\n" + 
			"4Q9706k4FcQHXlEL8YCZjXw7oAEj8Fy72v7N7puuJgTO26JBgFZbzC3+k919TApE\r\n" + 
			"EF8jjLRRAoGBAP0EWE47FS7DfO6I2OYVKu4CngaDzE+2Ri8Bzk/G/3SxHvOKD+C0\r\n" + 
			"v+8UrsZsybbbTnqXBGGsrGcw4hn6dJ0M1TVgCp+lwgnoYrjFmv2oUB85n8uQibTq\r\n" + 
			"8YvdyyIqeVkHDd+u5SH5PlLuymESQuyJb+4pfb0l8AJO4Z3dN6TFFSrLAoGBAOeR\r\n" + 
			"PUQaVGTtNGSm45trY9DYakr47y1mJcwa92X82DlwyxS3tTxNqT5KoAy6idjwO1Vf\r\n" + 
			"mVjildwC6mZAQ/dA6/4WpAwmYxNcegD23vhe5VlxrHwpCyLyIlMAFPRVIKenngZF\r\n" + 
			"v4D1w2YO/ho59CRZwuqVogT4eI9c2aVFSyjwVqjJAoGATG0Hj3nhSCLcleDYQ4eZ\r\n" + 
			"VBOOUEF3cYyJXqGZBBgECeeGheHqMeHnJ/yDXoR12boc1oLPXAGggfsOEPLKTrYV\r\n" + 
			"oO3Fx7t2BTAgwddOndg9e3EYh5C9R9y/4ZO5TDSwOugalLprk/uJi9t9Xhz6AiDx\r\n" + 
			"omr+BCh1ev0AwYKU0DyHzEkCgYEA4lSzs/ekGw05E1OGzSZ2Ze7QXTzX0OCw+sA0\r\n" + 
			"pIsv71yOMU6pJiqly4t2yVodV1reM0CACj9Ux7hVz+DqTSU4Q51vc6ylU4wkdwLT\r\n" + 
			"xaBCvFx9gNFA4EQXRlajJHh7tUIAExNi7LHI7NykgY+5TzooZ84ekIsmksDmvUFw\r\n" + 
			"B7B4kvkCgYBgJGOUSs54AHao+YkxKcRM2+Ugw1OaL6e5XgolX3/3bcO0y6egQNh/\r\n" + 
			"/a/ko52Za4CpkvG+/ZaF2HqSU1oYbZ5KvstoFT1V1iDK8Nq/XUsPKToAiZgDQKzC\r\n" + 
			"yJvD74BcHzzrylhufe47qf64perzzBH2GskwAtjr1d1OWLa2pbwhyQ==\r\n" + 
			"-----END RSA PRIVATE KEY-----";

	private String publicKey="-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5N53sfPXhzKvMjcRlG2C\r\n" + 
			"gHq8+oZu/jyq9UOhpeyAn149C2aMlPXBHBCOKPtoLByj7q4VuVV9bKsPk7LQ1myl\r\n" + 
			"gYtoW6ATisWJ3ZC3X1tApwVDaHsaNU/XjtBc/h3jmXVGKwf8N+r2MLAUpkONyENS\r\n" + 
			"0FP9XuhaN5H7SnD/i9JSM1CAqYPY9RNFxJ9cnmdnYpBocCPdwNGqHGHAq+5Gjl+M\r\n" + 
			"vDL4LutkVgKGseeRVt4sRXyAZLaCvE0NnL+h2iVryPQXZd7n74rDQAcW9Wez69uw\r\n" + 
			"VN8ml3JRhvLujSkAi7h2ml9amVblpt5Hw7i0+PTAFSGvLTTGC19jYBGn4genUeXR\r\n" + 
			"YwIDAQAB\r\n" + 
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
