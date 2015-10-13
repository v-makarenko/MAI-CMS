/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('AuthService',
    function ($http) {
        var url = "api/auth/";

        this.login = function (user) {

            return $http.post(url + "login", user
            )

        };

        this.logout = function () {
            return $http.post(url + "logout", {});
        };
        this.isAuthenticated = function () {
            return $http.get(url + "isAuthenticated", {});
        };

        this.signUp = function (newUser) {
            return $http.post(url + 'signUp', newUser)
        };

        this.getCurrentUser = function(){
            return $http.get(
                   url + 'getCurrentUser'
            );
        };

        this.restorePassword = function(email){
            return $http.post(url + 'restorePassword', email);
        };

    }
);