# 블로그 검색 서비스

## 빌드 및 실행

결과물 (Executable jar) : [api-1.0-SNAPSHOT.jar]()

*빌드 및 실행 명령어*

```shell
$ ./gradlew clean build
$ java -jar api/build/libs/api-1.0-SNAPSHOT.jar
```

## 프로젝트 소개

### 프로젝트 모듈 구성

- `api` : 앱을 동작하기 위한 모듈 [core, blogsource, persistence 의존]
- `core` : 도메인, 유스케이스 모듈 [독립 모듈, 의존관계 없음]
- `blogsource-adapter` : http-client (webflux) [core 의존]
- `persistence-adapter` : database (jpa, h2) [core 의존]

*의존관계 (그림으로 표현하자)*

(UML)

### 기능

1. 블로그 검색
    - 검색어(Keyword)를 통해 블로그를 검색할 수 있습니다.
2. 인기검색어 순위
    - 인기검색어를 최대 10개까지 검색할 수 있습니다.

### 블로그 검색 API

*Request Parameters*

| Parameter | Type      | Required | Default    | Description                        |
|:----------|:----------|:---------|:-----------|:-----------------------------------|
| `keyword` | `String`  | `O`      |            | 검색어 (keyword)                      |
| `sort`    | `String`  | `X`      | `ACCURACY` | 정렬 방식 ACCURACY(정확도순), RECENCY(최신순) |
| `page`    | `Integer` | `X`      | `1`        | 페이지 번호                             |
| `size`    | `Integer` | `X`      | `10`       | 페이지 크기                             |

*Request*

```http request
GET /api/v1/blogs?keyword={keyword}
```

*Responses*

```json

```

### 인기 검색어 API

*Request Parameters*

| Parameter | Type      | Required | Default | Description       |
|:----------|:----------|:---------|:--------|:------------------|
| `top`     | `Integer` | `X`      | `10`    | 검색 키워드 수 (최대 10개) |

*Request*

```http request
GET /api/v1/blogs/statistics/popular
```

*Responses*

```json

```

---

## 사용한 오픈 소스 와 사용 목적

- QueryDSL : JPQL 동적 쿼리 목적
- (추가중)