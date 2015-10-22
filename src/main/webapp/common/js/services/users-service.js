/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('UsersService',
    function ($http) {
        var url = "api/private/user/";

        this.save = function(user){
            return $http.put(url, user)
        };

        this.getCurrentUser = function(){
            return $http.get(url + 'getCurrent');
        };

        this.getAllUsers = function(){
            return $http.get(url + 'getAll');
        };
    }
)
;