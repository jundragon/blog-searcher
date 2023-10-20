# 블로그 검색 서비스

## 빌드 및 실행

결과물 (Executable jar) : [api-1.0-SNAPSHOT.jar]()

*빌드 및 실행 명령어*

```shell
$ ./gradlew clean build
$ docker-compose -f ./docker/docker-compose.yml up -d
$ java -jar api/build/libs/api-1.0-SNAPSHOT.jar
```

동시성 처리를 위해 도입한 kafka를 구동시켜야 합니다.

*대시보드*
kafka 토픽 확인 : http://localhost:9000

h2 데이터베이스 확인 : http://localhost:8080/h2-console

## 프로젝트 소개

### 프로젝트 모듈 구성

- `api` : 앱을 동작하기 위한 모듈 [core, blogsource, persistence, consummer 의존성]
- `core` : 도메인, 유스케이스 모듈 [독립 모듈, 의존관계 없음]
- `blogsource-adapter` : http-client (webflux) [core 의존성]
- `persistence-adapter` : database (jpa, h2) [core 의존성]
- `consummer-adapter` : kafka 메시지 큐 [cord 의존성]

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
{
  "result": {
    "code": "200",
    "message": "성공",
    "description": "성공"
  },
  "body": {
    "documents": [
      {
        "title": "김호중 &#39;<b>테스</b>형!!&#39; 유튜브 조회 수 300만 뷰!",
        "contents": "김호중 &#39;<b>테스</b>형!!&#39; 유튜브 조회 수 300만 뷰! . 2023년 9월27일 수요일 포스팅주제 ​김호중 [불후의명곡 &#39;<b>테스</b>형!&#39;] 유튜브 조회 수 삼백만 뷰 돌파 축하 ’불후의 명곡 2023 상반기 왕중왕전&#39; 최종 우승곡 <b>테스</b>형!!! ​ 김호중 가수님은 자기만의 스타일로 완벽하게 재해석, 독보적 천상의 목소리로 첫 소절 부터 관중을...",
        "url": "https://kimej004.tistory.com/1517",
        "blogName": "참사랑 블로그",
        "thumbnail": "https://search1.kakaocdn.net/argon/130x130_85_c/5vD1td4LEID",
        "createdAt": "2023-09-27T03:18:40"
      },
      ...
    ],
    "pagination": {
      "hasNextPage": true,
      "nextPage": 2,
      "currentPage": 1,
      "totalCount": 793,
      "size": 10
    }
  }
}
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
{
  "result": {
    "code": "200",
    "message": "성공",
    "description": "성공"
  },
  "body": [
    {
      "keyword": "테라포밍마스",
      "count": 1001
    }
  ]
}
```

---

## 고민한 내용들

- 트래픽이 많고, 저장되어 있는 데이터가 많음을 염두에 둔 구현에 대한 고민
    - 데이터가 많다고 가정한다면, Keyword 는 메모리 DB에 저장하기 어렵다고 판단함
    - 동시성을 제어하기 위해 Kafka 메시지 큐로 키워드 수집을 비동기, 순차적으로 수행하도록 하였음
    - Keyword에 긴문장이나 오타가 있다면 업무적으로 가치가 적은 데이터가 쌓일 것이라고 생각하여 문장을 단어 단위로 저장하는 기능을 추가 (이후 오타 보정기능 까지 추가한다면 좋을 것)
- core 모듈의 *'도메인과 유스케이스'* 는 최대한 자바 코드만으로 테스트를 할 수 있게 노력 (스몰테스트)

## 아쉬운 부분 (회고)

- 멀티 모듈, 비동기에 대한 이해가 부족함을 느꼈습니다. (특히 테스트..)
- 문제를 해결해가면서 구성이 점점 복잡해지는데, 통합 테스트를 간편히 할 수 있는 방법을 고민해야 될 것 같습니다. (외부 설정에 따라 깨지기 쉬운 테스트라서 관리가 어려울 것 같기도 하지만..)

## 사용한 오픈 소스 와 사용 목적

- `lombok` : 개발 편의성
- `QueryDSL` : JPQL 동적 쿼리 목적
- `Resilience4j` : 카카오 블로그 검색 API에 장애가 발생한 경우, 네이버 블로그 검색 API를 통해 데이터 제공
- `com.github.shin285:KOMORAN:3.3.9` : 문장 검색시 단어로 토큰화 하여 분석에 사용되도록 하기 위함
- `io.projectreactor:reactor-test` : webflux 테스트