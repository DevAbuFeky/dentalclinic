function checkPasswordMatch() {
    const password = $("#txtNewPassword").val();
    const confirmPassword = $("#txtConfirmPassword").val();

    if(password === "" && confirmPassword ==="") {
        $("#checkPasswordMatch").html("");
        $("#updateUserInfoButton").prop('disabled', false);
    } else {
        if(password !== confirmPassword) {
            $("#checkPasswordMatch").html("Passwords do not match!");
            $("#updateUserInfoButton").prop('disabled', true);
        } else {
            $("#checkPasswordMatch").html("Passwords match");
            $("#updateUserInfoButton").prop('disabled', false);
        }
    }
}