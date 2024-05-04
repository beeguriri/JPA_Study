# JPA_Study
자바ORM표준 JPA 프로그래밍 책을 읽으면서 공부하는 중입니다!

## 개발환경
- SpringBoot `3.2.5`
- gradle `8.7`
- java `17`
- DB : `H2`
- Depencency : `Spring Data JPA`, `querydsl` `Lombok` `Validation` `Thymeleaf`
- 그외 : `jquery` `bootstrap` `sweetalert2`

## querydsl 설정 (springboot 3.0 이상)
```xml
//dependency 추가
implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta'
annotationProcessor "com.querydsl:querydsl-apt:${dependencyManagement.importedProperties['querydsl.version']}:jakarta"
annotationProcessor "jakarta.annotation:jakarta.annotation-api"
annotationProcessor "jakarta.persistence:jakarta.persistence-api"

//script 작성
def querydslDir = "src/main/generated"

sourceSets {
    main.java.srcDirs += [ querydslDir ]
}

tasks.withType(JavaCompile) {
    options.getGeneratedSourceOutputDirectory().set(file(querydslDir))
}

clean.doLast {
    file(querydslDir).deleteDir()
}

//gradle 실행순서
//Gradle > Tasks > build > clean
//Gradle > Tasks > build > build
```
