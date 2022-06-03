import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.ecommerce"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}


dependencies {
	implementation("org.springframework.boot:spring-boot-gradle-plugin:2.7.0")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation:2.6.7")
	implementation("org.springdoc:springdoc-openapi-data-rest:1.6.7")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.7")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.6.7")
	implementation("io.springfox:springfox-swagger2:3.0.0")
	implementation("io.springfox:springfox-swagger-ui:3.0.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.boot:spring-boot-starter-mail:2.6.7")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.security:spring-security-jwt:1.1.1.RELEASE")
	implementation("org.springframework.security:spring-security-crypto:5.6.3")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:1.12.3")
	testImplementation ("org.mockito:mockito-core:3.+")
}
dependencies {
	implementation("junit:junit:4.13.2")
	implementation("org.junit.jupiter:junit-jupiter:5.8.2")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
	implementation("commons-io:commons-io:2.11.0")
	implementation("io.jsonwebtoken:jjwt:0.9.1")
}

tasks.withType<Test> {
	useJUnitPlatform()
}