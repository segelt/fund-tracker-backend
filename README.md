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


</details>

<details>
 <summary><code>POST</code> <code><b>/api/funds/v1/</b></code> <code>(Retrieves information about a single fund)</code></summary>

##### Body Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | Kod      |  required | string   | Exact symbol of the ETF  |

</details>


<details>
 <summary><code>GET</code> <code><b>/api/funds/v2/</b></code> <code>(Searches for ETFs that contain given filter)</code></summary>

##### Url Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | Kod      |  required | string   | Filter for the ETF symbols  |

</details>

<details>
 <summary><code>GET</code> <code><b>/api/historicdata/span</b></code> <code>(Gets historical data for ETFs with given time interval)</code></summary>

##### Body Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | Kod      |  required | string   | Exact Symbol for the ETF  |
> | beg      |  required | datetime   | beginning date for time interval  |
> | end      |  required | datetime   | end date for time interval  |

</details>

<details>
 <summary><code>POST</code> <code><b>/api/historicdata/batch/v1</b></code> <code>(Gets historical data for multiple ETFs with given time interval)</code></summary>

##### Body Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | {Synbol}  |  required | string   | Exact Symbol for the ETF  |
> | Time span |  required | int   | Value between 0-6. 0 = 1 wk, 1 = 1 mo, 2 = 3 mo, 3 = 1 yr, 4 = 3 yr, 5 = no limit |

##### Example Body

> | Request |
> |----------- |
> | `{"AFA" : 3,"TTE" : 3,"IPJ" : 3}` |

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

</details>

<details>
 <summary><code>POST</code> <code><b>/api/historicdata/batch/gains</b></code> <code>(For different funds, get gain ratio for each distinct time bought)</code></summary>

##### Body Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | {Symbol}  |  required | JSON   | Array of date - price and amount bought value pairs  |

##### Example Body

> | Request |
> |----------- |
> | `{"AFA" : {"12-10-2020" : [0.107567, 1000],"15-10-2020" : [0.102862, 1000]}, "IPJ" : {"12-10-2020" : [2.331948, 1000],"15-10-2020" : [2.431948, 1000]}}` |

</details>

<details>
 <summary><code>POST</code> <code><b>/api/historicdata/batch/portfolio</b></code> <code>(Groups each symbol in the parameter to obtain total price of each group, and percentage of the overall portfolio each group holds.)</code></summary>

##### Body Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | {Symbol}  |  required | JSON   | Array of date - price and amount bought value pairs  |

##### Example Body

> | Request |
> |----------- |
> | `{"NNF": {"16-05-2021": [1.03,2000],"20-05-2021" : [1.06,2000]},  "AFA": {"16-05-2021": [1.03,2000]}}` |

</details>

<details>
 <summary><code>POST</code> <code><b>/api/historicdata/sort</b></code> <code>(Sorts all ETFs based on given parameters)</code></summary>

##### Body Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | Kod       |  required | int   | Sort Direction (0 = ascending, 1 = descending order on percantage gain)                 |
> | Time span |  required | int   | Value between 0-5 to specify time range to calculate percentage gain                    |

</details>

<details>
 <summary><code>POST</code> <code><b>/api/historicdata/single/v1</b></code> <code>(Get Data for single fund)</code></summary>

##### Body Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | {Symbol} |  required | string   | Symbol to match exact ETF                                                             |
> | Time span |  required | int   | Value between 0-5 to specify time range to calculate percentage gain                    |

##### Example Body

> | Request |
> |----------- |
> | `{"TTE" : 6}` |

</details>

<details>
 <summary><code>GET</code> <code><b>/api/historicdata/</b></code> <code>(Get exact price value for single fund with exact date)</code></summary>

##### Url Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | fundCode |  required | string   | Symbol to match exact ETF                                                             |
> | Date |  required    | date   | Date of the price information to be obtained                    |

</details>