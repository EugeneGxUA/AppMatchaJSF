function checkLocation() {
    var UserPosition = {
        latitude: '',
        longitude: '',
        country: '',
        city: ''
    };


    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            UserPosition.latitude = position.coords.latitude;
            UserPosition.longitude = position.coords.longitude;
        });
    }
    if (UserPosition.latitude && UserPosition.longitude) {
        $.getJSON('https://geoip-db.com/json/geoip.php?jsonp=?').done(function (location) {
            UserPosition.country = location.country_name;
            UserPosition.city = location.city;
        });
    } else {
        $.getJSON('https://geoip-db.com/json/geoip.php?jsonp=?').done(function (location) {
            UserPosition.latitude = location.latitude;
            UserPosition.longitude = location.longitude;
            UserPosition.country = location.country_name;
            UserPosition.city = location.city;
        });
    }

    $.ajax({
        url: requestContextPath + '/profileSecured/profile.xhtml',
        type: "GET",
        data: {
            "longitude": UserPosition.longitude,
            "latitude": UserPosition.latitude,
            "city": UserPosition.city,
            "country": UserPosition.country
        },
        dataType: "json",
        success: function (data) {
            $.each(data, function (i, station) {
            console.log(i);
            console.log(station);
            });
        },
        error: function () {
        console.log("error");
        }
    });
}