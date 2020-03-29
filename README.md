# Flag Picker


## Build Run & Deploy
Flag-Picker requires MongoDB running at backend. MongoDB is the default data storage, it is configurable in the yml file 
 to switch to MySql. 
 
### setup MongoDB
```
docker run -d --name mongo -p 27017:27017 mongo:4.0.3
```

### Build Flag-Picker
```
./mvnw clean install -DskipTests
export SPRING_PROFILES_ACTIVE=dev
./mvnw spring-boot:run

```
### Build Docker image
```
docker build --tag flag-picker:1.0 .
```

### Deploy
```
docker run --publish 8000:8080 --name flag-picker:1.0
```

## API

### Query APIs
#### Query by Continent

Sample Request:
`http://localhost:8080/flag-picker/v1.0/flag/pick?continent-name=Asia`

Sample Response:
```
[
    {
        "name": "China",
        "flag": "🇨🇳"
    },
    {
        "name": "India",
        "flag": "🇮🇳"
    },
    {
        "name": "Indonesia",
        "flag": "🇮🇩"
    },
    {
        "name": "Pakistan",
        "flag": "🇵🇰"
    },
    {
        "name": "Bangladesh",
        "flag": "🇧🇩"
    }
]

```
#### Query by Country

Query
`http://localhost:8080/flag-picker/v1.0/flag/pick?country-name=China`

Sample Response:
```
[
    {
        "name": "China",
        "flag": "🇨🇳"
    }
]

```

### Admin APIs

#### Load Data into DB

`http://localhost:8080/flag-picker/admin/load`

#### Clear Cache

`http://localhost:8080/flag-picker/admin/clear-cache`

### System Metrics
`http://localhost:8080/flag-picker/actuator/prometheus`

### For more Detailed Info
`http://localhost:8080/flag-picker/v2/api-docs`



## About Bonus Implmentation

### Performance
Flag-Picker leverages Guava loading cache to speed up the query. Default TTL is 60 minutes. This is configurable in the yml file.
Cache could be cleared manually by Admin also. 

### Metric service
System metric service is implemented by using Spring Metric. Current implementation enables Prometheus metrics that are available
at `http://localhost:8080/flag-picker/actuator/prometheus`. Flag-Picker related metrics could be found by searching for `flag_picker_total`.
These metrics info could be retrieved by Prometheus and displayed on Grafana in the future.

### DB Schema
Current implementation is using MongoDB as storage. It's schemaless, but general idea is like below:
Country Collection
```
{
  "id": "5e8035b79cfbba604afd6f50",
  "countryName": "China",
  "continentName": "Asia",
  "flag": "🇨🇳"
}
```    
Current design also supports switch to RDBMS like MySQL. Abstract persistent layer has already been created. Based on the 
time limit, could not finish it. Schema like below:

Continent:
```
CREATE TABLE CONTINENTS (
  ID VARCHAR(100) NOT NULL, --'Continent code',
  CONTINENT_NAME VARCHAR(255),
  PRIMARY KEY (ID)
);
``` 

Country:
```
CREATE TABLE COUNTRIES (
  ID VARCHAR(100) NOT NULL, 
  NAME VARCHAR(255) NOT NULL, 
  FLAG VARCHAR(255) NOT NULL,
  CONTINENT_ID VARCHAR(100) NOT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT fk_countries_continents FOREIGN KEY (CONTINENT_ID) REFERENCES CONTINENTS (ID)
);
```

## Next Steps

### Quality
Quality is always the most important thing for an application. I wish I could have more time to write the unit tests,
as well as integrate with Spotbugs to improve the quality of the code.

### SecurityCheck
Integrate with Sping Authentication to secure the services.

### Scalability 
Integrate with Load balance tools like HAProxy to serve more requests currently.

### Monitoring
Integrate with Prometheus and Grafana for better monitoring.
