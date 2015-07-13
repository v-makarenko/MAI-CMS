/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('AuthService',
    function ($http, $q) {
        var url = "api/auth/";

        this.login = function (username, password) {

            return $http.post(url + "login",
                {
                    username: username,
                    password: password
                }
            )

        };

        this.logout = function () {
            return $http.post(url + "logout", {});
        };
        this.isAuthenticated = function () {
            return $http.get(url + "isAuthenticated", {});
        };
        this.getRoles = function () {
            var deferred = $q.defer();
            deferred.resolve({
                result: 'OK',
                msg: undefined,
                data: [{
                    id: 1,
                    role: 'VIEW_ORDERS'
                }, {
                    id: 2,
                    role: 'VIEW_STATS'
                }, {
                    id: 3,
                    role: 'VIEW_MENU_EDIT'
                },]
            });
            return deferred.promise;
        };

        this.signUp = function (newUser) {
            $http.post(url + 'signUp', newUser)
        };

        this.getCurrentUser = function(){
            return $http.get(
                   url + 'getCurrentUser'
            );
        }
    }
)
;