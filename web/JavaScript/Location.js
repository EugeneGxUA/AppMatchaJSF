function checkLocation() {
    $('input[name="profileForm:j_idt30"]').attr('readonly', 'readonly');
    $('input[name="profileForm:j_idt32"]').attr('readonly', 'readonly');
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
    setTimeout(function(){
        console.log(UserPosition.city);
        console.log(UserPosition.country);
        console.log(UserPosition.latitude);
        console.log(UserPosition.longitude);
        document.getElementById('profileForm:latitude').value = UserPosition.latitude;
        document.getElementById('profileForm:longitude').value = UserPosition.longitude;
        document.getElementById('profileForm:city').value = UserPosition.city;
        document.getElementById('profileForm:country').value = UserPosition.country;
    }, 2000);


}