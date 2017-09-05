var UserPosition = {
    latitude: '',
    longitude: '',
    country: '',
    city: ''
};

function SignInFacebook() {
    function statusChangeCallback(response) {
        if (response.status === 'connected') {
            console.log("Logged into FB");
            testAPI();
        }
        else {
            console.log("NOT logged into Fb");
            FB.login();
        }
    }


    // function checkLoginState() {
    //     FB.getLoginStatus(function (response) {
    //         statusChangeCallback(response);
    //     });
    // }

    window.fbAsyncInit = function () {
        FB.init({
            appId: '1589374887804459',
            cookie: true,
            xfbml: true,
            version: 'v2.10'
        });


        FB.getLoginStatus(function (response) {
            statusChangeCallback(response);
        });

    };

    (function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));

    function testAPI() {
        FB.api('/me?fields=id,first_name,last_name,email,birthday,gender,picture', function (response) {
            if (response && !response.error) {
                //console.log(response);
                buildProfile(response);
            }
        })
    }


    function buildProfile(user) {
        user = checkData(user);

        setTimeout(function () {
            console.log(user);
            console.log(UserPosition);
            $.ajax({
                url: "/facebookSignin",
                type: "POST",
                data: JSON.stringify(user),
                header: {
                    "Content-type": "application/json"
                },
                success: function (data) {
                    console.log(data);
                    console.log(typeof data);
                    if (data == "OK") {
                        console.log("OK");
                        return window.location.href = "../views/userProfile.jsp";
                    }
                },
                error: function (status, data) {
                    console.log(status);
                    console.log(data);
                }
            });
        }, 2000);
    }

    function checkData(user) {
        if (user.gender == "male") {
            user.gender = 1;
        } else {
            user.gender = 0;
        }

        //getUserPosition();
        user.from = 'facebook';
        user.firstName = user.first_name;
        user.lastName = user.last_name;
        user.age = ((new Date().getTime() - new Date(user.birthday)) / (24 * 3600 * 365.25 * 1000)) | 0;
        user.latitude = UserPosition.latitude;
        user.longitude = UserPosition.longitude;
        user.city = UserPosition.city;
        user.country = UserPosition.country;
        user.littlePhoto = user.picture.data.url;
        user.fbId = user.id;
        user.photo = "https://graph.facebook.com/" + user.id + "/picture?type=large&width=720&height=720";


        delete user.id;
        delete user.picture;
        delete user.first_name;
        delete user.last_name;
        delete user.birthday;

        return user;
    }
}

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