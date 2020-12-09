var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "10",
        "ok": "10",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "10",
        "ok": "10",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "543",
        "ok": "543",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "65",
        "ok": "65",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "159",
        "ok": "159",
        "ko": "-"
    },
    "percentiles1": {
        "total": "12",
        "ok": "12",
        "ko": "-"
    },
    "percentiles2": {
        "total": "14",
        "ok": "14",
        "ko": "-"
    },
    "percentiles3": {
        "total": "305",
        "ok": "305",
        "ko": "-"
    },
    "percentiles4": {
        "total": "495",
        "ok": "495",
        "ko": "-"
    },
    "group1": {
    "name": "t < 500 ms",
    "count": 9,
    "percentage": 90
},
    "group2": {
    "name": "500 ms < t < 2000 ms",
    "count": 1,
    "percentage": 10
},
    "group3": {
    "name": "t > 2000 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1",
        "ok": "1",
        "ko": "-"
    }
},
contents: {
"req_get-albums-49369": {
        type: "REQUEST",
        name: "get albums",
path: "get albums",
pathFormatted: "req_get-albums-49369",
stats: {
    "name": "get albums",
    "numberOfRequests": {
        "total": "10",
        "ok": "10",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "10",
        "ok": "10",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "543",
        "ok": "543",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "65",
        "ok": "65",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "159",
        "ok": "159",
        "ko": "-"
    },
    "percentiles1": {
        "total": "12",
        "ok": "12",
        "ko": "-"
    },
    "percentiles2": {
        "total": "14",
        "ok": "14",
        "ko": "-"
    },
    "percentiles3": {
        "total": "305",
        "ok": "305",
        "ko": "-"
    },
    "percentiles4": {
        "total": "495",
        "ok": "495",
        "ko": "-"
    },
    "group1": {
    "name": "t < 500 ms",
    "count": 9,
    "percentage": 90
},
    "group2": {
    "name": "500 ms < t < 2000 ms",
    "count": 1,
    "percentage": 10
},
    "group3": {
    "name": "t > 2000 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1",
        "ok": "1",
        "ko": "-"
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
