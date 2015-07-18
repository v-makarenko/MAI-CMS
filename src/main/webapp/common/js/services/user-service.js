/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('UserService',
    function ($http) {
        var url = "api/private/user/";

        this.save = function(user){
            return $http.post(url + '', user)
        };

        this.getCurrentUser = function(){
            return $http.get(url + 'getCurrent');
        }
    }
)
;