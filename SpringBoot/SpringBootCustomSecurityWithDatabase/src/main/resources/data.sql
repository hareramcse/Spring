  INSERT INTO users (username, password, full_name, role, country, enabled) VALUES
	('hare', '$2a$10$1SY9DwqFhRRDNhmBuon0A.vMbmbN1HbbalxtVAilRHwUDlP1piXwe', 'Hareram Singh', 'ROLE_ADMIN', 'US', 'Y'),
	('raghu', '$2a$10$EMPr/Cwr9AFqGNMBQz2Fo.IFsPvzAdNtKBeqMhSnuOw0TBt5y9Qg2', 'Raghu Raj', 'ROLE_USER', 'India', 'Y'); 

   INSERT INTO topics (topic_id, title, category) VALUES
	(1, 'Spring Rest Boot', 'Spring Boot'),
	(2, 'Spring Boot Security', 'Spring Boot'),
	(3, 'Spring MVC Framework', 'Spring Framework');