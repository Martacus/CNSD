var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "10",
        "ok": "0",
        "ko": "10"
    },
    "minResponseTime": {
        "total": "7",
        "ok": "-",
        "ko": "7"
    },
    "maxResponseTime": {
        "total": "41",
        "ok": "-",
        "ko": "41"
    },
    "meanResponseTime": {
        "total": "12",
        "ok": "-",
        "ko": "12"
    },
    "standardDeviation": {
        "total": "10",
        "ok": "-",
        "ko": "10"
    },
    "percentiles1": {
        "total": "9",
        "ok": "-",
        "ko": "9"
    },
    "percentiles2": {
        "total": "9",
        "ok": "-",
        "ko": "9"
    },
    "percentiles3": {
        "total": "27",
        "ok": "-",
        "ko": "27"
    },
    "percentiles4": {
        "total": "38",
        "ok": "-",
        "ko": "38"
    },
    "group1": {
    "name": "t < 500 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "500 ms < t < 2000 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 2000 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 10,
    "percentage": 100
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1",
        "ok": "-",
        "ko": "1"
    }
},
contents: {
"req_get-tracks-684a6": {
        type: "REQUEST",
        name: "get tracks",
path: "get tracks",
pathFormatted: "req_get-tracks-684a6",
stats: {
    "name": "get tracks",
    "numberOfRequests": {
        "total": "10",
        "ok": "0",
        "ko": "10"
    },
    "minResponseTime": {
        "total": "7",
        "ok": "-",
        "ko": "7"
    },
    "maxResponseTime": {
        "total": "41",
        "ok": "-",
        "ko": "41"
    },
    "meanResponseTime": {
        "total": "12",
        "ok": "-",
        "ko": "12"
    },
    "standardDeviation": {
        "total": "10",
        "ok": "-",
        "ko": "10"
    },
    "percentiles1": {
        "total": "9",
        "ok": "-",
        "ko": "9"
    },
    "percentiles2": {
        "total": "9",
        "ok": "-",
        "ko": "9"
    },
    "percentiles3": {
        "total": "27",
        "ok": "-",
        "ko": "27"
    },
    "percentiles4": {
        "total": "38",
        "ok": "-",
        "ko": "38"
    },
    "group1": {
    "name": "t < 500 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "500 ms < t < 2000 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 2000 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 10,
    "percentage": 100
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1",
        "ok": "-",
        "ko": "1"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
