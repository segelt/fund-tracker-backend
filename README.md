# fund-tracker-backend

This project serves as an API to provide information about various Turkish ETFs. It is written in Spring framework and it is developed be consumed by [fund-tracker-app](https://github.com/wozyn/fund-tracker-appd) mobile application. The API is used to display information about different ETFs, calculate unrealized gains/losses, to search for different ETFs, to provide a summary of client's portfolio and to provide other related services to the consumer.

Data is being stored in a PostgreSql database, and Hibernate ORM is used to map the application models with database tables.

------------------------------------------------------------------

<details>
 <summary><code>GET</code> <code><b>/api/funds/</b></code> <code>(Gets a list of information about all of funds available in the database)</code></summary>

##### Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | None      |  required | object (JSON or YAML)   | N/A  |


##### Responses

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `text/plain;charset=UTF-8`        | `List of all ETFs`                                |

</details>

<details>
 <summary><code>POST</code> <code><b>/api/funds/v1/</b></code> <code>(Retrieves information about a single fund)</code></summary>

##### Body Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | Kod      |  required | string   | Exact symbol of the ETF  |


##### Responses

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `text/plain;charset=UTF-8`        | `{ "fonKodu": "NNF","companyType": 1, "fonAdi": "HEDEF PORTFÖY BİRİNCİ HİSSE SENEDİ FONU (HİSSE SENEDİ YOĞUN FON)", "fonTuru": "Hisse Senedi Fonu", "fonTipi": "F"}`                                |

</details>


<details>
 <summary><code>GET</code> <code><b>/api/funds/v2/{Kod}</b></code> <code>(Searches for ETFs that contain given filter)</code></summary>

##### Url Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | Kod      |  required | string   | Filter for the ETF symbols  |


##### Responses

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `text/plain;charset=UTF-8`        | `[{"fonKodu": "ACY","companyType": 1,"fonAdi": "ACTUS PORTFÖY 2020 YATIRIM DÖNEMLİ DEĞİŞKEN FON","fonTuru": "Değişken Fon","fonTipi": "F"},...}`                                |

</details>

<details>
 <summary><code>GET</code> <code><b>/api/historicdata/span</b></code> <code>(Gets historical data for ETFs with given time interval)</code></summary>

##### Body Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | Kod      |  required | string   | Exact Symbol for the ETF  |
> | beg      |  required | datetime   | beginning date for time interval  |
> | end      |  required | datetime   | end date for time interval  |


##### Responses

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `text/plain;charset=UTF-8`        | `Array of price information for the specified ETF, for each date in the time interval`                                |

</details>

<details>
 <summary><code>GET</code> <code><b>/api/historicdata/batch/v1</b></code> <code>(Gets historical data for multiple ETFs with given time interval)</code></summary>

##### Body Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | {Synbol}  |  required | string   | Exact Symbol for the ETF  |
> | Time span |  required | int   | Value between 0-6. 0 = 1 wk, 1 = 1 mo, 2 = 3 mo, 3 = 1 yr, 4 = 3 yr, 5 = no limit |

##### Example Body

> | Request |
> |----------- |
> | `{"AFA" : 3,"TTE" : 3,"IPJ" : 3}` |



##### Responses

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `text/plain;charset=UTF-8`        | `Array of price information for the given ETFs in key, for each date in the time given time spans`                                |

</details>

<details>
 <summary><code>GET</code> <code><b>/api/historicdata/batch/v2</b></code> <code>(Gets historical data for multiple ETFs with given start time)</code></summary>

##### Body Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | {Synbol}  |  required | string   | Exact Symbol for the ETF  |
> | {date} |  required | datetime   | beginning date |

##### Example Body

> | Request |
> |----------- |
> | `{"AFA" : "10-10-2020","TTE" : "25-08-2020","IPJ" : "01-01-2021"}` |

##### Responses

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `text/plain;charset=UTF-8`        | `Array of price information for the given ETFs in key, for each date starting from the beginning time`                                |

</details>

<details>
 <summary><code>GET</code> <code><b>/api/historicdata/latest</b></code> <code>(Gets latest price information for ETF)</code></summary>

##### Body Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | Kod  |  required | string   | Exact Symbol for the ETF  |

##### Example Body

> | Request |
> |----------- |
> | `{"Kod" : "IPJ"}` |

##### Responses

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `text/plain;charset=UTF-8`        | `Latest price information for the given ETF`                        |

</details>