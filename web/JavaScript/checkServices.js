function checkSignUpForm(email, password, password1, firstName, lastName, age) {

    console.log(email.value);
    console.log(age.value);


    alert('PIZDEC !!!');

    //var CurrentDate= new Date();
    //var CurrentYear = CurrentDate.getFullYear();
    //var userYear = parseInt(age.value.split("-")[0]);

    //var result = CurrentYear - userYear;


    var checkEmail = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    var nameReg = /^[A-Za-z]{2,}$/;

    if (!checkEmail.test(email.value)) {
        document.querySelector('.error').innerHTML = "Invalid email";
        return (false);
    }

    if (!(password.value.length > 5 && password.value.length < 41)) {
        document.querySelector('.error').innerHTML = "Please use password from 6 to 40 characters";
        return (false);
    }

    if (password.value.localeCompare(password1.value)) {
        document.querySelector('.error').innerHTML = "Don't match passwords, check field 'repeat password'!";
        return false;
    }

    if (!(nameReg.test(firstName.value) || nameReg.test(lastName.value))) {

        document.querySelector('.error').innerHTML = "Your first name or last name can't be by one character OR not use numbers";
        return false;
    }
    if (result < 18) {
        document.querySelector('.error').innerHTML = "You are not 18 -> drochilo 0_o";
        return false;
    }
    document.querySelector('#user-age').value = result;

    return false;
}